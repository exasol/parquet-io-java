# Parquet for Java 1.0.2, released 2021-07-21

Code name: Fixed Transitive Dependency Vulnerability

## Summary

This releases remove transitive dependency that contains vulnerability by updating version of `hadoop-client`. Additionally, we updated versions of other runtime and test dependencies.

## Bug Fixes

* #22: Fixed transitive dependency vulnerability

## Dependency Updates

### Compile Dependency Updates

* Updated `org.apache.hadoop:hadoop-client:3.3.0` to `3.3.1`

### Test Dependency Updates

* Updated `org.mockito:mockito-core:3.10.0` to `3.11.2`
* Updated `org.mockito:mockito-junit-jupiter:3.10.0` to `3.11.2`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:0.8.0` to `0.10.0`
* Updated `net.alchim31.maven:scala-maven-plugin:4.4.1` to `4.5.3`
* Updated `org.apache.maven.plugins:maven-gpg-plugin:1.6` to `3.0.1`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.2.0` to `3.3.0`
* Updated `org.itsallcode:openfasttrace-maven-plugin:1.1.0` to `1.2.0`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.6` to `0.8.7`
