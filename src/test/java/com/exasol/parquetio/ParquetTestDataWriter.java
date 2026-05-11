package com.exasol.parquetio;

import java.io.IOException;
import java.util.HashMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.conf.ParquetConfiguration;
import org.apache.parquet.example.data.Group;
import org.apache.parquet.example.data.GroupWriter;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.api.WriteSupport;
import org.apache.parquet.hadoop.util.ConfigurationUtil;
import org.apache.parquet.hadoop.util.HadoopOutputFile;
import org.apache.parquet.io.api.RecordConsumer;
import org.apache.parquet.schema.MessageType;

final class ParquetTestDataWriter {
    private ParquetTestDataWriter() {
        // utility class
    }

    static ParquetWriter<Group> getParquetWriter(final org.apache.hadoop.fs.Path path, final MessageType schema,
            final boolean dictionaryEncoding) throws IOException {
        return new BaseGroupWriterBuilder(path, schema).withDictionaryEncoding(dictionaryEncoding).build();
    }

    private static final class BaseGroupWriteSupport extends WriteSupport<Group> {
        private final MessageType schema;
        private GroupWriter writer;

        private BaseGroupWriteSupport(final MessageType schema) {
            this.schema = schema;
        }

        @Override
        public void prepareForWrite(final RecordConsumer recordConsumer) {
            this.writer = new GroupWriter(recordConsumer, this.schema);
        }

        @Override
        @SuppressWarnings("deprecation") // Super class requires implementing this deprecated method
        public WriteContext init(final Configuration configuration) {
            return new WriteContext(this.schema, new HashMap<>());
        }

        @Override
        public WriteContext init(final ParquetConfiguration configuration) {
            return init(ConfigurationUtil.createHadoopConfiguration(configuration));
        }

        @Override
        public void write(final Group parquetRow) {
            this.writer.write(parquetRow);
        }
    }

    private static final class BaseGroupWriterBuilder extends ParquetWriter.Builder<Group, BaseGroupWriterBuilder> {
        private final MessageType schema;

        private BaseGroupWriterBuilder(final org.apache.hadoop.fs.Path path, final MessageType schema)
                throws IOException {
            super(HadoopOutputFile.fromPath(path, new Configuration()));
            this.schema = schema;
        }

        @Override
        @SuppressWarnings("deprecation") // Super class requires implementing this deprecated method
        protected WriteSupport<Group> getWriteSupport(final Configuration configuration) {
            return new BaseGroupWriteSupport(this.schema);
        }

        @Override
        protected WriteSupport<Group> getWriteSupport(final ParquetConfiguration conf) {
            return getWriteSupport(ConfigurationUtil.createHadoopConfiguration(conf));
        }

        @Override
        protected BaseGroupWriterBuilder self() {
            return this;
        }
    }
}
