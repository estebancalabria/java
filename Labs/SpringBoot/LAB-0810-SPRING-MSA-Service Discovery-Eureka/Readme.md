# 🟢 Laboratorio Spring Boot: Service Discovery con Eureka

## 🎯 En este laboratorio aprenderás…

* Qué es Eureka y cómo se usa en Spring Boot
* Crear un **Eureka Server** para registrar microservicios
* Registrar microservicios en Eureka
* Descubrir servicios desde otros microservicios
* Configurar puertos distintos para evitar conflictos

---

## 🟠 Crear Eureka Server

1. Ir a [https://start.spring.io/](https://start.spring.io/)
2. Configurar:

   * Project: **Maven**
   * Language: **Java**
   * Spring Boot: **la última estable**
   * Group: `com.example`
   * Artifact: `eurekaserver`
3. Dependencias: **Eureka Server** (Spring Cloud Netflix Eureka Server)
4. Click en **Generate** → descargar el zip y descomprimir
5. Abrir en Eclipse (Eclipse 1)

---


### 🔹 Activar Eureka Server yConfigurar Eureka Server

* Agregar anotacion @EnableEurekaServer al Archivo: `EurekaserverApplication.java`

```java
package com.example.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaserverApplication.class, args);
    }
}
```


### 🔹 Configurar propiedades

Archivo: `src/main/resources/application.properties`

```properties
spring.application.name=eureka-server
server.port=8761
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
```

---

### 🔹 Ejecutar Eureka Server

1. Click derecho → Run As → Spring Boot App
2. Abrir navegador: [http://localhost:8761/](http://localhost:8761/)
3. Deberías ver la **interfaz de Eureka**, con mensaje “**Instances currently registered with Eureka**: 0”

---

## 🟠 Paso 2: Configurar Microservicio A

### 🔹 Crear Microservicio A

1. Ir a [https://start.spring.io/](https://start.spring.io/)
2. Configurar:

   * Group: `com.example`
   * Artifact: `servicio-a`
3. Dependencias: **Spring Web**, **Eureka Discovery Client**
4. Click en **Generate** → descargar zip y descomprimir
5. Abrir en Eclipse (Eclipse 2 o nueva ventana)

---


### 🔹 application.properties

```properties
server.port=8081
spring.application.name=servicio-a

# Configuración Eureka Client
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```

---

### 🔹 Clase principal

Archivo: `ServicioAApplication.java`

```java
package com.example.servicioa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServicioAApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicioAApplication.class, args);
    }
}
```

---

### 🔹 Crear endpoint de prueba

Archivo: `UsuarioController.java`

```java
package com.example.servicioa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @GetMapping("/usuarios")
    public String listarUsuarios() {
        return "Lista de usuarios desde Servicio A";
    }
}
```
---

### 🔹 Ejecutar Microservicio A

* Run As → Spring Boot App
* Volver a Eureka Server → deberías ver **servicio-a registrado** con puerto 8081

---

## 🟠 Paso 3: Configurar Microservicio B

### 🔹 application.properties

```properties
server.port=8082
spring.application.name=servicio-b

# Configuración Eureka Client
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```

---

### 🔹 Clase principal

Archivo: `ServicioBApplication.java`

```java
package com.example.serviciob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServicioBApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicioBApplication.class, args);
    }
}
```

---

### 🔹 Crear endpoint de prueba

Archivo: `PedidoController.java`

```java
package com.example.serviciob;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidoController {

    @GetMapping("/pedidos")
    public String listarPedidos() {
        return "Lista de pedidos desde Servicio B";
    }
}
```

---

### 🔹 Crear Microservicio B

1. Mismo proceso que Microservicio A

   * Artifact: `servicio-b`
   * Dependencias: **Spring Web**, **Eureka Discovery Client**


### 🔹 Ejecutar Microservicio B


* Run As → Spring Boot App
* Volver a Eureka Server → ahora deberían aparecer **servicio-a** y **servicio-b**

---

## 🟠 Paso 4: Descubrir servicios desde otro microservicio

### 🔹 Crear un cliente simple en Microservicio A

Archivo: `ClienteController.java`

```java
package com.example.servicioa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RestController
public class ClienteController {

    private final RestTemplate restTemplate;

    public ClienteController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/pedidos-via-discovery")
    public String obtenerPedidos() {
        // Aquí simulamos descubrimiento; normalmente usarías @LoadBalanced RestTemplate
        return restTemplate.getForObject("http://localhost:8082/pedidos", String.class);
    }
}

@Configuration
class AppConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

---

### 🔹 Probar descubrimiento

* Abrir navegador → [http://localhost:8081/pedidos-via-discovery](http://localhost:8081/pedidos-via-discovery)
* ✔ Deberías ver: “Lista de pedidos desde Servicio B”
* Eureka permite **descubrir servicios dinámicamente**, aunque en laboratorio usamos el puerto fijo para simplificar.

---

## 🎯 Al final de este laboratorio lograrás…

* Crear un **Eureka Server** y entender su rol
* Configurar microservicios para **registrarse automáticamente**
* Visualizar servicios registrados en la interfaz de Eureka
* Consumir un servicio desde otro microservicio usando **información del descubrimiento**
* Entender cómo centralizar la información de los servicios para arquitecturas de microservicios

