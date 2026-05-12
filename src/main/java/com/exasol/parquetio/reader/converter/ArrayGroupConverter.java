package com.exasol.parquetio.reader.converter;

import org.apache.parquet.io.api.Converter;
import org.apache.parquet.io.api.GroupConverter;
import org.apache.parquet.schema.Type;

/**
 * Converter for standard three-level Parquet list annotated group type.
 */
// [impl->dsn~converting-nested-column-types~1]
final class ArrayGroupConverter extends AbstractArrayConverter {

    /**
     * Create a new array group converter.
     *
     * @param elementType      element type
     * @param index            field index
     * @param parentDataHolder parent value holder
     */
    public ArrayGroupConverter(final Type elementType, final int index, final ValueHolder parentDataHolder) {
        super(index, parentDataHolder, dataHolder -> new RepeatedListGroupConverter(elementType, index, dataHolder));
    }

    /**
     * Represents the repeated {@code list} wrapper group and stores only the actual element value.
     */
    private static final class RepeatedListGroupConverter extends GroupConverter implements ParquetConverter {
        private final ParquetConverter innerConverter;

        private RepeatedListGroupConverter(final Type elementType, final int index, final ValueHolder dataHolder) {
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
