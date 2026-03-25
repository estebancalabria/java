# 🧪 Laboratorio – Persistencia con Spring Boot usando H2 (Base de datos en memoria)

## 🎯 Objetivo

En este laboratorio aprenderás a:

* Integrar **Spring Data JPA** en una aplicación Spring Boot.
* Utilizar una base de datos **en memoria (H2)**.
* Configurar dependencias en Maven.
* Configurar la conexión en `application.properties`.
* Crear una **Entidad JPA**.
* Crear un **Repository**.
* Exponer datos mediante una **API REST**.
* Visualizar los datos desde una **consola web**.

---

# 🚀 Paso 1: Crear el proyecto Spring Boot

Ir a:

👉 [https://start.spring.io/](https://start.spring.io/)

Configurar:

* **Project:** Maven
* **Language:** Java
* **Spring Boot:** 3.x
* **Group:** org.indra
* **Artifact:** demo-jpa
* **Packaging:** Jar
* **Java:** 17 o superior

### 📦 Dependencias:

* Spring Web
* Spring Data JPA
* H2 Database

👉 Presionar **Generate** y descomprimir el `.zip`.

---

# 💻 Paso 2: Abrir el proyecto

Abrir en:

* Eclipse
* IntelliJ
* VS Code

Verificar que Maven descargue dependencias.

---

# 📁 Estructura esperada

```
demo-jpa
├─ src
│  ├─ main
│  │  ├─ java
│  │  └─ resources
│  │      └─ application.properties
├─ pom.xml
```

---

# ⚙️ Paso 3: Configurar H2

Abrir:

```
src/main/resources/application.properties
```

Configurar:

```properties id="h2config01"
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

# 📦 Paso 4: Crear paquete de entidades

Crear:

```
org.indra.demojpa.entities
```

---

# 🧱 Paso 5: Crear la entidad Cancion

Archivo:

```
Cancion.java
```

```java id="entidad01"
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

# 🗄️ Paso 6: Crear el Repository

Crear paquete:

```
org.indra.demojpa.repositories
```

Archivo:

```
CancionRepository.java
```

```java id="repo01"
package org.indra.demojpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.indra.demojpa.entities.Cancion;

public interface CancionRepository extends JpaRepository<Cancion, Long> {

}
```

👉 Spring genera automáticamente:

* `save()`
* `findAll()`
* `findById()`
* `delete()`

---

# 🌐 Paso 7: Crear el Controller REST

Crear paquete:

```
org.indra.demojpa.controllers
```

Archivo:

```
CancionController.java
```

```java id="controller01"
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
}
```

---

# ▶️ Paso 8: Ejecutar la aplicación

Ejecutar la clase principal.

La app inicia en:

```
http://localhost:3000
```

---

# 🧪 Paso 9: Probar la API

## ➕ Crear canción

```
POST http://localhost:3000/api/canciones
```

Body:

```json id="json01"
{
  "titulo": "De música ligera",
  "artista": "Soda Stereo"
}
```

Comando Curl

```cmd
curl -X POST http://localhost:3000/api/canciones -H "Content-Type: application/json" -d "{\"titulo\":\"De música ligera\",\"artista\":\"Soda Stereo\"}"
```

---

## 📄 Listar canciones

```
GET http://localhost:3000/api/canciones
```

Respuesta:

```json id="json02"
[
  {
    "id": 1,
    "titulo": "De música ligera",
    "artista": "Soda Stereo"
  }
]
```

---

# 🖥️ Paso 10: Ver la base de datos (H2 Console)

Abrir:

```
http://localhost:3000/h2-console
```

Configurar:

* **JDBC URL:** `jdbc:h2:mem:testdb`
* **User:** `sa`
* **Password:** (vacío)

👉 Click en **Connect**

Luego ejecutar:

```sql id="sql01"
SELECT * FROM CANCION;
```

> NOTA: Asegurarte jdbc:h2:mem:testdb

---

# ⚠️ Importante

* La base de datos es **en memoria**
* Los datos se pierden al reiniciar la app

---

# 🎯 Conclusión

En este laboratorio aprendiste a:

* Usar **Spring Data JPA**
* Trabajar con una base de datos **sin instalar nada**
* Crear una **Entidad JPA**
* Crear un **Repository**
* Exponer una **API REST**
* Visualizar datos en una **consola web**

