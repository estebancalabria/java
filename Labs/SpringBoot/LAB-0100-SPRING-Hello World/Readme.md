# **Laboratorio – Spring Boot: Primer API REST (Hello World)**

## **Objetivo**

En este laboratorio aprenderás a:

* Crear un proyecto **Spring Boot** desde cero usando **Spring Initializr**.
* Configurar un proyecto básico con **Spring Web (Spring MVC)**.
* Crear un **controlador REST**.
* Exponer endpoints HTTP.
* Devolver respuestas en **texto** y en **JSON**.

---

# **Paso 1: Crear el proyecto con Spring Initializr**

1. Abrir el sitio:

https://start.spring.io/

2. Configurar el proyecto con los siguientes valores:

* **Project:** Maven
* **Language:** Java
* **Spring Boot:** 3.x
* **Group:** `org.indra`
* **Artifact:** `demomvc`
* **Packaging:** Jar
* **Java:** 17 o superior

3. Hacer clic en **Add Dependencies**

Buscar y agregar:

* **Spring Web**

(Esta dependencia incluye **Spring MVC** para crear APIs REST.)

4. Presionar **Generate**

Esto descargará un archivo `.zip` con el proyecto.

---

# **Paso 2: Importar el proyecto en el IDE**

1. Descomprimir el archivo descargado.
2. Copiar la carpeta del proyecto dentro del **workspace** del IDE.
3. Abrir **Eclipse**.

Crear un proyecto nuevo:

