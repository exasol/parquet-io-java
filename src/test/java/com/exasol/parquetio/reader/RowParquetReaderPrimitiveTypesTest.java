package com.exasol.parquetio.reader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.apache.parquet.example.data.Group;
import org.apache.parquet.example.data.simple.SimpleGroup;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.schema.MessageType;
import org.junit.jupiter.api.Test;

import com.exasol.parquetio.BaseParquetReaderTest;
import com.exasol.parquetio.data.GenericRow;
import com.exasol.parquetio.data.Row;

// [utest->dsn~converting-primitive-column-types~1]
// [utest->dsn~converting-logical-column-types~1]
class RowParquetReaderPrimitiveTypesTest extends BaseParquetReaderTest {
    private static final int JULIAN_DAY_OF_EPOCH = 2_440_588;

    @Test
    void testReadsInt64TimestampMillisAsTimestampValue() throws IOException {
        final MessageType schema = parseSchema("message test {", "  required int64 col_long;",
                "  required int64 col_timestamp (TIMESTAMP_MILLIS);", "}");
        final long millisSinceEpoch = 1_712_297_228_009L;
        final Timestamp timestamp = Timestamp.from(Instant.parse("2024-04-05T06:07:08.009Z"));
        try (ParquetWriter<Group> writer = getParquetWriter(schema, false)) {
            final SimpleGroup parquetRow = new SimpleGroup(schema);
            parquetRow.append("col_long", 153L);
            parquetRow.append("col_timestamp", millisSinceEpoch);
            writer.write(parquetRow);
        }
        assertThat(getRecords(), contains(GenericRow.of(153L, timestamp)));
    }

    @Test
    void testReadsInt64TimestampMicrosAsTimestampValue() throws IOException {
        final MessageType schema = parseSchema("message test {", "  required int64 col_timestamp (TIMESTAMP_MICROS);",
                "}");
        final long microsSinceEpoch = 1_641_988_373_123_456L;
        final Timestamp timestamp = Timestamp.from(Instant.parse("2022-01-12T11:52:53.123456Z"));
        try (ParquetWriter<Group> writer = getParquetWriter(schema, false)) {
            final SimpleGroup parquetRow = new SimpleGroup(schema);
            parquetRow.append("col_timestamp", microsSinceEpoch);
            writer.write(parquetRow);
        }
        assertThat(getRecords(), contains(GenericRow.of(timestamp)));
    }

    @Test
    void testReadsInt96TimestampAsTimestampValue() throws IOException {
        final MessageType schema = parseSchema("message test {", "  required int96 col_timestamp;", "}");
        final long nanosSinceStartOfDay = 45_296_123_456_789L;
        try (ParquetWriter<Group> writer = getParquetWriter(schema, false)) {
            final SimpleGroup parquetRow = new SimpleGroup(schema);
            parquetRow.append("col_timestamp", getInt96Timestamp(JULIAN_DAY_OF_EPOCH + 1, nanosSinceStartOfDay));
            writer.write(parquetRow);
        }
        final Timestamp expected = Timestamp.from(Instant.parse("1970-01-02T12:34:56.123456Z"));
        assertThat(getRecords(), contains(GenericRow.of(expected)));
    }

    @Test
    void testReadsInt32DateAsDateValue() throws IOException {
        final MessageType schema = parseSchema("message test {", "  required int32 col_date (DATE);", "}");
        try (ParquetWriter<Group> writer = getParquetWriter(schema, false)) {
            final SimpleGroup parquetRow = new SimpleGroup(schema);
            parquetRow.append("col_date", 19_723);
            writer.write(parquetRow);
        }
        assertThat(getRecords(), contains(GenericRow.of(Date.valueOf("2024-01-01"))));
    }

    private Binary getInt96Timestamp(final int julianDay, final long nanosSinceStartOfDay) {
        final byte[] bytes = ByteBuffer.allocate(12).order(ByteOrder.LITTLE_ENDIAN).putLong(nanosSinceStartOfDay)
                .putInt(julianDay).array();
        return Binary.fromConstantByteArray(bytes);
    }

    @Test
    void testReadsFixedLenByteArrayAsStringValue() throws IOException {
        final int size = 5;
        final MessageType schema = parseSchema("message test {", "  required fixed_len_byte_array(" + size
                + ") col_byte_array;", "}");
        try (ParquetWriter<Group> writer = getParquetWriter(schema, false)) {
            final SimpleGroup parquetRow = new SimpleGroup(schema);
            parquetRow.append("col_byte_array", "hello");
            writer.write(parquetRow);
        }
        assertThat(getRecords(), contains(GenericRow.of("hello")));
    }

