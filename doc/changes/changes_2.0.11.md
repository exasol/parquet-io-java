# Parquet for Java 2.0.11, released 2024-??-??

Code name: Fixed vulnerability CVE-2024-47561 in org.apache.avro:avro:jar:1.11.3:compile

## Summary

This release fixes the following vulnerability:

### CVE-2024-47561 (CWE-502) in dependency `org.apache.avro:avro:jar:1.11.3:compile`
Schema parsing in the Java SDK of Apache Avro 1.11.3 and previous versions allows bad actors to execute arbitrary code.
Users are recommended to upgrade to version 1.11.4Â  or 1.12.0, which fix this issue.
#### References
* https://ossindex.sonatype.org/vulnerability/CVE-2024-47561?component-type=maven&component-name=org.apache.avro%2Favro&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2024-47561
* https://lists.apache.org/thread/c2v7mhqnmq0jmbwxqq3r5jbj1xg43h5x

## Security

* #76: Fixed vulnerability CVE-2024-47561 in dependency `org.apache.avro:avro:jar:1.11.3:compile`

## Dependency Updates

### Compile Dependency Updates

* Updated `dnsjava:dnsjava:3.6.0` to `3.6.2`
* Updated `io.airlift:aircompressor:0.27` to `2.0.2`
* Updated `org.apache.avro:avro:1.11.3` to `1.12.0`
* Updated `org.apache.commons:commons-compress:1.26.2` to `1.27.1`
* Updated `org.apache.parquet:parquet-hadoop:1.14.1` to `1.14.2`
* Updated `org.scala-lang:scala-library:2.13.14` to `2.13.15`
* Updated `org.xerial.snappy:snappy-java:1.1.10.5` to `1.1.10.7`

### Test Dependency Updates

* Updated `nl.jqno.equalsverifier:equalsverifier:3.16.1` to `3.17.1`
* Updated `org.hamcrest:hamcrest:2.2` to `3.0`
* Updated `org.junit.jupiter:junit-jupiter:5.10.3` to `5.11.2`
* Updated `org.mockito:mockito-core:5.12.0` to `5.14.1`
* Updated `org.mockito:mockito-junit-jupiter:5.12.0` to `5.14.1`
