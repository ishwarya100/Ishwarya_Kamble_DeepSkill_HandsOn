# Microservices Exercises — Answer Sheet

Solutions for all microservices hands-on exercises, organized as one folder
per exercise. Every service is an independent Spring Boot 3.3 / Java 17 /
Maven project that can be built and run on its own with `mvn spring-boot:run`.

Where an exercise needed a database, an embedded H2 in-memory database is
used (no external DB setup required). Where an exercise needed OAuth2/OIDC
credentials, placeholder values are provided and clearly marked, since real
credentials must come from an actual identity provider.

## Index

| Folder | Exercise |
|---|---|
| `01-user-order-service` | User and Order Management System (REST, OpenFeign) |
| `02-inventory-eureka-config` | Inventory Management with Eureka Service Discovery + Config Server |
| `03-api-gateway-rate-limit-cache` | API Gateway with rate limiting, caching, path rewriting |
| `04-circuit-breaker-payment` | Resilient Payment Service with Resilience4j Circuit Breaker |
| `05-edge-service-routing-filtering` | Edge Service for routing and filtering |
| `06-api-gateway-load-balancing` | Load balancing in an API Gateway |
| `07-api-gateway-resilience` | Resilience patterns in an API Gateway |
| `08-oauth2-oidc-login` | Centralized authentication with OAuth 2.1/OIDC |
| `09-authorization-resource-server` | Authorization Server / Resource Server configuration |
| `10-jwt-auth-filter` | JWT-based secure communication |
| `11-account-loan-eureka-gateway` | Account + Loan microservices with Eureka and API Gateway |

Each folder has its own `README.md` with the exercise recap, design notes,
run instructions, and test commands.

## Prerequisites
- Java 17
- Maven 3.8+
- No external database, Redis, or OAuth provider required to run the code
  (H2/in-memory/mocked substitutes are used throughout, as noted per
  exercise).

## General run pattern
Every exercise folder contains one or more independent Maven projects.
Where an exercise involves multiple services (e.g. a Eureka server + one or
more clients + a gateway), start them in the order listed in that exercise's
README — usually: discovery/config server first, then downstream services,
then the gateway last.
