package com.exasol.parquetio.reader.converter;

import org.apache.parquet.io.api.Converter;
import org.apache.parquet.io.api.GroupConverter;
import org.apache.parquet.schema.Type;

/**
 * Converter for standard three-level Parquet list annotated group type.
 */
// [impl->dsn~converting-nested-column-types~1]
public final class ArrayGroupConverter extends GroupConverter implements ParquetConverter {
    private final int index;
    private final ValueHolder parentDataHolder;
    private final AppendedValueHolder dataHolder = new AppendedValueHolder();
    private final ParquetConverter elementConverter;

    /**
     * Create a new array group converter.
     *
     * @param elementType element type
     * @param index field index
     * @param parentDataHolder parent value holder
     */
    public ArrayGroupConverter(final Type elementType, final int index, final ValueHolder parentDataHolder) {
        this.index = index;
        this.parentDataHolder = parentDataHolder;
        this.elementConverter = new ElementGroupConverter(elementType, index, this.dataHolder);
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

    private static final class ElementGroupConverter extends GroupConverter implements ParquetConverter {
        private final ParquetConverter innerConverter;

        private ElementGroupConverter(final Type elementType, final int index, final ValueHolder dataHolder) {
            this.innerConverter = ParquetConverterFactory.create(elementType, index, dataHolder);
        }

        @Override
        public Converter getConverter(final int index) {
            return this.innerConverter.asConverter();
        }

        @Override
        public void start() {
            this.innerConverter.parentStart();
        }

        @Override
        public void end() {
            this.innerConverter.parentEnd();
        }
    }
}
