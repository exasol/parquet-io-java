# Parquet for Java 2.0.12, released 2024-??-??

Code name: Fixed vulnerability CVE-2024-47535 in io.netty:netty-common:jar:4.1.100.Final:compile

## Summary

This release fixes the following vulnerability:

### CVE-2024-47535 (CWE-400) in dependency `io.netty:netty-common:jar:4.1.100.Final:compile`
Netty is an asynchronous event-driven network application framework for rapid development of maintainable high performance protocol servers & clients. An unsafe reading of environment file could potentially cause a denial of service in Netty. When loaded on an Windows application, Netty attempts to load a file that does not exist. If an attacker creates such a large file, the Netty application crashes. This vulnerability is fixed in 4.1.115.
#### References
* https://ossindex.sonatype.org/vulnerability/CVE-2024-47535?component-type=maven&component-name=io.netty%2Fnetty-common&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2024-47535
* https://github.com/advisories/GHSA-xq3w-v528-46rv

## Security

* #79: Fixed vulnerability CVE-2024-47535 in dependency `io.netty:netty-common:jar:4.1.100.Final:compile`

## Dependency Updates

### Compile Dependency Updates

* Updated `org.apache.hadoop:hadoop-client:3.4.0` to `3.4.1`
* Updated `org.apache.parquet:parquet-hadoop:1.14.3` to `1.14.4`

### Test Dependency Updates

* Updated `nl.jqno.equalsverifier:equalsverifier:3.17.1` to `3.17.3`
* Updated `org.junit.jupiter:junit-jupiter:5.11.2` to `5.11.3`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:4.3.3` to `4.4.0`
