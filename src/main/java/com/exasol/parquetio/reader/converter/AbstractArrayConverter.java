package com.exasol.parquetio.reader.converter;

import java.util.function.Function;

import org.apache.parquet.io.api.Converter;
import org.apache.parquet.io.api.GroupConverter;

/**
 * Base class for array converters that wrap a single repeated child field.
 */
abstract class AbstractArrayConverter extends GroupConverter implements ParquetConverter {
    private final int index;
    private final ValueHolder parentDataHolder;
    private final AppendedValueHolder dataHolder = new AppendedValueHolder();
    private final ParquetConverter elementConverter;

    AbstractArrayConverter(final int index, final ValueHolder parentDataHolder,
            final Function<ValueHolder, ParquetConverter> elementConverterFactory) {
        this.index = index;
        this.parentDataHolder = parentDataHolder;
        this.elementConverter = elementConverterFactory.apply(this.dataHolder);
    }

    @Override
    public final Converter getConverter(final int fieldIndex) {
        if (fieldIndex != 0) {
            throw new IllegalArgumentException(
                    "Illegal index '" + fieldIndex + "' to array converter. It should be only '0'.");
        }
        return this.elementConverter.asConverter();
    }

    @Override
    public final void start() {
        this.dataHolder.reset();
        this.elementConverter.parentStart();
    }

    @Override
    public final void end() {
        this.elementConverter.parentEnd();
        this.parentDataHolder.put(this.index, this.dataHolder.getValues());
    }
}
