package com.exasol.parquetio.reader.converter;

import java.util.Collections;
import java.util.List;

import org.apache.parquet.io.api.Converter;
import org.apache.parquet.io.api.GroupConverter;
import org.apache.parquet.schema.PrimitiveType;

/**
 * Converter for a repeated group that contains a single primitive value.
 */
public final class SingleValueConverter extends GroupConverter {
    private final int index;
    private final ValueHolder parentDataHolder;
    private final ParquetConverter singleValueConverter;
    private Object currentValue;

    /**
     * Create a new single value converter.
     *
     * @param valueType value type
     * @param index field index
     * @param parentDataHolder parent value holder
     */
    public SingleValueConverter(final PrimitiveType valueType, final int index, final ValueHolder parentDataHolder) {
        this.index = index;
        this.parentDataHolder = parentDataHolder;
        this.singleValueConverter = ParquetConverterFactory.create(valueType, index, new ValueHolder() {
            @Override
            public void put(final int index, final Object value) {
                SingleValueConverter.this.currentValue = value;
            }

            @Override
            public void reset() {
                // no internal values
            }

            @Override
            public List<Object> getValues() {
                return Collections.emptyList();
            }
        });
    }

    @Override
    public Converter getConverter(final int fieldIndex) {
        return this.singleValueConverter.asConverter();
    }

    @Override
    public void end() {
        this.parentDataHolder.put(this.index, this.currentValue);
    }

    @Override
    public void start() {
        this.currentValue = null;
    }
}
