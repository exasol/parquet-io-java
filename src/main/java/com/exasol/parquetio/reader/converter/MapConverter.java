package com.exasol.parquetio.reader.converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.parquet.io.api.Converter;
import org.apache.parquet.io.api.GroupConverter;
import org.apache.parquet.schema.GroupType;
import org.apache.parquet.schema.Type;

/**
 * Converter for Parquet {@code MAP} annotated type.
 */
// [impl->dsn~converting-nested-column-types~1]
public final class MapConverter extends GroupConverter implements ParquetConverter {
    private final int index;
    private final ValueHolder parentDataHolder;
    private final AppendedValueHolder keysDataHolder = new AppendedValueHolder();
    private final AppendedValueHolder valuesDataHolder = new AppendedValueHolder();
    private final Converter converter;

    /**
     * Create a new map converter.
     *
     * @param groupType map group type
     * @param index field index
     * @param parentDataHolder parent value holder
     */
    public MapConverter(final GroupType groupType, final int index, final ValueHolder parentDataHolder) {
        this.index = index;
        this.parentDataHolder = parentDataHolder;
        this.converter = createMapConverter(groupType);
    }

    @Override
    public Converter getConverter(final int fieldIndex) {
        if ((fieldIndex < 0) || (fieldIndex > 1)) {
            throw new IllegalArgumentException("Illegal index '" + fieldIndex
                    + "' to map converter. It should be either '0' for keys converter or '1' for values converter.");
        }
        return this.converter;
    }

    @Override
    public void start() {
        this.keysDataHolder.reset();
        this.valuesDataHolder.reset();
    }

    @Override
    public void end() {
        final List<Object> keys = this.keysDataHolder.getValues();
        final List<Object> values = this.valuesDataHolder.getValues();
        final Map<Object, Object> map = new HashMap<>();
        for (int index = 0; index < keys.size(); index++) {
            map.put(keys.get(index), values.get(index));
        }
        this.parentDataHolder.put(this.index, map);
    }

    private Converter createMapConverter(final GroupType groupType) {
        final GroupType mapType = groupType.getFields().get(0).asGroupType();
        final Type mapKeyType = mapType.getFields().get(0);
        final Type mapValueType = mapType.getFields().get(1);
        final ParquetConverter keysConverter = ParquetConverterFactory.create(mapKeyType, this.index,
                this.keysDataHolder);
        final ParquetConverter valuesConverter = ParquetConverterFactory.create(mapValueType, this.index,
                this.valuesDataHolder);
        return new GroupConverter() {
            @Override
            public Converter getConverter(final int index) {
                if (index == 0) {
                    return keysConverter.asConverter();
                }
                return valuesConverter.asConverter();
            }

            @Override
            public void start() {
                valuesConverter.parentStart();
            }

            @Override
            public void end() {
                valuesConverter.parentEnd();
            }
        };
    }
}
