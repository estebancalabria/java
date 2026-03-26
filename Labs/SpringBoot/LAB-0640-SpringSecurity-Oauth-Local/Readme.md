# 🟢 LABORATORIO COMPLETO: AUTH SERVER + RESOURCE SERVER

---

## **PARTE A — Authorization Server**

### 1️⃣ Crear proyecto con Spring Initializr

* Maven, Java 17+
* Dependencies:

  * Spring Web
  * Spring Security
  * OAuth2 Authorization Server

### 2️⃣ Clase principal

```java
package com.miempresa.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }
}
```

### 3️⃣ Usuarios en memoria

```java
package com.miempresa.authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserConfig {

    @Bean
    public UserDetailsService users() {
        UserDetails user = User.withUsername("juan")
                               .password("{noop}1234")
                               .roles("USER")
                               .build();
        return new InMemoryUserDetailsManager(user);
    }
}
```

### 4️⃣ Cliente OAuth2

```java
package com.miempresa.authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.util.UUID;

@Configuration
public class ClientConfig {

    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        RegisteredClient clientA = RegisteredClient.withId(UUID.randomUUID().toString())
            .clientId("microA")
            .clientSecret("{noop}secretA")
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS) // client_credentials
            .scope("read")
            .build();
        return new InMemoryRegisteredClientRepository(clientA);
    }
}
```

### 5️⃣ Levantar Authorization Server

* Ejecutar la clase principal `AuthServerApplication` desde el IDE.
* Puerto por defecto: `9000`

---

## **PARTE B — Resource Server (Microservicio A)**

### 1️⃣ Crear proyecto Spring Boot

* Maven, Java 17+
* Dependencies:

  * Spring Web
  * Spring Security
  * OAuth2 Resource Server

### 2️⃣ Configuración `application.yml`

```yaml
server:
  port: 8080

spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          jwk-set-uri: http://localhost:9000/oauth2/jwks
```

### 3️⃣ SecurityConfig

```java
package com.miempresa.microA.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/recurso").authenticated() // protegido
                .anyRequest().permitAll()                     // público
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> {})
            );

        return http.build();
    }
}
```

### 4️⃣ Endpoints público y protegido

```java
package com.miempresa.microA.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
public class RecursoController {

    @GetMapping("/recurso")
    public String recurso(@AuthenticationPrincipal Jwt jwt) {
        return "Hola " + jwt.getClaimAsString("sub") + ", accediste al recurso protegido!";
    }

    @GetMapping("/publico")
    public String publico() {
        return "Este endpoint es público y accesible sin token.";
    }
}
```

---

## **PARTE C — Probar con curl paso a paso**

### 1️⃣ Acceder sin token al endpoint protegido

```bash
curl -i http://localhost:8080/recurso
```

**Resultado esperado:**

```
HTTP/1.1 401 Unauthorized
WWW-Authenticate: Bearer
...
```

---

### 2️⃣ Acceder al endpoint público

```bash
curl -i http://localhost:8080/publico
```

**Resultado esperado:**

```
HTTP/1.1 200 OK
...
Este endpoint es público y accesible sin token.
```

---

### 3️⃣ Pedir token al Authorization Server (client_credentials)

```bash
curl -i -u microA:secretA -X POST -d "grant_type=client_credentials&scope=read" http://localhost:9000/oauth2/token
```

**Respuesta esperada (JSON):**

```json
{
  "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9...",
  "token_type": "Bearer",
  "expires_in": 3600,
  "scope": "read"
}
```

💡 Copiar el `access_token` para el siguiente paso.

---

### 4️⃣ Acceder al endpoint protegido con token

```bash
curl -i -H "Authorization: Bearer <access_token>" http://localhost:8080/recurso
```

**Resultado esperado:**

```
HTTP/1.1 200 OK
...
Hola microA, accediste al recurso protegido!
```

---

✅ Con esto tenés **un laboratorio completo, funcional, didáctico y paso a paso**:

1. Probás primero sin token → 401
2. Endpoint público accesible sin token
3. Solicitud de token con client_credentials
4. Acceso al recurso protegido con token válido
5. Uso de `@AuthenticationPrincipal` para mostrar información del token

