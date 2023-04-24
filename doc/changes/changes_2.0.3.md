# Parquet for Java 2.0.3, released 2023-04-24

Code name: Fix CVE-2023-26048

## Summary

This release fixes vulnerability CVE-2023-26048 (Uncontrolled Resource Consumption) in transitive dependency `org.eclipse.jetty:jetty-util:jar:9.4.48.v20220622` by excluding it as it is not used.

## Security

* #57: Fixed CVE-2023-26048

## Dependency Updates

### Test Dependency Updates

* Updated `org.mockito:mockito-core:5.3.0` to `5.3.1`
* Updated `org.mockito:mockito-junit-jupiter:5.3.0` to `5.3.1`
