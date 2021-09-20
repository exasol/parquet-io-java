package com.exasol.parquetio.merger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.exasol.errorreporting.ExaError;
import com.exasol.parquetio.data.ChunkInterval;
import com.exasol.parquetio.data.ChunkIntervalImpl;

/**
 * A class that sorts and merges list of {@link ChunkInterval}s.
 */
public class ChunkIntervalMerger {

    /**
     * Sorts and merges overlapping chunks.
     *
     * @param chunks a list {@link ChunkInterval} chunks
     * @return a sorted and merged chunks
     */
    public List<ChunkInterval> sortAndMerge(final List<ChunkInterval> chunks) {
        if (chunks == null || chunks.isEmpty()) {
            throw new IllegalArgumentException(
                    ExaError.messageBuilder("E-PIOJ-5").message("Chunk intervals list is empty.")
                            .mitigation("Please provide a valid list of Parquet file chunks.").toString());
        }
        if (chunks.size() == 1) {
            return chunks;
        }
        final List<ChunkInterval> modifiableChunks = new ArrayList<>(chunks);
        sortByStartPosition(modifiableChunks);
        return mergeOverlaps(modifiableChunks);
    }

    private void sortByStartPosition(final List<ChunkInterval> chunks) {
        Collections.sort(chunks, (a, b) -> Long.compare(a.getStartPosition(), b.getStartPosition()));
    }

    private List<ChunkInterval> mergeOverlaps(final List<ChunkInterval> chunks) {
        final List<ChunkInterval> result = new ArrayList<>();
        long startPosition = chunks.get(0).getStartPosition();
        long endPosition = chunks.get(0).getEndPosition();
        for (final var chunk : chunks) {
            if (chunk.getStartPosition() <= endPosition) {
                endPosition = Math.max(endPosition, chunk.getEndPosition());
            } else {
                result.add(new ChunkIntervalImpl(startPosition, endPosition));
                startPosition = chunk.getStartPosition();
                endPosition = chunk.getEndPosition();
            }
        }
        result.add(new ChunkIntervalImpl(startPosition, endPosition));
        return result;
    }

}
