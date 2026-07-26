# Exercise 10: JSON Web Tokens (JWT) for Secure Communication

## Task
Use JSON Web Tokens (JWT) for secure communication in a Spring Boot
application.

## Design
- `JwtTokenProvider` — issues and validates HS256 JWTs (jjwt 0.11.x API).
- `JwtTokenFilter` — a `OncePerRequestFilter` that reads the `Authorization:
  Bearer <token>` header, validates it, and populates the security context.
- `SecurityConfig` — a `SecurityFilterChain` (Spring Boot 3 style, no
  `WebSecurityConfigurerAdapter`) that permits `/login` and requires a valid
  JWT for everything else; the JWT filter runs before the standard username/
  password filter.
- `AuthController` — `POST /login?username=...` issues a demo token (no
  password check, for simplicity of the exercise).
- `SecureController` — `GET /secure`, reachable only with a valid token.

## Run
```bash
cd jwt-auth-service
mvn spring-boot:run
# runs on http://localhost:8098
```

## Test
```bash
# issue a token
TOKEN=$(curl -s -X POST "http://localhost:8098/login?username=alice")

# without token -> 401/403
curl -i http://localhost:8098/secure

# with token -> 200 OK
curl -i http://localhost:8098/secure -H "Authorization: Bearer $TOKEN"
```
