<!-- @formatter:off -->
# Dependencies

## Compile Dependencies

| Dependency                        | License                                       |
| --------------------------------- | --------------------------------------------- |
| [Apache Parquet Hadoop][0]        | [The Apache Software License, Version 2.0][1] |
| [aircompressor][2]                | [Apache License 2.0][3]                       |
| [snappy-java][4]                  | [Apache-2.0][3]                               |
| Apache Hadoop Client Aggregator   | [Apache-2.0][5]                               |
| [Apache Avro][6]                  | [Apache-2.0][5]                               |
| [Apache Commons Compress][7]      | [Apache-2.0][5]                               |
| [Apache Commons Configuration][8] | [Apache-2.0][5]                               |
| [Scala Library][9]                | [Apache-2.0][10]                              |
| [error-reporting-java][11]        | [MIT License][12]                             |

## Test Dependencies

| Dependency                                 | License                                   |
| ------------------------------------------ | ----------------------------------------- |
| [JUnit Jupiter (Aggregator)][13]           | [Eclipse Public License v2.0][14]         |
| [mockito-core][15]                         | [MIT][16]                                 |
| [mockito-junit-jupiter][15]                | [MIT][16]                                 |
| [Hamcrest][17]                             | [BSD License 3][18]                       |
| [scalatest][19]                            | [the Apache License, ASL Version 2.0][20] |
| [EqualsVerifier \| release normal jar][21] | [Apache License, Version 2.0][5]          |

## Plugin Dependencies

| Dependency                                              | License                                   |
| ------------------------------------------------------- | ----------------------------------------- |
| [SonarQube Scanner for Maven][22]                       | [GNU LGPL 3][23]                          |
| [Apache Maven Toolchains Plugin][24]                    | [Apache-2.0][5]                           |
| [Apache Maven Compiler Plugin][25]                      | [Apache-2.0][5]                           |
| [Apache Maven Enforcer Plugin][26]                      | [Apache-2.0][5]                           |
| [Maven Flatten Plugin][27]                              | [Apache Software Licenese][5]             |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][28] | [ASL2][1]                                 |
| [Maven Surefire Plugin][29]                             | [Apache-2.0][5]                           |
| [Versions Maven Plugin][30]                             | [Apache License, Version 2.0][5]          |
| [scala-maven-plugin][31]                                | [Public domain (Unlicense)][32]           |
| [ScalaTest Maven Plugin][33]                            | [the Apache License, ASL Version 2.0][20] |
| [OpenFastTrace Maven Plugin][34]                        | [GNU General Public License v3.0][35]     |
| [Project Keeper Maven plugin][36]                       | [The MIT License][37]                     |
| [duplicate-finder-maven-plugin Maven Mojo][38]          | [Apache License 2.0][39]                  |
| [Apache Maven Deploy Plugin][40]                        | [Apache-2.0][5]                           |
| [Apache Maven GPG Plugin][41]                           | [Apache-2.0][5]                           |
| [Apache Maven Source Plugin][42]                        | [Apache License, Version 2.0][5]          |
| [Apache Maven Javadoc Plugin][43]                       | [Apache-2.0][5]                           |
| [Nexus Staging Maven Plugin][44]                        | [Eclipse Public License][45]              |
| [Maven Failsafe Plugin][46]                             | [Apache-2.0][5]                           |
| [JaCoCo :: Maven Plugin][47]                            | [EPL-2.0][48]                             |
| [error-code-crawler-maven-plugin][49]                   | [MIT License][50]                         |
| [Reproducible Build Maven Plugin][51]                   | [Apache 2.0][1]                           |

[0]: https://parquet.apache.org
[1]: http://www.apache.org/licenses/LICENSE-2.0.txt
[2]: https://github.com/airlift/aircompressor
[3]: https://www.apache.org/licenses/LICENSE-2.0.html
[4]: https://github.com/xerial/snappy-java
[5]: https://www.apache.org/licenses/LICENSE-2.0.txt
[6]: https://avro.apache.org
[7]: https://commons.apache.org/proper/commons-compress/
[8]: https://commons.apache.org/proper/commons-configuration/
[9]: https://www.scala-lang.org/
[10]: https://www.apache.org/licenses/LICENSE-2.0
[11]: https://github.com/exasol/error-reporting-java/
[12]: https://github.com/exasol/error-reporting-java/blob/main/LICENSE
[13]: https://junit.org/junit5/
[14]: https://www.eclipse.org/legal/epl-v20.html
[15]: https://github.com/mockito/mockito
[16]: https://opensource.org/licenses/MIT
[17]: http://hamcrest.org/JavaHamcrest/
[18]: http://opensource.org/licenses/BSD-3-Clause
[19]: http://www.scalatest.org
[20]: http://www.apache.org/licenses/LICENSE-2.0
[21]: https://www.jqno.nl/equalsverifier
[22]: http://sonarsource.github.io/sonar-scanner-maven/
[23]: http://www.gnu.org/licenses/lgpl.txt
[24]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[25]: https://maven.apache.org/plugins/maven-compiler-plugin/
[26]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[27]: https://www.mojohaus.org/flatten-maven-plugin/
[28]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[29]: https://maven.apache.org/surefire/maven-surefire-plugin/
[30]: https://www.mojohaus.org/versions/versions-maven-plugin/
[31]: http://github.com/davidB/scala-maven-plugin
[32]: http://unlicense.org/
[33]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[34]: https://github.com/itsallcode/openfasttrace-maven-plugin
[35]: https://www.gnu.org/licenses/gpl-3.0.html
[36]: https://github.com/exasol/project-keeper/
[37]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[38]: https://basepom.github.io/duplicate-finder-maven-plugin
[39]: http://www.apache.org/licenses/LICENSE-2.0.html
[40]: https://maven.apache.org/plugins/maven-deploy-plugin/
[41]: https://maven.apache.org/plugins/maven-gpg-plugin/
[42]: https://maven.apache.org/plugins/maven-source-plugin/
[43]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[44]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[45]: http://www.eclipse.org/legal/epl-v10.html
[46]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[47]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[48]: https://www.eclipse.org/legal/epl-2.0/
[49]: https://github.com/exasol/error-code-crawler-maven-plugin/
[50]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[51]: http://zlika.github.io/reproducible-build-maven-plugin
