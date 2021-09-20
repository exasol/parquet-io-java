package com.exasol.parquetio.data;

/**
 * An implementation of {@link ChunkInterval} that holds {@code start} and {@code end} position of row group chunks.
 */
public class ChunkIntervalImpl implements ChunkInterval {

    private final long start;
    private final long end;

    /**
     * Creates a new instance of {@link ChunkInterval}.
     *
     * @param start a starting row group position
     * @param end   an ending row group position
     */
    public ChunkIntervalImpl(final long start, final long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public long getStartPosition() {
        return start;
    }

    @Override
    public long getEndPosition() {
        return end;
    }

}
