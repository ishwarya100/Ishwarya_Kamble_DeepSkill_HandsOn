# Employee Management System

Spring Boot application built with Spring Data JPA and Hibernate. It manages
employees and departments, backed by an in-memory H2 database.

## Prerequisites

- Java 17 or later
- Maven 3.6 or later

## Project Structure

```
EmployeeManagementSystem/
├── pom.xml
├── src/main/java/com/example/ems/
│   ├── EmployeeManagementSystemApplication.java
│   ├── config/           # auditing configuration
│   ├── entity/           # JPA entities (Employee, Department, Auditable)
│   ├── repository/       # Spring Data JPA repositories
│   ├── projection/       # interface based and class based projections
│   ├── service/          # business logic, pagination, batch processing
│   └── controller/       # REST controllers
└── src/main/resources/
    └── application.properties
```

## Setup and Run

1. Extract the zip file and move into the project folder:
   ```
   cd EmployeeManagementSystem
   ```

2. Build the project:
   ```
   mvn clean install
   ```

3. Run the application:
   ```
   mvn spring-boot:run
   ```

The application starts on `http://localhost:8080`.

The H2 console is available at `http://localhost:8080/h2-console`
(JDBC URL: `jdbc:h2:mem:testdb`, username: `sa`, password: `password`).

## API Endpoints

### Departments (`/api/departments`)
- `POST /api/departments` - create a department
- `GET /api/departments` - list all departments
- `GET /api/departments/{id}` - get a department by id
- `PUT /api/departments/{id}` - update a department
- `DELETE /api/departments/{id}` - delete a department
- `GET /api/departments/search?name=` - search departments by name

### Employees (`/api/employees`)
- `POST /api/employees` - create an employee
- `GET /api/employees/{id}` - get an employee by id
- `GET /api/employees?page=&size=&sortBy=&direction=` - paginated, sorted employee list
- `PUT /api/employees/{id}` - update an employee
- `DELETE /api/employees/{id}` - delete an employee
- `GET /api/employees/search?name=` - search employees by name
- `GET /api/employees/department/{departmentId}` - employees in a department
- `GET /api/employees/department-name/{departmentName}` - employees by department name (named query)
- `GET /api/employees/projections/department/{departmentId}` - interface based projection
- `GET /api/employees/summaries` - class based projection
- `POST /api/employees/batch` - bulk insert employees (Hibernate batch processing)

## Sample Request

Create a department:
```
POST /api/departments
Content-Type: application/json

{ "name": "Engineering" }
```

Create an employee in that department:
```
POST /api/employees
Content-Type: application/json

{
  "name": "Jane Doe",
  "email": "jane.doe@example.com",
  "department": { "id": 1 }
}
```
