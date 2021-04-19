package com.exasol.parquetio.data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * An implementation of {@link Row} that uses unmodifiable list for storing values.
 */
public class GenericRow implements Row {
    private static final long serialVersionUID = -6175254671958738095L;

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
        if (!(other instanceof GenericRow)) {
            return false;
        }
        GenericRow otherRow = (GenericRow) other;
        if (this.size() != otherRow.size()) {
            return false;
        }
        for (int i = 0; i < this.size(); i++) {
            Object lhs = this.getObjectAt(i);
            Object rhs = otherRow.getObjectAt(i);
            if (lhs == null && rhs == null) {
                continue;
            }
            if (lhs == null || rhs == null) {
                return false;
            }
            if (!lhs.equals(rhs)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValues());
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Row(values=[");
        for (int i = 0; i < this.size(); i++) {
            if (i > 0) {
                stringBuffer.append(",");
            }
            stringBuffer.append(this.getObjectAt(i));
        }
        stringBuffer.append("])");
        return stringBuffer.toString();
    }

}
