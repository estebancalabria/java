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

### Feigns (Usando Intefaces) (Clean Code)

* Fegin (Libreria externa)
* HTTPInterace (Spring 6)
	* No la use

* Agregar en el POM

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

* En la clase principal agregar @EnableFeignClients

```java
package org.gobvasco.cursomsa.clasedos.servicioA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableFeignClients
public class ServicioAApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioAApplication.class, args);
	}
	

}

```

* Crearmos la interfaz del servicio al que queremos llamar


```java
package org.gobvasco.cursomsa.clasedos.servicioA.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="servicio-b", url="http://localhost:8080/")
public interface ServiceBInvocation {
	
	@GetMapping("/serviceb")
	String getMensaje();

}

```

* Usamos la interfaz

```java
package org.gobvasco.cursomsa.clasedos.servicioA.controllers;

import org.gobvasco.cursomsa.clasedos.servicioA.external.ServiceBInvocation;
import org.gobvasco.cursomsa.clasedos.servicioA.services.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MainController {
	
	private final RestTemplate http;
	
	@Value("${app.mensaje}")
	private String mensaje;
	
	@Autowired
	private AsyncService service;
	
	private ServiceBInvocation externalService;
		
	public MainController(RestTemplate http, ServiceBInvocation external) {
		this.http = http;
		this.externalService = external;
	}
	
	@GetMapping("/servicea")
	public String heatbeat() {
		//return "Service A is OK";
		return this.mensaje;
	}
	
	@GetMapping("/sync")
	public String syncComminication() {
		String resultadoAnidado = this.http.getForObject(
				"http://localhost:8080/serviceb", 
				String.class);
				
		
		return "La llamada al servicio anidado es " + resultadoAnidado;
		
	}
	
	@GetMapping("/sync-feign")
	public String syncComminicationFerign() {
		/*String resultadoAnidado = this.http.getForObject(
				"http://localhost:8080/serviceb", 
				String.class);*/
				
		
		return "La llamada al servicio anidado es " + this.externalService.getMensaje();
		
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

* Ejemplo de configuracion del comportamiento del async (GRACIAS OIER)

```java
@Configuration
@EnableAsync
public class AsyncConfig {
  //  //
  private int corePoolSize = 5;
  private int maxPoolSize = 10;
  private int queueCapacity = 500;
 
  @Bean(name = "asyncExecutor")
  public Executor getAsyncExecutor() {
    System.out.println("INICIO Executor");
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(corePoolSize);
    executor.setMaxPoolSize(maxPoolSize);
    executor.setQueueCapacity(queueCapacity);
    executor.setThreadNamePrefix(Constantes.APLICACION.concat("-"));
    executor.initialize();
    return executor;
  } 
}
 
 
generas una clase de configuración no para toda la app y configuras los hilos y de mas
 
y el método asíncrono pues así en el service que toca
 
@Async("asyncExecutor")
  @Override
  public void generarNotificacionAsincrono
```

## Comunicacion Asincronica con un webHook

---
---

# Configuracion (Config Server)

## Config Server

* En el Spring Initalizer Incluir

> ConfigServer

* Agregarle la anotacion @EnableConfigServer a la clase Principal

```java
package org.gobvasco.cursomsa.clasedos.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigserverApplication.class, args);
	}

}
```

* El application.propeties me queda asi

```yml
spring.application.name=configserver
server.port=8888
#spring.cloud.config.server.git.uri=http://github.com/estebancalabria/config

spring.profiles.active=native
spring.cloud.config.server.native.search-locations=file:///C:/Cursos/Java//Cursos/2026-03-GobVasco/workspace/config-files

```

> En este ejemplo configuramos el config server para que lo lea del filesystem, pero en la practica las configuraciones se suben a un repo de git

---
---

## Config Client

* El application.yaml me queda asi (no bootstrap.yml)

```yml
spring:
  application:
    name: config-client-demo
    
  config:
    import: configserver:http://localhost:8888
```

* Al ejecutarnos nos damos cuenta si funciono cuando lo levanta del puerto 8082 que definimos en el confiserver

* Para estar mas seguros lo probamos en un controlador

```java
package org.gobvasco.cursomsa.clasedos.configclient.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientDemoController {
	
	@Value("${app.mensaje}")
	private String mensaje;

	@GetMapping("/demo")
	public String demo() {
		return this.mensaje;
	}
}
```	

# Para la proximas...

* Miercoles 
	* Validaciones de Beans (SpringBoot)
	*  JPA (Persistencia)
		 *  Migraciones
* Jueves
	* Spring Security
	  * Service Discovery
* Viernes
	* APi Gateway (Eureka)
 	* Logging (Actuator) 
	* Examen
