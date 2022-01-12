# Parquet for Java 1.3.0, released 2022-01-12

Code name: Support UUID and TIMESTAMP_MICROS Logical Types

## Summary

In this release, we added support for reading `FIXED_LEN_BYTE_ARRAY (UUID)` and `INT64 (TIMESTAMP_MICROS)` logical types.

## Features

* #33: Added support for reading `UUID` logical type as Java UUID
* #38: Added support for reading `TIMESTAMP_MICROS` logical type as Java timestamp

## Dependency Updates

### Compile Dependency Updates

* Updated `org.scala-lang:scala-library:2.13.7` to `2.13.8`

### Test Dependency Updates

* Updated `org.junit.jupiter:junit-jupiter:5.8.1` to `5.8.2`
* Updated `org.mockito:mockito-core:4.1.0` to `4.2.0`
* Updated `org.mockito:mockito-junit-jupiter:4.1.0` to `4.2.0`

### Plugin Dependency Updates

* Updated `io.github.zlika:reproducible-build-maven-plugin:0.14` to `0.15`
* Updated `net.alchim31.maven:scala-maven-plugin:4.5.4` to `4.5.6`
* Updated `org.apache.maven.plugins:maven-jar-plugin:3.2.0` to `3.2.2`
* Updated `org.apache.maven.plugins:maven-site-plugin:3.9.1` to `3.10.0`
* Updated `org.itsallcode:openfasttrace-maven-plugin:1.2.0` to `1.3.0`
