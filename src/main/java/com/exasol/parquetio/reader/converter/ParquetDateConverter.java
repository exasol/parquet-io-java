package com.exasol.parquetio.reader.converter;

import org.apache.parquet.io.api.PrimitiveConverter;

import com.exasol.parquetio.helper.DateTimeHelper;

/**
 * Converter for Parquet {@code INT32} with {@code DATE} annotation.
 */
// [impl->dsn~converting-logical-column-types~1]
public final class ParquetDateConverter extends PrimitiveConverter implements ParquetConverter {
    private final int index;
    private final ValueHolder holder;

    /**
     * Create a new date converter.
     *
     * @param index field index
     * @param holder value holder
     */
    public ParquetDateConverter(final int index, final ValueHolder holder) {
        this.index = index;
        this.holder = holder;
    }

    @Override
    public void addInt(final int value) {
        this.holder.put(this.index, DateTimeHelper.daysToDate(value));
    }
}
