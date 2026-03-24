# 🟢 LAB-0635 – Spring Boot JWT Authentication con filtros

**Objetivo:** Implementar autenticación stateless en Spring Boot usando **JWT**, validando tokens en cada request, y manejando respuestas claras para tokens inválidos o expirados.

---

## Paso 0: Preparar proyecto

1. Crear proyecto Spring Boot con dependencias:

   * **Spring Web**
   * **Spring Security**
   * **Spring Boot DevTools** (opcional)
   * **jjwt** (Java JWT library)

2. Agregar dependencias JWT en `pom.xml`:

```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.11.5</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.11.5</version>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.11.5</version>
    <scope>runtime</scope>
</dependency>
```

---

## Paso 1: Clase principal

`src/main/java/com/example/jwt_demo/DemoJwtApplication.java`

```java
package com.example.jwt_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoJwtApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoJwtApplication.class, args);
    }
}
```

---

## Paso 2: DTO para login

`src/main/java/com/example/jwt_demo/dto/AuthRequest.java`

```java
package com.example.jwt_demo.dto;

public class AuthRequest {
    private String username;
    private String password;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
```

---

## Paso 3: Servicio para generar y validar JWT

`src/main/java/com/example/jwt_demo/util/JwtUtil.java`

```java
package com.example.jwt_demo.util;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "esta_es_una_clave_muy_larga_de_32_bytes_minimo!!";

    private Key getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes();
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = getClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
```

---

## Paso 4: Filtro de JWT

`src/main/java/com/example/jwt_demo/config/JwtAuthenticationFilter.java`

```java
package com.example.jwt_demo.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.jwt_demo.util.JwtUtil;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authHeader.substring(7);
        String username;

        try {
            username = jwtUtil.extractUsername(token);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("TOKEN INVÁLIDO");
            return;
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (jwtUtil.validateToken(token)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(username, null, List.of());
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("ACCESO DENEGADO: TOKEN EXPIRADO O INVÁLIDO");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
```

---

## Paso 5: Configuración de seguridad

`src/main/java/com/example/jwt_demo/config/SecurityConfig.java`

```java
package com.example.jwt_demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll()
                .anyRequest().authenticated()
            );

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
```

---

## Paso 6: Controladores

`AuthController.java`

```java
package com.example.jwt_demo.controllers;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import com.example.jwt_demo.dto.AuthRequest;
import com.example.jwt_demo.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody AuthRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        String token = jwtUtil.generateToken(request.getUsername());
        return Collections.singletonMap("token", token);
    }
}
```

`TestController.java`

```java
package com.example.jwt_demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/private")
    public String privateEndpoint() {
        return "Acceso a endpoint protegido por JWT";
    }
}
```

---

## Paso 7: Probar JWT con `curl`

1️⃣ Login y obtener token:

```bash
curl -X POST http://localhost:8080/auth/login \
-H "Content-Type: application/json" \
-d "{\"username\":\"user\",\"password\":\"password\"}"
```

Respuesta:

```json
{"token":"<JWT_TOKEN_AQUI>"}
```

2️⃣ Acceso al endpoint privado con token válido:

```bash
curl -X GET http://localhost:8080/private \
-H "Authorization: Bearer <JWT_TOKEN_AQUI>"
```

Respuesta:

```
Acceso a endpoint protegido por JWT
```

3️⃣ Token inválido o incompleto:

```bash
curl -X GET http://localhost:8080/private \
-H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.12345"
```

Respuesta:

```
TOKEN INVÁLIDO
```

4️⃣ Token expirado:

```
ACCESO DENEGADO: TOKEN EXPIRADO O INVÁLIDO
```

----

# En este laboratorio has aprendido a:

* Implementar autenticación stateless en Spring Boot usando JWT, crear filtros que validan el token en cada request, manejar respuestas claras para tokens inválidos o expirados, y proteger endpoints con roles y permisos.