    @Test
    void testReadsFixedLenByteArrayUuidAsUuidValue() throws IOException {
        final MessageType schema = parseSchema("message test {", "  required fixed_len_byte_array(16) col_uuid (UUID);",
                "}");
        final UUID uuid = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        try (ParquetWriter<Group> writer = getParquetWriter(schema, false)) {
            final SimpleGroup parquetRow = new SimpleGroup(schema);
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
    void testReadsBinaryAsStringValue() throws IOException {
        final MessageType schema = parseSchema("message test {", "  required binary col_binary;", "}");
        try (ParquetWriter<Group> writer = getParquetWriter(schema, false)) {
            final SimpleGroup parquetRow = new SimpleGroup(schema);
            parquetRow.append("col_binary", "test");
            writer.write(parquetRow);
        }
        assertThat(getRecords(), contains(GenericRow.of("test")));
    }

    @Test
    void testReadsBinaryUtf8AsStringValueUsingDictionaryEncoding() throws IOException {
        final MessageType schema = parseSchema("message test {", "  required binary col_binary (UTF8);", "}");
        try (ParquetWriter<Group> writer = getParquetWriter(schema, true)) {
            for (final String value : List.of("north", "south", "north", "south")) {
                final SimpleGroup parquetRow = new SimpleGroup(schema);
                parquetRow.append("col_binary", value);
                writer.write(parquetRow);
            }
        }
        final List<Row> records = getRecords();
        assertAll(() -> assertThat(records, hasSize(4)),
                () -> assertThat(records.get(0), equalTo(GenericRow.of("north"))),
                () -> assertThat(records.get(1), equalTo(GenericRow.of("south"))));
    }

    @Test
    void testReadsInt32DecimalAsBigDecimalValueUsingDictionaryEncoding() throws IOException {
        final MessageType schema = parseSchema("message test {", "  required int32 col_int (DECIMAL(9,2));", "}");
        try (ParquetWriter<Group> writer = getParquetWriter(schema, true)) {
            for (final int value : List.of(123456789, 0, 0, 123456789)) {
                final SimpleGroup parquetRow = new SimpleGroup(schema);
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
    void testReadsInt64DecimalAsBigDecimalValueUsingDictionaryEncoding() throws IOException {
        final MessageType schema = parseSchema("message test {", "  required int64 column (DECIMAL(18,2));", "}");
        try (ParquetWriter<Group> writer = getParquetWriter(schema, true)) {
            for (final long value : List.of(1234567890123456L, 1L, 1L, 1234567890123456L)) {
                final SimpleGroup parquetRow = new SimpleGroup(schema);
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
    void testReadsBinaryDecimalAsBigDecimalValueUsingDictionaryEncoding() throws IOException {
        final MessageType schema = parseSchema("message test {", "  required binary column (DECIMAL(30,2));", "}");
        final String decimalValue = "123456789012345678901234567890";
        try (ParquetWriter<Group> writer = getParquetWriter(schema, true)) {
            for (final String value : List.of(decimalValue, decimalValue)) {
                final SimpleGroup parquetRow = new SimpleGroup(schema);
                parquetRow.append("column", value);
                writer.write(parquetRow);
            }
        }
        final BigDecimal expected = new BigDecimal("3.39538960730037532986880915048E+69");
        final List<Row> records = getRecords();
        assertAll(() -> assertThat(records, hasSize(2)), () -> assertThat(records.get(0), equalTo(GenericRow.of(expected))),
                () -> assertThat(records.get(1), equalTo(GenericRow.of(expected))));
    }

    @Test
    void testReadsFixedLenByteArrayDecimalAsBigDecimalValueUsingDictionaryEncoding() throws IOException {
        final MessageType schema = parseSchema("message test {",
                "  required fixed_len_byte_array(9) column (DECIMAL(20,2));", "}");
        final String decimalValue = "12345678901234567890";
        final Binary decimalBinary = Binary
                .fromConstantByteArray(new BigDecimal(decimalValue).unscaledValue().toByteArray());
        final Binary zeros = Binary.fromConstantByteArray(new byte[9], 0, 9);
        try (ParquetWriter<Group> writer = getParquetWriter(schema, true)) {
            for (final Binary value : List.of(decimalBinary, zeros)) {
                final SimpleGroup parquetRow = new SimpleGroup(schema);
                parquetRow.append("column", value);
                writer.write(parquetRow);
            }
        }
        final BigDecimal expected = new BigDecimal("123456789012345678.90");
        final List<Row> records = getRecords();
        assertAll(() -> assertThat(records, hasSize(2)), () -> assertThat(records.get(0), equalTo(GenericRow.of(expected))),
                () -> assertThat(records.get(1), equalTo(GenericRow.of(BigDecimal.valueOf(0, 2)))));
    }
}
