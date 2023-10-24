# Parquet for Java 2.0.6, released 2023-10-24

Code name: Fix CVE-2023-39410 and CVE-2023-42503

## Summary

This release fixes the following vulnerabilities:

* CVE-2023-39410 in compile dependency `org.apache.avro:avro`
* CVE-2023-42503 in compile dependency `org.apache.commons:commons-compress`

## Security

* #64: Fixed CVE-2023-39410 in `org.apache.avro:avro`

## Dependency Updates

### Compile Dependency Updates

* Added `org.apache.avro:avro:1.11.3`
* Added `org.apache.commons:commons-compress:1.24.0`

### Test Dependency Updates

* Added `nl.jqno.equalsverifier:equalsverifier:3.15.2`
* Updated `org.mockito:mockito-core:5.5.0` to `5.6.0`
* Updated `org.mockito:mockito-junit-jupiter:5.5.0` to `5.6.0`
