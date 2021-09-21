# Parquet for Java 1.1.0, released 2021-09-21

Code name: New Chunk Based Reader

## Summary

This release brings new Parquet reader that can read chunks of a file containing several row groups.

## Features

* #28: Added chunked Parquet reader

## Refactoring

* #26: Replaced foreach iterations with loops
* #30: Added sorting and merging of overlapping chunks to be safe.

## Dependency Updates

### Compile Dependency Updates

* Added `com.exasol:error-reporting-java:0.4.0`
* Updated `org.apache.parquet:parquet-hadoop:1.12.0` to `1.12.1`

### Test Dependency Updates

* Updated `org.junit.jupiter:junit-jupiter:5.7.2` to `5.8.0`
* Updated `org.mockito:mockito-core:3.11.2` to `3.12.4`
* Updated `org.mockito:mockito-junit-jupiter:3.11.2` to `3.12.4`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:0.10.0` to `1.2.0`
