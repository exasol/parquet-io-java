package com.exasol.parquetio.reader.converter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

abstract class AbstractParquetTimestampConverterTest {
    protected static final TimeZone UTC = TimeZone.getTimeZone("UTC");
    protected static final TimeZone GMT_PLUS_ONE = TimeZone.getTimeZone("GMT+01:00");
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

    protected final void assertRecordedValue(final RecordingValueHolder holder, final int expectedIndex,
            final Object expectedValue) {
        assertAll(() -> assertThat(holder.index, equalTo(expectedIndex)),
                () -> assertThat(holder.value, equalTo(expectedValue)));
    }

    protected final void withTimeZone(final TimeZone timeZone, final Runnable action) {
        final TimeZone timeZoneBeforeAction = TimeZone.getDefault();
        try {
            TimeZone.setDefault(timeZone);
            action.run();
        } finally {
            TimeZone.setDefault(timeZoneBeforeAction);
        }
    }

    protected static final class RecordingValueHolder implements ValueHolder {
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
