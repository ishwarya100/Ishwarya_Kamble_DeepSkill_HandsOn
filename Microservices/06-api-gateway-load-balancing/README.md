# Exercise 6: Load Balancing in an API Gateway

## Task
Implement load balancing in an API Gateway using Spring Boot 3, Spring Cloud
Gateway and Spring Cloud LoadBalancer.

## Design
- **eureka-server** (port 8761) — service registry.
- **example-service** — registers as `example-service`; run two instances on
  different ports so there is something to load-balance across.
- **lb-gateway** (port 9093) — routes `/loadbalanced/**` to `lb://example-service`
  (resolved through Eureka) and uses a custom `RandomLoadBalancer`, wired in
  via `@LoadBalancerClient(name = "example-service", configuration = ...)`,
  instead of the default round-robin balancer.

## Run
```bash
cd eureka-server && mvn spring-boot:run                                   # http://localhost:8761

cd example-service && mvn spring-boot:run                                 # instance 1, port 8091
cd example-service && mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8092  # instance 2

cd lb-gateway && mvn spring-boot:run                                      # http://localhost:9093
```
Confirm both `example-service` instances show up under the same application
name at `http://localhost:8761`.

## Test
```bash
# call repeatedly and observe the port in the response alternate/vary
# between 8091 and 8092, confirming random load balancing
for i in $(seq 1 6); do curl http://localhost:9093/loadbalanced/hello; echo; done
```
