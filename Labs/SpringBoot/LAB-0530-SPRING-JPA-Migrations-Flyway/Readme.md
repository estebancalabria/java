# 🧪 Laboratorio – Flyway + H2 persistente (tabla creada por Flyway)

## 🎯 Objetivo

* Crear un proyecto Spring Boot desde Spring Initializr
* Configurar H2 persistente
* Crear la tabla inicial mediante Flyway
* Aplicar una migración que agregue una columna y actualice datos
* Observar los cambios y el historial de migraciones en H2 Console

---

## Paso 1: Crear proyecto Spring Boot

1. Ir a [Spring Initializr](https://start.spring.io/)
2. Configurar:

| Campo       | Valor       |
| ----------- | ----------- |
| Project     | Maven       |
| Language    | Java        |
| Spring Boot | 3.x         |
| Group       | org.indra   |
| Artifact    | demo-flyway |
| Packaging   | Jar         |
| Java        | 17          |

3. Añadir dependencias:

* Spring Data JPA
* H2 Database
* Flyway Migration

4. Presionar **Generate** y descomprimir el proyecto.
5. Abrir en **Eclipse / IntelliJ / VS Code**.
6. Verificar que Maven descargue todas las dependencias.

---

## Paso 2: Configurar H2 persistente y Flyway

Archivo `src/main/resources/application.properties` **sin comentarios**:

```properties id="fw0qv3"
spring.application.name=demo-flyway
server.port=3000
spring.datasource.url=jdbc:h2:file:~/demo-flyway-db
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=none
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.flyway.enabled=true
spring.flyway.baseline-on-migrate=true
```

> `jdbc:h2:file:~/demo-flyway-db` asegura que la base persista en disco, no en memoria.

---

## Paso 3: Crear carpeta de migraciones

Crear carpeta:

```
src/main/resources/db/migration
```

Flyway detectará todos los archivos con el patrón:

```
V<version>__<descripcion>.sql
```

---

## Paso 4: Script inicial – crear tabla y datos

Archivo: `V1__crear_tabla_artista.sql`

```sql id="t1qk5b"
CREATE TABLE ARTISTA (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    NOMBRE VARCHAR(100) NOT NULL
);

INSERT INTO ARTISTA (NOMBRE) VALUES ('Soda Stereo');
INSERT INTO ARTISTA (NOMBRE) VALUES ('Queen');
INSERT INTO ARTISTA (NOMBRE) VALUES ('AC/DC');
```

> Esto crea la tabla **desde cero** y agrega datos iniciales.

---

## Paso 5: Script de migración – agregar columna y actualizar datos

Archivo: `V2__agregar_columna_nacionalidad.sql`

```sql id="vjq9u2"
ALTER TABLE ARTISTA ADD COLUMN NACIONALIDAD VARCHAR(50);
UPDATE ARTISTA SET NACIONALIDAD = 'Argentina';
```

> Todos los artistas tendrán la nacionalidad `Argentina`.

---

## Paso 6: Ejecutar la aplicación

1. Ejecutar la clase principal `DemoFlywayApplication.java`
2. Flyway aplicará **V1** y **V2** en orden automáticamente
3. Flyway crea la tabla `flyway_schema_history` para registrar qué migraciones se ejecutaron

---

## Paso 7: Ver resultados en H2 Console

1. Abrir H2 Console: [http://localhost:3000/h2-console](http://localhost:3000/h2-console)
2. Configurar:

```
JDBC URL: jdbc:h2:file:~/demo-flyway-db
User: sa
Password: (vacío)
```

3. Click **Connect**

---

### Ver tabla `ARTISTA`

```sql id="j5d2sx"
SELECT * FROM ARTISTA;
```

Deberías ver:

| ID | NOMBRE      | NACIONALIDAD |
| -- | ----------- | ------------ |
| 1  | Soda Stereo | Argentina    |
| 2  | Queen       | Argentina    |
| 3  | AC/DC       | Argentina    |

---

### Ver historial de migraciones

```sql id="p3c2wz"
SELECT * FROM FLYWAY_SCHEMA_HISTORY;
```

Ejemplo de salida:

| installed_rank | version | description                  | type | script                               | success |
| -------------- | ------- | ---------------------------- | ---- | ------------------------------------ | ------- |
| 1              | 1       | crear tabla artista          | SQL  | V1__crear_tabla_artista.sql          | TRUE    |
| 2              | 2       | agregar columna nacionalidad | SQL  | V2__agregar_columna_nacionalidad.sql | TRUE    |

---

## Paso 8: Agregar nuevas migraciones (opcional)

* Crear `V3__insertar_artista_nuevo.sql`:

```sql id="x5b2qr"
INSERT INTO ARTISTA (NOMBRE, NACIONALIDAD) VALUES ('The Beatles','Reino Unido');
```

* Reiniciar la aplicación
* Consultar `ARTISTA` y `FLYWAY_SCHEMA_HISTORY`
* Solo se aplicará la nueva migración, Flyway no reejecuta las anteriores

---

## ✅ Conclusión

* La tabla se creó **completamente con Flyway**
* Flyway aplica migraciones **ordenadas y persistentes**
* La base H2 en disco permite **ver cambios antes y después**
* `FLYWAY_SCHEMA_HISTORY` muestra **qué scripts se ejecutaron y cuándo**
* Ideal para laboratorio didáctico: se ve claramente cómo evoluciona la base
