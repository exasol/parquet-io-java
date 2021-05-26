package com.exasol.parquetio.data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
     */
    public static GenericRow of(final Object... values) {
        return new GenericRow(Arrays.asList(values));
    }

    /**
     * A factory method to create a new instance with {@link MessageType} schema.
     *
     * @param schema schema of a row
     * @param values list of values
     */
    public static GenericRow of(final MessageType schema, final Object... values) {
        return new GenericRow(schema, Arrays.asList(values));
    }

    /**
     * Checks if a column with a given name exists.
     *
     * @param fieldName field name in a row
     * @return {@code true} if column with a name exists; otherwise {@code false}
     */
    public boolean hasFieldName(final String fieldName) {
        return schema.containsField(fieldName);
    }

    /**
     * Returns list of field names.
     *
     * @return list of field names
     */
    public List<String> getFieldNames() {
        return schema.getFields().stream().map(Type::getName).collect(Collectors.toList());
    }

    @Override
    public Object getValue(final int position) {
        return values.get(position);
    }

    @Override
    public Object getValue(final String fieldName) {
        if (schema == null) {
            throw new IllegalArgumentException("Generic row does not have a schema. Please use positional access method.");
        }
        int fieldPosition = schema.getFieldIndex(fieldName);
        return values.get(fieldPosition);
    }

    @Override
    public List<Object> getValues() {
        return values;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GenericRow)) {
            return false;
        }
        GenericRow otherRow = (GenericRow) other;
        return this.values.equals(otherRow.values);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValues());
    }

    @Override
    public String toString() {
        var stringBuilder = new StringBuilder();
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
