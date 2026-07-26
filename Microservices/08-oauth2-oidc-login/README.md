# Exercise 8: Centralized Authentication with OAuth 2.1 / OIDC

## Task
Implement centralized authentication using OAuth 2.1/OIDC in a Spring Boot
application.

## Design
- `spring-boot-starter-oauth2-client` handles the OAuth2/OIDC authorization
  code flow.
- `SecurityConfig` defines a `SecurityFilterChain` bean requiring
  authentication on every request and enabling `oauth2Login()`. Spring Boot 3
  removed `WebSecurityConfigurerAdapter`, so this replaces the older
  extends-based configuration style.
- `UserController` exposes `/user`, returning the authenticated principal
  once logged in.
- `application.yml` is pre-wired for Google's OIDC endpoints as an example
  provider — swap in `client-id` / `client-secret` from any real OIDC
  provider (Google, Okta, Keycloak, Auth0, ...) to actually log in.

## Run
```bash
cd oauth-client-app
mvn spring-boot:run
# runs on http://localhost:8096
```

## Test
```bash
# open in a browser — any unauthenticated request is redirected to the
# provider's login page
open http://localhost:8096/user

# after logging in, /user returns the OIDC principal (name, email, etc.)
```
Note: without real OAuth client credentials configured, the login redirect
will fail at the provider step — this is expected until real credentials are
supplied.
