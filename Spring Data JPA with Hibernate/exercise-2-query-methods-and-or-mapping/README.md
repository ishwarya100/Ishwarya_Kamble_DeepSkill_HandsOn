# Exercise 2 - Query Methods and O/R Mapping

Covers Spring Data JPA Query Methods and
Object/Relational mapping relationships.

- Hands on 1: `Country` Query Methods - partial name search (search box),
  starting-with search (alphabet index), both sorted ascending by name
- Hands on 2: `Stock` Query Methods - date range, greater-than close
  price, top-3 highest volume overall, top-3 lowest volume for a code
- Hands on 3: `Employee`, `Department`, `Skill` entities and repositories
- Hands on 4: `@ManyToOne` / `@JoinColumn` from Employee to Department,
  get/add/update an employee
- Hands on 5: `@OneToMany` from Department to Employee
- Hands on 6: `@ManyToMany` between Employee and Skill, adding a skill
  to an employee

## Project structure

```
exercise-2-query-methods-and-or-mapping/
├── pom.xml
├── src/main/java/com/cognizant/ormlearn/
│   ├── OrmLearnApplication.java     entry point, runs the 3 demos below on startup
│   ├── model/                       Country, Stock, Department, Skill, Employee
│   ├── repository/                  one repository per entity, with the Query Methods
│   ├── service/                     one service per entity
│   └── demo/
│       ├── CountryDemo.java         partial-name and starting-with searches
│       ├── StockDemo.java           date range, greater-than, top-N queries
│       └── EmployeeDemo.java        many-to-one, one-to-many, many-to-many demos
└── src/main/resources/
    ├── application.properties
    └── sql/
        ├── schema.sql                country, stock, department, skill, employee, employee_skill
        └── data.sql                  country list, sample stock rows matching the
                                       document's expected query results, and sample
                                       department/skill/employee data
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
`OrmLearnApplication.DemoRunner` runs `CountryDemo`, `StockDemo` and
`EmployeeDemo` in turn, logging results to the console
(`org.hibernate.SQL=trace` shows every generated SQL statement).
