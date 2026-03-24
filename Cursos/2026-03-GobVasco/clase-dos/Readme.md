# Arquitectura de Microservicios

# Clase Dos - 24 de Marzo del 2026

# Repaso

* Arquitectura
    * Arquitectura Interna de un Microservicio
        * Controladores
        * Servicio
        * Persistencia
        * DTO
        * Entities
    * Arquitectura de Infraestructura
        * Api Gateway (Servicio de los servicios que se encarga de incumbencias tranversales)
            * Logging
            * Autenticacion
            * Control de Cuotas / Lites de Uso
        * Servidor de Configuracion
        * Servidor de Descubrimiento
            * Eureka en Spring Boot
        * Transaccionalidad
            * Patron SAGA
            * 1 base de datos global o 1 servicio
  * SpringBoot
    * Anotaciones

# Arquitectura MSA (Micro Service Archivecture)

*  Lo IDEAL pero a la ves utopico es tener a 1 base de datos por microoservicio
  *  Pero en la practica es comun tener 1 base datos para todos
  *  1 base de datos para 1 conjunto de microoservicios
*  NUNCA PONER LOS ENDPOINTS HARDCODEADOS EN LA APP
  *  En el application.properties (o el yaml)
  *  El config server (mejor)
  *  En el eureka  
* Los Servicios DEBEN responder en forma inmediata, si un servicio va a tardar (mas de 15 segundos por ejemplos) debemos implementar una llamada async


# Inyeccion de dependencias por Codigo

* Anotaciones
  * @Configuration
  * @Bean

```java
```

# Cominicacion entre Micro Servicios

## Comunicacion Sincronica

### RestTemplate (el clasico)

* Primero vamos a configurar la Inyeccion de Dependencias

```java
package org.gobvasco.cursomsa.clasedos.servicioA;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
```

* Uso de RestClient

```java
package org.gobvasco.cursomsa.clasedos.servicioA.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MainController {
	
	private final RestTemplate http;
		
	public MainController(RestTemplate http) {
		this.http = http;
	}
	
	@GetMapping("/servicea")
	public String heatbeat() {
		return "Service A is OK";
	}
	
	@GetMapping("/sync")
	public String syncComminication() {
		String resultadoAnidado = this.http.getForObject(
				"http://localhost:8080/serviceb", 
				String.class);
				
		
		return "La llamada al servicio anidado es " + resultadoAnidado;
		
	}

}

```

### WebClient (mas moderno)

* Aca lo instacioamos directo mediante su interfaz fluent

```java
package org.gobvasco.cursomsa.clasedos.servicioB.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class ServiceBController {

		
	@GetMapping("/serviceb")
	public String heatbeat() {
		return "Service B is OK";
	}
	
	@GetMapping("/sync")
	public String syncCommunication() {
		String resultadoLlamada = WebClient.create("http://localhost:8081/")
			.get()
			.uri("servicea")
			.retrieve()
			.bodyToMono(String.class)
			.block();
		 
		return "La llamada anidada es " + resultadoLlamada; 
	}

}

```

### Usando Intefaces


* La vamos a ver si hay tiempo

## Comunicacion Asincronica con dos Processos

* Habilitar llamada asincronicas en el Servicio prinicpal @EnableAsync (el que es asincrono)

```java
package org.gobvasco.cursomsa.clasedos.servicioA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ServicioAApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioAApplication.class, args);
	}
	

}
```

* Los metodos que tardan los voy a decorar con 	@Async. Servicio de Ejemplo

```java
package org.gobvasco.cursomsa.clasedos.servicioA.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

	private String estado = "Proceso Largo no Inciado";
	
	@Async
	public void iniciarProcesoLargo() {
		this.estado = "Proceso Largo Inciado";
		try {
			Thread.sleep(10000);
			this.estado = "Proceso Largo Finalizado";
		} catch (InterruptedException e) {
		   //...
		}
	}
	
	public String getEstadoProceso() {
		return this.estado;
	}
	
}
```

* Lo ejecuto desde el controlador

```java
package org.gobvasco.cursomsa.clasedos.servicioA.controllers;

import org.gobvasco.cursomsa.clasedos.servicioA.services.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MainController {
	
	private final RestTemplate http;
	
	@Autowired
	private AsyncService service;
		
	public MainController(RestTemplate http) {
		this.http = http;
	}
	
	@GetMapping("/servicea")
	public String heatbeat() {
		return "Service A is OK";
	}
	
	@GetMapping("/sync")
	public String syncComminication() {
		String resultadoAnidado = this.http.getForObject(
				"http://localhost:8080/serviceb", 
				String.class);
				
		
		return "La llamada al servicio anidado es " + resultadoAnidado;
		
	}
	
	@GetMapping("/start-async")
	public String startAsyncProcess() {
		this.service.iniciarProcesoLargo();
		return "Proceso Largo Inciado";
	}
	
	@GetMapping("/async-status")
	public String estadoProceso() {
		return this.service.getEstadoProceso();		
	}

}

```

---

## Comunicacion Asincronica con un webHook

# Validaciones de Beans (SpringBoot)

# Configuracion (Config Server)

# JPa...
