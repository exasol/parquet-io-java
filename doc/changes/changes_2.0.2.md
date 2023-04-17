# Parquet for Java 2.0.2, released 2023-04-17

Code name: Remove duplicate classes

## Summary

This release removes duplicate classes from dependencies.

## Bugfixes

* #55: Removed duplicate classes from dependencies

## Dependency Updates

### Compile Dependency Updates

* Removed `com.fasterxml.woodstox:woodstox-core:6.5.0`
* Removed `com.google.guava:guava:31.1-jre`
* Removed `org.apache.commons:commons-compress:1.22`
* Updated `org.apache.hadoop:hadoop-client:3.3.4` to `3.3.5`
* Updated `org.apache.parquet:parquet-hadoop:1.12.3` to `1.13.0`

### Test Dependency Updates

* Updated `org.mockito:mockito-core:5.2.0` to `5.3.0`
* Updated `org.mockito:mockito-junit-jupiter:5.2.0` to `5.3.0`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:1.2.2` to `1.2.3`
* Updated `com.exasol:project-keeper-maven-plugin:2.9.4` to `2.9.7`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.10.1` to `3.11.0`
* Updated `org.apache.maven.plugins:maven-deploy-plugin:3.1.0` to `3.1.1`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.2.1` to `3.3.0`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.0.0-M8` to `3.0.0`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.4.1` to `3.5.0`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.0.0-M8` to `3.0.0`
* Added `org.basepom.maven:duplicate-finder-maven-plugin:1.5.1`
* Updated `org.codehaus.mojo:flatten-maven-plugin:1.3.0` to `1.4.1`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.14.2` to `2.15.0`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.8` to `0.8.9`
