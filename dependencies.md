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

| Dependency                                              | License                                   |
| ------------------------------------------------------- | ----------------------------------------- |
| [Apache Maven Clean Plugin][17]                         | [Apache-2.0][3]                           |
| [Apache Maven Install Plugin][18]                       | [Apache-2.0][3]                           |
| [Apache Maven Resources Plugin][19]                     | [Apache-2.0][3]                           |
| [Apache Maven Site Plugin][20]                          | [Apache-2.0][3]                           |
| [SonarQube Scanner for Maven][21]                       | [GNU LGPL 3][22]                          |
| [Apache Maven Toolchains Plugin][23]                    | [Apache-2.0][3]                           |
| [Apache Maven Compiler Plugin][24]                      | [Apache-2.0][3]                           |
| [Apache Maven Enforcer Plugin][25]                      | [Apache-2.0][3]                           |
| [Maven Flatten Plugin][26]                              | [Apache Software Licenese][3]             |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][27] | [ASL2][28]                                |
| [Maven Surefire Plugin][29]                             | [Apache-2.0][3]                           |
| [Versions Maven Plugin][30]                             | [Apache License, Version 2.0][3]          |
| [scala-maven-plugin][31]                                | [Public domain (Unlicense)][32]           |
| [ScalaTest Maven Plugin][33]                            | [the Apache License, ASL Version 2.0][15] |
| [OpenFastTrace Maven Plugin][34]                        | [GNU General Public License v3.0][35]     |
| [Project Keeper Maven plugin][36]                       | [The MIT License][37]                     |
| [duplicate-finder-maven-plugin Maven Mojo][38]          | [Apache License 2.0][39]                  |
| [Apache Maven Deploy Plugin][40]                        | [Apache-2.0][3]                           |
| [Apache Maven GPG Plugin][41]                           | [Apache-2.0][3]                           |
| [Apache Maven Source Plugin][42]                        | [Apache License, Version 2.0][3]          |
| [Apache Maven Javadoc Plugin][43]                       | [Apache-2.0][3]                           |
| [Nexus Staging Maven Plugin][44]                        | [Eclipse Public License][45]              |
| [Maven Failsafe Plugin][46]                             | [Apache-2.0][3]                           |
| [JaCoCo :: Maven Plugin][47]                            | [EPL-2.0][48]                             |
| [Quality Summarizer Maven Plugin][49]                   | [MIT License][50]                         |
| [error-code-crawler-maven-plugin][51]                   | [MIT License][52]                         |
| [Reproducible Build Maven Plugin][53]                   | [Apache 2.0][28]                          |

[0]: http://www.slf4j.org
[1]: http://www.opensource.org/licenses/mit-license.php
[2]: https://parquet.apache.org
[3]: https://www.apache.org/licenses/LICENSE-2.0.txt
[4]: https://www.scala-lang.org/
[5]: https://www.apache.org/licenses/LICENSE-2.0
[6]: https://github.com/exasol/error-reporting-java/
[7]: https://github.com/exasol/error-reporting-java/blob/main/LICENSE
[8]: https://junit.org/junit5/
[9]: https://www.eclipse.org/legal/epl-v20.html
[10]: https://github.com/mockito/mockito
[11]: https://opensource.org/licenses/MIT
[12]: http://hamcrest.org/JavaHamcrest/
[13]: https://raw.githubusercontent.com/hamcrest/JavaHamcrest/master/LICENSE
[14]: http://www.scalatest.org
[15]: http://www.apache.org/licenses/LICENSE-2.0
[16]: https://www.jqno.nl/equalsverifier
[17]: https://maven.apache.org/plugins/maven-clean-plugin/
[18]: https://maven.apache.org/plugins/maven-install-plugin/
[19]: https://maven.apache.org/plugins/maven-resources-plugin/
[20]: https://maven.apache.org/plugins/maven-site-plugin/
[21]: http://docs.sonarqube.org/display/PLUG/Plugin+Library/sonar-maven-plugin
[22]: http://www.gnu.org/licenses/lgpl.txt
[23]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[24]: https://maven.apache.org/plugins/maven-compiler-plugin/
[25]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[26]: https://www.mojohaus.org/flatten-maven-plugin/
[27]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[28]: http://www.apache.org/licenses/LICENSE-2.0.txt
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
[49]: https://github.com/exasol/quality-summarizer-maven-plugin/
[50]: https://github.com/exasol/quality-summarizer-maven-plugin/blob/main/LICENSE
[51]: https://github.com/exasol/error-code-crawler-maven-plugin/
[52]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[53]: http://zlika.github.io/reproducible-build-maven-plugin
