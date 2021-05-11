package com.exasol.parquetio.helper

import java.sql.Date
import java.sql.Timestamp
import java.time._
import java.util.TimeZone

/**
 * Helper functions to convert date time values.
 */
object DateTimeHelper {
  // scalastyle:off magic.number
  val UnixEpochDateTime: LocalDateTime = LocalDateTime.of(1970, 1, 1, 0, 0, 0)
  // scalastyle:on magic.number

  val JULIAN_DAY_OF_EPOCH: Long = 2440588
  val SECONDS_PER_DAY: Long = 60 * 60 * 24L
  val MILLIS_PER_SECOND: Long = 1000L
  val MILLIS_PER_DAY: Long = SECONDS_PER_DAY * MILLIS_PER_SECOND
  val MICROS_PER_MILLIS: Long = 1000L
  val MICROS_PER_SECOND: Long = MICROS_PER_MILLIS * MILLIS_PER_SECOND
  val MICROS_PER_DAY: Long = MICROS_PER_SECOND * SECONDS_PER_DAY

  /**
   * Returns a timestamp from number of
   * microseconds since epoch.
   */
  def getTimestampFromMicros(us: Long): Timestamp = {
    // setNanos() will overwrite the millisecond part, so the
    // milliseconds should be cut off at seconds
    var seconds = us / MICROS_PER_SECOND
    var micros = us % MICROS_PER_SECOND
    if (micros < 0) { // setNanos() can not accept negative value
      micros += MICROS_PER_SECOND
      seconds -= 1
    }
    val ts = new Timestamp(seconds * 1000)
    ts.setNanos(micros.toInt * 1000)

    ts
  }

  /**
   * Returns a timestamp from number of
   * milliseconds since epoch.
   */
  def getTimestampFromMillis(millis: Long): Timestamp =
    new Timestamp(millis)

  /**
   * Returns the number of micros since epoch from a timestamp.
   */
  def getMicrosFromTimestamp(ts: Timestamp): Long =
    if (ts != null) {
      ts.getTime() * 1000L + (ts.getNanos().toLong / 1000) % 1000L
    } else {
      0L
    }

  /**
   * Returns Julian day and nanoseconds in a day from microseconds since
   * epoch.
   */
  @SuppressWarnings(Array("org.wartremover.contrib.warts.ExposedTuples"))
  def getJulianDayAndNanos(us: Long): (Int, Long) = {
    val julianUs = us + JULIAN_DAY_OF_EPOCH * MICROS_PER_DAY
    val day = julianUs / MICROS_PER_DAY
    val micros = julianUs % MICROS_PER_DAY
    (day.toInt, micros * 1000L)
  }

  /**
   * Returns microseconds since epoch from Julian day and nanoseconds in
   * a day.
   */
  def getMicrosFromJulianDay(day: Int, nanos: Long): Long = {
    val seconds = (day - JULIAN_DAY_OF_EPOCH).toLong * SECONDS_PER_DAY
    seconds * MICROS_PER_SECOND + nanos / 1000L
  }

  /** Returns the number of days since unix epoch. */
  @SuppressWarnings(Array("org.wartremover.contrib.warts.OldTime"))
  def daysSinceEpoch(date: Date): Long = {
    val millisUtc = date.getTime
    val millis = millisUtc + (TimeZone.getTimeZone(ZoneId.systemDefault).getOffset(millisUtc))
    Math.floor(millis.toDouble / MILLIS_PER_DAY).toLong
  }

  /** Returns a date given the days since epoch. */
  def daysToDate(days: Long): Date = {
    val date = UnixEpochDateTime.plusDays(days)
    val millis = date.atZone(ZoneId.systemDefault).toInstant.toEpochMilli
    new Date(millis)
  }

}
