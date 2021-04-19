package com.exasol.parquetio

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{Path => HPath}
import org.apache.parquet.example.data.Group
import org.apache.parquet.example.data.GroupWriter
import org.apache.parquet.hadoop.ParquetWriter
import org.apache.parquet.hadoop.api.WriteSupport
import org.apache.parquet.io.api.RecordConsumer
import org.apache.parquet.schema._

trait ParquetTestDataWriter {

  final def getParquetWriter(
    path: HPath,
    schema: MessageType,
    dictionaryEncoding: Boolean
  ): ParquetWriter[Group] =
    BaseGroupWriterBuilder(path, schema)
      .withDictionaryEncoding(dictionaryEncoding)
      .build()

  private[this] case class BaseGroupWriteSupport(schema: MessageType)
      extends WriteSupport[Group] {
    var writer: GroupWriter = null

    override def prepareForWrite(recordConsumer: RecordConsumer): Unit =
      writer = new GroupWriter(recordConsumer, schema)

    override def init(configuration: Configuration): WriteSupport.WriteContext =
      new WriteSupport.WriteContext(schema, new java.util.HashMap[String, String]())

    override def write(record: Group): Unit =
      writer.write(record)
  }

  private[this] case class BaseGroupWriterBuilder(path: HPath, schema: MessageType)
      extends ParquetWriter.Builder[Group, BaseGroupWriterBuilder](path) {
    override def getWriteSupport(conf: Configuration): WriteSupport[Group] =
      BaseGroupWriteSupport(schema)
    override def self(): BaseGroupWriterBuilder = this
  }

}
