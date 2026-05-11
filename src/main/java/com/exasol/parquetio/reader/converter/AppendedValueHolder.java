package com.exasol.parquetio.reader.converter;

import java.util.*;

final class AppendedValueHolder implements ValueHolder {
    private final List<Object> array = new ArrayList<>();

    @Override
    public void reset() {
        this.array.clear();
    }

    @Override
    public List<Object> getValues() {
        return Collections.unmodifiableList(new ArrayList<>(this.array));
    }

    @Override
    public void put(final int index, final Object value) {
        this.array.add(value);
    }
}
