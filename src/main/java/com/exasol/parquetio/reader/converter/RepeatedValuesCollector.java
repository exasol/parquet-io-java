package com.exasol.parquetio.reader.converter;

import java.util.Collections;
import java.util.List;

/**
 * Collects repeated values and writes the complete list to the parent holder at the end of the parent group.
 */
final class RepeatedValuesCollector implements ValueHolder {
    private final int index;
    private final ValueHolder parentDataHolder;
    private final AppendedValueHolder dataHolder = new AppendedValueHolder();

    RepeatedValuesCollector(final int index, final ValueHolder parentDataHolder) {
        this.index = index;
        this.parentDataHolder = parentDataHolder;
    }

    void parentStart() {
        this.dataHolder.reset();
    }

    void parentEnd() {
        this.parentDataHolder.put(this.index, this.dataHolder.getValues());
    }

    @Override
    public void reset() {
        // values are managed by parentStart()
    }

    @Override
    public List<Object> getValues() {
        // Repeated values are accumulated and flushed to the parent in parentEnd().
        return Collections.emptyList();
    }

    @Override
    public void put(final int index, final Object value) {
        this.dataHolder.put(index, value);
    }
}
