# Transaction System 
## 📂 Project Structure

```bash
transaction/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/transaction/
│   │   │       ├── controller/
│   │   │       ├── service/
│   │   │       ├── model/
│   │   │       └── TransactionApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
├── Parent.json
├── Child.json
├── pom.xml
└── README.md

# Transaction BE

## Requirements

For building and running the application you need:

- [Maven 3](https://maven.apache.org/download.cgi)
- Java 17+ (or the version specified in `pom.xml`)

---

## 🚀 Running the application locally

There are several ways to run a Spring Boot application on your local machine.

### ▶️ Option 1: Using your IDE

Execute the `main` method in the  
`com.example.transaction.TransactionApplication` class.

### ▶️ Option 2: Using Maven

Make sure you're in the project root directory, then run:

```bash
./mvnw spring-boot:run
