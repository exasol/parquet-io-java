package com.exasol.parquetio.reader

import java.math.BigDecimal
import java.math.BigInteger
import java.math.MathContext
import java.nio.charset.StandardCharsets.UTF_8
import java.sql.Timestamp

import com.exasol.parquetio.data.GenericRow
import com.exasol.parquetio.BaseParquetReaderTest

import org.apache.parquet.example.data.simple.SimpleGroup
import org.apache.parquet.io.api.Binary
import org.apache.parquet.schema.MessageTypeParser

// [utest->dsn~converting-primitive-column-types~1]
// [utest->dsn~converting-logical-column-types~1]
class RowParquetReaderPrimitiveTypesTest extends BaseParquetReaderTest {

  test("reads INT64 (TIMESTAMP_MILLIS) as timestamp value") {
    val schema = MessageTypeParser.parseMessageType(
      """|message test {
         |  required int64 col_long;
         |  required int64 col_timestamp (TIMESTAMP_MILLIS);
         |}
         |""".stripMargin
    )
    val timestamp = new Timestamp(System.currentTimeMillis())
    withResource(getParquetWriter(schema, false)) { writer =>
      val record = new SimpleGroup(schema)
      record.append("col_long", 153L)
      record.append("col_timestamp", timestamp.getTime())
      writer.write(record)
    }
    assert(getRecords() === Seq(GenericRow.of(java.lang.Long.valueOf(153), timestamp)))
  }

  test("reads INT64 (TIMESTAMP_MICROS) as timestamp value") {
    val schema = MessageTypeParser.parseMessageType(
      """|message test {
         |  required int64 col_timestamp (TIMESTAMP_MICROS);
         |}
         |""".stripMargin
    )
    val timestamp = Timestamp.valueOf("2022-01-12 11:52:53.123456")
    val micros = timestamp.getTime() * 1000L + (timestamp.getNanos().toLong / 1000) % 1000L
    withResource(getParquetWriter(schema, false)) { writer =>
      val record = new SimpleGroup(schema)
      record.append("col_timestamp", micros)
      writer.write(record)
    }
    assert(getRecords() === Seq(GenericRow.of(timestamp)))
  }

  test("reads FIXED_LEN_BYTE_ARRAY as string value") {
    val size = 5
    val schema = MessageTypeParser.parseMessageType(
      s"""|message test {
          |  required fixed_len_byte_array($size) col_byte_array;
          |}
          |""".stripMargin
    )
    withResource(getParquetWriter(schema, false)) { writer =>
      val record = new SimpleGroup(schema)
      record.append("col_byte_array", "hello")
      writer.write(record)
    }
    assert(getRecords() === Seq(GenericRow.of("hello")))
  }

  test("reads BINARY as string value") {
    val schema = MessageTypeParser.parseMessageType(
      """|message test {
         |  required binary col_binary;
         |}
         |""".stripMargin
    )
    withResource(getParquetWriter(schema, false)) { writer =>
      val record = new SimpleGroup(schema)
      record.append("col_binary", "test")
      writer.write(record)
    }
    assert(getRecords() === Seq(GenericRow.of("test")))
  }

  test("reads BINARY (UTF8) as string value using dictionary encoding") {
    val schema = MessageTypeParser.parseMessageType(
      """|message test {
         |  required binary col_binary (UTF8);
         |}
         |""".stripMargin
    )
    withResource(getParquetWriter(schema, true)) { writer =>
      Seq("test1", "test2", "test1", "test2").foreach { value =>
        val record = new SimpleGroup(schema)
        record.append("col_binary", value)
        writer.write(record)
      }
    }
    val records = getRecords()
    assert(records.size === 4)
    assert(records(0) === GenericRow.of("test1"))
    assert(records(1) === GenericRow.of("test2"))
  }

  test("reads INT32 (decimal) as big decimal value using dictionary encoding") {
    val schema = MessageTypeParser.parseMessageType(
      """|message test {
         |  required int32 col_int (DECIMAL(9,2));
         |}
         |""".stripMargin
    )
    withResource(getParquetWriter(schema, true)) { writer =>
      Seq(123456789, 0, 0, 123456789).foreach { value =>
        val record = new SimpleGroup(schema)
        record.append("col_int", value)
        writer.write(record)
      }
    }
    val records = getRecords()
    assert(records.size === 4)
    assert(records(0) === GenericRow.of(BigDecimal.valueOf(123456789, 2)))
    assert(records(1) === GenericRow.of(BigDecimal.valueOf(0, 2)))
  }

  test("reads INT64 (decimal) as big decimal value using dictionary encoding") {
    val schema = MessageTypeParser.parseMessageType(
      """|message test {
         |  required int64 column (DECIMAL(18,2));
         |}
         |""".stripMargin
    )
    withResource(getParquetWriter(schema, true)) { writer =>
      Seq(1234567890123456L, 1L, 1L, 1234567890123456L).foreach { value =>
        val record = new SimpleGroup(schema)
        record.append("column", value)
        writer.write(record)
      }
    }
    val records = getRecords()
    assert(records.size === 4)
    assert(records(0) === GenericRow.of(BigDecimal.valueOf(1234567890123456L, 2)))
    assert(records(1) === GenericRow.of(BigDecimal.valueOf(1L, 2)))
  }

  test("reads BINARY (decimal) as big decimal value using dictionary encoding") {
    val schema = MessageTypeParser.parseMessageType(
      """|message test {
         |  required binary column (DECIMAL(30,2));
         |}
         |""".stripMargin
    )
    val decimalValue = "123456789012345678901234567890"
    withResource(getParquetWriter(schema, true)) { writer =>
      Seq(decimalValue, decimalValue).foreach { value =>
        val record = new SimpleGroup(schema)
        record.append("column", value)
        writer.write(record)
      }
    }
    val expected = new BigDecimal(new BigInteger(decimalValue.getBytes(UTF_8)), 2, new MathContext(30))
    val records = getRecords()
    assert(records.size === 2)
    assert(records(0) === GenericRow.of(expected))
    assert(records(1) === GenericRow.of(expected))
  }

  test("reads FIXED_LEN_BYTE_ARRAY (decimal) as big decimal value using dictionary encoding") {
    val schema = MessageTypeParser.parseMessageType(
      """|message test {
         |  required fixed_len_byte_array(9) column (DECIMAL(20,2));
         |}
         |""".stripMargin
    )
    val decimalValue = "12345678901234567890"
    val decimalBinary = Binary.fromConstantByteArray(new BigDecimal(decimalValue).unscaledValue().toByteArray())
    val zeros = Binary.fromConstantByteArray(Array.fill[Byte](9)(0x0), 0, 9)
    withResource(getParquetWriter(schema, true)) { writer =>
      Seq(decimalBinary, zeros).foreach { value =>
        val record = new SimpleGroup(schema)
        record.append("column", value)
        writer.write(record)
      }
    }
    val expected = new BigDecimal(new BigInteger(decimalValue), 2, new MathContext(20))
    val records = getRecords()
    assert(records.size === 2)
    assert(records(0) === GenericRow.of(expected))
    assert(records(1) === GenericRow.of(BigDecimal.valueOf(0, 2)))
  }

}
