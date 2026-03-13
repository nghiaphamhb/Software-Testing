# Вопросы к защите лабораторной работы (ДОП)

## SCA (Software Composition Analysis)

SCA — это проверка сторонних библиотек и зависимостей, чтобы найти:

- известные уязвимости (CVE)

- риски лицензий (GPL, AGPL и др.)

## Dependency tree: Direct vs transitive dependencies

- Direct dependencies — зависимости, которые вы указали сами (в package.json, pom.xml, requirements.txt и т.д.)

- Transitive dependencies — зависимости, которые подтягиваются автоматически (часто содержат CVE)

Пример (Maven): ```mvn dependency:tree```

## CVE (Common Vulnerabilities and Exposures)

### Что такое CVE?

CVE — это уникальный идентификатор уязвимости.

Зачем нужен:

- помогает говорить об одной уязвимости одинаково (например: CVE-2021-44228)

- удобно искать в базах данных (NVD, GitHub Advisories, OSV)

- используется SCA-инструментами для связи зависимостей и уязвимостей

### CVSS (Common Vulnerability Scoring System)

CVSS - оценка уязвимости CVE:

- 0.1–3.9: Low

- 4.0–6.9: Medium

- 7.0–8.9: High

- 9.0–10: Critical

Часто pipeline останавливается при:

- CVSS ≥ 7

- или severity ≥ High

###  Базы данных CVE
- [NVD (National Vulnerability Database)](https://nvd.nist.gov/)
- [OSV (Open Source Vulnerabilities – Google)](https://osv.dev/)
- [GitHub Security Advisories (GHSA)](https://github.com/advisories)

## False positives

False positive — это ситуация, когда инструмент показывает уязвимость, но на самом деле проект не влияется.

Особенно часто это бывает в OWASP Dependency-Check, потому что он использует CPE и иногда ошибается при сопоставлении.

## Suppression file (OWASP DC)

Suppression это способ игнорировать ложные предупреждения.

Это файл (обычно `suppression.xml`), где указано:

- какие уязвимости игнорировать

- по какому условию (CVE, CPE, имя пакета, путь и т.д.)

- можно добавить комментарий (очень рекомендуется)

Пример:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<suppressions xmlns="https://jeremylong.github.io/DependencyCheck/dependency-suppression.1.3.xsd">
  <suppress>
    <notes>False positive: component mapping mismatch</notes>
    <cve>CVE-2023-12345</cve>
  </suppress>
</suppressions>
```

Запуск:
```bash
mvn org.owasp:dependency-check-maven:check \
  -DsuppressionFile=dependency-check-suppressions.xml
```

## Ignore policy (Snyk)

В Snyk есть механизм ignore (игнорирование):

- по ID уязвимости

- по пути зависимости

- можно указать срок (expiry date)

Пример файла `.snyk`:
```xml
ignore:
  SNYK-JAVA-ORGAPACHELOGGINGLOG4J-2314720:
    - expires: "2026-03-31T00:00:00.000Z"
      reason: "False positive"
```

Запуск:
```bash
snyk test
```

## OWASP Dependency-Check

[**OWASP Dependency-Check**](https://jeremylong.github.io/DependencyCheck/) - это open-source инструмент SCA для поиска CVE в зависимостях.

Как он работает:

- анализирует зависимости проекта

- определяет компоненты и их CPE

- ищет уязвимости CVE в базе (например, NVD) и создаёт отчёт

Иногда бывают false positives → тогда используют suppression.

## CPE (Common Platform Enumeration)

CPE - это стандартное имя продукта, которое используется для связи с CVE.

Пример: `cpe:2.3:a:vendor:product:version:update:edition:language:sw_edition:target_sw:target_hw:other`

## SBOM (Software Bill of Materials)

SBOM — это список всех компонентов в вашем приложении.

Он содержит:

- библиотеки

- версии

- идентификаторы

Просто:

- SBOM = список “что используется”

- SCA = проверка этого списка на уязвимости