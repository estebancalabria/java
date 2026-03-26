# 🟢 LAB — Spring Cloud Gateway (Reactive)

**Objetivo:** Implementar un API Gateway con Spring Cloud Gateway (WebFlux) que enrute requests hacia un microservicio independiente.

---

## 🧱 Arquitectura

```text
Cliente (Postman / Browser)
            ↓
    API Gateway  :8080
            ↓
  Productos Service :8081
```

---

# PARTE A — Microservicio Productos

---

## Paso 0: Crear proyecto

En **Spring Initializr** (https://start.spring.io):

| Campo | Valor |
|---|---|
| Project | Maven |
| Language | Java |
| Spring Boot | (última estable) |
| Artifact | `productos-service` |
| Packaging | Jar |
| Java | 21 |
| **Dependencia** | **Spring Web** |

---

## Paso 1: application.properties

```properties
server.port=8081
spring.application.name=productos-service
```

---

## Paso 2: Controller

```java
@RestController
@RequestMapping("/productos")
public class ProductoController {

    @GetMapping
    public List<String> listar() {
        return List.of("Notebook", "Mouse", "Teclado");
    }
}
```

---

## Paso 3: Ejecutar y probar ✅

Levantá el servicio y verificá que responde **directamente**:

```
GET http://localhost:8081/productos
```

> 🟢 Si ves la lista de productos, el microservicio está listo.

---

---

# PARTE B — API Gateway

---

## Paso 0: Crear proyecto

En **Spring Initializr**:

| Campo | Valor |
|---|---|
| Project | Maven |
| Language | Java |
| Spring Boot | (última estable) |
| Artifact | `api-gateway` |
| Packaging | Jar |
| Java | 21 |
| **Dependencia** | **Reactive Gateway** *(Spring Cloud Routing)* |

> ⚠️ **No agregar Spring Web.** Reactive Gateway ya incluye su propio servidor reactivo (Netty).
> En Spring Initializr existen dos opciones parecidas: elegir **Reactive Gateway**, no "Gateway".

---

## Paso 1: Verificar pom.xml

El `pom.xml` generado debe tener estas dependencias y el BOM de Spring Cloud:

```xml
<properties>
    <java.version>21</java.version>
    <spring-cloud.version>2025.0.0</spring-cloud.version> <!-- ajustar según versión -->
</properties>

<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-gateway-server-webflux</artifactId>
    </dependency>
</dependencies>

<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>${spring-cloud.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

---

## Paso 2: Ejecutar SIN configuración de rutas

Levantá el gateway **antes** de tocar el `application.yml`.

Probá:

```
GET http://localhost:8080/productos
```

> 💥 Responde **404**. El gateway está corriendo pero no sabe a dónde enviar el request.
>
> Esto es intencional — vamos a configurar las rutas en el siguiente paso.

---

## Paso 3: Configurar rutas en application.yml

> ⚠️ **Importante:** En Spring Boot 4.x + Spring Cloud 2025.x, el namespace del gateway
> cambió. Las rutas van bajo `spring.cloud.gateway.server.webflux`.

Creá (o reemplazá) el archivo `src/main/resources/application.yml`:

```yaml
server:
  port: 8080

spring:
  cloud:
    gateway:
      server:
        webflux:
          routes:
            - id: productos-service
              uri: http://localhost:8081
              predicates:
                - Path=/productos,/productos/**
```

### ¿Qué hace cada parte?

| Propiedad | Descripción |
|---|---|
| `id` | Nombre identificador de la ruta (libre) |
| `uri` | URL del microservicio de destino |
| `predicates: Path` | Condición: solo rutea si el path matchea |
| `/productos,/productos/**` | Acepta `/productos` exacto Y cualquier sub-path |

---

## Paso 4: Reiniciar y probar ✅

Reiniciá el gateway y probá:

```
GET http://localhost:8080/productos        → Lista todos los productos
```

> 🟢 El cliente habla con el **puerto 8080** (gateway).
> El gateway redirige transparentemente al **puerto 8081** (microservicio).
> El microservicio nunca es accedido directamente por el cliente.

---

## 💣 Paso 5 (Didáctico) — ¿Qué pasa si el microservicio se cae?

1. **Apagá** el `productos-service`
2. Volvé a hacer:

```
GET http://localhost:8080/productos
```

> 💥 El gateway devuelve un error de conexión.
>
> Esto demuestra la **dependencia**: si el microservicio no está disponible,
> el gateway no puede enrutar. En producción, esto se resuelve con
> **Circuit Breaker** (Resilience4j) — tema para el próximo lab.

---

# 🎯 Conclusión

En este laboratorio aprendiste:

- Qué es un **API Gateway** y para qué sirve
- Cómo crear un microservicio con **Spring Web**
- Cómo crear un gateway con **Spring Cloud Gateway (WebFlux/Reactive)**
- Cómo configurar rutas con el namespace correcto para **Spring Boot 4.x**
- El efecto de tener (y no tener) un microservicio disponible

> **Concepto clave:** El cliente ya no conoce los microservicios. Solo conoce el Gateway.
