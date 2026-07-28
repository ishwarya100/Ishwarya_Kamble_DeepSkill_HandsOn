# Exercise 3 - HQL, JPQL and Criteria Query

Covers Hibernate Query Language, JPQL, the
`fetch` keyword, aggregate functions, native queries and Criteria Query.

- Hands on 1: introduction to HQL and JPQL (reference material, no code)
- Hands on 2: all permanent employees using HQL, with department and
  skill list populated via an explicit `JOIN FETCH`
- Hands on 3: fetch quiz attempt details using HQL - joins `user`,
  `attempt`, `attempt_question`, `question`, `attempt_option` and
  `options`, printing each question with its options, scores and
  whether that option was the one selected
- Hands on 4: average salary using HQL, overall and filtered by
  department id
- Hands on 5: get all employees using a native query
- Hands on 6: Criteria Query - this hands-on is a conceptual walkthrough
  of an external reference link in the document and does not specify
  any code to write, so no Criteria Query class is included

## Project structure

```
exercise-3-hql-jpql-and-criteria-query/
├── pom.xml
├── src/main/java/com/cognizant/ormlearn/
│   ├── OrmLearnApplication.java     entry point, runs the 2 demos below on startup
│   ├── model/
│   │   ├── Department.java, Skill.java, Employee.java
│   │   └── quiz/                    AppUser, Question, Option, Attempt,
│   │                                AttemptQuestion, AttemptOption
│   ├── repository/
│   │   ├── EmployeeRepository.java  permanent employees, average salary, native query
│   │   └── quiz/AttemptRepository.java
│   ├── service/
│   │   ├── EmployeeService.java
│   │   └── quiz/AttemptService.java
│   └── demo/
│       ├── HqlDemo.java             Hands on 2, 4 and 5
│       └── QuizDemo.java            Hands on 3
└── src/main/resources/
    ├── application.properties
    └── sql/
        ├── schema.sql                department, skill, employee, employee_skill, plus quiz schema
        └── data.sql                  sample employee data and quiz sample data matching
                                       the exact questions, options, scores and selected
                                       answers shown in the document
```

## Software prerequisites

- JDK 17+
- Maven 3.6+
- MySQL Server 8.0
- MySQL Workbench 8 (optional, for browsing the data)

## Setup

1. Create the schema in MySQL:

   ```
   mysql -u root -p
   mysql> create schema ormlearn;
   ```

2. Open `src/main/resources/application.properties` and adjust
   `spring.datasource.username` / `spring.datasource.password` if your
   local MySQL credentials differ from `root` / `root`.

3. Build the project:

   ```
   mvn clean package
   ```

## Run

```
mvn spring-boot:run
```

or run the packaged jar:

```
java -jar target/orm-learn-0.0.1-SNAPSHOT.jar
```

On startup, `schema.sql`/`data.sql` set up every table, then
`OrmLearnApplication.DemoRunner` runs `HqlDemo` and `QuizDemo` in turn,
logging results to the console (`org.hibernate.SQL=trace` shows every
generated SQL statement).
