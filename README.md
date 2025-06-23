# Transaction-System 🏦📊

*An in-memory, JSON-backed transaction API built with Spring Boot.*

<p align="center">
  <img src="https://img.shields.io/github/license/Adityarajsingh2904/transaction-system" alt="License">
  <img src="https://img.shields.io/github/languages/top/Adityarajsingh2904/transaction-system" alt="Top Language">
  <img src="https://img.shields.io/github/commit-activity/m/Adityarajsingh2904/transaction-system" alt="Commit Activity">
  <img src="https://img.shields.io/github/issues/Adityarajsingh2904/transaction-system" alt="Open Issues">
</p>

Transaction-System is a **lightweight RESTful service** for managing parent-child financial transactions. Powered by **Spring Boot 3**, it reads JSON files at runtime, exposes CRUD-like endpoints for trading data, and computes aggregates on demand.

---

## 📚 Table of Contents
1. [Key Features](#-key-features)
2. [Architecture & File Overview](#-architecture--file-overview)
3. [Branching & Version Policy](#-branching--version-policy)
4. [Data Model & Schema](#-data-model--schema)
5. [Installation & Setup](#-installation--setup)
6. [Usage & Endpoints](#-usage--endpoints)
7. [Error Handling & Validation](#-error-handling--validation)
8. [Testing & CI/CD](#-testing--ci-cd)
9. [Roadmap](#-roadmap)
10. [Contributing](#-contributing)
11. [License](#-license)

---

## ✨ Key Features
- 🔄 **JSON-Backed Data**: Uses `Parent.json` and `Child.json` as in-memory data sources.
- 🚀 **Spring Boot 3**: Rapid development with embedded Tomcat and auto-configuration.
- 🗃️ **RESTful API**: CRUD-like operations for transactions and installments.
- 📈 **Aggregate Computations**: Calculates sum of child payments per parent on the fly.
- 📦 **Standalone JAR & Docker**: Run locally or in a container via the provided `Dockerfile`.

---

## 🏗 Architecture & File Overview
```
transaction-system/
├── Parent.json               # Sample parent transaction records
├── Child.json                # Sample child installment records
├── Dockerfile                # Multi-stage Docker build
├── mvnw, mvnw.cmd            # Maven wrapper scripts
├── .mvn/                     # Maven wrapper binaries
├── pom.xml                   # Project dependencies & build config
├── LICENSE                   # MIT License
├── .gitignore                # Ignore build artifacts
└── src/
    └── main/
        ├── java/com/example/transaction/
        │   ├── TransactionApplication.java   # Spring Boot entry point
        │   ├── controller/
        │   │   └── TransactionController.java # REST endpoints
        │   ├── service/
        │   │   ├── TransactionService.java   # Parent logic & aggregation
        │   │   └── InstallmentService.java   # Child lookup logic
        │   └── model/
        │       ├── Parent.java               # Parent POJO
        │       ├── Child.java                # Child POJO
        │       └── Installment.java          # Installment mapping
        └── resources/
            └── application.properties       # Spring configuration
```

---

## 🌳 Branching & Version Policy
| Branch          | Status      | Purpose                                    |
|-----------------|-------------|--------------------------------------------|
| **`main`**      | 🔵 Stable    | Production-ready code with tags/releases.  |
| `feature/*`     | 🛠️ In progress | New features, experimental work.          |
| `hotfix/*`      | 🚨 Urgent    | Critical fixes against main branch.       |
| `release/*`     | 🚀 Release   | Prepared for public or internal releases. |

> All merges to `main` require at least one review and passing CI checks.

---

## 📊 Data Model & Schema
**Parent.json** sample:
```json
[  { "id": 1, "sender": "Alice", "receiver": "Bob", "totalAmount": 1000, "createdAt": "2025-06-01T12:00:00" },  ...]
```
**Child.json** sample:
```json
[  { "id": 101, "parentId": 1, "paidAmount": 200, "paidAt": "2025-06-05T09:30:00" },  ...]
```
- **Parent**: Unique `id`, financial metadata, and timestamp.
- **Child**: Tied to `parentId`, tracks individual payments.

---

## ⚙️ Installation & Setup

> **Note:** Build artifacts in the `target/` directory are already ignored via `.gitignore` to keep your working copy clean.

1. **Clone** the repo:
   ```bash
   git clone https://github.com/Adityarajsingh2904/transaction-system.git
   cd transaction-system
   ```
2. **Ensure Java 17**+ is installed and `JAVA_HOME` is set.
   If `./mvnw` complains about a missing `JAVA_HOME`, point it to your JDK:
   ```bash
   export JAVA_HOME=/path/to/your/jdk
   ```
3. **Build** the project:
   ```bash
   ./mvnw clean package -DskipTests
   ```
   Or set `JAVA_HOME` inline when running the wrapper:
   ```bash
   JAVA_HOME=/path/to/your/jdk ./mvnw clean install
   ```
4. **Run** locally:
   ```bash
   java -jar target/transaction-0.0.1-SNAPSHOT.jar
   ```
5. **Docker** (optional):
   ```bash
   docker build -t transaction-system .
   docker run -p 8080:8080 transaction-system
   ```

---

## 🚀 Usage & Endpoints
| Method | URI                       | Description                                    |
|--------|---------------------------|------------------------------------------------|
| GET    | `/parent`                 | List all parent transactions                   |
| GET    | `/parent/{id}`            | Get parent and aggregated child sum            |
| GET    | `/child`                  | List all child installments                    |
| GET    | `/child?parentId={id}`    | List installments for a specific parent        |

**Example**:
```bash
curl http://localhost:8080/parent/1
# → { "id":1, "sender":"Alice", "receiver":"Bob", "totalAmount":1000, "sumPaid":200 }
```

---

## 🛡️ Error Handling & Validation
- Returns **404 Not Found** if `parentId` or `child` resources are missing.
- **400 Bad Request** for invalid query parameters.
- Wraps JSON parse errors into **503 Service Unavailable** with clear messages.

---

## 🔧 Testing & CI/CD
> **Note**: No tests currently included. Recommended additions:
- **Unit Tests** with JUnit 5 & Mockito for service logic.
- **Integration Tests** using Spring Boot Test for endpoints.
- **GitHub Actions** workflow to run `mvn test` on PRs.

---

## 🗺️ Roadmap
- [ ] Persist data in **PostgreSQL** or **H2** instead of JSON files.
- [ ] Add **Swagger/OpenAPI** documentation at `/swagger-ui.html`.
- [ ] Implement **CRUD** endpoints for POST/PUT/DELETE.
- [ ] Introduce **security** with Spring Security (JWT).

---

## 🤝 Contributing
1. Fork the repo & create a `feature/xyz` branch.
2. Write clear, atomic commits and add tests.
3. Open a PR against `main` and tag reviewers.

Please adhere to the branch & version policy above.

---

## 📄 License
Licensed under the **MIT License**. See [LICENSE](LICENSE) for details.

---

> _Generated with ❤️ by Aditya Raj Singh_