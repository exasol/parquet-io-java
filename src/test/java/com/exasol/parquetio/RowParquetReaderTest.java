package com.exasol.parquetio;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.parquet.example.data.simple.SimpleGroup;
import org.junit.jupiter.api.Test;

import com.exasol.parquetio.data.GenericRow;
import com.exasol.parquetio.data.Row;

// [utest->dsn~read-parquet-file-contents~1]
class RowParquetReaderTest extends BaseParquetReaderTest {
    @Test
    void readsFileContents() throws IOException {
        final var schema = parseSchema("message test {", "  required int32 col_int;", "}");
        final List<Integer> input = List.of(1, 173, Integer.MAX_VALUE, Integer.MIN_VALUE);
        try (var writer = getParquetWriter(schema, true)) {
            for (final int value : input) {
                final var parquetRow = new SimpleGroup(schema);
                parquetRow.append("col_int", value);
                writer.write(parquetRow);
            }
        }
        final List<Row> records = getRecords();
        final List<GenericRow> expected = new ArrayList<>();
        for (final int value : input) {
            expected.add(GenericRow.of(value));
        }
        assertAll(() -> assertThat(records, hasSize(4)), () -> assertThat(records, equalTo(expected)));
    }

    @Test
    void readsFileRowValuesUsingPositionalIndexAndFieldNames() throws IOException {
        final var schema = parseSchema("message test {", "  required int32 col_int;", "  required binary col_str;",
                "}");
        final List<Integer> values = List.of(1, 10, 13);
        try (var writer = getParquetWriter(schema, true)) {
            for (final int value : values) {
                final var parquetRow = new SimpleGroup(schema);
                parquetRow.append("col_int", value);
                parquetRow.append("col_str", Integer.toString(value));
                writer.write(parquetRow);
            }
        }
        final List<Row> records = getRecords();
        for (int index = 0; index < values.size(); index++) {
            final int value = values.get(index);
            final Row row = records.get(index);
            assertAll(() -> assertThat(row.getValue(0), equalTo(value)),
                    () -> assertThat(row.getValue(1), equalTo(Integer.toString(value))),
                    () -> assertThat(row.getValue("col_int"), equalTo(value)),
                    () -> assertThat(row.getValue("col_str"), equalTo(Integer.toString(value))));
        }
    }
}
