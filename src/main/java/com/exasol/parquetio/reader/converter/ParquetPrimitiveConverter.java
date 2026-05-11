package com.exasol.parquetio.reader.converter;

import static java.nio.charset.StandardCharsets.UTF_8;

import org.apache.parquet.io.api.Binary;
import org.apache.parquet.io.api.PrimitiveConverter;

/**
 * Default converter for Parquet primitive types.
 */
// [impl->dsn~converting-primitive-column-types~1]
final class ParquetPrimitiveConverter extends PrimitiveConverter implements ParquetConverter {
    private final int index;
    private final ValueHolder holder;

    /**
     * Create a new primitive converter.
     *
     * @param index  field index
     * @param holder value holder
     */
    public ParquetPrimitiveConverter(final int index, final ValueHolder holder) {
        this.index = index;
        this.holder = holder;
    }

    @Override
    public void addBinary(final Binary value) {
        this.holder.put(this.index, new String(value.getBytes(), UTF_8));
    }

    @Override
    public void addBoolean(final boolean value) {
        this.holder.put(this.index, value);
    }

    @Override
    public void addDouble(final double value) {
        this.holder.put(this.index, value);
    }

    @Override
    public void addFloat(final float value) {
        this.holder.put(this.index, value);
    }

    @Override
    public void addInt(final int value) {
        this.holder.put(this.index, value);
    }

    @Override
    public void addLong(final long value) {
        this.holder.put(this.index, value);
    }
}
