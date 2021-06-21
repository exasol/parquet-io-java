package com.exasol.parquetio.data;

import java.util.*;

import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.Type;

/**
 * An implementation of {@link Row} that uses unmodifiable list for storing values.
 */
public class GenericRow implements Row {
    private final MessageType schema;
    private final List<Object> values;

    /**
     * A constructor to create a new instance.
     *
     * @param values list of values
     */
    public GenericRow(final List<Object> values) {
        this.schema = null;
        this.values = Collections.unmodifiableList(values);
    }

    /**
     * A constructor to create a new instance with {@link MessageType} schema.
     *
     * @param schema schema of a row
     * @param values list of values
     */
    public GenericRow(final MessageType schema, final List<Object> values) {
        this.schema = schema;
        this.values = Collections.unmodifiableList(values);
    }

    /**
     * A factory method to create a new instance.
     *
     * @param values list of values
     * @return an instance of {@link GenericRow}
     */
    public static GenericRow of(final Object... values) {
        return new GenericRow(Arrays.asList(values));
    }

    /**
     * A factory method to create a new instance with {@link MessageType} schema.
     *
     * @param schema schema of a row
     * @param values list of values
     * @return an instance of {@link GenericRow}
     */
    public static GenericRow of(final MessageType schema, final Object... values) {
        return new GenericRow(schema, Arrays.asList(values));
    }

    @Override
    public boolean hasFieldName(final String fieldName) {
        return this.schema.containsField(fieldName);
    }

    @Override
    public List<String> getFieldNames() {
        final List<String> fieldNames = new ArrayList<>(size());
        for (final Type type : this.schema.getFields()) {
            fieldNames.add(type.getName());
        }
        return fieldNames;
    }

    @Override
    public Object getValue(final int position) {
        return this.values.get(position);
    }

    @Override
    public Object getValue(final String fieldName) {
        if (this.schema == null) {
            throw new IllegalArgumentException(
                    "Generic row does not have a schema. Please use positional access method.");
        }
        final int fieldPosition = this.schema.getFieldIndex(fieldName);
        return this.values.get(fieldPosition);
    }

    @Override
    public List<Object> getValues() {
        return this.values;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GenericRow)) {
            return false;
        }
        final GenericRow otherRow = (GenericRow) other;
        return this.values.equals(otherRow.values);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValues());
    }

    @Override
    public String toString() {
        final var stringBuilder = new StringBuilder();
        stringBuilder.append("Row(values=[");
        for (var index = 0; index < this.size(); index++) {
            if (index > 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(this.getValue(index));
        }
        stringBuilder.append("])");
        return stringBuilder.toString();
    }
}
