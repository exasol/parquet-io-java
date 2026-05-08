# Parquet for Java 2.0.14, released 2026-05-08

Code name: Fix CVE-2025-67721 in dependency io.airlift:aircompressor

## Summary

This release fixes CVE-2025-67721 in dependency `io.airlift:aircompressor`.

## Security

* #84: Fixed CVE-2025-67721 in `io.airlift:aircompressor`

## Dependency Updates

### Compile Dependency Updates

* Updated `com.exasol:error-reporting-java:1.0.1` to `1.0.2`
* Updated `org.apache.hadoop:hadoop-client-api:3.4.1` to `3.4.3`
* Updated `org.apache.parquet:parquet-hadoop:1.15.0` to `1.17.0`
* Updated `org.scala-lang:scala-library:2.13.16` to `2.13.18`

### Runtime Dependency Updates

* Updated `org.apache.hadoop:hadoop-client-runtime:3.4.1` to `3.4.3`

### Test Dependency Updates

* Updated `nl.jqno.equalsverifier:equalsverifier:3.19` to `3.19.4`
* Updated `org.junit.jupiter:junit-jupiter:5.11.4` to `5.14.4`
* Updated `org.mockito:mockito-core:5.15.2` to `5.23.0`
* Updated `org.mockito:mockito-junit-jupiter:5.15.2` to `5.23.0`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:2.0.3` to `2.0.7`
* Updated `com.exasol:project-keeper-maven-plugin:4.5.0` to `5.6.2`
* Updated `com.exasol:quality-summarizer-maven-plugin:0.2.0` to `0.2.1`
* Added `io.github.git-commit-id:git-commit-id-maven-plugin:10.0.0`
* Removed `io.github.zlika:reproducible-build-maven-plugin:0.17`
* Updated `net.alchim31.maven:scala-maven-plugin:4.8.1` to `4.9.10`
* Added `org.apache.maven.plugins:maven-artifact-plugin:3.6.1`
* Updated `org.apache.maven.plugins:maven-clean-plugin:3.4.0` to `3.5.0`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.13.0` to `3.15.0`
* Updated `org.apache.maven.plugins:maven-deploy-plugin:3.1.3` to `3.1.4`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.5.0` to `3.6.2`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.5.2` to `3.5.5`
* Updated `org.apache.maven.plugins:maven-gpg-plugin:3.2.7` to `3.2.8`
* Updated `org.apache.maven.plugins:maven-install-plugin:3.1.3` to `3.1.4`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.11.1` to `3.12.0`
* Updated `org.apache.maven.plugins:maven-resources-plugin:3.3.1` to `3.5.0`
* Updated `org.apache.maven.plugins:maven-source-plugin:3.2.1` to `3.4.0`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.5.2` to `3.5.5`
* Updated `org.codehaus.mojo:flatten-maven-plugin:1.6.0` to `1.7.3`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.18.0` to `2.21.0`
* Updated `org.itsallcode:openfasttrace-maven-plugin:1.6.2` to `2.3.0`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.12` to `0.8.14`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:5.0.0.4389` to `5.5.0.6356`
* Added `org.sonatype.central:central-publishing-maven-plugin:0.10.0`
* Removed `org.sonatype.plugins:nexus-staging-maven-plugin:1.7.0`
