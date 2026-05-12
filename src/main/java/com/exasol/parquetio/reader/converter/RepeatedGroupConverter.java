package com.exasol.parquetio.reader.converter;

import java.util.Collections;
import java.util.List;

import org.apache.parquet.io.api.Converter;
import org.apache.parquet.io.api.GroupConverter;
import org.apache.parquet.schema.GroupType;

/**
 * Converter for a Parquet repeated group.
 */
// [impl->dsn~converting-nested-column-types~1]
final class RepeatedGroupConverter extends GroupConverter implements ParquetConverter, ValueHolder {
    private final int index;
    private final ValueHolder parentDataHolder;
    private final AppendedValueHolder dataHolder = new AppendedValueHolder();
    private final GroupConverter groupConverter;

    /**
     * Create a new repeated group converter.
     *
     * @param groupType        group type
     * @param index            field index
     * @param parentDataHolder parent value holder
     */
    RepeatedGroupConverter(final GroupType groupType, final int index, final ValueHolder parentDataHolder) {
        this.index = index;
        this.parentDataHolder = parentDataHolder;
        if (groupType.getFieldCount() > 1) {
            this.groupConverter = new StructConverter(groupType, index, this);
        } else {
            this.groupConverter = new SingleValueConverter(groupType.getType(0).asPrimitiveType(), index,
                    this.dataHolder);
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
        // Repeated group values are accumulated in dataHolder and flushed to the parent in parentEnd().
        return Collections.emptyList();
    }

    @Override
    public void put(final int index, final Object value) {
        this.dataHolder.put(index, value);
    }
}
