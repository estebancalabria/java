# 🧪 Laboratorio – Documentación de APIs con OpenAPI / Swagger en Spring Boot

## 🎯 Objetivo

En este laboratorio aprenderás a:

* Documentar una API automáticamente con **Swagger / OpenAPI**.
* Visualizar endpoints en una interfaz web.
* Probar endpoints directamente desde el navegador.
* Agregar descripciones a la API.

---

# 🚀 Paso 1: Crear el proyecto Spring Boot

Ir a:

👉 [https://start.spring.io/](https://start.spring.io/)

Configurar:

* **Project:** Maven
* **Language:** Java
* **Spring Boot:** 3.x
* **Group:** org.indra
* **Artifact:** demo-swagger
* **Packaging:** Jar
* **Java:** 17+

### 📦 Dependencias:

* Spring Web
* SpringBoot Dev Tools (Opcional)
* SpringDoc OpenAPI

👉 Presionar **Generate** y descomprimir.

---

# 💻 Paso 2: Abrir el proyecto

Abrir en tu IDE (Eclipse / IntelliJ / VS Code)

# 📦 Paso 3: Crear paquete de controllers

Crear paquete:

```
org.indra.demoswagger.controllers
```

---

# 🌐 Paso 4: Crear un Controller de prueba

Archivo:

```
PersonaController.java
```

```java id="controller04"
package org.indra.demoswagger.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    @GetMapping
    public String listar() {
        return "Listado de personas";
    }

    @PostMapping
    public String crear(@RequestBody String nombre) {
        return "Persona creada: " + nombre;
    }
}
```

---

# ▶️ Paso 5: Ejecutar la aplicación

Ejecutar la clase principal.

---

# 🌐 Paso 6: Acceder a Swagger UI

Abrir en el navegador:

```
http://localhost:8080/swagger-ui/index.html
```

👉 Vas a ver:

* Lista de endpoints
* Botón **Try it out**
* Posibilidad de ejecutar requests

---

# 🧪 Paso 7: Probar desde Swagger

1. Expandir `/api/personas`
2. Probar `GET`
3. Probar `POST`
4. Ejecutar requests sin usar Postman

---

# ✨ Paso 8: Agregar documentación a los endpoints

Modificar el controller:

```java id="controller05"
package org.indra.demoswagger.controllers;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/personas")
@Tag(name = "Personas", description = "Operaciones relacionadas con personas")
public class PersonaController {

    @Operation(summary = "Obtener todas las personas")
    @GetMapping
    public String listar() {
        return "Listado de personas";
    }

    @Operation(summary = "Crear una nueva persona")
    @PostMapping
    public String crear(@RequestBody String nombre) {
        return "Persona creada: " + nombre;
    }
}
```

👉 Volver a Swagger y ver los cambios.

---

# 🔥 Resultado

Ahora Swagger muestra:

* Nombre del grupo (**Personas**)
* Descripción de endpoints
* Documentación más clara

---

# ⚠️ Problemas comunes

## ❌ No abre Swagger

Probar estas URLs:

```
/swagger-ui/index.html
/swagger-ui.html
```

---

## ❌ Error de dependencia

Verificar versión:

```xml id="dep02"
2.5.0 o superior
```

---

# 🧠 Conceptos clave aprendidos

* OpenAPI como estándar
* Documentación automática
* Uso de Swagger UI
* `@Operation` y `@Tag`

---

# 🎯 Conclusión

En este laboratorio aprendiste a:

* Documentar tu API automáticamente
* Probar endpoints sin herramientas externas
* Mejorar la experiencia del consumidor de tu API
