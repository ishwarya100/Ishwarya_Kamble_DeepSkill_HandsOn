# Exercise 9: Authorization Server and Resource Server Configuration

## Task
Configure Authorization Server and Resource Server behavior in a Spring Boot
application.

## Design
- `resource-server` is configured as an OAuth2 Resource Server: it validates
  incoming JWT access tokens issued by an external Authorization Server
  (identified by `issuer-uri`) rather than implementing its own Authorization
  Server.
- `ResourceServerConfig` defines a `SecurityFilterChain` requiring
  authentication on every request and validating the bearer token via
  `oauth2ResourceServer().jwt()`.
- `/secure` is only reachable with a valid JWT access token in the
  `Authorization: Bearer <token>` header.

## Run
```bash
cd resource-server
mvn spring-boot:run
# runs on http://localhost:8097
```
Set `spring.security.oauth2.resourceserver.jwt.issuer-uri` in
`application.yml` to a real Authorization Server (e.g. Keycloak, Okta, or
Spring Authorization Server) to obtain valid tokens.

## Test
```bash
# without a token -> 401 Unauthorized
curl -i http://localhost:8097/secure

# with a valid JWT from the configured issuer -> 200 OK
curl -i http://localhost:8097/secure -H "Authorization: Bearer <valid-jwt>"
```
