package com.exasol.parquetio.reader;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.LongStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.column.page.PageReadStore;
import org.apache.parquet.filter2.compat.FilterCompat;
import org.apache.parquet.hadoop.ParquetFileReader;
import org.apache.parquet.hadoop.util.HadoopInputFile;
import org.apache.parquet.io.*;
import org.apache.parquet.io.api.RecordMaterializer;

import com.exasol.errorreporting.ExaError;
import com.exasol.parquetio.data.*;
import com.exasol.parquetio.merger.ChunkIntervalMerger;

/**
 * A Parquet file reader that reads only provided row groups.
 */
public class RowParquetChunkReader {
    private static final String CHECK_FILE_MITIGATION = "Please make sure that the file is valid and not corrupted.";

    private final InputFile file;
    private final List<ChunkInterval> chunks;
    private final MessageColumnIO messageIO;
    private final RecordMaterializer<Row> recordMaterializer;

    /**
     * Creates a new instance of {@link RowParquetChunkReader}.
     *
     * Since no chunks are provided it reads all row groups of given file.
     *
     * @param file a Parquet file
     */
    public RowParquetChunkReader(final InputFile file) {
        this(file, List.of(new ChunkIntervalImpl(0L, getRowGroupSize(file))));
    }

    /**
     * Creates a new instance of {@link RowParquetChunkReader}.
     *
     * @param file  a Parquet file
     * @param start a starting row group position
     * @param end   an ending row group position (exclusive)
     */
    public RowParquetChunkReader(final InputFile file, final long start, final long end) {
        this(file, List.of(new ChunkIntervalImpl(start, end)));
    }

    /**
     * Creates a new instance of {@link RowParquetChunkReader}.
     *
     * @param file   a Parquet file
     * @param chunks a list of row group chunks
     */
    public RowParquetChunkReader(final InputFile file, final List<ChunkInterval> chunks) {
        this.file = file;
        if (chunks == null || chunks.isEmpty()) {
            throw new IllegalArgumentException(
                    ExaError.messageBuilder("E-PIOJ-5").message("Chunk intervals list is empty.")
                            .mitigation("Please provide a valid list of Parquet file chunks.").toString());
        }
        this.chunks = new ChunkIntervalMerger().sortAndMerge(chunks);
        final var readSupport = new RowReadSupport();
        try (final var reader = ParquetFileReader.open(file)) {
            final var conf = getConfiguration(file);
            final var schema = reader.getFooter().getFileMetaData().getSchema();
            final var readContext = readSupport.init(conf, Collections.emptyMap(), schema);
            this.recordMaterializer = readSupport.prepareForRead(conf, Collections.emptyMap(), schema, readContext);
            this.messageIO = new ColumnIOFactory(reader.getFooter().getFileMetaData().getCreatedBy())//
                    .getColumnIO(readContext.getRequestedSchema(), schema, true);
        } catch (final IOException exception) {
            throw new UncheckedIOException(getFileReadingErrorMessage(file), exception);
        } catch (final RuntimeException exception) {
            throw new IllegalStateException(getFileReadingErrorMessage(file), exception);
        }
    }

    private Configuration getConfiguration(final InputFile file) {
        if (file instanceof HadoopInputFile) {
            return new Configuration(((HadoopInputFile) file).getConfiguration());
        } else {
            return new Configuration();
        }
    }

    private static long getRowGroupSize(final InputFile file) {
        try (final var reader = ParquetFileReader.open(file)) {
            return reader.getRowGroups().size();
        } catch (final IOException exception) {
            throw new UncheckedIOException(getFileReadingErrorMessage(file), exception);
        } catch (final RuntimeException exception) {
            throw new IllegalStateException(ExaError.messageBuilder("E-PIOJ-3")
                    .message("Error getting row group size from a Parquet {{FILE}} file.", file.toString())
                    .mitigation(CHECK_FILE_MITIGATION).toString(), exception);
        }
    }

    /**
     * Get an iterator that reads the rows.
     * 
     * @return iterator
     */
    public RowIterator iterator() {
        return new RowIterator(getReader(), this.file, this.messageIO, this.recordMaterializer, this.chunks);
    }

    private PositionAwareReader getReader() {
        try {
            return new PositionAwareReader(ParquetFileReader.open(this.file));
        } catch (final IOException exception) {
            throw new UncheckedIOException(getFileReadingErrorMessage(this.file), exception);
        }
    }

    /**
     * Reads row group chunks of a Parquet file.
     *
     * @param rowConsumer a consumer function that accept read data
     */
    // [impl->dsn~read-parquet-file-chunks-contents~1]
    public void read(final Consumer<Row> rowConsumer) {
        try (final RowIterator iterator = iterator()) {
            iterator.forEachRemaining(rowConsumer);
        } catch (final IOException exception) {
            throw new UncheckedIOException(
                    ExaError.messageBuilder("E-PIOJ-6").message("Failed to close reader.").toString(), exception);
        }
    }

    /**
     * Iterator that reads the rows of a given list of chunks.
     */
    public static class RowIterator implements Iterator<Row>, AutoCloseable {
        private final PositionAwareReader reader;
        private final InputFile file;
        private final MessageColumnIO messageIO;
        private final RecordMaterializer<Row> recordMaterializer;
        private final Iterator<ChunkInterval> chunkIterator;
        private Iterator<Long> rowGroupIterator;
        private Iterator<Row> rowInRowGroupIterator;
        private Row next;
        private boolean hasNext = true;

