# Parquet for Java 2.0.13, released 2025-02-12

Code name: Hadoop dependency cleanup

## Summary

This release fine-tunes the needed dependencies replacing `haddop-client` by `hadoop-client-api` at compile time and 
`hadoop-client-runtime` and runtime. `hadoop-client` pulled many vulnerabilities that were not needed like `netty`

## Security

* #81: Fix vulnerability CVE-2025-25193 in `io.netty:netty-common:jar:4.1.115.Final:runtime`

## Dependency Updates

### Compile Dependency Updates

* Removed `dnsjava:dnsjava:3.6.2`
* Removed `org.apache.avro:avro:1.12.0`
* Removed `org.apache.commons:commons-configuration2:2.11.0`
* Added `org.apache.hadoop:hadoop-client-api:3.4.1`
* Removed `org.apache.hadoop:hadoop-client:3.4.1`
* Updated `org.apache.parquet:parquet-hadoop:1.14.4` to `1.15.0`
* Updated `org.scala-lang:scala-library:2.13.15` to `2.13.16`
* Added `org.slf4j:slf4j-api:1.7.36`

### Runtime Dependency Updates

* Removed `io.netty:netty-transport-native-epoll:4.1.115.Final`
* Added `org.apache.hadoop:hadoop-client-runtime:3.4.1`
* Added `org.slf4j:jcl-over-slf4j:1.7.36`

### Test Dependency Updates

* Updated `nl.jqno.equalsverifier:equalsverifier:3.17.3` to `3.19`
* Updated `org.junit.jupiter:junit-jupiter:5.11.3` to `5.11.4`
* Updated `org.mockito:mockito-core:5.14.2` to `5.15.2`
* Updated `org.mockito:mockito-junit-jupiter:5.14.2` to `5.15.2`
* Added `org.slf4j:slf4j-jdk14:1.7.36`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:4.4.0` to `4.5.0`
* Updated `org.apache.maven.plugins:maven-deploy-plugin:3.1.2` to `3.1.3`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.5.1` to `3.5.2`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.10.1` to `3.11.1`
* Updated `org.apache.maven.plugins:maven-site-plugin:3.9.1` to `3.21.0`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.5.1` to `3.5.2`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.17.1` to `2.18.0`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:4.0.0.4121` to `5.0.0.4389`
