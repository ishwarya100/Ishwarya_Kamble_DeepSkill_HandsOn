# Exercise 5: Edge Service for Routing and Filtering

## Task
Implement an edge service for routing and filtering requests using Spring
Boot 3 and Spring Cloud Gateway.

## Design
- **example-service** (port 8090) exposes `GET /example/hello`.
- **edge-gateway** (port 9092) routes any `/example/**` path to
  `example-service`, and applies a global `LoggingFilter` that logs the URI
  of every request passing through the gateway.

## Run
```bash
cd example-service && mvn spring-boot:run   # http://localhost:8090
cd edge-gateway     && mvn spring-boot:run   # http://localhost:9092
```

## Test
```bash
curl http://localhost:9092/example/hello
# -> "Hello from example-service"
# check edge-gateway console output for the logged request URI
```
