package com.exasol.parquetio;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import java.io.IOException;
import java.util.*;

import org.apache.parquet.example.data.simple.SimpleGroup;
import org.junit.jupiter.api.Test;

import com.exasol.parquetio.data.GenericRow;

// [utest->dsn~converting-nested-column-types~1]
class RowParquetReaderComplexTypesTest extends BaseParquetReaderTest {
    @Test
    void readsListOfStrings() throws IOException {
        final var schema = parseSchema("message test {", "  optional group names (LIST) {",
                "    repeated group list {", "      required binary name (UTF8);", "    }", "  }", "}");
        try (var writer = getParquetWriter(schema, true)) {
            final var parquetRow = new SimpleGroup(schema);
            final var names = parquetRow.addGroup(0);
            names.addGroup(0).append("name", "John");
            names.addGroup(0).append("name", "Jane");
            writer.write(parquetRow);
        }
        assertThat(getRecords(), contains(GenericRow.of(List.of("John", "Jane"))));
    }

    @Test
    void readsListOfInts() throws IOException {
        final var schema = parseSchema("message test {", "  optional group ages (LIST) {",
                "    repeated group list {", "      required int32 age;", "    }", "  }", "}");
        try (var writer = getParquetWriter(schema, true)) {
            final var parquetRow = new SimpleGroup(schema);
            final var ages = parquetRow.addGroup(0);
            ages.addGroup(0).append("age", 3);
            ages.addGroup(0).append("age", 4);
            writer.write(parquetRow);
        }
        assertThat(getRecords(), contains(GenericRow.of(List.of(3, 4))));
    }

    @Test
    void readsListOfDoubles() throws IOException {
        final var schema = parseSchema("message test {", "  optional group prices (LIST) {",
                "    repeated group list {", "      required double price;", "    }", "  }", "}");
        try (var writer = getParquetWriter(schema, true)) {
            final var parquetRow = new SimpleGroup(schema);
            final var prices = parquetRow.addGroup(0);
            prices.addGroup(0).append("price", 3.14);
            prices.addGroup(0).append("price", 2.71);
            writer.write(parquetRow);
        }
        assertThat(getRecords(), contains(GenericRow.of(List.of(3.14, 2.71))));
    }

    @Test
    void readsNonStandardList() throws IOException {
        final var schema = parseSchema("message test {", "  optional group heights (LIST) {",
                "    repeated int32 height;", "  }", "}");
        try (var writer = getParquetWriter(schema, true)) {
            final var parquetRow = new SimpleGroup(schema);
            final var heights = parquetRow.addGroup(0);
            heights.append("height", 314);
            heights.append("height", 271);
            writer.write(parquetRow);
        }
        assertThat(getRecords(), contains(GenericRow.of(List.of(314, 271))));
    }

    @Test
    void readsRepeatedFieldAsList() throws IOException {
        final var schema = parseSchema("message test {", "  repeated binary name (UTF8);", "}");
        try (var writer = getParquetWriter(schema, true)) {
            final var parquetRow = new SimpleGroup(schema);
            parquetRow.add(0, "John");
            parquetRow.add(0, "Jane");
            writer.write(parquetRow);
        }
        assertThat(getRecords(), contains(GenericRow.of(List.of("John", "Jane"))));
    }

    @Test
    void readsRepeatedGroupWithSingleElementAsList() throws IOException {
        final var schema = parseSchema("message test {", "  repeated group person {",
                "    required binary name (UTF8);", "  }", "}");
        try (var writer = getParquetWriter(schema, true)) {
            final var parquetRow = new SimpleGroup(schema);
            var person = parquetRow.addGroup(0);
            person.append("name", "John");
            person = parquetRow.addGroup(0);
            person.append("name", "Jane");
            writer.write(parquetRow);
        }
        assertThat(getRecords(), contains(GenericRow.of(List.of("John", "Jane"))));
    }

    @Test
    void readsRepeatedGroupWithMultipleElementsAsListOfMaps() throws IOException {
        final var schema = parseSchema("message test {", "  repeated group person {",
                "    required binary name (UTF8);", "    optional int32 age;", "  }", "}");
        try (var writer = getParquetWriter(schema, true)) {
            final var parquetRow = new SimpleGroup(schema);
            var person = parquetRow.addGroup(0);
            person.append("name", "John").append("age", 24);
            person = parquetRow.addGroup(0);
            person.append("name", "Jane").append("age", 22);
            writer.write(parquetRow);
        }
        final var expectedValues = List.of(Map.of("name", "John", "age", 24), Map.of("name", "Jane", "age", 22));
        assertThat(getRecords(), contains(GenericRow.of(expectedValues)));
    }

