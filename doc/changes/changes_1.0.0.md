# Parquet for Java 1.0.0, released 2021-06-01

Code name: Initial Implementation

## Summary

A Java library to read Parquet files that we extracted into its own repository.

## Features

* #1: Extracted Parquet readers from cloud-storage-extension.
* #2: Added requirements specification.
* #4: Added Parquet field names to Row API.

## Refactoring

* #6: Fixed dependency vulnerability and broken links.
* #11: Updated Scala library to `2.12.14` version.

## Dependency Updates

### Compile Dependency Updates

* Added `com.google.guava:guava:30.1.1-jre`
* Added `org.apache.hadoop:hadoop-client:3.3.0`
* Added `org.apache.parquet:parquet-hadoop:1.12.0`
* Added `org.scala-lang:scala-library:2.13.6`

### Test Dependency Updates

* Added `org.hamcrest:hamcrest:2.2`
* Added `org.junit.jupiter:junit-jupiter:5.7.2`
* Added `org.mockito:mockito-core:3.10.0`
* Added `org.mockito:mockito-junit-jupiter:3.10.0`
* Added `org.scalatest:scalatest_2.13:3.2.7`

### Plugin Dependency Updates

* Added `io.github.zlika:reproducible-build-maven-plugin:0.13`
* Added `net.alchim31.maven:scala-maven-plugin:4.4.1`
* Added `org.apache.maven.plugins:maven-assembly-plugin:3.3.0`
* Added `org.apache.maven.plugins:maven-clean-plugin:2.5`
* Added `org.apache.maven.plugins:maven-compiler-plugin:3.8.1`
* Added `org.apache.maven.plugins:maven-deploy-plugin:3.0.0-M1`
* Added `org.apache.maven.plugins:maven-enforcer-plugin:3.0.0-M3`
* Added `org.apache.maven.plugins:maven-failsafe-plugin:3.0.0-M3`
* Added `org.apache.maven.plugins:maven-gpg-plugin:1.6`
* Added `org.apache.maven.plugins:maven-install-plugin:2.4`
* Added `org.apache.maven.plugins:maven-jar-plugin:2.4`
* Added `org.apache.maven.plugins:maven-javadoc-plugin:3.2.0`
* Added `org.apache.maven.plugins:maven-resources-plugin:2.6`
* Added `org.apache.maven.plugins:maven-site-plugin:3.3`
* Added `org.apache.maven.plugins:maven-source-plugin:3.2.1`
* Added `org.apache.maven.plugins:maven-surefire-plugin:3.0.0-M4`
* Added `org.codehaus.mojo:versions-maven-plugin:2.8.1`
* Added `org.itsallcode:openfasttrace-maven-plugin:1.1.0`
* Added `org.jacoco:jacoco-maven-plugin:0.8.6`
* Added `org.scalatest:scalatest-maven-plugin:2.0.2`
* Added `org.sonatype.ossindex.maven:ossindex-maven-plugin:3.1.0`
* Added `org.sonatype.plugins:nexus-staging-maven-plugin:1.6.8`
