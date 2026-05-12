# Parquet for Java 2.0.16, released 2026-05-??

Code name: Migrate Scala to Java

## Summary

This release migrates the project language from Scala to Java to simplify maintenance and reduce dependencies.

We removed the Maven Failsafe plugin, since this project does not feature any integration tests.

## Refactoring

* #87: Converted production and test code from Scala to Java.

## Bugfixes

* #88: Fixed requirements tracing using OpenFastTrace

## Dependency Updates

### Compile Dependency Updates

* Removed `org.scala-lang:scala-library:2.13.18`

### Test Dependency Updates

* Removed `org.scalatest:scalatest_2.13:3.3.0-SNAP4`

### Plugin Dependency Updates

* Removed `net.alchim31.maven:scala-maven-plugin:4.9.10`
* Removed `org.apache.maven.plugins:maven-failsafe-plugin:3.5.5`
* Removed `org.scalatest:scalatest-maven-plugin:2.2.0`
