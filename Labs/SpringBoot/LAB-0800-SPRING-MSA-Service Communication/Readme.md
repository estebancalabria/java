# 🟢 Laboratorio Spring Boot: Microservice Architecture

# Comunicación entre Microservicios (RestTemplate, WebClient y Async)

---

## 🎯 En este laboratorio aprenderás...

* Crear **dos microservicios desde cero**
* Comunicar servicios usando HTTP (**RestTemplate**)
* Usar comunicación moderna (**WebClient**)
* Entender la diferencia con procesamiento **asíncrono**

---

## 🟠 Paso 0: Crear los proyectos desde Spring Initializr

👉 Abrir el navegador e ir a:
[https://start.spring.io/](https://start.spring.io/)

---

### 🔹 Crear Servicio B (proveedor)

Completar:

* Project: Maven
* Language: Java
* Spring Boot: (dejar default)
* Group: `com.example`
* Artifact: `serviciob`

👉 En "Dependencies" agregar:

* **Spring Web**

Luego hacer click en:
👉 **Generate**

Descargar el .zip y descomprimir.

---

### 🔹 Crear Servicio A (cliente)

Volver a [https://start.spring.io/](https://start.spring.io/)

Completar:

* Group: `com.example`
* Artifact: `servicioa`

👉 En "Dependencies" agregar:

* **Spring Web**
* **Spring Reactive Web (WebFlux)**

👉 Click en **Generate**

Descomprimir.

---

## 🟠 Paso 1: Abrir los proyectos en el IDE

👉 Abrir **Eclipse (o tu IDE)**

* File → Open Projects from File System
* Importar:

  * carpeta `serviciob`
  * carpeta `servicioa`

✔ Vas a tener **dos proyectos abiertos**

---

## 🟠 Paso 2: Configurar puertos

👉 Esto es importante para que no choquen

---

### 🔹 Servicio B

Ir a:
`src/main/resources/application.properties`

Agregar:

```properties
server.port=8081
```

---

### 🔹 Servicio A

Ir a:
`src/main/resources/application.properties`

Agregar:

```properties
server.port=8080
```

---

## 🟠 Paso 3: Crear endpoint en Servicio B

👉 Este servicio va a responder mensajes

---

Ir a:
`src/main/java/com/example/serviciob`

Crear clase:

**MensajeController.java**

```java
package com.example.serviciob;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MensajeController {

    @GetMapping("/mensaje")
    public String mensaje() {
        return "Hola desde Servicio B";
    }
}
```

---

## 🟠 Paso 4: Ejecutar Servicio B

👉 Click derecho sobre el proyecto `serviciob`
👉 Run As → Spring Boot App

---

👉 Abrir navegador:

[http://localhost:8081/mensaje](http://localhost:8081/mensaje)

✔ Deberías ver:
**Hola desde Servicio B**

---

## 🟠 Paso 5: Comunicación síncrona con RestTemplate

👉 Ahora el Servicio A va a llamar al B

---

Ir a:
`servicioa`

---

### 🔹 Crear configuración

Crear clase:

**AppConfig.java**

```java
package com.example.servicioa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

---

### 🔹 Crear controller

Crear clase:

**ClienteController.java**

```java
package com.example.servicioa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ClienteController {

    private final RestTemplate restTemplate;

    public ClienteController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/sync")
    public String llamar() {
        return restTemplate.getForObject(
                "http://localhost:8081/mensaje",
                String.class
        );
    }
}
```

---

## 🟠 Paso 6: Ejecutar Servicio A

👉 Run As → Spring Boot App

---

👉 Abrir navegador:

[http://localhost:8080/sync](http://localhost:8080/sync)

✔ Deberías ver:
**Hola desde Servicio B**

---

## 🟠 Paso 7: Comunicación con WebClient

👉 Alternativa moderna

---

Crear clase:

**WebClientController.java**

```java
package com.example.servicioa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class WebClientController {

    private final WebClient webClient;

    public WebClientController() {
        this.webClient = WebClient.create("http://localhost:8081");
    }

    @GetMapping("/reactive")
    public String llamar() {
        return webClient.get()
                .uri("/mensaje")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
```

---

👉 Probar:

[http://localhost:8080/reactive](http://localhost:8080/reactive)

✔ Deberías ver:
**Hola desde Servicio B**

---

## 🟠 Paso 8: Simular proceso asíncrono

👉 No espera respuesta (no bloquea)

---

### 🔹 Habilitar async

Ir a la clase principal de `servicioa` y agregar:

```java
@EnableAsync
```

Quedando:

```java
@SpringBootApplication
@EnableAsync
public class ServicioAApplication {
```

---

### 🔹 Crear servicio async

**AsyncService.java**

```java
package com.example.servicioa;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    @Async
    public void ejecutar() {
        try {
            Thread.sleep(3000);
            System.out.println("Tarea async terminada");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

---

### 🔹 Crear controller

**AsyncController.java**

```java
package com.example.servicioa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncController {

    private final AsyncService asyncService;

    public AsyncController(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @GetMapping("/async")
    public String ejecutar() {
        asyncService.ejecutar();
        return "Proceso iniciado";
    }
}
```

---

👉 Probar:

[http://localhost:8080/async](http://localhost:8080/async)

✔ Respuesta inmediata
✔ Mensaje aparece luego en consola

---

## 🎯 Al finalizar este laboratorio sabrás...

* Crear y ejecutar múltiples microservicios
* Conectar servicios usando HTTP
* Usar RestTemplate y WebClient
* Entender la diferencia entre síncrono y asíncrono
Si algo no te cierra, lo ajustamos fino antes de avanzar 👍
