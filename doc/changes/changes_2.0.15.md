# Parquet for Java 2.0.15, released 2026-05-08

Code name: Fix CVE-2025-67721 for users

## Summary

This release fixes CVE-2025-67721 in dependency `io.airlift:aircompressor` also for users of this library by moving the upgraded dependency from `<dependencyManagement>` to `<dependencies>`. This upgrades the library also for clients.

## Dependency Updates

### Runtime Dependency Updates

* Added `io.airlift:aircompressor:2.0.3`
