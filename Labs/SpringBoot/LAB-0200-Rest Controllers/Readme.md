# **Laboratorio – Controladores REST en Spring Boot**

## **Objetivo del laboratorio**

En este laboratorio aprenderás a:

* Crear endpoints REST con **Spring Boot** usando `@RestController`.
* Recibir datos mediante **query params, path variables y request body**.
* Usar **listas y diccionarios** en request body.
* Trabajar con **headers HTTP** y **parámetros opcionales**.
* Probar los endpoints directamente desde **Eclipse** con una extensión RESTClient.
* Interpretar la **respuesta esperada** de cada endpoint.

---

## **Paso 0: Preparar el proyecto**

1. Crear un proyecto Spring Boot usando **Spring Initializr**:

   * Dependencias: `Spring Web`, `DevTools` (opcional).
2. Abrir proyecto en **Eclipse**.
3. Crear paquete:

   ```
   src/main/java/com/ejemplo/demo/controllers
   ```
4. Crear clase vacía:

   ```java
   public class ApiDemoController {}
   ```

---

## **Paso 1: GET – Query params simples**

**Objetivo parcial:** recibir un **String** (nombre) y un **int** (edad) y devolver un saludo.

**Código:**

```java
@GetMapping("/saludoQuery")
public String saludoQuery(@RequestParam String nombre,
                          @RequestParam int edad) {
    return "Hola " + nombre + " de " + edad + " años (query params)";
}
```

**Probar en RESTClient:**

```
GET http://localhost:8081/api/saludoQuery?nombre=Juan&edad=30
```

**Respuesta esperada:**

```
Hola Juan de 30 años (query params)
```

---

## **Paso 2: GET – Path variables**

**Objetivo parcial:** recibir parámetros por **path**.

**Código:**

```java
@GetMapping("/saludoPath/{nombre}/{edad}")
public String saludoPath(@PathVariable String nombre,
                         @PathVariable int edad) {
    return "Hola " + nombre + " de " + edad + " años (path variables)";
}
```

**Probar en RESTClient:**

```
GET http://localhost:8081/api/saludoPath/Juan/30
```

**Respuesta esperada:**

```
Hola Juan de 30 años (path variables)
```

---

## **Paso 3: POST – Request body simple**

**Objetivo parcial:** recibir un JSON con **nombre** y **edad**.

**Código:**

```java
@PostMapping("/saludoBody")
public String saludoBody(@RequestBody Map<String, Object> payload) {
    String nombre = (String) payload.get("nombre");
    int edad = (Integer) payload.get("edad");
    return "Hola " + nombre + " de " + edad + " años (body simple)";
}
```

**Probar en RESTClient:**

```
POST http://localhost:8081/api/saludoBody
Content-Type: application/json
Body:
{
  "nombre": "Juan",
  "edad": 30
}
```

**Respuesta esperada:**

```
Hola Juan de 30 años (body simple)
```

---

## **Paso 4: POST – Lista de enteros**

**Objetivo parcial:** recibir una lista y devolver la **sumatoria**.

**Código:**

```java
@PostMapping("/sumatoriaLista")
public int sumatoriaLista(@RequestBody List<Integer> numeros) {
    return numeros.stream().mapToInt(Integer::intValue).sum();
}
```

**Probar:**

```
POST http://localhost:8081/api/sumatoriaLista
Content-Type: application/json
Body: [1,2,3,4,5]
```

**Respuesta esperada:**

```
15
```

---

## **Paso 5: POST – Diccionario**

**Objetivo parcial:** recibir un **diccionario String→Integer** y devolver la **sumatoria de los valores**.

**Código:**

```java
@PostMapping("/sumatoriaMap")
public int sumatoriaMap(@RequestBody Map<String, Integer> edades) {
    return edades.values().stream().mapToInt(Integer::intValue).sum();
}
```

**Probar:**

