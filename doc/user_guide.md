# User Guide

This guide is for developers integrating Parquet IO Java into their applications.

## Get The Library From Maven Central

Add Parquet IO Java to your Maven dependencies.

```xml
<dependency>
    <groupId>com.exasol</groupId>
    <artifactId>parquet-io-java</artifactId>
    <version>LATEST VERSION</version>
</dependency>
```

For the newest release, check [Maven Central](https://search.maven.org/artifact/com.exasol/parquet-io-java).

## API Documentation

The JavaDoc API documentation is published [here](https://exasol.github.io/parquet-io-java/latest/api/index.html).

## Basic Usage Examples

### Read All Rows From A Parquet File

```java
final Path path = new Path("/data/parquet/part-0000.parquet");
final Configuration conf = new Configuration();
try (final ParquetReader<Row> reader = RowParquetReader
        .builder(HadoopInputFile.fromPath(path, conf)).build()) {
    Row row = reader.read();
    while (row != null) {
        final List<Object> values = row.getValues();
        System.out.println(values);
        row = reader.read();
    }
}
```

### Read A Single Column By Name

```java
try (final ParquetReader<Row> reader = RowParquetReader
        .builder(HadoopInputFile.fromPath(path, conf)).build()) {
    Row row = reader.read();
    while (row != null) {
        final Object customerId = row.getValue("customer_id");
        System.out.println(customerId);
        row = reader.read();
    }
}
```

### Read Only Selected Row Groups

```java
final List<ChunkInterval> intervals = List.of(new ChunkIntervalImpl(0, 2));
try (final RowParquetChunkReader reader = RowParquetChunkReader
        .builder(HadoopInputFile.fromPath(path, conf), intervals).build()) {
    Row row = reader.read();
    while (row != null) {
        System.out.println(row.getValues());
        row = reader.read();
    }
}
```

## Data Type Mapping

The following table shows how each Parquet data type is mapped into Java data types.

| Parquet Data Type    | Parquet Logical Type | Java Data Type |
|:---------------------|:---------------------|:---------------|
| boolean              |                      | Boolean        |
| int32                |                      | Integer        |
| int32                | date                 | Date           |
| int32                | decimal(p, s)        | BigDecimal     |
| int64                |                      | Long           |
| int64                | timestamp_millis     | Timestamp      |
| int64                | timestamp_micros     | Timestamp      |
| int64                | decimal(p, s)        | BigDecimal     |
| float                |                      | Float          |
| double               |                      | Double         |
| binary               |                      | String         |
| binary               | utf8                 | String         |
| binary               | decimal(p, s)        | BigDecimal     |
| fixed_len_byte_array |                      | String         |
| fixed_len_byte_array | decimal(p, s)        | BigDecimal     |
| fixed_len_byte_array | uuid                 | UUID           |
| int96                |                      | Timestamp      |
| group                |                      | Map            |
| group                | LIST                 | List           |
| group                | MAP                  | Map            |
| group                | REPEATED             | List           |

## Parquet Repeated Types

Parquet data types can repeat a single field or a group of fields. Parquet IO Java reads these repeated types into Java `List` types.

For example, given the following Parquet schemas:

```
message parquet_schema {
  repeated binary name (UTF8);
}
```

```
message parquet_schema {
  repeated group person {
    required binary name (UTF8);
  }
}
```

Parquet IO Java reads both of these Parquet types into a Java list such as `["John", "Jane"]`.

On the other hand, you can import a repeated group with multiple fields as a list of maps.

```
message parquet_schema {
  repeated group person {
    required binary name (UTF8);
    optional int32 age;
  }
}
```

Parquet IO Java reads it into a list of person maps:

```
[ Map("name" -> "John", "age" -> 24), Map("name" -> "Jane", "age" -> 22) ]
```
