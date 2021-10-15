package com.exasol.parquetio.reader;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import com.exasol.errorreporting.ExaError;
import com.exasol.parquetio.data.ChunkInterval;
import com.exasol.parquetio.data.ChunkIntervalImpl;
import com.exasol.parquetio.data.Row;
import com.exasol.parquetio.merger.ChunkIntervalMerger;

import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.column.page.PageReadStore;
import org.apache.parquet.filter2.compat.FilterCompat;
import org.apache.parquet.hadoop.ParquetFileReader;
import org.apache.parquet.hadoop.util.HadoopInputFile;
import org.apache.parquet.io.ColumnIOFactory;
import org.apache.parquet.io.InputFile;
import org.apache.parquet.io.MessageColumnIO;
import org.apache.parquet.io.ParquetDecodingException;
import org.apache.parquet.io.RecordReader;
import org.apache.parquet.io.api.RecordMaterializer;

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
        } catch (IOException exception) {
            throw new UncheckedIOException(getFileReadingErrorMessage(file), exception);
        } catch (RuntimeException exception) {
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
        } catch (IOException exception) {
            throw new UncheckedIOException(getFileReadingErrorMessage(file), exception);
        } catch (RuntimeException exception) {
            throw new IllegalStateException(ExaError.messageBuilder("E-PIOJ-3")
                    .message("Error getting row group size from a Parquet {{FILE}} file.", file.toString())
                    .mitigation(CHECK_FILE_MITIGATION).toString(), exception);
        }
    }

    /**
     * Reads row group chunks of a Parquet file.
     *
     * @param rowConsumer a consumer function that accept read data
     */
    // [impl->dsn~read-parquet-file-chunks-contents~1]
    public void read(final Consumer<Row> rowConsumer) {
        try (var reader = ParquetFileReader.open(this.file)) {
            long currentRowGroup = 0;
            for (final ChunkInterval chunk : chunks) {
                currentRowGroup = moveToRowGroupPosition(reader, currentRowGroup, chunk.getStartPosition());
                final long endPosition = chunk.getEndPosition();
                while (currentRowGroup < endPosition) {
                    final PageReadStore pageStore = reader.readNextRowGroup();
                    currentRowGroup += 1;
                    consumeRows(pageStore, rowConsumer);
                }
            }
        } catch (IOException exception) {
            throw new UncheckedIOException(getFileReadingErrorMessage(this.file), exception);
        }
    }

    private long moveToRowGroupPosition(final ParquetFileReader reader, final long currentPosition,
            final long startPosition) {
        long position = currentPosition;
        while (position < startPosition) {
            reader.skipNextRowGroup();
            position += 1;
        }
        return position;
    }

    private void consumeRows(final PageReadStore pageStore, final Consumer<Row> rowConsumer) {
        final RecordReader<Row> recordReader = messageIO.getRecordReader(pageStore, recordMaterializer,
                FilterCompat.NOOP);
        consumeRecords(recordReader, pageStore.getRowCount(), rowConsumer);
    }

    /**
     * Consumes rows from record reader.
     *
     * This similar how Parquet reads records internally, you can check it out in detail.
     *
     * @see <a href=
     *      "https://github.com/apache/parquet-mr/blob/master/parquet-hadoop/src/main/java/org/apache/parquet/hadoop/InternalParquetRecordReader.java#L217">InternalParquetRecordReader.java#L217</a>
     *
     * @param recordReader a Parquet record reader
     * @param totalRows    a total number rows to consume
     * @param rowConsumer  a {@link Consumer} that accept {@link Row}s
     */
    protected void consumeRecords(final RecordReader<Row> recordReader, final long totalRows,
            final Consumer<Row> rowConsumer) {
        long currentRow = 0;
        Row row;
        while (currentRow < totalRows) {
            currentRow += 1;
            try {
                row = recordReader.read();
            } catch (RecordMaterializer.RecordMaterializationException exception) {
                throw new ParquetDecodingException(ExaError.messageBuilder("F-PIOJ-2")
                        .message("Failed to materialize a record from the Parquet file {{FILE}}.", this.file.toString())
                        .mitigation(CHECK_FILE_MITIGATION).toString(), exception);
            }
            if (row == null) { // Only happens with FilteredRecordReader at end of block
                break;
            }
            if (!recordReader.shouldSkipCurrentRecord()) {
                rowConsumer.accept(row);
            }
        }
    }

    private static String getFileReadingErrorMessage(final InputFile file) {
        return ExaError.messageBuilder("E-PIOJ-1").message("Failed to read Parquet file {{FILE}}.", file.toString())
                .mitigation(CHECK_FILE_MITIGATION).toString();
    }

}
