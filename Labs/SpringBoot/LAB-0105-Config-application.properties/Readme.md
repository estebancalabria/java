# **Laboratorio – Fundamentos de Spring Boot: Configuración básica paso a paso**

## **Objetivo**

En este laboratorio aprenderás a:

* Crear un proyecto Spring Boot desde cero usando **Spring Initializr**.
* Entender la **estructura de un proyecto Spring Boot** y la diferencia con Spring MVC tradicional.
* Configurar propiedades clave: **puerto**, **context-path**, **nombre de aplicación**, **mensaje** y **versión**.
* Ver cómo estas configuraciones afectan el comportamiento de la aplicación y la salida de un endpoint REST.

---

## **Paso 1: Crear el proyecto con Spring Boot**

1. Abrir [Spring Initializr](https://start.spring.io/) en tu navegador.
2. Configurar el proyecto de la siguiente manera:

   * **Project:** Maven
   * **Language:** Java
   * **Spring Boot:** 3.x (última estable)
   * **Group:** `com.ejemplo`
   * **Artifact:** `demo`
   * **Packaging:** Jar
   * **Java:** 17 o superior
3. Añadir las siguientes dependencias:

   * **Spring Web** (para crear controladores REST)
   * **Spring Boot DevTools** (opcional, para recarga automática)
4. Hacer clic en **Generate** para descargar el proyecto.
5. Descomprimir el archivo y abrirlo en tu IDE (IntelliJ, Eclipse o VS Code).

---

## **Paso 2: Explorar la estructura del proyecto**

Después de abrir el proyecto en el IDE, la estructura típica será:

```
demo
├─ src
│  ├─ main
│  │  ├─ java
│  │  │  └─ com/ejemplo/demo
│  │  │      └─ DemoApplication.java
│  │  │
│  │  └─ resources
│  │      ├─ application.properties
│  │      ├─ static
│  │      └─ templates
├─ pom.xml
```

* **DemoApplication.java** contiene el método `main()` que arranca automáticamente el **servidor embebido** (Tomcat).
* **resources/application.properties** es donde configuraremos propiedades clave de la app.

---

## **Paso 3: Crear el controlador `/hola`**

1. Crear un paquete nuevo llamado `controllers` dentro de `com.ejemplo.demo`.
2. Crear un archivo `HolaController.java` con el siguiente contenido:

```java
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

## **Paso 4: Configuración inicial en application.properties**

Crear o editar `src/main/resources/application.properties` con:

```properties
# Puerto del servidor
server.port=8081

# Path base para todos los endpoints
server.servlet.context-path=/api

# Nombre de la aplicación
spring.application.name=DemoSpringBoot

# Mensaje dinámico que devolverá el endpoint
app.mensaje.hola=¡Hola desde Spring Boot configurado!

# Versión de la aplicación
app.version=1.0.0
```

---

## **Paso 5: Ejecutar la aplicación**

1. Ejecutar `DemoApplication.java` como aplicación Java.
2. Abrir el navegador y visitar:

```
http://localhost:8081/api/hola
```

**Resultado esperado:**

```
DemoSpringBoot (v1.0.0): ¡Hola desde Spring Boot configurado!
```

---

## **Paso 6: Probar cambios de configuración uno por uno**

1. **Cambiar el puerto**

   ```properties
   server.port=9090
   ```

   * Resultado: la app arranca en `http://localhost:9090/api/hola`.

2. **Cambiar el context-path**

   ```properties
   server.servlet.context-path=/v1
   ```

   * Resultado: el endpoint queda en `http://localhost:8081/v1/hola`.

3. **Cambiar el nombre de la app**

   ```properties
   spring.application.name=MiAppSpring
   ```

   * Resultado: `/hola` devuelve `MiAppSpring (v1.0.0): ¡Hola desde Spring Boot configurado!`.

4. **Cambiar el mensaje dinámico**

   ```properties
   app.mensaje.hola=Hola alumnos!
   ```

   * Resultado: `/hola` devuelve `MiAppSpring (v1.0.0): Hola alumnos!`.

5. **Cambiar la versión**

   ```properties
   app.version=2.0.0
   ```

   * Resultado: `/hola` devuelve `MiAppSpring (v2.0.0): Hola alumnos!`.

---

## **Conclusión**

* Los alumnos han creado un proyecto Spring Boot desde cero.
* Han visto cómo la **configuración mediante propiedades** impacta en puerto, path, nombre, mensaje y versión.
* Este laboratorio prepara para el siguiente módulo: **desarrollo de APIs REST completas y validaciones**.
