package com.exasol.parquetio.reader.converter

import java.util.ArrayList
import java.util.List

import org.apache.parquet.schema.GroupType

/**
 * The main Parquet data types to {@link Row} converter class.
 *
 * It calls separate converters for each field of the Parquet schema.
 *
 * @param schema the main schema for the Parquet file
 */
final case class ParquetRootConverter(schema: GroupType) extends AbstractStructConverter(schema, -1, EmptyValueHolder) {

  /**
   * Returns deserialized Parquet field values for this schema.
   *
   * @return list of deserialized objects
   */
  def getResult(): List[Object] = {
    val result = new ArrayList[AnyRef]()
    val values = dataHolder.getValues()
    var idx = 0
    while (idx < values.size) {
      result.add(values.get(idx).asInstanceOf[AnyRef]) // safe cast
      idx = idx + 1
    }
    result
  }

  override def endOperation(): Unit = {
    // no end operation
  }

}
