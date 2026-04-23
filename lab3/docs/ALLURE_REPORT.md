# Allure Test Report

## Purpose

Allure is used to collect and present the automated Selenium test results for the GitHub use-case coverage defined in:

- `docs/document/USE_CASE.md`
- `docs/document/TEST_SCENARIOS.md`
- `docs/document/CHECKLIST.md`

The report is generated from JUnit 5 test execution results stored in `demo/target/allure-results`.

## Maven Configuration

Allure is configured in `demo/pom.xml` with:

- `io.qameta.allure:allure-junit5` for collecting JUnit 5 test results.
- `io.qameta.allure:allure-maven` for generating the HTML report.
- `org.junit.platform:junit-platform-launcher` to keep JUnit Platform discovery aligned with the current JUnit version.

The Allure results directory is also configured in:

- `demo/src/test/resources/allure.properties`

## Commands

Run all automated tests:

```bash
cd demo
mvn test
```

Generate the Allure HTML report:

```bash
mvn allure:report
```

Open the generated report:

```text
demo/target/allure-report/index.html
```
