package com.exasol.parquetio.reader;

import java.io.IOException;

import com.exasol.parquetio.data.Row;

import org.apache.parquet.hadoop.ParquetFileReader;
import org.apache.parquet.hadoop.ParquetReader;
import org.apache.parquet.hadoop.api.ReadSupport;
import org.apache.parquet.io.InputFile;
import org.apache.parquet.schema.MessageType;

/**
 * A reader that creates Row from Parquet file records.
 */
// [impl->dsn~read-parquet-file-contents~1]
public class RowParquetReader {

    private RowParquetReader() {
        // intentionally private
    }

    /**
     * Creates a new builder for {@link RowParquetReader}.
     *
     * @param file input file to read from
     * @return new {@link Builder}
     */
    public static Builder builder(final InputFile file) {
        return new Builder(file);
    }

    /**
     * A builder for {@link RowParquetReader}.
     */
    public static class Builder extends ParquetReader.Builder<Row> {
        /**
         * A constructor for the builder of {@link RowParquetReader}.
         *
         * @param file input file to read from
         */
        protected Builder(final InputFile file) {
            super(file);
        }

        @Override
        protected ReadSupport<Row> getReadSupport() {
            return new RowReadSupport();
        }
    }

    /**
     * Returns a Parquet file schema.
     *
     * @param file an input file
     * @return Parquet file schema
     * @throws IOException if it cannot open the file for reading
     */
    public static MessageType getSchema(InputFile file) throws IOException {
        try (final var reader = ParquetFileReader.open(file)) {
            return reader.getFileMetaData().getSchema();
        }
    }

}
