package com.exasol.parquetio.reader.converter;

import java.util.Objects;

import org.apache.parquet.schema.*;
import org.apache.parquet.schema.Type.Repetition;

/**
 * Factory for Parquet field data type converters.
 */
@SuppressWarnings("deprecation")
public final class ParquetConverterFactory {
    private ParquetConverterFactory() {
        // utility class
    }

    /**
     * Creates a Parquet converter for a field type.
     *
     * @param parquetType      Parquet type
     * @param fieldIndex       field index in the Parquet schema
     * @param parentDataHolder parent value holder
     * @return converter for the field type
     */
    public static ParquetConverter create(final Type parquetType, final int fieldIndex,
            final ValueHolder parentDataHolder) {
        if (parquetType.isPrimitive()) {
            if (parquetType.isRepetition(Repetition.REPEATED)) {
                return new RepeatedPrimitiveConverter(parquetType.asPrimitiveType(), fieldIndex, parentDataHolder);
            }
            return createPrimitiveConverter(parquetType.asPrimitiveType(), fieldIndex, parentDataHolder);
        }
        return createGroupConverter(parquetType, fieldIndex, parentDataHolder);
    }

    static ParquetConverter createPrimitiveConverter(final PrimitiveType parquetType, final int index,
            final ValueHolder parentHolder) {
        switch (parquetType.getPrimitiveTypeName()) {
            case BOOLEAN:
            case DOUBLE:
            case FLOAT:
                return new ParquetPrimitiveConverter(index, parentHolder);
            case BINARY:
                return createBinaryConverter(parquetType, index, parentHolder);
            case FIXED_LEN_BYTE_ARRAY:
                return createFixedByteArrayConverter(parquetType, index, parentHolder);
            case INT32:
                return createIntegerConverter(parquetType, index, parentHolder);
            case INT64:
                return createLongConverter(parquetType, index, parentHolder);
            case INT96:
                return new ParquetTimestampInt96Converter(index, parentHolder);
            default:
                throw new UnsupportedOperationException(
                        "Unsupported primitive type: " + parquetType.getPrimitiveTypeName());
        }
    }

    private static ParquetConverter createGroupConverter(final Type parquetType, final int index,
            final ValueHolder parentHolder) {
        final OriginalType originalType = parquetType.getOriginalType();
        if (originalType == OriginalType.LIST) {
            return createArrayConverter(parquetType.asGroupType().getType(0), index, parentHolder);
        } else if (originalType == OriginalType.MAP) {
            return new MapConverter(parquetType.asGroupType(), index, parentHolder);
        } else if (parquetType.asGroupType().isRepetition(Repetition.REPEATED)) {
            return new RepeatedGroupConverter(parquetType.asGroupType(), index, parentHolder);
        } else {
            return new StructConverter(parquetType.asGroupType(), index, parentHolder);
        }
    }

    private static ParquetConverter createBinaryConverter(final PrimitiveType primitiveType, final int index,
            final ValueHolder holder) {
        final OriginalType originalType = primitiveType.getOriginalType();
        if (originalType == OriginalType.UTF8) {
            return new ParquetStringConverter(index, holder);
        } else if (originalType == OriginalType.DECIMAL) {
            return new ParquetDecimalConverter(primitiveType, index, holder);
        }
        return new ParquetPrimitiveConverter(index, holder);
    }

    private static ParquetConverter createFixedByteArrayConverter(final PrimitiveType primitiveType, final int index,
            final ValueHolder holder) {
        if (Objects.equals(primitiveType.getLogicalTypeAnnotation(), LogicalTypeAnnotation.uuidType())) {
            return new ParquetUUIDConverter(index, holder);
        }
        if (primitiveType.getOriginalType() == OriginalType.DECIMAL) {
            return new ParquetDecimalConverter(primitiveType, index, holder);
        }
        return new ParquetPrimitiveConverter(index, holder);
    }

    private static ParquetConverter createIntegerConverter(final PrimitiveType primitiveType, final int index,
            final ValueHolder holder) {
        final OriginalType originalType = primitiveType.getOriginalType();
        if (originalType == OriginalType.DATE) {
            return new ParquetDateConverter(index, holder);
        } else if (originalType == OriginalType.DECIMAL) {
            return new ParquetDecimalConverter(primitiveType, index, holder);
        }
        return new ParquetPrimitiveConverter(index, holder);
    }

    private static ParquetConverter createLongConverter(final PrimitiveType primitiveType, final int index,
            final ValueHolder holder) {
        final OriginalType originalType = primitiveType.getOriginalType();
        if (originalType == OriginalType.TIMESTAMP_MILLIS) {
            return new ParquetTimestampMillisConverter(index, holder);
        } else if (originalType == OriginalType.TIMESTAMP_MICROS) {
            return new ParquetTimestampMicrosConverter(index, holder);
        } else if (originalType == OriginalType.DECIMAL) {
            return new ParquetDecimalConverter(primitiveType, index, holder);
        }
        return new ParquetPrimitiveConverter(index, holder);
    }

    private static ParquetConverter createArrayConverter(final Type repeatedType, final int index,
            final ValueHolder holder) {
        if (repeatedType.isPrimitive()) {
            return new ArrayPrimitiveConverter(repeatedType.asPrimitiveType(), index, holder);
        } else if (repeatedType.asGroupType().getFieldCount() > 1) {
            return new ArrayGroupConverter(repeatedType, index, holder);
        } else {
            return new ArrayGroupConverter(repeatedType.asGroupType().getType(0), index, holder);
        }
    }
}
