<!-- @formatter:off -->
# Dependencies

## Compile Dependencies

| Dependency                      | License                                       |
| ------------------------------- | --------------------------------------------- |
| [Apache Parquet Hadoop][0]      | [The Apache Software License, Version 2.0][1] |
| [snappy-java][2]                | [Apache-2.0][3]                               |
| Apache Hadoop Client Aggregator | [Apache License, Version 2.0][4]              |
| [Scala Library][5]              | [Apache-2.0][6]                               |
| [error-reporting-java][7]       | [MIT License][8]                              |

## Test Dependencies

| Dependency                                 | License                                   |
| ------------------------------------------ | ----------------------------------------- |
| [JUnit Jupiter (Aggregator)][9]            | [Eclipse Public License v2.0][10]         |
| [mockito-core][11]                         | [MIT][12]                                 |
| [mockito-junit-jupiter][11]                | [MIT][12]                                 |
| [Hamcrest][13]                             | [BSD License 3][14]                       |
| [scalatest][15]                            | [the Apache License, ASL Version 2.0][16] |
| [EqualsVerifier \| release normal jar][17] | [Apache License, Version 2.0][4]          |

## Plugin Dependencies

| Dependency                                              | License                                       |
| ------------------------------------------------------- | --------------------------------------------- |
| [SonarQube Scanner for Maven][18]                       | [GNU LGPL 3][19]                              |
| [Apache Maven Compiler Plugin][20]                      | [Apache-2.0][4]                               |
| [Apache Maven Enforcer Plugin][21]                      | [Apache-2.0][4]                               |
| [Maven Flatten Plugin][22]                              | [Apache Software Licenese][4]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][23] | [ASL2][1]                                     |
| [Maven Surefire Plugin][24]                             | [Apache-2.0][4]                               |
| [Versions Maven Plugin][25]                             | [Apache License, Version 2.0][4]              |
| [scala-maven-plugin][26]                                | [Public domain (Unlicense)][27]               |
| [ScalaTest Maven Plugin][28]                            | [the Apache License, ASL Version 2.0][16]     |
| [OpenFastTrace Maven Plugin][29]                        | [GNU General Public License v3.0][30]         |
| [Project keeper maven plugin][31]                       | [The MIT License][32]                         |
| [duplicate-finder-maven-plugin Maven Mojo][33]          | [Apache License 2.0][34]                      |
| [Apache Maven Deploy Plugin][35]                        | [Apache-2.0][4]                               |
| [Apache Maven GPG Plugin][36]                           | [Apache-2.0][4]                               |
| [Apache Maven Source Plugin][37]                        | [Apache License, Version 2.0][4]              |
| [Apache Maven Javadoc Plugin][38]                       | [Apache-2.0][4]                               |
| [Nexus Staging Maven Plugin][39]                        | [Eclipse Public License][40]                  |
| [Maven Failsafe Plugin][41]                             | [Apache-2.0][4]                               |
| [JaCoCo :: Maven Plugin][42]                            | [Eclipse Public License 2.0][43]              |
| [error-code-crawler-maven-plugin][44]                   | [MIT License][45]                             |
| [Reproducible Build Maven Plugin][46]                   | [Apache 2.0][1]                               |
| [Maven Clean Plugin][47]                                | [The Apache Software License, Version 2.0][1] |
| [Maven Resources Plugin][48]                            | [The Apache Software License, Version 2.0][1] |
| [Maven JAR Plugin][49]                                  | [The Apache Software License, Version 2.0][1] |
| [Maven Install Plugin][50]                              | [The Apache Software License, Version 2.0][1] |
| [Maven Site Plugin 3][51]                               | [The Apache Software License, Version 2.0][1] |

[0]: https://parquet.apache.org
[1]: http://www.apache.org/licenses/LICENSE-2.0.txt
[2]: https://github.com/xerial/snappy-java
[3]: https://www.apache.org/licenses/LICENSE-2.0.html
[4]: https://www.apache.org/licenses/LICENSE-2.0.txt
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
[17]: https://www.jqno.nl/equalsverifier
[18]: http://sonarsource.github.io/sonar-scanner-maven/
[19]: http://www.gnu.org/licenses/lgpl.txt
[20]: https://maven.apache.org/plugins/maven-compiler-plugin/
[21]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[22]: https://www.mojohaus.org/flatten-maven-plugin/
[23]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[24]: https://maven.apache.org/surefire/maven-surefire-plugin/
[25]: https://www.mojohaus.org/versions/versions-maven-plugin/
[26]: http://github.com/davidB/scala-maven-plugin
[27]: http://unlicense.org/
[28]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[29]: https://github.com/itsallcode/openfasttrace-maven-plugin
[30]: https://www.gnu.org/licenses/gpl-3.0.html
[31]: https://github.com/exasol/project-keeper/
[32]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[33]: https://basepom.github.io/duplicate-finder-maven-plugin
[34]: http://www.apache.org/licenses/LICENSE-2.0.html
[35]: https://maven.apache.org/plugins/maven-deploy-plugin/
[36]: https://maven.apache.org/plugins/maven-gpg-plugin/
[37]: https://maven.apache.org/plugins/maven-source-plugin/
[38]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[39]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[40]: http://www.eclipse.org/legal/epl-v10.html
[41]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[42]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[43]: https://www.eclipse.org/legal/epl-2.0/
[44]: https://github.com/exasol/error-code-crawler-maven-plugin/
[45]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[46]: http://zlika.github.io/reproducible-build-maven-plugin
[47]: http://maven.apache.org/plugins/maven-clean-plugin/
[48]: http://maven.apache.org/plugins/maven-resources-plugin/
[49]: http://maven.apache.org/plugins/maven-jar-plugin/
[50]: http://maven.apache.org/plugins/maven-install-plugin/
[51]: http://maven.apache.org/plugins/maven-site-plugin/
