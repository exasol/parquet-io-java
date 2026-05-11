package com.exasol.parquetio.reader.converter;

import java.util.Collections;
import java.util.List;

import org.apache.parquet.column.Dictionary;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.io.api.PrimitiveConverter;
import org.apache.parquet.schema.PrimitiveType;

/**
 * Converter for a Parquet repeated primitive field.
 */
// [impl->dsn~converting-nested-column-types~1]
public final class RepeatedPrimitiveConverter extends PrimitiveConverter implements ParquetConverter, ValueHolder {
    private final int index;
    private final ValueHolder parentDataHolder;
    private final AppendedValueHolder dataHolder = new AppendedValueHolder();
    private final PrimitiveConverter primitiveConverter;

    /**
     * Create a new repeated primitive converter.
     *
     * @param elementType element type
     * @param index field index
     * @param parentDataHolder parent value holder
     */
    public RepeatedPrimitiveConverter(final PrimitiveType elementType, final int index,
            final ValueHolder parentDataHolder) {
        this.index = index;
        this.parentDataHolder = parentDataHolder;
        this.primitiveConverter = ParquetConverterFactory.createPrimitiveConverter(elementType, index, this)
                .asConverter().asPrimitiveConverter();
    }

    @Override
    public void addBinary(final Binary value) {
        this.primitiveConverter.addBinary(value);
    }

    @Override
    public void addBoolean(final boolean value) {
        this.primitiveConverter.addBoolean(value);
    }

    @Override
    public void addDouble(final double value) {
        this.primitiveConverter.addDouble(value);
    }

    @Override
    public void addFloat(final float value) {
        this.primitiveConverter.addFloat(value);
    }

    @Override
    public void addInt(final int value) {
        this.primitiveConverter.addInt(value);
    }

    @Override
    public void addLong(final long value) {
        this.primitiveConverter.addLong(value);
    }

    @Override
    public boolean hasDictionarySupport() {
        return this.primitiveConverter.hasDictionarySupport();
    }

    @Override
    public void setDictionary(final Dictionary dictionary) {
        this.primitiveConverter.setDictionary(dictionary);
    }

    @Override
    public void addValueFromDictionary(final int dictionaryId) {
        this.primitiveConverter.addValueFromDictionary(dictionaryId);
    }

    @Override
    public void parentStart() {
        this.dataHolder.reset();
    }

    @Override
    public void parentEnd() {
        this.parentDataHolder.put(this.index, this.dataHolder.getValues());
    }

    @Override
    public void reset() {
        // values are managed by parentStart()
    }

    @Override
    public List<Object> getValues() {
        return Collections.emptyList();
    }

    @Override
    public void put(final int index, final Object value) {
        this.dataHolder.put(index, value);
    }
}
