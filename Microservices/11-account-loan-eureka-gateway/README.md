# Exercise 11: Account and Loan Microservices with Eureka and API Gateway

## Task
Create two microservices for a bank (account, loan), register them with a
Eureka discovery server, and expose them through a Spring Cloud API Gateway
with a global request-logging filter.

## Design
- **eureka-server** (port 8761) — service registry.
- **account-service** (port 8080) — `GET /accounts/{number}`, dummy response,
  no backend connectivity, as specified in the exercise.
- **loan-service** (port 8081) — `GET /loans/{number}`, dummy response.
- **api-gateway** (port 9090) — `spring.cloud.gateway.discovery.locator`
  is enabled, so every service registered in Eureka is automatically
  exposed as a route named after its (lower-cased) service id — no manual
  route config needed. `LogFilter` is a global filter that logs the URI of
  every request the gateway forwards.

## Run (in order)
```bash
cd eureka-server    && mvn spring-boot:run   # http://localhost:8761
cd account-service   && mvn spring-boot:run   # http://localhost:8080
cd loan-service      && mvn spring-boot:run   # http://localhost:8081
cd api-gateway        && mvn spring-boot:run   # http://localhost:9090
```
Confirm `ACCOUNT-SERVICE` and `LOAN-SERVICE` both appear at
`http://localhost:8761`.

## Test
```bash
curl http://localhost:9090/account-service/accounts/00987987973432
curl http://localhost:9090/loan-service/loans/H00987987972342

# check the api-gateway console log for the "====>Request URL ..." line
```
