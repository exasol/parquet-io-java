# parquet-io-java

[![Build Status](https://github.com/exasol/parquet-io-java/actions/workflows/ci-build.yml/badge.svg)](https://github.com/exasol/parquet-io-java/actions/workflows/ci-build.yml)
[![Maven Central – Parquet for Java](https://img.shields.io/maven-central/v/com.exasol/parquet-io-java)](https://search.maven.org/artifact/com.exasol/parquet-io-java)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aparquet-io-java&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.exasol%3Aparquet-io-java)

[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aparquet-io-java&metric=security_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Aparquet-io-java)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aparquet-io-java&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Aparquet-io-java)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aparquet-io-java&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Aparquet-io-java)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aparquet-io-java&metric=sqale_index)](https://sonarcloud.io/dashboard?id=com.exasol%3Aparquet-io-java)

[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aparquet-io-java&metric=code_smells)](https://sonarcloud.io/dashboard?id=com.exasol%3Aparquet-io-java)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aparquet-io-java&metric=coverage)](https://sonarcloud.io/dashboard?id=com.exasol%3Aparquet-io-java)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aparquet-io-java&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=com.exasol%3Aparquet-io-java)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aparquet-io-java&metric=ncloc)](https://sonarcloud.io/dashboard?id=com.exasol%3Aparquet-io-java)

This project provides a library that reads [Parquet](https://parquet.apache.org/) files into Java objects.

## Installation

Add this library as a dependency to your project's `pom.xml` file.

```xml
<dependencies>
    <dependency>
        <groupId>com.exasol</groupId>
        <artifactId>parquet-io-java</artifactId>
        <version>LATEST VERSION</version>
    </dependency>
</dependencies>
```

Please use the latest version of the library.

## Usage

Here is a small example code showing the usage of the library.

```java
final Path path = new Path("/data/parquet/part-0000.parquet");
final Configuration conf = new Configuration();
try (final ParquetReader<Row> reader = RowParquetReader
        .builder(HadoopInputFile.fromPath(path, conf)).build()) {
    Row row = reader.read();
    while (row != null) {
        List<Object> values = row.getValues();
        System.out.println(values);
        row = reader.read();
    }
} catch (final IOException exception) {
    //
}
```

## Data Type Mapping

The following table shows how each Parquet data type is mapped into Java data
types.

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

### Parquet Repeated Types

Parquet data type can repeat a single field or the group of fields. The
parquet-io-java (PIOJ) reads these data types into Java `List` type.

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

The PIOJ reads both of these Parquet types into Java list of `["John", "Jane"]`.

On the other hand, you can import a repeated group with multiple fields as a
list of maps.

```
message parquet_schema {
  repeated group person {
    required binary name (UTF8);
    optional int32 age;
  }
}
```

The PIOJ reads it into a list of person maps:

```
[ Map("name" -> "John", "age" -> 24), Map("name" -> "Jane", "age" -> 22) ]
```

## Information for Users

- [Changelog](doc/changes/changelog.md)
- [Dependencies](dependencies.md)

## Information for Developers

* [System Requirement Specification](doc/system_requirements.md)
* [Design](doc/design.md)
