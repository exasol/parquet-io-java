package com.exasol.parquetio.reader;

import com.exasol.errorreporting.ExaError;
import com.exasol.parquetio.data.ChunkInterval;
import com.exasol.parquetio.data.Interval;
import com.exasol.parquetio.data.Row;
import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.column.page.PageReadStore;
import org.apache.parquet.filter2.compat.FilterCompat;
import org.apache.parquet.hadoop.ParquetFileReader;
import org.apache.parquet.hadoop.util.HadoopInputFile;
import org.apache.parquet.io.*;
import org.apache.parquet.io.api.RecordMaterializer;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * A Parquet file reader that reads only provided row groups.
 */
public class RowParquetChunkReader {

    private static final String CHECK_FILE_MITIGATION = "Please make sure that the file is valid and not corrupted.";

    private final InputFile file;
    private final List<Interval> chunks;
    private final MessageColumnIO messageIO;
    private final RecordMaterializer<Row> recordMaterializer;

    /**
     * Creates a new instance of {@link RowParquetChunkReader}.
     *
     * Since no chunks are provided it reads all row groups of given file.
     *
     * @param file  a Parquet file
     */
    public RowParquetChunkReader(final InputFile file) {
        this(file, List.of(new ChunkInterval(0L, getRowGroupSize(file))));
    }

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
        this.file = file;
        this.chunks = chunks;
        final var readSupport = new RowReadSupport();
        try (final var reader = ParquetFileReader.open(file)) {
            final var conf = getConfiguration(file);
            final var schema = reader.getFooter().getFileMetaData().getSchema();
            final var readContext = readSupport.init(conf, Collections.emptyMap(), schema);
            this.recordMaterializer = readSupport.prepareForRead(conf, Collections.emptyMap(), schema, readContext);
            this.messageIO = new ColumnIOFactory(reader.getFooter().getFileMetaData().getCreatedBy())//
                .getColumnIO(readContext.getRequestedSchema(), schema, true);
        } catch (Exception exception) {
            throw new IllegalStateException(getFileReadingErrorMessage(), exception);
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
        } catch (Exception exception) {
            throw new IllegalStateException(ExaError
                .messageBuilder("E-PIOJ-3")
                .message("Error getting row group size from a Parquet {{FILE}} file.", file.toString())
                .mitigation(CHECK_FILE_MITIGATION).toString(),
                exception
            );
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
            for (final Interval chunk : chunks) {
                currentRowGroup = moveToRowGroupPosition(reader, currentRowGroup, chunk.getStartPosition());
                final long endPosition = chunk.getEndPosition();
                while (currentRowGroup < endPosition) {
                    final PageReadStore pageStore = reader.readNextRowGroup();
                    currentRowGroup += 1;
                    consumeRows(pageStore, rowConsumer);
                }
            }
        } catch (IOException exception) {
            throw new IllegalStateException(getFileReadingErrorMessage(), exception);
        }
    }

    private long moveToRowGroupPosition(final ParquetFileReader reader, final long currentPosition, final long startPosition) {
        long position = currentPosition;
        while (position < startPosition) {
            reader.skipNextRowGroup();
            position += 1;
        }
        return position;
    }

    private void consumeRows(final PageReadStore pageStore, final Consumer<Row> rowConsumer) {
        final RecordReader<Row> recordReader = messageIO.getRecordReader(pageStore, recordMaterializer, FilterCompat.NOOP);
        consumeRecords(recordReader, pageStore.getRowCount(), rowConsumer);
    }

    // This similar how Parquet reads records underneath,
    // https://github.com/apache/parquet-mr/blob/master/parquet-hadoop/src/main/java/org/apache/parquet/hadoop/InternalParquetRecordReader.java#L217
    private void consumeRecords(final RecordReader<Row> recordReader, final long totalRows, final Consumer<Row> rowConsumer) {
        long currentRow = 0;
        Row row;
        while (currentRow < totalRows) {
            currentRow += 1;
            try {
                row = recordReader.read();
            } catch (RecordMaterializer.RecordMaterializationException exception) {
                throw new ParquetDecodingException(ExaError
                    .messageBuilder("F-PIOJ-2")
                    .message("Failed to materialize a record from the Parquet file {{FILE}}.", this.file.toString())
                    .mitigation(CHECK_FILE_MITIGATION)
                    .toString(),
                    exception
                );
            }
            if (row == null) { // Only happens with FilteredRecordReader at end of block
                break;
            }
            if (!recordReader.shouldSkipCurrentRecord()) {
                rowConsumer.accept(row);
            }
        }
    }

    private String getFileReadingErrorMessage() {
        return ExaError
            .messageBuilder("E-PIOJ-1")
            .message("Failed to read Parquet file {{FILE}}.", this.file.toString())
            .mitigation(CHECK_FILE_MITIGATION)
            .toString();
    }

}
