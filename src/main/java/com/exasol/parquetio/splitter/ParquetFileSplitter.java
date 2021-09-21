package com.exasol.parquetio.splitter;

import java.util.ArrayList;
import java.util.List;

import com.exasol.errorreporting.ExaError;
import com.exasol.parquetio.data.ChunkInterval;
import com.exasol.parquetio.data.ChunkIntervalImpl;
import org.apache.parquet.hadoop.ParquetFileReader;
import org.apache.parquet.hadoop.metadata.BlockMetaData;
import org.apache.parquet.io.InputFile;

/**
 * A class that splits Parquet file into chunks of certain size.
 *
 * Each chunk then contains one or more row group start and end positions in a Parquet file.
 */
public class ParquetFileSplitter implements FileSplitter {

    private static final long DEFAULT_CHUNK_SIZE = 64L * 1024 * 1024;
    private final InputFile file;
    private final long chunkSize;

    /**
     * Creates a new instance of {@link ParquetFileSplitter}.
     *
     * It uses default chunk size of {@code 64MB} to split the file.
     *
     * @param file a Parquet file
     */
    public ParquetFileSplitter(final InputFile file) {
        this(file, DEFAULT_CHUNK_SIZE);
    }

    /**
     * Creates a new instance of {@link ParquetFileSplitter}.
     *
     * @param file      a Parquet file
     * @param chunkSize a chunk size in bytes
     */
    public ParquetFileSplitter(final InputFile file, final long chunkSize) {
        this.file = file;
        this.chunkSize = chunkSize;
    }

    @Override
    public List<ChunkInterval> getSplits() {
        try (final var reader = ParquetFileReader.open(file)) {
            return getRowGroupSplits(reader.getRowGroups());
        } catch (Exception exception) {
            throw new IllegalStateException(ExaError.messageBuilder("E-PIOJ-4")
                    .message("Failed to open a Parquet file {{FILE}} for splitting.", this.file.toString()).toString(),
                    exception);
        }
    }

    protected List<ChunkInterval> getRowGroupSplits(final List<BlockMetaData> rowGroups) {
        final List<ChunkInterval> chunks = new ArrayList<>();
        final long end = rowGroups.size();
        var startPosition = 0L;
        var currentSize = 0L;
        for (var endPosition = 0; endPosition < end; endPosition++) {
            currentSize += rowGroups.get(endPosition).getTotalByteSize();
            if (currentSize >= this.chunkSize) {
                chunks.add(new ChunkIntervalImpl(startPosition, endPosition + 1L));
                startPosition = endPosition + 1L;
                currentSize = 0L;
            }
        }
        if (currentSize != 0) {
            chunks.add(new ChunkIntervalImpl(startPosition, end));
        }
        return chunks;
    }

}
