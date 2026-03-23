
# 🟢 LAB-SPRING-API-GATEWAY

**Objetivo:**
Implementar un **API Gateway con Spring Cloud Gateway** que enrute requests hacia dos microservicios independientes.

---

# 🧱 Arquitectura

```text
Cliente (Postman / Browser)
        ↓
   API Gateway (8080)
     ↓         ↓
Usuarios(8081) Productos(8082)
```

---

# 🧰 PARTE A — Microservicio Usuarios

---

## **Paso 0: Crear proyecto**

Spring Initializr:

* Spring Web

Nombre:

```bash
usuarios-service
```

---

## **Paso 1: application.properties**

```properties
server.port=8081
spring.application.name=usuarios-service
```

---

## **Paso 2: Controller**

```java
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @GetMapping
    public List<String> listar() {
        return List.of("Juan", "Ana", "Pedro");
    }

    @GetMapping("/{id}")
    public String obtener(@PathVariable String id) {
        return "Usuario " + id;
    }
}
```

---

## **Paso 3: Ejecutar**

👉 [http://localhost:8081/usuarios](http://localhost:8081/usuarios)

---

# 🟣 PARTE B — Microservicio Productos

---

## **Paso 0: Crear proyecto**

Spring Initializr:

* Spring Web

Nombre:

```bash
productos-service
```

---

## **Paso 1: application.properties**

```properties
server.port=8082
spring.application.name=productos-service
```

---

## **Paso 2: Controller**

```java
@RestController
@RequestMapping("/productos")
public class ProductoController {

    @GetMapping
    public List<String> listar() {
        return List.of("Notebook", "Mouse", "Teclado");
    }

    @GetMapping("/{id}")
    public String obtener(@PathVariable String id) {
        return "Producto " + id;
    }
}
```

---

## **Paso 3: Ejecutar**

👉 [http://localhost:8082/productos](http://localhost:8082/productos)

---

# 🟢 PARTE C — API Gateway

---

## **Paso 0: Crear proyecto**

Spring Initializr:

* **Spring Cloud Gateway**
* **Spring WebFlux**

Nombre:

```bash
api-gateway
```

---

## ⚠️ IMPORTANTE (Spring Cloud)

Agregar en `pom.xml`:

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>2023.0.1</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

---

## **Paso 1: application.yml**

```yaml
server:
  port: 8080

spring:
  cloud:
    gateway:
      routes:
        - id: usuarios-service
          uri: http://localhost:8081
          predicates:
            - Path=/usuarios/**
        
        - id: productos-service
          uri: http://localhost:8082
          predicates:
            - Path=/productos/**
```

---

## **Paso 2: Clase principal**

```java
@SpringBootApplication
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
```

---

# 🚀 Paso 3: Ejecutar TODO

👉 Orden:

1. usuarios-service (8081)
2. productos-service (8082)
3. api-gateway (8080)

---

# 🧪 Paso 4: Probar

---

## 🔹 Sin gateway

```bash
GET http://localhost:8081/usuarios
GET http://localhost:8082/productos
```

---

## 🔹 Con gateway

```bash
GET http://localhost:8080/usuarios
GET http://localhost:8080/productos
```

👉 🔥 Funciona igual, pero ahora pasa por el gateway

---

# 🧠 Explicación clave para clase

> “El cliente ya no conoce los microservicios.
> Solo conoce el Gateway.”

---

# 🔥 Paso 5 (opcional) — Filtro global

```java
@Bean
public GlobalFilter loggingFilter() {
    return (exchange, chain) -> {
        System.out.println("Request → " + exchange.getRequest().getURI());
        return chain.filter(exchange);
    };
}
```

👉 Vas a ver logs cada vez que pasa por el gateway

---

# 💣 Paso 6 (MUY didáctico)

Apagar un microservicio:

👉 usuarios-service OFF

Probar:

```bash
GET http://localhost:8080/usuarios
```

💥 Error → muestra dependencia del gateway

---

# 🎯 Conclusión

En este laboratorio aprendiste:

* Qué es un API Gateway
* Cómo enrutar requests
* Cómo desacoplar cliente de microservicios
* Cómo centralizar acceso

