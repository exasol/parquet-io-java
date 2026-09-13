# Parquet for Java 2.1.1, released 2026-??-??

Code name: Fixed vulnerabilities CVE-2026-73334, CVE-2026-87795, CVE-2026-87823, CVE-2026-89045

## Summary

This release fixes the following 4 vulnerabilities:

### CVE-2026-73334 (CWE-20) in dependency `org.apache.parquet:parquet-hadoop:jar:1.17.1:compile`
Potential problem for users of theÂ org.apache.parquet.crypto.keytools package in Apache Parquet, versions 1.12 to 1.18. 
This package enables users to encrypt Parquet files via an envelope encryption mechanism that wraps (encrypts) data keys via a Key Management Service (KMS).Â 
On the reader side, the KMS URL can be application-controlled or file-controlled.
If the user does not leverage application control for this parameter, a file-controlled KMS URL is forwarded to a pluggable KmsClient implementation. 
If the pluggable implementation does not perform host validation, a KMS token can be sent to a malicious host set by an attacker in the file.

Before the problem is fixed, users are recommended toÂ leverage application control for KMS URL parameter in readers (versions 1.12-1.18).
After the problem is fixed (presumably in version 1.19), the upgrade will disable file-controlled KMS URL by default. Users of the KMS URL parameterÂ 
will have two options then:Â leverage application control for KMS URL parameter in readers, or enable file-controlledÂ KMS URL (via a new app parameter).
The latter option will explicitly require (in the new parameter documentation) to validate the KMS URL and use authentication in the custom implementation of the KMS client plug in.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-73334?component-type=maven&component-name=org.apache.parquet%2Fparquet-hadoop&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-73334
* https://lists.apache.org/thread/ro2vomk9xxhv34xhvopgys96c9j8ojm0
* http://www.openwall.com/lists/oss-security/2026/09/08/11

### CVE-2026-87795 (CWE-125) in dependency `com.github.luben:zstd-jni:jar:1.5.7-3:compile`
zstd-jni versions before 1.5.7-14 fail to validate offset and length parameters in the ZstdDictCompress constructor, allowing out-of-bounds memory reads. Attackers can supply untrusted offset or length values to read native heap memory into the compression dictionary, typically causing JVM crashes.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-87795?component-type=maven&component-name=com.github.luben%2Fzstd-jni&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-87795
* https://github.com/luben/zstd-jni/security/advisories/GHSA-ff36-7w3w-g8rm

### CVE-2026-87823 (CWE-190) in dependency `com.github.luben:zstd-jni:jar:1.5.7-3:compile`
zstd-jni before 1.5.7-14 performs 32-bit signed bounds checks on three direct-ByteBuffer frame-size native methods, allowing out-of-bounds memory reads via negative or overflowing offsets. Attackers can supply negative offset values near Integer.MIN_VALUE to read unmapped memory, causing JVM termination or extracting arbitrary frame size data from unintended memory locations.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-87823?component-type=maven&component-name=com.github.luben%2Fzstd-jni&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-87823
* https://github.com/luben/zstd-jni/security/advisories/GHSA-jfr6-9xqw-2g2q
* https://www.vulncheck.com/advisories/zstd-jni-1.1.1-through-1.5.7-13-out-of-bounds-read-via-direct-bytebuffer-frame-size-methods

### CVE-2026-89045 (CWE-835) in dependency `com.github.luben:zstd-jni:jar:1.5.7-3:compile`
zstd-jni versions 1.4.8-4 through 1.5.7-13 fail to validate negative length parameters in ZstdInputStreamNoFinalizer.read(), allowing attackers to trigger infinite loops. Attackers can pass negative length values to cause the read method to spin indefinitely while holding the stream monitor, blocking all other threads from accessing the stream.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-89045?component-type=maven&component-name=com.github.luben%2Fzstd-jni&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-89045
* https://github.com/luben/zstd-jni/releases/tag/v1.5.7-14
* https://github.com/luben/zstd-jni/security/advisories/GHSA-9jx2-gfp9-phfm

## Security

* #97: Fixed vulnerability CVE-2026-73334 in dependency `org.apache.parquet:parquet-hadoop:jar:1.17.1:compile`
* #98: Fixed vulnerability CVE-2026-87795 in dependency `com.github.luben:zstd-jni:jar:1.5.7-3:compile`
* #99: Fixed vulnerability CVE-2026-87823 in dependency `com.github.luben:zstd-jni:jar:1.5.7-3:compile`
* #100: Fixed vulnerability CVE-2026-89045 in dependency `com.github.luben:zstd-jni:jar:1.5.7-3:compile`

## Dependency Updates

### Compile Dependency Updates

* Updated `org.apache.hadoop:hadoop-client-api:3.4.3` to `3.5.0`
* Updated `org.apache.parquet:parquet-hadoop:1.17.1` to `1.18.1`

### Runtime Dependency Updates

* Updated `org.apache.hadoop:hadoop-client-runtime:3.4.3` to `3.5.0`
* Updated `org.slf4j:jcl-over-slf4j:2.0.18` to `2.0.19`

### Test Dependency Updates

* Updated `nl.jqno.equalsverifier:equalsverifier:3.19.4` to `4.5.2`
* Updated `org.junit.jupiter:junit-jupiter:5.14.4` to `6.1.3`
* Updated `org.slf4j:slf4j-jdk14:2.0.18` to `2.0.19`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:5.7.4` to `5.7.5`
