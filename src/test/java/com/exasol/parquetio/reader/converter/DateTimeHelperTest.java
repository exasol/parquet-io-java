package com.exasol.parquetio.reader.converter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.TimeZone;

import org.junit.jupiter.api.*;

class DateTimeHelperTest {
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
    void testGetTimestampFromMicrosWithPositiveValue() {
        final Timestamp timestamp = DateTimeHelper.getTimestampFromMicros(1_234_567L);

        assertAll(() -> assertThat(timestamp.getTime(), equalTo(1_234L)),
                () -> assertThat(timestamp.getNanos(), equalTo(234_567_000)));
    }

    @Test
    void testGetTimestampFromMicrosWithNegativeValue() {
        final Timestamp timestamp = DateTimeHelper.getTimestampFromMicros(-1L);

        assertAll(() -> assertThat(timestamp.getTime(), equalTo(-1L)),
                () -> assertThat(timestamp.getNanos(), equalTo(999_999_000)));
    }

    @Test
    // [utest->dsn~converting-nanosecond-timestamp-values~1]
    void testGetTimestampFromNanosWithPositiveValue() {
        final Timestamp timestamp = DateTimeHelper.getTimestampFromNanos(1_234_567_890L);

        assertAll(() -> assertThat(timestamp.getTime(), equalTo(1_234L)),
                () -> assertThat(timestamp.getNanos(), equalTo(234_567_890)));
    }

    @Test
    // [utest->dsn~converting-nanosecond-timestamp-values~1]
    void testGetTimestampFromNanosWithNegativeValue() {
        final Timestamp timestamp = DateTimeHelper.getTimestampFromNanos(-1L);

        assertAll(() -> assertThat(timestamp.getTime(), equalTo(-1L)),
                () -> assertThat(timestamp.getNanos(), equalTo(999_999_999)));
    }

    @Test
    void testGetTimestampFromMillis() {
        assertThat(DateTimeHelper.getTimestampFromMillis(1_234L), equalTo(new Timestamp(1_234L)));
    }

    @Test
    void testGetLocalTimestampFromNanosUsesLocalEpoch() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+01:00"));

        assertThat(DateTimeHelper.getLocalTimestampFromNanos(1_234_567_890L),
                equalTo(Timestamp.valueOf("1970-01-01 00:00:01.23456789")));
    }

    @Test
    void testGetLocalTimestampFromMicrosWithValueOutsideNanosecondRange() {
        assertThat(DateTimeHelper.getLocalTimestampFromMicros(10_000_000_000_000_000L),
                equalTo(Timestamp.valueOf("2286-11-20 17:46:40")));
    }

    @Test
    void testGetLocalTimestampFromMillisWithValueOutsideNanosecondRange() {
        assertThat(DateTimeHelper.getLocalTimestampFromMillis(10_000_000_000_000L),
                equalTo(Timestamp.valueOf("2286-11-20 17:46:40")));
    }

    @Test
    void testGetLocalTimestampFromMicrosRejectsYearZero() {
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> DateTimeHelper.getLocalTimestampFromMicros(-62_167_219_200_000_000L));
        assertThat(exception.getMessage(),
                equalTo("E-PIOJ-8: Local timestamps before year 1 are not supported. Please use UTC timestamps instead of local timestamps."));
    }

    @Test
    void testGetLocalTimestampFromMillisRejectsYearZero() {
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> DateTimeHelper.getLocalTimestampFromMillis(-62_167_219_200_000L));
        assertThat(exception.getMessage(),
                equalTo("E-PIOJ-8: Local timestamps before year 1 are not supported. Please use UTC timestamps instead of local timestamps."));
    }

    @Test
    void testGetMicrosFromTimestampReturnsZeroForNull() {
        assertThat(DateTimeHelper.getMicrosFromTimestamp(null), equalTo(0L));
    }

    @Test
    void testGetMicrosFromTimestampWithMicrosPrecision() {
        final Timestamp timestamp = new Timestamp(1_234L);
        timestamp.setNanos(234_567_000);

        assertThat(DateTimeHelper.getMicrosFromTimestamp(timestamp), equalTo(1_234_567L));
    }

    @Test
    void testGetMicrosFromJulianDayAtEpoch() {
        assertThat(DateTimeHelper.getMicrosFromJulianDay(2_440_588, 1_234_567_890L), equalTo(1_234_567L));
    }

    @Test
    void testGetMicrosFromJulianDayBeforeEpoch() {
        assertThat(DateTimeHelper.getMicrosFromJulianDay(2_440_587, 0L), equalTo(-86_400_000_000L));
    }

    @Test
    void testDaysSinceEpochAtEpoch() {
        assertThat(DateTimeHelper.daysSinceEpoch(Date.valueOf("1970-01-01")), equalTo(0L));
    }

    @Test
    void testDaysSinceEpochBeforeEpoch() {
        assertThat(DateTimeHelper.daysSinceEpoch(Date.valueOf("1969-12-31")), equalTo(-1L));
    }

    @Test
    void testDaysToDateAtEpoch() {
        assertThat(DateTimeHelper.daysToDate(0), equalTo(Date.valueOf("1970-01-01")));
    }

    @Test
    void testDaysToDateBeforeEpoch() {
        assertThat(DateTimeHelper.daysToDate(-1), equalTo(Date.valueOf("1969-12-31")));
    }

    @Test
    void testDaysToDateAfterEpoch() {
        assertThat(DateTimeHelper.daysToDate(19_723), equalTo(Date.valueOf("2024-01-01")));
    }
}
