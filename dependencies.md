<!-- @formatter:off -->
# Dependencies

## Compile Dependencies

| Dependency                 | License                                       |
| -------------------------- | --------------------------------------------- |
| [SLF4J API Module][0]      | [MIT License][1]                              |
| [Apache Parquet Hadoop][2] | [The Apache Software License, Version 2.0][3] |
| Apache Hadoop Client API   | [Apache-2.0][3]                               |
| [Scala Library][4]         | [Apache-2.0][5]                               |
| [error-reporting-java][6]  | [MIT License][7]                              |

## Test Dependencies

| Dependency                                 | License                                   |
| ------------------------------------------ | ----------------------------------------- |
| [SLF4J JDK14 Binding][0]                   | [MIT License][1]                          |
| [JUnit Jupiter (Aggregator)][8]            | [Eclipse Public License v2.0][9]          |
| [mockito-core][10]                         | [MIT][11]                                 |
| [mockito-junit-jupiter][10]                | [MIT][11]                                 |
| [Hamcrest][12]                             | [BSD-3-Clause][13]                        |
| [scalatest][14]                            | [the Apache License, ASL Version 2.0][15] |
| [EqualsVerifier \| release normal jar][16] | [Apache License, Version 2.0][3]          |

## Runtime Dependencies

| Dependency                          | License                          |
| ----------------------------------- | -------------------------------- |
| Apache Hadoop Client Runtime        | [Apache-2.0][3]                  |
| [JCL 1.2 implemented over SLF4J][0] | [Apache License, Version 2.0][3] |

## Plugin Dependencies

| Dependency                                              | License                                     |
| ------------------------------------------------------- | ------------------------------------------- |
| [SonarQube Scanner for Maven][17]                       | [GNU LGPL 3][18]                            |
| [Apache Maven Toolchains Plugin][19]                    | [Apache-2.0][3]                             |
| [Apache Maven Compiler Plugin][20]                      | [Apache-2.0][3]                             |
| [Apache Maven Enforcer Plugin][21]                      | [Apache-2.0][3]                             |
| [Maven Flatten Plugin][22]                              | [Apache Software License][3]                |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][23] | [ASL2][24]                                  |
| [Maven Surefire Plugin][25]                             | [Apache-2.0][3]                             |
| [Versions Maven Plugin][26]                             | [Apache License, Version 2.0][3]            |
| [scala-maven-plugin][27]                                | [Public domain (Unlicense)][28]             |
| [ScalaTest Maven Plugin][29]                            | [the Apache License, ASL Version 2.0][15]   |
| [OpenFastTrace Maven Plugin][30]                        | [GNU General Public License v3.0][31]       |
| [Project Keeper Maven plugin][32]                       | [The MIT License][33]                       |
| [duplicate-finder-maven-plugin Maven Mojo][34]          | [Apache License 2.0][35]                    |
| [Apache Maven Artifact Plugin][36]                      | [Apache-2.0][3]                             |
| [Apache Maven Deploy Plugin][37]                        | [Apache-2.0][3]                             |
| [Apache Maven GPG Plugin][38]                           | [Apache-2.0][3]                             |
| [Apache Maven Source Plugin][39]                        | [Apache-2.0][3]                             |
| [Apache Maven Javadoc Plugin][40]                       | [Apache-2.0][3]                             |
| [Central Publishing Maven Plugin][41]                   | [The Apache License, Version 2.0][3]        |
| [Maven Failsafe Plugin][42]                             | [Apache-2.0][3]                             |
| [JaCoCo :: Maven Plugin][43]                            | [EPL-2.0][44]                               |
| [Quality Summarizer Maven Plugin][45]                   | [MIT License][46]                           |
| [error-code-crawler-maven-plugin][47]                   | [MIT License][48]                           |
| [Git Commit Id Maven Plugin][49]                        | [GNU Lesser General Public License 3.0][50] |
| [Apache Maven Clean Plugin][51]                         | [Apache-2.0][3]                             |
| [Apache Maven Resources Plugin][52]                     | [Apache-2.0][3]                             |
| [Apache Maven Install Plugin][53]                       | [Apache-2.0][3]                             |
| [Apache Maven Site Plugin][54]                          | [Apache-2.0][3]                             |

[0]: http://www.slf4j.org
[1]: http://www.opensource.org/licenses/mit-license.php
[2]: https://parquet.apache.org
[3]: https://www.apache.org/licenses/LICENSE-2.0.txt
[4]: https://www.scala-lang.org/
[5]: https://www.apache.org/licenses/LICENSE-2.0
[6]: https://github.com/exasol/error-reporting-java/
[7]: https://github.com/exasol/error-reporting-java/blob/main/LICENSE
[8]: https://junit.org/
[9]: https://www.eclipse.org/legal/epl-v20.html
[10]: https://github.com/mockito/mockito
[11]: https://opensource.org/licenses/MIT
[12]: http://hamcrest.org/JavaHamcrest/
[13]: https://raw.githubusercontent.com/hamcrest/JavaHamcrest/master/LICENSE
[14]: http://www.scalatest.org
[15]: http://www.apache.org/licenses/LICENSE-2.0
[16]: https://www.jqno.nl/equalsverifier
[17]: https://docs.sonarsource.com/sonarqube-server/latest/extension-guide/developing-a-plugin/plugin-basics/sonar-scanner-maven/sonar-maven-plugin/
[18]: http://www.gnu.org/licenses/lgpl.txt
[19]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[20]: https://maven.apache.org/plugins/maven-compiler-plugin/
[21]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[22]: https://www.mojohaus.org/flatten-maven-plugin/
[23]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[24]: http://www.apache.org/licenses/LICENSE-2.0.txt
[25]: https://maven.apache.org/surefire/maven-surefire-plugin/
[26]: https://www.mojohaus.org/versions/versions-maven-plugin/
[27]: https://github.com/davidB/scala-maven-plugin
[28]: https://unlicense.org/
[29]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[30]: https://github.com/itsallcode/openfasttrace-maven-plugin
[31]: https://www.gnu.org/licenses/gpl-3.0.html
[32]: https://github.com/exasol/project-keeper/
[33]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[34]: https://basepom.github.io/duplicate-finder-maven-plugin
[35]: http://www.apache.org/licenses/LICENSE-2.0.html
[36]: https://maven.apache.org/plugins/maven-artifact-plugin/
[37]: https://maven.apache.org/plugins/maven-deploy-plugin/
[38]: https://maven.apache.org/plugins/maven-gpg-plugin/
[39]: https://maven.apache.org/plugins/maven-source-plugin/
[40]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[41]: https://central.sonatype.org
[42]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[43]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[44]: https://www.eclipse.org/legal/epl-2.0/
[45]: https://github.com/exasol/quality-summarizer-maven-plugin/
[46]: https://github.com/exasol/quality-summarizer-maven-plugin/blob/main/LICENSE
[47]: https://github.com/exasol/error-code-crawler-maven-plugin/
[48]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[49]: https://github.com/git-commit-id/git-commit-id-maven-plugin
[50]: http://www.gnu.org/licenses/lgpl-3.0.txt
[51]: https://maven.apache.org/plugins/maven-clean-plugin/
[52]: https://maven.apache.org/plugins/maven-resources-plugin/
[53]: https://maven.apache.org/plugins/maven-install-plugin/
[54]: https://maven.apache.org/plugins/maven-site-plugin/
