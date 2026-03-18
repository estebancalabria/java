# 🟢 LAB-0630-SPRING-JWT-Authentication

**Objetivo:** Implementar autenticación stateless en Spring Boot usando **JWT**, validando tokens en cada request.

---

### **Paso 0: Preparar proyecto**

1. Crear proyecto Spring Boot con dependencias:

   * **Spring Web**
   * **Spring Security**
   * **Spring Boot DevTools** (opcional)
   * **jjwt** (Java JWT library)

Agregar dependencia JWT en `pom.xml`:

```xml id="lab0630-0"
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

Archivo: `src/main/java/com/miempresa/demoactuator/DemoJwtApplication.java`

```java id="lab0630-1"
package com.miempresa.demoactuator;

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

### **Paso 2: Modelo de usuario para login**

Archivo: `src/main/java/com/miempresa/demoactuator/model/AuthRequest.java`

```java id="lab0630-2"
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

### **Paso 3: Servicio para generar JWT**

Archivo: `src/main/java/com/miempresa/demoactuator/util/JwtUtil.java`

```java id="lab0630-3"
package com.miempresa.demoactuator.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "miSecretoSuperSeguro";

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
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

### **Paso 4: Controlador de autenticación**

Archivo: `src/main/java/com/miempresa/demoactuator/controller/AuthController.java`

```java id="lab0630-4"
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
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()
                )
        );
        return jwtUtil.generateToken(request.getUsername());
    }
}
```

---

### **Paso 5: Configuración de seguridad JWT**

Archivo: `src/main/java/com/miempresa/demoactuator/config/SecurityConfig.java`

```java id="lab0630-5"
package com.miempresa.demoactuator.config;

import com.miempresa.demoactuator.util.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
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
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
```

---

### **Paso 6: Controlador protegido**

Archivo: `src/main/java/com/miempresa/demoactuator/controller/TestController.java`

```java id="lab0630-6"
package com.miempresa.demoactuator.controller;

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

### **Paso 7: Probar JWT**

1. Ejecutar aplicación:

```bash id="lab0630-7"
./mvnw spring-boot:run
```

2. Enviar POST a `/auth/login` con JSON:

```json id="lab0630-8"
{
  "username": "user",
  "password": "password"
}
```

3. Recibirás un **JWT token** como respuesta.

4. Acceder a `/private` incluyendo JWT en header `Authorization: Bearer <token>` → acceso permitido.

5. Probar token expirado → acceso denegado.

---

### **Paso 8: Extensiones**

* Configurar filtros para validar JWT en cada request.
* Agregar roles dentro del token y validarlos en endpoints (`hasRole("ADMIN")`).
* Integrar con OAuth2 en laboratorios siguientes.