        /**
         * Create a new instance of {@link RowIterator}.
         * 
         * @param reader             reader
         * @param file               file
         * @param messageIO          message io
         * @param recordMaterializer record materializer
         * @param chunks             chunks to read
         */
        public RowIterator(final PositionAwareReader reader, final InputFile file, final MessageColumnIO messageIO,
                final RecordMaterializer<Row> recordMaterializer, final List<ChunkInterval> chunks) {
            this.reader = reader;
            this.file = file;
            this.messageIO = messageIO;
            this.recordMaterializer = recordMaterializer;
            this.chunkIterator = chunks.iterator();
            loadNext();
        }

        @Override
        public boolean hasNext() {
            return this.hasNext;
        }

        @Override
        public Row next() {
            if (!this.hasNext) {
                throw new NoSuchElementException();
            }
            final Row nextCache = this.next;
            loadNext();
            return nextCache;
        }

        private void loadNext() {
            try {
                while (this.rowInRowGroupIterator == null || !this.rowInRowGroupIterator.hasNext()) {
                    while (this.rowGroupIterator == null || !this.rowGroupIterator.hasNext()) {
                        if (!this.chunkIterator.hasNext()) {
                            this.hasNext = false;
                            return;
                        }
                        final ChunkInterval chunk = this.chunkIterator.next();
                        this.reader.moveToRowGroupPosition(chunk.getStartPosition());
                        final long endPosition = chunk.getEndPosition();
                        this.rowGroupIterator = LongStream.range(this.reader.getCurrentRowGroup(), endPosition)
                                .iterator();
                    }
                    this.rowGroupIterator.next();
                    final PageReadStore pageStore = this.reader.readNextRowGroup();
                    final RecordReader<Row> recordReader = this.messageIO.getRecordReader(pageStore,
                            this.recordMaterializer, FilterCompat.NOOP);
                    this.rowInRowGroupIterator = new RecordIterator(recordReader, pageStore.getRowCount(),
                            this.file.toString());
                }
                this.next = this.rowInRowGroupIterator.next();
            } catch (final IOException exception) {
                throw new UncheckedIOException(getFileReadingErrorMessage(this.file), exception);
            }
        }

        @Override
        public void close() throws IOException {
            this.reader.close();
        }
    }

    private static class PositionAwareReader implements AutoCloseable {
        private final ParquetFileReader reader;
        private long currentRowGroup;

        /**
         * Create a new instance of {@link PositionAwareReader}.
         * 
         * @param reader reader to wrap
         */
        public PositionAwareReader(final ParquetFileReader reader) {
            this.reader = reader;
            this.currentRowGroup = 0;
        }

        /**
         * Move to a given position.
         * <p>
         * Can only move forward.
         * </p>
         * 
         * @param startPosition position to move to
         */
        public void moveToRowGroupPosition(final long startPosition) {
            long position = this.currentRowGroup;
            while (position < startPosition) {
                this.reader.skipNextRowGroup();
                position += 1;
            }
            this.currentRowGroup = position;
        }

        /**
         * Read the next row group.
         * 
         * @return read row group
         * @throws IOException if read fails
         */
        public PageReadStore readNextRowGroup() throws IOException {
            this.currentRowGroup++;
            return this.reader.readNextRowGroup();
        }

        /**
         * Get the current row group number.
         * 
         * @return current row group number
         */
        public long getCurrentRowGroup() {
            return this.currentRowGroup;
        }

        @Override
        public void close() throws IOException {
            this.reader.close();
        }
    }

    /**
     * Iterator that reads the rows of a row group.
     */
    protected static class RecordIterator implements Iterator<Row> {
        private final RecordReader<Row> recordReader;
        private final long totalRows;
        private final String fileNameForLogging;
        boolean hasNext;
        private long currentRow = 0;
        private Row next;

        /**
         * Create a new instance of {@link RecordIterator}.
         * 
         * @param recordReader       reader
         * @param totalRows          total rows
         * @param fileNameForLogging file name, used in exception message
         */
        public RecordIterator(final RecordReader<Row> recordReader, final long totalRows,
                final String fileNameForLogging) {
            this.recordReader = recordReader;
            this.totalRows = totalRows;
            this.fileNameForLogging = fileNameForLogging;
            loadNext();
        }

        @Override
        public boolean hasNext() {
            return this.hasNext;
        }

        @Override
        public Row next() {
            if (!this.hasNext) {
                throw new NoSuchElementException();
            }
            final Row result = this.next;
            loadNext();
            return result;
        }

        private void loadNext() {
            do {
                if (this.currentRow >= this.totalRows) {
                    this.hasNext = false;
                    return;
                }
                this.currentRow += 1;
                try {
                    this.next = this.recordReader.read();
                } catch (final RecordMaterializer.RecordMaterializationException exception) {
                    throw new ParquetDecodingException(
                            ExaError.messageBuilder("F-PIOJ-2")
                                    .message("Failed to materialize a record from the Parquet file {{FILE}}.",
                                            this.fileNameForLogging)
                                    .mitigation(CHECK_FILE_MITIGATION).toString(),
                            exception);
                }
                if (this.next == null) { // Only happens with FilteredRecordReader at end of block
                    this.hasNext = false;
                    return;
                }
            } while (this.recordReader.shouldSkipCurrentRecord());
            this.hasNext = true;
        }
    }

    private static String getFileReadingErrorMessage(final InputFile file) {
        return ExaError.messageBuilder("E-PIOJ-1").message("Failed to read Parquet file {{FILE}}.", file.toString())
                .mitigation(CHECK_FILE_MITIGATION).toString();
    }
}
