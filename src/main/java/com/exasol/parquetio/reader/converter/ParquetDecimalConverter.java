package com.exasol.parquetio.reader.converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

import org.apache.parquet.column.Dictionary;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.io.api.PrimitiveConverter;
import org.apache.parquet.schema.LogicalTypeAnnotation.DecimalLogicalTypeAnnotation;
import org.apache.parquet.schema.PrimitiveType;
import org.apache.parquet.schema.PrimitiveType.PrimitiveTypeName;

/**
 * Converter for {@code DECIMAL} annotated Parquet types.
 */
// [impl->dsn~converting-logical-column-types~1]
public final class ParquetDecimalConverter extends PrimitiveConverter implements ParquetConverter {
    private final PrimitiveType primitiveType;
    private final int index;
    private final ValueHolder holder;
    private final int precision;
    private final int scale;
    private BigDecimal[] decodedDictionary;

    /**
     * Create a new decimal converter.
     *
     * @param primitiveType Parquet primitive type
     * @param index field index
     * @param holder value holder
     */
    public ParquetDecimalConverter(final PrimitiveType primitiveType, final int index, final ValueHolder holder) {
        this.primitiveType = primitiveType;
        this.index = index;
        this.holder = holder;
        final DecimalLogicalTypeAnnotation decimalType = (DecimalLogicalTypeAnnotation) primitiveType
                .getLogicalTypeAnnotation();
        this.precision = decimalType.getPrecision();
        this.scale = decimalType.getScale();
    }

    @Override
    public boolean hasDictionarySupport() {
        return true;
    }

    @Override
    public void setDictionary(final Dictionary dictionary) {
        this.decodedDictionary = new BigDecimal[dictionary.getMaxId() + 1];
        for (int id = 0; id <= dictionary.getMaxId(); id++) {
            this.decodedDictionary[id] = getDecimalFromType(dictionary, id);
        }
    }

    private BigDecimal getDecimalFromType(final Dictionary dictionary, final int id) {
        final PrimitiveTypeName primitiveTypeName = this.primitiveType.getPrimitiveTypeName();
        switch (primitiveTypeName) {
        case INT32:
            return getDecimalFromLong(dictionary.decodeToInt(id));
        case INT64:
            return getDecimalFromLong(dictionary.decodeToLong(id));
        case BINARY:
        case FIXED_LEN_BYTE_ARRAY:
            return getDecimalFromBinary(dictionary.decodeToBinary(id));
        default:
            throw new UnsupportedOperationException("Cannot convert parquet type to decimal type. Please check that "
                    + "Parquet decimal type is stored as INT32, INT64, BINARY or FIXED_LEN_BYTE_ARRAY.");
        }
    }

    private BigDecimal getDecimalFromLong(final long value) {
        return BigDecimal.valueOf(value, this.scale);
    }

    private BigDecimal getDecimalFromBinary(final Binary value) {
        final BigInteger bigInteger = new BigInteger(value.getBytes());
        return new BigDecimal(bigInteger, this.scale, new MathContext(this.precision));
    }

    @Override
    public void addInt(final int value) {
        this.holder.put(this.index, getDecimalFromLong(value));
    }

    @Override
    public void addLong(final long value) {
        this.holder.put(this.index, getDecimalFromLong(value));
    }

    @Override
    public void addBinary(final Binary value) {
        this.holder.put(this.index, getDecimalFromBinary(value));
    }

    @Override
    public void addValueFromDictionary(final int dictionaryId) {
        this.holder.put(this.index, this.decodedDictionary[dictionaryId]);
    }
}
