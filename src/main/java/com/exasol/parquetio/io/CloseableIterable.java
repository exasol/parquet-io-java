package com.exasol.parquetio.io;

import java.io.Closeable;

/**
 * An {@link Iterable} interface that provides closeable {@link Iterable#iterator()} implementation.
 *
 * @param <T> a type of iterator values
 */
public interface CloseableIterable<T> extends Iterable<T>, Closeable {

    /**
     * Returns a closeable iterator with elements of type {@code T}.
     *
     * @return an instance of {@link CloseableIterator}.
     */
    @Override
    CloseableIterator<T> iterator();

}