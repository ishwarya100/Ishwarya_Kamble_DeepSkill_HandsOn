# Exercise 2: Inventory Management System with Service Discovery

## Task
Build:
- **product-service** — manages products and stock
- **inventory-service** — tracks stock levels for each product

Requirements: use Eureka for service discovery and a Config Server for
centralized configuration.

## Design
- **eureka-server** (port 8761) — service registry.
- **config-server** (port 8888) — serves configuration from its own classpath
  (`native` profile) so no external Git repo is required. It hands
  `product-service.yml` to product-service.
- **product-service** (port 8083) — registers with Eureka, imports its config
  from config-server, exposes CRUD for products (H2 in-memory DB).
- **inventory-service** (port 8084) — registers with Eureka, uses an
  **OpenFeign** client (`ProductClient`) that resolves `product-service` by
  name through Eureka (no hardcoded URL) and records tracked stock.

## Run (in order)
```bash
cd eureka-server    && mvn spring-boot:run   # http://localhost:8761
cd config-server     && mvn spring-boot:run   # http://localhost:8888
cd product-service   && mvn spring-boot:run   # http://localhost:8083
cd inventory-service && mvn spring-boot:run   # http://localhost:8084
```
Wait for each service to start before starting the next one. Confirm
`product-service` and `inventory-service` are both listed at
`http://localhost:8761`.

## Test
```bash
# confirm config-server is serving product-service config
curl http://localhost:8888/product-service/default

# create a product
curl -X POST http://localhost:8083/products -H "Content-Type: application/json" \
  -d '{"name":"Keyboard","stock":50}'

# have inventory-service fetch it via Eureka + Feign and record it
curl -X POST http://localhost:8084/inventory/track/1

# check config value pulled from config-server
curl http://localhost:8083/products/low-stock-threshold
```
