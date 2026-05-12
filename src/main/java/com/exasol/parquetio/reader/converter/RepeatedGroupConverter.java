package com.exasol.parquetio.reader.converter;

import java.util.List;

import org.apache.parquet.io.api.Converter;
import org.apache.parquet.io.api.GroupConverter;
import org.apache.parquet.schema.GroupType;

/**
 * Converter for a Parquet repeated group.
 */
// [impl->dsn~converting-nested-column-types~1]
final class RepeatedGroupConverter extends GroupConverter implements ParquetConverter, ValueHolder {
    private final RepeatedValuesCollector valuesCollector;
    private final GroupConverter groupConverter;

    /**
     * Create a new repeated group converter.
     *
     * @param groupType        group type
     * @param index            field index
     * @param parentDataHolder parent value holder
     */
    RepeatedGroupConverter(final GroupType groupType, final int index, final ValueHolder parentDataHolder) {
        this.valuesCollector = new RepeatedValuesCollector(index, parentDataHolder);
        if (groupType.getFieldCount() > 1) {
            this.groupConverter = new StructConverter(groupType, index, this);
        } else {
            this.groupConverter = new SingleValueConverter(groupType.getType(0).asPrimitiveType(), index,
                    this.valuesCollector);
        }
    }

    @Override
    public void start() {
        this.groupConverter.start();
    }

    @Override
    public void end() {
        this.groupConverter.end();
    }

    @Override
    public Converter getConverter(final int fieldIndex) {
        return this.groupConverter.getConverter(fieldIndex);
    }

    @Override
    public void parentStart() {
        this.valuesCollector.parentStart();
    }

    @Override
    public void parentEnd() {
        this.valuesCollector.parentEnd();
    }

    @Override
    public void reset() {
        this.valuesCollector.reset();
    }

    @Override
    public List<Object> getValues() {
        return this.valuesCollector.getValues();
    }

    @Override
    public void put(final int index, final Object value) {
        this.valuesCollector.put(index, value);
    }
}
