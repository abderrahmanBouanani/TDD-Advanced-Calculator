# TDD Advanced Calculator

An advanced calculator implementation demonstrating Test-Driven Development (TDD) practices in Java.

## Features
- **Arithmetic**: Add, Subtract, Multiply, Divide, Modulo, Power, Absolute Value, IsEven.
- **Architecture**: Uses Dependency Injection with `MathLogger` and `SecurityValidator`.
- **Testing Standard**: Strict Red-Green-Refactor TDD cycle.
- **Tech Stack**: Java 17, Maven, JUnit 5, AssertJ, Mockito.
- **Quality**: Logic coverage > 90% (verified by JaCoCo).

## Running Tests
To execute all unit tests:
```bash
mvn clean test
```

## Code Coverage
To generate the coverage report:
```bash
mvn clean verify -Pcoverage
```
The report will be available at `target/site/jacoco/index.html`.

## Project Structure
- `src/main/java`: Source code (`AdvancedCalculator`, `MathLogger`, `SecurityValidator`).
- `src/test/java`: Unit tests using Mockito and AAA pattern.
- `src/Rapport`: LaTeX project report.