    @Test
    void readsListOfLists() throws IOException {
        final var schema = parseSchema("message test {", "  optional group arrays (LIST) {",
                "    repeated group list {", "      required group inner (LIST) {",
                "        repeated group list {", "          required int32 element;", "        }", "      }", "    }",
                "  }", "}");
        try (var writer = getParquetWriter(schema, true)) {
            final var parquetRow = new SimpleGroup(schema);
            final var arrays = parquetRow.addGroup(0).addGroup(0);
            var inner = arrays.addGroup("inner");
            inner.addGroup(0).append("element", 1);
            inner.addGroup(0).append("element", 2);
            inner = arrays.addGroup("inner");
            inner.addGroup(0).append("element", 3);
            writer.write(parquetRow);
        }
        assertThat(getRecords(), contains(GenericRow.of(List.of(List.of(1, 2), Collections.singletonList(3)))));
    }

    @Test
    void readsListOfMaps() throws IOException {
        final var schema = parseSchema("message test {", "  optional group maps (LIST) {",
                "    repeated group list {", "      optional group map (MAP) {",
                "        repeated group key_value {", "          required binary key (UTF8);",
                "          optional double price;", "        }", "      }", "    }", "  }", "}");
        try (var writer = getParquetWriter(schema, true)) {
            final var parquetRow = new SimpleGroup(schema);
            final var array = parquetRow.addGroup(0).addGroup(0);
            var map = array.addGroup("map");
            map.addGroup("key_value").append("key", "key1").append("price", 3.14);
            map.addGroup("key_value").append("key", "key2").append("price", 2.71);
            map = array.addGroup("map");
            map.addGroup("key_value").append("key", "a").append("price", 100.0);
            writer.write(parquetRow);
        }
        assertThat(getRecords(),
                contains(GenericRow.of(List.of(Map.of("key1", 3.14, "key2", 2.71), Map.of("a", 100.0)))));
    }

    @Test
    void readsMap() throws IOException {
        final var schema = parseSchema("message test {", "  optional group map (MAP) {",
                "    repeated group key_value {", "      required binary key (UTF8);", "      required int64 value;",
                "    }", "  }", "}");
        try (var writer = getParquetWriter(schema, true)) {
            final var parquetRow = new SimpleGroup(schema);
            final var map = parquetRow.addGroup(0);
            map.addGroup("key_value").append("key", "key1").append("value", 314L);
            map.addGroup("key_value").append("key", "key2").append("value", 271L);
            writer.write(parquetRow);
        }
        assertThat(getRecords(), contains(GenericRow.of(Map.of("key1", 314L, "key2", 271L))));
    }

    @Test
    void readsMapWithArrayValues() throws IOException {
        final var schema = parseSchema("message test {", "  optional group map (MAP) {",
                "    repeated group key_value {", "      required binary key (UTF8);",
                "      optional group prices (LIST) {", "        repeated group list {",
                "          required double price;", "        }", "      }", "    }", "  }", "}");
        try (var writer = getParquetWriter(schema, true)) {
            final var parquetRow = new SimpleGroup(schema);
            final var map = parquetRow.addGroup(0).addGroup("key_value");
            final var prices = map.append("key", "key1").addGroup("prices");
            prices.addGroup(0).append("price", 3.14);
            prices.addGroup(0).append("price", 2.71);
            writer.write(parquetRow);
        }
        assertThat(getRecords(), contains(GenericRow.of(Map.of("key1", List.of(3.14, 2.71)))));
    }

    @Test
    void readsMapWithGroupValues() throws IOException {
        final var schema = parseSchema("message test {", "  optional group maps (MAP) {",
                "    repeated group key_value {", "      required binary name (UTF8);",
                "      required group values {", "        optional int32 height;", "        optional int32 weight;",
                "      }", "    }", "  }", "}");
        try (var writer = getParquetWriter(schema, true)) {
            final var parquetRow = new SimpleGroup(schema);
            final var maps = parquetRow.addGroup(0);
            var map = maps.addGroup("key_value");
            map.append("name", "John").addGroup("values").append("height", 170).append("weight", 70);
            map = maps.addGroup("key_value");
            map.append("name", "Jane").addGroup("values").append("height", 160).append("weight", 60);
            writer.write(parquetRow);
        }
        final var expectedValues = Map.of("John", Map.of("height", 170, "weight", 70), "Jane",
                Map.of("height", 160, "weight", 60));
        assertThat(getRecords(), contains(GenericRow.of(expectedValues)));
    }

