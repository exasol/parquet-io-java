package com.exasol.parquetio.reader.converter

import java.util.Collections

import org.apache.parquet.column.Dictionary
import org.apache.parquet.io.api.Binary
import org.apache.parquet.io.api.Converter
import org.apache.parquet.io.api.GroupConverter
import org.apache.parquet.io.api.PrimitiveConverter
import org.apache.parquet.schema.GroupType
import org.apache.parquet.schema.PrimitiveType

/**
 * A Parquet converter for the
 * [[org.apache.parquet.schema.Type.Repetition.REPEATED]] group with a single
 * type.
 *
 * It is converted into an array of values.
 *
 * The following schema is converted with this converter:
 * {{{
 * message parquet_file_schema {
 *   repeated binary name (UTF8);
 * }
 * }}}
 */
final case class RepeatedPrimitiveConverter(elementType: PrimitiveType, index: Int, parentDataHolder: ValueHolder)
    extends PrimitiveConverter
    with ParquetConverter
    with ValueHolder {

  private[this] val dataHolder = new AppendedValueHolder()
  private[this] val primitiveConverter = ParquetConverterFactory
    .createPrimitiveConverter(elementType, index, this)
    .asPrimitiveConverter()

  override def addBinary(value: Binary): Unit = primitiveConverter.addBinary(value)
  override def addBoolean(value: Boolean): Unit = primitiveConverter.addBoolean(value)
  override def addDouble(value: Double): Unit = primitiveConverter.addDouble(value)
  override def addFloat(value: Float): Unit = primitiveConverter.addFloat(value)
  override def addInt(value: Int): Unit = primitiveConverter.addInt(value)
  override def addLong(value: Long): Unit = primitiveConverter.addLong(value)

  override def hasDictionarySupport(): Boolean = primitiveConverter.hasDictionarySupport()

  override def setDictionary(dictionary: Dictionary): Unit =
    primitiveConverter.setDictionary(dictionary)

  override def addValueFromDictionary(dictionaryId: Int): Unit =
    primitiveConverter.addValueFromDictionary(dictionaryId)

  override def parentStart(): Unit = dataHolder.reset()
  override def parentEnd(): Unit = parentDataHolder.put(index, dataHolder.getValues())
  override def reset(): Unit = {
    //
  }

  override def getValues(): java.util.List[Any] = Collections.emptyList()
  override def put(index: Int, value: Any): Unit = dataHolder.put(index, value)
}

/**
 * A Parquet converter for the {@link REPEATED} group type.
 *
 * It is converted into an array of key value maps.
 *
 * The following schemas are converted with this converter:
 * {{{
 * message parquet_file_schema {
 *   repeated group person {
 *     required binary name (UTF8);
 *   }
 * }
 *
 * and
 *
 * message parquet_file_schema {
 *   repeated group person {
 *     required binary name (UTF8);
 *     optional int32 age;
 *   }
 * }
 * }}}
 */
final case class RepeatedGroupConverter(groupType: GroupType, index: Int, parentDataHolder: ValueHolder)
    extends GroupConverter
    with ParquetConverter
    with ValueHolder {

  private[this] val dataHolder = new AppendedValueHolder()
  private[this] val groupConverter: GroupConverter =
    if (groupType.getFieldCount() > 1) {
      StructConverter(groupType, index, this)
    } else {
      val innerPrimitiveType = groupType.getType(0).asPrimitiveType()
      SingleValueConverter(innerPrimitiveType, index, dataHolder)
    }

  override def start(): Unit = groupConverter.start()
  override def end(): Unit = groupConverter.end()

  override def getConverter(fieldIndex: Int): Converter =
    groupConverter.getConverter(fieldIndex)

  override def parentStart(): Unit = dataHolder.reset()
  override def parentEnd(): Unit = {
    val values = dataHolder.getValues()
    parentDataHolder.put(index, values)
  }
  override def reset(): Unit = {
    //
  }

  override def getValues(): java.util.List[Any] = Collections.emptyList()
  override def put(index: Int, value: Any): Unit = dataHolder.put(index, value)
}

final case class SingleValueConverter(valueType: PrimitiveType, index: Int, parentDataHolder: ValueHolder)
    extends GroupConverter {

  private[this] var currentValue: Any = _

  private[this] val singleValueConverter =
    ParquetConverterFactory(
      valueType,
      index,
      new ValueHolder {
        override def put(index: Int, value: Any): Unit = currentValue = value
        override def reset(): Unit = {
          //
        }
        override def getValues(): java.util.List[Any] = java.util.Collections.emptyList()
      }
    )

  override def getConverter(fieldIndex: Int): Converter = singleValueConverter
  override def end(): Unit = parentDataHolder.put(index, currentValue)
  override def start(): Unit = currentValue = null
}
