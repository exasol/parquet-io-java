package com.exasol.parquetio;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.parquet.example.data.Group;
import org.apache.parquet.example.data.simple.SimpleGroup;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.schema.MessageType;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;

import com.exasol.parquetio.data.GenericRow;
import com.exasol.parquetio.data.Row;

// [utest->dsn~converting-nested-column-types~1]
class RowParquetReaderComplexTypesTest extends BaseParquetReaderTest {
    @Test
    void testReadsListOfStrings() throws IOException {
        final MessageType schema = parseSchema("message test {", "  optional group names (LIST) {",
                "    repeated group list {", "      required binary name (UTF8);", "    }", "  }", "}");
        try (ParquetWriter<Group> writer = getParquetWriter(schema, true)) {
            final SimpleGroup parquetRow = new SimpleGroup(schema);
            final Group names = parquetRow.addGroup(0);
            names.addGroup(0).append("name", "John");
            names.addGroup(0).append("name", "Jane");
            writer.write(parquetRow);
        }
        assertThat(getRecords(), containsRow(List.of("John", "Jane")));
    }

    @Test
    void testReadsListOfInts() throws IOException {
        final MessageType schema = parseSchema("message test {", "  optional group ages (LIST) {",
                "    repeated group list {", "      required int32 age;", "    }", "  }", "}");
        try (ParquetWriter<Group> writer = getParquetWriter(schema, true)) {
            final SimpleGroup parquetRow = new SimpleGroup(schema);
            final Group ages = parquetRow.addGroup(0);
            ages.addGroup(0).append("age", 3);
            ages.addGroup(0).append("age", 4);
            writer.write(parquetRow);
        }
        assertThat(getRecords(), containsRow(List.of(3, 4)));
    }

    @Test
    void testReadsListOfDoubles() throws IOException {
        final MessageType schema = parseSchema("message test {", "  optional group prices (LIST) {",
                "    repeated group list {", "      required double price;", "    }", "  }", "}");
        try (ParquetWriter<Group> writer = getParquetWriter(schema, true)) {
            final SimpleGroup parquetRow = new SimpleGroup(schema);
            final Group prices = parquetRow.addGroup(0);
            prices.addGroup(0).append("price", 3.14);
            prices.addGroup(0).append("price", 2.71);
            writer.write(parquetRow);
        }
        assertThat(getRecords(), containsRow(List.of(3.14, 2.71)));
    }

    @Test
    void testReadsNonStandardRepeatedInt32List() throws IOException {
        final MessageType schema = parseSchema("message test {", "  optional group heights (LIST) {",
                "    repeated int32 height;", "  }", "}");
        try (ParquetWriter<Group> writer = getParquetWriter(schema, true)) {
            final SimpleGroup parquetRow = new SimpleGroup(schema);
            final Group heights = parquetRow.addGroup(0);
            heights.append("height", 314);
            heights.append("height", 271);
            writer.write(parquetRow);
        }
        assertThat(getRecords(), containsRow(List.of(314, 271)));
    }

    @Test
    void testReadsRepeatedFieldAsList() throws IOException {
        final MessageType schema = parseSchema("message test {", "  repeated binary name (UTF8);", "}");
        try (ParquetWriter<Group> writer = getParquetWriter(schema, true)) {
            final SimpleGroup parquetRow = new SimpleGroup(schema);
            parquetRow.add(0, "John");
            parquetRow.add(0, "Jane");
            writer.write(parquetRow);
        }
        assertThat(getRecords(), containsRow(List.of("John", "Jane")));
    }

    @Test
    void testReadsRepeatedGroupWithSingleElementAsList() throws IOException {
        final MessageType schema = parseSchema("message test {", "  repeated group person {",
                "    required binary name (UTF8);", "  }", "}");
        try (ParquetWriter<Group> writer = getParquetWriter(schema, true)) {
            final SimpleGroup parquetRow = new SimpleGroup(schema);
            Group person = parquetRow.addGroup(0);
            person.append("name", "John");
            person = parquetRow.addGroup(0);
            person.append("name", "Jane");
            writer.write(parquetRow);
        }
        assertThat(getRecords(), containsRow(List.of("John", "Jane")));
    }

