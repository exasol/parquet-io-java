package com.exasol.parquetio.reader.converter;

import org.apache.parquet.io.api.PrimitiveConverter;

/**
 * Converter for Parquet {@code INT64} with {@code TIMESTAMP_MICROS} annotation.
 */
// [impl->dsn~converting-logical-column-types~1]
public final class ParquetTimestampMicrosConverter extends PrimitiveConverter implements ParquetConverter {
    private final int index;
    private final ValueHolder holder;

    /**
     * Create a new timestamp micros converter.
     *
     * @param index  field index
     * @param holder value holder
     */
    public ParquetTimestampMicrosConverter(final int index, final ValueHolder holder) {
        this.index = index;
        this.holder = holder;
    }

    @Override
    public void addLong(final long value) {
        this.holder.put(this.index, DateTimeHelper.getTimestampFromMicros(value));
    }
}
