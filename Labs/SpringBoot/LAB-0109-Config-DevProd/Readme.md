# **Laboratorio – Fundamentos de Spring Boot: Configuración y perfiles por entorno**

## **Objetivo**

En este laboratorio aprenderás a:

* Crear un proyecto Spring Boot desde cero usando **Spring Initializr**.
* Configurar propiedades clave mediante **YAML**: puerto, context-path, nombre de aplicación, mensaje y versión.
* Usar **perfiles por entorno** (`dev` y `prod`) y ver cómo cambian las propiedades automáticamente.
* Ejecutar la aplicación con distintos perfiles directamente desde **Eclipse**.

---

## **Paso 1: Crear el proyecto con Spring Boot**

1. Abrir [Spring Initializr](https://start.spring.io/).
2. Configurar el proyecto:

   * **Project:** Maven
   * **Language:** Java
   * **Spring Boot:** 3.x (última estable)
   * **Group:** `com.ejemplo`
   * **Artifact:** `demo`
   * **Packaging:** Jar
   * **Java:** 17+
3. Añadir dependencias:

   * **Spring Web** (para crear controladores REST)
   * **Spring Boot DevTools** (opcional, recarga automática)
4. Hacer clic en **Generate**, descargar, descomprimir y abrir en **Eclipse**.

---

## **Paso 2: Explorar la estructura del proyecto**

Estructura típica:

```id="j1l5f0"
demo
├─ src
│  ├─ main
│  │  ├─ java
│  │  │  └─ com/ejemplo/demo
│  │  │      └─ DemoApplication.java
│  │  │
│  │  └─ resources
│  │      ├─ application.yml
│  │      ├─ application-dev.yml
│  │      ├─ application-prod.yml
│  │      ├─ static
│  │      └─ templates
├─ pom.xml
```

* **DemoApplication.java** inicia el **servidor embebido Tomcat**.
* Los archivos YAML contendrán la configuración por perfil.

---

## **Paso 3: Crear el controlador `/hola`**

Archivo `HolaController.java` en paquete `controllers`:

```java id="w5h2k3"
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

## **Paso 4: Configuración en YAML**

### **application.yml** (configuración base)

```yaml id="k2f7l5"
spring:
  application:
    name: DemoSpringBoot
```

### **application-dev.yml** (perfil DEV)

```yaml id="m9t8r2"
server:
  port: 8081
  servlet:
    context-path: /api

app:
  mensaje:
    hola: "¡Hola desde DEV!"
  version: "1.0.0-DEV"
```

### **application-prod.yml** (perfil PROD)

```yaml id="p3h6j9"
server:
  port: 9090
  servlet:
    context-path: /api

app:
  mensaje:
    hola: "¡Hola desde PROD!"
  version: "1.0.0-PROD"
```

---

## **Paso 5: Ejecutar perfiles desde Eclipse**

### **Opción 1: Usando VM arguments**

1. Botón derecho sobre el proyecto → **Run As → Run Configurations…**
2. Seleccionar **Spring Boot App** (o **Java Application**)
3. En **Arguments → VM arguments**, escribir:

```id="v8r6f4"
-Dspring.profiles.active=dev
```

* Para prod:

```id="n2k9p5"
-Dspring.profiles.active=prod
```

4. Hacer clic en **Apply → Run**

### **Opción 2: Spring Boot Dashboard (si está disponible)**

1. Abrir **Spring Boot Dashboard** en Eclipse
2. Seleccionar la aplicación → **Run As → Run Configurations**
3. En **Active Profiles**, escribir `dev` o `prod`
4. Ejecutar → la app arranca con ese perfil automáticamente

---

## **Paso 6: Verificar resultados**

1. **Perfil DEV**:

* URL: `http://localhost:8081/api/hola`
* Resultado esperado:

```id="d2k4p8"
DemoSpringBoot (v1.0.0-DEV): ¡Hola desde DEV!
```

2. **Perfil PROD**:

* URL: `http://localhost:9090/api/hola`
* Resultado esperado:

```id="c5m7j1"
DemoSpringBoot (v1.0.0-PROD): ¡Hola desde PROD!
```

---

## **Paso 7: Experimentar cambios**

* Cambiar **mensaje**, **versión** o **context-path** en los YAML y reiniciar con el mismo perfil.
* Observar cómo **la aplicación responde automáticamente** según el perfil activo.

---

## **Conclusión**

* Los alumnos crean un proyecto Spring Boot completo desde cero.
* Aprenden a usar **perfiles por entorno** (`dev` / `prod`) y ver cómo afectan **puerto, path, mensaje y versión**.
* Practican **configuración en YAML** y la ejecución de distintos perfiles directamente en Eclipse.
* Este laboratorio completa el **Módulo 1**, preparando para el desarrollo de APIs REST y migración de proyectos Spring MVC.

