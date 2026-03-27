# Microservicios - Gorbienrno Vasco

# Clase Cinco - 27 de Marzo del 2027

# Repaso

* Spring Boot
  * Filtros
* Spring Security
  * HttpBasico
  * JWT
  * OAuth
    * Microservicio ---> Microservicio : CLIENT_CREDENTIALS
    * Usuario Final -----> Web APP(clientID/ClientSecret) ------> Microservicio ---- > Auth-server : AUTHORIZATION_CODE

# Buenas Practicas

* Los controlles NO tienen logica de negocio
* MUY BUENO OSCAR
 	 * Cual el tamanio de un microservicios
	 * Compromiso Practicidad, velocidad y la arquitectura de refencia de mini-microservicios
		  * La arquitectura de microservicios teoric suele "despreciar" las velocidades de coneccion en pos del mantenimiento (deplagar un servicio y que cambie)
	 * No es lo mismo pensar una solucion desde cero que trabajar con una bases ya legada
		  * Posibilidad de Hacer un JOIN (Lo resuelvo en el mismo microservicio)
	      * Tener dos microservicios distintos (Uno trae una parte de la tabla y el otro trae de la otra) 
* Pensar una arquitectura Microservicios
	* Antes : Servicio REST (Generar Factura) (MAs hibrido)
		 * Este Servicio Graba en la base de datos y ademas tiene un monton de logica brutal  (Controllers+servicio+Persitencia+todo)
	     * Si vemos el compilado pesa 500MB
   * Despues: Paso a tener Varios Microservicios (Mas Jedi de Microservicios)
	   * Generar Factura
       * Calcular Impuestos (Otro Endpoint)
		       * Si el dia de maniana cambia el calculo de los impuestos solamente despliegando este servicio se actualiza todo 
       * Validar Cliente (Otro Endpoint)
       * ....
* Es comun encontrar en arquitecturas microservicios a nivel base de datos
	* (id, ...., url_cliente <<< Donde dice url_cliente  servicio-clientes/clientes/1
		 * servicio_clientes es el nombre del serivcio tal cual lo cargas en el Eureka

*  (ciudadano) --> (web JSP) --> (servicio)
*  (ciudadano) --> (web REACT) --> (Micro-servicio /BFF [Backend for Frontend] ) --> (Micro-servicio [De Negocio])  //Codicionado al caso de si conviene cada caso dado que tenmos una estructura legacy y y a una base datos

	*  (Micro-servicio /BFF [Backend for Frontend] )
		 *  Este microservicio esta pensado en el fronted especifico que lo va a consumir
	     *  Muchas veces los desarrolladores frontend terminan en su codigo convirtiendo los datos que ingresa el usuario en una estructura JSON que el servicio de negocio espera (y es bastante codigo/ no es trivial muchas veces)
         * En lugar de hacer esa adaparacion en el frontend pensar la posibilidad de tener un serivcio que haga toda esa adaptacion, librar de eso al frontend y que los parametros de ese microservicio BFF sean "Comodos" para el programador del frontend.
         * Otra... Mas importante todavia..(bien ahi diste en el clavo OIER)... Que el fontend tenga 20 llamadas a microservicios par a hacer algo y se reemplaze por una sola llamada al BFF y que ese sea el que lo redistribuya  

# Documentacion

* Cremos un proyecto con la libreria "SpringDoc OpenAPI"
* Se accede a la documentacion en

```
http://localhost:8080/swagger-ui/index.htm
```

* Ejemplo de Clase documentada

```
package org.gobvasco.cursomsa.documentation.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name="DemoController", description="Este es un controlador de prueba")
public class DemoController {

	@GetMapping("/docu")
	@Operation(summary="Una operacion de prueba para mostrar la docummentacion")
	public String demodoc() {
		
		return "Esta es una demo para mostrar la swagger";
	}
	
	@GetMapping("/oculto")
	@Hidden
	public String oculto() {
		
		return "Esta es una demo para mostrar la swagger, pero esto esta oculto";
	}	
}
```

* Me imagino que se debe poder generar automaticamente los controladores a patir de...

```
http://localhost:8080/v3/api-docs
```

> el online lo quitaron, parece que ahra en un ejecutable y parece de pago ademas
https://github.com/swagger-api/swagger-codegen
https://swagger.io/tools/swagger-codegen/
aqui lo tienen y para lo de openAPI que decia Jose Manuel:
https://learn.openapis.org/
es la especificación para generar codigo a partir del yml para crear los controllers, genera interfaces para los controllers (Gracias JON)

* Hay coniguraciones para solo dejar hacer gets

```
springdoc.swagger-ui.supported-submit-methods=get
```

# Api Gateway

* Si bien para el ejemplo vamos a utilizar una api de SpringBoot
	* Puede ser util para agregar filtos al proyecto
* Tambien tenemos miles de soluciones para implementar gateways (con load balancer e incluso autenticacion)
	* https://nginx.org/
 * Crear en Spring Initializr
	  * Reactive Gateway
	  *  (No incluir Spring Web)
*  Este gateway usa programacion reactiva para ser mas eficiente (Http no bloqueante)
	*  https://www.rxjava.com/
* Este gateway generalmente esta instalado un servidor fontera que tiene salida a Internet y el resto de los servicios estan en el boundary de una red interna

* El application.yml me quedaria asi:
 
```yml
server:
  port: 9005 

spring:
  application:
    name: gateway
  cloud:
    gateway:
      server:
        webflux:
          routes:
            - id: documentation
              uri: http://localhost:8080
              predicates:
                - Path=/docu,/docu/** 
```

* En produccion voy a tener prefihos no asi (es una archivo bastante extenso que generalente va direcamente en el config server)

```yml
              predicates:
                - Path=/api/ciudadanos/**

             ...

              predicates:
                - Path=/api/tramites/** 

             ...

			  predicates:
                - Path=/api/reglamentaciones/** 

             ....

             predicates:
                - Path=/api/etc/** 

```

* Compruebo que da igual acceder a...

```
http://localhost:8080/docu
```

* Que a...

```
http://localhost:9005/docu
```

# Descubrimento de Servicios



# Monitoreo
