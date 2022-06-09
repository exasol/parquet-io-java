<!-- @formatter:off -->
# Dependencies

## Compile Dependencies

| Dependency                                 | License                                       |
| ------------------------------------------ | --------------------------------------------- |
| [Apache Parquet Hadoop][0]                 | [The Apache Software License, Version 2.0][1] |
| Apache Hadoop Client Aggregator            | [Apache License, Version 2.0][2]              |
| [Guava: Google Core Libraries for Java][3] | [Apache License, Version 2.0][1]              |
| [Apache Commons Compress][5]               | [Apache License, Version 2.0][2]              |
| [Scala Library][7]                         | [Apache-2.0][8]                               |
| [error-reporting-java][9]                  | [MIT][10]                                     |

## Test Dependencies

| Dependency                       | License                                   |
| -------------------------------- | ----------------------------------------- |
| [JUnit Jupiter (Aggregator)][11] | [Eclipse Public License v2.0][12]         |
| [mockito-core][13]               | [The MIT License][14]                     |
| [mockito-junit-jupiter][13]      | [The MIT License][14]                     |
| [Hamcrest][17]                   | [BSD License 3][18]                       |
| [scalatest][19]                  | [the Apache License, ASL Version 2.0][20] |

## Plugin Dependencies

| Dependency                                              | License                                       |
| ------------------------------------------------------- | --------------------------------------------- |
| [SonarQube Scanner for Maven][21]                       | [GNU LGPL 3][22]                              |
| [Apache Maven Compiler Plugin][23]                      | [Apache License, Version 2.0][2]              |
| [Apache Maven Enforcer Plugin][25]                      | [Apache License, Version 2.0][2]              |
| [Maven Flatten Plugin][27]                              | [Apache Software Licenese][1]                 |
| [scala-maven-plugin][29]                                | [Public domain (Unlicense)][30]               |
| [ScalaTest Maven Plugin][31]                            | [the Apache License, ASL Version 2.0][20]     |
| [Apache Maven Assembly Plugin][33]                      | [Apache License, Version 2.0][2]              |
| [OpenFastTrace Maven Plugin][35]                        | [GNU General Public License v3.0][36]         |
| [Project keeper maven plugin][37]                       | [The MIT License][38]                         |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][39] | [ASL2][1]                                     |
| [Reproducible Build Maven Plugin][41]                   | [Apache 2.0][1]                               |
| [Maven Surefire Plugin][43]                             | [Apache License, Version 2.0][2]              |
| [Versions Maven Plugin][45]                             | [Apache License, Version 2.0][2]              |
| [Apache Maven Deploy Plugin][47]                        | [Apache License, Version 2.0][2]              |
| [Apache Maven GPG Plugin][49]                           | [Apache License, Version 2.0][2]              |
| [Apache Maven Source Plugin][51]                        | [Apache License, Version 2.0][2]              |
| [Apache Maven Javadoc Plugin][53]                       | [Apache License, Version 2.0][2]              |
| [Nexus Staging Maven Plugin][55]                        | [Eclipse Public License][56]                  |
| [Maven Failsafe Plugin][57]                             | [Apache License, Version 2.0][2]              |
| [JaCoCo :: Maven Plugin][59]                            | [Eclipse Public License 2.0][60]              |
| [error-code-crawler-maven-plugin][61]                   | [MIT][10]                                     |
| [Maven Clean Plugin][63]                                | [The Apache Software License, Version 2.0][1] |
| [Maven Resources Plugin][65]                            | [The Apache Software License, Version 2.0][1] |
| [Maven JAR Plugin][67]                                  | [The Apache Software License, Version 2.0][1] |
| [Maven Install Plugin][69]                              | [The Apache Software License, Version 2.0][1] |
| [Maven Site Plugin 3][71]                               | [The Apache Software License, Version 2.0][1] |

[9]: https://github.com/exasol/error-reporting-java
[1]: http://www.apache.org/licenses/LICENSE-2.0.txt
[43]: https://maven.apache.org/surefire/maven-surefire-plugin/
[63]: http://maven.apache.org/plugins/maven-clean-plugin/
[10]: https://opensource.org/licenses/MIT
[13]: https://github.com/mockito/mockito
[27]: https://www.mojohaus.org/flatten-maven-plugin/
[5]: https://commons.apache.org/proper/commons-compress/
[37]: https://github.com/exasol/project-keeper/
[45]: http://www.mojohaus.org/versions-maven-plugin/
[18]: http://opensource.org/licenses/BSD-3-Clause
[23]: https://maven.apache.org/plugins/maven-compiler-plugin/
[35]: https://github.com/itsallcode/openfasttrace-maven-plugin
[60]: https://www.eclipse.org/legal/epl-2.0/
[47]: https://maven.apache.org/plugins/maven-deploy-plugin/
[22]: http://www.gnu.org/licenses/lgpl.txt
[30]: http://unlicense.org/
[8]: https://www.apache.org/licenses/LICENSE-2.0
[31]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[59]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[14]: https://github.com/mockito/mockito/blob/main/LICENSE
[41]: http://zlika.github.io/reproducible-build-maven-plugin
[0]: https://parquet.apache.org
[21]: http://sonarsource.github.io/sonar-scanner-maven/
[11]: https://junit.org/junit5/
[51]: https://maven.apache.org/plugins/maven-source-plugin/
[17]: http://hamcrest.org/JavaHamcrest/
[65]: http://maven.apache.org/plugins/maven-resources-plugin/
[20]: http://www.apache.org/licenses/LICENSE-2.0
[3]: https://github.com/google/guava
[55]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[19]: http://www.scalatest.org
[57]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[7]: https://www.scala-lang.org/
[56]: http://www.eclipse.org/legal/epl-v10.html
[38]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[36]: https://www.gnu.org/licenses/gpl-3.0.html
[67]: http://maven.apache.org/plugins/maven-jar-plugin/
[2]: https://www.apache.org/licenses/LICENSE-2.0.txt
[25]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[12]: https://www.eclipse.org/legal/epl-v20.html
[69]: http://maven.apache.org/plugins/maven-install-plugin/
[39]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[49]: https://maven.apache.org/plugins/maven-gpg-plugin/
[29]: http://github.com/davidB/scala-maven-plugin
[71]: http://maven.apache.org/plugins/maven-site-plugin/
[53]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[61]: https://github.com/exasol/error-code-crawler-maven-plugin
[33]: https://maven.apache.org/plugins/maven-assembly-plugin/
