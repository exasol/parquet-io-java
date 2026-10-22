# Parquet for Java 2.1.2, released 2026-10-22

Code name: Fixed CVE-2026-108106,CVE-2026-108104, CVE-2026-93452, CVE-2026-93451, CVE-2026-90559

## Summary

Fixed the following vulnerabilities by dependency update:

* In `org.xerial.snappy:snappy-java:jar:1.1.10.`: CVE-2026-108106,CVE-2026-108104, CVE-2026-93452, CVE-2026-93451, CVE-2026-90559

## Security

* #102: CVE-2026-90559: org.xerial.snappy:snappy-java:jar:1.1.10.8:compile
* #103: CVE-2026-93451: org.xerial.snappy:snappy-java:jar:1.1.10.8:compile
* #104: CVE-2026-93452: org.xerial.snappy:snappy-java:jar:1.1.10.8:compile
* #106: CVE-2026-108104: org.xerial.snappy:snappy-java:jar:1.1.10.8:compile
* #107: CVE-2026-108106: org.xerial.snappy:snappy-java:jar:1.1.10.8:compile

## Dependency Updates

### Compile Dependency Updates

* Updated `org.apache.hadoop:hadoop-client-api:3.4.3` to `3.5.0`
* Added `org.xerial.snappy:snappy-java:1.1.10.12`

### Runtime Dependency Updates

* Updated `org.apache.hadoop:hadoop-client-runtime:3.4.3` to `3.5.0`
* Updated `org.slf4j:jcl-over-slf4j:2.0.19` to `2.0.20`

### Test Dependency Updates

* Updated `nl.jqno.equalsverifier:equalsverifier:3.19.4` to `4.5.2`
* Updated `org.junit.jupiter:junit-jupiter:5.14.4` to `6.1.3`
* Updated `org.mockito:mockito-core:5.23.0` to `5.24.0`
* Updated `org.mockito:mockito-junit-jupiter:5.23.0` to `5.24.0`
* Updated `org.slf4j:slf4j-jdk14:2.0.19` to `2.0.20`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:2.1.1` to `2.1.2`
* Updated `com.exasol:project-keeper-maven-plugin:5.7.5` to `5.8.0`
* Updated `org.apache.maven.plugins:maven-artifact-plugin:3.6.1` to `3.7.0`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.15.0` to `3.16.0`
* Updated `org.apache.maven.plugins:maven-deploy-plugin:3.1.4` to `3.2.0`
* Updated `org.apache.maven.plugins:maven-install-plugin:3.1.4` to `3.2.0`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.5.6` to `3.6.0`
* Updated `org.codehaus.mojo:build-helper-maven-plugin:3.6.1` to `3.6.2`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.21.0` to `2.23.0`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:5.7.0.6970` to `5.8.0.7211`
* Updated `org.sonatype.central:central-publishing-maven-plugin:0.11.0` to `0.12.0`
