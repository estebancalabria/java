# 🧪 Laboratorio – Spring Cloud Config Server (Básico, sin perfiles)

## 🎯 Objetivo

En este laboratorio aprenderás a:

* Crear un **Config Server** desde cero
* Crear un repositorio de configuración
* Conectar microservicios como **Config Clients**
* Obtener configuración externa (sin usar `application.yml` local)

---

# 🖥️ Organización

Vas a trabajar con **3 proyectos (simulando 3 Eclipse)**:

1. `config-server`
2. `servicio-a`
3. `servicio-b`

👉 Podés tenerlos en el mismo workspace.

---

# 🔹 Paso 1: Crear el Config Server

## 🌐 1.1 Ir a Spring Initializr

Entrar a:
👉 [https://start.spring.io/](https://start.spring.io/)

---

## ⚙️ 1.2 Configurar el proyecto

* **Project:** Maven
* **Language:** Java
* **Spring Boot:** última versión estable (3.x)
* **Group:** `com.ejemplo`
* **Artifact:** `config-server`
* **Packaging:** Jar
* **Java:** 17 o superior

---

## 📦 1.3 Agregar dependencias

Buscar y agregar:

* ✅ **Spring Web**
* ✅ **Spring Cloud Config Server**

---

## ⬇️ 1.4 Generar proyecto

* Click en **Generate**
* Descargar ZIP
* Descomprimir
* Importar en Eclipse:

  * File → Import → Existing Maven Project

---

## 🧩 1.5 Habilitar Config Server

Abrir la clase principal:

```java
package com.ejemplo.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
```

---

## ⚙️ 1.6 Configurar application.yml

Ir a:

```
src/main/resources/application.yml
```

Reemplazar contenido por:

```yaml
server:
  port: 8888

spring:
  cloud:
    config:
      server:
        git:
          uri: C:/config-repo
```

👉 Vamos a crear esa carpeta en el siguiente paso

---

# 🔹 Paso 2: Crear repositorio de configuración

## 📁 2.1 Crear carpeta local

Crear en tu PC:

```bash
C:/config-repo
```

👉 (Podés usar cualquier ruta, pero debe coincidir con el YAML)

---

## 📄 2.2 Crear archivos de configuración

### Archivo: servicio-a.yml

```yaml
server:
  port: 8081

app:
  mensaje: "Hola desde Servicio A (Config Server)"
```

---

### Archivo: servicio-b.yml

```yaml
server:
  port: 8082

app:
  mensaje: "Hola desde Servicio B (Config Server)"
```

---

# 🔹 Paso 3: Probar Config Server

## ▶️ 3.1 Ejecutar proyecto

* Botón derecho → Run As → Spring Boot App

---

## 🌐 3.2 Probar en navegador

👉 Abrir:

```
http://localhost:8888/servicio-a/default
```

---

## 📥 Resultado esperado

Un JSON con propiedades:

```json
{
  "name": "servicio-a",
  "propertySources": [...]
}
```

👉 Si ves esto → ✅ Config Server funcionando

---

# 🔹 Paso 4: Crear Microservicio A

## 🌐 4.1 Ir a Spring Initializr

👉 [https://start.spring.io/](https://start.spring.io/)

---

## ⚙️ 4.2 Configuración

* **Group:** com.ejemplo
* **Artifact:** servicio-a

---

## 📦 4.3 Dependencias

* ✅ Spring Web
* ✅ Spring Cloud Config Client

---

## ⬇️ 4.4 Importar en Eclipse

Igual que antes.

---

## ⚙️ 4.5 Crear application.yml

📁 `src/main/resources/application.yml`

```yaml
spring:
  application:
    name: servicio-a

  config:
    import: "optional:configserver:http://localhost:8888"
```

👉 Esto reemplaza `application.propieties`

---

## 🧩 4.6 Crear Controller

```java
package com.ejemplo.servicioa;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaController {

    @Value("${app.mensaje}")
    private String mensaje;

    @GetMapping("/hola")
    public String saludar() {
        return mensaje;
    }
}
```

---

# 🔹 Paso 5: Crear Microservicio B

Repetir exactamente lo mismo que A.

---

## ⚙️ bootstrap.yml

```yaml
spring:
  application:
    name: servicio-b

  config:
    import: "optional:configserver:http://localhost:8888"
```

---

## 🧩 Controller

Igual que el anterior.

---

# 🔹 Paso 6: Ejecutar todo

## 🟢 Orden

1. ▶️ Config Server
2. ▶️ Servicio A
3. ▶️ Servicio B

---

# 🔹 Paso 7: Verificar

## 🌐 Servicio A

```
http://localhost:8081/hola
```

👉 Resultado:

```
Hola desde Servicio A (Config Server)
```

---

## 🌐 Servicio B

```
http://localhost:8082/hola
```

👉 Resultado:

```
Hola desde Servicio B (Config Server)
```

---

# 🔹 Paso 8: Experimento clave 🔥

👉 Cambiar en:

```
C:/config-repo/servicio-a.yml
```

Ej:

```yaml
app:
  mensaje: "CAMBIO EN CALIENTE"
```

---

## 🔄 Reiniciar servicio A

👉 Ver resultado actualizado sin tocar código

---

# 🧠 Conceptos que aprendieron

✔ Configuración externa
✔ Separación código vs configuración
✔ Microservicios consumiendo config central
✔ Uso básico de Spring Cloud Config

---

# 🎯 Conclusión didáctica

👉 Antes:

* Cada app tenía su `application.yml`

👉 Ahora:

* Todas las apps leen desde un **servidor central**

\
