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
        // The Parquet callback index is ignored because repeated values arrive in order and are appended.
        this.array.add(value);
    }
}
