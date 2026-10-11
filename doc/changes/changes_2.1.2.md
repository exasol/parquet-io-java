# Parquet for Java 2.1.2, released 2026-??-??

Code name: Fixed vulnerabilities CVE-2026-108104, CVE-2026-108106

## Summary

This release fixes the following 2 vulnerabilities:

### CVE-2026-108104 (CWE-415) in dependency `org.xerial.snappy:snappy-java:jar:1.1.10.8:compile`
Xerial snappy-java from 1.1.7.4 before 1.1.10.10 contains a double release vulnerability in SnappyFramedInputStream that returns pooled buffers twice when replacement allocation fails. Attackers can supply framed data with a large declared chunk length to trigger OutOfMemoryError, causing shared backing arrays that expose or overwrite other streams' decompressed data.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-108104?component-type=maven&component-name=org.xerial.snappy%2Fsnappy-java&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-108104
* https://github.com/CVEProject/cvelistV5/blob/main/cves/2026/108xxx/CVE-2026-108104.json

### CVE-2026-108106 (CWE-789) in dependency `org.xerial.snappy:snappy-java:jar:1.1.10.8:compile`
Xerial snappy-java before 1.1.10.9 contains an unbounded memory allocation vulnerability that allows attackers to exhaust JVM memory by declaring a large uncompressed length in compressed input. Attackers can supply a few crafted bytes to Snappy.uncompress, uncompressString, SnappyInputStream or SnappyFramedInputStream to force allocations up to 2 GB, causing OutOfMemoryError and denial of service.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-108106?component-type=maven&component-name=org.xerial.snappy%2Fsnappy-java&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-108106
* https://github.com/CVEProject/cvelistV5/blob/main/cves/2026/108xxx/CVE-2026-108106.json

## Security

* #106: Fixed vulnerability CVE-2026-108104 in dependency `org.xerial.snappy:snappy-java:jar:1.1.10.8:compile`
* #107: Fixed vulnerability CVE-2026-108106 in dependency `org.xerial.snappy:snappy-java:jar:1.1.10.8:compile`

## Dependency Updates

### Compile Dependency Updates

* Updated `org.apache.hadoop:hadoop-client-api:3.4.3` to `3.5.0`

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

* Updated `com.exasol:project-keeper-maven-plugin:5.7.5` to `5.7.6`
