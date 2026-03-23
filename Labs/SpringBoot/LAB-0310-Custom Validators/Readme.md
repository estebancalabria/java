# 🧪 Laboratorio – Validación Custom con Bean Validation en Spring Boot (sin persistencia)

## 🎯 Objetivo

En este laboratorio aprenderás a:

* Crear una **validación personalizada** con Bean Validation.
* Definir una anotación propia (`@NombreValido`).
* Implementar la lógica de validación.
* Validar requests automáticamente con `@Valid`.
* Manejar errores de forma clara.

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

👉 Presionar **Generate** y descomprimir.

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
│  │  │   └─ org.indra.demovalidaciones
│  │  └─ resources
│  │      └─ application.properties
├─ pom.xml
```

---

# 📦 Paso 3: Crear paquete de validaciones

Dentro de:

```
src/main/java/org/indra/demovalidaciones
```

Crear el paquete:

```
validators
```

👉 Queda:

```
org.indra.demovalidaciones.validators
```

---

# 🧩 Paso 4: Crear la anotación custom

Archivo:

```
NombreValido.java
```

```java
package org.indra.demovalidaciones.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NombreValidoValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NombreValido {

    String message() default "El nombre no es válido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
```

---

# ⚙️ Paso 5: Crear el Validator

Archivo:

```
NombreValidoValidator.java
```

```java
package org.indra.demovalidaciones.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NombreValidoValidator implements ConstraintValidator<NombreValido, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null || value.isEmpty()) {
            return false;
        }

        // Regla: solo letras y espacios
        return value.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$");
    }
}
```

---

# 📦 Paso 6: Crear paquete de modelos

Crear paquete:

```
org.indra.demovalidaciones.models
```

---

# 🧱 Paso 7: Crear el modelo Persona

Archivo:

```
Persona.java
```

```java
package org.indra.demovalidaciones.models;

import org.indra.demovalidaciones.validators.NombreValido;

public class Persona {

    @NombreValido
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
```

👉 🔥 Importante:
Este lab usa **solo validación custom** (no usamos `@NotNull`, `@Size`, etc.)

---

# 📦 Paso 8: Crear paquete de controllers

Crear paquete:

```
org.indra.demovalidaciones.controllers
```

---

# 🌐 Paso 9: Crear el Controller

Archivo:

```
PersonaController.java
```

```java
package org.indra.demovalidaciones.controllers;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import org.indra.demovalidaciones.models.Persona;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    @PostMapping
    public String crear(@Valid @RequestBody Persona persona) {
        return "Persona válida";
    }
}
```

---

# 📦 Paso 10: Crear paquete de excepciones

Crear paquete:

```
org.indra.demovalidaciones.exceptions
```

---

# ⚠️ Paso 11: Crear GlobalExceptionHandler

Archivo:

```
GlobalExceptionHandler.java
```

```java
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

# ▶️ Paso 12: Ejecutar la aplicación

```
http://localhost:8080
```

---

# 🧪 Paso 13: Probar la API

## ❌ Caso inválido

```json
{
  "nombre": "Juan123"
}
```

👉 Respuesta:

```json
{
  "nombre": "El nombre no es válido"
}
```

---

## ❌ Caso inválido (vacío)

```json
{
  "nombre": ""
}
```

---

## ✅ Caso válido

```json
{
  "nombre": "Juan Perez"
}
```

👉 Respuesta:

```
Persona válida
```

---

# 🧠 Conceptos clave aprendidos

* Crear anotaciones custom (`@interface`)
* Usar `@Constraint`
* Implementar `ConstraintValidator`
* Integrar validación con `@Valid`
* Centralizar errores con `@RestControllerAdvice`

---

# 🎯 Conclusión

En este laboratorio aprendiste a:

* Crear tu propia validación
* Aplicar reglas de negocio personalizadas
* Validar datos sin base de datos
* Mejorar la calidad de tu API

---

# 🚀 Próximo paso sugerido

👉 Combinar esto con:

* DTO + Service
* Arquitectura limpia
* Validaciones más complejas

