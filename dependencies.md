<!-- @formatter:off -->
# Dependencies

## Compile Dependencies

| Dependency                        | License                                       |
| --------------------------------- | --------------------------------------------- |
| [Apache Parquet Hadoop][0]        | [The Apache Software License, Version 2.0][1] |
| Apache Hadoop Client Aggregator   | [Apache-2.0][2]                               |
| [Apache Avro][3]                  | [Apache-2.0][2]                               |
| [Apache Commons Configuration][4] | [Apache-2.0][2]                               |
| [dnsjava][5]                      | [BSD-3-Clause][6]                             |
| [Scala Library][7]                | [Apache-2.0][8]                               |
| [error-reporting-java][9]         | [MIT License][10]                             |

## Test Dependencies

| Dependency                                 | License                                   |
| ------------------------------------------ | ----------------------------------------- |
| [JUnit Jupiter (Aggregator)][11]           | [Eclipse Public License v2.0][12]         |
| [mockito-core][13]                         | [MIT][14]                                 |
| [mockito-junit-jupiter][13]                | [MIT][14]                                 |
| [Hamcrest][15]                             | [BSD-3-Clause][16]                        |
| [scalatest][17]                            | [the Apache License, ASL Version 2.0][18] |
| [EqualsVerifier \| release normal jar][19] | [Apache License, Version 2.0][2]          |

## Runtime Dependencies

| Dependency                         | License                          |
| ---------------------------------- | -------------------------------- |
| [Netty/Transport/Native/Epoll][20] | [Apache License, Version 2.0][8] |

## Plugin Dependencies

| Dependency                                              | License                                   |
| ------------------------------------------------------- | ----------------------------------------- |
| [Apache Maven Clean Plugin][21]                         | [Apache-2.0][2]                           |
| [Apache Maven Install Plugin][22]                       | [Apache-2.0][2]                           |
| [Apache Maven Resources Plugin][23]                     | [Apache-2.0][2]                           |
| [Apache Maven Site Plugin][24]                          | [Apache License, Version 2.0][2]          |
| [SonarQube Scanner for Maven][25]                       | [GNU LGPL 3][26]                          |
| [Apache Maven Toolchains Plugin][27]                    | [Apache-2.0][2]                           |
| [Apache Maven Compiler Plugin][28]                      | [Apache-2.0][2]                           |
| [Apache Maven Enforcer Plugin][29]                      | [Apache-2.0][2]                           |
| [Maven Flatten Plugin][30]                              | [Apache Software Licenese][2]             |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][31] | [ASL2][1]                                 |
| [Maven Surefire Plugin][32]                             | [Apache-2.0][2]                           |
| [Versions Maven Plugin][33]                             | [Apache License, Version 2.0][2]          |
| [scala-maven-plugin][34]                                | [Public domain (Unlicense)][35]           |
| [ScalaTest Maven Plugin][36]                            | [the Apache License, ASL Version 2.0][18] |
| [OpenFastTrace Maven Plugin][37]                        | [GNU General Public License v3.0][38]     |
| [Project Keeper Maven plugin][39]                       | [The MIT License][40]                     |
| [duplicate-finder-maven-plugin Maven Mojo][41]          | [Apache License 2.0][42]                  |
| [Apache Maven Deploy Plugin][43]                        | [Apache-2.0][2]                           |
| [Apache Maven GPG Plugin][44]                           | [Apache-2.0][2]                           |
| [Apache Maven Source Plugin][45]                        | [Apache License, Version 2.0][2]          |
| [Apache Maven Javadoc Plugin][46]                       | [Apache-2.0][2]                           |
| [Nexus Staging Maven Plugin][47]                        | [Eclipse Public License][48]              |
| [Maven Failsafe Plugin][49]                             | [Apache-2.0][2]                           |
| [JaCoCo :: Maven Plugin][50]                            | [EPL-2.0][51]                             |
| [Quality Summarizer Maven Plugin][52]                   | [MIT License][53]                         |
| [error-code-crawler-maven-plugin][54]                   | [MIT License][55]                         |
| [Reproducible Build Maven Plugin][56]                   | [Apache 2.0][1]                           |

[0]: https://parquet.apache.org
[1]: http://www.apache.org/licenses/LICENSE-2.0.txt
[2]: https://www.apache.org/licenses/LICENSE-2.0.txt
[3]: https://avro.apache.org
[4]: https://commons.apache.org/proper/commons-configuration/
[5]: https://github.com/dnsjava/dnsjava
[6]: https://opensource.org/licenses/BSD-3-Clause
[7]: https://www.scala-lang.org/
[8]: https://www.apache.org/licenses/LICENSE-2.0
[9]: https://github.com/exasol/error-reporting-java/
[10]: https://github.com/exasol/error-reporting-java/blob/main/LICENSE
[11]: https://junit.org/junit5/
[12]: https://www.eclipse.org/legal/epl-v20.html
[13]: https://github.com/mockito/mockito
[14]: https://opensource.org/licenses/MIT
[15]: http://hamcrest.org/JavaHamcrest/
[16]: https://raw.githubusercontent.com/hamcrest/JavaHamcrest/master/LICENSE
[17]: http://www.scalatest.org
[18]: http://www.apache.org/licenses/LICENSE-2.0
[19]: https://www.jqno.nl/equalsverifier
[20]: https://netty.io/netty-transport-native-epoll/
[21]: https://maven.apache.org/plugins/maven-clean-plugin/
[22]: https://maven.apache.org/plugins/maven-install-plugin/
[23]: https://maven.apache.org/plugins/maven-resources-plugin/
[24]: https://maven.apache.org/plugins/maven-site-plugin/
[25]: http://sonarsource.github.io/sonar-scanner-maven/
[26]: http://www.gnu.org/licenses/lgpl.txt
[27]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[28]: https://maven.apache.org/plugins/maven-compiler-plugin/
[29]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[30]: https://www.mojohaus.org/flatten-maven-plugin/
[31]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[32]: https://maven.apache.org/surefire/maven-surefire-plugin/
[33]: https://www.mojohaus.org/versions/versions-maven-plugin/
[34]: http://github.com/davidB/scala-maven-plugin
[35]: http://unlicense.org/
[36]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[37]: https://github.com/itsallcode/openfasttrace-maven-plugin
[38]: https://www.gnu.org/licenses/gpl-3.0.html
[39]: https://github.com/exasol/project-keeper/
[40]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[41]: https://basepom.github.io/duplicate-finder-maven-plugin
[42]: http://www.apache.org/licenses/LICENSE-2.0.html
[43]: https://maven.apache.org/plugins/maven-deploy-plugin/
[44]: https://maven.apache.org/plugins/maven-gpg-plugin/
[45]: https://maven.apache.org/plugins/maven-source-plugin/
[46]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[47]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[48]: http://www.eclipse.org/legal/epl-v10.html
[49]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[50]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[51]: https://www.eclipse.org/legal/epl-2.0/
[52]: https://github.com/exasol/quality-summarizer-maven-plugin/
[53]: https://github.com/exasol/quality-summarizer-maven-plugin/blob/main/LICENSE
[54]: https://github.com/exasol/error-code-crawler-maven-plugin/
[55]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[56]: http://zlika.github.io/reproducible-build-maven-plugin
