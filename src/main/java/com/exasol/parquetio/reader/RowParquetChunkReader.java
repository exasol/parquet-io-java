package com.exasol.parquetio.reader;

import com.exasol.errorreporting.ExaError;
import com.exasol.parquetio.data.ChunkInterval;
import com.exasol.parquetio.data.Interval;
import com.exasol.parquetio.data.Row;
import com.exasol.parquetio.io.BaseResourceCloseable;
import com.exasol.parquetio.io.CloseableIterable;
import com.exasol.parquetio.io.CloseableIterator;
import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.column.page.PageReadStore;
import org.apache.parquet.filter2.compat.FilterCompat;
import org.apache.parquet.hadoop.ParquetFileReader;
import org.apache.parquet.hadoop.metadata.BlockMetaData;
import org.apache.parquet.hadoop.util.HadoopInputFile;
import org.apache.parquet.io.*;
import org.apache.parquet.io.api.RecordMaterializer;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A Parquet file reader that reads only provided row groups.
 */
public class RowParquetChunkReader extends BaseResourceCloseable implements CloseableIterable<Row> {

    private static final String CHECK_FILE_MITIGATION = "Please make sure that the file is valid and not corrupted.";

    private final InputFile file;
    private final List<Interval> chunks;

    /**
     * Creates a new instance of {@link RowParquetChunkReader}.
     *
     * @param file  a Parquet file
     * @param start a starting row group position
     * @param end   an ending row group position (exclusive)
     */
    public RowParquetChunkReader(final InputFile file, final long start, final long end) {
        this(file, List.of(new ChunkInterval(start, end)));
    }

    /**
     * Creates a new instance of {@link RowParquetChunkReader}.
     *
     * @param file   a Parquet file
     * @param chunks a list of row group chunks
     */
    public RowParquetChunkReader(final InputFile file, final List<Interval> chunks) {
        super();
        this.file = file;
        this.chunks = chunks;
    }

    @Override
    public CloseableIterator<Row> iterator() {
        var rowIterator = new RowIterator(this.file, this.chunks);
        addCloseable(rowIterator);
        return rowIterator;
    }

    // [impl->dsn~read-parquet-file-chunks-contents~1]
    private static class RowIterator implements CloseableIterator<Row> {
        private final InputFile file;
        private final ParquetFileReader reader;
        private final long totalRecordCount;
        private final boolean[] selectedRowGroups;
        private final MessageColumnIO messageIO;
        private final RecordMaterializer<Row> recordMaterializer;

        private int rowGroupIndex = 0;
        private long recordCount = 0;
        private long nextRowGroupStartingPosition = 0;
        private RecordReader<Row> recordReader;
        private boolean hasNext;
        private Row next;

        public RowIterator(final InputFile file, final List<Interval> chunks) {
            this.file = file;
            this.reader = getReader(file);
            var rowGroups = reader.getRowGroups();
            this.selectedRowGroups = new boolean[rowGroups.size()];
            var totalCount = 0L;
            for (final Interval interval : chunks) {
                int currentRowGroup = (int) interval.getStartPosition();
                final long endIndex = interval.getEndPosition();
                while (currentRowGroup < endIndex) {
                    if (currentRowGroup >= rowGroups.size()) {
                        throw new IllegalArgumentException(ExaError
                            .messageBuilder("E-PIOJ-5")
                            .message("Row group index {{INDEX}} is larger than available row groups.", currentRowGroup)
                            .mitigation("Please check that the start and end indexes of chunks are correct.").toString()
                        );
                    }
                    BlockMetaData rowGroup = rowGroups.get(currentRowGroup);
                    this.selectedRowGroups[currentRowGroup] = true;
                    totalCount += rowGroup.getRowCount();
                    currentRowGroup += 1;
                }
            }
            this.totalRecordCount = totalCount;
            final var conf = getConfiguration(file);
            final var readSupport = new RowReadSupport();
            final var schema = reader.getFooter().getFileMetaData().getSchema();
            final var readContext = readSupport.init(conf, Collections.emptyMap(), schema);
            this.recordMaterializer = readSupport.prepareForRead(conf, Collections.emptyMap(), schema, readContext);
            this.messageIO = new ColumnIOFactory(this.reader.getFooter().getFileMetaData().getCreatedBy()) //
                .getColumnIO(readContext.getRequestedSchema(), schema, true);
            readNextRecord();
        }

