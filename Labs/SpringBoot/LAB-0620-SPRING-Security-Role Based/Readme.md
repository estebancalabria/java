# 🟢 LAB-0620-SPRING-Role-Based-Security

**Objetivo:** Implementar roles y restricciones de acceso basadas en roles en una aplicación Spring Boot usando Spring Security.

---

### **Paso 0: Preparar proyecto**

1. Usar proyecto Spring Boot existente o crear uno nuevo con:

   * Dependencias: **Spring Web**, **Spring Security**, **Spring Boot DevTools** (opcional).
2. Abrir en IDE (IntelliJ, VSCode, Eclipse).

---

### **Paso 1: Clase principal**

Archivo: `src/main/java/com/miempresa/demoactuator/DemoRolesApplication.java`

```java id="lab0620-1"
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

```java id="lab0620-2"
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

### **Paso 3: Configuración de seguridad con roles**

Archivo: `src/main/java/com/miempresa/demoactuator/config/SecurityConfig.java`

```java id="lab0620-3"
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
            .formLogin(form -> form
                .loginPage("/login").permitAll()
            )
            .httpBasic(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
```

---

### **Paso 4: Controlador de login**

Archivo: `src/main/java/com/miempresa/demoactuator/controller/LoginController.java`

```java id="lab0620-4"
package com.miempresa.demoactuator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login"; // Llamará a login.html en resources/templates
    }
}
```

---

### **Paso 5: Vista de login**

Archivo: `src/main/resources/templates/login.html`

```html id="lab0620-5"
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

### **Paso 6: Probar roles**

1. Ejecutar aplicación:

```bash id="lab0620-6"
./mvnw spring-boot:run
```

2. Acceder a endpoints:

| Endpoint  | Usuario        | Resultado esperado |
| --------- | -------------- | ------------------ |
| `/user`   | user           | ✅ acceso permitido |
| `/user`   | admin          | ❌ acceso denegado  |
| `/admin`  | admin          | ✅ acceso permitido |
| `/admin`  | user           | ❌ acceso denegado  |
| `/common` | user o admin   | ✅ acceso permitido |
| `/common` | no autenticado | ❌ redirige a login |

3. Probar `super` → acceso a `/user` y `/admin` permitido (tiene ambos roles).

---

### **Paso 7: Extender práctica**

* Cambiar roles asignados a usuarios.
* Probar combinaciones de roles y restricciones de acceso.
* Configurar redirecciones personalizadas según rol después del login.
