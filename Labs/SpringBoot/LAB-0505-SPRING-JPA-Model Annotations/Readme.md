# **Laboratorio – Spring Data JPA: Modelo Decorado con Anotaciones**

## **Objetivo**

En este laboratorio aprenderás a:

* Crear un **modelo decorado con anotaciones JPA**.
* Utilizar **Lombok** para evitar escribir getters y setters manualmente.
* Definir cómo se mapea una clase Java a una **tabla de base de datos**.
* Crear un **Repository** usando Spring Data JPA.
* Probar el acceso a datos mediante un **endpoint REST**.

---

# **Paso 1: Crear el proyecto Spring Boot**

Ir a:

```

[https://start.spring.io/](https://start.spring.io/)

```

Configurar:

* **Project:** Maven
* **Language:** Java
* **Spring Boot:** 3.x
* **Group:** `org.tiben`
* **Artifact:** `demohibernate`
* **Packaging:** Jar
* **Java:** 17

Agregar dependencias:

* **Spring Web**
* **Spring Data JPA**
* **H2 Database** (para simplificar el laboratorio)
* **Lombok**

Generar el proyecto y abrirlo en el IDE.

---

# **Paso 2: Verificar la estructura del proyecto**

Estructura típica:

```

demohibernate
├─ src
│  ├─ main
│  │  ├─ java
│  │  │  └─ org/tiben/demohibernate
│  │  │
│  │  └─ resources
│  │      └─ application.properties
├─ pom.xml

```

---

# **Paso 3: Configurar la base de datos**

Abrir:

```

src/main/resources/application.properties

````

Configurar:

```properties
spring.application.name=Demo Hibernate

server.port=3000

spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update

spring.h2.console.enabled=true
````

Esto configura una **base de datos en memoria H2**.

---

# **Paso 4: Crear el paquete de modelos**

Crear el paquete:

```
org.tiben.demohibernate.models
```

---

# **Paso 5: Crear el modelo decorado**

Crear la clase:

```
Cancion.java
```

Código:

```java
package org.tiben.demohibernate.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name="cancion")
public class Cancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "artista", nullable = false)
    private String artista;

}
```

---

## **Explicación de las anotaciones**

**@Entity**

Indica que la clase representa una **tabla en la base de datos**.

---

**@Table**

Define el nombre de la tabla.

```
@Table(name="cancion")
```

---

**@Id**

Define la **clave primaria**.

---

**@GeneratedValue**

Indica que el ID será **generado automáticamente por la base de datos**.

---

**@Column**

Permite personalizar el mapeo de una columna.

Ejemplo:

```
@Column(name="titulo", nullable=false)
```

Esto significa:

* nombre de la columna → `titulo`
* no puede ser `null`

---

**@Getter / @Setter (Lombok)**

Genera automáticamente:

* getters
* setters

Sin necesidad de escribirlos manualmente.

---

# **Paso 6: Crear el paquete de repositorios**

Crear el paquete:

```
org.tiben.demohibernate.repositories
```

---

# **Paso 7: Crear el Repository**

Crear la interfaz:

```
CancionRepository.java
```

Código:

```java
package org.tiben.demohibernate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tiben.demohibernate.models.Cancion;

@Repository
public interface CancionRepository extends JpaRepository<Cancion,Integer> {

}
```

---

## **¿Qué hace JpaRepository?**

Spring Data JPA genera automáticamente métodos como:

* `save()`
* `findAll()`
* `findById()`
* `delete()`

Sin necesidad de escribir SQL.

---

# **Paso 8: Crear un controlador REST**

Crear el paquete:

```
org.tiben.demohibernate.controllers
```

Crear la clase:

```
CancionController.java
```

Código:

```java
package org.tiben.demohibernate.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.tiben.demohibernate.models.Cancion;
import org.tiben.demohibernate.repositories.CancionRepository;

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

Ejecutar la clase principal de Spring Boot.

La aplicación iniciará en:

```
http://localhost:3000
```

---

# **Paso 10: Probar la API**

### Crear una canción

```
POST http://localhost:3000/api/canciones
```

Body JSON:

```json
{
  "titulo": "Persiana Americana",
  "artista": "Soda Stereo"
}
```

---

### Listar canciones

```
GET http://localhost:3000/api/canciones
```

Respuesta esperada:

```json
[
  {
    "id": 1,
    "titulo": "Persiana Americana",
    "artista": "Soda Stereo"
  }
]
```

---

# **Paso 11: Acceder a la consola de H2**

Abrir en el navegador:

```
http://localhost:3000/h2-console
```

Configurar:

```
JDBC URL: jdbc:h2:mem:testdb
User: sa
Password:
```

Esto permite **ver las tablas y datos creados automáticamente por Hibernate**.

---

# **Conclusión**

En este laboratorio aprendiste a:

* Crear un **modelo decorado con anotaciones JPA**.
* Utilizar **Lombok** para simplificar el código.
* Mapear clases Java a **tablas de base de datos**.
* Crear un **Repository con Spring Data JPA**.
* Exponer operaciones de persistencia mediante una **API REST**.

