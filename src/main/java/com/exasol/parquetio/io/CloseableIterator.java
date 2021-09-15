package com.exasol.parquetio.io;

import java.io.Closeable;
import java.util.Iterator;

/**
 * An {@link Iterator} interface that requires closeable implementations.
 *
 * @param <T> a type of iterator elements
 */
public interface CloseableIterator<T> extends Iterator<T>, Closeable {

}