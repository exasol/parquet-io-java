package com.exasol.parquetio.reader.converter;

import java.util.List;

/**
 * Stores converted Parquet values.
 */
public interface ValueHolder {
    /**
     * Resets the internal value holder data structure.
     */
    void reset();

    /**
     * Returns the values as immutable list.
     *
     * @return list of values for this value holder
     */
    List<Object> getValues();

    /**
     * Inserts a value to the given position.
     *
     * @param index position to insert value
     * @param value value to insert
     */
    void put(int index, Object value);
}
