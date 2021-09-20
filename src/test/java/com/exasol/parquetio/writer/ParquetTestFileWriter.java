package com.exasol.parquetio.writer;

import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.example.data.Group;
import org.apache.parquet.example.data.simple.SimpleGroupFactory;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.example.ExampleParquetWriter;
import org.apache.parquet.hadoop.example.GroupWriteSupport;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.parquet.hadoop.util.HadoopInputFile;
import org.apache.parquet.io.InputFile;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.PrimitiveType;
import org.apache.parquet.schema.Types;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ParquetTestFileWriter {

    private static final int DEFAULT_PAGE_SIZE = 1024; // 1K
    private static final long DEFAULT_ROW_GROUP_SIZE = 8L * 1024; // 8K
    private static final String MESSAGE_TYPE_NAME = "test";
    private static final String FIELD_TYPE_NAME = "field";

    private final Path path;
    private final PrimitiveType.PrimitiveTypeName type;
    private final MessageType schema;

    public ParquetTestFileWriter(final Path path, final PrimitiveType.PrimitiveTypeName type) {
        this.path = path;
        this.type = type;
        this.schema = getMessageType(type);
    }

    private MessageType getMessageType(final PrimitiveType.PrimitiveTypeName type) {
        if (type == PrimitiveType.PrimitiveTypeName.FIXED_LEN_BYTE_ARRAY) {
            throw new UnsupportedOperationException("Fixed Length Byte Array is not supported.");
        } else {
            return Types.buildMessage().required(type).named(FIELD_TYPE_NAME).named(MESSAGE_TYPE_NAME);
        }
    }

    public static InputFile getInputFile(final Path path) throws IOException {
        final var parquetFile = new org.apache.hadoop.fs.Path(path.toString());
        return HadoopInputFile.fromPath(parquetFile, new Configuration());
    }

    public static List<Integer> getIntegerValues(final int size) {
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            values.add(i);
        }
        return values;
    }


    public void write(final List<?> values) {
        write(values, DEFAULT_ROW_GROUP_SIZE);
    }

    public void write(final List<?> values, final long rowGroupSize) {
        final Configuration conf = new Configuration();
        GroupWriteSupport.setSchema(this.schema, conf);
        try (final ParquetWriter<Group> writer = ExampleParquetWriter
            .builder(new org.apache.hadoop.fs.Path(this.path.toString()))
            .withCompressionCodec(CompressionCodecName.UNCOMPRESSED)
            .withRowGroupSize(rowGroupSize)
            .withPageSize(DEFAULT_PAGE_SIZE)
            .withDictionaryEncoding(false)
            .withConf(conf)
            .build()) {
            writeValues(writer, values);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("error");
        }
    }

    private void writeValues(final ParquetWriter<Group> writer, final List<?> values) throws IOException {
        final SimpleGroupFactory groupFactory = new SimpleGroupFactory(this.schema);
        for (Object o : values) {
            switch (type) {
                case BOOLEAN:
                    writer.write(groupFactory.newGroup().append(FIELD_TYPE_NAME, (Boolean) o));
                    break;
                case INT32:
                    writer.write(groupFactory.newGroup().append(FIELD_TYPE_NAME, (Integer) o));
                    break;
                case INT64:
                    writer.write(groupFactory.newGroup().append(FIELD_TYPE_NAME, (Long) o));
                    break;
                case FLOAT:
                    writer.write(groupFactory.newGroup().append(FIELD_TYPE_NAME, (Float) o));
                    break;
                case DOUBLE:
                    writer.write(groupFactory.newGroup().append(FIELD_TYPE_NAME, (Double) o));
                    break;
                case INT96:
                case BINARY:
                case FIXED_LEN_BYTE_ARRAY:
                    writer.write(groupFactory.newGroup().append(FIELD_TYPE_NAME, (Binary) o));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown type name: " + type);
            }
        }
    }

}