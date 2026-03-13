# **Laboratorio – Persistencia con Spring Boot: Usando SQLite con Spring Data JPA**

## **Objetivo**

En este laboratorio aprenderás a:

* Integrar **Spring Data JPA** en una aplicación Spring Boot.
* Utilizar **SQLite** como base de datos.
* Configurar dependencias necesarias en **Maven**.
* Configurar la conexión a base de datos en `application.properties`.
* Crear una **Entidad JPA**.
* Crear un **Repository** para acceder a los datos.
* Exponer datos mediante un **endpoint REST**.

---

# **Paso 1: Crear el proyecto Spring Boot**

1. Ir a **Spring Initializr**

```

[https://start.spring.io/](https://start.spring.io/)

```

2. Configurar el proyecto:

* **Project:** Maven
* **Language:** Java
* **Spring Boot:** 3.x
* **Group:** `org.indra`
* **Artifact:** `demo-jpa`
* **Packaging:** Jar
* **Java:** 17 o superior

3. Agregar dependencias:

* **Spring Web**
* **Spring Data JPA**

4. Presionar **Generate**.

5. Descargar el proyecto `.zip` y descomprimirlo.

---

# **Paso 2: Abrir el proyecto en el IDE**

1. Abrir el proyecto en **Eclipse, IntelliJ o VS Code**.
2. Verificar que **Maven descargue todas las dependencias**.

Estructura típica:

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

# **Paso 3: Agregar dependencias para SQLite**

Abrir el archivo:

```

pom.xml

````

Agregar dentro de `<dependencies>`:

```xml
<dependency>
    <groupId>org.xerial</groupId>
    <artifactId>sqlite-jdbc</artifactId>
    <version>3.36.0.3</version>
</dependency>

<!-- Dependencia antigua (ya no necesaria)
<dependency>
    <groupId>com.github.gwenn</groupId>
    <artifactId>sqlite-dialect</artifactId>
    <version>0.1.2</version>
</dependency>
-->

<dependency>
    <groupId>org.hibernate.orm</groupId>
    <artifactId>hibernate-community-dialects</artifactId>
</dependency>
````

---

# **Paso 4: Configurar application.properties**

Abrir:

```
src/main/resources/application.properties
```

Configurar:

```properties
# nombre de la aplicación
spring.application.name=Aplicacion Clase Quince

# puerto del servidor
server.port=3000

# configuración de la base de datos SQLite
spring.datasource.url=jdbc:sqlite:canciones.db

# dialecto de Hibernate para SQLite
spring.jpa.database-platform=org.hibernate.community.dialect.SQLiteDialect

# creación/actualización automática de tablas
spring.jpa.hibernate.ddl-auto=update
```

---

# **Paso 5: Crear el paquete de entidades**

Dentro de `src/main/java` crear el paquete:

```
org.indra.demojpa.entities
```

---

# **Paso 6: Crear la entidad Cancion**

Crear la clase:

```
Cancion.java
```

Código:

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

# **Paso 7: Crear el Repository**

Crear el paquete:

```
org.indra.demojpa.repositories
```

Crear la interfaz:

```
CancionRepository.java
```

Código:

```java
package org.indra.demojpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.indra.demojpa.entities.Cancion;

public interface CancionRepository extends JpaRepository<Cancion, Long> {

}
```

Spring Data JPA generará automáticamente métodos como:

* `save()`
* `findAll()`
* `findById()`
* `delete()`

---

# **Paso 8: Crear el controlador REST**

Crear el paquete:

```
org.indra.demojpa.controllers
```

Crear la clase:

```
CancionController.java
```

Código:

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
}
```

---

# **Paso 9: Ejecutar la aplicación**

Ejecutar la clase principal del proyecto Spring Boot.

La aplicación iniciará en:

```
http://localhost:3000
```

---

# **Paso 10: Probar la API**

### Crear una canción

Endpoint:

```
POST http://localhost:3000/api/canciones
```

Body JSON:

```json
{
  "titulo": "De música ligera",
  "artista": "Soda Stereo"
}
```

---

### Listar canciones

Endpoint:

```
GET http://localhost:3000/api/canciones
```

Respuesta esperada:

```json
[
  {
    "id": 1,
    "titulo": "De música ligera",
    "artista": "Soda Stereo"
  }
]
```

---

# **Paso 11: Ver la base de datos**

Al ejecutar la aplicación se creará automáticamente el archivo:

```
canciones.db
```

Este archivo contiene la **base de datos SQLite** generada por Hibernate.

Puede abrirse con herramientas como:

* DB Browser for SQLite
* SQLiteStudio

---

# **Conclusión**

En este laboratorio aprendiste a:

* Configurar **Spring Data JPA** en Spring Boot.
* Conectar una aplicación con **SQLite**.
* Crear una **Entidad JPA**.
* Crear un **Repository**.
* Exponer operaciones de persistencia mediante una **API REST**.
* Permitir que **Hibernate genere automáticamente las tablas**.

