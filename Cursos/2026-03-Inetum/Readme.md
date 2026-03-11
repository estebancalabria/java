# Curso: Spring Boot y Arquitectura de Microservicios

**Duración:** 20 horas

## Descripción del curso
Este curso de **Spring Boot y Microservicios** está diseñado para desarrolladores que ya trabajan con Spring MVC y desean evolucionar hacia el desarrollo moderno basado en **Spring Boot** y **arquitecturas de microservicios**.  

Los participantes aprenderán:
- Qué cambia al pasar de Spring MVC tradicional a Spring Boot.
- Cómo acelerar el desarrollo mediante **auto-configuración** y **starters**.
- Cómo diseñar, construir, securizar y monitorizar **APIs REST** preparadas para entornos distribuidos.  

El enfoque es eminentemente práctico: se desarrollarán varios microservicios funcionales conectados entre sí, incluyendo persistencia, seguridad, monitorización y buenas prácticas arquitectónicas.

---

## Objetivos del curso

### Objetivo general
Capacitar a desarrolladores con experiencia en Spring MVC para diseñar e implementar aplicaciones modernas con Spring Boot bajo un enfoque de microservicios, incluyendo seguridad, persistencia y monitorización.

### Objetivos específicos
- Comprender las diferencias entre **Spring MVC tradicional** y **Spring Boot**.  
- Crear y estructurar proyectos con Spring Boot de forma eficiente.  
- Diseñar e implementar **APIs RESTful** siguiendo buenas prácticas.  
- Gestionar **validaciones, excepciones y DTOs** correctamente.  
- Integrar bases de datos y gestionar transacciones.  
- Implementar seguridad con **Spring Security** y **OAuth2**.  
- Monitorizar aplicaciones mediante **Actuator** y logging estructurado.  
- Entender los fundamentos y patrones clave de **arquitectura de microservicios**.

---

## Público objetivo
- Desarrolladores Java con experiencia en Spring MVC.  
- Equipos técnicos que necesitan modernizar aplicaciones monolíticas.  
- Programadores backend interesados en arquitecturas distribuidas.  
- Profesionales que busquen adoptar buenas prácticas en **APIs REST** y microservicios.

---

## Requisitos previos
- Experiencia práctica en Java.  
- Conocimientos sólidos de Spring MVC.  
- Conocimientos básicos de **REST** y **HTTP**.  
- Experiencia básica con bases de datos relacionales y SQL.

---

## Temario

### MÓDULO 1. Fundamentos de Spring Boot
- Evolución del ecosistema Spring  
- De Spring MVC a Spring Boot: qué cambia y qué se mantiene  
- Auto-configuración y convenciones  
- Starters y dependencias  
- Spring Initializr  
- Estructura de un proyecto Spring Boot  
- `application.properties` y `application.yml`  
- Perfiles y configuración por entornos  

**Práctica:** Migración de un proyecto Spring MVC básico a Spring Boot.

---

### MÓDULO 2. Desarrollo de APIs RESTful
- Principios de diseño REST  
- Métodos HTTP y códigos de estado  
- Controladores REST con `@RestController`  
- Diseño de endpoints y versionado  
- Uso de DTOs  
- Validación con Bean Validation  
- Manejo global de excepciones (`@ControllerAdvice`)  
- Documentación de APIs (OpenAPI / Swagger)  

**Práctica:** Desarrollo de una API REST completa con validaciones y gestión de errores.

---

### MÓDULO 3. Persistencia y Gestión de Datos
- Configuración de conexión a base de datos  
- Spring Data JPA  
- Entidades y repositorios  
- Consultas derivadas y personalizadas  
- Relaciones entre entidades  
- Manejo de transacciones (`@Transactional`)  
- Gestión de migraciones (Flyway o Liquibase)  
- Buenas prácticas de acceso a datos  

**Práctica:** Integración de la API con base de datos relacional y operaciones CRUD completas.

---

### MÓDULO 4. Seguridad en Spring Boot
- Fundamentos de seguridad en aplicaciones web  
- Introducción a Spring Security  
- Configuración básica de seguridad  
- Autenticación y autorización  
- Seguridad basada en roles  
- JWT (conceptos e implementación básica)  
- Introducción a OAuth2  
- Protección de APIs REST  

**Práctica:** Implementación de autenticación y autorización en la API desarrollada.

---

### MÓDULO 5. Gestión y Monitorización
- Introducción a Spring Boot Actuator  
- Endpoints de monitorización  
- Métricas y health checks  
- Configuración de logs  
- Logging estructurado  
- Gestión de perfiles en producción  
- Buenas prácticas de observabilidad  

**Práctica:** Configuración de monitorización y análisis de métricas de la aplicación.

---

### MÓDULO 6. Arquitectura de Microservicios
- Evolución de arquitecturas: monolito vs microservicios  
- Ventajas y desafíos de microservicios  
- Arquitecturas híbridas  
- Principios de diseño de microservicios  
- Patrones clave:  
  - API Gateway  
  - Service Discovery  
  - Circuit Breaker  
  - Configuración centralizada  
  - Comunicación síncrona vs asíncrona  
- Introducción a Spring Cloud (visión general)  
- Comunicación entre microservicios (`RestTemplate` / `WebClient`)  
- Gestión de errores en sistemas distribuidos  

**Práctica:** Separación de la aplicación en dos microservicios con comunicación entre ellos.

---

## Proyecto Final Integrador
Desarrollo de una arquitectura básica de microservicios que incluya:  
- Al menos dos servicios independientes  
- Persistencia en base de datos  
- Seguridad con autenticación  
- Monitorización con Actuator  
- Comunicación entre servicios  
- Aplicación de patrones básicos de microservicios

---
