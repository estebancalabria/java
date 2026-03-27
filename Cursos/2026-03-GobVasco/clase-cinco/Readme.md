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

* Hay coniguraciones para solo dejar hacer gets

```
springdoc.swagger-ui.supported-submit-methods=get
```

# Descubrimento de Servicios

# Api Gateway

# Monitoreo
