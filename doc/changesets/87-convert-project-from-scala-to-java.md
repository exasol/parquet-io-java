# GH-87 Convert project from Scala to Java

## Goal

Migrate the project to a Java-only codebase while preserving the current
runtime behavior, public reader behavior, requirement trace coverage, and test
coverage.

Issue: <https://github.com/exasol/parquet-io-java/issues/87>

## Scope

In scope:

* Convert all production code under `src/main/scala` to Java.
* Convert all Scala tests and Scala test helpers under `src/test/scala` to
  Java/JUnit 5.
* Remove Scala source directories, Scala runtime dependencies, ScalaTest
  dependencies, Scala Maven plugins, Scaladoc artifact generation, and
  Scala-specific build workarounds.
* Keep all existing functional tests green after the production migration and
  again after the test migration.
* Preserve all OpenFastTrace `impl` and `utest` coverage links while moving
  coverage tags from Scala files to Java files.
* Verify overall code coverage stays above 80%.
* Update generated dependency documentation, Project Keeper configuration, CI
  build inputs, and changelog entries affected by removing Scala.

Out of scope:

* Changing Parquet reading behavior or output data structures.
* Adding new runtime dependencies.
* Changing user-facing requirements. Where implementation reveals an existing requirement/design mismatch, add to `design/open_issues.md`.
* Refactoring unrelated Java code outside the migration path.
* Changing the public API beyond changes unavoidable when removing Scala-specific implementation classes.

## Design References

* [System Requirements](../system_requirements.md)
* [Software Architectural Design](../design.md)
* [Quality Requirements](../design/quality_requirements.md)
* [Maven Build Configuration](../../pom.xml)
* [Project Keeper Configuration](../../.project-keeper.yml)
* [CI Build Workflow](../../.github/workflows/ci-build.yml)
* [Current Release Changelog](../changes/changes_2.0.16.md)

Notes:

* GH-88 is related because it restores tracing across Java and Scala source
  roots. GH-87 must either build on that fix or update the OpenFastTrace inputs
  to the final Java-only layout.

## Preliminary Analysis

The current system requirements describe Parquet reader behavior and do not
mention Scala as user-visible behavior. The existing design items remain the
same behavioral units after the migration:

* `dsn~read-parquet-file-contents~1`
* `dsn~read-parquet-file-chunks-contents~1`
* `dsn~converting-primitive-column-types~1`
* `dsn~converting-logical-column-types~1`
* `dsn~converting-nested-column-types~1`

Production Scala files to migrate:

* `src/main/scala/com/exasol/parquetio/helper/DataTimeHelper.scala`
* `src/main/scala/com/exasol/parquetio/reader/RowReadSupport.scala`
* `src/main/scala/com/exasol/parquetio/reader/converter/ParquetConverter.scala`
* `src/main/scala/com/exasol/parquetio/reader/converter/ParquetConverterFactory.scala`
* `src/main/scala/com/exasol/parquetio/reader/converter/ParquetRootConverter.scala`
* `src/main/scala/com/exasol/parquetio/reader/converter/RepeatedConverter.scala`
* `src/main/scala/com/exasol/parquetio/reader/converter/ValueHolder.scala`

Scala language features that need explicit Java replacements:

* `trait ParquetConverter extends Converter`: use a Java interface with default
  lifecycle methods and concrete converter classes that also extend
  `PrimitiveConverter` or `GroupConverter`. Keep explicit casts or helper
  methods where Parquet APIs require `Converter`.
* `trait ValueHolder`: use a Java interface plus concrete Java holder classes.
* `object DateTimeHelper`, `object EmptyValueHolder`, and
  `object ParquetConverterFactory`: use final utility classes or singleton
  instances without keeping Scala module classes.
* `final case class` converters and holders: use final Java classes with
  explicit fields and constructors.
* Pattern matching on Parquet enum types: use Java `switch` statements.
* Scala `for` comprehensions and ranges: use Java loops.
* Scala `ArrayBuffer`, arrays, `Seq`, and tuple return values: use Java
  collections, arrays, or small Java helper types where still needed.
* `getJulianDayAndNanos` currently returns a Scala tuple and appears unused;
  remove it if it is not part of the supported API, otherwise replace it with a
  Java-only value type.
* ScalaDoc links in migrated comments: convert to Javadoc links.

Scala test features to replace:

* ScalaTest `AnyFunSuite`: use JUnit 5 `@Test`.
* `BeforeAndAfterEach`: use JUnit 5 `@BeforeEach` and `@AfterEach`.
* Scala traits for shared test helpers: use Java base classes or helper
  classes.
* `Seq`, `map`, `foreach`, `zipWithIndex`, and Scala assertions: use Java
  collections, loops, JUnit assertions, or Hamcrest assertions.
* `stripMargin` schema literals: use Java 11 compatible string construction
  because the project targets Java 11 and cannot rely on Java text blocks.
* Scala resource helpers: use try-with-resources.

## Strategy

Perform the migration in two compileable steps.

First, migrate production code from Scala to Java while leaving the Scala tests
and Scala test build support in place. The existing Scala tests must pass
without changing their assertions so this step proves that production behavior
did not change.

Second, migrate the Scala tests and helpers to Java/JUnit 5. After the tests are
Java-only and green, remove the remaining Scala runtime, test dependencies,
plugins, source roots, and CI/Project Keeper workarounds.

Keep OFT tags close to the migrated implementation and test classes. After each
migration step, run tracing so missing or stale `impl` and `utest` links are
caught while the moved code is still easy to review.

