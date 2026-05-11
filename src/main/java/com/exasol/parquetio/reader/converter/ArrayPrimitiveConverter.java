package com.exasol.parquetio.reader.converter;

import org.apache.parquet.io.api.Converter;
import org.apache.parquet.io.api.GroupConverter;
import org.apache.parquet.schema.PrimitiveType;

/**
 * Converter for non-standard Parquet list annotated groups with a single repeated type.
 */
// [impl->dsn~converting-nested-column-types~1]
final class ArrayPrimitiveConverter extends GroupConverter implements ParquetConverter {
    private final int index;
    private final ValueHolder parentDataHolder;
    private final AppendedValueHolder dataHolder = new AppendedValueHolder();
    private final ParquetConverter elementConverter;

    /**
     * Create a new array primitive converter.
     *
     * @param elementType      element type
     * @param index            field index
     * @param parentDataHolder parent value holder
     */
    public ArrayPrimitiveConverter(final PrimitiveType elementType, final int index,
            final ValueHolder parentDataHolder) {
        this.index = index;
        this.parentDataHolder = parentDataHolder;
        this.elementConverter = ParquetConverterFactory.createPrimitiveConverter(elementType, index, this.dataHolder);
    }

    @Override
    public Converter getConverter(final int fieldIndex) {
        if (fieldIndex != 0) {
            throw new IllegalArgumentException(
                    "Illegal index '" + fieldIndex + "' to array converter. It should be only '0'.");
        }
        return this.elementConverter.asConverter();
    }

    @Override
    public void start() {
        this.dataHolder.reset();
        this.elementConverter.parentStart();
    }

    @Override
    public void end() {
        this.elementConverter.parentEnd();
        this.parentDataHolder.put(this.index, this.dataHolder.getValues());
    }
}
