package com.exasol.parquetio.reader.converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.parquet.io.api.Converter;
import org.apache.parquet.io.api.GroupConverter;
import org.apache.parquet.schema.GroupType;

/**
 * Abstract base class for Parquet {@code STRUCT} converters.
 */
// [impl->dsn~converting-nested-column-types~1]
abstract class AbstractStructConverter extends GroupConverter implements ParquetConverter {
    private final GroupType groupType;
    private final int index;
    private final ValueHolder parentDataHolder;
    private final int size;
    private final IndexedValueHolder dataHolder;
    private final ParquetConverter[] converters;

    AbstractStructConverter(final GroupType groupType, final int index, final ValueHolder parentDataHolder) {
        this.groupType = groupType;
        this.index = index;
        this.parentDataHolder = parentDataHolder;
        this.size = groupType.getFieldCount();
        this.dataHolder = new IndexedValueHolder(this.size);
        this.converters = createFieldConverters();
    }

    @Override
    public final Converter getConverter(final int fieldIndex) {
        return this.converters[fieldIndex].asConverter();
    }

    @Override
    public final void start() {
        this.dataHolder.reset();
        for (final ParquetConverter converter : this.converters) {
            converter.parentStart();
        }
    }

    @Override
    public final void end() {
        for (final ParquetConverter converter : this.converters) {
            converter.parentEnd();
        }
        endOperation();
    }

    abstract void endOperation();

    List<Object> getDataHolderValues() {
        return this.dataHolder.getValues();
    }

    void putStructValuesToParent() {
        final List<Object> values = this.dataHolder.getValues();
        final Map<Object, Object> map = new HashMap<>();
        for (int i = 0; i < values.size(); i++) {
            map.put(this.groupType.getFieldName(i), values.get(i));
        }
        this.parentDataHolder.put(this.index, map);
    }

    private ParquetConverter[] createFieldConverters() {
        final ParquetConverter[] fieldConverters = new ParquetConverter[this.size];
        for (int i = 0; i < this.size; i++) {
            fieldConverters[i] = ParquetConverterFactory.create(this.groupType.getType(i), i, this.dataHolder);
        }
        return fieldConverters;
    }
}
