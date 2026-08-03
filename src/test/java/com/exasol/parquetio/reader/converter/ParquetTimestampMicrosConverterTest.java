package com.exasol.parquetio.reader.converter;

import org.junit.jupiter.api.Test;

class ParquetTimestampMicrosConverterTest extends AbstractParquetTimestampConverterTest {
    @Test
    void testConvertsAdjustedToUtc() {
        final RecordingValueHolder holder = convert(true, 1_234_567L);

        assertRecordedValue(holder, 1, DateTimeHelper.getTimestampFromMicros(1_234_567L));
    }

    @Test
    void testConvertsWithoutUtcAdjustment() {
        withTimeZone(GMT_PLUS_ONE, () -> {
            final RecordingValueHolder holder = convert(false, 1_234_567L);

            assertRecordedValue(holder, 1, DateTimeHelper.getLocalTimestampFromMicros(1_234_567L));
        });
    }

    private static RecordingValueHolder convert(final boolean adjustedToUTC, final long value) {
        final RecordingValueHolder holder = new RecordingValueHolder();
        final ParquetTimestampMicrosConverter converter = new ParquetTimestampMicrosConverter(1, holder,
                adjustedToUTC);

        converter.addLong(value);

        return holder;
    }
}
