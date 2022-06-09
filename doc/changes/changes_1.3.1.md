# Parquet for Java 1.3.1, released 2022-06-09

Code name: 1.3.1: Fix vulnerabilities

## Summary

This release fixes vulnerabilities in dependencies by updating compile dependency guava, hadoop-client and parquet-hadoop, along with test and plugin dependencies.

## Features

* #42: Upgraded dependencies to fix vulnerabilities

## Dependency Updates

### Compile Dependency Updates

* Updated `com.google.guava:guava:31.0.1-jre` to `31.1-jre`
* Updated `org.apache.hadoop:hadoop-client:3.3.1` to `3.3.3`
* Updated `org.apache.parquet:parquet-hadoop:1.12.2` to `1.12.3`

### Test Dependency Updates

* Updated `org.mockito:mockito-core:4.2.0` to `4.6.1`
* Updated `org.mockito:mockito-junit-jupiter:4.2.0` to `4.6.1`
* Updated `org.scalatest:scalatest_2.13:3.2.7` to `3.2.12`

### Plugin Dependency Updates

* Added `com.exasol:error-code-crawler-maven-plugin:1.1.1`
* Updated `com.exasol:project-keeper-maven-plugin:1.3.4` to `2.4.6`
* Updated `net.alchim31.maven:scala-maven-plugin:4.5.6` to `4.6.2`
* Updated `org.apache.maven.plugins:maven-clean-plugin:3.1.0` to `2.5`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.8.1` to `3.10.1`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.0.0-M3` to `3.0.0-M5`
* Updated `org.apache.maven.plugins:maven-install-plugin:2.5.2` to `2.4`
* Updated `org.apache.maven.plugins:maven-jar-plugin:3.2.2` to `2.4`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.3.1` to `3.4.0`
* Updated `org.apache.maven.plugins:maven-resources-plugin:3.2.0` to `2.6`
* Updated `org.apache.maven.plugins:maven-site-plugin:3.10.0` to `3.3`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.0.0-M4` to `3.0.0-M5`
* Added `org.codehaus.mojo:flatten-maven-plugin:1.2.7`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.8.1` to `2.10.0`
* Updated `org.itsallcode:openfasttrace-maven-plugin:1.3.0` to `1.5.0`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.7` to `0.8.8`
* Added `org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.1.2184`
* Updated `org.sonatype.ossindex.maven:ossindex-maven-plugin:3.1.0` to `3.2.0`
* Updated `org.sonatype.plugins:nexus-staging-maven-plugin:1.6.8` to `1.6.13`
