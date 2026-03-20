# 🟢 LAB-0408-SPRING-REST-API-SECURITY

**Objetivo:** Proteger endpoints REST `/api/**` con JWT y roles, evitando acceso no autorizado.

---

### **Paso 0: Preparar proyecto**

1. Usar proyecto Spring Boot existente o crear uno nuevo con dependencias:

   * **Spring Web**
   * **Spring Security**
   * **Spring Boot DevTools** (opcional)
   * **jjwt** (Java JWT library)

2. Abrir en IDE y asegurarse de tener dependencia JWT en `pom.xml`:

```xml id="lab0408-0"
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

### **Paso 1: Clase principal**

Archivo: `src/main/java/com/miempresa/demoactuator/DemoRestSecurityApplication.java`

```java id="lab0408-1"
package com.miempresa.demoactuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoRestSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoRestSecurityApplication.class, args);
    }
}
```

---

### **Paso 2: Modelo de login**

Archivo: `src/main/java/com/miempresa/demoactuator/model/AuthRequest.java`

```java id="lab0408-2"
package com.miempresa.demoactuator.model;

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

### **Paso 3: Utilidad JWT**

Archivo: `src/main/java/com/miempresa/demoactuator/util/JwtUtil.java`

```java id="lab0408-3"
package com.miempresa.demoactuator.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "miSecretoSuperSeguro";

    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return getClaims(token).get("role", String.class);
    }

    public boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
```

---

### **Paso 4: Filtro JWT**

Archivo: `src/main/java/com/miempresa/demoactuator/filter/JwtFilter.java`

```java id="lab0408-4"
package com.miempresa.demoactuator.filter;

import com.miempresa.demoactuator.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        String username = null;
        String token = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtUtil.extractUsername(token);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (!jwtUtil.isTokenExpired(token)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
```

---

### **Paso 5: Servicio de login**

Archivo: `src/main/java/com/miempresa/demoactuator/controller/AuthController.java`

```java id="lab0408-5"
package com.miempresa.demoactuator.controller;

import com.miempresa.demoactuator.model.AuthRequest;
import com.miempresa.demoactuator.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()
                )
        );

        String role = auth.getAuthorities().iterator().next().getAuthority();
        return jwtUtil.generateToken(request.getUsername(), role);
    }
}
```

---

### **Paso 6: Endpoints REST protegidos**

Archivo: `src/main/java/com/miempresa/demoactuator/controller/ApiController.java`

```java id="lab0408-6"
package com.miempresa.demoactuator.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/api/user")
    @PreAuthorize("hasRole('USER')")
    public String userEndpoint() {
        return "Acceso a endpoint USER";
    }

    @GetMapping("/api/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminEndpoint() {
        return "Acceso a endpoint ADMIN";
    }

    @GetMapping("/api/common")
    public String commonEndpoint() {
        return "Acceso a endpoint público dentro de /api";
    }
}
```

---

### **Paso 7: Configuración de seguridad**

Archivo: `src/main/java/com/miempresa/demoactuator/config/SecurityConfig.java`

```java id="lab0408-7"
package com.miempresa.demoactuator.config;

import com.miempresa.demoactuator.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

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
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/api/**").authenticated()
            )
            .httpBasic(Customizer.withDefaults());

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
```

---

### **Paso 8: Probar endpoints con Postman o curl**

1. Ejecutar aplicación:

```bash
./mvnw spring-boot:run
```

2. **Login para obtener token**:

```bash
POST http://localhost:8080/auth/login
Content-Type: application/json

{
  "username": "user",
  "password": "password"
}
```

Respuesta: JWT token.

3. **Acceder a endpoints protegidos**:

```bash
GET http://localhost:8080/api/user
Authorization: Bearer <TOKEN>
```

✅ Token válido → acceso permitido
❌ Token ausente o rol incorrecto → acceso denegado

4. Repetir con `/api/admin` usando `admin` y `/api/common` para acceso público.

---

### **Paso 9: Extensiones**

* Agregar más roles y scopes en JWT.
* Configurar expiración corta y probar token expirado.
* Integrar con frontend o mobile app para probar seguridad REST completa.
