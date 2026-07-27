# Library Management – Using Spring Core & Maven

This package contains 9 self-contained Maven projects, one per exercise.
Each folder can be opened and run independently.

## Prerequisites

- JDK 8 or higher installed (JDK 17 required for Exercise 9 – Spring Boot)
- Apache Maven installed and available on your PATH
- An internet connection the first time you build (Maven downloads dependencies from Maven Central)

Check your setup:
```
java -version
mvn -version
```

## Exercises 1, 2, 3, 4, 5, 6, 7, 8 (Plain Spring Core / Spring AOP)

Each of these folders is a plain Java + Spring project with a `MainApp` class.
The `exec-maven-plugin` is already wired into each `pom.xml`, so from inside
the exercise folder you only need one command:

```
cd Exercise1_BasicSpringApplication (or any other folder you want to open)
mvn compile exec:java
```

(replace the folder name with whichever exercise you're running — the
`mainClass` is already configured in each project's `pom.xml`, so you don't
need to pass it on the command line).

Alternatively, you can just open the folder in an IDE (Eclipse / IntelliJ) as
a Maven project and run the `MainApp` class directly — the IDE resolves the
Maven dependencies automatically.

| Folder | What it demonstrates |
|---|---|
| Exercise1_BasicSpringApplication | Basic Spring project, beans defined in `applicationContext.xml` |
| Exercise2_DependencyInjection | Setter-based DI wiring `BookRepository` into `BookService` |
| Exercise3_AOPLogging | Spring AOP `@Around` advice logging method execution time |
| Exercise4_MavenProjectConfig | Maven `pom.xml` with Spring Context/AOP/WebMVC and Java 1.8 compiler plugin |
| Exercise5_IoCContainer | Central IoC container configuration for both beans |
| Exercise6_AnnotationConfig | `@Service` / `@Repository` with component scanning, no explicit bean tags |
| Exercise7_ConstructorSetterInjection | `BookService` wired via both constructor and setter injection |
| Exercise8_BasicAOP | `@Before` / `@After` advice with AspectJ auto-proxying |

Expect console output from each `MainApp` confirming the Spring context loaded
and the relevant behaviour (DI, AOP logs, etc.) worked.

## Exercise 9 – Spring Boot REST Application

```
cd Exercise9_SpringBootApplication
mvn spring-boot:run
```

The app starts on `http://localhost:8080`. Test the REST endpoints, e.g. with curl:

```
curl -X POST http://localhost:8080/api/books -H "Content-Type: application/json" -d "{\"title\":\"Effective Java\",\"author\":\"Joshua Bloch\"}"
curl http://localhost:8080/api/books
curl http://localhost:8080/api/books/1
curl -X PUT http://localhost:8080/api/books/1 -H "Content-Type: application/json" -d "{\"title\":\"Effective Java 3rd Edition\",\"author\":\"Joshua Bloch\"}"
curl -X DELETE http://localhost:8080/api/books/1
```

The H2 in-memory database console is available at `http://localhost:8080/h2-console`
(JDBC URL: `jdbc:h2:mem:librarydb`, user: `sa`, no password).

## Notes

- Every project uses only the Maven/JVM commands mentioned above; no additional
  build tools are required.
- Dependency versions: Spring Framework 5.3.31 for Exercises 1–8 (Java 8 compatible,
  as required by Exercise 4), Spring Boot 3.2.5 for Exercise 9 (requires Java 17).
