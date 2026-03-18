# 🟢 LAB-0410-SPRING-Web-Login-Security

**Objetivo:** Configurar seguridad en Spring Boot con **login web**, autenticación y autorización basada en roles, usando **Spring Security** y **Thymeleaf**.

---

### **Paso 0: Preparar proyecto**

1. Crear proyecto Spring Boot con dependencias:

   * **Spring Web**
   * **Spring Security**
   * **Spring Boot DevTools** (opcional)
   * **Thymeleaf**

2. Abrir el proyecto en el IDE.

---

### **Paso 1: Clase principal**

Archivo: `src/main/java/com/miempresa/demoactuator/DemoWebLoginApplication.java`

```java id="lab0410-1"
package com.miempresa.demoactuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoWebLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoWebLoginApplication.class, args);
    }
}
```

---

### **Paso 2: Controlador de endpoints**

Archivo: `src/main/java/com/miempresa/demoactuator/controller/TestController.java`

```java id="lab0410-2"
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
        return "Acceso privado para usuarios con rol USER";
    }
}
```

---

### **Paso 3: Controlador de login**

Archivo: `src/main/java/com/miempresa/demoactuator/controller/LoginController.java`

```java id="lab0410-3"
package com.miempresa.demoactuator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
```

---

### **Paso 4: Configuración de seguridad**

Archivo: `src/main/java/com/miempresa/demoactuator/config/SecurityConfig.java`

```java id="lab0410-4"
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
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/private", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/public")
            )
            .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
```

---

### **Paso 5: Vista de login**

Archivo: `src/main/resources/templates/login.html`

```html id="lab0410-5"
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Login</title>
</head>
<body>
<h2>Login</h2>
<form th:action="@{/login}" method="post">
    <div>
        <label>Usuario:</label>
        <input type="text" name="username"/>
    </div>
    <div>
        <label>Contraseña:</label>
        <input type="password" name="password"/>
    </div>
    <div>
        <button type="submit">Ingresar</button>
    </div>
</form>
</body>
</html>
```

---

### **Paso 6: Probar la aplicación**

1. Ejecutar:

```bash id="lab0410-6"
./mvnw spring-boot:run
```

2. Acceder a los endpoints en el navegador:

* `http://localhost:8080/public` → acceso público
* `http://localhost:8080/private` → redirige a `http://localhost:8080/login`

3. Login con:

* Usuario: `user` / Password: `password` → acceso a `/private` permitido
* Usuario: `admin` / Password: `admin123` → acceso denegado si solo `USER` tiene rol

4. Logout con `http://localhost:8080/logout` → redirige a `/public`.

---

### **Paso 7: Extender roles**

* Modificar `SecurityConfig` para permitir que `ADMIN` acceda a `/private` si se desea:

```java id="lab0410-7"
.requestMatchers("/private").hasAnyRole("USER","ADMIN")
```

---

### **Resultado**

* Aplicación con **login web funcional**.
* **Autenticación y autorización por roles**.
* Endpoints protegidos y públicos.
* Base lista para extender a JWT u OAuth2 en laboratorios siguientes.
