package com.exasol.parquetio.reader.converter;

import java.util.Collections;
import java.util.List;

final class EmptyValueHolder implements ValueHolder {
    static final EmptyValueHolder INSTANCE = new EmptyValueHolder();

    private EmptyValueHolder() {
        // singleton
    }

    @Override
    public void reset() {
        // empty holder
    }

    @Override
    public List<Object> getValues() {
        return Collections.emptyList();
    }

    @Override
    public void put(final int index, final Object value) {
        // empty holder
    }
}
