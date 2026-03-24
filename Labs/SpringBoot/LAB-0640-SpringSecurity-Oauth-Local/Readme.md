# 🟢 LAB-0408-SPRING-OAuth2-Servidor-Propio

**Objetivo:**
Implementar un flujo completo de OAuth2 usando:

* 🟢 Un **Authorization Server propio (Spring Boot)**
* 🔵 Un **Client (Spring Boot)** que hace login contra ese servidor

Vas a tener **2 aplicaciones corriendo en paralelo**.

---

# 🧱 Arquitectura del laboratorio

```
[ Cliente (puerto 8080) ] ---> [ Authorization Server (puerto 9000) ]
```

* Cliente → redirige al login del Authorization Server
* Auth Server → autentica usuario
* Auth Server → devuelve token + información del usuario al cliente

---

# 🟢 PARTE A — Authorization Server

---

## **Paso 0: Crear proyecto Authorization Server**

1. Abrir [https://start.spring.io](https://start.spring.io)
2. Configurar opciones del proyecto:

   * **Project:** Maven
   * **Language:** Java
   * **Spring Boot:** versión estable más reciente
   * **Group:** `com.miempresa`
   * **Artifact:** `auth-server`
   * **Name:** `AuthServerApplication`
   * **Package name:** `com.miempresa.authserver`
   * **Packaging:** Jar
   * **Java:** 17 o superior
3. En **Dependencies**, agregar:

   * **Spring Web**
   * **Spring Security**
   * **OAuth2 Authorization Server**
4. Hacer clic en **GENERATE** para descargar el proyecto y abrirlo en tu IDE.

---

## **Paso 1: Configuración básica del servidor**

Archivo: `src/main/resources/application.yml`

```yaml
server:
  port: 9000
```

* Esto indica que el Authorization Server se ejecutará en **puerto 9000**.
* Cada aplicación de este laboratorio tendrá su propio puerto.

---

## **Paso 2: Crear clase principal**

Archivo: `src/main/java/com/miempresa/authserver/AuthServerApplication.java`

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

* `@SpringBootApplication` marca la clase como principal y permite que Spring Boot la ejecute.

---

## **Paso 3: Configurar usuarios en memoria**

Archivo: `src/main/java/com/miempresa/authserver/config/UserConfig.java`

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
        // Creamos un usuario con username 'esteban' y password '1234'
        UserDetails user = User
                .withUsername("esteban")
                .password("{noop}1234") // {noop} indica que no se aplica encoding
                .roles("USER")
                .build();

        // InMemoryUserDetailsManager guarda usuarios en memoria para pruebas
        return new InMemoryUserDetailsManager(user);
    }
}
```

* Cada usuario tiene un **nombre**, **contraseña** y **roles** asignados.
* `{noop}` indica que la contraseña se almacena en texto plano (solo para laboratorio).
* Esto permite que el Authorization Server pueda autenticar a los usuarios cuando el cliente haga login.


## **Paso 3: Configurar cliente OAuth**

👉 ESTE ES EL CLIENTE QUE VA A USAR LA OTRA APP

```java
@Bean
public RegisteredClientRepository registeredClientRepository() {

    RegisteredClient client = RegisteredClient.withId(UUID.randomUUID().toString())
            .clientId("cliente-app")
            .clientSecret("{noop}secret")
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .redirectUri("http://localhost:8080/login/oauth2/code/custom")
            .scope("openid")
            .scope("profile")
            .build();

    return new InMemoryRegisteredClientRepository(client);
}
```

## **Paso 4: Probar login en el navegador**

* Abrir en el navegador: `http://localhost:9000/login`
* Ingresar las credenciales definidas en memoria:

  * Usuario: `esteban`
  * Contraseña: `1234`
* Esto verifica que Spring Security puede autenticar al usuario correctamente.

---

## **Paso 5: Probar el endpoint de autorización con curl**

* Para simular un **flujo password grant** (solo testing):

```bash
curl -u client-id:client-secret -X POST \
  -d "grant_type=password&username=esteban&password=1234" \
  http://localhost:9000/oauth2/token
```

* Devuelve un JSON con:

  * `access_token` → token JWT para acceder a recursos protegidos
  * `token_type`
  * `expires_in`
  * `scope`