```
POST http://localhost:8081/api/sumatoriaMap
Content-Type: application/json
Body:
{
  "Juan": 30,
  "Ana": 25,
  "Luis": 20
}
```

**Respuesta esperada:**

```
75
```

---

## **Paso 6: GET – Query param opcional**

**Objetivo parcial:** recibir un parámetro que puede faltar, usando **valor por defecto**.

**Código:**

```java
@GetMapping("/saludoOpcional")
public String saludoOpcional(@RequestParam(defaultValue = "Anonimo") String nombre) {
    return "Hola " + nombre + " (query param opcional)";
}
```

**Probar:**

1. Con parámetro:

```
GET http://localhost:8081/api/saludoOpcional?nombre=Pedro
```

**Respuesta esperada:**

```
Hola Pedro (query param opcional)
```

2. Sin parámetro:

```
GET http://localhost:8081/api/saludoOpcional
```

**Respuesta esperada:**

```
Hola Anonimo (query param opcional)
```

---

## **Paso 7: GET – Header HTTP**

**Objetivo parcial:** recibir datos por **headers HTTP**.

**Código:**

```java
@GetMapping("/saludoHeader")
public String saludoHeader(@RequestHeader("X-Nombre") String nombre,
                           @RequestHeader(value = "X-Idioma", defaultValue = "ES") String idioma) {
    return "Hola " + nombre + " (idioma: " + idioma + ") desde header HTTP";
}
```

**Probar:**

```
GET http://localhost:8081/api/saludoHeader
Headers:
X-Nombre: Juan
X-Idioma: EN
```

**Respuesta esperada:**

```
Hola Juan (idioma: EN) desde header HTTP
```

---

## **Paso 8: GET – Suma simple con query params**

**Objetivo parcial:** recibir dos enteros y devolver la suma.

**Código:**

```java
@GetMapping("/sumarQuery")
public int sumarQuery(@RequestParam int a, @RequestParam int b) {
    return a + b;
}
```

**Probar:**

```
GET http://localhost:8081/api/sumarQuery?a=5&b=7
```

**Respuesta esperada:**

```
12
```

---

## **Paso 9: Código completo del controlador**

```java
package com.ejemplo.demo.controllers;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiDemoController {

    @GetMapping("/saludoQuery")
    public String saludoQuery(@RequestParam String nombre,
                              @RequestParam int edad) {
        return "Hola " + nombre + " de " + edad + " años (query params)";
    }

    @GetMapping("/saludoPath/{nombre}/{edad}")
    public String saludoPath(@PathVariable String nombre,
                             @PathVariable int edad) {
        return "Hola " + nombre + " de " + edad + " años (path variables)";
    }

    @PostMapping("/saludoBody")
    public String saludoBody(@RequestBody Map<String, Object> payload) {
        String nombre = (String) payload.get("nombre");
        int edad = (Integer) payload.get("edad");
        return "Hola " + nombre + " de " + edad + " años (body simple)";
    }

    @PostMapping("/sumatoriaLista")
    public int sumatoriaLista(@RequestBody List<Integer> numeros) {
        return numeros.stream().mapToInt(Integer::intValue).sum();
    }

    @PostMapping("/sumatoriaMap")
    public int sumatoriaMap(@RequestBody Map<String, Integer> edades) {
        return edades.values().stream().mapToInt(Integer::intValue).sum();
    }

    @GetMapping("/saludoOpcional")
    public String saludoOpcional(@RequestParam(defaultValue = "Anonimo") String nombre) {
        return "Hola " + nombre + " (query param opcional)";
    }

    @GetMapping("/saludoHeader")
    public String saludoHeader(@RequestHeader("X-Nombre") String nombre,
                               @RequestHeader(value = "X-Idioma", defaultValue = "ES") String idioma) {
        return "Hola " + nombre + " (idioma: " + idioma + ") desde header HTTP";
    }

    @GetMapping("/sumarQuery")
    public int sumarQuery(@RequestParam int a, @RequestParam int b) {
        return a + b;
    }
}
```

-]
