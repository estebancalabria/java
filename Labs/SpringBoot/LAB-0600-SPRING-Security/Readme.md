# 🟢 LAB Spring Security

**Objetivo:** Configurar seguridad básica en Spring Boot usando Spring Security, creando usuarios en memoria, autenticación y autorización de endpoints.

---

### **Paso 0: Preparar proyecto**

* Crear proyecto Spring Boot con:

  * Dependencias: **Spring Web** + **Spring Security**
* Abrir en IDE (IntelliJ, VSCode, Eclipse)

---

### **Paso 1: Crear clase principal**

Archivo: `src/main/java/com/miempresa/demoactuator/DemoSecurityApplication.java`

```java
package com.miempresa.demoactuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoSecurityApplication.class, args);
    }
}
```

---

### **Paso 2: Crear endpoint de prueba**

Archivo: `src/main/java/com/miempresa/demoactuator/controller/TestController.java`

```java
package com.miempresa.demoactuator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "Acceso público";
    }

    @GetMapping("/private")
    public String privateEndpoint() {
        return "Acceso privado";
    }
}
```

---

### **Paso 3: Configuración básica de Spring Security**

Archivo: `src/main/java/com/miempresa/demoactuator/config/SecurityConfig.java`

```java
package com.example.security.demosecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public").permitAll()
                .requestMatchers("/private").hasRole("USER")
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults())  // <-- CORRECTO
            .csrf(csrf -> csrf.disable());         // opcional, útil para pruebas con Postman

        return http.build();
    }
}

```

---

### **Paso 4: Probar seguridad**

1️⃣ Ejecutar la aplicación:

```bash
./mvnw spring-boot:run
```

2️⃣ Acceder a endpoints:

* `http://localhost:8080/public` → acceso público, no pide login
* `http://localhost:8080/private` → pide usuario/contraseña

  * Usuario: `user` / Password: `password` → acceso permitido
  * Usuario: `admin` / Password: `admin123` → acceso denegado si no se asigna el rol correcto

---

### **Paso 5: Extender roles y autorización**

* Modificar `SecurityConfig` para permitir que `admin` acceda también al endpoint privado si se quiere.
* Ejemplo:

```java
.requestMatchers("/private").hasAnyRole("USER", "ADMIN")
```

* Esto introduce el concepto de **autorización basada en roles**.

---

Con este laboratorio práctico, los alumnos **aprenden todo lo que cubren 4.1 a 4.4** en un solo flujo:

1. Fundamentos de seguridad
2. Introducción a Spring Security
3. Configuración básica
4. Autenticación y autorización

