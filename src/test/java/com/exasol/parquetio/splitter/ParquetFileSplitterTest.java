package com.exasol.parquetio.splitter;

import com.exasol.parquetio.data.ChunkInterval;
import com.exasol.parquetio.writer.ParquetTestFileWriter;
import org.apache.parquet.hadoop.metadata.BlockMetaData;
import org.apache.parquet.schema.PrimitiveType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ParquetFileSplitterTest {

    @Test
    void testGetSplits(@TempDir final Path tempDir) throws IOException {
        final Path path = tempDir.resolve("part-0000.parquet");
        final ParquetTestFileWriter writer = new ParquetTestFileWriter(path, PrimitiveType.PrimitiveTypeName.INT32);
        writer.write(ParquetTestFileWriter.getIntegerValues(200000)); // 200000 * 4 bytes
        final List<ChunkInterval> intervals = new ParquetFileSplitter(ParquetTestFileWriter.getInputFile(path), 16 * 1024).getSplits();
        assertAll(
            () -> assertThat(intervals.get(0).getStartPosition(), equalTo(0L)),
            () -> assertThat(intervals.get(0).getEndPosition(), equalTo(2L)),
            () -> assertThat(intervals.get(49).getStartPosition(), equalTo(98L)),
            () -> assertThat(intervals.get(49).getEndPosition(), equalTo(100L)),
            () -> assertThat(intervals.size(), equalTo(50)));
    }

    @Test
    void testGetRowSplitsEmpty() {
        final List<BlockMetaData> rowGroups = new ArrayList<>();
        final List<ChunkInterval> intervals = new ParquetFileSplitter(null, 10).getRowGroupSplits(rowGroups);
        assertThat(intervals.isEmpty(), equalTo(true));
    }

    @Test
    void testGetRowSplits() {
        BlockMetaData testBlock = mock(BlockMetaData.class);
        when(testBlock.getTotalByteSize()).thenReturn((long) 30);
        final List<BlockMetaData> rowGroups = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            rowGroups.add(testBlock);
        }
        final List<ChunkInterval> intervals = new ParquetFileSplitter(null, 100).getRowGroupSplits(rowGroups);
        assertAll(//
            () -> assertThat(intervals.get(0).getStartPosition(), equalTo(0L)),
            () -> assertThat(intervals.get(0).getEndPosition(), equalTo(4L)),
            () -> assertThat(intervals.get(1).getStartPosition(), equalTo(4L)),
            () -> assertThat(intervals.get(1).getEndPosition(), equalTo(7L)),
            () -> assertThat(intervals.size(), equalTo(2))
        );
    }

}
