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
     * Returns the value at the given position.
     *
     * @param position position of value in the row
     * @return Java Object
     */
    public Object getObjectAt(final int position);

    /**
     * Checks whether a value at a position is null.
     *
     * @param position position of a value in a row
     * @return {@code true} if value at position is null; otherwise {@code false}
     */
    default boolean isNullAt(final int position) {
        return getObjectAt(position) == null;
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
