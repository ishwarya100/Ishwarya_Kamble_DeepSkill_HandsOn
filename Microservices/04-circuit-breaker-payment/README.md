# Exercise 4: Resilient Microservices with Circuit Breaker

## Task
A Payment Service calls a slow third-party API. Implement a Circuit Breaker
and fallback logic using Resilience4j, and log/monitor fallback events.

## Design
- `ThirdPartyPaymentClient` simulates the slow, failing third-party API
  (sleeps 3s then throws).
- `PaymentService.processPayment()` is annotated with `@CircuitBreaker`,
  pointing at a fallback method that returns a safe response instead of
  propagating the failure.
- `CircuitBreakerEventLogger` subscribes to the circuit breaker's event
  publisher on startup and logs every state transition (CLOSED → OPEN →
  HALF_OPEN) and every recorded error, satisfying the "log and monitor
  fallback events" requirement.
- Actuator's `circuitbreakers` endpoint is exposed to inspect live state.

## Run
```bash
cd payment-service
mvn spring-boot:run
# runs on http://localhost:8087
```

## Test
```bash
# each call is slow and fails, so the fallback response is returned;
# after enough failures within the sliding window the circuit opens
curl http://localhost:8087/payments/ORD-1
curl http://localhost:8087/payments/ORD-2
curl http://localhost:8087/payments/ORD-3

# inspect circuit breaker state and metrics
curl http://localhost:8087/actuator/circuitbreakers

# watch the console log for state-transition and error log lines
```
