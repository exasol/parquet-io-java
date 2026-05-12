# GH-90 Create user guide

## Goal

Provide a developer-focused user guide and published JavaDoc API documentation
for Parquet IO Java, including CI automation for generating and publishing the
API docs.

Issue: <https://github.com/exasol/parquet-io-java/issues/90>

## Scope

In scope:

* Add and maintain a developer user guide at `doc/user_guide.md`.
* Document Maven Central consumption and basic usage examples in the user guide.
* Build JavaDoc API documentation in CI.
* Publish JavaDoc API documentation to GitHub Pages.
* Link published JavaDoc API documentation from the user guide.
* Update README links where needed so users can discover the user guide.

Out of scope:

* Changing library runtime behavior.
* Changing existing reader APIs.
* Adding third-party dependencies unless design review explicitly approves.

## Design References

* [System Requirements](../system_requirements.md)
* [Software Architectural Design](../design.md)
* [Quality Requirements](../design/quality_requirements.md)
* [CI Build Workflow](../../.github/workflows/ci-build.yml)
* [Release Workflow](../../.github/workflows/release.yml)

## Strategy

Follow spec-first delivery: update traced requirements and design first, then
implement workflow and documentation updates, and finally verify trace/build
quality gates.

## Task List

- [ ] Create and checkout a new Git branch
      `feature/90-create-user-guide`

### Requirements And Design

- [x] Add user-facing requirement coverage for developer documentation in
      `doc/system_requirements.md`.
- [x] Stop and ask user for a review of the system requirements.
- [x] Add/update design items for JavaDoc generation and GitHub Pages
      publication in `doc/design.md`.
- [ ] Stop and ask user for a review of the design.

### Implementation

- [x] Add `doc/user_guide.md` with Maven Central dependency instructions,
      JavaDoc link, and usage examples.
- [x] Add or update GitHub workflow to build and publish JavaDoc API docs to
      GitHub Pages.
- [x] Update project documentation links in `README.md` to include the user
      guide.

### Verification

- [ ] Run OpenFastTrace and keep the trace clean.
- [ ] Run required Maven build checks from quality requirements.
- [ ] Validate the workflow changes are syntactically correct and reference
      existing repository paths.

## Version And Changelog Update

- [ ] Decide version impact for issue GH-90.
- [ ] Add a changelog entry for the next release if required.
