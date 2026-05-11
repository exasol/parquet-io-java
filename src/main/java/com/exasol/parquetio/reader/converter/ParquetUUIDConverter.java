package com.exasol.parquetio.reader.converter;

import java.nio.ByteBuffer;
import java.util.UUID;

import org.apache.parquet.io.api.Binary;
import org.apache.parquet.io.api.PrimitiveConverter;

/**
 * Converter for {@code UUID} annotated Parquet type.
 */
// [impl->dsn~converting-logical-column-types~1]
 final class ParquetUUIDConverter extends PrimitiveConverter implements ParquetConverter {
    private final int index;
    private final ValueHolder holder;

    /**
     * Create a new UUID converter.
     *
     * @param index field index
     * @param holder value holder
     */
     ParquetUUIDConverter(final int index, final ValueHolder holder) {
        this.index = index;
        this.holder = holder;
    }

    @Override
    public void addBinary(final Binary value) {
        this.holder.put(this.index, getUuidFromBytes(value.getBytes()));
    }

    private UUID getUuidFromBytes(final byte[] bytes) {
        final ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        final long mostSignificantBits = byteBuffer.getLong();
        final long leastSignificantBits = byteBuffer.getLong();
        return new UUID(mostSignificantBits, leastSignificantBits);
    }
}
