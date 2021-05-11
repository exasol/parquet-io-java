package com.exasol.parquetio.reader

import com.exasol.parquetio.data.Row
import com.exasol.parquetio.data.GenericRow
import com.exasol.parquetio.reader.converter.ParquetRootConverter

import org.apache.hadoop.conf.Configuration
import org.apache.parquet.hadoop.api.ReadSupport
import org.apache.parquet.hadoop.api.ReadSupport.ReadContext
import org.apache.parquet.io.api.RecordMaterializer
import org.apache.parquet.schema.MessageType

/**
 * A concrete implementation of [[org.apache.parquet.hadoop.api.ReadSupport]]
 * that materializes Parquet records into internal [[com.exasol.parquetio.data.Row]]
 * structure.
 */
final class RowReadSupport extends ReadSupport[Row] {
  override def prepareForRead(
    conf: Configuration,
    metadata: java.util.Map[String, String],
    messageType: MessageType,
    readContext: ReadContext
  ): RecordMaterializer[Row] =
    new RowRecordMaterializer(messageType, readContext)

  override def init(
    conf: Configuration,
    metadata: java.util.Map[String, String],
    messageType: MessageType
  ): ReadSupport.ReadContext = {
    val projection = conf.get(ReadSupport.PARQUET_READ_SCHEMA)
    val requestedSchema = ReadSupport.getSchemaForRead(messageType, projection)
    new ReadSupport.ReadContext(requestedSchema)
  }

  private[this] final class RowRecordMaterializer(
    messageType: MessageType,
    readContext: ReadContext
  ) extends RecordMaterializer[Row] {
    val rootConverter = ParquetRootConverter(messageType)
    override def getRootConverter(): ParquetRootConverter = rootConverter
    override def skipCurrentRecord(): Unit = getRootConverter().start()
    override def getCurrentRecord(): Row = new GenericRow(getRootConverter().getResult())
  }
}
