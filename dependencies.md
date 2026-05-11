<!-- @formatter:off -->
# Dependencies

## Compile Dependencies

| Dependency                 | License                                       |
| -------------------------- | --------------------------------------------- |
| [SLF4J API Module][0]      | [MIT License][1]                              |
| [Apache Parquet Hadoop][2] | [The Apache Software License, Version 2.0][3] |
| Apache Hadoop Client API   | [Apache-2.0][3]                               |
| [error-reporting-java][4]  | [MIT License][5]                              |

## Test Dependencies

| Dependency                                 | License                          |
| ------------------------------------------ | -------------------------------- |
| [SLF4J JDK14 Binding][0]                   | [MIT License][1]                 |
| [JUnit Jupiter (Aggregator)][6]            | [Eclipse Public License v2.0][7] |
| [mockito-core][8]                          | [MIT][9]                         |
| [mockito-junit-jupiter][8]                 | [MIT][9]                         |
| [Hamcrest][10]                             | [BSD-3-Clause][11]               |
| [EqualsVerifier \| release normal jar][12] | [Apache License, Version 2.0][3] |

## Runtime Dependencies

| Dependency                          | License                          |
| ----------------------------------- | -------------------------------- |
| [aircompressor][13]                 | [Apache License 2.0][14]         |
| Apache Hadoop Client Runtime        | [Apache-2.0][3]                  |
| [JCL 1.2 implemented over SLF4J][0] | [Apache License, Version 2.0][3] |

## Plugin Dependencies

| Dependency                                              | License                                     |
| ------------------------------------------------------- | ------------------------------------------- |
| [SonarQube Scanner for Maven][15]                       | [GNU LGPL 3][16]                            |
| [Apache Maven Toolchains Plugin][17]                    | [Apache-2.0][3]                             |
| [Apache Maven Compiler Plugin][18]                      | [Apache-2.0][3]                             |
| [Apache Maven Enforcer Plugin][19]                      | [Apache-2.0][3]                             |
| [Maven Flatten Plugin][20]                              | [Apache Software License][3]                |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][21] | [ASL2][22]                                  |
| [Maven Surefire Plugin][23]                             | [Apache-2.0][3]                             |
| [Versions Maven Plugin][24]                             | [Apache License, Version 2.0][3]            |
| [Project Keeper Maven plugin][25]                       | [The MIT License][26]                       |
| [duplicate-finder-maven-plugin Maven Mojo][27]          | [Apache License 2.0][28]                    |
| [Apache Maven Artifact Plugin][29]                      | [Apache-2.0][3]                             |
| [Apache Maven Deploy Plugin][30]                        | [Apache-2.0][3]                             |
| [Apache Maven GPG Plugin][31]                           | [Apache-2.0][3]                             |
| [Apache Maven Source Plugin][32]                        | [Apache-2.0][3]                             |
| [OpenFastTrace Maven Plugin][33]                        | [GNU General Public License v3.0][34]       |
| [Apache Maven Javadoc Plugin][35]                       | [Apache-2.0][3]                             |
| [Central Publishing Maven Plugin][36]                   | [The Apache License, Version 2.0][3]        |
| [Maven Failsafe Plugin][37]                             | [Apache-2.0][3]                             |
| [JaCoCo :: Maven Plugin][38]                            | [EPL-2.0][39]                               |
| [Quality Summarizer Maven Plugin][40]                   | [MIT License][41]                           |
| [error-code-crawler-maven-plugin][42]                   | [MIT License][43]                           |
| [Git Commit Id Maven Plugin][44]                        | [GNU Lesser General Public License 3.0][45] |
| [Apache Maven Clean Plugin][46]                         | [Apache-2.0][3]                             |
| [Apache Maven Resources Plugin][47]                     | [Apache-2.0][3]                             |
| [Apache Maven Install Plugin][48]                       | [Apache-2.0][3]                             |
| [Apache Maven Site Plugin][49]                          | [Apache-2.0][3]                             |

[0]: http://www.slf4j.org
[1]: http://www.opensource.org/licenses/mit-license.php
[2]: https://parquet.apache.org
[3]: https://www.apache.org/licenses/LICENSE-2.0.txt
[4]: https://github.com/exasol/error-reporting-java/
[5]: https://github.com/exasol/error-reporting-java/blob/main/LICENSE
[6]: https://junit.org/
[7]: https://www.eclipse.org/legal/epl-v20.html
[8]: https://github.com/mockito/mockito
[9]: https://opensource.org/licenses/MIT
[10]: http://hamcrest.org/JavaHamcrest/
[11]: https://raw.githubusercontent.com/hamcrest/JavaHamcrest/master/LICENSE
[12]: https://www.jqno.nl/equalsverifier
[13]: https://github.com/airlift/aircompressor
[14]: https://www.apache.org/licenses/LICENSE-2.0.html
[15]: https://docs.sonarsource.com/sonarqube-server/latest/extension-guide/developing-a-plugin/plugin-basics/sonar-scanner-maven/sonar-maven-plugin/
[16]: http://www.gnu.org/licenses/lgpl.txt
[17]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[18]: https://maven.apache.org/plugins/maven-compiler-plugin/
[19]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[20]: https://www.mojohaus.org/flatten-maven-plugin/
[21]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[22]: http://www.apache.org/licenses/LICENSE-2.0.txt
[23]: https://maven.apache.org/surefire/maven-surefire-plugin/
[24]: https://www.mojohaus.org/versions/versions-maven-plugin/
[25]: https://github.com/exasol/project-keeper/
[26]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[27]: https://basepom.github.io/duplicate-finder-maven-plugin
[28]: http://www.apache.org/licenses/LICENSE-2.0.html
[29]: https://maven.apache.org/plugins/maven-artifact-plugin/
[30]: https://maven.apache.org/plugins/maven-deploy-plugin/
[31]: https://maven.apache.org/plugins/maven-gpg-plugin/
[32]: https://maven.apache.org/plugins/maven-source-plugin/
[33]: https://github.com/itsallcode/openfasttrace-maven-plugin
[34]: https://www.gnu.org/licenses/gpl-3.0.html
[35]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[36]: https://central.sonatype.org
[37]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[38]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[39]: https://www.eclipse.org/legal/epl-2.0/
[40]: https://github.com/exasol/quality-summarizer-maven-plugin/
[41]: https://github.com/exasol/quality-summarizer-maven-plugin/blob/main/LICENSE
[42]: https://github.com/exasol/error-code-crawler-maven-plugin/
[43]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[44]: https://github.com/git-commit-id/git-commit-id-maven-plugin
[45]: http://www.gnu.org/licenses/lgpl-3.0.txt
[46]: https://maven.apache.org/plugins/maven-clean-plugin/
[47]: https://maven.apache.org/plugins/maven-resources-plugin/
[48]: https://maven.apache.org/plugins/maven-install-plugin/
[49]: https://maven.apache.org/plugins/maven-site-plugin/
