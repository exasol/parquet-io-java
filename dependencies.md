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
| [dnsjava][9]                      | [BSD-3-Clause][10]                            |
| [Scala Library][11]               | [Apache-2.0][12]                              |
| [error-reporting-java][13]        | [MIT License][14]                             |

## Test Dependencies

| Dependency                                 | License                                   |
| ------------------------------------------ | ----------------------------------------- |
| [JUnit Jupiter (Aggregator)][15]           | [Eclipse Public License v2.0][16]         |
| [mockito-core][17]                         | [MIT][18]                                 |
| [mockito-junit-jupiter][17]                | [MIT][18]                                 |
| [Hamcrest][19]                             | [BSD-3-Clause][20]                        |
| [scalatest][21]                            | [the Apache License, ASL Version 2.0][22] |
| [EqualsVerifier \| release normal jar][23] | [Apache License, Version 2.0][5]          |

## Plugin Dependencies

| Dependency                                              | License                                   |
| ------------------------------------------------------- | ----------------------------------------- |
| [SonarQube Scanner for Maven][24]                       | [GNU LGPL 3][25]                          |
| [Apache Maven Toolchains Plugin][26]                    | [Apache-2.0][5]                           |
| [Apache Maven Compiler Plugin][27]                      | [Apache-2.0][5]                           |
| [Apache Maven Enforcer Plugin][28]                      | [Apache-2.0][5]                           |
| [Maven Flatten Plugin][29]                              | [Apache Software Licenese][5]             |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][30] | [ASL2][1]                                 |
| [Maven Surefire Plugin][31]                             | [Apache-2.0][5]                           |
| [Versions Maven Plugin][32]                             | [Apache License, Version 2.0][5]          |
| [scala-maven-plugin][33]                                | [Public domain (Unlicense)][34]           |
| [ScalaTest Maven Plugin][35]                            | [the Apache License, ASL Version 2.0][22] |
| [OpenFastTrace Maven Plugin][36]                        | [GNU General Public License v3.0][37]     |
| [Project Keeper Maven plugin][38]                       | [The MIT License][39]                     |
| [duplicate-finder-maven-plugin Maven Mojo][40]          | [Apache License 2.0][41]                  |
| [Apache Maven Deploy Plugin][42]                        | [Apache-2.0][5]                           |
| [Apache Maven GPG Plugin][43]                           | [Apache-2.0][5]                           |
| [Apache Maven Source Plugin][44]                        | [Apache License, Version 2.0][5]          |
| [Apache Maven Javadoc Plugin][45]                       | [Apache-2.0][5]                           |
| [Nexus Staging Maven Plugin][46]                        | [Eclipse Public License][47]              |
| [Maven Failsafe Plugin][48]                             | [Apache-2.0][5]                           |
| [JaCoCo :: Maven Plugin][49]                            | [EPL-2.0][50]                             |
| [error-code-crawler-maven-plugin][51]                   | [MIT License][52]                         |
| [Reproducible Build Maven Plugin][53]                   | [Apache 2.0][1]                           |

[0]: https://parquet.apache.org
[1]: http://www.apache.org/licenses/LICENSE-2.0.txt
[2]: https://github.com/airlift/aircompressor
[3]: https://www.apache.org/licenses/LICENSE-2.0.html
[4]: https://github.com/xerial/snappy-java
[5]: https://www.apache.org/licenses/LICENSE-2.0.txt
[6]: https://avro.apache.org
[7]: https://commons.apache.org/proper/commons-compress/
[8]: https://commons.apache.org/proper/commons-configuration/
[9]: https://github.com/dnsjava/dnsjava
[10]: https://opensource.org/licenses/BSD-3-Clause
[11]: https://www.scala-lang.org/
[12]: https://www.apache.org/licenses/LICENSE-2.0
[13]: https://github.com/exasol/error-reporting-java/
[14]: https://github.com/exasol/error-reporting-java/blob/main/LICENSE
[15]: https://junit.org/junit5/
[16]: https://www.eclipse.org/legal/epl-v20.html
[17]: https://github.com/mockito/mockito
[18]: https://opensource.org/licenses/MIT
[19]: http://hamcrest.org/JavaHamcrest/
[20]: https://raw.githubusercontent.com/hamcrest/JavaHamcrest/master/LICENSE
[21]: http://www.scalatest.org
[22]: http://www.apache.org/licenses/LICENSE-2.0
[23]: https://www.jqno.nl/equalsverifier
[24]: http://sonarsource.github.io/sonar-scanner-maven/
[25]: http://www.gnu.org/licenses/lgpl.txt
[26]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[27]: https://maven.apache.org/plugins/maven-compiler-plugin/
[28]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[29]: https://www.mojohaus.org/flatten-maven-plugin/
[30]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[31]: https://maven.apache.org/surefire/maven-surefire-plugin/
[32]: https://www.mojohaus.org/versions/versions-maven-plugin/
[33]: http://github.com/davidB/scala-maven-plugin
[34]: http://unlicense.org/
[35]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[36]: https://github.com/itsallcode/openfasttrace-maven-plugin
[37]: https://www.gnu.org/licenses/gpl-3.0.html
[38]: https://github.com/exasol/project-keeper/
[39]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[40]: https://basepom.github.io/duplicate-finder-maven-plugin
[41]: http://www.apache.org/licenses/LICENSE-2.0.html
[42]: https://maven.apache.org/plugins/maven-deploy-plugin/
[43]: https://maven.apache.org/plugins/maven-gpg-plugin/
[44]: https://maven.apache.org/plugins/maven-source-plugin/
[45]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[46]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[47]: http://www.eclipse.org/legal/epl-v10.html
[48]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[49]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[50]: https://www.eclipse.org/legal/epl-2.0/
[51]: https://github.com/exasol/error-code-crawler-maven-plugin/
[52]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[53]: http://zlika.github.io/reproducible-build-maven-plugin