    @Test
    void testReadsRepeatedGroupWithMultipleElementsAsListOfMaps() throws IOException {
        final MessageType schema = parseSchema("message test {", "  repeated group person {",
                "    required binary name (UTF8);", "    optional int32 age;", "  }", "}");
        try (ParquetWriter<Group> writer = getParquetWriter(schema, true)) {
            final SimpleGroup parquetRow = new SimpleGroup(schema);
            Group person = parquetRow.addGroup(0);
            person.append("name", "John").append("age", 24);
            person = parquetRow.addGroup(0);
            person.append("name", "Jane");
            writer.write(parquetRow);
        }
        final List<Map<String, Object>> expectedValues = List.of(Map.of("name", "John", "age", 24),
                nullableMap("name", "Jane", "age", null));
        assertThat(getRecords(), containsRow(expectedValues));
    }

    @Test
    void testReadsListOfLists() throws IOException {
        final MessageType schema = parseSchema("message test {", "  optional group arrays (LIST) {",
                "    repeated group list {", "      required group inner (LIST) {",
                "        repeated group list {", "          required int32 element;", "        }", "      }", "    }",
                "  }", "}");
        try (ParquetWriter<Group> writer = getParquetWriter(schema, true)) {
            final SimpleGroup parquetRow = new SimpleGroup(schema);
            final Group arrays = parquetRow.addGroup(0).addGroup(0);
            Group inner = arrays.addGroup("inner");
            inner.addGroup(0).append("element", 1);
            inner.addGroup(0).append("element", 2);
            inner = arrays.addGroup("inner");
            inner.addGroup(0).append("element", 3);
            writer.write(parquetRow);
        }
        assertThat(getRecords(), containsRow(List.of(List.of(1, 2), Collections.singletonList(3))));
    }

    @Test
    void testReadsListOfMaps() throws IOException {
        final MessageType schema = parseSchema("message test {", "  optional group maps (LIST) {",
                "    repeated group list {", "      optional group map (MAP) {",
                "        repeated group key_value {", "          required binary key (UTF8);",
                "          optional double price;", "        }", "      }", "    }", "  }", "}");
        try (ParquetWriter<Group> writer = getParquetWriter(schema, true)) {
            final SimpleGroup parquetRow = new SimpleGroup(schema);
            final Group array = parquetRow.addGroup(0).addGroup(0);
            Group map = array.addGroup("map");
            map.addGroup("key_value").append("key", "key1").append("price", 3.14);
            map.addGroup("key_value").append("key", "key2").append("price", 2.71);
            map.addGroup("key_value").append("key", "key-without-price");
            map = array.addGroup("map");
            map.addGroup("key_value").append("key", "a").append("price", 100.0);
            writer.write(parquetRow);
        }
        final List<Map<String, Object>> expectedValues = List.of(
                nullableMap("key1", 3.14, "key2", 2.71, "key-without-price", null), Map.of("a", 100.0));
        assertThat(getRecords(), containsRow(expectedValues));
    }

    @Test
    void testReadsMap() throws IOException {
        final MessageType schema = parseSchema("message test {", "  optional group map (MAP) {",
                "    repeated group key_value {", "      required binary key (UTF8);", "      required int64 value;",
                "    }", "  }", "}");
        try (ParquetWriter<Group> writer = getParquetWriter(schema, true)) {
            final SimpleGroup parquetRow = new SimpleGroup(schema);
            final Group map = parquetRow.addGroup(0);
            map.addGroup("key_value").append("key", "key1").append("value", 314L);
            map.addGroup("key_value").append("key", "key2").append("value", 271L);
            writer.write(parquetRow);
        }
        assertThat(getRecords(), containsRow(Map.of("key1", 314L, "key2", 271L)));
    }

