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
| [JCL 1.2 implemented over SLF4J][0] | [Apache License, Version 2.0][3] |
| [aircompressor][13]                 | [Apache License 2.0][14]         |
| Apache Hadoop Client Runtime        | [Apache-2.0][3]                  |

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
| [OpenFastTrace Maven Plugin][25]                        | [GNU General Public License v3.0][26]       |
| [Project Keeper Maven plugin][27]                       | [The MIT License][28]                       |
| [duplicate-finder-maven-plugin Maven Mojo][29]          | [Apache License 2.0][30]                    |
| [Apache Maven Artifact Plugin][31]                      | [Apache-2.0][3]                             |
| [Apache Maven Deploy Plugin][32]                        | [Apache-2.0][3]                             |
| [Apache Maven GPG Plugin][33]                           | [Apache-2.0][3]                             |
| [Apache Maven Source Plugin][34]                        | [Apache-2.0][3]                             |
| [Apache Maven Javadoc Plugin][35]                       | [Apache-2.0][3]                             |
| [Central Publishing Maven Plugin][36]                   | [The Apache License, Version 2.0][3]        |
| [JaCoCo :: Maven Plugin][37]                            | [EPL-2.0][38]                               |
| [Quality Summarizer Maven Plugin][39]                   | [MIT License][40]                           |
| [error-code-crawler-maven-plugin][41]                   | [MIT License][42]                           |
| [Git Commit Id Maven Plugin][43]                        | [GNU Lesser General Public License 3.0][44] |
| [Apache Maven Clean Plugin][45]                         | [Apache-2.0][3]                             |
| [Apache Maven Resources Plugin][46]                     | [Apache-2.0][3]                             |
| [Apache Maven Install Plugin][47]                       | [Apache-2.0][3]                             |
| [Apache Maven Site Plugin][48]                          | [Apache-2.0][3]                             |

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
[25]: https://github.com/itsallcode/openfasttrace-maven-plugin
[26]: https://www.gnu.org/licenses/gpl-3.0.html
[27]: https://github.com/exasol/project-keeper/
[28]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[29]: https://basepom.github.io/duplicate-finder-maven-plugin
[30]: http://www.apache.org/licenses/LICENSE-2.0.html
[31]: https://maven.apache.org/plugins/maven-artifact-plugin/
[32]: https://maven.apache.org/plugins/maven-deploy-plugin/
[33]: https://maven.apache.org/plugins/maven-gpg-plugin/
[34]: https://maven.apache.org/plugins/maven-source-plugin/
[35]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[36]: https://central.sonatype.org
[37]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[38]: https://www.eclipse.org/legal/epl-2.0/
[39]: https://github.com/exasol/quality-summarizer-maven-plugin/
[40]: https://github.com/exasol/quality-summarizer-maven-plugin/blob/main/LICENSE
[41]: https://github.com/exasol/error-code-crawler-maven-plugin/
[42]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[43]: https://github.com/git-commit-id/git-commit-id-maven-plugin
[44]: http://www.gnu.org/licenses/lgpl-3.0.txt
[45]: https://maven.apache.org/plugins/maven-clean-plugin/
[46]: https://maven.apache.org/plugins/maven-resources-plugin/
[47]: https://maven.apache.org/plugins/maven-install-plugin/
[48]: https://maven.apache.org/plugins/maven-site-plugin/
