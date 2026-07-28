# Spring REST & JWT Hands-On

This submission contains 5 self-contained Maven projects, one for each hands-on exercise. Every folder builds on the previous one, so the code is cumulative — folder 5 contains the complete application with everything from folders 1-4 plus JWT security.

| Folder | Covers |
|---|---|
| `01-spring-core-basics` | Spring Boot project creation, Spring XML configuration, bean scopes, logging |
| `02-rest-get-services` | RESTful GET services, `@PathVariable`, exception handling, MockMvc tests |
| `03-employee-department-services` | Employee/Department REST services with Controller-Service-Dao layers |
| `04-post-put-delete-validation` | POST/PUT/DELETE services, bean validation, global exception handling |
| `05-jwt-security` | Spring Security with in-memory users and JWT based authentication/authorization |

All projects use:
- **Group Id:** `com.cognizant`
- **Artifact Id:** `spring-learn`
- **Base package:** `com.cognizant.springlearn`
- **Java version:** 17
- **Spring Boot version:** 2.7.18
- **Default port:** `8090` (from folder 2 onward, set in `application.properties`)

## Prerequisites

- JDK 17
- Maven 3.6+
- An IDE (Eclipse / IntelliJ / VS Code) — optional, only needed to run classes directly
- Postman or curl for testing the REST services

## Building and Running a Project

Open a terminal in any exercise folder and run:

```bash
mvn clean package
```

This compiles the project, runs the tests, and produces a runnable jar in `target/`.

To start the application:

```bash
mvn spring-boot:run
```

or

```bash
java -jar target/spring-learn-0.0.1-SNAPSHOT.jar
```

To run only the tests:

```bash
mvn clean test
```

> If your network requires a proxy, append the proxy flags to the above commands, for example:
> `mvn clean package -Dhttp.proxyHost=<host> -Dhttp.proxyPort=<port>`

## Folder 1 — Spring Core Basics

No REST endpoints yet. Run the application and check the console log:

```bash
mvn spring-boot:run
```

The log shows:
- The parsed date from `date-format.xml`
- The `India` country bean loaded from `country.xml` (also demonstrates singleton scope)
- The list of four countries loaded from `country.xml`

## Folder 2 — REST GET Services

Start the application (port `8090`), then try:

```bash
curl http://localhost:8090/hello
curl http://localhost:8090/country
curl http://localhost:8090/countries
curl http://localhost:8090/countries/in
curl -i http://localhost:8090/countries/az
```

Run the MockMvc tests:

```bash
mvn clean test
```

## Folder 3 — Employee and Department Services

```bash
curl http://localhost:8090/employees
curl http://localhost:8090/departments
```

## Folder 4 — POST / PUT / DELETE with Validation

```bash
# create a country (validated)
curl -i -H 'Content-Type: application/json' -X POST -d '{"code":"IN","name":"India"}' http://localhost:8090/countries

# invalid country code triggers the global exception handler
curl -i -H 'Content-Type: application/json' -X POST -d '{"code":"I","name":"India"}' http://localhost:8090/countries

# update an employee
curl -i -H 'Content-Type: application/json' -X PUT -d '{"id":1,"name":"Ravi Kumar","salary":60000,"permanent":true,"dateOfBirth":"12/05/1990"}' http://localhost:8090/employees

# delete an employee
curl -i -X DELETE http://localhost:8090/employees/1
```

## Folder 5 — JWT Security

Every endpoint except `/authenticate` now requires an authenticated user (in-memory users `admin` and `user`, both with password `pwd`).

1. Get a token:

```bash
curl -s -u user:pwd http://localhost:8090/authenticate
```

This returns `{"token":"..."}`.

2. Call any service using the returned token as a Bearer token:

```bash
curl -s -H "Authorization: Bearer REPLACE_TOKEN_HERE" http://localhost:8090/countries
```

3. A request without a token, or with an invalid one, is rejected with `401 Unauthorized`.

You can also call the services with HTTP Basic authentication directly, since Basic auth is still enabled alongside the JWT filter:

```bash
curl -s -u user:pwd http://localhost:8090/countries
```

## Importing into an IDE

For Eclipse: `File > Import > Maven > Existing Maven Projects`, then browse to the exercise folder and finish.

For IntelliJ / VS Code: open the exercise folder directly — both IDEs auto-detect the Maven project from `pom.xml`.
