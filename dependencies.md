<!-- @formatter:off -->
# Dependencies

## Compile Dependencies

| Dependency                      | License                                       |
| ------------------------------- | --------------------------------------------- |
| [Apache Parquet Hadoop][0]      | [The Apache Software License, Version 2.0][1] |
| [snappy-java][2]                | [Apache-2.0][3]                               |
| Apache Hadoop Client Aggregator | [Apache License, Version 2.0][4]              |
| [Apache Avro][5]                | [Apache-2.0][4]                               |
| [Scala Library][6]              | [Apache-2.0][7]                               |
| [error-reporting-java][8]       | [MIT License][9]                              |

## Test Dependencies

| Dependency                                 | License                                   |
| ------------------------------------------ | ----------------------------------------- |
| [JUnit Jupiter (Aggregator)][10]           | [Eclipse Public License v2.0][11]         |
| [mockito-core][12]                         | [MIT][13]                                 |
| [mockito-junit-jupiter][12]                | [MIT][13]                                 |
| [Hamcrest][14]                             | [BSD License 3][15]                       |
| [scalatest][16]                            | [the Apache License, ASL Version 2.0][17] |
| [EqualsVerifier \| release normal jar][18] | [Apache License, Version 2.0][4]          |

## Plugin Dependencies

| Dependency                                              | License                                       |
| ------------------------------------------------------- | --------------------------------------------- |
| [SonarQube Scanner for Maven][19]                       | [GNU LGPL 3][20]                              |
| [Apache Maven Compiler Plugin][21]                      | [Apache-2.0][4]                               |
| [Apache Maven Enforcer Plugin][22]                      | [Apache-2.0][4]                               |
| [Maven Flatten Plugin][23]                              | [Apache Software Licenese][4]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][24] | [ASL2][1]                                     |
| [Maven Surefire Plugin][25]                             | [Apache-2.0][4]                               |
| [Versions Maven Plugin][26]                             | [Apache License, Version 2.0][4]              |
| [scala-maven-plugin][27]                                | [Public domain (Unlicense)][28]               |
| [ScalaTest Maven Plugin][29]                            | [the Apache License, ASL Version 2.0][17]     |
| [OpenFastTrace Maven Plugin][30]                        | [GNU General Public License v3.0][31]         |
| [Project keeper maven plugin][32]                       | [The MIT License][33]                         |
| [duplicate-finder-maven-plugin Maven Mojo][34]          | [Apache License 2.0][35]                      |
| [Apache Maven Deploy Plugin][36]                        | [Apache-2.0][4]                               |
| [Apache Maven GPG Plugin][37]                           | [Apache-2.0][4]                               |
| [Apache Maven Source Plugin][38]                        | [Apache License, Version 2.0][4]              |
| [Apache Maven Javadoc Plugin][39]                       | [Apache-2.0][4]                               |
| [Nexus Staging Maven Plugin][40]                        | [Eclipse Public License][41]                  |
| [Maven Failsafe Plugin][42]                             | [Apache-2.0][4]                               |
| [JaCoCo :: Maven Plugin][43]                            | [Eclipse Public License 2.0][44]              |
| [error-code-crawler-maven-plugin][45]                   | [MIT License][46]                             |
| [Reproducible Build Maven Plugin][47]                   | [Apache 2.0][1]                               |
| [Maven Clean Plugin][48]                                | [The Apache Software License, Version 2.0][1] |
| [Maven Resources Plugin][49]                            | [The Apache Software License, Version 2.0][1] |
| [Maven JAR Plugin][50]                                  | [The Apache Software License, Version 2.0][1] |
| [Maven Install Plugin][51]                              | [The Apache Software License, Version 2.0][1] |
| [Maven Site Plugin 3][52]                               | [The Apache Software License, Version 2.0][1] |

[0]: https://parquet.apache.org
[1]: http://www.apache.org/licenses/LICENSE-2.0.txt
[2]: https://github.com/xerial/snappy-java
[3]: https://www.apache.org/licenses/LICENSE-2.0.html
[4]: https://www.apache.org/licenses/LICENSE-2.0.txt
[5]: https://avro.apache.org
[6]: https://www.scala-lang.org/
[7]: https://www.apache.org/licenses/LICENSE-2.0
[8]: https://github.com/exasol/error-reporting-java/
[9]: https://github.com/exasol/error-reporting-java/blob/main/LICENSE
[10]: https://junit.org/junit5/
[11]: https://www.eclipse.org/legal/epl-v20.html
[12]: https://github.com/mockito/mockito
[13]: https://github.com/mockito/mockito/blob/main/LICENSE
[14]: http://hamcrest.org/JavaHamcrest/
[15]: http://opensource.org/licenses/BSD-3-Clause
[16]: http://www.scalatest.org
[17]: http://www.apache.org/licenses/LICENSE-2.0
[18]: https://www.jqno.nl/equalsverifier
[19]: http://sonarsource.github.io/sonar-scanner-maven/
[20]: http://www.gnu.org/licenses/lgpl.txt
[21]: https://maven.apache.org/plugins/maven-compiler-plugin/
[22]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[23]: https://www.mojohaus.org/flatten-maven-plugin/
[24]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[25]: https://maven.apache.org/surefire/maven-surefire-plugin/
[26]: https://www.mojohaus.org/versions/versions-maven-plugin/
[27]: http://github.com/davidB/scala-maven-plugin
[28]: http://unlicense.org/
[29]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[30]: https://github.com/itsallcode/openfasttrace-maven-plugin
[31]: https://www.gnu.org/licenses/gpl-3.0.html
[32]: https://github.com/exasol/project-keeper/
[33]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[34]: https://basepom.github.io/duplicate-finder-maven-plugin
[35]: http://www.apache.org/licenses/LICENSE-2.0.html
[36]: https://maven.apache.org/plugins/maven-deploy-plugin/
[37]: https://maven.apache.org/plugins/maven-gpg-plugin/
[38]: https://maven.apache.org/plugins/maven-source-plugin/
[39]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[40]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[41]: http://www.eclipse.org/legal/epl-v10.html
[42]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[43]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[44]: https://www.eclipse.org/legal/epl-2.0/
[45]: https://github.com/exasol/error-code-crawler-maven-plugin/
[46]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[47]: http://zlika.github.io/reproducible-build-maven-plugin
[48]: http://maven.apache.org/plugins/maven-clean-plugin/
[49]: http://maven.apache.org/plugins/maven-resources-plugin/
[50]: http://maven.apache.org/plugins/maven-jar-plugin/
[51]: http://maven.apache.org/plugins/maven-install-plugin/
[52]: http://maven.apache.org/plugins/maven-site-plugin/
