# Arquitectura de Microservicios - Gobierno Vasco

# Clase Tres - 25 de Marzo del 2026

# Repaso

* Arqutiectura de Microservicios
  * Buenas practica
    * Tiempo de ejecucion de Microsoericios
* Comunicacion de Servicios
  * Asincrono
    * Estrategia de comunucacion asincora de servicios
  * Sincrono
    * RestTemplate
    * Webclient
    * Feign
    * HttpInterface
* Configuracion de Servicios
  * Config Server
  * application.properties vs application.yml vs Config Server
  * Subir configuracion repo de Github vs configuracion local
* SpringBoot
  * Configuracion por Codigo
    * @Configuration
    * @bean

# JPA

* Formas de Trabajo con JPA
    * Database First
    * Code First
      * Codificar los MAPEOS :
        * Antes con XML o MAPPERs ---> Ahora con DATA Annotations
* Buenas Practicas
  * IDEAL : Una base de datos no muy grande por Microservicios       <<
      * DESAFIO : Manejo de tracacciones de BD distribuidas
  * INTERMEDIO : Una base de datos cada X servicios relacionados
  * REAL EN LA PRACTICA : Una base de datos gigante para todos los Microservicios


## Creacion de CR(UD) con JPA

* Crear el proyecto en spring intializr con
    * Spring Web
    * JPA
    * H2
* Configuramos el application.properties

```
spring.application.name=jpademo

spring.datasource.url=jdbc:h2:mem:test
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update

spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

* Probar el acceso al h2 console

```
http://localhost:8080/h2-console
```

> ⚠️Cambiar la url para que coincida con el que pusimos en el proyecto

* Generar la clase Canciones en el paquete de entities

```java
package org.gobvasco.cursomsa.clasetres.jpademo.entities;

import jakarta.persistence.*;

@Entity
public class Cancion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titulo;
	private String artista;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getArtista() {
		return artista;
	}
	public void setArtista(String artista) {
		this.artista = artista;
	}	
}

```

* Crear el repositorio en el paquete de repositories

```java
package org.gobvasco.cursomsa.clasetres.jpademo.repositories;

import org.gobvasco.cursomsa.clasetres.jpademo.entities.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CancionRepository extends JpaRepository<Cancion, Long> {

}

```

# Validaciones de Beans

# Arquitectura de Microservicios (MSA)

* Buenas Practicas
  * Algunos proyectos solo tienen paquete/capa de dto
  * Recomendacion tener dos paquetes
    * entidades : mas de negocio, mas similar al la estructura de la base de datos
    * dto : mas para comunicacion entre capas (controladores-servicios), mas pensando en lo que devuelven los controllers (controllers)
    * Para mapear entre dto y entidades algunos utilizan librerias como MapStruct
        * https://mapstruct.org/

## Versionado de Microservicios

* Es una buena practiva versionar las api segun el endpoint ej:
	* "/api/v1/canciones"
 * En general este versionado puede quedar oculto por el API Gateway (El punto de entrada)
    * "/api/canciones" ----> "/api/v1/canciones"
    * "/api/canciones" ----> "/api/v2/canciones"
* Aunque para uso publico masivo se puede dejar tranquilamente (decision de arquitectura) la version sin problema
* Los cambios de versiones segun el enpoint es depende el caso (cambio de version no siempre refleja cambio de endpoint)
 	 * Agrego campos nuevos sin necesidad de cambiar de version de endpoint
	 * Cambio de estructura JSON ----> Cambia endpoint

# Documentacion de APIS con Swagger <<< Verlo Segurisimo

