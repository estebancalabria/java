# **Laboratorio – Fundamentos de Spring Boot (con YAML)**

## **Objetivo**

En este laboratorio aprenderás a:

* Crear un proyecto Spring Boot desde cero usando **Spring Initializr**.
* Configurar propiedades clave usando **YAML**: puerto, context-path, nombre de aplicación, mensaje y versión.
* Ver cómo estas configuraciones afectan el comportamiento de la aplicación y la salida de un endpoint REST.

---

## **Paso 1: Crear el proyecto con Spring Boot**

1. Abrir [Spring Initializr](https://start.spring.io/)
2. Configurar el proyecto:

   * **Project:** Maven
   * **Language:** Java
   * **Spring Boot:** 3.x (última estable)
   * **Group:** `com.ejemplo`
   * **Artifact:** `demo`
   * **Packaging:** Jar
   * **Java:** 17+
3. Añadir dependencias:

   * **Spring Web**
   * **Spring Boot DevTools** (opcional)
4. Hacer clic en **Generate**, descargar, descomprimir y abrir en el IDE.

---

## **Paso 2: Explorar la estructura del proyecto**

La estructura típica es:

```id="q3d3f8"
demo
├─ src
│  ├─ main
│  │  ├─ java
│  │  │  └─ com/ejemplo/demo
│  │  │      └─ DemoApplication.java
│  │  │
│  │  └─ resources
│  │      ├─ application.yml
│  │      ├─ static
│  │      └─ templates
├─ pom.xml
```

* `DemoApplication.java` inicia el **servidor embebido Tomcat** automáticamente.
* `application.yml` contendrá nuestras configuraciones.

---

## **Paso 3: Crear el controlador `/hola`**

Archivo `HolaController.java` en paquete `controllers`:

```java id="b7xv0p"
package com.ejemplo.demo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaController {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${app.mensaje.hola}")
    private String mensajeHola;

    @Value("${app.version}")
    private String version;

    @GetMapping("/hola")
    public String saludar() {
        return String.format("%s (v%s): %s", appName, version, mensajeHola);
    }
}
```

---

## **Paso 4: Configuración en `application.yml`**

```yaml id="r2m6js"
server:
  port: 8081
  servlet:
    context-path: /api

spring:
  application:
    name: DemoSpringBoot

app:
  mensaje:
    hola: "¡Hola desde Spring Boot configurado!"
  version: "1.0.0"
```

---

## **Paso 5: Ejecutar la aplicación**

1. Ejecutar `DemoApplication.java`
2. Abrir navegador y visitar:

```id="8m4p5k"
http://localhost:8081/api/hola
```

**Resultado esperado:**

```id="b1n2j3"
DemoSpringBoot (v1.0.0): ¡Hola desde Spring Boot configurado!
```

---

## **Paso 6: Probar cambios de configuración**

1. **Cambiar el puerto**

```yaml id="2h4f9v"
server:
  port: 9090
```

* Resultado: la app arranca en `http://localhost:9090/api/hola`

2. **Cambiar el context-path**

```yaml id="3l7f0p"
server:
  servlet:
    context-path: /v1
```

* Resultado: el endpoint queda en `http://localhost:8081/v1/hola`

3. **Cambiar nombre de la aplicación**

```yaml id="5k1b7c"
spring:
  application:
    name: MiAppSpring
```

* Resultado: `/hola` devuelve `MiAppSpring (v1.0.0): ¡Hola desde Spring Boot configurado!`

4. **Cambiar mensaje dinámico**

```yaml id="6d9h2x"
app:
  mensaje:
    hola: "Hola alumnos!"
```

* Resultado: `/hola` devuelve `MiAppSpring (v1.0.0): Hola alumnos!`

5. **Cambiar la versión**

```yaml id="7j4l3m"
app:
  version: "2.0.0"
```

* Resultado: `/hola` devuelve `MiAppSpring (v2.0.0): Hola alumnos!`

---

## **Conclusión**

* Los alumnos crean un proyecto Spring Boot completo desde cero.
* Configuran propiedades en **YAML** y ven cómo afectan **puerto, context-path, nombre, mensaje y versión**.
* Preparados para los siguientes módulos: **APIs REST completas y validación de propiedades**.
