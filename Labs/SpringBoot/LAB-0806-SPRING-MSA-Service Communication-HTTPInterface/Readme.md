# 🟢 LAB: Comunicación entre Microservicios con **HTTP Interface (Spring Boot 3)**

## 🎯 Objetivo

Aprender a comunicar microservicios usando **HTTP Interface**, la forma **nativa, moderna y declarativa** de Spring.

---

# 🧠 En este laboratorio vas a…

* Crear **dos microservicios desde cero**
* Consumir un endpoint usando una **interfaz (sin Feign)**
* Entender cómo Spring genera el cliente automáticamente
* Usar **RestClient (nuevo en Spring 6)**

---

# 🟠 Paso 0: Crear los proyectos desde Spring Initializr

👉 Ir a:
[https://start.spring.io/](https://start.spring.io/)

---

## 🔹 Crear Servicio B (proveedor)

Completar:

* Project: Maven
* Language: Java
* Group: `com.example`
* Artifact: `serviciob`

👉 Dependencies:

* ✅ Spring Web

👉 Generate → descomprimir

---

## 🔹 Crear Servicio A (cliente)

Completar:

* Group: `com.example`
* Artifact: `servicioa`

👉 Dependencies:

* ✅ Spring Web

👉 Generate → descomprimir

---

# 🟠 Paso 1: Abrir en el IDE

👉 Importar ambos proyectos en Eclipse o IntelliJ

✔ `servicioa`
✔ `serviciob`

---

# 🟠 Paso 2: Configurar puertos

## 🔹 Servicio B

```properties
server.port=8081
```

---

## 🔹 Servicio A

```properties
server.port=8080
```

---

# 🟠 Paso 3: Crear endpoint en Servicio B

👉 Clase: `MensajeController.java`

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

# 🟠 Paso 4: Ejecutar Servicio B

👉 Run As → Spring Boot App

👉 Probar:

```
http://localhost:8081/mensaje
```

✔ Resultado:
**Hola desde Servicio B**

---

# 🟠 Paso 5: Crear HTTP Interface en Servicio A

👉 Crear interfaz:

**ClienteB.java**

```java
package com.example.servicioa;

import org.springframework.web.service.annotation.GetExchange;

public interface ClienteB {

    @GetExchange("/mensaje")
    String obtenerMensaje();
}
```

---

# 🟠 Paso 6: Configurar el cliente (la “magia”)

👉 Crear clase:

**ClientConfig.java**

```java
package com.example.servicioa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ClientConfig {

    @Bean
    public ClienteB clienteB() {

        RestClient restClient = RestClient.builder()
                .baseUrl("http://localhost:8081")
                .build();

        HttpServiceProxyFactory factory =
                HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient))
                        .build();

        return factory.createClient(ClienteB.class);
    }
}
```

---

# 🟠 Paso 7: Crear Controller en Servicio A

👉 Clase: `HttpClientController.java`

```java
package com.example.servicioa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpClientController {

    private final ClienteB clienteB;

    public HttpClientController(ClienteB clienteB) {
        this.clienteB = clienteB;
    }

    @GetMapping("/http")
    public String llamar() {
        return clienteB.obtenerMensaje();
    }
}
```

---

# 🟠 Paso 8: Ejecutar Servicio A

👉 Run As → Spring Boot App

---

👉 Probar:

```
http://localhost:8080/http
```

✔ Resultado esperado:
**Hola desde Servicio B**

---

# 🧠 ¿Qué pasó acá?

👉 Definiste esto:

```java
public interface ClienteB {
    @GetExchange("/mensaje")
}
```

👉 Y Spring hizo TODO esto por vos:

* Crear cliente HTTP
* Hacer request
* Mapear respuesta

🔥 Sin Feign
🔥 Sin RestTemplate
🔥 Sin WebClient explícito

---

# ⚠️ Importante (limitación actual)

👉 Seguís teniendo:

```java
.baseUrl("http://localhost:8081")
```

❗ Hardcodeado

---

# 🟡 Mejora opcional

👉 application.properties

```properties
serviciob.url=http://localhost:8081
```

👉 Config:

```java
.baseUrl(serviciobUrl)
```

---

# 🚀 Comparación final (ideal slide)

| Tecnología     | Tipo                       | Nivel  |
| -------------- | -------------------------- | ------ |
| RestTemplate   | Imperativo                 | 🧓     |
| WebClient      | Reactivo                   | ✅      |
| Feign          | Declarativo (Spring Cloud) | 🚀     |
| HTTP Interface | Declarativo nativo         | 🔥🔥🔥 |

---

# 🎯 Conclusión para clase

> “HTTP Interface es el reemplazo natural de Feign dentro del core de Spring”

