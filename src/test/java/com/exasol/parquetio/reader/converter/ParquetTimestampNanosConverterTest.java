package com.exasol.parquetio.reader.converter;

import org.junit.jupiter.api.Test;

class ParquetTimestampNanosConverterTest extends AbstractParquetTimestampConverterTest {
    @Test
    void testConvertsAdjustedToUtc() {
        final RecordingValueHolder holder = convert(true, 1_234_567_890L);

        assertRecordedValue(holder, 1, DateTimeHelper.getTimestampFromNanos(1_234_567_890L));
    }

    @Test
    void testConvertsWithoutUtcAdjustment() {
        withTimeZone(GMT_PLUS_ONE, () -> {
            final RecordingValueHolder holder = convert(false, 1_234_567_890L);

            assertRecordedValue(holder, 1, DateTimeHelper.getLocalTimestampFromNanos(1_234_567_890L));
        });
    }

    private static RecordingValueHolder convert(final boolean adjustedToUTC, final long value) {
        final RecordingValueHolder holder = new RecordingValueHolder();
        final ParquetTimestampNanosConverter converter = new ParquetTimestampNanosConverter(1, holder,
                adjustedToUTC);

        converter.addLong(value);

        return holder;
    }
}
