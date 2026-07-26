# Exercise 1: User and Order Management System

## Task
Build two microservices:
- **user-service** — manages users
- **order-service** — manages orders, and calls user-service (via OpenFeign) to
  validate a user before placing an order

## Design
- Each service is an independent Spring Boot 3 Maven project with its own REST
  API and its own embedded H2 database (`user-service` uses `userdb`,
  `order-service` uses `orderdb`).
- Communication is done with **OpenFeign** (`UserClient`), calling user-service
  directly by URL. In exercise 2 the same pattern is extended to use Eureka for
  discovery instead of a hardcoded URL.

## Run
```bash
# terminal 1
cd user-service
mvn spring-boot:run
# runs on http://localhost:8081

# terminal 2
cd order-service
mvn spring-boot:run
# runs on http://localhost:8082
```

## Test
```bash
# create a user
curl -X POST http://localhost:8081/users -H "Content-Type: application/json" \
  -d '{"name":"Alice","email":"alice@example.com"}'

# create an order for that user (id from previous response)
curl -X POST http://localhost:8082/orders -H "Content-Type: application/json" \
  -d '{"userId":1,"product":"Laptop","amount":75000}'

# ordering for a non-existent user returns 400 Bad Request
curl -X POST http://localhost:8082/orders -H "Content-Type: application/json" \
  -d '{"userId":99,"product":"Phone","amount":20000}'
```
