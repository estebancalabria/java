# 🟢 LAB-Feign: Comunicación entre Microservicios con OpenFeign

## 🎯 Objetivo

Aprender a comunicar microservicios usando **Feign**, una forma **declarativa y moderna** de hacer llamadas HTTP en Spring Boot.

---

# 🟠 En este laboratorio vas a…

* Crear **dos microservicios desde cero**
* Agregar **OpenFeign manualmente**
* Implementar comunicación **sin escribir lógica HTTP**
* Entender cómo Feign simplifica el código

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

👉 Click en **Generate** y descomprimir

---

## 🔹 Crear Servicio A (cliente)

Volver a Spring Initializr

Completar:

* Group: `com.example`
* Artifact: `servicioa`

👉 Dependencies:

* ✅ Spring Web

⚠️ **NO busques Feign acá → no está**

👉 Click en **Generate** y descomprimir

---

# 🟠 Paso 1: Abrir los proyectos en el IDE

👉 Abrir Eclipse (o IntelliJ)

* Importar `serviciob`
* Importar `servicioa`

✔ Ambos proyectos abiertos

---

# 🟠 Paso 2: Configurar puertos

## 🔹 Servicio B

`application.properties`

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

👉 Probar en navegador:

```
http://localhost:8081/mensaje
```

✔ Resultado esperado:
**Hola desde Servicio B**

---

# 🟠 Paso 5: Agregar Feign en Servicio A

👉 Abrir `pom.xml` de `servicioa`

---

## 🔹 Agregar dependency

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

---

## 🔹 Agregar BOM (MUY IMPORTANTE)

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>2023.0.0</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

---

👉 Guardar → Maven descargará dependencias

---

# 🟠 Paso 6: Habilitar Feign

👉 Ir a la clase principal:

```java
@SpringBootApplication
@EnableFeignClients
public class ServicioAApplication {
```

---

# 🟠 Paso 7: Crear cliente Feign

👉 Crear interfaz:

**ClienteB.java**

```java
package com.example.servicioa;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "servicio-b", url = "http://localhost:8081")
public interface ClienteB {

    @GetMapping("/mensaje")
    String obtenerMensaje();
}
```

---

# 🟠 Paso 8: Crear Controller en Servicio A

👉 Clase: `FeignController.java`

```java
package com.example.servicioa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {

    private final ClienteB clienteB;

    public FeignController(ClienteB clienteB) {
        this.clienteB = clienteB;
    }

    @GetMapping("/feign")
    public String llamar() {
        return clienteB.obtenerMensaje();
    }
}
```

---

# 🟠 Paso 9: Ejecutar Servicio A

👉 Run As → Spring Boot App

---

👉 Probar en navegador:

```
http://localhost:8080/feign
```

✔ Resultado esperado:
**Hola desde Servicio B**

---

# 🧠 ¿Qué acaba de pasar?

👉 NO escribiste:

* RestTemplate ❌
* WebClient ❌

👉 Solo definiste una **interfaz**

🔥 Spring generó automáticamente la llamada HTTP

---

# ⚠️ Importante (limitación de este lab)

Actualmente:

```java
url = "http://localhost:8081"
```

👉 Está hardcodeado

---

# 🟡 Mejora (opcional)

👉 `application.properties`

```properties
serviciob.url=http://localhost:8081
```

---

👉 Feign:

```java
@FeignClient(name = "servicio-b", url = "${serviciob.url}")
```

---

# 🎯 Al finalizar este laboratorio sabrás…

* Crear microservicios desde cero
* Agregar Feign manualmente
* Comunicar servicios sin código HTTP explícito
* Entender por qué Feign simplifica el desarrollo

---

# 🚀 Próximo paso recomendado

👉 Evolucionar a:

* Feign + Eureka (Service Discovery)
* API Gateway
* Load Balancing

