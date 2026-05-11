package com.exasol.parquetio.reader.converter;

import org.apache.parquet.schema.GroupType;

/**
 * Converter for Parquet nested groups.
 */
 final class StructConverter extends AbstractStructConverter {
    /**
     * Create a new struct converter.
     *
     * @param groupType group type
     * @param index field index
     * @param parentDataHolder parent value holder
     */
     StructConverter(final GroupType groupType, final int index, final ValueHolder parentDataHolder) {
        super(groupType, index, parentDataHolder);
    }

    @Override
    void endOperation() {
        putStructValuesToParent();
    }
}
