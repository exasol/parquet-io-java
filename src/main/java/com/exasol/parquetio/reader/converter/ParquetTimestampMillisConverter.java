package com.exasol.parquetio.reader.converter;

import org.apache.parquet.io.api.PrimitiveConverter;

/**
 * Converter for Parquet {@code INT64} with {@code TIMESTAMP_MILLIS} annotation.
 */
// [impl->dsn~converting-logical-column-types~1]
final class ParquetTimestampMillisConverter extends PrimitiveConverter implements ParquetConverter {
    private final int index;
    private final ValueHolder holder;
    private final boolean adjustedToUTC;

    /**
     * Create a new timestamp millis converter.
     *
     * @param index  field index
     * @param holder value holder
     */
    ParquetTimestampMillisConverter(final int index, final ValueHolder holder, final boolean adjustedToUTC) {
        this.index = index;
        this.holder = holder;
        this.adjustedToUTC = adjustedToUTC;
    }

    @Override
    public void addLong(final long value) {
        this.holder.put(this.index, this.adjustedToUTC ? DateTimeHelper.getTimestampFromMillis(value)
                : DateTimeHelper.getLocalTimestampFromMillis(value));
    }
}
