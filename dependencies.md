<!-- @formatter:off -->
# Dependencies

## Compile Dependencies

| Dependency                      | License                                       |
| ------------------------------- | --------------------------------------------- |
| [Apache Parquet Hadoop][0]      | [The Apache Software License, Version 2.0][1] |
| Apache Hadoop Client Aggregator | [Apache License, Version 2.0][2]              |
| [Scala Library][3]              | [Apache-2.0][4]                               |
| [error-reporting-java][5]       | [MIT License][6]                              |

## Test Dependencies

| Dependency                      | License                                   |
| ------------------------------- | ----------------------------------------- |
| [JUnit Jupiter (Aggregator)][7] | [Eclipse Public License v2.0][8]          |
| [mockito-core][9]               | [The MIT License][10]                     |
| [mockito-junit-jupiter][9]      | [The MIT License][10]                     |
| [Hamcrest][11]                  | [BSD License 3][12]                       |
| [scalatest][13]                 | [the Apache License, ASL Version 2.0][14] |

## Plugin Dependencies

| Dependency                                              | License                                       |
| ------------------------------------------------------- | --------------------------------------------- |
| [SonarQube Scanner for Maven][15]                       | [GNU LGPL 3][16]                              |
| [Apache Maven Compiler Plugin][17]                      | [Apache-2.0][2]                               |
| [Apache Maven Enforcer Plugin][18]                      | [Apache-2.0][2]                               |
| [Maven Flatten Plugin][19]                              | [Apache Software Licenese][2]                 |
| [scala-maven-plugin][20]                                | [Public domain (Unlicense)][21]               |
| [ScalaTest Maven Plugin][22]                            | [the Apache License, ASL Version 2.0][14]     |
| [OpenFastTrace Maven Plugin][23]                        | [GNU General Public License v3.0][24]         |
| [Project keeper maven plugin][25]                       | [The MIT License][26]                         |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][27] | [ASL2][1]                                     |
| [Maven Surefire Plugin][28]                             | [Apache-2.0][2]                               |
| [Versions Maven Plugin][29]                             | [Apache License, Version 2.0][2]              |
| [duplicate-finder-maven-plugin Maven Mojo][30]          | [Apache License 2.0][31]                      |
| [Apache Maven Deploy Plugin][32]                        | [Apache-2.0][2]                               |
| [Apache Maven GPG Plugin][33]                           | [Apache License, Version 2.0][2]              |
| [Apache Maven Source Plugin][34]                        | [Apache License, Version 2.0][2]              |
| [Apache Maven Javadoc Plugin][35]                       | [Apache-2.0][2]                               |
| [Nexus Staging Maven Plugin][36]                        | [Eclipse Public License][37]                  |
| [Maven Failsafe Plugin][38]                             | [Apache-2.0][2]                               |
| [JaCoCo :: Maven Plugin][39]                            | [Eclipse Public License 2.0][40]              |
| [error-code-crawler-maven-plugin][41]                   | [MIT License][42]                             |
| [Reproducible Build Maven Plugin][43]                   | [Apache 2.0][1]                               |
| [Maven Clean Plugin][44]                                | [The Apache Software License, Version 2.0][1] |
| [Maven Resources Plugin][45]                            | [The Apache Software License, Version 2.0][1] |
| [Maven JAR Plugin][46]                                  | [The Apache Software License, Version 2.0][1] |
| [Maven Install Plugin][47]                              | [The Apache Software License, Version 2.0][1] |
| [Maven Site Plugin 3][48]                               | [The Apache Software License, Version 2.0][1] |

[0]: https://parquet.apache.org
[1]: http://www.apache.org/licenses/LICENSE-2.0.txt
[2]: https://www.apache.org/licenses/LICENSE-2.0.txt
[3]: https://www.scala-lang.org/
[4]: https://www.apache.org/licenses/LICENSE-2.0
[5]: https://github.com/exasol/error-reporting-java/
[6]: https://github.com/exasol/error-reporting-java/blob/main/LICENSE
[7]: https://junit.org/junit5/
[8]: https://www.eclipse.org/legal/epl-v20.html
[9]: https://github.com/mockito/mockito
[10]: https://github.com/mockito/mockito/blob/main/LICENSE
[11]: http://hamcrest.org/JavaHamcrest/
[12]: http://opensource.org/licenses/BSD-3-Clause
[13]: http://www.scalatest.org
[14]: http://www.apache.org/licenses/LICENSE-2.0
[15]: http://sonarsource.github.io/sonar-scanner-maven/
[16]: http://www.gnu.org/licenses/lgpl.txt
[17]: https://maven.apache.org/plugins/maven-compiler-plugin/
[18]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[19]: https://www.mojohaus.org/flatten-maven-plugin/
[20]: http://github.com/davidB/scala-maven-plugin
[21]: http://unlicense.org/
[22]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[23]: https://github.com/itsallcode/openfasttrace-maven-plugin
[24]: https://www.gnu.org/licenses/gpl-3.0.html
[25]: https://github.com/exasol/project-keeper/
[26]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[27]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[28]: https://maven.apache.org/surefire/maven-surefire-plugin/
[29]: https://www.mojohaus.org/versions/versions-maven-plugin/
[30]: https://github.com/basepom/duplicate-finder-maven-plugin
[31]: http://www.apache.org/licenses/LICENSE-2.0.html
[32]: https://maven.apache.org/plugins/maven-deploy-plugin/
[33]: https://maven.apache.org/plugins/maven-gpg-plugin/
[34]: https://maven.apache.org/plugins/maven-source-plugin/
[35]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[36]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[37]: http://www.eclipse.org/legal/epl-v10.html
[38]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[39]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[40]: https://www.eclipse.org/legal/epl-2.0/
[41]: https://github.com/exasol/error-code-crawler-maven-plugin/
[42]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[43]: http://zlika.github.io/reproducible-build-maven-plugin
[44]: http://maven.apache.org/plugins/maven-clean-plugin/
[45]: http://maven.apache.org/plugins/maven-resources-plugin/
[46]: http://maven.apache.org/plugins/maven-jar-plugin/
[47]: http://maven.apache.org/plugins/maven-install-plugin/
[48]: http://maven.apache.org/plugins/maven-site-plugin/