---

## **Paso 6: Probar el endpoint JWKS con curl**

```bash
curl http://localhost:9000/oauth2/jwks
```

* Devuelve la **clave pública** usada para validar los JWT emitidos por el Authorization Server.

---

## **Paso 7: Validar acceso con el token**

* Una vez obtenido el `access_token`, se puede probar un recurso protegido (simulado) en otro servicio cliente:

```bash
curl -H "Authorization: Bearer <access_token>" \
  http://localhost:8080/protected-resource
```

* Devuelve el resultado del recurso protegido si el token es válido.
* Devuelve `401 Unauthorized` si el token es inválido o expiró.

---

## **Paso 8: Resumen de endpoints disponibles**

| Endpoint            | Método   | Descripción                                        |
| ------------------- | -------- | -------------------------------------------------- |
| `/login`            | GET/POST | Página de login de Spring Security                 |
| `/oauth2/authorize` | GET/POST | Inicio del flujo de autorización                   |
| `/oauth2/token`     | POST     | Intercambia código o credenciales por access token |
| `/oauth2/jwks`      | GET      | Devuelve clave pública para validar JWT            |
| `/logout`           | POST     | Cierra la sesión del usuario                       |


---

## **Paso 4: Configuración de seguridad**

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

    return http.formLogin().and().build();
}
```

---

## **Paso 5: Ejecutar**

👉 Levantar este proyecto en:

```
http://localhost:9000
```

---

# 🔵 PARTE B — Cliente OAuth2

---

## **Paso 0: Crear segundo proyecto**

Spring Initializr:

* Spring Web
* Spring Security
* OAuth2 Client
* Thymeleaf (opcional)

👉 Nombre:

```
oauth-client
```

---

## **Paso 1: application.yml**

```yaml
server:
  port: 8080

spring:
  security:
    oauth2:
      client:
        registration:
          custom:
            client-id: cliente-app
            client-secret: secret
            scope: openid, profile
            authorization-grant-type: authorization_code
            redirect-uri: http://localhost:8080/login/oauth2/code/custom

        provider:
          custom:
            authorization-uri: http://localhost:9000/oauth2/authorize
            token-uri: http://localhost:9000/oauth2/token
            user-info-uri: http://localhost:9000/userinfo
            user-name-attribute: sub
```

---

## **Paso 2: Controller**

```java
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/user")
    public String user(@AuthenticationPrincipal OAuth2User principal, Model model) {

        model.addAttribute("name", principal.getAttribute("sub"));

        return "user";
    }
}
```

---

## **Paso 3: SecurityConfig**

```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/").permitAll()
            .anyRequest().authenticated()
        )
        .oauth2Login(oauth -> oauth
            .defaultSuccessUrl("/user", true)
        );

    return http.build();
}
```

---

## **Paso 4: Vistas**

### home.html

```html
<h2>Cliente OAuth</h2>
<a href="/user">Login con OAuth2</a>
```

---

### user.html

```html
<h2>Usuario autenticado</h2>
<p th:text="${name}"></p>
<a href="/logout">Logout</a>
```

---

# 🚀 Paso 5: Ejecutar TODO

👉 Orden importante:

1. Levantar **Auth Server (9000)**
2. Levantar **Cliente (8080)**

---

# 🧪 Paso 6: Probar flujo

1. Ir a:

```
http://localhost:8080
```

2. Click → `/user`

3. Redirección a:

```
http://localhost:9000/login
```

4. Login con:

```
usuario: esteban
password: 1234
```

5. Redirección de vuelta a cliente

---

# 🧠 Qué está pasando (explicación clave para clase)

👉 Flujo real OAuth2:

1. Cliente redirige al Auth Server
2. Usuario se autentica
3. Auth Server devuelve **authorization code**
4. Cliente intercambia code por **access token**
5. Spring Security arma el `OAuth2User`

---

# 🔥 Valor pedagógico de este lab

Esto les deja clarísimo:

✅ OAuth NO es “login con Google”
✅ Es un **protocolo**
✅ Podés tener tu propio servidor
✅ Spring lo abstrae TODO

---

# 💣 Extensiones (para nivel avanzado)

* Agregar JWT en el Auth Server
* Mapear roles
* Proteger APIs REST
* Multi-client

