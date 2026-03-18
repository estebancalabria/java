## 🟢 Laboratorio Spring Boot: Gestión y Monitorización con Actuator

**Objetivo:** Aprender a monitorizar y gestionar una aplicación Spring Boot usando **Spring Boot Actuator**, explorando health checks, métricas, logging y perfiles de producción.

---

### **Paso 0: Preparar el proyecto**

1. Crear un proyecto Spring Boot en [Spring Initializr](https://start.spring.io/) con:

   * Dependencias: **Spring Web** + **Spring Boot Actuator**
   * Java 17+
2. Abrir el proyecto en tu IDE (IntelliJ, VSCode, Eclipse).

---

### **Paso 1: Crear clase principal**

Archivo: `src/main/java/com/miempresa/demoactuator/DemoActuatorApplication.java`

```java
package com.miempresa.demoactuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoActuatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoActuatorApplication.class, args);
    }
}
```

---

### **Paso 2: Configurar Actuator**

Archivo: `src/main/resources/application.properties`

```properties
# Exponer todos los endpoints
management.endpoints.web.exposure.include=*

# Mostrar detalles en health
management.endpoint.health.show-details=always

# Puerto de la app
server.port=8080

# Configuración de logs
logging.level.root=INFO
logging.level.com.miempresa.demoactuator=DEBUG
```

---

### **Paso 3: Explorar endpoints**

Con la app corriendo (`./mvnw spring-boot:run`):

* `http://localhost:8080/actuator` → Lista todos los endpoints
* `http://localhost:8080/actuator/health` → Estado de la app
* `http://localhost:8080/actuator/info` → Información básica

---

### **Paso 4: Crear Health Indicator personalizado**

Archivo: `src/main/java/com/miempresa/demoactuator/health/CustomHealthIndicator.java`

```java
package com.miempresa.demoactuator.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        boolean databaseUp = true; // Simula chequeo de DB
        if (databaseUp) {
            return Health.up().withDetail("database", "OK").build();
        } else {
            return Health.down().withDetail("database", "DOWN").build();
        }
    }
}
```

Acceder a `http://localhost:8080/actuator/health` y verificar que se vea el estado `customHealthIndicator`.

---

### **Paso 5: Consultar métricas**

Endpoint general de métricas:

```http
http://localhost:8080/actuator/metrics
```

Ejemplo de métricas específicas (requests HTTP):

```bash
curl http://localhost:8080/actuator/metrics/http.server.requests
```

---

### **Paso 6: Logging con Actuator**

Consultar niveles de log:

```bash
curl http://localhost:8080/actuator/loggers/com.miempresa.demoactuator
```

Cambiar nivel de log en caliente:

```bash
curl -X POST -H "Content-Type: application/json" \
    -d '{"configuredLevel":"DEBUG"}' \
    http://localhost:8080/actuator/loggers/com.miempresa.demoactuator
```

---

### **Paso 7: Configuración de perfiles**

1. Crear archivos:

   * `src/main/resources/application-dev.properties`
   * `src/main/resources/application-prod.properties`
2. Ejemplo para producción (`application-prod.properties`):

```properties
logging.level.root=WARN
management.endpoints.web.exposure.include=health,info,metrics,loggers
```

3. Activar perfil al arrancar:

```bash
java -jar -Dspring.profiles.active=prod target/demoactuator-0.0.1-SNAPSHOT.jar
```

---

### **Paso 8: Buenas prácticas**

* Exponer solo endpoints necesarios en producción.
* Usar logging estructurado para integraciones con herramientas externas.
* Mantener perfiles separados (`dev` / `prod`).
* Monitorizar health checks críticos (DB, colas, servicios externos).
* Combinar métricas, logs y health para una **observabilidad completa**.