    @Test
    void testReadsMapWithArrayValues() throws IOException {
        final MessageType schema = parseSchema("message test {", "  optional group map (MAP) {",
                "    repeated group key_value {", "      required binary key (UTF8);",
                "      optional group prices (LIST) {", "        repeated group list {",
                "          required double price;", "        }", "      }", "    }", "  }", "}");
        try (ParquetWriter<Group> writer = getParquetWriter(schema, true)) {
            final SimpleGroup parquetRow = new SimpleGroup(schema);
            final Group mapGroup = parquetRow.addGroup(0);
            Group map = mapGroup.addGroup("key_value");
            final Group prices = map.append("key", "key1").addGroup("prices");
            prices.addGroup(0).append("price", 3.14);
            prices.addGroup(0).append("price", 2.71);
            map = mapGroup.addGroup("key_value");
            map.append("key", "key2");
            writer.write(parquetRow);
        }
        assertThat(getRecords(), containsRow(nullableMap("key1", List.of(3.14, 2.71), "key2", null)));
    }

    @Test
    void testReadsMapWithGroupValues() throws IOException {
        final MessageType schema = parseSchema("message test {", "  optional group maps (MAP) {",
                "    repeated group key_value {", "      required binary name (UTF8);",
                "      required group values {", "        optional int32 height;", "        optional int32 weight;",
                "      }", "    }", "  }", "}");
        try (ParquetWriter<Group> writer = getParquetWriter(schema, true)) {
            final SimpleGroup parquetRow = new SimpleGroup(schema);
            final Group maps = parquetRow.addGroup(0);
            Group map = maps.addGroup("key_value");
            map.append("name", "John").addGroup("values").append("height", 170).append("weight", 70);
            map = maps.addGroup("key_value");
            map.append("name", "Jane").addGroup("values").append("height", 160);
            writer.write(parquetRow);
        }
        final Map<String, Object> expectedValues = Map.of("John", Map.of("height", 170, "weight", 70), "Jane",
                nullableMap("height", 160, "weight", null));
        assertThat(getRecords(), containsRow(expectedValues));
    }

    @Test
    void testReadsListOfRepeatedGroup() throws IOException {
        final MessageType schema = parseSchema("message test {", "  optional group array (LIST) {",
                "    repeated group list {", "      repeated group values {", "        required binary key (UTF8);",
                "        optional double price;", "      }", "    }", "  }", "}");
        try (ParquetWriter<Group> writer = getParquetWriter(schema, true)) {
            final SimpleGroup parquetRow = new SimpleGroup(schema);
            final Group array = parquetRow.addGroup(0).addGroup(0);
            array.addGroup("values").append("key", "key1").append("price", 3.14);
            array.addGroup("values").append("key", "key2").append("price", 2.71);
            array.addGroup("values").append("key", "key-without-price");
            writer.write(parquetRow);
        }
        final List<Map<String, Object>> repeatedValues = List.of(Map.of("key", "key1", "price", 3.14),
                Map.of("key", "key2", "price", 2.71), nullableMap("key", "key-without-price", "price", null));
        assertThat(getRecords(), containsRow(Collections.singletonList(repeatedValues)));
    }

    @Test
    void testReadsMapWithRepeatedGroupValues() throws IOException {
        final MessageType schema = parseSchema("message test {", "  optional group maps (MAP) {",
                "    repeated group key_value {", "      required binary name (UTF8);",
                "      repeated group values {", "        required int32 year;", "        optional int32 height;",
                "        optional int32 weight;", "      }", "    }", "  }", "}");
        try (ParquetWriter<Group> writer = getParquetWriter(schema, true)) {
            final SimpleGroup parquetRow = new SimpleGroup(schema);
            final Group maps = parquetRow.addGroup(0);
            Group map = maps.addGroup("key_value");
            map.append("name", "John");
            map.addGroup(1).append("year", 2019).append("height", 170).append("weight", 70);
            map.addGroup(1).append("year", 2020).append("height", 170).append("weight", 80);

            map = maps.addGroup("key_value");
            map.append("name", "Jane");
            map.addGroup(1).append("year", 2019).append("height", 160);
            map.addGroup(1).append("year", 2020);
            writer.write(parquetRow);
        }
        final List<Map<String, Object>> janeValues = List.of(nullableMap("year", 2019, "height", 160, "weight", null),
                nullableMap("year", 2020, "height", null, "weight", null));
        final Map<String, Object> expectedValues = Map.of("John",
                List.of(Map.of("year", 2019, "height", 170, "weight", 70),
                        Map.of("year", 2020, "height", 170, "weight", 80)),
                "Jane", janeValues);
        assertThat(getRecords(), containsRow(expectedValues));
    }

