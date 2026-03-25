# 🟢 LAB-0620-SPRING-Role-Based-Security (HTTP Basic Authentication)

**Objetivo:** Implementar roles y restricciones de acceso basadas en roles en una aplicación Spring Boot usando Spring Security con HTTP Basic Authentication.

---

### **Paso 0: Crear el proyecto en Spring Initializr**

1. Abrir [https://start.spring.io](https://start.spring.io).

2. Seleccionar las siguientes opciones:

   * **Project:** Maven
   * **Language:** Java
   * **Spring Boot:** la versión estable más reciente
   * **Project Metadata:**

     * Group: `com.miempresa`
     * Artifact: `demoactuator`
     * Name: `DemoRolesApplication`
     * Package name: `com.miempresa.demoactuator`
     * Packaging: `Jar`
     * Java: 17 o superior

3. En **Dependencies**, agregar:

   * **Spring Web**
   * **Spring Security**
   * **Spring Boot DevTools** (opcional)

4. Hacer clic en **GENERATE** para descargar el proyecto.

5. Descomprimir y abrir en IDE (IntelliJ, VSCode o Eclipse).

---

### **Paso 1: Clase principal**

Archivo: `src/main/java/com/miempresa/demoactuator/DemoRolesApplication.java`

```java
package com.miempresa.demoactuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoRolesApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoRolesApplication.class, args);
    }
}
```

---

### **Paso 2: Controlador con endpoints según roles**

Archivo: `src/main/java/com/miempresa/demoactuator/controller/RoleController.java`

```java
package com.miempresa.demoactuator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @GetMapping("/user")
    public String userAccess() {
        return "Acceso permitido para USER";
    }

    @GetMapping("/admin")
    public String adminAccess() {
        return "Acceso permitido para ADMIN";
    }

    @GetMapping("/common")
    public String commonAccess() {
        return "Acceso para cualquier usuario autenticado";
    }
}
```

---

### **Paso 3: Configuración de seguridad con HTTP Basic**

Archivo: `src/main/java/com/miempresa/demoactuator/config/SecurityConfig.java`

```java
package com.miempresa.demoactuator.config;

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

        UserDetails superuser = User.builder()
                .username("super")
                .password(passwordEncoder.encode("super123"))
                .roles("USER","ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin, superuser);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/user").hasRole("USER")
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/common").authenticated()
                .anyRequest().permitAll()
            )
            .httpBasic(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
```

---

### **Paso 4: Ejecutar la aplicación**

```bash
./mvnw spring-boot:run
```

---

### **Paso 5: Probar endpoints con curl**

* **Acceso como `user` a `/user`**

```bash
curl -u user:password http://localhost:8080/user
# Resultado: Acceso permitido para USER
```

* **Acceso como `admin` a `/user`**

```bash
curl -u admin:admin123 http://localhost:8080/user
# Resultado: 401 Unauthorized
```

* **Acceso como `admin` a `/admin`**

```bash
curl -u admin:admin123 http://localhost:8080/admin
# Resultado: Acceso permitido para ADMIN
```

* **Acceso como `user` a `/admin`**

```bash
curl -u user:password http://localhost:8080/admin
# Resultado: 401 Unauthorized
```

* **Acceso a `/common` como usuario autenticado**

```bash
curl -u user:password http://localhost:8080/common
# Resultado: Acceso para cualquier usuario autenticado
curl -u admin:admin123 http://localhost:8080/common
# Resultado: Acceso para cualquier usuario autenticado
```

* **Acceso a `/common` sin credenciales**

```bash
curl http://localhost:8080/common
# Resultado: 401 Unauthorized
```

* **Acceso con `super` a `/user` y `/admin`**

```bash
curl -u super:super123 http://localhost:8080/user
# Resultado: Acceso permitido para USER
curl -u super:super123 http://localhost:8080/admin
# Resultado: Acceso permitido para ADMIN
```

---

### **En este laboratorio has:**

* Aprendido a crear un proyecto Spring Boot desde Spring Initializr.
* Configurado usuarios con roles en memoria.
* Protegido endpoints según roles (`USER`, `ADMIN`).
* Implementado HTTP Basic Authentication y enviado credenciales mediante `curl`.
* Verificado accesos y códigos HTTP (`200 OK`, `401 Unauthorized`).
* Entendido cómo manejar usuarios con múltiples roles.
