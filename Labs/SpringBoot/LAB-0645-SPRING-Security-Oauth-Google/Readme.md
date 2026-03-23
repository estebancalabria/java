# 🟢 LAB-0407-SPRING-OAuth2-Google

**Objetivo:** Configurar Spring Boot con **OAuth2 Client** para login usando Google y observar cómo Spring Security gestiona el token y la información del usuario.

---

### **Paso 0: Preparar proyecto**

1. Crear proyecto Spring Boot con dependencias:

   * **Spring Web**
   * **Spring Security**
   * **Spring Boot DevTools** (opcional)
   * **Thymeleaf** (opcional, para login web visual)

2. Abrir proyecto en IDE.

---

### **Paso 1: Crear proyecto en Google Cloud Console**

1. Ir a [Google Cloud Console](https://console.cloud.google.com/).

2. Crear un **nuevo proyecto** (ej: `DemoOAuth2`).

3. Habilitar **APIs & Services → OAuth Consent Screen**:

   * User Type: `External`
   * Nombre de aplicación: `Demo OAuth2 Spring`
   * Guardar y continuar (solo para laboratorio no necesitamos scopes avanzados).

4. Crear **credenciales → OAuth 2.0 Client IDs**:

   * Application Type: `Web application`
   * Name: `SpringBoot OAuth Client`
   * Authorized redirect URIs: `http://localhost:8080/login/oauth2/code/google`
   * Guardar → copiar **Client ID** y **Client Secret**

---

### **Paso 2: Configurar Spring Boot para OAuth2**

Archivo: `src/main/resources/application.yml`

```yaml id="lab0407-2"
spring:
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: YOUR_CLIENT_ID
            client-secret: YOUR_CLIENT_SECRET
            scope:
              - openid
              - profile
              - email
        provider:
          google:
            authorization-uri: https://accounts.google.com/o/oauth2/v2/auth
            token-uri: https://oauth2.googleapis.com/token
            user-info-uri: https://www.googleapis.com/oauth2/v3/userinfo
            user-name-attribute: sub
server:
  port: 8080
```

> 🔹 Reemplaza `YOUR_CLIENT_ID` y `YOUR_CLIENT_SECRET` con los valores obtenidos en Google Cloud Console.

---

### **Paso 3: Clase principal**

Archivo: `src/main/java/com/miempresa/demoactuator/DemoOAuth2Application.java`

```java id="lab0407-3"
package com.miempresa.demoactuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoOAuth2Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoOAuth2Application.class, args);
    }
}
```

---

### **Paso 4: Controlador web para login**

Archivo: `src/main/java/com/miempresa/demoactuator/controller/HomeController.java`

```java id="lab0407-4"
package com.miempresa.demoactuator.controller;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home"; // Página pública
    }

    @GetMapping("/user")
    public String user(@AuthenticationPrincipal OAuth2User principal, Model model) {
        model.addAttribute("name", principal.getAttribute("name"));
        model.addAttribute("email", principal.getAttribute("email"));
        return "user"; // Página protegida, info del usuario
    }
}
```

---

### **Paso 5: Vistas con Thymeleaf**

#### **home.html** – `src/main/resources/templates/home.html`

```html id="lab0407-5a"
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Home</title>
</head>
<body>
<h2>Página Pública</h2>
<a href="/user">Ir a página de usuario (login requerido)</a>
</body>
</html>
```

#### **user.html** – `src/main/resources/templates/user.html`

```html id="lab0407-5b"
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Usuario</title>
</head>
<body>
<h2>Página protegida</h2>
<p>Nombre: <span th:text="${name}"></span></p>
<p>Email: <span th:text="${email}"></span></p>
<a href="/logout">Logout</a>
</body>
</html>
```

---

### **Paso 6: Configuración de seguridad**

Archivo: `src/main/java/com/miempresa/demoactuator/config/SecurityConfig.java`

```java id="lab0407-6"
package com.miempresa.demoactuator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/css/**").permitAll()
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth2 -> oauth2
                .defaultSuccessUrl("/user", true)
            )
            .logout(logout -> logout.logoutSuccessUrl("/"));

        return http.build();
    }
}
```

---

### **Paso 7: Ejecutar la aplicación**

1. Ejecutar con:

```bash id="lab0407-7"
./mvnw spring-boot:run
```

2. Abrir en navegador: `http://localhost:8080/` → página pública
3. Click en “Ir a página de usuario” → redirige a **Google login**
4. Autorizar aplicación → redirige a `/user` con **nombre y email** del usuario

---

### **Paso 8: Observar tokens y sesión**

* Spring Security maneja automáticamente el **OAuth2AuthenticationToken**.
* Puedes ver los atributos del usuario (`name`, `email`, `sub`, etc.) en `/user`.
* Logout → redirige a `/`.

---

### **Paso 9: Extensiones**

* Probar login con otro usuario de Google.
* Agregar roles según email o dominio (`@empresa.com`).
* Integrar con JWT o API REST para login stateless.
