package com.exasol.parquetio.helper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.TimeZone;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void getTimestampFromMicrosWithPositiveValue() {
        final Timestamp timestamp = DateTimeHelper.getTimestampFromMicros(1_234_567L);

        assertAll(() -> assertThat(timestamp.getTime(), equalTo(1_234L)),
                () -> assertThat(timestamp.getNanos(), equalTo(234_567_000)));
    }

    @Test
    void getTimestampFromMicrosWithNegativeValue() {
        final Timestamp timestamp = DateTimeHelper.getTimestampFromMicros(-1L);

        assertAll(() -> assertThat(timestamp.getTime(), equalTo(-1L)),
                () -> assertThat(timestamp.getNanos(), equalTo(999_999_000)));
    }

    @Test
    void getTimestampFromMillis() {
        assertThat(DateTimeHelper.getTimestampFromMillis(1_234L), equalTo(new Timestamp(1_234L)));
    }

    @Test
    void getMicrosFromTimestampReturnsZeroForNull() {
        assertThat(DateTimeHelper.getMicrosFromTimestamp(null), equalTo(0L));
    }

    @Test
    void getMicrosFromTimestampWithMicrosPrecision() {
        final Timestamp timestamp = new Timestamp(1_234L);
        timestamp.setNanos(234_567_000);

        assertThat(DateTimeHelper.getMicrosFromTimestamp(timestamp), equalTo(1_234_567L));
    }

    @Test
    void getMicrosFromJulianDayAtEpoch() {
        assertThat(DateTimeHelper.getMicrosFromJulianDay(2_440_588, 1_234_567_890L), equalTo(1_234_567L));
    }

    @Test
    void getMicrosFromJulianDayBeforeEpoch() {
        assertThat(DateTimeHelper.getMicrosFromJulianDay(2_440_587, 0L), equalTo(-86_400_000_000L));
    }

    @Test
    void daysSinceEpochAtEpoch() {
        assertThat(DateTimeHelper.daysSinceEpoch(Date.valueOf("1970-01-01")), equalTo(0L));
    }

    @Test
    void daysSinceEpochBeforeEpoch() {
        assertThat(DateTimeHelper.daysSinceEpoch(Date.valueOf("1969-12-31")), equalTo(-1L));
    }

    @Test
    void daysToDateAtEpoch() {
        assertThat(DateTimeHelper.daysToDate(0), equalTo(Date.valueOf("1970-01-01")));
    }

    @Test
    void daysToDateBeforeEpoch() {
        assertThat(DateTimeHelper.daysToDate(-1), equalTo(Date.valueOf("1969-12-31")));
    }

    @Test
    void daysToDateAfterEpoch() {
        assertThat(DateTimeHelper.daysToDate(19_723), equalTo(Date.valueOf("2024-01-01")));
    }
}
