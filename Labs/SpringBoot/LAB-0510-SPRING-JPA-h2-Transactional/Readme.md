# 🧪 Laboratorio – OneToOne (Canción ↔ Artista) + Transacciones

## 🎯 Objetivo

Aprenderás a:

* Modelar una relación **OneToOne** entre Canción y Artista
* Persistir datos relacionados
* Mostrar qué pasa **sin `@Transactional`**
* Corregir con **@Transactional** para mantener consistencia

---

# 🚀 Escenario

* Cada **Canción** tiene un **Artista**
* Se crea una Canción y su Artista
* ❌ Sin transacción → si falla algo, la base queda inconsistente
* ✅ Con transacción → todo se revierte si ocurre un error

---

# 🧱 Paso 1: Entidades

## 📦 Paquete

```id="pkg5"
org.indra.demojpa.entities
```

---

## 🎵 Cancion

```java id="ent5"
package org.indra.demojpa.entities;

import jakarta.persistence.*;

@Entity
public class Cancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "artista_id")
    private Artista artista;

    public Long getId() { return id; }

    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    public Artista getArtista() { return artista; }

    public void setArtista(Artista artista) { this.artista = artista; }
}
```

---

## 🧑 Artista

```java id="ent6"
package org.indra.demojpa.entities;

import jakarta.persistence.*;

@Entity
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToOne(mappedBy = "artista")
    private Cancion cancion;

    public Long getId() { return id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public Cancion getCancion() { return cancion; }

    public void setCancion(Cancion cancion) { this.cancion = cancion; }
}
```

---

# 🗄️ Paso 2: Repositories

```java id="repo5"
package org.indra.demojpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.indra.demojpa.entities.Cancion;

public interface CancionRepository extends JpaRepository<Cancion, Long> {
}
```

```java id="repo6"
package org.indra.demojpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.indra.demojpa.entities.Artista;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
}
```

---

# ⚠️ Paso 3: Servicio SIN transacción (problema)

```java id="svc5"
package org.indra.demojpa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.indra.demojpa.entities.*;
import org.indra.demojpa.repositories.*;

@Service
public class CancionService {

    @Autowired
    private CancionRepository cancionRepo;

    @Autowired
    private ArtistaRepository artistaRepo;

    // ❌ SIN @Transactional
    public void crearCancionConError(Cancion cancion) {

        cancionRepo.save(cancion);

        Artista artista = cancion.getArtista();
        artista.setCancion(cancion);
        artistaRepo.save(artista);

        // 💥 error simulado
        if (true) {
            throw new RuntimeException("Error después de guardar!");
        }
    }
}
```

---

# 🌐 Paso 4: Controller

```java id="ctrl5"
package org.indra.demojpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.indra.demojpa.entities.Cancion;
import org.indra.demojpa.services.CancionService;

@RestController
@RequestMapping("/api/canciones")
public class CancionController {

    @Autowired
    private CancionService service;

    @PostMapping("/error")
    public String crearConError(@RequestBody Cancion cancion) {
        service.crearCancionConError(cancion);
        return "OK";
    }
}
```

---

# 🧪 Paso 5: Probar SIN transacción

```bash id="curl5"
curl -X POST http://localhost:3000/api/canciones/error \
-H "Content-Type: application/json" \
-d '{
  "titulo": "De música ligera",
  "artista": {
    "nombre": "Soda Stereo"
  }
}'
```

### 🔥 Resultado esperado

* Canción ✔
* Artista ✔
* 💥 Error
* ❌ La base queda **inconsistente**

---

# ✅ Paso 6: Servicio CON transacción

```java id="svc6"
package org.indra.demojpa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.indra.demojpa.entities.*;
import org.indra.demojpa.repositories.*;

@Service
public class CancionService {

    @Autowired
    private CancionRepository cancionRepo;

    @Autowired
    private ArtistaRepository artistaRepo;

    // ✅ CON @Transactional
    @Transactional
    public void crearCancionConTransaccion(Cancion cancion) {

        cancionRepo.save(cancion);

        Artista artista = cancion.getArtista();
        artista.setCancion(cancion);
        artistaRepo.save(artista);

        // 💥 error simulado
        if (true) {
            throw new RuntimeException("Error después de guardar!");
        }
    }
}
```

---

# 🌐 Paso 7: Endpoint correcto

```java id="ctrl6"
@PostMapping("/transaccion")
public String crearConTransaccion(@RequestBody Cancion cancion) {
    service.crearCancionConTransaccion(cancion);
    return "OK";
}
```

---

# 🧪 Paso 8: Probar CON transacción

```bash id="curl6"
curl -X POST http://localhost:3000/api/canciones/transaccion \
-H "Content-Type: application/json" \
-d '{
  "titulo": "De música ligera",
  "artista": {
    "nombre": "Soda Stereo"
  }
}'
```

### ✅ Resultado esperado

* 💥 Error lanzado
* ❌ Canción **NO guardada**
* ❌ Artista **NO guardado**
* ✅ Base consistente

---

# 🎯 Conclusión

* `OneToOne` funciona igual que relaciones más complejas
* **Sin `@Transactional`** → riesgo de inconsistencias
* **Con `@Transactional`** → rollback automático
* Enseña cómo Spring maneja transacciones de manera práctica
