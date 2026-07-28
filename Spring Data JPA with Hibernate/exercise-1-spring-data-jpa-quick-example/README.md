# Exercise 1 - Spring Data JPA Quick Example

Covers ORM basics and the first Spring Data JPA
hands-on, built around the `Country` entity.

- Hands on 1: project setup, `Country` entity, `CountryRepository`,
  `CountryService.getAllCountries()`
- Hands on 6: find a country by code (`CountryNotFoundException` on a miss)
- Hands on 7: add a new country
- Hands on 8: update a country's name by code
- Hands on 9: delete a country by code

## Project structure

```
exercise-1-spring-data-jpa-quick-example/
├── pom.xml
├── src/main/java/com/cognizant/ormlearn/
│   ├── OrmLearnApplication.java     entry point, runs CountryDemo on startup
│   ├── model/Country.java
│   ├── repository/CountryRepository.java
│   ├── service/CountryService.java
│   ├── service/exception/CountryNotFoundException.java
│   └── demo/CountryDemo.java        test methods for every Hands on step above
└── src/main/resources/
    ├── application.properties
    └── sql/
        ├── schema.sql                country table only
        └── data.sql                  the full 249-country reference list
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

On startup, `schema.sql` creates the `country` table, `data.sql` loads
every country, then `CountryDemo` exercises every method listed above
and logs the results to the console (`org.hibernate.SQL=trace` shows
every generated SQL statement).