    @Test
    void readsListOfRepeatedGroup() throws IOException {
        final var schema = parseSchema("message test {", "  optional group array (LIST) {",
                "    repeated group list {", "      repeated group values {", "        required binary key (UTF8);",
                "        optional double price;", "      }", "    }", "  }", "}");
        try (var writer = getParquetWriter(schema, true)) {
            final var parquetRow = new SimpleGroup(schema);
            final var array = parquetRow.addGroup(0).addGroup(0);
            array.addGroup("values").append("key", "key1").append("price", 3.14);
            array.addGroup("values").append("key", "key2").append("price", 2.71);
            array.addGroup("values").append("key", "a").append("price", 100.0);
            writer.write(parquetRow);
        }
        final var expectedValues = Collections.singletonList(List.of(Map.of("key", "key1", "price", 3.14),
                Map.of("key", "key2", "price", 2.71), Map.of("key", "a", "price", 100.0)));
        assertThat(getRecords(), contains(GenericRow.of(expectedValues)));
    }

    @Test
    void readsMapWithRepeatedGroupValues() throws IOException {
        final var schema = parseSchema("message test {", "  optional group maps (MAP) {",
                "    repeated group key_value {", "      required binary name (UTF8);",
                "      repeated group values {", "        required int32 year;", "        optional int32 height;",
                "        optional int32 weight;", "      }", "    }", "  }", "}");
        try (var writer = getParquetWriter(schema, true)) {
            final var parquetRow = new SimpleGroup(schema);
            final var maps = parquetRow.addGroup(0);
            var map = maps.addGroup("key_value");
            map.append("name", "John");
            map.addGroup(1).append("year", 2019).append("height", 170).append("weight", 70);
            map.addGroup(1).append("year", 2020).append("height", 170).append("weight", 80);

            map = maps.addGroup("key_value");
            map.append("name", "Jane");
            map.addGroup(1).append("year", 2019).append("height", 160);
            writer.write(parquetRow);
        }
        final Map<String, Integer> nullableMap = new HashMap<>();
        nullableMap.put("year", 2019);
        nullableMap.put("height", 160);
        nullableMap.put("weight", null);
        final var expectedValues = Map.of("John",
                List.of(Map.of("year", 2019, "height", 170, "weight", 70),
                        Map.of("year", 2020, "height", 170, "weight", 80)),
                "Jane", Collections.singletonList(nullableMap));
        assertThat(getRecords(), contains(GenericRow.of(expectedValues)));
    }

    @Test
    void readsGroup() throws IOException {
        final var schema = parseSchema("message test {", "  required binary name (UTF8);",
                "  optional group contacts {", "    required binary name (UTF8);",
                "    optional binary phoneNumber (UTF8);", "  }", "}");
        try (var writer = getParquetWriter(schema, false)) {
            final var parquetRow = new SimpleGroup(schema);
            parquetRow.add(0, "John");
            final var contacts = parquetRow.addGroup(1);
            contacts.append("name", "Jane").append("phoneNumber", "1337");
            writer.write(parquetRow);
        }
        assertThat(getRecords(),
                contains(GenericRow.of("John", Map.of("name", "Jane", "phoneNumber", "1337"))));
    }

    @Test
    void readsGroupWithRepeatedGroup() throws IOException {
        final var schema = parseSchema("message test {", "  required binary name (UTF8);",
                "  optional group contacts {", "    repeated group person {", "      required binary name (UTF8);",
                "      optional binary phoneNumber (UTF8);", "    }", "    optional int32 count;", "  }", "}");
        try (var writer = getParquetWriter(schema, false)) {
            final var parquetRow = new SimpleGroup(schema);
            parquetRow.add(0, "John");
            final var contacts = parquetRow.addGroup(1);
            contacts.addGroup(0).append("name", "Jane").append("phoneNumber", "1337");
            contacts.addGroup(0).append("name", "Jake");
            contacts.append("count", 2);
            writer.write(parquetRow);
        }
        final Map<String, String> nullableMap = new HashMap<>();
        nullableMap.put("name", "Jake");
        nullableMap.put("phoneNumber", null);
        final var columnValues = Map.of("person", List.of(Map.of("name", "Jane", "phoneNumber", "1337"), nullableMap),
                "count", 2);
        assertThat(getRecords(), contains(GenericRow.of("John", columnValues)));
    }

    @Test
    void readsNestedGroups() throws IOException {
        final var schema = parseSchema("message test {", "  required binary name (UTF8);",
                "  optional group contacts (MAP) {", "    repeated group key_value {",
                "      required binary name (UTF8);", "      optional group numbers (LIST) {",
                "        repeated group list {", "          optional binary phoneNumber (UTF8);", "        }", "      }",
                "    }", "  }", "}");
        try (var writer = getParquetWriter(schema, false)) {
            final var parquetRow = new SimpleGroup(schema);
            parquetRow.add(0, "John");
            final var contacts = parquetRow.addGroup(1);
            final var phoneNumbers = contacts.addGroup(0).append("name", "Jane").addGroup("numbers");
            phoneNumbers.addGroup(0).append("phoneNumber", "1337");
            writer.write(parquetRow);
        }
        assertThat(getRecords(), contains(GenericRow.of("John", Map.of("Jane", Collections.singletonList("1337")))));
    }
}
