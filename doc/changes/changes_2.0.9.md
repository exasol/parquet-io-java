# Parquet for Java 2.0.9, released 2024-06-03

Code name: Security update - fix for CVE-2024-36114

## Summary

Fixed CVE-2024-36114  https://github.com/advisories/GHSA-973x-65j7-xcf4 via transitive version update.
Updated dependencies.

## Security

* CVE-2024-36114: io.airlift:aircompressor:jar:0.21:compile

## Dependency Updates

### Compile Dependency Updates

* Added `io.airlift:aircompressor:0.27`
* Updated `org.apache.commons:commons-compress:1.26.1` to `1.26.2`

### Test Dependency Updates

* Updated `org.mockito:mockito-core:5.11.0` to `5.12.0`
* Updated `org.mockito:mockito-junit-jupiter:5.11.0` to `5.12.0`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:2.0.2` to `2.0.3`
* Updated `com.exasol:project-keeper-maven-plugin:4.3.0` to `4.3.2`
* Updated `org.apache.maven.plugins:maven-deploy-plugin:3.1.1` to `3.1.2`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.4.1` to `3.5.0`
* Updated `org.apache.maven.plugins:maven-gpg-plugin:3.2.2` to `3.2.4`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.6.3` to `3.7.0`
* Updated `org.apache.maven.plugins:maven-toolchains-plugin:3.1.0` to `3.2.0`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:3.11.0.3922` to `4.0.0.4121`
* Updated `org.sonatype.plugins:nexus-staging-maven-plugin:1.6.13` to `1.7.0`
