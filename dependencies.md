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
| [aircompressor][17]                 | [Apache License 2.0][18]         |
| Apache Hadoop Client Runtime        | [Apache-2.0][3]                  |
| [JCL 1.2 implemented over SLF4J][0] | [Apache License, Version 2.0][3] |

## Plugin Dependencies

| Dependency                                              | License                                     |
| ------------------------------------------------------- | ------------------------------------------- |
| [SonarQube Scanner for Maven][19]                       | [GNU LGPL 3][20]                            |
| [Apache Maven Toolchains Plugin][21]                    | [Apache-2.0][3]                             |
| [Apache Maven Compiler Plugin][22]                      | [Apache-2.0][3]                             |
| [Apache Maven Enforcer Plugin][23]                      | [Apache-2.0][3]                             |
| [Maven Flatten Plugin][24]                              | [Apache Software License][3]                |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][25] | [ASL2][26]                                  |
| [Maven Surefire Plugin][27]                             | [Apache-2.0][3]                             |
| [Versions Maven Plugin][28]                             | [Apache License, Version 2.0][3]            |
| [scala-maven-plugin][29]                                | [Public domain (Unlicense)][30]             |
| [ScalaTest Maven Plugin][31]                            | [the Apache License, ASL Version 2.0][15]   |
| [OpenFastTrace Maven Plugin][32]                        | [GNU General Public License v3.0][33]       |
| [Project Keeper Maven plugin][34]                       | [The MIT License][35]                       |
| [duplicate-finder-maven-plugin Maven Mojo][36]          | [Apache License 2.0][37]                    |
| [Apache Maven Artifact Plugin][38]                      | [Apache-2.0][3]                             |
| [Apache Maven Deploy Plugin][39]                        | [Apache-2.0][3]                             |
| [Apache Maven GPG Plugin][40]                           | [Apache-2.0][3]                             |
| [Apache Maven Source Plugin][41]                        | [Apache-2.0][3]                             |
| [Apache Maven Javadoc Plugin][42]                       | [Apache-2.0][3]                             |
| [Central Publishing Maven Plugin][43]                   | [The Apache License, Version 2.0][3]        |
| [Maven Failsafe Plugin][44]                             | [Apache-2.0][3]                             |
| [JaCoCo :: Maven Plugin][45]                            | [EPL-2.0][46]                               |
| [Quality Summarizer Maven Plugin][47]                   | [MIT License][48]                           |
| [error-code-crawler-maven-plugin][49]                   | [MIT License][50]                           |
| [Git Commit Id Maven Plugin][51]                        | [GNU Lesser General Public License 3.0][52] |
| [Apache Maven Clean Plugin][53]                         | [Apache-2.0][3]                             |
| [Apache Maven Resources Plugin][54]                     | [Apache-2.0][3]                             |
| [Apache Maven Install Plugin][55]                       | [Apache-2.0][3]                             |
| [Apache Maven Site Plugin][56]                          | [Apache-2.0][3]                             |

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
[17]: https://github.com/airlift/aircompressor
[18]: https://www.apache.org/licenses/LICENSE-2.0.html
[19]: https://docs.sonarsource.com/sonarqube-server/latest/extension-guide/developing-a-plugin/plugin-basics/sonar-scanner-maven/sonar-maven-plugin/
[20]: http://www.gnu.org/licenses/lgpl.txt
[21]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[22]: https://maven.apache.org/plugins/maven-compiler-plugin/
[23]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[24]: https://www.mojohaus.org/flatten-maven-plugin/
[25]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[26]: http://www.apache.org/licenses/LICENSE-2.0.txt
[27]: https://maven.apache.org/surefire/maven-surefire-plugin/
[28]: https://www.mojohaus.org/versions/versions-maven-plugin/
[29]: https://github.com/davidB/scala-maven-plugin
[30]: https://unlicense.org/
[31]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[32]: https://github.com/itsallcode/openfasttrace-maven-plugin
[33]: https://www.gnu.org/licenses/gpl-3.0.html
[34]: https://github.com/exasol/project-keeper/
[35]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[36]: https://basepom.github.io/duplicate-finder-maven-plugin
[37]: http://www.apache.org/licenses/LICENSE-2.0.html
[38]: https://maven.apache.org/plugins/maven-artifact-plugin/
[39]: https://maven.apache.org/plugins/maven-deploy-plugin/
[40]: https://maven.apache.org/plugins/maven-gpg-plugin/
[41]: https://maven.apache.org/plugins/maven-source-plugin/
[42]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[43]: https://central.sonatype.org
[44]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[45]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[46]: https://www.eclipse.org/legal/epl-2.0/
[47]: https://github.com/exasol/quality-summarizer-maven-plugin/
[48]: https://github.com/exasol/quality-summarizer-maven-plugin/blob/main/LICENSE
[49]: https://github.com/exasol/error-code-crawler-maven-plugin/
[50]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[51]: https://github.com/git-commit-id/git-commit-id-maven-plugin
[52]: http://www.gnu.org/licenses/lgpl-3.0.txt
[53]: https://maven.apache.org/plugins/maven-clean-plugin/
[54]: https://maven.apache.org/plugins/maven-resources-plugin/
[55]: https://maven.apache.org/plugins/maven-install-plugin/
[56]: https://maven.apache.org/plugins/maven-site-plugin/
