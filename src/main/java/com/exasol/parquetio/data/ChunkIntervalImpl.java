package com.exasol.parquetio.data;

import java.util.Objects;

/**
 * An implementation of {@link ChunkInterval} that holds {@code start} and {@code end} position of row group chunks.
 */
public final class ChunkIntervalImpl implements ChunkInterval {

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

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ChunkIntervalImpl)) {
            return false;
        }
        final ChunkIntervalImpl otherChunk = (ChunkIntervalImpl) other;
        return this.start == otherChunk.start && this.end == otherChunk.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.start, this.end);
    }

    @Override
    public String toString() {
        final var stringBuilder = new StringBuilder();
        stringBuilder//
                .append("ChunkInterval(start = ")//
                .append(this.start)//
                .append(", end = ")//
                .append(this.end)//
                .append(")");
        return stringBuilder.toString();
    }

}
