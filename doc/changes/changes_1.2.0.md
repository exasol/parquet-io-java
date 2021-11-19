# Parquet for Java 1.2.0, released 2021-11-22

Code name: Iterator for RowParquetChunkReader

## Summary

In this release we added an iterator interface to `RowParquetChunkReader`. That allows you to also pull the rows instead of the previous push implementation.

## Features

* #35: Added iterator interface to RowParquetChunkReader

## Documentation

* #32: Added Parquet to Java data types mapping table

## Dependency Updates

### Compile Dependency Updates

* Updated `com.exasol:error-reporting-java:0.4.0` to `0.4.1`
* Updated `com.google.guava:guava:30.1.1-jre` to `31.0.1-jre`
* Updated `org.apache.parquet:parquet-hadoop:1.12.1` to `1.12.2`
* Updated `org.scala-lang:scala-library:2.12.14` to `2.12.15`

### Test Dependency Updates

* Updated `org.junit.jupiter:junit-jupiter:5.8.0` to `5.8.1`
* Updated `org.mockito:mockito-core:3.12.4` to `4.0.0`
* Updated `org.mockito:mockito-junit-jupiter:3.12.4` to `4.0.0`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:1.2.0` to `1.3.1`
* Updated `net.alchim31.maven:scala-maven-plugin:4.5.3` to `4.5.4`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.0.0-M3` to `3.0.0`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.3.0` to `3.3.1`
