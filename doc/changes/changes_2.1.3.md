# Parquet for Java 2.1.3, released 2026-??-??

Code name: Fixed vulnerability CVE-2026-116486 in org.objenesis:objenesis:jar:3.3:test

## Summary

This release fixes the following vulnerability:

### CVE-2026-116486 (CWE-386) in dependency `org.objenesis:objenesis:jar:3.3:test`
Objenesis before 3.6 contains a type confusion vulnerability that allows code in one class loader to substitute objects into another by exploiting instantiator caching keyed on class name. When a shared ObjenesisStd instance caches instantiators by clazz.getName() in ObjenesisBase.getInstantiatorOf(), same-named classes collide, causing newInstance() to return wrong-class objects and ClassCastException failures.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-116486?component-type=maven&component-name=org.objenesis%2Fobjenesis&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-116486
* https://github.com/CVEProject/cvelistV5/blob/main/cves/2026/116xxx/CVE-2026-116486.json

## Security

* #113: Fixed vulnerability CVE-2026-116486 in dependency `org.objenesis:objenesis:jar:3.3:test`

## Dependency Updates

### Compile Dependency Updates

* Updated `org.apache.hadoop:hadoop-client-api:3.4.3` to `3.5.0`

### Runtime Dependency Updates

* Updated `org.apache.hadoop:hadoop-client-runtime:3.4.3` to `3.5.0`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:5.8.0` to `5.8.2`
