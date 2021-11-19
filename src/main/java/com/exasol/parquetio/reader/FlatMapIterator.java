package com.exasol.parquetio.reader;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class FlatMapIterator<T, S> implements Iterator<T> {
    private final Iterator<S> source;
    private final Function<S, Iterator<T>> mapFunction;
    private Iterator<T> currentIterator = null;
    private T next = null;
    private boolean hasNext = false;

    public FlatMapIterator(final Iterator<S> source, final Function<S, Iterator<T>> mapFunction) {
        this.source = source;
        this.mapFunction = mapFunction;
        this.loadNext();
    }

    private void loadNext() {
        while (this.currentIterator == null || !this.currentIterator.hasNext()) {
            if (!this.source.hasNext()) {
                this.hasNext = false;
                return;
            }

            this.currentIterator = (Iterator) this.mapFunction.apply(this.source.next());
        }

        this.next = this.currentIterator.next();
        this.hasNext = true;
    }

    public boolean hasNext() {
        return this.hasNext;
    }

    public T next() {
        if (!this.hasNext) {
            throw new NoSuchElementException();
        } else {
            final T nextCache = this.next;
            this.loadNext();
            return nextCache;
        }
    }
}
