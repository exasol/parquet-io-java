# Parquet for Java 2.0.7, released 2024-04-11

Code name: Fix vulnerabilities CVE-2024-25710, CVE-2024-26308 and CVE-2023-52428 in compile dependencies

## Summary

This release fixes vulnerabilities in the following compile dependencies:

* `org.apache.commons:commons-compress`
  * CVE-2024-25710: CWE-835: Loop with Unreachable Exit Condition ('Infinite Loop') (8.1)
  * CVE-2024-26308: CWE-770: Allocation of Resources Without Limits or Throttling (7.5)
* `com.nimbusds:nimbus-jose-jwt`
  * CVE-2023-52428: CWE-400: Uncontrolled Resource Consumption ('Resource Exhaustion') (7.5)

## Security

#66: Fixed vulnerabilities

## Dependency Updates

### Compile Dependency Updates

* Updated `org.apache.commons:commons-compress:1.24.0` to `1.26.1`
* Updated `org.scala-lang:scala-library:2.13.12` to `2.13.13`

### Test Dependency Updates

* Updated `nl.jqno.equalsverifier:equalsverifier:3.15.2` to `3.15.8`
* Updated `org.junit.jupiter:junit-jupiter:5.10.0` to `5.10.2`
* Updated `org.mockito:mockito-core:5.6.0` to `5.11.0`
* Updated `org.mockito:mockito-junit-jupiter:5.6.0` to `5.11.0`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:1.3.0` to `2.0.0`
* Updated `com.exasol:project-keeper-maven-plugin:2.9.12` to `4.1.0`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.11.0` to `3.12.1`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.4.0` to `3.4.1`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.1.2` to `3.2.5`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.5.0` to `3.6.3`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.1.2` to `3.2.5`
* Added `org.apache.maven.plugins:maven-toolchains-plugin:3.1.0`
* Updated `org.codehaus.mojo:flatten-maven-plugin:1.5.0` to `1.6.0`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.16.0` to `2.16.2`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.10` to `0.8.11`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.1.2184` to `3.10.0.2594`