    @Test
    void testReadsGroup() throws IOException {
        final MessageType schema = parseSchema("message test {", "  required binary name (UTF8);",
                "  optional group contacts {", "    required binary name (UTF8);",
                "    optional binary phoneNumber (UTF8);", "  }", "}");
        try (ParquetWriter<Group> writer = getParquetWriter(schema, false)) {
            final SimpleGroup firstRow = new SimpleGroup(schema);
            firstRow.add(0, "John");
            final Group contacts = firstRow.addGroup(1);
            contacts.append("name", "Jane").append("phoneNumber", "1337");
            writer.write(firstRow);

            final SimpleGroup secondRow = new SimpleGroup(schema);
            secondRow.add(0, "Jake");
            secondRow.addGroup(1).append("name", "Jill");
            writer.write(secondRow);
        }
        assertThat(getRecords(), contains(row("John", Map.of("name", "Jane", "phoneNumber", "1337")),
                row("Jake", nullableMap("name", "Jill", "phoneNumber", null))));
    }

    @Test
    void testReadsGroupWithRepeatedGroup() throws IOException {
        final MessageType schema = parseSchema("message test {", "  required binary name (UTF8);",
                "  optional group contacts {", "    repeated group person {", "      required binary name (UTF8);",
                "      optional binary phoneNumber (UTF8);", "    }", "    optional int32 count;", "  }", "}");
        try (ParquetWriter<Group> writer = getParquetWriter(schema, false)) {
            final SimpleGroup firstRow = new SimpleGroup(schema);
            firstRow.add(0, "John");
            final Group contacts = firstRow.addGroup(1);
            contacts.addGroup(0).append("name", "Jane").append("phoneNumber", "1337");
            contacts.addGroup(0).append("name", "Jake");
            contacts.append("count", 2);
            writer.write(firstRow);

            final SimpleGroup secondRow = new SimpleGroup(schema);
            secondRow.add(0, "Jill");
            writer.write(secondRow);
        }
        final Map<String, Object> columnValues = Map.of("person",
                List.of(Map.of("name", "Jane", "phoneNumber", "1337"),
                        nullableMap("name", "Jake", "phoneNumber", null)),
                "count", 2);
        assertThat(getRecords(), contains(row("John", columnValues), row("Jill", null)));
    }

    @Test
    void testReadsNestedGroups() throws IOException {
        final MessageType schema = parseSchema("message test {", "  required binary name (UTF8);",
                "  optional group contacts (MAP) {", "    repeated group key_value {",
                "      required binary name (UTF8);", "      optional group numbers (LIST) {",
                "        repeated group list {", "          optional binary phoneNumber (UTF8);", "        }", "      }",
                "    }", "  }", "}");
        try (ParquetWriter<Group> writer = getParquetWriter(schema, false)) {
            final SimpleGroup parquetRow = new SimpleGroup(schema);
            parquetRow.add(0, "John");
            final Group contacts = parquetRow.addGroup(1);
            Group phoneNumbers = contacts.addGroup(0).append("name", "Jane").addGroup("numbers");
            phoneNumbers.addGroup(0).append("phoneNumber", "1337");
            phoneNumbers = contacts.addGroup(0).append("name", "Jake").addGroup("numbers");
            phoneNumbers.addGroup(0);
            writer.write(parquetRow);
        }
        assertThat(getRecords(), containsRow("John",
                nullableMap("Jane", Collections.singletonList("1337"), "Jake", Collections.emptyList())));
    }

    private static Matcher<Iterable<? extends Row>> containsRow(final Object... values) {
        return contains(row(values));
    }

    private static Row row(final Object... values) {
        return GenericRow.of(values);
    }

    private static Map<String, Object> nullableMap(final Object... keyValuePairs) {
        final Map<String, Object> map = new LinkedHashMap<>();
        for (int index = 0; index < keyValuePairs.length; index += 2) {
            map.put((String) keyValuePairs[index], keyValuePairs[index + 1]);
        }
        return map;
    }
}
