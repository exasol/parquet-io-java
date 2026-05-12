# Quality Requirements

This chapter documents architecture-relevant quality requirements and technical quality goals.

User-facing acceptance scenarios are defined in [System Requirements](../system_requirements.md).

Terms such as `Parquet`, `Row`, `Row Group`, `OpenFastTrace`, and `OFT` use the definitions from [System Requirements](../system_requirements.md).

## Requirement Quality

We have the following requirement hierarchy in this project:

1. `feat`: Top level feature as you would find on a product sheet (limited number)
2. `req`: High level requirement
3. `dsn`: Design requirement
   `constr`: Technical constraint
4. `impl`: Implementation
   `utest`: Unit test
   `itest`: Integration test

## Code Quality

1. Production code and test code follow clean-code principles.
2. The implementation prefers speaking names over explanatory comments. Comments are only acceptable when intent cannot be expressed clearly in code.
3. Abbreviations are only used where they are guaranteed common (meaning everyone knows them) otherwise all symbol names are written out.
4. Methods avoid side effects where possible.
5. Methods stay short and keep cyclomatic complexity low. When behavior grows beyond a small, readable unit, the design is refactored into smaller collaborating types or methods.
6. When the cyclomatic complexity is too high, use extract method refactoring.
7. APIs (not implementation) are documented with JavaDoc: interfaces, abstract classes, methods, parameters, return values, side effects (if any), purpose
8. All objects that can be, are immutable.
9. The implementation prefers static object allocation over dynamic allocation where possible.
10. All method parameters are final. Output parameters are only allowed when they are used in external libraries.

### Test Code Guideline

1. Test method names have descriptive names starting with `test`.
2. Method parameters are final.
3. The project uses Hamcrest matchers.
4. If possible each test method has only a single assert.
5. If multiple asserts are necessary and the latter asserts are not a follow-up symptom of the previous ones, the asserts must be wrapped in `assertAll`.
6. When exceptions are asserted, then both type and message are validated.
7. Where possible similar tests are bundled into parameterized tests (e.g., when multiple variants of input are tested against the same implementation method).
8. Prefer parameterized tests over tests that exercise different scenarios of the same method under test with multiple asserts.
9. Parameter validation is tested with multiple valid and invalid inputs. Testing valid and invalid input is done in separate test methods.

## Dependency Policy

Parquet IO Java uses the minimum set of dependencies required for:

* reading Apache Parquet files
* converting Parquet rows to Java objects
* running automated tests and build quality gates

Additional libraries are not allowed by default. Any new third-party dependency requires an explicit design decision and approval before it is added to the build.

## Static Analysis And Security Gates

Static code analysis runs in SonarQube Cloud and acts as a CI build breaker. A failing quality gate blocks integration until the reported issues are resolved or an approved exception exists.

OpenFastTrace tracing runs as a build breaker for the specification artifacts in scope. The trace stays clean for the requirement and design artifact types used by the project.

The Maven build applies the latest OpenFastTrace Maven plugin version approved for the project and executes requirement tracing locally and in CI through the standard `verify` lifecycle.

## Testability And Coverage

Automated tests use JUnit 5 together with Hamcrest matchers.

Path coverage across the code base stays at or above 80%. Coverage below that threshold fails the build unless a documented exception is accepted in advance.

The architecture favors testable units with clear boundaries so the coverage target can be met without relying mainly on brittle UI-level tests.

## Platform Compatibility

The required Java version follows the Java version configured in the Maven build. The project does not introduce a language level or bytecode target that exceeds that build configuration.
