# Parquet for Java 2.1.0, released 2026-08-03

Code name: Nanosecond Support for TIMESTAMP type

## Summary

This release adds support for reading timestamps with nanosecond precision.

## Features

* #95: Added support for reading Parquet `TIMESTAMP(NANOS, ...)` values as Java timestamps.

## Dependency Updates

### Compile Dependency Updates

* Updated `org.apache.parquet:parquet-hadoop:1.17.0` to `1.17.1`
* Removed `org.slf4j:slf4j-api:1.7.36`

### Runtime Dependency Updates

* Updated `org.slf4j:jcl-over-slf4j:1.7.36` to `2.0.18`

### Test Dependency Updates

* Updated `org.slf4j:slf4j-jdk14:1.7.36` to `2.0.18`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:2.0.7` to `2.1.0`
* Updated `com.exasol:project-keeper-maven-plugin:5.6.2` to `5.7.4`
* Removed `com.exasol:quality-summarizer-maven-plugin:0.2.1`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.6.2` to `3.6.3`
* Updated `org.apache.maven.plugins:maven-site-plugin:3.21.0` to `3.22.0`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.5.5` to `3.5.6`
* Added `org.codehaus.mojo:build-helper-maven-plugin:3.6.1`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.14` to `0.8.15`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:5.5.0.6356` to `5.7.0.6970`
* Updated `org.sonatype.central:central-publishing-maven-plugin:0.10.0` to `0.11.0`
* Added `org.spdx:spdx-maven-plugin:1.0.4`
