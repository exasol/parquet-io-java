package com.exasol.parquetio.reader.converter;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.*;
import java.util.TimeZone;

import com.exasol.errorreporting.ExaError;

/**
 * Helper functions to convert date time values.
 */
final class DateTimeHelper {
    private static final LocalDateTime UNIX_EPOCH_DATE_TIME = LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC);
    private static final long JULIAN_DAY_OF_EPOCH = 2440588L;
    private static final long SECONDS_PER_DAY = 60L * 60L * 24L;
    private static final long MILLIS_PER_SECOND = 1000L;
    private static final long MILLIS_PER_DAY = SECONDS_PER_DAY * MILLIS_PER_SECOND;
    private static final long MICROS_PER_MILLIS = 1000L;
    private static final long MICROS_PER_SECOND = MICROS_PER_MILLIS * MILLIS_PER_SECOND;
    private static final long NANOS_PER_MILLIS = 1_000_000L;
    private static final long NANOS_PER_MICROS = 1_000L;
    private static final long NANOS_PER_SECOND = 1_000_000_000L;

    private DateTimeHelper() {
        // utility class
    }

    /**
     * Returns a timestamp from number of microseconds since epoch.
     *
     * @param microseconds microseconds since epoch
     * @return timestamp
     */
    static Timestamp getTimestampFromMicros(final long microseconds) {
        long seconds = microseconds / MICROS_PER_SECOND;
        long micros = microseconds % MICROS_PER_SECOND;
        if (micros < 0) {
            micros += MICROS_PER_SECOND;
            seconds -= 1;
        }
        final Timestamp timestamp = new Timestamp(seconds * MILLIS_PER_SECOND);
        timestamp.setNanos((int) micros * (int) MICROS_PER_MILLIS);
        return timestamp;
    }

    /**
     * Returns a timestamp from a number of microseconds since the local epoch.
     *
     * @param microseconds microseconds since epoch
     * @return timestamp
     */
    static Timestamp getLocalTimestampFromMicros(final long microseconds) {
        return getTimestampFromLocalDateTime(getLocalDateTimeFromEpoch(microseconds, MICROS_PER_SECOND, NANOS_PER_MICROS));
    }

    /**
     * Returns a timestamp from number of nanoseconds since epoch.
     *
     * @param nanoseconds nanoseconds since epoch
     * @return timestamp
     */
    // [impl->dsn~converting-nanosecond-timestamp-values~1]
    static Timestamp getTimestampFromNanos(final long nanoseconds) {
        long seconds = nanoseconds / NANOS_PER_SECOND;
        long nanos = nanoseconds % NANOS_PER_SECOND;
        if (nanos < 0) {
            nanos += NANOS_PER_SECOND;
            seconds -= 1;
        }
        final Timestamp timestamp = new Timestamp(seconds * MILLIS_PER_SECOND);
        timestamp.setNanos((int) nanos);
        return timestamp;
    }

    /**
     * Returns a timestamp from a number of nanoseconds since the local epoch.
     *
     * @param nanoseconds nanoseconds since epoch
     * @return timestamp
     */
    static Timestamp getLocalTimestampFromNanos(final long nanoseconds) {
        return getTimestampFromLocalDateTime(getLocalDateTimeFromEpoch(nanoseconds, NANOS_PER_SECOND, 1));
    }

    private static LocalDateTime getLocalDateTimeFromEpoch(final long value, final long unitsPerSecond,
            final long nanosPerUnit) {
        final long seconds = Math.floorDiv(value, unitsPerSecond);
        final long nanos = Math.floorMod(value, unitsPerSecond) * nanosPerUnit;
        return UNIX_EPOCH_DATE_TIME.plusSeconds(seconds).plusNanos(nanos);
    }

    private static Timestamp getTimestampFromLocalDateTime(final LocalDateTime localDateTime) {
        if (localDateTime.getYear() < 1) {
            throw new IllegalArgumentException(
                    ExaError.messageBuilder("E-PIOJ-8")
                            .message("Local timestamps before year 1 are not supported.")
                            .mitigation("Please use UTC timestamps instead of local timestamps.")
                            .toString());
        }
        return Timestamp.valueOf(localDateTime);
    }

    /**
     * Returns a timestamp from number of milliseconds since epoch.
     *
     * @param millis milliseconds since epoch
     * @return timestamp
     */
    static Timestamp getTimestampFromMillis(final long millis) {
        return new Timestamp(millis);
    }

    /**
     * Returns a timestamp from a number of milliseconds since the local epoch.
     *
     * @param millis milliseconds since epoch
     * @return timestamp
     */
    static Timestamp getLocalTimestampFromMillis(final long millis) {
        return getTimestampFromLocalDateTime(getLocalDateTimeFromEpoch(millis, MILLIS_PER_SECOND, NANOS_PER_MILLIS));
    }

    /**
     * Returns the number of micros since epoch from a timestamp.
     *
     * @param timestamp timestamp
     * @return microseconds since epoch
     */
    static long getMicrosFromTimestamp(final Timestamp timestamp) {
        if (timestamp == null) {
            return 0L;
        }
        return timestamp.getTime() * MICROS_PER_MILLIS + (timestamp.getNanos() / MICROS_PER_MILLIS) % MICROS_PER_MILLIS;
    }

    /**
     * Returns microseconds since epoch from Julian day and nanoseconds in a day.
     *
     * @param day   Julian day
     * @param nanos nanoseconds in a day
     * @return microseconds since epoch
     */
    static long getMicrosFromJulianDay(final int day, final long nanos) {
        final long seconds = (day - JULIAN_DAY_OF_EPOCH) * SECONDS_PER_DAY;
        return seconds * MICROS_PER_SECOND + nanos / MICROS_PER_MILLIS;
    }

    /**
     * Returns the number of days since unix epoch.
     *
     * @param date date
     * @return days since epoch
     */
    static long daysSinceEpoch(final Date date) {
        final long millisUtc = date.getTime();
        final long millis = millisUtc + TimeZone.getTimeZone(ZoneId.systemDefault()).getOffset(millisUtc);
        return (long) Math.floor((double) millis / MILLIS_PER_DAY);
    }

    /**
     * Returns a date given the days since epoch.
     *
     * @param days days since epoch
     * @return date
     */
    static Date daysToDate(final long days) {
        final LocalDateTime date = UNIX_EPOCH_DATE_TIME.plusDays(days);
        final long millis = date.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return new Date(millis);
    }
}
