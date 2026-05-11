package com.exasol.parquetio.reader;

import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.hadoop.api.ReadSupport;
import org.apache.parquet.io.api.RecordMaterializer;
import org.apache.parquet.schema.MessageType;

import com.exasol.parquetio.data.GenericRow;
import com.exasol.parquetio.data.Row;
import com.exasol.parquetio.reader.converter.ParquetRootConverter;

/**
 * Materializes Parquet records into internal {@link Row} values.
 */
@SuppressWarnings("deprecation")
final class RowReadSupport extends ReadSupport<Row> {
    @Override
    public RecordMaterializer<Row> prepareForRead(final Configuration configuration,
            final Map<String, String> metadata, final MessageType messageType, final ReadContext readContext) {
        return new RowRecordMaterializer(messageType);
    }

    @Override
    public ReadContext init(final Configuration configuration, final Map<String, String> metadata,
            final MessageType messageType) {
        final String projection = configuration.get(ReadSupport.PARQUET_READ_SCHEMA);
        final MessageType requestedSchema = ReadSupport.getSchemaForRead(messageType, projection);
        return new ReadContext(requestedSchema);
    }

    private static final class RowRecordMaterializer extends RecordMaterializer<Row> {
        private final MessageType messageType;
        private final ParquetRootConverter rootConverter;

        private RowRecordMaterializer(final MessageType messageType) {
            this.messageType = messageType;
            this.rootConverter = new ParquetRootConverter(messageType);
        }

        @Override
        public ParquetRootConverter getRootConverter() {
            return this.rootConverter;
        }

        @Override
        public void skipCurrentRecord() {
            getRootConverter().start();
        }

        @Override
        public Row getCurrentRecord() {
            return new GenericRow(this.messageType, getRootConverter().getResult());
        }
    }
}