## Task List

- [ ] Create and checkout a new Git branch
      `refactoring/87-convert-project-from-scala-to-java`.

### Requirements And Design

- [ ] Confirm that `doc/system_requirements.md` remains accurate because this
      issue is a pure refactoring with no behavior change.
- [ ] Stop and ask the user for a review if the migration reveals any behavior
      change or requirement mismatch.
- [ ] Review `doc/design.md` and either leave it unchanged because the existing
      runtime design items still apply, or add a concise Java-only
      implementation/build decision if the project wants this constraint
      documented.
- [ ] Stop and ask the user for a review if quality requirements need semantic
      changes before they can be used as implementation gates.

### Production Migration

- [ ] Convert `DateTimeHelper.scala` to Java and remove Scala tuple usage.
- [ ] Convert `ValueHolder.scala` to Java holder interfaces/classes while
      preserving reset, indexed, appended, empty, and unmodifiable-list
      behavior.
- [ ] Convert `ParquetConverter.scala` to Java converter classes and preserve
      primitive, string dictionary, UUID, decimal, timestamp, date, list, map,
      and struct behavior.
- [ ] Convert `RepeatedConverter.scala` to Java and preserve repeated primitive,
      repeated group, and single-value group handling.
- [ ] Convert `ParquetConverterFactory.scala` to a Java factory and preserve all
      primitive/logical/nested converter selection rules.
- [ ] Convert `ParquetRootConverter.scala` and `RowReadSupport.scala` to Java
      and preserve row materialization behavior.
- [ ] Move the existing `impl` OFT coverage tags from Scala files to the
      corresponding Java implementation classes.
- [ ] Delete migrated files from `src/main/scala` only after the Java
      replacements compile.
- [ ] Run the existing test suite with the Scala tests unchanged and confirm it
      still passes.

### Test Migration

- [ ] Convert `BaseParquetReaderTest.scala` to a Java JUnit 5 base class or
      helper.
- [ ] Convert `TestFileManager.scala` to a Java test helper.
- [ ] Convert `ParquetTestDataWriter.scala` to Java or merge it with the
      existing Java `ParquetTestFileWriter` where this reduces duplication
      without changing test behavior.
- [ ] Convert `RowParquetReaderTest.scala` to Java/JUnit 5.
- [ ] Convert `RowParquetReaderPrimitiveTypesTest.scala` to Java/JUnit 5.
- [ ] Convert `RowParquetReaderComplexTypesTest.scala` to Java/JUnit 5.
- [ ] Move the existing `utest` OFT coverage tags from Scala tests to the
      corresponding Java tests.
- [ ] Delete migrated files from `src/test/scala` only after the Java tests
      compile and pass.

### Build And Dependency Cleanup

- [ ] Remove `scala.version` and `scala.compat.version` from `pom.xml`.
- [ ] Remove `org.scala-lang:scala-library`.
- [ ] Remove `org.scalatest:scalatest_${scala.compat.version}`.
- [ ] Remove `net.alchim31.maven:scala-maven-plugin` and all Scala compile,
      test-compile, and Scaladoc executions.
- [ ] Remove `org.scalatest:scalatest-maven-plugin`.
- [ ] Remove the `error-code-crawler-maven-plugin` suppression that exists only
      because the crawler does not support hybrid Java/Scala projects.
- [ ] Update `.project-keeper.yml` to remove dummy error-code-report workflow
      customizations if the crawler works after the Java-only migration.
- [ ] Regenerate Project Keeper files and dependency documentation, including
      `dependencies.md` and generated workflows, as needed.
- [ ] Remove empty `src/main/scala` and `src/test/scala` directories.
- [ ] Search the repository for remaining `scala`, `Scala`, `scalatest`,
      `scala-maven-plugin`, and `scaladoc` references and remove all traces
      except historical changelog entries.

### Verification

- [ ] Run `mvn --batch-mode test -DossindexSkip=true` after production code is
      migrated and before Scala tests are changed.
- [ ] Run `mvn --batch-mode test -DossindexSkip=true` after test migration.
- [ ] Run `mvn --batch-mode process-test-resources openfasttrace:trace
      -DskipTests -DossindexSkip=true` and confirm tracing is clean.
- [ ] Confirm OpenFastTrace imports the final Java-only source and test
      directories and no longer depends on Scala source roots.
- [ ] Run `mvn --batch-mode clean verify -DossindexSkip=true`.
- [ ] Inspect `target/site/jacoco/jacoco.csv` or the generated JaCoCo report and
      confirm overall coverage is above 80%.
- [ ] Run the CI-equivalent Java 17 compatibility command from
      `.github/workflows/ci-build.yml`:
      `mvn --batch-mode clean package -DtrimStackTrace=false -Djava.version=17
      -DossindexSkip=true`.
- [ ] Run Project Keeper verification and confirm generated files are
      consistent.
- [ ] Run a repository search for `.scala` files and Scala build/dependency
      strings and confirm only changelog history remains.

### Update User Documentation

- [ ] Update `README.md` only if users or contributors need changed build or
      test instructions.
- [ ] Update `dependencies.md` if Project Keeper does not regenerate it
      automatically.

## Version And Changelog Update

- [ ] Keep the release version at `2.0.16` unless the release plan changes.
- [ ] Update `doc/changes/changes_2.0.16.md` with a GH-87 refactoring entry.
- [ ] Ensure the changelog summary accurately states that the project migrated
      from Scala to Java and removed Scala dependencies/build support.
