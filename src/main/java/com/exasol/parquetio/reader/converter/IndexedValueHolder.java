package com.exasol.parquetio.reader.converter;

import java.util.*;

final class IndexedValueHolder implements ValueHolder {
    private Object[] array;

    IndexedValueHolder(final int size) {
        this.array = new Object[size];
    }

    @Override
    public void reset() {
        this.array = new Object[this.array.length];
    }

    @Override
    public List<Object> getValues() {
        return Collections.unmodifiableList(new ArrayList<>(Arrays.asList(this.array)));
    }

    @Override
    public void put(final int index, final Object value) {
        this.array[index] = value;
    }
}
