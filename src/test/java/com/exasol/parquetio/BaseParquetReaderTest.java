package com.exasol.parquetio;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.parquet.example.data.Group;
import org.apache.parquet.hadoop.ParquetReader;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.util.HadoopInputFile;
import org.apache.parquet.schema.MessageType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import com.exasol.parquetio.data.Row;
import com.exasol.parquetio.reader.RowParquetReader;

public abstract class BaseParquetReaderTest {
    private Configuration configuration;
    private Path outputDirectory;
    private org.apache.hadoop.fs.Path path;

    @BeforeEach
    protected final void beforeEach() throws IOException {
        this.configuration = new Configuration();
        FileSystem.get(this.configuration);
        this.outputDirectory = TestFileManager.createTemporaryFolder("parquetRowReaderTest");
        this.path = new org.apache.hadoop.fs.Path(this.outputDirectory.toUri().toString(), "part-00000.parquet");
    }

    @AfterEach
    protected final void afterEach() throws IOException {
        TestFileManager.deletePathFiles(this.outputDirectory);
    }

    protected final List<Row> getRecords() throws IOException {
        try (ParquetReader<Row> reader = RowParquetReader
                .builder(HadoopInputFile.fromPath(this.path, this.configuration)).build()) {
            final List<Row> records = new ArrayList<>();
            Row row = reader.read();
            while (row != null) {
                records.add(row);
                row = reader.read();
            }
            return records;
        }
    }

    protected final ParquetWriter<Group> getParquetWriter(final MessageType schema, final boolean encoding)
            throws IOException {
        return ParquetTestDataWriter.getParquetWriter(this.path, schema, encoding);
    }

    protected final MessageType parseSchema(final String... lines) {
        return org.apache.parquet.schema.MessageTypeParser.parseMessageType(String.join("\n", lines));
    }
}
