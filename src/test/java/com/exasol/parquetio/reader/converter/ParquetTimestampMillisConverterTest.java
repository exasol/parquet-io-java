package com.exasol.parquetio.reader.converter;

import org.junit.jupiter.api.Test;

class ParquetTimestampMillisConverterTest extends AbstractParquetTimestampConverterTest {
    @Test
    void testConvertsAdjustedToUtc() {
        final RecordingValueHolder holder = convert(true, 1_234L);

        assertRecordedValue(holder, 1, DateTimeHelper.getTimestampFromMillis(1_234L));
    }

    @Test
    void testConvertsWithoutUtcAdjustment() {
        withTimeZone(GMT_PLUS_ONE, () -> {
            final RecordingValueHolder holder = convert(false, 1_234L);

            assertRecordedValue(holder, 1, DateTimeHelper.getLocalTimestampFromMillis(1_234L));
        });
    }

    private static RecordingValueHolder convert(final boolean adjustedToUTC, final long value) {
        final RecordingValueHolder holder = new RecordingValueHolder();
        final ParquetTimestampMillisConverter converter = new ParquetTimestampMillisConverter(1, holder,
                adjustedToUTC);

        converter.addLong(value);

        return holder;
    }
}
