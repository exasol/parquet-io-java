# GH-88 Fix broken OFT tracing

## Goal

Restore OpenFastTrace requirement tracing so the project reports all coverage
defects, includes Java and Scala implementation and test coverage tags, and
fails the CI build when tracing is broken.

Issue: <https://github.com/exasol/parquet-io-java/issues/88>

## Scope

In scope:

* Fix the Maven OpenFastTrace configuration so it scans all directories that
  contain specification items or OFT coverage tags:
  * `doc`
  * `src/main/java`
  * `src/main/scala`
  * `src/test/java`
  * `src/test/scala`
  * `src/test/resources`
* Ensure the Maven build fails when OpenFastTrace reports defects.
* Make the CI build fail on broken tracing through the existing Maven build
  gate.
* Correct OFT coverage tags in comments when trace defects are caused by
  missing, stale, or wrong coverage tags.
* Preserve runtime behavior and production code.
* List all trace defects during verification so reviewers can see whether any
  requirement is not properly covered or covered by the wrong artifact type.

Out of scope:

* Changing production behavior.
* Refactoring Java or Scala implementation code.
* Adding, removing, or changing functional requirements unless the trace shows
  that the existing requirements are semantically wrong.
* Adding new runtime dependencies.
* Reworking Project Keeper generated workflows beyond the minimum needed to keep
  trace verification in the generated CI build.

## Design References

* [System Requirements](../system_requirements.md)
* [Software Architectural Design](../design.md)
* [CI Build Workflow](../../.github/workflows/ci-build.yml)
* [Maven Build Configuration](../../pom.xml)

Notes:

* The repo-local OFT spec-driven-development skill expects
  `doc/design/quality_requirements.md`, but this repository does not currently
  contain that file.
* The repo-local skill also expects `doc/changesets/README.md`, but this
  repository does not currently contain that file or prior changesets.

## Current Trace Findings

Running `mvn --batch-mode openfasttrace:trace -DskipTests` currently fails with
10 defects out of 20 imported items.

Primary finding:

* The OpenFastTrace Maven plugin imports `doc`, `src/main/java`,
  `src/test/java`, and `src/test/resources`, but it does not import
  `src/main/scala` or `src/test/scala`.

Observed defects:

* `dsn~converting-logical-column-types~1` is missing `impl` and `utest`
  coverage.
* `dsn~converting-nested-column-types~1` is missing `impl` and `utest`
  coverage.
* `dsn~converting-primitive-column-types~1` is missing `impl` and `utest`
  coverage.
* `dsn~read-parquet-file-contents~1` is missing `utest` coverage.
* `feat~column-data-reading~1`, `feat~parquet-rows~1`,
  `req~accessing-decoded-rows~1`, `req~reading-logical-types~1`,
  `req~reading-nested-types~1`, and `req~reading-primitive-types~1` are
  transitively not ok because their downstream design coverage is incomplete.

Additional suspicious tag:

* `.github/workflows/release.yml` contains
  `[impl->dsn~release-workflow.create-golang-tags~1]`, but this design item is
  not defined in `doc/design.md`. The current Maven trace does not import
  `.github`, so this tag is not part of the reported failure. Do not include
  `.github` in trace inputs unless release workflow requirements and design
  items are added deliberately.

## Strategy

Configure the OpenFastTrace Maven plugin with explicit input directories instead
of relying on the current default source set discovery. Include both Java and
Scala source/test directories so existing OFT tags in Scala files are imported.

Use the Maven plugin's build-failing behavior as the CI gate. The generated CI
workflow already runs `mvn --batch-mode clean verify`, so the OpenFastTrace goal
must be bound to the `verify` lifecycle after Scala source roots are registered.

Prefer a plain-text failure report for CI readability if supported without
losing the existing HTML report. Keep or generate
`target/tracing-report.html` for local inspection.

## Task List

- [ ] Create and checkout a new Git branch
      `fix/88-fix-broken-oft-tracing`. Blocked locally because `.git/refs` is
      read-only in this environment.

### Requirements And Design

- [x] Confirm that `doc/system_requirements.md` and `doc/design.md` already
      describe the current behavior and only the trace configuration is broken.
- [x] Stop and ask the user for a review if the trace reveals semantically wrong
      or missing requirements/design items, because issue GH-88 explicitly asks
      not to change code beyond OFT coverage comments.
- [x] Leave user-facing requirements unchanged if all defects are caused by
      trace input configuration or OFT coverage tags.
- [x] Leave design unchanged if the existing `dsn` items are correct and only
      their `impl` or `utest` coverage is not imported.

### Implementation

- [x] Update `pom.xml` OpenFastTrace plugin configuration so lifecycle builds
      execute OFT after Maven has registered `doc`, `src/main/java`,
      `src/main/scala`, `src/test/java`, `src/test/scala`, and
      `src/test/resources`.
- [x] Ensure the OpenFastTrace Maven execution fails the build on trace defects.
- [x] Check whether Project Keeper preserves the OpenFastTrace plugin
      configuration or requires an update to `.project-keeper.yml`.
- [x] Regenerate Project Keeper files if needed so
      `.github/workflows/ci-build.yml` continues to run tracing through
      `mvn --batch-mode clean verify`.
- [x] If trace defects remain after Scala directories are imported, fix only OFT
      coverage tags in comments or specification references needed for correct
      tracing. No coverage tag fixes were needed.
- [x] Do not modify production logic.

### Verification

- [x] Run `mvn --batch-mode process-test-resources openfasttrace:trace
      -DskipTests -DossindexSkip=true` and confirm it exits with status `0`.
- [x] Capture the complete OpenFastTrace defect list before fixing and confirm
      all defects are resolved or explicitly explained.
- [x] Confirm the trace imports Java and Scala implementation and test
      directories.
- [x] Confirm `target/tracing-report.html` is generated and reports no defects.
- [x] Run `mvn --batch-mode verify -DskipTests -DossindexSkip=true` and confirm
      OFT runs during the Maven lifecycle.
- [x] Run `mvn --batch-mode clean verify -DossindexSkip=true` and confirm the
      whole Maven build passes. The command passes outside the sandbox, where
      Mockito/Byte Buddy can attach its test agent.
- [x] If Project Keeper files are regenerated, run the configured Project Keeper
      verification and confirm `.github/workflows/ci-build.yml` remains
      consistent.
- [x] Run the existing unit test suite through Maven as part of `clean verify`.
      The sandboxed run failed because Mockito inline could not self-attach to
      the current JDK 11 VM, but the escalated run passed.

### Update User Documentation

- [x] Update README.md only if users or contributors need a documented command
      for local trace verification.

## Version And Changelog Update

- [x] Decide whether this documentation/build-gate fix requires a patch release
      entry for the next version.
- [x] If required, add a changelog entry for the next patch version after
      `2.0.15`. No changelog entry was added because this change only restores a
      build quality gate and does not affect released runtime behavior.
