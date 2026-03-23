# 🧪 Laboratorio – Validación con Bean Validation en Spring Boot (sin persistencia)

## 🎯 Objetivo

En este laboratorio aprenderás a:

* Utilizar **Bean Validation (Jakarta Validation)** en Spring Boot.
* Aplicar validaciones sobre objetos (`@NotNull`, `@Size`, etc.).
* Validar automáticamente requests HTTP con `@Valid`.
* Manejar errores de validación.
* Probar validaciones desde una API REST.

---

# 🚀 Paso 1: Crear el proyecto Spring Boot

Ir a:

👉 [https://start.spring.io/](https://start.spring.io/)

Configurar:

* **Project:** Maven
* **Language:** Java
* **Spring Boot:** 3.x
* **Group:** org.indra
* **Artifact:** demo-validaciones
* **Packaging:** Jar
* **Java:** 17+

### 📦 Dependencias:

* Spring Web
* Validation

👉 Presionar **Generate**

---

# 💻 Paso 2: Abrir el proyecto

Abrir en tu IDE (Eclipse / IntelliJ / VS Code)

---

# 📁 Estructura esperada

```
demo-validaciones
├─ src
│  ├─ main
│  │  ├─ java
│  │  └─ resources
│  │      └─ application.properties
├─ pom.xml
```

---

# 📦 Paso 3: Crear el modelo (DTO con validaciones)

Crear paquete:

```
org.indra.demovalidaciones.models
```

Archivo:

```
Persona.java
```

```java id="modelo01"
package org.indra.demovalidaciones.models;

import jakarta.validation.constraints.*;

public class Persona {

    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @Min(value = 0, message = "La edad no puede ser negativa")
    @Max(value = 120, message = "Edad inválida")
    private int edad;

    @Email(message = "Debe ser un email válido")
    private String email;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
```

---

# 🌐 Paso 4: Crear el Controller

Crear paquete:

```
org.indra.demovalidaciones.controllers
```

Archivo:

```
PersonaController.java
```

```java id="controller02"
package org.indra.demovalidaciones.controllers;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import org.indra.demovalidaciones.models.Persona;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    @PostMapping
    public String crear(@Valid @RequestBody Persona persona) {
        return "Persona válida creada correctamente";
    }
}
```

---

# ▶️ Paso 5: Ejecutar la aplicación

Ejecutar la clase principal.

Por defecto corre en:

```
http://localhost:8080
```

---

# 🧪 Paso 6: Probar la API

## ✅ Caso válido

```
POST http://localhost:8080/api/personas
```

```json id="json03"
{
  "nombre": "Juan",
  "apellido": "Perez",
  "edad": 30,
  "email": "juan@email.com"
}
```

👉 Respuesta:

```
Persona válida creada correctamente
```

---

## ❌ Caso inválido

```json id="json04"
{
  "nombre": "J",
  "apellido": "",
  "edad": -5,
  "email": "correo-invalido"
}
```

👉 Spring responde automáticamente:

```json id="json05"
{
  "timestamp": "...",
  "status": 400,
  "errors": [...]
}
```

---

# ⚠️ Paso 7: Manejo de errores personalizado (opcional)

Para hacerlo más didáctico 👇

Crear clase:

```
GlobalExceptionHandler.java
```

```java id="exception01"
package org.indra.demovalidaciones.exceptions;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> manejarErrores(MethodArgumentNotValidException ex) {

        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
            errores.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(errores);
    }
}
```

---

## 🔥 Resultado mejorado

Ahora el error será:

```json id="json06"
{
  "nombre": "El nombre debe tener entre 2 y 50 caracteres",
  "apellido": "El apellido es obligatorio",
  "edad": "La edad no puede ser negativa",
  "email": "Debe ser un email válido"
}
```

👉 Mucho más claro para el alumno 👌

---

# 🧠 Conceptos clave aprendidos

* `@Valid` → activa validación automática
* `@NotNull` vs `@NotBlank`
* `@Size`, `@Min`, `@Max`, `@Email`
* Manejo de errores con `@RestControllerAdvice`

---

# 🎯 Conclusión

En este laboratorio aprendiste a:

* Validar datos sin base de datos
* Proteger endpoints REST
* Evitar datos inválidos antes de procesarlos
* Mejorar respuestas de error

---

# 🚀 Próximo paso sugerido

👉 Integrar estas validaciones con:

* JPA + Base de datos
* DTOs separados de entidades
* Validaciones custom (`@CustomValidator`)


Si querés, el siguiente nivel te lo armo tipo 💣:
👉 *"Validaciones + DTO + Service + arquitectura limpia"* (ideal para cerrar el módulo).
