<!-- @formatter:off -->
# Dependencies

## Compile Dependencies

| Dependency                      | License                                       |
| ------------------------------- | --------------------------------------------- |
| [Apache Parquet Hadoop][0]      | [The Apache Software License, Version 2.0][1] |
| Apache Hadoop Client Aggregator | [Apache License, Version 2.0][2]              |
| [snappy-java][3]                | [Apache-2.0][4]                               |
| [Scala Library][5]              | [Apache-2.0][6]                               |
| [error-reporting-java][7]       | [MIT License][8]                              |

## Test Dependencies

| Dependency                      | License                                   |
| ------------------------------- | ----------------------------------------- |
| [JUnit Jupiter (Aggregator)][9] | [Eclipse Public License v2.0][10]         |
| [mockito-core][11]              | [The MIT License][12]                     |
| [mockito-junit-jupiter][11]     | [The MIT License][12]                     |
| [Hamcrest][13]                  | [BSD License 3][14]                       |
| [scalatest][15]                 | [the Apache License, ASL Version 2.0][16] |

## Plugin Dependencies

| Dependency                                              | License                                       |
| ------------------------------------------------------- | --------------------------------------------- |
| [SonarQube Scanner for Maven][17]                       | [GNU LGPL 3][18]                              |
| [Apache Maven Compiler Plugin][19]                      | [Apache-2.0][2]                               |
| [Apache Maven Enforcer Plugin][20]                      | [Apache-2.0][2]                               |
| [Maven Flatten Plugin][21]                              | [Apache Software Licenese][2]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][22] | [ASL2][1]                                     |
| [Maven Surefire Plugin][23]                             | [Apache-2.0][2]                               |
| [Versions Maven Plugin][24]                             | [Apache License, Version 2.0][2]              |
| [scala-maven-plugin][25]                                | [Public domain (Unlicense)][26]               |
| [ScalaTest Maven Plugin][27]                            | [the Apache License, ASL Version 2.0][16]     |
| [OpenFastTrace Maven Plugin][28]                        | [GNU General Public License v3.0][29]         |
| [Project keeper maven plugin][30]                       | [The MIT License][31]                         |
| [duplicate-finder-maven-plugin Maven Mojo][32]          | [Apache License 2.0][33]                      |
| [Apache Maven Deploy Plugin][34]                        | [Apache-2.0][2]                               |
| [Apache Maven GPG Plugin][35]                           | [Apache License, Version 2.0][2]              |
| [Apache Maven Source Plugin][36]                        | [Apache License, Version 2.0][2]              |
| [Apache Maven Javadoc Plugin][37]                       | [Apache-2.0][2]                               |
| [Nexus Staging Maven Plugin][38]                        | [Eclipse Public License][39]                  |
| [Maven Failsafe Plugin][40]                             | [Apache-2.0][2]                               |
| [JaCoCo :: Maven Plugin][41]                            | [Eclipse Public License 2.0][42]              |
| [error-code-crawler-maven-plugin][43]                   | [MIT License][44]                             |
| [Reproducible Build Maven Plugin][45]                   | [Apache 2.0][1]                               |
| [Maven Clean Plugin][46]                                | [The Apache Software License, Version 2.0][1] |
| [Maven Resources Plugin][47]                            | [The Apache Software License, Version 2.0][1] |
| [Maven JAR Plugin][48]                                  | [The Apache Software License, Version 2.0][1] |
| [Maven Install Plugin][49]                              | [The Apache Software License, Version 2.0][1] |
| [Maven Site Plugin 3][50]                               | [The Apache Software License, Version 2.0][1] |

[0]: https://parquet.apache.org
[1]: http://www.apache.org/licenses/LICENSE-2.0.txt
[2]: https://www.apache.org/licenses/LICENSE-2.0.txt
[3]: https://github.com/xerial/snappy-java
[4]: https://www.apache.org/licenses/LICENSE-2.0.html
[5]: https://www.scala-lang.org/
[6]: https://www.apache.org/licenses/LICENSE-2.0
[7]: https://github.com/exasol/error-reporting-java/
[8]: https://github.com/exasol/error-reporting-java/blob/main/LICENSE
[9]: https://junit.org/junit5/
[10]: https://www.eclipse.org/legal/epl-v20.html
[11]: https://github.com/mockito/mockito
[12]: https://github.com/mockito/mockito/blob/main/LICENSE
[13]: http://hamcrest.org/JavaHamcrest/
[14]: http://opensource.org/licenses/BSD-3-Clause
[15]: http://www.scalatest.org
[16]: http://www.apache.org/licenses/LICENSE-2.0
[17]: http://sonarsource.github.io/sonar-scanner-maven/
[18]: http://www.gnu.org/licenses/lgpl.txt
[19]: https://maven.apache.org/plugins/maven-compiler-plugin/
[20]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[21]: https://www.mojohaus.org/flatten-maven-plugin/
[22]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[23]: https://maven.apache.org/surefire/maven-surefire-plugin/
[24]: https://www.mojohaus.org/versions/versions-maven-plugin/
[25]: http://github.com/davidB/scala-maven-plugin
[26]: http://unlicense.org/
[27]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[28]: https://github.com/itsallcode/openfasttrace-maven-plugin
[29]: https://www.gnu.org/licenses/gpl-3.0.html
[30]: https://github.com/exasol/project-keeper/
[31]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[32]: https://github.com/basepom/duplicate-finder-maven-plugin
[33]: http://www.apache.org/licenses/LICENSE-2.0.html
[34]: https://maven.apache.org/plugins/maven-deploy-plugin/
[35]: https://maven.apache.org/plugins/maven-gpg-plugin/
[36]: https://maven.apache.org/plugins/maven-source-plugin/
[37]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[38]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[39]: http://www.eclipse.org/legal/epl-v10.html
[40]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[41]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[42]: https://www.eclipse.org/legal/epl-2.0/
[43]: https://github.com/exasol/error-code-crawler-maven-plugin/
[44]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[45]: http://zlika.github.io/reproducible-build-maven-plugin
[46]: http://maven.apache.org/plugins/maven-clean-plugin/
[47]: http://maven.apache.org/plugins/maven-resources-plugin/
[48]: http://maven.apache.org/plugins/maven-jar-plugin/
[49]: http://maven.apache.org/plugins/maven-install-plugin/
[50]: http://maven.apache.org/plugins/maven-site-plugin/
