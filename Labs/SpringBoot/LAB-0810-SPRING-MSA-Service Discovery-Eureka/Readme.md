# 🟢 Laboratorio Spring Boot: Service Discovery con Eureka

## 🎯 En este laboratorio aprenderás…

* Qué es Eureka y cómo se usa en Spring Boot
* Crear un **Eureka Server** para registrar microservicios
* Registrar microservicios en Eureka
* Descubrir servicios desde otros microservicios
* Configurar puertos distintos para evitar conflictos

---

## 🟠 Crear Eureka Server

1. Ir a [https://start.spring.io/](https://start.spring.io/)
2. Configurar:

   * Project: **Maven**
   * Language: **Java**
   * Spring Boot: **la última estable**
   * Group: `com.example`
   * Artifact: `eurekaserver`
3. Dependencias: **Eureka Server** (Spring Cloud Netflix Eureka Server)
4. Click en **Generate** → descargar el zip y descomprimir
5. Abrir en Eclipse (Eclipse 1)


### 🔹 Activar Eureka Server y Configurar Eureka Server

* Agregar anotacion @EnableEurekaServer al Archivo: `EurekaserverApplication.java`

```java
package com.example.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaserverApplication.class, args);
    }
}
```

### 🔹 Configurar propiedades

Archivo: `src/main/resources/application.properties`

```properties
spring.application.name=eureka-server
server.port=8761
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
```

### 🔹 Ejecutar Eureka Server

1. Click derecho → Run As → Spring Boot App
2. Abrir navegador: [http://localhost:8761/](http://localhost:8761/)
3. Deberías ver la **interfaz de Eureka**, con mensaje “**Instances currently registered with Eureka**: 0”

---


## Paso 2: Crear Microservicio A 

1. Ir a [https://start.spring.io/](https://start.spring.io/)
2. Configurar:

   * Group: `com.example`
   * Artifact: `servicio-a`
3. Dependencias: **Spring Web**, **Eureka Discovery Client**
4. Click en **Generate** → descargar zip y descomprimir
5. Abrir en Eclipse (Eclipse 2 o nueva ventana)

### 🔹 Configurar Conexion a Eureka Server

* En el application.properties poner

```properties
server.port=8081
spring.application.name=servicio-a
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```

> Para desactivarlo poner  eureka.client.enabled=false

### Configurar request HTTPPAra que usen Eureka

* Para que los request HTTP usen Eureka debemos usar la anotacion @LoadBalanced en una configuracion
* Archivo AppConfig.java
* Cadda vez que usamos servicio-a como url lo cambiara por la url correspondiente que le pase Eureka de forma transparente
* Esto tambien se puede hacer con WebClient y con Feign

``` java
@Configuration
public class AppConfig {
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
}

```  

### Crear endpoints de prueba

* Vamos a crear dos endopoints, uno que llama al servicio A directamente y uno que se llama a si mismo indirectamtente con eureka
* Archivo: `DatosController.java`

```java
@RestController
public class DatosController {

	@Autowired
	RestTemplate restTemplate;
	
    @GetMapping("/datos")
    public String listarPedidos() {
        return "Datos desde Servicio A";
    }

    @GetMapping("/datos-ind")
    public String listarPedidosIndirectamente() {
    	
        return "Indirecto " + restTemplate.getForObject("http://servicio-a/datos", String.class);    	
    }
 
    
}
```

### 🔹 Ejecutar Microservicio A

* Run As → Spring Boot App
* Volver a Eureka Server → deberías ver **servicio-a registrado** con puerto 8081

### 🔹 Probar descubrimiento

* Probar Urls
  * http://localhost:8080/datos
  * http://localhost:8080/datos-ind

---

## 🎯 Al final de este laboratorio lograrás…

* Crear un **Eureka Server** y entender su rol
* Configurar microservicios para **registrarse automáticamente**
* Visualizar servicios registrados en la interfaz de Eureka
* Consumir un servicio desde otro microservicio usando **información del descubrimiento**
* Entender cómo centralizar la información de los servicios para arquitecturas de microservicios

