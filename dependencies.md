<!-- @formatter:off -->
# Dependencies

## Compile Dependencies

| Dependency                        | License                                       |
| --------------------------------- | --------------------------------------------- |
| [Apache Parquet Hadoop][0]        | [The Apache Software License, Version 2.0][1] |
| [snappy-java][2]                  | [Apache-2.0][3]                               |
| Apache Hadoop Client Aggregator   | [Apache-2.0][4]                               |
| [Apache Avro][5]                  | [Apache-2.0][4]                               |
| [Apache Commons Compress][6]      | [Apache-2.0][4]                               |
| [Apache Commons Configuration][7] | [Apache-2.0][4]                               |
| [Scala Library][8]                | [Apache-2.0][9]                               |
| [error-reporting-java][10]        | [MIT License][11]                             |

## Test Dependencies

| Dependency                                 | License                                   |
| ------------------------------------------ | ----------------------------------------- |
| [JUnit Jupiter (Aggregator)][12]           | [Eclipse Public License v2.0][13]         |
| [mockito-core][14]                         | [MIT][15]                                 |
| [mockito-junit-jupiter][14]                | [MIT][15]                                 |
| [Hamcrest][16]                             | [BSD License 3][17]                       |
| [scalatest][18]                            | [the Apache License, ASL Version 2.0][19] |
| [EqualsVerifier \| release normal jar][20] | [Apache License, Version 2.0][4]          |

## Plugin Dependencies

| Dependency                                              | License                                   |
| ------------------------------------------------------- | ----------------------------------------- |
| [SonarQube Scanner for Maven][21]                       | [GNU LGPL 3][22]                          |
| [Apache Maven Toolchains Plugin][23]                    | [Apache License, Version 2.0][4]          |
| [Apache Maven Compiler Plugin][24]                      | [Apache-2.0][4]                           |
| [Apache Maven Enforcer Plugin][25]                      | [Apache-2.0][4]                           |
| [Maven Flatten Plugin][26]                              | [Apache Software Licenese][4]             |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][27] | [ASL2][1]                                 |
| [Maven Surefire Plugin][28]                             | [Apache-2.0][4]                           |
| [Versions Maven Plugin][29]                             | [Apache License, Version 2.0][4]          |
| [scala-maven-plugin][30]                                | [Public domain (Unlicense)][31]           |
| [ScalaTest Maven Plugin][32]                            | [the Apache License, ASL Version 2.0][19] |
| [OpenFastTrace Maven Plugin][33]                        | [GNU General Public License v3.0][34]     |
| [Project Keeper Maven plugin][35]                       | [The MIT License][36]                     |
| [duplicate-finder-maven-plugin Maven Mojo][37]          | [Apache License 2.0][38]                  |
| [Apache Maven Deploy Plugin][39]                        | [Apache-2.0][4]                           |
| [Apache Maven GPG Plugin][40]                           | [Apache-2.0][4]                           |
| [Apache Maven Source Plugin][41]                        | [Apache License, Version 2.0][4]          |
| [Apache Maven Javadoc Plugin][42]                       | [Apache-2.0][4]                           |
| [Nexus Staging Maven Plugin][43]                        | [Eclipse Public License][44]              |
| [Maven Failsafe Plugin][45]                             | [Apache-2.0][4]                           |
| [JaCoCo :: Maven Plugin][46]                            | [EPL-2.0][47]                             |
| [error-code-crawler-maven-plugin][48]                   | [MIT License][49]                         |
| [Reproducible Build Maven Plugin][50]                   | [Apache 2.0][1]                           |

[0]: https://parquet.apache.org
[1]: http://www.apache.org/licenses/LICENSE-2.0.txt
[2]: https://github.com/xerial/snappy-java
[3]: https://www.apache.org/licenses/LICENSE-2.0.html
[4]: https://www.apache.org/licenses/LICENSE-2.0.txt
[5]: https://avro.apache.org
[6]: https://commons.apache.org/proper/commons-compress/
[7]: https://commons.apache.org/proper/commons-configuration/
[8]: https://www.scala-lang.org/
[9]: https://www.apache.org/licenses/LICENSE-2.0
[10]: https://github.com/exasol/error-reporting-java/
[11]: https://github.com/exasol/error-reporting-java/blob/main/LICENSE
[12]: https://junit.org/junit5/
[13]: https://www.eclipse.org/legal/epl-v20.html
[14]: https://github.com/mockito/mockito
[15]: https://opensource.org/licenses/MIT
[16]: http://hamcrest.org/JavaHamcrest/
[17]: http://opensource.org/licenses/BSD-3-Clause
[18]: http://www.scalatest.org
[19]: http://www.apache.org/licenses/LICENSE-2.0
[20]: https://www.jqno.nl/equalsverifier
[21]: http://sonarsource.github.io/sonar-scanner-maven/
[22]: http://www.gnu.org/licenses/lgpl.txt
[23]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[24]: https://maven.apache.org/plugins/maven-compiler-plugin/
[25]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[26]: https://www.mojohaus.org/flatten-maven-plugin/
[27]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[28]: https://maven.apache.org/surefire/maven-surefire-plugin/
[29]: https://www.mojohaus.org/versions/versions-maven-plugin/
[30]: http://github.com/davidB/scala-maven-plugin
[31]: http://unlicense.org/
[32]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[33]: https://github.com/itsallcode/openfasttrace-maven-plugin
[34]: https://www.gnu.org/licenses/gpl-3.0.html
[35]: https://github.com/exasol/project-keeper/
[36]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[37]: https://basepom.github.io/duplicate-finder-maven-plugin
[38]: http://www.apache.org/licenses/LICENSE-2.0.html
[39]: https://maven.apache.org/plugins/maven-deploy-plugin/
[40]: https://maven.apache.org/plugins/maven-gpg-plugin/
[41]: https://maven.apache.org/plugins/maven-source-plugin/
[42]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[43]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[44]: http://www.eclipse.org/legal/epl-v10.html
[45]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[46]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[47]: https://www.eclipse.org/legal/epl-2.0/
[48]: https://github.com/exasol/error-code-crawler-maven-plugin/
[49]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[50]: http://zlika.github.io/reproducible-build-maven-plugin
