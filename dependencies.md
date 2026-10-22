<!-- @formatter:off -->
# Dependencies

## Compile Dependencies

| Dependency                 | License                                       |
| -------------------------- | --------------------------------------------- |
| [Apache Parquet Hadoop][0] | [The Apache Software License, Version 2.0][1] |
| Apache Hadoop Client API   | [Apache-2.0][1]                               |
| [snappy-java][2]           | [Apache-2.0][3]                               |
| [error-reporting-java][4]  | [MIT License][5]                              |

## Test Dependencies

| Dependency                                 | License                          |
| ------------------------------------------ | -------------------------------- |
| [SLF4J JDK14 Provider][6]                  | [MIT][7]                         |
| [JUnit Jupiter (Aggregator)][8]            | [Eclipse Public License v2.0][9] |
| [mockito-core][10]                         | [MIT][11]                        |
| [mockito-junit-jupiter][10]                | [MIT][11]                        |
| [Hamcrest][12]                             | [BSD-3-Clause][13]               |
| [EqualsVerifier \| release normal jar][14] | [Apache License, Version 2.0][1] |

## Runtime Dependencies

| Dependency                          | License                 |
| ----------------------------------- | ----------------------- |
| [aircompressor][15]                 | [Apache License 2.0][3] |
| Apache Hadoop Client Runtime        | [Apache-2.0][1]         |
| [JCL 1.2 implemented over SLF4J][6] | [Apache-2.0][1]         |

## Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [SonarQube Scanner for Maven][16]                       | [GNU LGPL 3][17]                               |
| [Apache Maven Toolchains Plugin][18]                    | [Apache-2.0][1]                                |
| [Apache Maven Compiler Plugin][19]                      | [Apache-2.0][1]                                |
| [Apache Maven Enforcer Plugin][20]                      | [Apache-2.0][1]                                |
| [Maven Flatten Plugin][21]                              | [Apache Software License][1]                   |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][22] | [ASL2][23]                                     |
| [Maven Surefire Plugin][24]                             | [Apache-2.0][1]                                |
| [Versions Maven Plugin][25]                             | [Apache License, Version 2.0][1]               |
| [OpenFastTrace Maven Plugin][26]                        | [GNU General Public License v3.0][27]          |
| [Project Keeper Maven plugin][28]                       | [The MIT License][29]                          |
| [duplicate-finder-maven-plugin Maven Mojo][30]          | [Apache License 2.0][31]                       |
| [Apache Maven Artifact Plugin][32]                      | [Apache-2.0][1]                                |
| [Apache Maven Deploy Plugin][33]                        | [Apache-2.0][1]                                |
| [Apache Maven Source Plugin][34]                        | [Apache-2.0][1]                                |
| [Apache Maven Javadoc Plugin][35]                       | [Apache-2.0][1]                                |
| [spdx-maven-plugin Maven Plugin][36]                    | [The Apache Software License, Version 2.0][23] |
| [Build Helper Maven Plugin][37]                         | [The MIT License][38]                          |
| [Apache Maven GPG Plugin][39]                           | [Apache-2.0][1]                                |
| [Central Publishing Maven Plugin][40]                   | [The Apache License, Version 2.0][1]           |
| [JaCoCo :: Maven Plugin][41]                            | [EPL-2.0][42]                                  |
| [error-code-crawler-maven-plugin][43]                   | [MIT License][44]                              |
| [Git Commit Id Maven Plugin][45]                        | [GNU Lesser General Public License 3.0][46]    |
| [Apache Maven Clean Plugin][47]                         | [Apache-2.0][1]                                |
| [Apache Maven Resources Plugin][48]                     | [Apache-2.0][1]                                |
| [Apache Maven Install Plugin][49]                       | [Apache-2.0][1]                                |
| [Apache Maven Site Plugin][50]                          | [Apache-2.0][1]                                |

[0]: https://parquet.apache.org
[1]: https://www.apache.org/licenses/LICENSE-2.0.txt
[2]: https://github.com/xerial/snappy-java
[3]: https://www.apache.org/licenses/LICENSE-2.0.html
[4]: https://github.com/exasol/error-reporting-java/
[5]: https://github.com/exasol/error-reporting-java/blob/main/LICENSE
[6]: http://www.slf4j.org
[7]: https://opensource.org/license/mit
[8]: https://junit.org/
[9]: https://www.eclipse.org/legal/epl-v20.html
[10]: https://github.com/mockito/mockito
[11]: https://opensource.org/licenses/MIT
[12]: http://hamcrest.org/JavaHamcrest/
[13]: https://raw.githubusercontent.com/hamcrest/JavaHamcrest/master/LICENSE
[14]: https://www.jqno.nl/equalsverifier
[15]: https://github.com/airlift/aircompressor
[16]: https://docs.sonarsource.com/sonarqube-server/latest/extension-guide/developing-a-plugin/plugin-basics/sonar-scanner-maven/sonar-maven-plugin/
[17]: http://www.gnu.org/licenses/lgpl.txt
[18]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[19]: https://maven.apache.org/plugins/maven-compiler-plugin/
[20]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[21]: https://www.mojohaus.org/flatten-maven-plugin/
[22]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[23]: http://www.apache.org/licenses/LICENSE-2.0.txt
[24]: https://maven.apache.org/surefire/maven-surefire-plugin/
[25]: https://www.mojohaus.org/versions/versions-maven-plugin/
[26]: https://github.com/itsallcode/openfasttrace-maven-plugin
[27]: https://www.gnu.org/licenses/gpl-3.0.html
[28]: https://github.com/exasol/project-keeper/
[29]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[30]: https://basepom.github.io/duplicate-finder-maven-plugin
[31]: http://www.apache.org/licenses/LICENSE-2.0.html
[32]: https://maven.apache.org/plugins/maven-artifact-plugin/
[33]: https://maven.apache.org/plugins/maven-deploy-plugin/
[34]: https://maven.apache.org/plugins/maven-source-plugin/
[35]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[36]: https://github.com/spdx/spdx-maven-plugin
[37]: https://www.mojohaus.org/build-helper-maven-plugin/
[38]: https://spdx.org/licenses/MIT.txt
[39]: https://maven.apache.org/plugins/maven-gpg-plugin/
[40]: https://central.sonatype.org
[41]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[42]: https://www.eclipse.org/legal/epl-2.0/
[43]: https://github.com/exasol/error-code-crawler-maven-plugin/
[44]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[45]: https://github.com/git-commit-id/git-commit-id-maven-plugin
[46]: http://www.gnu.org/licenses/lgpl-3.0.txt
[47]: https://maven.apache.org/plugins/maven-clean-plugin/
[48]: https://maven.apache.org/plugins/maven-resources-plugin/
[49]: https://maven.apache.org/plugins/maven-install-plugin/
[50]: https://maven.apache.org/plugins/maven-site-plugin/
