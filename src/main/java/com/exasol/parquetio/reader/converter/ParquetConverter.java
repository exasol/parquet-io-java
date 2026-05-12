package com.exasol.parquetio.reader.converter;

import org.apache.parquet.io.api.Converter;

/**
 * Common lifecycle interface for Parquet converters.
 */
public interface ParquetConverter {
    /**
     * Notify the converter that the parent type starts.
     */
    default void parentStart() {
        // default implementation
    }

    /**
     * Notify the converter that the parent type ends.
     */
    default void parentEnd() {
        // default implementation
    }

    /**
     * Return this converter as Parquet API converter.
     *
     * @return Parquet converter
     */
    default Converter asConverter() {
        return (Converter) this;
    }
}
