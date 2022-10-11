# Parquet for Java 2.0.0, released 2022-10-11

Code name: Remove packed dependencies from JAR

## Summary

This is a breaking change. The built JAR contained classes from all dependencies. We removed them, and now dependencies must be resolved via Maven Central.

## Features

* #51: Removed packed dependencies from JAR

## Dependency Updates

### Compile Dependency Updates

* Updated `org.scala-lang:scala-library:2.13.9` to `2.13.10`

### Plugin Dependency Updates

* Removed `org.apache.maven.plugins:maven-assembly-plugin:3.3.0`
