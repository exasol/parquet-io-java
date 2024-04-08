# Parquet for Java 2.0.8, released 2024-04-08

Code name: Fix CVE-2024-29131 & CVE-2024-29133 in `org.apache.commons:commons-configuration2:jar:2.8.0:compile`

## Summary

This release fixes vulnerabilities CVE-2024-29131 & CVE-2024-29133 in `org.apache.commons:commons-configuration2:jar:2.8.0:compile`.

## Security

* #68: Fixed CVE-2024-29131 in `org.apache.commons:commons-configuration2:jar:2.8.0:compile`
* #69: Fixed CVE-2024-29133 in `org.apache.commons:commons-configuration2:jar:2.8.0:compile`

## Dependency Updates

### Compile Dependency Updates

* Added `org.apache.commons:commons-configuration2:2.10.1`
* Updated `org.apache.hadoop:hadoop-client:3.3.6` to `3.4.0`

### Test Dependency Updates

* Updated `nl.jqno.equalsverifier:equalsverifier:3.15.8` to `3.16.1`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:2.0.0` to `2.0.2`
* Updated `com.exasol:project-keeper-maven-plugin:4.1.0` to `4.3.0`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.12.1` to `3.13.0`
* Updated `org.apache.maven.plugins:maven-gpg-plugin:3.1.0` to `3.2.2`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.11` to `0.8.12`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:3.10.0.2594` to `3.11.0.3922`
