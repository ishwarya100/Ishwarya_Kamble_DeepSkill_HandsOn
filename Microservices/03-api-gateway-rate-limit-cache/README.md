# Exercise 3: API Gateway with Rate Limiting, Caching and Path Rewriting

## Task
Create an API Gateway that routes requests to a Customer Service and a
Billing Service, with rate limiting, caching, and path rewriting.

## Design
- **customer-service** (port 8085) — `GET /customers/{id}`
- **billing-service** (port 8086) — `GET /bills/{id}`
- **api-gateway** (port 9091) — Spring Cloud Gateway with, per route:
  - `RewritePath` — external path `/customer/**` and `/billing/**` are
    rewritten to the real downstream paths `/customers/**` and `/bills/**`.
  - `SimpleRateLimiter` — a small custom `GatewayFilterFactory` that limits
    each client IP to N requests per time window using an in-memory counter
    (avoids requiring a Redis instance, which the built-in
    `RequestRateLimiter` filter needs).
  - `LocalResponseCache` — Spring Cloud Gateway's built-in in-memory response
    cache, with a 30 second TTL.

## Run
```bash
cd customer-service && mvn spring-boot:run   # http://localhost:8085
cd billing-service  && mvn spring-boot:run   # http://localhost:8086
cd api-gateway       && mvn spring-boot:run   # http://localhost:9091
```

## Test
```bash
# routed + path-rewritten to customer-service
curl http://localhost:9091/customer/101

# routed + path-rewritten to billing-service
curl http://localhost:9091/billing/55

# fire more than 5 requests within 10 seconds to trigger 429 Too Many Requests
for i in $(seq 1 7); do curl -i http://localhost:9091/customer/101; done

# repeated identical request within 30s is served from the local cache
curl http://localhost:9091/billing/55
```