        private ParquetFileReader getReader(final InputFile file) {
            try {
                return ParquetFileReader.open(file);
            } catch (IOException exception) {
                throw new IllegalStateException(ExaError.messageBuilder("E-PIOJ-1")
                    .message("Failed to read Parquet file {{FILE}}.", file.toString())
                    .mitigation(CHECK_FILE_MITIGATION).toString(), exception);
            }
        }

        private Configuration getConfiguration(final InputFile file) {
            if (file instanceof HadoopInputFile) {
                return new Configuration(((HadoopInputFile) file).getConfiguration());
            } else {
                return new Configuration();
            }
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
            final var currentRow = this.next;
            readNextRecord();
            return currentRow;
        }

        private void readNextRecord() {
            var isRecordRead = false;
            while (!isRecordRead) {
                if (this.recordCount >= this.totalRecordCount) {
                    this.hasNext = false;
                    return;
                }
                try {
                    advance();
                    if (!readSingleRow()) {
                        continue;
                    }
                    isRecordRead = true;
                } catch (Exception exception) {
                    throw new ParquetDecodingException(ExaError.messageBuilder("F-PIOJ-2")
                        .message("Error reading record at {{RECORD}} in row group {{ROWGROUP}} in file {{FILE}}.",
                            this.recordCount, this.rowGroupIndex, this.file.toString())
                        .mitigation(CHECK_FILE_MITIGATION).toString(), exception);
                }
            }
            this.hasNext = true;
        }

        private void advance() {
            if (this.recordCount >= this.nextRowGroupStartingPosition) {
                while (!this.selectedRowGroups[this.rowGroupIndex]) {
                    this.rowGroupIndex += 1;
                    this.reader.skipNextRowGroup();
                }
                try {
                    final PageReadStore pageStore = this.reader.readNextRowGroup();
                    this.recordReader = this.messageIO.getRecordReader(pageStore, this.recordMaterializer,
                        FilterCompat.NOOP);
                    this.nextRowGroupStartingPosition += pageStore.getRowCount();
                } catch (IOException exception) {
                    throw new ParquetDecodingException(ExaError.messageBuilder("F-PIOJ-4")
                        .message("Error reading page from row group {{ROWGROUP}} in file {{FILE}}.",
                            this.rowGroupIndex, this.file.toString())
                        .mitigation(CHECK_FILE_MITIGATION).toString(), exception);
                }
                this.rowGroupIndex += 1;
            }
        }

        // This similar how Parquet reads records underneath,
        // https://github.com/apache/parquet-mr/blob/master/parquet-hadoop/src/main/java/org/apache/parquet/hadoop/InternalParquetRecordReader.java#L217
        private boolean readSingleRow() {
            this.recordCount += 1;
            try {
                this.next = this.recordReader.read();
            } catch (RecordMaterializer.RecordMaterializationException exception) {
                throw new IllegalStateException(ExaError.messageBuilder("F-PIOJ-3")
                    .message("Failed to read Parquet file {{FILE}} record.", this.file.toString())
                    .mitigation(CHECK_FILE_MITIGATION).toString(), exception);
            }
            if (this.recordReader.shouldSkipCurrentRecord()) {
                // this record is being filtered via the filter2 package
                return false;
            }
            if (this.next == null) {
                // only happens with FilteredRecordReader at end of block
                this.recordCount = this.nextRowGroupStartingPosition;
                return false;
            }
            return true;
        }

        @Override
        public void close() throws IOException {
            reader.close();
        }

    }
}
