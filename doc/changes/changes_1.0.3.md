# Parquet for Java 1.0.3, released 2021-08-05

Code name: Excluded vulnerable dependency

## Summary

This releases excludes a vulnerable (CVE-2021-35516, CVE-2021-35515, CVE-2021-35517, CVE-2021-36090) transitive dependency and adds updated version of it.

## Refactoring

* #24: Excluded older version of commons-compress which included vulnerabilities

## Dependency Updates

### Compile Dependency Updates

* Added `org.apache.commons:commons-compress:1.21`
