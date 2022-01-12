package com.exasol.parquetio.reader.converter

import org.apache.parquet.schema.LogicalTypeAnnotation
import org.apache.parquet.schema.OriginalType
import org.apache.parquet.schema.PrimitiveType
import org.apache.parquet.schema.PrimitiveType.PrimitiveTypeName._
import org.apache.parquet.schema.Type
import org.apache.parquet.schema.Type.Repetition

/**
 * Parquet field data type converter factory class.
 */
object ParquetConverterFactory {

  /**
   * Creates Parquet converters that convert schema field value type
   * into a Java datatype.
   *
   * If Parquet field is a complex datatype, then it is converted to the
   * JSON string.
   *
   * @param fieldIndex a field index in the Parquet schema
   * @param parquetType a Parquet schema field type
   * @param parentDataHolder a parent data holder for this field
   * @return specific data converter for the field type
   */
  def apply(parquetType: Type, fieldIndex: Int, parentDataHolder: ValueHolder): ParquetConverter =
    if (parquetType.isPrimitive()) {
      if (parquetType.isRepetition(Repetition.REPEATED)) {
        RepeatedPrimitiveConverter(parquetType.asPrimitiveType(), fieldIndex, parentDataHolder)
      } else {
        createPrimitiveConverter(parquetType.asPrimitiveType(), fieldIndex, parentDataHolder)
      }
    } else {
      createGroupConverter(parquetType, fieldIndex, parentDataHolder)
    }

  private[converter] def createPrimitiveConverter(
    parquetType: PrimitiveType,
    index: Int,
    parentHolder: ValueHolder
  ): ParquetConverter =
    parquetType.getPrimitiveTypeName() match {
      case BOOLEAN              => ParquetPrimitiveConverter(index, parentHolder)
      case DOUBLE               => ParquetPrimitiveConverter(index, parentHolder)
      case FLOAT                => ParquetPrimitiveConverter(index, parentHolder)
      case BINARY               => createBinaryConverter(parquetType, index, parentHolder)
      case FIXED_LEN_BYTE_ARRAY => createFixedByteArrayConverter(parquetType, index, parentHolder)
      case INT32                => createIntegerConverter(parquetType, index, parentHolder)
      case INT64                => createLongConverter(parquetType, index, parentHolder)
      case INT96                => ParquetTimestampInt96Converter(index, parentHolder)
    }

  private[converter] def createGroupConverter(
    parquetType: Type,
    index: Int,
    parentHolder: ValueHolder
  ): ParquetConverter = {
    val groupType = parquetType.asGroupType()
    parquetType.getOriginalType() match {
      case OriginalType.LIST => createArrayConverter(groupType.getType(0), index, parentHolder)
      case OriginalType.MAP  => MapConverter(parquetType.asGroupType(), index, parentHolder)
      case _ =>
        if (groupType.isRepetition(Repetition.REPEATED)) {
          RepeatedGroupConverter(groupType, index, parentHolder)
        } else {
          StructConverter(groupType, index, parentHolder)
        }
    }
  }

  private[this] def createBinaryConverter(
    primitiveType: PrimitiveType,
    index: Int,
    holder: ValueHolder
  ): ParquetConverter = primitiveType.getOriginalType() match {
    case OriginalType.UTF8    => ParquetStringConverter(index, holder)
    case OriginalType.DECIMAL => ParquetDecimalConverter(primitiveType, index, holder)
    case _                    => ParquetPrimitiveConverter(index, holder)
  }

  private[this] def createFixedByteArrayConverter(
    primitiveType: PrimitiveType,
    index: Int,
    holder: ValueHolder
  ): ParquetConverter =
    if (primitiveType.getLogicalTypeAnnotation == LogicalTypeAnnotation.uuidType()) {
      ParquetUUIDConverter(index, holder)
    } else {
      primitiveType.getOriginalType() match {
        case OriginalType.DECIMAL => ParquetDecimalConverter(primitiveType, index, holder)
        case _                    => ParquetPrimitiveConverter(index, holder)
      }
    }

  private[this] def createIntegerConverter(
    primitiveType: PrimitiveType,
    index: Int,
    holder: ValueHolder
  ): ParquetConverter = primitiveType.getOriginalType() match {
    case OriginalType.DATE    => ParquetDateConverter(index, holder)
    case OriginalType.DECIMAL => ParquetDecimalConverter(primitiveType, index, holder)
    case _                    => ParquetPrimitiveConverter(index, holder)
  }

  private[this] def createLongConverter(
    primitiveType: PrimitiveType,
    index: Int,
    holder: ValueHolder
  ): ParquetConverter = primitiveType.getOriginalType() match {
    case OriginalType.TIMESTAMP_MILLIS => ParquetTimestampMillisConverter(index, holder)
    case OriginalType.TIMESTAMP_MICROS => ParquetTimestampMicrosConverter(index, holder)
    case OriginalType.DECIMAL          => ParquetDecimalConverter(primitiveType, index, holder)
    case _                             => ParquetPrimitiveConverter(index, holder)
  }

  private[this] def createArrayConverter(
    repeatedType: Type,
    index: Int,
    holder: ValueHolder
  ): ParquetConverter =
    if (repeatedType.isPrimitive()) {
      ArrayPrimitiveConverter(repeatedType.asPrimitiveType(), index, holder)
    } else if (repeatedType.asGroupType().getFieldCount() > 1) {
      ArrayGroupConverter(repeatedType, index, holder)
    } else {
      val innerElementType = repeatedType.asGroupType().getType(0)
      ArrayGroupConverter(innerElementType, index, holder)
    }

}
