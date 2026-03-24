# 🧪 Laboratorio – Persistencia con Spring Boot usando H2 + Consultas Avanzadas

## 🎯 Objetivo

En este laboratorio aprenderás a:

* Integrar Spring Data JPA en una aplicación Spring Boot.
* Utilizar una base de datos en memoria (H2).
* Configurar dependencias en Maven.
* Configurar la conexión en `application.properties`.
* Crear una Entidad JPA.
* Crear un Repository.
* Exponer datos mediante una API REST.
* Visualizar los datos desde una consola web.
* 🆕 Crear **consultas derivadas automáticamente**.
* 🆕 Crear **consultas personalizadas con JPQL**.

---

## 🚀 Paso 1: Crear el proyecto Spring Boot

👉 [https://start.spring.io/](https://start.spring.io/)

**Configurar:**

* Project: Maven
* Language: Java
* Spring Boot: 3.x
* Group: org.indra
* Artifact: demo-jpa
* Packaging: Jar
* Java: 17 o superior

📦 **Dependencias:**

* Spring Web
* Spring Data JPA
* H2 Database

👉 Generate y descomprimir.

---

## 💻 Paso 2: Abrir el proyecto

Abrir en:

* Eclipse / IntelliJ / VS Code

Verificar que Maven descargue dependencias.

---

## ⚙️ Paso 3: Configurar H2

📄 `application.properties`

```properties
spring.application.name=Aplicacion Clase H2

server.port=3000

spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

---

## 📦 Paso 4: Crear paquete de entidades

```
org.indra.demojpa.entities
```

---

## 🧱 Paso 5: Crear la entidad Cancion

```java
package org.indra.demojpa.entities;

import jakarta.persistence.*;

@Entity
public class Cancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String artista;

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }
}
```

---

## 🗄️ Paso 6: Crear el Repository

📁 `org.indra.demojpa.repositories`

```java
package org.indra.demojpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.indra.demojpa.entities.Cancion;

public interface CancionRepository extends JpaRepository<Cancion, Long> {

    // 🔹 CONSULTAS DERIVADAS (Spring las genera automáticamente)

    List<Cancion> findByArtista(String artista);

    List<Cancion> findByTituloContaining(String texto);

    List<Cancion> findByArtistaAndTituloContaining(String artista, String titulo);

    // 🔹 CONSULTAS PERSONALIZADAS (JPQL)

    @Query("SELECT c FROM Cancion c WHERE c.artista = ?1")
    List<Cancion> buscarPorArtistaJPQL(String artista);

    @Query("SELECT c FROM Cancion c WHERE LOWER(c.titulo) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Cancion> buscarPorTituloFlexible(String texto);
}
```

👉 Acá está la magia:

* Spring interpreta el nombre del método → SQL automático
* Con `@Query` escribís tu propia lógica

---

## 🌐 Paso 7: Crear el Controller REST

📁 `org.indra.demojpa.controllers`

```java
package org.indra.demojpa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.indra.demojpa.entities.Cancion;
import org.indra.demojpa.repositories.CancionRepository;

@RestController
@RequestMapping("/api/canciones")
public class CancionController {

    @Autowired
    private CancionRepository repository;

    @PostMapping
    public Cancion crear(@RequestBody Cancion cancion) {
        return repository.save(cancion);
    }

    @GetMapping
    public List<Cancion> listar() {
        return repository.findAll();
    }

    // 🔹 DERIVADAS

    @GetMapping("/artista/{artista}")
    public List<Cancion> porArtista(@PathVariable String artista) {
        return repository.findByArtista(artista);
    }

    @GetMapping("/buscar")
    public List<Cancion> porTitulo(@RequestParam String texto) {
        return repository.findByTituloContaining(texto);
    }

    // 🔹 PERSONALIZADAS

    @GetMapping("/jpql/{artista}")
    public List<Cancion> porArtistaJPQL(@PathVariable String artista) {
        return repository.buscarPorArtistaJPQL(artista);
    }

    @GetMapping("/flex")
    public List<Cancion> busquedaFlexible(@RequestParam String texto) {
        return repository.buscarPorTituloFlexible(texto);
    }
}
```

---

## ▶️ Paso 8: Ejecutar la aplicación

👉 [http://localhost:3000](http://localhost:3000)

---

## 🧪 Paso 9: Probar la API

### ➕ Crear canción

```bash
curl -X POST http://localhost:3000/api/canciones \
-H "Content-Type: application/json" \
-d "{\"titulo\":\"De música ligera\",\"artista\":\"Soda Stereo\"}"
```

---

### 📄 Listar

```bash
curl http://localhost:3000/api/canciones
```

---

### 🔍 Buscar por artista (DERIVADA)

```bash
curl http://localhost:3000/api/canciones/artista/Soda%20Stereo
```

---

### 🔍 Buscar por título (LIKE automático)

```bash
curl "http://localhost:3000/api/canciones/buscar?texto=musica"
```

---

### 🔍 JPQL personalizada

```bash
curl http://localhost:3000/api/canciones/jpql/Soda%20Stereo
```

---

### 🔍 Búsqueda flexible (case insensitive)

```bash
curl "http://localhost:3000/api/canciones/flex?texto=ligera"
```

---

## 🖥️ Paso 10: Ver la base de datos (H2 Console)

👉 [http://localhost:3000/h2-console](http://localhost:3000/h2-console)

**Config:**

* JDBC URL: `jdbc:h2:mem:testdb`
* User: `sa`
* Password: vacío

```sql
SELECT * FROM CANCION;
```

---

## ⚠️ Importante

* La base es en memoria
* Se pierde al reiniciar

---

## 🎯 Conclusión

Ahora además aprendiste:

### 🔹 Consultas derivadas

* `findByArtista`
* `findByTituloContaining`
  👉 Sin escribir SQL

### 🔹 Consultas personalizadas

* `@Query` con JPQL
  👉 Control total de la consulta

