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
