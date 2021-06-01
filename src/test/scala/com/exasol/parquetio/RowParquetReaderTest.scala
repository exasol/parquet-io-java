package com.exasol.parquetio

import java.lang.Integer

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
    val input = Seq(1, 173, Int.MaxValue, Int.MinValue)
    withResource(getParquetWriter(schema, true)) { writer =>
      input.foreach { value =>
        val record = new SimpleGroup(schema)
        record.append("col_int", value)
        writer.write(record)
      }
    }
    val records = getRecords()
    val expected = input.map(v => GenericRow.of(Integer.valueOf(v)))
    assert(records.size === 4)
    assert(records === expected)
  }

  test("read file row values using positional index and field names") {
    val schema = MessageTypeParser.parseMessageType(
      """|message test {
         |  required int32 col_int;
         |  required binary col_str;
         |}
         |""".stripMargin
    )
    val values = Seq(1, 10, 13)
    withResource(getParquetWriter(schema, true)) { writer =>
      values.foreach { value =>
        val record = new SimpleGroup(schema)
        record.append("col_int", value)
        record.append("col_str", s"$value")
        writer.write(record)
      }
    }
    val records = getRecords()
    values.zipWithIndex.foreach { case (value, index) =>
      assert(records(index).getValue(0) === value)
      assert(records(index).getValue(1) === s"$value")
      assert(records(index).getValue("col_int") === value)
      assert(records(index).getValue("col_str") === s"$value")
    }
  }

}
