package com.exasol.parquetio.reader.converter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.sql.Timestamp;
import java.util.*;

import org.apache.parquet.io.api.Binary;
import org.junit.jupiter.api.*;

class ParquetTimestampInt96ConverterTest {
    private static final int JULIAN_DAY_OF_EPOCH = 2_440_588;
    private static final TimeZone UTC = TimeZone.getTimeZone("UTC");
    private TimeZone originalTimeZone;

    @BeforeEach
    void setUp() {
        this.originalTimeZone = TimeZone.getDefault();
        TimeZone.setDefault(UTC);
    }

    @AfterEach
    void tearDown() {
        TimeZone.setDefault(this.originalTimeZone);
    }

    @Test
    void convertsEpochStart() {
        final RecordingValueHolder holder = convert(JULIAN_DAY_OF_EPOCH, 0L);

        assertAll(() -> assertThat(holder.index, equalTo(3)),
                () -> assertThat(holder.value, equalTo(Timestamp.valueOf("1970-01-01 00:00:00"))));
    }

    @Test
    void convertsTimestampAfterEpoch() {
        final long nanosSinceStartOfDay = ((12L * 60L * 60L) + (34L * 60L) + 56L) * 1_000_000_000L
                + 123_456_789L;

        final RecordingValueHolder holder = convert(2_460_311, nanosSinceStartOfDay);

        assertAll(() -> assertThat(holder.index, equalTo(3)),
                () -> assertThat(holder.value, equalTo(Timestamp.valueOf("2024-01-01 12:34:56.123456"))));
    }

    @Test
    void convertsTimestampBeforeEpoch() {
        final RecordingValueHolder holder = convert(JULIAN_DAY_OF_EPOCH - 1, 86_399_999_999_000L);

        assertAll(() -> assertThat(holder.index, equalTo(3)),
                () -> assertThat(holder.value, equalTo(Timestamp.valueOf("1969-12-31 23:59:59.999999"))));
    }

    private static RecordingValueHolder convert(final int julianDay, final long nanosSinceStartOfDay) {
        final RecordingValueHolder holder = new RecordingValueHolder();
        final ParquetTimestampInt96Converter converter = new ParquetTimestampInt96Converter(3, holder);

        converter.addBinary(int96Binary(julianDay, nanosSinceStartOfDay));

        return holder;
    }

    private static Binary int96Binary(final int julianDay, final long nanosSinceStartOfDay) {
        final byte[] bytes = ByteBuffer.allocate(12)
                .order(ByteOrder.LITTLE_ENDIAN)
                .putLong(nanosSinceStartOfDay)
                .putInt(julianDay)
                .array();
        return Binary.fromConstantByteArray(bytes);
    }

    private static final class RecordingValueHolder implements ValueHolder {
        private int index = -1;
        private Object value;

        @Override
        public void reset() {
            this.index = -1;
            this.value = null;
        }

        @Override
        public List<Object> getValues() {
            final List<Object> values = new ArrayList<>();
            values.add(this.value);
            return values;
        }

        @Override
        public void put(final int index, final Object value) {
            this.index = index;
            this.value = value;
        }
    }
}
