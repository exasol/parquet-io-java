package com.exasol.parquetio.reader.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.parquet.schema.GroupType;

/**
 * The main Parquet data types to {@link com.exasol.parquetio.data.Row}
 * converter class.
 */
public final class ParquetRootConverter extends AbstractStructConverter {
    /**
     * Create a new root converter.
     *
     * @param schema main schema for the Parquet file
     */
    public ParquetRootConverter(final GroupType schema) {
        super(schema, -1, EmptyValueHolder.INSTANCE);
    }

    /**
     * Returns deserialized Parquet field values for this schema.
     *
     * @return list of deserialized objects
     */
    public List<Object> getResult() {
        return new ArrayList<>(getDataHolderValues());
    }

    @Override
    void endOperation() {
        // root converter has no parent holder
    }
}
