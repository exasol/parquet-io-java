package com.exasol.parquetio.reader.converter;

import org.apache.parquet.schema.PrimitiveType;

/**
 * Converter for non-standard Parquet list annotated groups with a single repeated type.
 */
// [impl->dsn~converting-nested-column-types~1]
final class ArrayPrimitiveConverter extends AbstractArrayConverter {

    /**
     * Create a new array primitive converter.
     *
     * @param elementType      element type
     * @param index            field index
     * @param parentDataHolder parent value holder
     */
    public ArrayPrimitiveConverter(final PrimitiveType elementType, final int index,
            final ValueHolder parentDataHolder) {
        super(index, parentDataHolder,
                dataHolder -> ParquetConverterFactory.createPrimitiveConverter(elementType, index, dataHolder));
    }
}
