# parquet-io-java

[![Build Status](https://github.com/exasol/parquet-io-java/actions/workflows/ci-build.yml/badge.svg)](https://github.com/exasol/parquet-io-java/actions/workflows/ci-build.yml)
[![Maven Central &ndash; Parquet for Java](https://img.shields.io/maven-central/v/com.exasol/parquet-io-java)](https://search.maven.org/artifact/com.exasol/parquet-io-java)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aparquet-io-java&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.exasol%3Aparquet-io-java)

[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aparquet-io-java&metric=security_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Aparquet-io-java)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aparquet-io-java&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Aparquet-io-java)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aparquet-io-java&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Aparquet-io-java)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aparquet-io-java&metric=sqale_index)](https://sonarcloud.io/dashboard?id=com.exasol%3Aparquet-io-java)

[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aparquet-io-java&metric=code_smells)](https://sonarcloud.io/dashboard?id=com.exasol%3Aparquet-io-java)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aparquet-io-java&metric=coverage)](https://sonarcloud.io/dashboard?id=com.exasol%3Aparquet-io-java)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aparquet-io-java&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=com.exasol%3Aparquet-io-java)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aparquet-io-java&metric=ncloc)](https://sonarcloud.io/dashboard?id=com.exasol%3Aparquet-io-java)

## In a nutshell

This project provides a library that reads [Parquet](https://parquet.apache.org/) files into Java objects.

```java
final Path path = new Path("/data/parquet/part-0000.parquet");
final Configuration conf = new Configuration();
try (final ParquetReader<Row> reader = RowParquetReader
        .builder(HadoopInputFile.fromPath(path, conf)).build()) {
    Row row = reader.read();
    while (row != null) {
        System.out.println(row.getValues());
        row = reader.read();
    }
}
```

For installation, usage examples, data type mapping, and the API reference, see the [User Guide](doc/user_guide.md).
## Information for Users

- [User Guide](doc/user_guide.md)
- [JavaDoc API](https://exasol.github.io/parquet-io-java/latest/api/index.html)
- [Changelog](doc/changes/changelog.md)
- [Dependencies](dependencies.md)

## Information for Developers

* [System Requirement Specification](doc/system_requirements.md)
* [Design](doc/design.md)
