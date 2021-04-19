package com.exasol.parquetio.reader;

import java.io.IOException;

import com.exasol.parquetio.data.Row;
import com.exasol.parquetio.reader.RowReadSupport;

import org.apache.parquet.hadoop.ParquetFileReader;
import org.apache.parquet.hadoop.ParquetReader;
import org.apache.parquet.hadoop.api.ReadSupport;
import org.apache.parquet.io.InputFile;
import org.apache.parquet.schema.MessageType;

/**
 * A reader that creates Row from Parquet file records.
 */
public class RowParquetReader {

    private RowParquetReader() {
        // intentionally private
    }

    /**
     * Creates a new builder for {@link RowParquetReader}.
     *
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
     */
    public static MessageType getSchema(InputFile file) throws IOException {
        return ParquetFileReader.open(file).getFileMetaData().getSchema();
    }

}
