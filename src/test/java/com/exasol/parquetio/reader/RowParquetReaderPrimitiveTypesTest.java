package com.exasol.parquetio.reader;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.apache.parquet.example.data.simple.SimpleGroup;
import org.apache.parquet.io.api.Binary;
import org.junit.jupiter.api.Test;

import com.exasol.parquetio.BaseParquetReaderTest;
import com.exasol.parquetio.data.GenericRow;
import com.exasol.parquetio.data.Row;

// [utest->dsn~converting-primitive-column-types~1]
// [utest->dsn~converting-logical-column-types~1]
class RowParquetReaderPrimitiveTypesTest extends BaseParquetReaderTest {
    @Test
    void readsInt64TimestampMillisAsTimestampValue() throws IOException {
        final var schema = parseSchema("message test {", "  required int64 col_long;",
                "  required int64 col_timestamp (TIMESTAMP_MILLIS);", "}");
        final var timestamp = new Timestamp(System.currentTimeMillis());
        try (var writer = getParquetWriter(schema, false)) {
            final var parquetRow = new SimpleGroup(schema);
            parquetRow.append("col_long", 153L);
            parquetRow.append("col_timestamp", timestamp.getTime());
            writer.write(parquetRow);
        }
        assertThat(getRecords(), contains(GenericRow.of(153L, timestamp)));
    }

    @Test
    void readsInt64TimestampMicrosAsTimestampValue() throws IOException {
        final var schema = parseSchema("message test {", "  required int64 col_timestamp (TIMESTAMP_MICROS);", "}");
        final var timestamp = Timestamp.valueOf("2022-01-12 11:52:53.123456");
        final long micros = timestamp.getTime() * 1000L + (timestamp.getNanos() / 1000L) % 1000L;
        try (var writer = getParquetWriter(schema, false)) {
            final var parquetRow = new SimpleGroup(schema);
            parquetRow.append("col_timestamp", micros);
            writer.write(parquetRow);
        }
        assertThat(getRecords(), contains(GenericRow.of(timestamp)));
    }

    @Test
    void readsFixedLenByteArrayAsStringValue() throws IOException {
        final int size = 5;
        final var schema = parseSchema("message test {", "  required fixed_len_byte_array(" + size
                + ") col_byte_array;", "}");
        try (var writer = getParquetWriter(schema, false)) {
            final var parquetRow = new SimpleGroup(schema);
            parquetRow.append("col_byte_array", "hello");
            writer.write(parquetRow);
        }
        assertThat(getRecords(), contains(GenericRow.of("hello")));
    }

    @Test
    void readsFixedLenByteArrayUuidAsUuidValue() throws IOException {
        final var schema = parseSchema("message test {", "  required fixed_len_byte_array(16) col_uuid (UUID);",
                "}");
        final UUID uuid = UUID.randomUUID();
        try (var writer = getParquetWriter(schema, false)) {
            final var parquetRow = new SimpleGroup(schema);
            parquetRow.append("col_uuid", getByteBufferFromUuid(uuid));
            writer.write(parquetRow);
        }
        assertThat(getRecords(), contains(GenericRow.of(uuid)));
    }

    private Binary getByteBufferFromUuid(final UUID uuid) {
        final byte[] bytes = ByteBuffer.allocate(16).order(ByteOrder.BIG_ENDIAN)
                .putLong(uuid.getMostSignificantBits()).putLong(uuid.getLeastSignificantBits()).array();
        return Binary.fromConstantByteArray(bytes);
    }

    @Test
    void readsBinaryAsStringValue() throws IOException {
        final var schema = parseSchema("message test {", "  required binary col_binary;", "}");
        try (var writer = getParquetWriter(schema, false)) {
            final var parquetRow = new SimpleGroup(schema);
            parquetRow.append("col_binary", "test");
            writer.write(parquetRow);
        }
        assertThat(getRecords(), contains(GenericRow.of("test")));
    }

