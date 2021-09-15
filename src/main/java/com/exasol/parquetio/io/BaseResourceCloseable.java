package com.exasol.parquetio.io;

import com.exasol.errorreporting.ExaError;

import java.io.Closeable;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * A class that manages closeable {@link Closeable} resources.
 */
public class BaseResourceCloseable implements Closeable {

    private final Deque<AutoCloseable> resources;

    /**
     * Creates an instance of {@link BaseResourceCloseable}.
     */
    public BaseResourceCloseable() {
        this.resources = new ArrayDeque<>();
    }

    /**
     * Registers a closeable resource for closing.
     *
     * @param resource a closeable resource
     */
    public void addCloseable(Closeable resource) {
        resources.add(resource);
    }

    @Override
    public void close() {
        while (!resources.isEmpty()) {
            final AutoCloseable resource = resources.pollFirst();
            if (resource != null) {
                try {
                    resource.close();
                } catch (Exception exception) {
                    throw new IllegalStateException(ExaError
                        .messageBuilder("F-PIOJ-4")
                        .message("Failed to close a closeable resource.")
                        .ticketMitigation()
                        .toString(),
                        exception
                    );
                }
            }
        }
    }
}