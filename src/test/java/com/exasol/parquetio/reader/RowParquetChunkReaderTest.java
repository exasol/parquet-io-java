package com.exasol.parquetio.reader;

import com.exasol.parquetio.data.ChunkInterval;
import com.exasol.parquetio.data.Interval;
import com.exasol.parquetio.data.Row;
import com.exasol.parquetio.writer.ParquetTestFileWriter;
import org.apache.parquet.schema.PrimitiveType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertThrows;

// [utest->dsn~read-parquet-file-chunks-contents~1]
class RowParquetChunkReaderTest {

    private static final int RECORD_COUNT = 200000;

    @Test
    void testReadAll(@TempDir final Path tempDir) throws IOException {
        final Path path = tempDir.resolve("part-0000.parquet");
        final ParquetTestFileWriter writer = new ParquetTestFileWriter(path, PrimitiveType.PrimitiveTypeName.INT32);
        writer.write(ParquetTestFileWriter.getIntegerValues(10));
        final var reader = new RowParquetChunkReader(ParquetTestFileWriter.getInputFile(path));
        List<Integer> values = collectValues(reader);
        assertThat(values, containsInAnyOrder(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    }

    @Test
    void testReadingFileThrows(@TempDir final Path tempDir) throws IOException {
        final Path path = tempDir.resolve("part-0000-corrupted.parquet");
        Files.writeString(path, "abc");
        final var inputFile = ParquetTestFileWriter.getInputFile(path);
        final IllegalStateException exception = assertThrows(IllegalStateException.class,
            () -> new RowParquetChunkReader(inputFile, 0L, 1L));
        assertThat(exception.getMessage(), startsWith("E-PIOJ-1:"));
        assertThat(exception.getMessage(), containsString("part-0000-corrupted.parquet"));
    }

    @Test
    void testGettingRowGroupSizeThrows(@TempDir final Path tempDir) throws IOException {
        final Path path = tempDir.resolve("part-0000-row-groups-missing.parquet");
        Files.writeString(path, "abcdef");
        final var inputFile = ParquetTestFileWriter.getInputFile(path);
        final IllegalStateException exception = assertThrows(IllegalStateException.class,
            () -> new RowParquetChunkReader(inputFile));
        assertThat(exception.getMessage(), startsWith("E-PIOJ-3:"));
        assertThat(exception.getMessage(), containsString("part-0000-row-groups-missing.parquet"));
    }

    @Test
    void testRead(@TempDir final Path tempDir) throws IOException {
        final Path path = tempDir.resolve("part-0000.parquet");
        final ParquetTestFileWriter writer = new ParquetTestFileWriter(path, PrimitiveType.PrimitiveTypeName.INT32);
        writer.write(ParquetTestFileWriter.getIntegerValues(RECORD_COUNT));
        final List<Interval> chunks = List.of(new ChunkInterval(0, 2), new ChunkInterval(98, 100));
        final var reader = new RowParquetChunkReader(ParquetTestFileWriter.getInputFile(path), chunks);
        assertValues(reader);
    }

    private void assertValues(final RowParquetChunkReader reader) {
        List<Integer> values = collectValues(reader);
        for (int value = 0; value <= 4000; value++) {
            assertThat(values.contains(value), equalTo(true));
        }
        for (int value = 4200; value < RECORD_COUNT - 2500; value++) {
            assertThat(values.contains(value), equalTo(false));
        }
        for (int value = RECORD_COUNT - 1; value >= RECORD_COUNT - 2000 - 1; value--) {
            assertThat(values.contains(value), equalTo(true));
        }
    }

    private List<Integer> collectValues(final RowParquetChunkReader reader) {
        List<Integer> values = new ArrayList<>();
        reader.read(row -> values.add((Integer) row.getValue(0)));
        return values;
    }
}