    @Test
    void readsBinaryUtf8AsStringValueUsingDictionaryEncoding() throws IOException {
        final var schema = parseSchema("message test {", "  required binary col_binary (UTF8);", "}");
        try (var writer = getParquetWriter(schema, true)) {
            for (final String value : List.of("test1", "test2", "test1", "test2")) {
                final var parquetRow = new SimpleGroup(schema);
                parquetRow.append("col_binary", value);
                writer.write(parquetRow);
            }
        }
        final List<Row> records = getRecords();
        assertAll(() -> assertThat(records, hasSize(4)), () -> assertThat(records.get(0), equalTo(GenericRow.of("test1"))),
                () -> assertThat(records.get(1), equalTo(GenericRow.of("test2"))));
    }

    @Test
    void readsInt32DecimalAsBigDecimalValueUsingDictionaryEncoding() throws IOException {
        final var schema = parseSchema("message test {", "  required int32 col_int (DECIMAL(9,2));", "}");
        try (var writer = getParquetWriter(schema, true)) {
            for (final int value : List.of(123456789, 0, 0, 123456789)) {
                final var parquetRow = new SimpleGroup(schema);
                parquetRow.append("col_int", value);
                writer.write(parquetRow);
            }
        }
        final List<Row> records = getRecords();
        assertAll(() -> assertThat(records, hasSize(4)),
                () -> assertThat(records.get(0), equalTo(GenericRow.of(BigDecimal.valueOf(123456789, 2)))),
                () -> assertThat(records.get(1), equalTo(GenericRow.of(BigDecimal.valueOf(0, 2)))));
    }

    @Test
    void readsInt64DecimalAsBigDecimalValueUsingDictionaryEncoding() throws IOException {
        final var schema = parseSchema("message test {", "  required int64 column (DECIMAL(18,2));", "}");
        try (var writer = getParquetWriter(schema, true)) {
            for (final long value : List.of(1234567890123456L, 1L, 1L, 1234567890123456L)) {
                final var parquetRow = new SimpleGroup(schema);
                parquetRow.append("column", value);
                writer.write(parquetRow);
            }
        }
        final List<Row> records = getRecords();
        assertAll(() -> assertThat(records, hasSize(4)),
                () -> assertThat(records.get(0), equalTo(GenericRow.of(BigDecimal.valueOf(1234567890123456L, 2)))),
                () -> assertThat(records.get(1), equalTo(GenericRow.of(BigDecimal.valueOf(1L, 2)))));
    }

    @Test
    void readsBinaryDecimalAsBigDecimalValueUsingDictionaryEncoding() throws IOException {
        final var schema = parseSchema("message test {", "  required binary column (DECIMAL(30,2));", "}");
        final String decimalValue = "123456789012345678901234567890";
        try (var writer = getParquetWriter(schema, true)) {
            for (final String value : List.of(decimalValue, decimalValue)) {
                final var parquetRow = new SimpleGroup(schema);
                parquetRow.append("column", value);
                writer.write(parquetRow);
            }
        }
        final var expected = new BigDecimal(new BigInteger(decimalValue.getBytes(UTF_8)), 2, new MathContext(30));
        final List<Row> records = getRecords();
        assertAll(() -> assertThat(records, hasSize(2)), () -> assertThat(records.get(0), equalTo(GenericRow.of(expected))),
                () -> assertThat(records.get(1), equalTo(GenericRow.of(expected))));
    }

    @Test
    void readsFixedLenByteArrayDecimalAsBigDecimalValueUsingDictionaryEncoding() throws IOException {
        final var schema = parseSchema("message test {", "  required fixed_len_byte_array(9) column (DECIMAL(20,2));",
                "}");
        final String decimalValue = "12345678901234567890";
        final Binary decimalBinary = Binary.fromConstantByteArray(new BigDecimal(decimalValue).unscaledValue().toByteArray());
        final Binary zeros = Binary.fromConstantByteArray(new byte[9], 0, 9);
        try (var writer = getParquetWriter(schema, true)) {
            for (final Binary value : List.of(decimalBinary, zeros)) {
                final var parquetRow = new SimpleGroup(schema);
                parquetRow.append("column", value);
                writer.write(parquetRow);
            }
        }
        final var expected = new BigDecimal(new BigInteger(decimalValue), 2, new MathContext(20));
        final List<Row> records = getRecords();
        assertAll(() -> assertThat(records, hasSize(2)), () -> assertThat(records.get(0), equalTo(GenericRow.of(expected))),
                () -> assertThat(records.get(1), equalTo(GenericRow.of(BigDecimal.valueOf(0, 2)))));
    }
}
