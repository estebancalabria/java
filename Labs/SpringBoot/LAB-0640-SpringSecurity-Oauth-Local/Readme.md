# 🟢 LAB-0408-SPRING-OAuth2-Servidor-Propio

**Objetivo:**
Implementar un flujo completo de OAuth2 usando:

* 🟢 Un **Authorization Server propio (Spring Boot)**
* 🔵 Un **Client (Spring Boot)** que hace login contra ese servidor

👉 Vas a tener **2 aplicaciones corriendo en paralelo (2 Eclipse)**

---

# 🧱 Arquitectura del laboratorio

```
[ Cliente (puerto 8080) ] ---> [ Authorization Server (puerto 9000) ]
```

* Cliente → redirige al login
* Auth Server → autentica usuario
* Devuelve token + user info

---

# 🟢 PARTE A — Authorization Server

---

## **Paso 0: Crear proyecto**

Spring Initializr:

* **Spring Web**
* **Spring Security**
* **OAuth2 Authorization Server**

👉 Nombre sugerido:

```
auth-server
```

---

## **Paso 1: Configuración básica**

Archivo:
`application.yml`

```yaml
server:
  port: 9000
```

---

## **Paso 2: Configurar usuarios en memoria**

```java
@Bean
public UserDetailsService users() {
    UserDetails user = User
            .withUsername("esteban")
            .password("{noop}1234")
            .roles("USER")
            .build();

    return new InMemoryUserDetailsManager(user);
}
```

---

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

