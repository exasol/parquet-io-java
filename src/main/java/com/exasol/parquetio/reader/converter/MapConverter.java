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
final class MapConverter extends GroupConverter implements ParquetConverter {
    private static final int MAP_ENTRIES_FIELD_INDEX = 0;
    private static final int KEY_FIELD_INDEX = 0;
    private static final int VALUE_FIELD_INDEX = 1;
    private static final int ENTRY_FIELD_COUNT = 2;
    private final int index;
    private final ValueHolder parentDataHolder;
    private final Map<Object, Object> map = new HashMap<>();
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
        if (fieldIndex != MAP_ENTRIES_FIELD_INDEX) {
            throw new IllegalArgumentException("Illegal index '" + fieldIndex
                    + "' to map converter. It should be '0' for the map entries converter.");
        }
        return this.converter;
    }

    @Override
    public void start() {
        this.map.clear();
    }

    @Override
    public void end() {
        this.parentDataHolder.put(this.index, new HashMap<>(this.map));
    }

    private Converter createMapConverter(final GroupType groupType) {
        final GroupType mapType = groupType.getFields().get(MAP_ENTRIES_FIELD_INDEX).asGroupType();
        final Type mapKeyType = mapType.getFields().get(KEY_FIELD_INDEX);
        final Type mapValueType = mapType.getFields().get(VALUE_FIELD_INDEX);
        return new MapEntryConverter(mapKeyType, mapValueType);
    }

    private final class MapEntryConverter extends GroupConverter {
        private final IndexedValueHolder entryDataHolder = new IndexedValueHolder(ENTRY_FIELD_COUNT);
        private final ParquetConverter keysConverter;
        private final ParquetConverter valuesConverter;

        private MapEntryConverter(final Type mapKeyType, final Type mapValueType) {
            this.keysConverter = ParquetConverterFactory.create(mapKeyType, KEY_FIELD_INDEX, this.entryDataHolder);
            this.valuesConverter = ParquetConverterFactory.create(mapValueType, VALUE_FIELD_INDEX,
                    this.entryDataHolder);
        }

        @Override
        public Converter getConverter(final int index) {
            if (index == KEY_FIELD_INDEX) {
                return this.keysConverter.asConverter();
            } else if (index == VALUE_FIELD_INDEX) {
                return this.valuesConverter.asConverter();
            }
            throw new IllegalArgumentException("Illegal map entry index '" + index + "'.");
        }

        @Override
        public void start() {
            this.entryDataHolder.reset();
            this.keysConverter.parentStart();
            this.valuesConverter.parentStart();
        }

        @Override
        public void end() {
            this.keysConverter.parentEnd();
            this.valuesConverter.parentEnd();
            final List<Object> values = this.entryDataHolder.getValues();
            MapConverter.this.map.put(values.get(KEY_FIELD_INDEX), values.get(VALUE_FIELD_INDEX));
        }
    }
}
