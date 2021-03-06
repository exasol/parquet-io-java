package com.exasol.parquetio.merger;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import com.exasol.parquetio.data.ChunkInterval;
import com.exasol.parquetio.data.ChunkIntervalImpl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ChunkIntervalMergerTest {

    static Stream<Arguments> getChunks() {
        return Stream.of(//
                Arguments.of(null, null), //
                Arguments.of(Collections.emptyList(), Collections.emptyList()),
                Arguments.of(List.of(new ChunkIntervalImpl(0, 1)), List.of(new ChunkIntervalImpl(0, 1))),
                Arguments.of(List.of(new ChunkIntervalImpl(1, 2), new ChunkIntervalImpl(0, 1)),
                        List.of(new ChunkIntervalImpl(0, 2))),
                Arguments.of(
                        List.of(new ChunkIntervalImpl(1, 2), new ChunkIntervalImpl(0, 3), new ChunkIntervalImpl(4, 5)),
                        List.of(new ChunkIntervalImpl(0, 3), new ChunkIntervalImpl(4, 5)))//
        );
    }

    @ParameterizedTest
    @MethodSource("getChunks")
    void testSortAndMerge(final List<ChunkInterval> input, final List<ChunkInterval> expected) {
        assertThat(new ChunkIntervalMerger().sortAndMerge(input), equalTo(expected));
    }

}
