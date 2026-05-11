package com.exasol.parquetio.reader.converter;

import org.apache.parquet.column.Dictionary;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.io.api.PrimitiveConverter;

/**
 * Converter for Parquet binary type with {@code STRING} or {@code UTF8} annotation.
 */
// [impl->dsn~converting-primitive-column-types~1]
final class ParquetStringConverter extends PrimitiveConverter implements ParquetConverter {
    private final int index;
    private final ValueHolder holder;
    private String[] decodedDictionary;

    /**
     * Create a new string converter.
     *
     * @param index  field index
     * @param holder value holder
     */
    public ParquetStringConverter(final int index, final ValueHolder holder) {
        this.index = index;
        this.holder = holder;
    }

    @Override
    public boolean hasDictionarySupport() {
        return true;
    }

    @Override
    public void setDictionary(final Dictionary dictionary) {
        this.decodedDictionary = new String[dictionary.getMaxId() + 1];
        for (int id = 0; id <= dictionary.getMaxId(); id++) {
            this.decodedDictionary[id] = dictionary.decodeToBinary(id).toStringUsingUTF8();
        }
    }

    @Override
    public void addBinary(final Binary value) {
        this.holder.put(this.index, value.toStringUsingUTF8());
    }

    @Override
    public void addValueFromDictionary(final int dictionaryId) {
        this.holder.put(this.index, this.decodedDictionary[dictionaryId]);
    }
}
