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

| Dependency                                              | License                                   |
| ------------------------------------------------------- | ----------------------------------------- |
| [SonarQube Scanner for Maven][21]                       | [GNU LGPL 3][22]                          |
| [scala-maven-plugin][23]                                | [Public domain (Unlicense)][24]           |
| [Apache Maven Compiler Plugin][25]                      | [Apache License, Version 2.0][2]          |
| [Apache Maven Enforcer Plugin][27]                      | [Apache License, Version 2.0][2]          |
| [Maven Flatten Plugin][29]                              | [Apache Software Licenese][1]             |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][31] | [ASL2][1]                                 |
| [Reproducible Build Maven Plugin][33]                   | [Apache 2.0][1]                           |
| [Maven Surefire Plugin][35]                             | [Apache License, Version 2.0][2]          |
| [Versions Maven Plugin][37]                             | [Apache License, Version 2.0][2]          |
| [Apache Maven Deploy Plugin][39]                        | [Apache License, Version 2.0][2]          |
| [OpenFastTrace Maven Plugin][41]                        | [GNU General Public License v3.0][42]     |
| [Maven Failsafe Plugin][43]                             | [Apache License, Version 2.0][2]          |
| [Apache Maven GPG Plugin][45]                           | [Apache License, Version 2.0][2]          |
| [Apache Maven Source Plugin][47]                        | [Apache License, Version 2.0][2]          |
| [Apache Maven Javadoc Plugin][49]                       | [Apache License, Version 2.0][2]          |
| [Nexus Staging Maven Plugin][51]                        | [Eclipse Public License][52]              |
| [ScalaTest Maven Plugin][53]                            | [the Apache License, ASL Version 2.0][20] |
| [Apache Maven Assembly Plugin][55]                      | [Apache License, Version 2.0][2]          |
| [JaCoCo :: Maven Plugin][57]                            | [Eclipse Public License 2.0][58]          |
| [error-code-crawler-maven-plugin][59]                   | [MIT][10]                                 |
| [Project keeper maven plugin][61]                       | [The MIT License][62]                     |
| [Apache Maven Clean Plugin][63]                         | [Apache License, Version 2.0][2]          |
| [Apache Maven Resources Plugin][65]                     | [Apache License, Version 2.0][2]          |
| [Apache Maven JAR Plugin][67]                           | [Apache License, Version 2.0][2]          |
| [Apache Maven Install Plugin][69]                       | [Apache License, Version 2.0][1]          |
| [Apache Maven Site Plugin][71]                          | [Apache License, Version 2.0][2]          |

[9]: https://github.com/exasol/error-reporting-java
[1]: http://www.apache.org/licenses/LICENSE-2.0.txt
[35]: https://maven.apache.org/surefire/maven-surefire-plugin/
[10]: https://opensource.org/licenses/MIT
[13]: https://github.com/mockito/mockito
[29]: https://www.mojohaus.org/flatten-maven-plugin/
[5]: https://commons.apache.org/proper/commons-compress/
[37]: http://www.mojohaus.org/versions-maven-plugin/
[53]: https://mvnrepository.com/artifact/org.scalatest/scalatest-maven-plugin
[61]: https://github.com/exasol/project-keeper/
[18]: http://opensource.org/licenses/BSD-3-Clause
[25]: https://maven.apache.org/plugins/maven-compiler-plugin/
[65]: https://maven.apache.org/plugins/maven-resources-plugin/
[41]: https://github.com/itsallcode/openfasttrace-maven-plugin
[63]: https://maven.apache.org/plugins/maven-clean-plugin/
[58]: https://www.eclipse.org/legal/epl-2.0/
[39]: https://maven.apache.org/plugins/maven-deploy-plugin/
[22]: http://www.gnu.org/licenses/lgpl.txt
[24]: http://unlicense.org/
[8]: https://www.apache.org/licenses/LICENSE-2.0
[57]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[14]: https://github.com/mockito/mockito/blob/main/LICENSE
[33]: http://zlika.github.io/reproducible-build-maven-plugin
[0]: https://parquet.apache.org
[21]: http://sonarsource.github.io/sonar-scanner-maven/
[11]: https://junit.org/junit5/
[47]: https://maven.apache.org/plugins/maven-source-plugin/
[17]: http://hamcrest.org/JavaHamcrest/
[67]: https://maven.apache.org/plugins/maven-jar-plugin/
[20]: http://www.apache.org/licenses/LICENSE-2.0
[3]: https://github.com/google/guava
[51]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[19]: http://www.scalatest.org
[43]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[7]: https://www.scala-lang.org/
[52]: http://www.eclipse.org/legal/epl-v10.html
[62]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[71]: https://maven.apache.org/plugins/maven-site-plugin/
[42]: https://www.gnu.org/licenses/gpl-3.0.html
[2]: https://www.apache.org/licenses/LICENSE-2.0.txt
[27]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[12]: https://www.eclipse.org/legal/epl-v20.html
[69]: http://maven.apache.org/plugins/maven-install-plugin/
[31]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[45]: https://maven.apache.org/plugins/maven-gpg-plugin/
[23]: http://github.com/davidB/scala-maven-plugin
[49]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[59]: https://github.com/exasol/error-code-crawler-maven-plugin
[55]: https://maven.apache.org/plugins/maven-assembly-plugin/
