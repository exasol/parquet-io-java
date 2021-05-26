package com.exasol.parquetio.data;

import org.apache.parquet.io.InvalidRecordException;
import org.apache.parquet.schema.*;

import static org.apache.parquet.schema.PrimitiveType.PrimitiveTypeName.INT32;
import static org.apache.parquet.schema.PrimitiveType.PrimitiveTypeName.INT64;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class GenericRowTest {

    private static MessageType schema;

    @BeforeAll
    static void setUp() {
        schema = Types.buildMessage().addFields( //
                Types.primitive(INT32, Type.Repetition.OPTIONAL).named("col_int"), //
                Types.primitive(INT64, Type.Repetition.OPTIONAL).named("col_long")) //
                .named("parquet_schema");
    }

    @Test
    void testGenericRowPositional() {
        final GenericRow row = GenericRow.of(1, "hello", 12L);
        assertAll(() -> assertThat(row.getValue(0), equalTo(1)), //
                () -> assertThat(row.getValue(1), equalTo("hello")), //
                () -> assertThat(row.getValue(2), equalTo(12L)));
    }

    @Test
    void testGenericRowPositionalFieldNameThrows() {
        final GenericRow row = GenericRow.of("world");
        assertThrows(IllegalArgumentException.class, () -> row.getValue("first"));
    }

    @Test
    void testGenericRowFieldNameDoesNotExistThrows() {
        final GenericRow row = GenericRow.of(schema, 1, 2);
        assertThrows(InvalidRecordException.class, () -> row.getValue("col"));
    }

    @Test
    void testGenericRowWithSchema() {
        final GenericRow row = GenericRow.of(schema, 13, 14L);
        assertAll(() -> assertThat(row.getValue("col_int"), equalTo(13)), //
                () -> assertThat(row.getValue("col_long"), equalTo(14L)));
    }

    @Test
    void testGenericRowIsNullPositional() {
        final GenericRow row = GenericRow.of(1L, null);
        assertAll(() -> assertThat(row.isNull(0), equalTo(false)), //
                () -> assertThat(row.isNull(1), equalTo(true)));
    }

    @Test
    void testGenericRowIsNullFieldName() {
        final GenericRow row = GenericRow.of(schema, 1L, null);
        assertAll(() -> assertThat(row.isNull(0), equalTo(false)),
                () -> assertThat(row.isNull(1), equalTo(true)));
    }

    @Test
    void testGenericRowGetFieldNames() {
        final GenericRow row = GenericRow.of(schema, 1L, 2L);
        assertThat(row.getFieldNames(), contains("col_int", "col_long"));
    }

    @Test
    void testGenericRowHasFieldName() {
        final GenericRow row = GenericRow.of(schema, 1L, 2L);
        assertAll(() -> assertThat(row.hasFieldName("col_int"), equalTo(true)), //
                () -> assertThat(row.hasFieldName("dummy"), equalTo(false)));
    }

}
