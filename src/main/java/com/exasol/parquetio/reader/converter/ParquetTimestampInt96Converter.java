package com.exasol.parquetio.reader.converter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.sql.Timestamp;

import org.apache.parquet.io.api.Binary;
import org.apache.parquet.io.api.PrimitiveConverter;

/**
 * Converter for Parquet {@code INT96} type.
 */
// [impl->dsn~converting-primitive-column-types~1]
public final class ParquetTimestampInt96Converter extends PrimitiveConverter implements ParquetConverter {
    private final int index;
    private final ValueHolder holder;

    /**
     * Create a new INT96 timestamp converter.
     *
     * @param index  field index
     * @param holder value holder
     */
    public ParquetTimestampInt96Converter(final int index, final ValueHolder holder) {
        this.index = index;
        this.holder = holder;
    }

    @Override
    public void addBinary(final Binary value) {
        final ByteBuffer buffer = value.toByteBuffer().order(ByteOrder.LITTLE_ENDIAN);
        final long nanos = buffer.getLong();
        final int days = buffer.getInt();
        final long micros = DateTimeHelper.getMicrosFromJulianDay(days, nanos);
        final Timestamp timestamp = DateTimeHelper.getTimestampFromMicros(micros);
        this.holder.put(this.index, timestamp);
    }
}
