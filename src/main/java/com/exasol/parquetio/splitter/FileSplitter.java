package com.exasol.parquetio.splitter;

import java.util.List;

import com.exasol.parquetio.data.ChunkInterval;

/**
 * An interface for splitting files into chunks.
 */
public interface FileSplitter {

    /**
     * Gets file splits in the form of {@code start} and {@code end} intervals.
     *
     * @return an array of intervals
     */
    List<ChunkInterval> getSplits();

}
