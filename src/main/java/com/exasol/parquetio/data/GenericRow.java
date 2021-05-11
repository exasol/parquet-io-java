package com.exasol.parquetio.data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * An implementation of {@link Row} that uses unmodifiable list for storing values.
 */
public class GenericRow implements Row {

    private final List<Object> values;

    /**
     * A constructor to create a new instance.
     *
     * @param values list of values
     */
    public GenericRow(final List<Object> values) {
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

    @Override
    public Object getObjectAt(final int position) {
        return values.get(position);
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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Row(values=[");
        for (int index = 0; index < this.size(); index++) {
            if (index > 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(this.getObjectAt(index));
        }
        stringBuilder.append("])");
        return stringBuilder.toString();
    }

}
