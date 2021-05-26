package com.exasol.parquetio.data;

import java.util.List;

/**
 * A Row holds set of values.
 *
 * It is a similar to the database record where each value maps to a column in a table.
 */
public interface Row {

    /**
     * Returns the list of values this Row represents.
     *
     * @return list of values
     */
    public List<Object> getValues();

    /**
     * Returns a value at the given position.
     *
     * @param position position of a value in the row
     * @return Java object
     */
    public Object getValue(final int position);

    /**
     * Returns a value that corresponds to field name.
     *
     * @param fieldName field name of a value in the row
     * @return Java object
     */
    public Object getValue(final String fieldName);

    /**
     * Checks whether a value at a position is {@code null}.
     *
     * @param position position of a value in a row
     * @return {@code true} if value at position is null; otherwise {@code false}
     */
    default boolean isNull(final int position) {
        return getValue(position) == null;
    }

    /**
     * Checks whether a value for field name is {@code null}.
     *
     * @param fieldName field name of a value in a row
     * @return {@code true} if value for field name is null; otherwise {@code false}
     */
    default boolean isNull(final String fieldName) {
        return getValue(fieldName) == null;
    }

    /**
     * Checks if the list of value empty for this Row.
     *
     * @return {@code true} if values list is empty; otherwise {@code false}
     */
    default boolean isEmpty() {
        return getValues().isEmpty();
    }

    /**
     * Returns the number of values in this Row.
     *
     * @return number of values
     */
    default int size() {
        return getValues().size();
    }

}
