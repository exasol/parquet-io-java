package com.exasol.parquetio.reader.converter;

import org.apache.parquet.io.api.PrimitiveConverter;

/**
 * Converter for Parquet {@code INT64} with {@code TIMESTAMP(NANOS, ...)} annotation.
 */
// [impl->dsn~converting-nanosecond-timestamp-values~1]
final class ParquetTimestampNanosConverter extends PrimitiveConverter implements ParquetConverter {
    private final int index;
    private final ValueHolder holder;

    /**
     * Create a new timestamp nanos converter.
     *
     * @param index field index
     * @param holder value holder
     */
    ParquetTimestampNanosConverter(final int index, final ValueHolder holder) {
        this.index = index;
        this.holder = holder;
    }

    @Override
    public void addLong(final long value) {
        this.holder.put(this.index, DateTimeHelper.getTimestampFromNanos(value));
    }
}
