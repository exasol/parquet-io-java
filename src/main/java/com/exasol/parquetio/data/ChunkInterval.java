package com.exasol.parquetio.data;

/**
 * An interval interface that holds start and end positions {@code [...)} of each splitted chunk.
 */
public interface ChunkInterval {

    /**
     * Gets the start position of this interval.
     *
     * @return a start position
     */
    long getStartPosition();

    /**
     * Gets the end position of this interval that is not inclusive.
     *
     * @return an end position
     */
    long getEndPosition();

}
