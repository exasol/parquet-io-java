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

This project provides a library that reads [Parquet][parquet-link] files into
Java objects.

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

## Dependencies

### Run Time Dependencies

Running the `parquet-io-java` requires a Java Runtime version 11 or later.

| Dependency                                                                          | Purpose                                                | License                       |
|-------------------------------------------------------------------------------------|--------------------------------------------------------|-------------------------------|
| [Parquet Hadoop](http://hamcrest.org/JavaHamcrest/)                                 | Checking for conditions in code via matchers           | BSD License                   |
| [Hadoop Client](https://junit.org/junit5)                                           | Unit testing framework                                 | Eclipse Public License 1.0    |
| [Google Guava](https://junit.org/junit5)                                            | Unit testing framework                                 | Eclipse Public License 1.0    |
| [Scala Library](https://junit.org/junit5)                                           | Unit testing framework                                 | Eclipse Public License 1.0    |

### Test Dependencies

| Dependency                                                                          | Purpose                                                | License                       |
|-------------------------------------------------------------------------------------|--------------------------------------------------------|-------------------------------|
| [Java Hamcrest](http://hamcrest.org/JavaHamcrest/)                                  | Checking for conditions in code via matchers           | BSD License                   |
| [JUnit](https://junit.org/junit5)                                                   | Unit testing framework                                 | Eclipse Public License 1.0    |
| [Mockito](http://site.mockito.org/)                                                 | Mocking framework                                      | MIT License                   |
| [Scalatest](https://www.scalatest.org/)                                             | Testing tool for Scala and Java developers             | Apache License 2.0            |

### Build Dependencies

| Plug-in                                                                             | Purpose                                                | License                       |
|-------------------------------------------------------------------------------------|--------------------------------------------------------|-------------------------------|
| [Apache Maven](https://maven.apache.org/)                                           | Build tool                                             | Apache License 2.0            |
| [Maven Compiler Plugin](https://maven.apache.org/plugins/maven-compiler-plugin/)    | Setting required Java version                          | Apache License 2.0            |
| [Scala Maven Plugin](https://davidb.github.io/scala-maven-plugin/index.html)        | Setting for compiling Scala code using maven           | Unlicense License             |
| [Maven GPG Plugin](https://maven.apache.org/plugins/maven-gpg-plugin/)              | Code signing                                           | Apache License 2.0            |
| [Maven Enforcer Plugin][maven-enforcer-plugin]                                      | Controlling environment constants                      | Apache License 2.0            |
| [Maven Assembly Plugin][maven-assembly-plugin]                                      | Creating JAR                                           | Apache License 2.0            |
| [Maven Javadoc Plugin](https://maven.apache.org/plugins/maven-javadoc-plugin/)      | Creating a Javadoc JAR                                 | Apache License 2.0            |
| [Maven Jacoco Plugin](https://www.eclemma.org/jacoco/trunk/doc/maven.html)          | Code coverage metering                                 | Eclipse Public License 2.0    |
| [Maven Source Plugin](https://maven.apache.org/plugins/maven-source-plugin/)        | Creating a source code JAR                             | Apache License 2.0            |
| [Maven Surefire Plugin](https://maven.apache.org/surefire/maven-surefire-plugin/)   | Unit testing                                           | Apache License 2.0            |
| [Scalatest Maven Plugin](https://github.com/scalatest/scalatest-maven-plugin)       | Runs ScalaTest test through maven                      | Apache License 2.0            |
| [OpenFastTrace Maven Plugin][oft-maven-plugin]                                      | Requirement Tracing                                    | GPL v3                        |
| [Sonatype OSS Index Maven Plugin][sonatype-oss-index-maven-plugin]                  | Checking Dependencies Vulnerability                    | ASL2                          |
| [Versions Maven Plugin][versions-maven-plugin]                                      | Checking if dependencies updates are available         | Apache License 2.0            |
| [Reproducible Build Maven Plugin][reproducible-maven-plugin]                        | Reproducible maven builds                              | Apache License 2.0            |
| [Project Keeper Maven Plugin][project-keeper-maven-plugin]                          | Checking project structure                             | MIT License                   |

[parquet-link]: https://parquet.apache.org/
[maven-enforcer-plugin]: http://maven.apache.org/enforcer/maven-enforcer-plugin/
[maven-assembly-plugin]: https://maven.apache.org/plugins/maven-assembly-plugin/
[oft-maven-plugin]: https://github.com/itsallcode/openfasttrace-maven-plugin
[sonatype-oss-index-maven-plugin]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[versions-maven-plugin]: https://www.mojohaus.org/versions-maven-plugin/
[reproducible-maven-plugin]: https://zlika.github.io/reproducible-build-maven-plugin/
[project-keeper-maven-plugin]: https://github.com/exasol/project-keeper-maven-plugin
