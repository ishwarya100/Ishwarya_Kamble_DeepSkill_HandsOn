# Exercise 7: Resilience Patterns in an API Gateway

## Task
Implement resilience patterns in an API Gateway using Spring Boot 3, Spring
Cloud Gateway and Resilience4j.

## Design
- **downstream-service** (port 8095) exposes `/downstream/unstable`, which
  always throws, to simulate a failing dependency.
- **resilient-gateway** (port 9094) routes `/downstream/**` through a
  `CircuitBreaker` gateway filter (`exampleCircuitBreaker`). When the circuit
  is open, or the call fails, the request is forwarded to `/fallback` instead
  of returning an error to the caller.
- `ResilienceConfiguration` registers the default Resilience4j circuit
  breaker + time limiter configuration used by the gateway's circuit breaker
  filters.

## Run
```bash
cd downstream-service  && mvn spring-boot:run   # http://localhost:8095
cd resilient-gateway    && mvn spring-boot:run   # http://localhost:9094
```

## Test
```bash
# every call fails at downstream-service, so the gateway serves the fallback
# response instead of propagating the 500 error
curl http://localhost:9094/downstream/unstable

# after enough failures within the sliding window, the circuit opens and
# subsequent calls are short-circuited straight to the fallback
for i in $(seq 1 10); do curl http://localhost:9094/downstream/unstable; echo; done
```
