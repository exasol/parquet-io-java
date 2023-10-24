<!-- @formatter:off -->
# Dependencies

## Compile Dependencies

| Dependency                      | License                                       |
| ------------------------------- | --------------------------------------------- |
| [Apache Parquet Hadoop][0]      | [The Apache Software License, Version 2.0][1] |
| [snappy-java][2]                | [Apache-2.0][3]                               |
| Apache Hadoop Client Aggregator | [Apache License, Version 2.0][4]              |
| [Apache Avro][5]                | [Apache-2.0][4]                               |
| [Apache Commons Compress][6]    | [Apache-2.0][4]                               |
| [Scala Library][7]              | [Apache-2.0][8]                               |
| [error-reporting-java][9]       | [MIT License][10]                             |

## Test Dependencies

| Dependency                                 | License                                   |
| ------------------------------------------ | ----------------------------------------- |
| [JUnit Jupiter (Aggregator)][11]           | [Eclipse Public License v2.0][12]         |
| [mockito-core][13]                         | [MIT][14]                                 |
| [mockito-junit-jupiter][13]                | [MIT][14]                                 |
| [Hamcrest][15]                             | [BSD License 3][16]                       |
| [scalatest][17]                            | [the Apache License, ASL Version 2.0][18] |
| [EqualsVerifier \| release normal jar][19] | [Apache License, Version 2.0][4]          |

## Plugin Dependencies

| Dependency                                              | License                                       |
| ------------------------------------------------------- | --------------------------------------------- |
| [SonarQube Scanner for Maven][20]                       | [GNU LGPL 3][21]                              |
| [Apache Maven Compiler Plugin][22]                      | [Apache-2.0][4]                               |
| [Apache Maven Enforcer Plugin][23]                      | [Apache-2.0][4]                               |
| [Maven Flatten Plugin][24]                              | [Apache Software Licenese][4]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][25] | [ASL2][1]                                     |
| [Maven Surefire Plugin][26]                             | [Apache-2.0][4]                               |
| [Versions Maven Plugin][27]                             | [Apache License, Version 2.0][4]              |
| [scala-maven-plugin][28]                                | [Public domain (Unlicense)][29]               |
| [ScalaTest Maven Plugin][30]                            | [the Apache License, ASL Version 2.0][18]     |
| [OpenFastTrace Maven Plugin][31]                        | [GNU General Public License v3.0][32]         |
| [Project keeper maven plugin][33]                       | [The MIT License][34]                         |
| [duplicate-finder-maven-plugin Maven Mojo][35]          | [Apache License 2.0][36]                      |
| [Apache Maven Deploy Plugin][37]                        | [Apache-2.0][4]                               |
| [Apache Maven GPG Plugin][38]                           | [Apache-2.0][4]                               |
| [Apache Maven Source Plugin][39]                        | [Apache License, Version 2.0][4]              |
| [Apache Maven Javadoc Plugin][40]                       | [Apache-2.0][4]                               |
| [Nexus Staging Maven Plugin][41]                        | [Eclipse Public License][42]                  |
| [Maven Failsafe Plugin][43]                             | [Apache-2.0][4]                               |
| [JaCoCo :: Maven Plugin][44]                            | [Eclipse Public License 2.0][45]              |
| [error-code-crawler-maven-plugin][46]                   | [MIT License][47]                             |
| [Reproducible Build Maven Plugin][48]                   | [Apache 2.0][1]                               |
| [Maven Clean Plugin][49]                                | [The Apache Software License, Version 2.0][1] |
| [Maven Resources Plugin][50]                            | [The Apache Software License, Version 2.0][1] |
| [Maven JAR Plugin][51]                                  | [The Apache Software License, Version 2.0][1] |
| [Maven Install Plugin][52]                              | [The Apache Software License, Version 2.0][1] |
| [Maven Site Plugin 3][53]                               | [The Apache Software License, Version 2.0][1] |

[0]: https://parquet.apache.org
[1]: http://www.apache.org/licenses/LICENSE-2.0.txt
[2]: https://github.com/xerial/snappy-java
[3]: https://www.apache.org/licenses/LICENSE-2.0.html
[4]: https://www.apache.org/licenses/LICENSE-2.0.txt
[5]: https://avro.apache.org
[6]: https://commons.apache.org/proper/commons-compress/
[7]: https://www.scala-lang.org/
[8]: https://www.apache.org/licenses/LICENSE-2.0
[9]: https://github.com/exasol/error-reporting-java/
[10]: https://github.com/exasol/error-reporting-java/blob/main/LICENSE
[11]: https://junit.org/junit5/
[12]: https://www.eclipse.org/legal/epl-v20.html
[13]: https://github.com/mockito/mockito
[14]: https://github.com/mockito/mockito/blob/main/LICENSE
[15]: http://hamcrest.org/JavaHamcrest/
[16]: http://opensource.org/licenses/BSD-3-Clause
[17]: http://www.scalatest.org
[18]: http://www.apache.org/licenses/LICENSE-2.0
[19]: https://www.jqno.nl/equalsverifier
[20]: http://sonarsource.github.io/sonar-scanner-maven/
[21]: http://www.gnu.org/licenses/lgpl.txt
[22]: https://maven.apache.org/plugins/maven-compiler-plugin/
[23]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[24]: https://www.mojohaus.org/flatten-maven-plugin/
[25]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[26]: https://maven.apache.org/surefire/maven-surefire-plugin/
[27]: https://www.mojohaus.org/versions/versions-maven-plugin/
[28]: http://github.com/davidB/scala-maven-plugin
[29]: http://unlicense.org/
[30]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[31]: https://github.com/itsallcode/openfasttrace-maven-plugin
[32]: https://www.gnu.org/licenses/gpl-3.0.html
[33]: https://github.com/exasol/project-keeper/
[34]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[35]: https://basepom.github.io/duplicate-finder-maven-plugin
[36]: http://www.apache.org/licenses/LICENSE-2.0.html
[37]: https://maven.apache.org/plugins/maven-deploy-plugin/
[38]: https://maven.apache.org/plugins/maven-gpg-plugin/
[39]: https://maven.apache.org/plugins/maven-source-plugin/
[40]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[41]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[42]: http://www.eclipse.org/legal/epl-v10.html
[43]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[44]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[45]: https://www.eclipse.org/legal/epl-2.0/
[46]: https://github.com/exasol/error-code-crawler-maven-plugin/
[47]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[48]: http://zlika.github.io/reproducible-build-maven-plugin
[49]: http://maven.apache.org/plugins/maven-clean-plugin/
[50]: http://maven.apache.org/plugins/maven-resources-plugin/
[51]: http://maven.apache.org/plugins/maven-jar-plugin/
[52]: http://maven.apache.org/plugins/maven-install-plugin/
[53]: http://maven.apache.org/plugins/maven-site-plugin/
