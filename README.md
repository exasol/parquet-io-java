# parquet-io-java

[![Build Status](https://api.travis-ci.com/exasol/parquet-io-java.svg?branch=master)](https://travis-ci.com/exasol/parquet-io-java)
[![Maven Central](https://img.shields.io/maven-central/v/com.exasol/parquet-io-java)](https://search.maven.org/artifact/com.exasol/parquet-io-java)

SonarCloud results:

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
        <artifactId>parquetio</artifactId>
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

## Information for Users

- [Changelog](doc/changes/changelog.md)
- [Dependencies](dependencies.md)

## Information for Developers

* [System Requirement Specification](doc/system_requirements.md)
* [Design](doc/design.md)
