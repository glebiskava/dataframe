# DataFrame Project

[![License](https://img.shields.io/badge/LICENSE-MIT-1B56FD)](LICENSE)
[![Java 17](https://img.shields.io/badge/Java-17-E78B48)](https://adoptium.net/)
[![Maven](https://img.shields.io/badge/Maven-3.8.6-F7374F)](https://maven.apache.org/)
![Coverage](https://img.shields.io/badge/Coverage-61%25-green?logo=jacoco)
[![Docker](https://img.shields.io/badge/Docker-Image-2496ED?logo=docker&logoColor=white)](https://hub.docker.com/r/ouassimboulkamh/dataframe)


A Java library for tabular data manipulation with comprehensive CSV support and data analysis capabilities.

## Features

- **CSV Handling**
  - Robust CSV parsing with type inference for integer, double, and string columns
  - Support for missing values (`null` handling)
  - Automatic null value detection and handling

- **Data Manipulation**
  - Row/column selection and filtering using SQL-like syntax (`age > 25`, `name = Alice`)
  - Head/Tail preview functionality
  - Data validation and schema enforcement

- **Statistical Operations**
  - Column statistics: mean, sum, min, max, count
  - Handling of null values in calculations
  - Type-safe operations for numeric columns

- **Data Representation**
  - Tabular printing with aligned columns
  - Multi-type column support (String, Integer, Double)
  - Missing value visualization in printed output

- **Infrastructure**
  - Docker containerization with multi-stage build
  - GitHub Packages deployment

## Tool Choices

- **Core Stack**
  - Java 17: For modern language features and LTS support
  - Maven: Build automation and dependency management
  - JUnit 5: Unit testing framework

- **CI/CD**
  - GitHub Actions: Automated testing, packaging, and deployment
  - Jacoco: Code coverage reporting (60%+ coverage)

- **Containerization**
  - Docker: Multi-stage builds for optimized image sizes (~64MB final image)
  - Docker Hub: Image registry with automated builds

- **Documentation**
  - GitHub Pages: Hosted documentation
  - Maven Site Plugin: Project reports and metrics

## Git Workflow

1. **Branching Strategy**:
   - `main`: Production-ready code
   - `dev`: Integration branch for features
   - Feature branches: `feature/feature-name` (e.g., `feature/csv-parser`)

2. **Code Validation**:
   - All pull requests require:
     - 1 peer code reviews
     - Passing CI pipeline (tests, coverage, build)
     - Jacoco coverage threshold enforcement (minimum 60%)
   - Automated checks:
     - Unit tests (including parameterized tests)
     - Integration tests with edge cases

## Docker Images

**Production Image**:  
[`ouassimboulkamh/dataframe:latest`](https://hub.docker.com/r/ouassimboulkamh/dataframe)
- Size: 64MB
- Layered build:  
  - Builder stage: Maven 3.8.5 + OpenJDK 17
  - Runtime stage: Eclipse Temurin 17 JRE (alpine)
- Usage:
  ```bash
  docker pull ouassimboulkamh/dataframe:latest
  docker run ouassimboulkamh/dataframe:latest
  ```

## Feedback & Experience

**Maven**
- Positive: Effective dependency management and plugin ecosystem
- Challenge: Initial complexity in POM configuration for multi-module setups

**JUnit 5**
- Positive: Parameterized tests significantly improved test coverage
- Challenge: Learning curve for complex test scenarios with null values

**GitHub Actions**
- Positive: Seamless integration with Java ecosystem tools
- Challenge: Debugging workflow failures required YAML expertise

**Docker**
- Positive: Multi-stage builds dramatically reduced image sizes
- Challenge: Initial secure setup

**Git Workflow**
- Positive: Feature branches prevented main branch pollution
- Challenge: Merge conflicts resolution required team coordination

**Collaboration**
- Pair programming proved invaluable for complex features like CSV type inference
- GitHub's code review tools enhanced code quality through collaborative reviews

---

**Contributors**:  
[BOULKAMH Mohamed](https://github.com/ouassim-boulkamh) • 
[MITTELBERGER Léopold](https://github.com/glebiskava) • 
[NGUYEN Huynh Man](https://github.com/nhman2002) • 
