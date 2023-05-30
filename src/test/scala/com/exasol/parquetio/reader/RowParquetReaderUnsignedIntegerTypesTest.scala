package com.exasol.parquetio.reader

import java.math.BigDecimal
import java.math.BigInteger
import java.math.MathContext
import java.nio.charset.StandardCharsets.UTF_8

import com.exasol.parquetio.data.GenericRow
import com.exasol.parquetio.BaseParquetReaderTest

import org.apache.parquet.example.data.simple.SimpleGroup
import org.apache.parquet.schema.MessageTypeParser

// [utest->dsn~converting-primitive-column-types~1]
class RowParquetReaderUnsignedIntegerTest extends BaseParquetReaderTest {

  test("reads unsigned values") {
    val schema = MessageTypeParser.parseMessageType(
      """|message test {
         |  required int32 col_uint8;
         |  required int32 col_uint16;
         |  required int64 col_uint32;
         |  required binary col_uint64 (DECIMAL(20,0));
         |}
         |""".stripMargin
    )
    val bigValueString = "18446744073709551615"
    withResource(getParquetWriter(schema, true)) { writer =>
      val record = new SimpleGroup(schema)
      record.append("col_uint8", 255)
      record.append("col_uint16", 65535)
      record.append("col_uint32", 4294967295L)
      record.append("col_uint64", bigValueString)
      writer.write(record)
    }
    val bigValueDecimal = new BigDecimal(new BigInteger(bigValueString.getBytes(UTF_8)), 0, new MathContext(20))
    assert(getRecords() === Seq(GenericRow.of(255, 65535, java.lang.Long.valueOf(4294967295L), bigValueDecimal)))
  }

}
