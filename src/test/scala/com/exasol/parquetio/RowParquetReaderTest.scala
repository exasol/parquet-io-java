package com.exasol.parquetio

import com.exasol.parquetio.data.GenericRow

import org.apache.parquet.example.data.simple.SimpleGroup
import org.apache.parquet.schema.MessageTypeParser

// [utest->dsn~read-parquet-file-contents~1]
class RowParquetReaderTest extends BaseParquetReaderTest {

  test("reads file contents") {
    val schema = MessageTypeParser.parseMessageType(
      """|message test {
         |  required int32 col_int;
         |}
         |""".stripMargin
    )
    withResource(getParquetWriter(schema, true)) { writer =>
      Seq(1, 173, Int.MaxValue, Int.MinValue).foreach { value =>
        val record = new SimpleGroup(schema)
        record.append("col_int", value)
        writer.write(record)
      }
    }
    val records = getRecords()
    assert(records.size === 4)
    assert(records === Seq(
      GenericRow.of(1),
      GenericRow.of(173),
      GenericRow.of(Int.MaxValue),
      GenericRow.of(Int.MinValue)
    ))
  }

}
