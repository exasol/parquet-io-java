package com.exasol.parquetio.reader;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.parquet.io.*;
import org.apache.parquet.io.api.RecordMaterializer;
import org.apache.parquet.schema.PrimitiveType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import com.exasol.parquetio.data.*;
import com.exasol.parquetio.writer.ParquetTestFileWriter;

// [utest->dsn~read-parquet-file-chunks-contents~1]
class RowParquetChunkReaderTest {

    private static final int RECORD_COUNT = 200000;

    @ParameterizedTest
    @NullAndEmptySource
    void testReadChunksNullAndEmptyThrows(final List<ChunkInterval> list) {
        final var inputFile = new SimpleInputFile("dummy");
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new RowParquetChunkReader(inputFile, list));
        assertThat(exception.getMessage(), startsWith("E-PIOJ-5"));
    }

    @Test
    void testReadAll(@TempDir final Path tempDir) throws IOException {
        final var reader = new RowParquetChunkReader(writeSimpleFile(tempDir));
        final List<Integer> values = collectValues(reader);
        assertThat(values, containsInAnyOrder(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    }

    @Test
    void testReadInvalidFileThrows(@TempDir final Path tempDir) throws IOException {
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
    void testConsumeRecordsThrows() {
        @SuppressWarnings("unchecked") // unchecked conversion
        final RecordReader<Row> recordReader = mock(RecordReader.class);
        when(recordReader.read()).thenThrow(RecordMaterializer.RecordMaterializationException.class);
        final ParquetDecodingException exception = assertThrows(ParquetDecodingException.class,
                () -> new RowParquetChunkReader.RecordIterator(recordReader, 1L, ""));
        assertThat(exception.getMessage(), startsWith("F-PIOJ-2: Failed to materialize a record"));
    }

    @Test
    void testConsumeRecordsSkips() {
        @SuppressWarnings("unchecked") // unchecked conversion
        final RecordReader<Row> recordReader = mock(RecordReader.class);
        when(recordReader.read()).thenReturn(new GenericRow(Collections.emptyList()), GenericRow.of("a"));
        when(recordReader.shouldSkipCurrentRecord()).thenReturn(true, false);
        final AtomicInteger count = new AtomicInteger();
        final RowParquetChunkReader.RecordIterator iterator = new RowParquetChunkReader.RecordIterator(recordReader, 2L,
                "");
        iterator.forEachRemaining(row -> count.addAndGet(1));
        assertThat(count.get(), equalTo(1));
    }

    @Test
    void testConsumeRecordsStopsOnNull() {
        @SuppressWarnings("unchecked") // unchecked conversion
        final RecordReader<Row> recordReader = mock(RecordReader.class);
        when(recordReader.read()).thenReturn(new GenericRow(Collections.emptyList()), null, GenericRow.of(1));
        final AtomicInteger count = new AtomicInteger();
        final RowParquetChunkReader.RecordIterator iterator = new RowParquetChunkReader.RecordIterator(recordReader, 3L,
                "");
        iterator.forEachRemaining(row -> count.addAndGet(1));
        assertThat(count.get(), equalTo(1));
    }

    private InputFile writeSimpleFile(final Path tempDir) throws IOException {
        final Path path = tempDir.resolve("part-0000.parquet");
        final ParquetTestFileWriter writer = new ParquetTestFileWriter(path, PrimitiveType.PrimitiveTypeName.INT32);
        writer.write(ParquetTestFileWriter.getIntegerValues(10));
        return ParquetTestFileWriter.getInputFile(path);
    }

    @Test
    void testRead(@TempDir final Path tempDir) throws IOException {
        final Path path = tempDir.resolve("part-0000.parquet");
        final ParquetTestFileWriter writer = new ParquetTestFileWriter(path, PrimitiveType.PrimitiveTypeName.INT32);
        writer.write(ParquetTestFileWriter.getIntegerValues(RECORD_COUNT));
        final List<ChunkInterval> chunks = List.of(new ChunkIntervalImpl(0, 2), new ChunkIntervalImpl(98, 100));
        final var reader = new RowParquetChunkReader(ParquetTestFileWriter.getInputFile(path), chunks);
        assertValues(reader);
    }

    private void assertValues(final RowParquetChunkReader reader) {
        final List<Integer> values = collectValues(reader);
        for (int value = 0; value <= 4033; value++) {
            assertThat(values.contains(value), equalTo(true));
        }
        for (int value = 4034; value < RECORD_COUNT - 2500; value++) {
            assertThat(values.contains(value), equalTo(false));
        }
        for (int value = RECORD_COUNT - 1; value >= RECORD_COUNT - 2000 - 1; value--) {
            assertThat(values.contains(value), equalTo(true));
        }
    }

    private List<Integer> collectValues(final RowParquetChunkReader reader) {
        final List<Integer> values = new ArrayList<>();
        reader.read(row -> values.add((Integer) row.getValue(0)));
        return values;
    }

    private static class SimpleInputFile implements InputFile {
        private final String path;

        public SimpleInputFile(final String path) {
            this.path = path;
        }

        @Override
        public long getLength() {
            return 0L;
        }

        @Override
        public SeekableInputStream newStream() {
            return null;
        }

        @Override
        public String toString() {
            return this.path;
        }
    }
}
