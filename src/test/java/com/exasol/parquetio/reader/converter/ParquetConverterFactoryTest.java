package com.exasol.parquetio.reader.converter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

import org.apache.parquet.schema.*;
import org.apache.parquet.schema.LogicalTypeAnnotation.TimeUnit;
import org.apache.parquet.schema.PrimitiveType.PrimitiveTypeName;
import org.junit.jupiter.api.Test;

// [utest->dsn~converting-nanosecond-timestamp-values~1]
class ParquetConverterFactoryTest {
    @Test
    void testCreatesMillisConverterForModernTimestampAnnotation() {
        assertThat(createTimestampConverter(TimeUnit.MILLIS), instanceOf(ParquetTimestampMillisConverter.class));
    }

    @Test
    void testCreatesMicrosConverterForModernTimestampAnnotation() {
        assertThat(createTimestampConverter(TimeUnit.MICROS), instanceOf(ParquetTimestampMicrosConverter.class));
    }

    @Test
    void testCreatesNanosConverterForModernTimestampAnnotation() {
        assertThat(createTimestampConverter(TimeUnit.NANOS), instanceOf(ParquetTimestampNanosConverter.class));
    }

    @Test
    @SuppressWarnings("deprecation") // org.apache.parquet.schema.OriginalType is deprecated, but we need to test the legacy behavior
    void testCreatesMillisConverterForLegacyTimestampAnnotation() {
        assertThat(createLegacyTimestampConverter(OriginalType.TIMESTAMP_MILLIS),
                instanceOf(ParquetTimestampMillisConverter.class));
    }

    @Test
    @SuppressWarnings("deprecation") // org.apache.parquet.schema.OriginalType is deprecated, but we need to test the legacy behavior
    void testCreatesMicrosConverterForLegacyTimestampAnnotation() {
        assertThat(createLegacyTimestampConverter(OriginalType.TIMESTAMP_MICROS),
                instanceOf(ParquetTimestampMicrosConverter.class));
    }

    private static ParquetConverter createTimestampConverter(final TimeUnit timeUnit) {
        final PrimitiveType primitiveType = Types.required(PrimitiveTypeName.INT64)
                .as(LogicalTypeAnnotation.timestampType(true, timeUnit))
                .named("timestamp");
        return ParquetConverterFactory.createPrimitiveConverter(primitiveType, 0, EmptyValueHolder.INSTANCE);
    }

    @SuppressWarnings("deprecation")
    private static ParquetConverter createLegacyTimestampConverter(final OriginalType originalType) {
        final PrimitiveType primitiveType = Types.required(PrimitiveTypeName.INT64).as(originalType).named("timestamp");
        return ParquetConverterFactory.createPrimitiveConverter(primitiveType, 0, EmptyValueHolder.INSTANCE);
    }
}
