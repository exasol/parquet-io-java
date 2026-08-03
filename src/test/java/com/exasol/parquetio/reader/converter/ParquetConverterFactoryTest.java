package com.exasol.parquetio.reader.converter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

import org.apache.parquet.schema.LogicalTypeAnnotation;
import org.apache.parquet.schema.LogicalTypeAnnotation.TimeUnit;
import org.apache.parquet.schema.PrimitiveType;
import org.apache.parquet.schema.PrimitiveType.PrimitiveTypeName;
import org.apache.parquet.schema.Types;
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

    private static ParquetConverter createTimestampConverter(final TimeUnit timeUnit) {
        final PrimitiveType primitiveType = Types.required(PrimitiveTypeName.INT64)
                .as(LogicalTypeAnnotation.timestampType(true, timeUnit))
                .named("timestamp");
        return ParquetConverterFactory.createPrimitiveConverter(primitiveType, 0, EmptyValueHolder.INSTANCE);
    }
}
