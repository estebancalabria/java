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
    * No olvidarse del @Transactional en los servicios si guardas mas de una tabla
* Anotacion de JPA 
	* @Entity
	* @Id
  	* @GeneratedValue(strategy = GenerationType.IDENTITY)
  	* @Table
  	* @Column
  	* @OneToOne
	* @JoinColumn
    * @Transactional 

* Interfaces y clases
	* JpaRepository<> 

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

* Agregamos la clase CancionController en controllers

```java
package org.gobvasco.cursomsa.clasetres.jpademo.controllers;

import java.util.List;

import org.gobvasco.cursomsa.clasetres.jpademo.entities.*;
import org.gobvasco.cursomsa.clasetres.jpademo.repositories.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class CancionController {

	//OJO: Mando un repository de una pero esta mal, deberia pasar por el service
	@Autowired
	private CancionRepository repo;
	
	@PostMapping("/api/v1/canciones")
	public Cancion crear(@RequestBody Cancion cancion) {
		//El servicio haria validaciones por codigo, logica de negocios, etc...
		return this.repo.save(cancion);
	}
	
	
	//Mejor api/cancion en singular se usa mucho
	@GetMapping("/api/v1/canciones")
	public List<Cancion> listar(){
		return this.repo.findAll();
	}
}
```

* Ejecutar y probar el endpoint

```
http://localhost:8080/api/v1/canciones
```

* Probar el endpoint de post con curl

```cmd
curl -X POST http://localhost:8080/api/v1/canciones -H "Content-Type: application/json" -d "{\"titulo\": \"Creci en los 80\", \"artista\":\"El reno Renardo\"}"
```

* Probar el endpoint de Get y el h2Console

* Fijarse en el H2Console que se haya generado la tabla

## Convenciones de nombres en los repositories

* Documentacion oficial
> https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

* Ejemplo de repo

```java
package org.gobvasco.cursomsa.clasetres.jpademo.repositories;

import java.util.List;

import org.gobvasco.cursomsa.clasetres.jpademo.entities.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CancionRepository extends JpaRepository<Cancion, Long> {

	//Convenciones de nombre
	List<Cancion> findByArtista(String artista);
	
	List<Cancion> findByTituloContaining(String titulo);
	
	@Query("Select c FROM Cancion c WHERE c.titulo LIKE '%?1%'")
	List<Cancion> buscarPorTituloConQuery(String titulo);
}
```

* Agregamos una accion al controller

```java
	@GetMapping("/api/v1/cancionestitulo")
	public List<Cancion> listarPorTitulo(@RequestParam String titulo){
		
		//return this.repo.findByTituloContaining(titulo);
		//O bien la otra opcion....
		return this.repo.buscarPorTituloConQuery(titulo);
	}
```

* Probarlo

```
http://localhost:8080/api/v1/cancionestitulo?titulo=80
```

## Anotaciones del Modelo (Mapeos)

* Ahora puedo anotar el modelo y especificar los Mapeos

```java
package org.gobvasco.cursomsa.clasetres.jpademo.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Song")
public class Cancion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="title", nullable=false)
	private String titulo;
	
	@Column(name="artist", nullable=false)
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

* Esto se utiliza cuando hago Database First y tengo la tabla mapeada

## Manejo de Transacciones y mapeos extendidos

* Agregamos el DTO (CancionDTO) en el paquete dto

```java
package org.gobvasco.cursomsa.clasetres.jpademo.dto;

public class CancionDTO {

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

* Creamos ahora la clase Artista

```java
package org.gobvasco.cursomsa.clasetres.jpademo.entities;

import jakarta.persistence.*;

@Entity
public class Artista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}

```

* Modifico la clase Cancion

```java
package org.gobvasco.cursomsa.clasetres.jpademo.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Song")
public class Cancion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="title", nullable=false)
	private String titulo;
	
	private Artista artista;
	
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
	public Artista getArtista() {
		return artista;
	}
	public void setArtista(Artista artista) {
		this.artista = artista;
	}
}

```

* Agrego el repositorio de Artistas en el paquete de repositories

```java
package org.gobvasco.cursomsa.clasetres.jpademo.repositories;

import org.gobvasco.cursomsa.clasetres.jpademo.entities.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository  extends JpaRepository<Artista, Long> {

}

```

* Vamos a Agregar el CancionService dentro del paquete de services

```java
package org.gobvasco.cursomsa.clasetres.jpademo.services;

import java.util.ArrayList;
import java.util.List;

import org.gobvasco.cursomsa.clasetres.jpademo.dto.CancionDTO;
import org.gobvasco.cursomsa.clasetres.jpademo.entities.*;
import org.gobvasco.cursomsa.clasetres.jpademo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CancionService {
	
	@Autowired
	private CancionRepository repoCancion;
	
	@Autowired
	private ArtistaRepository repoArtista;
	
	public CancionDTO crearConError(CancionDTO cancion) {
		//En este ejemplo va a guardar el artista pero no va a guardar la cancion
		
		Artista artista = new Artista();
		artista.setNombre(cancion.getArtista());
		
		this.repoArtista.save(artista);
		
		//Voy a guardar la cancion
		if (true) {
			throw new RuntimeException("Error simulado");
		}
		
		Cancion cancionNueva = new Cancion();
		cancionNueva.setArtista(artista);
		cancionNueva.setTitulo(cancion.getTitulo());
		
		this.repoCancion.save(cancionNueva);
		
		cancion.setId(cancionNueva.getId());
		
		return cancion;
	}
	
	@Transactional
	public CancionDTO crearConErrorYTransaccion(CancionDTO cancion) {
		//En este ejemplo como da error hace ROOLBACK de la transaccion y no guarda el artista
		
		Artista artista = new Artista();
		artista.setNombre(cancion.getArtista());
		
		this.repoArtista.save(artista);
		
		//Voy a guardar la cancion
		if (true) {
			throw new RuntimeException("Error simulado");
		}
		
		Cancion cancionNueva = new Cancion();
		cancionNueva.setArtista(artista);
		cancionNueva.setTitulo(cancion.getTitulo());
		
		this.repoCancion.save(cancionNueva);
		
		cancion.setId(cancionNueva.getId());
		
		return cancion;
	}
	
	@Transactional
	public CancionDTO crearSinError(CancionDTO cancion) {
		//En este ejemplo como da error hace ROOLBACK de la transaccion y no guarda el artista
		
		Artista artista = new Artista();
		artista.setNombre(cancion.getArtista());
		
		this.repoArtista.save(artista);
		
		Cancion cancionNueva = new Cancion();
		cancionNueva.setArtista(artista);
		cancionNueva.setTitulo(cancion.getTitulo());
		
		this.repoCancion.save(cancionNueva);
		
		cancion.setId(cancionNueva.getId());
		
		return cancion;
	}


	public List<CancionDTO> listar(){
		List<Cancion> canciones = this.repoCancion.findAll();
		
		//Se puede usar MapStruct para noh acer el mapeo a mano
		List<CancionDTO> result = new ArrayList<>();
		
		for (Cancion c : canciones) {
			CancionDTO nuevo = new CancionDTO();
			nuevo.setId(c.getId());
			nuevo.setTitulo(c.getTitulo());
			
			if (c.getArtista() != null) {
				nuevo.setArtista(c.getArtista().getNombre());
			}
			
			 result.add(nuevo);
		}
		
		return result;		
	}
}

```

* Modificamos ahora el controller

```java
package org.gobvasco.cursomsa.clasetres.jpademo.controllers;

import java.util.List;

import org.gobvasco.cursomsa.clasetres.jpademo.dto.CancionDTO;
import org.gobvasco.cursomsa.clasetres.jpademo.entities.*;
import org.gobvasco.cursomsa.clasetres.jpademo.repositories.CancionRepository;
import org.gobvasco.cursomsa.clasetres.jpademo.services.CancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class CancionController {

	//OJO: Mando un repository de una pero esta mal, deberia pasar por el service
	@Autowired
	private CancionService service;
	
	@PostMapping("/api/v1/canciones")
	public CancionDTO crearOk(@RequestBody CancionDTO cancion) {
		//El servicio haria validaciones por codigo, logica de negocios, etc...
		return this.service.crearSinError(cancion);
	}
	
	@PostMapping("/api/v1/canciones-sin-transaccion")
	public CancionDTO crearSinTransaccion(@RequestBody CancionDTO cancion) {
		//El servicio haria validaciones por codigo, logica de negocios, etc...
		return this.service.crearConError(cancion);
	}
	
	@PostMapping("/api/v1/canciones-con-transaccion")
	public CancionDTO crearConTransaccion(@RequestBody CancionDTO cancion) {
		//El servicio haria validaciones por codigo, logica de negocios, etc...
		return this.service.crearConErrorYTransaccion(cancion);
	}
	
	//Mejor api/cancion en singular se usa mucho
	@GetMapping("/api/v1/canciones")
	public List<CancionDTO> listar(){
		return this.service.listar();
	}
	
	//En la practica me gustraria tener un solo encpoint /canciones pero bueno...
	//Habria que agregarle el metodo al servicio, pero por lo pronto no me interesa
	/*@GetMapping("/api/v1/cancionestitulo")
	public List<Cancion> listarPorTitulo(@RequestParam String titulo){
		
		//return this.repo.findByTituloContaining(titulo);
		//O bien la otra opcion....
		//return this.repo.buscarPorTituloConQuery(titulo);		
	}*/
}

```

* Probamos el caso donde Sale todo OK

* Hacemos primero Curl
```
curl -X POST http://localhost:8080/api/v1/canciones -H "Content-Type: application/json" -d "{\"titulo\": \"Creci en los 80\", \"artista\":\"El reno Renardo\"}"
{"artista":"El reno Renardo","id":1,"titulo":"Creci en los 80"}
```

* Lo verficamos en el navegador
```
http://localhost:8080/api/v1/canciones
```

* Lo verficamos en la consola del la base de datos h2

```
http://localhost:8080/h2-console/
```

* Y ejecutamos SQL

```sql
SELECT * from Song;
SELECT * from Artista;
```

* Verificar pero sin transaccion

```
curl -X POST http://localhost:8080/api/v1/canciones-sin-transaccion -H "Content-Type: application/json" -d "{\"titulo\": \"No te olvides el transactional\", \"artista\":\"Springboot Records\"}"
```

* Verifico las tablas

```sql
SELECT * from Song;
SELECT * from Artista;
```

> NOTA: Verificar como aparece el registro en la tabla de artistas pero NO APARECE en la tabla de canciones

* Verificar CON transaccion

```
curl -X POST http://localhost:8080/api/v1/canciones-con-transaccion -H "Content-Type: application/json" -d "{\"titulo\": \"Eye of the Tiger\", \"artista\":\"Survivor\"}"
```

> Ahora tira error pero  no deberia guardar nada en la base de datos por el @Transactional



# Validaciones de Beans

* Agregar la dependecia de Validation al POM (en caso de no haberla incluido en el Spring Intializr)

```xml
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

* Este paquete me agrega un monton de atributos de validacion en el paquete jakarta.validation.constraints
	 * @NotBlank
	 * @Min
     * @Max
     * @Pattern <<< Muy utilizaco para validaciones personalizadas con expresiones regulares
     * @NotNull
* Anotaciones para el controlador
	* @Valid
* Si ninguna de las anotaciones sirve (hay un monton) esta la posibilidad de programar mis propias validaciones

* Ahora el DTO de canciones modificado queda

```java
package org.gobvasco.cursomsa.clasetres.jpademo.dto;

import jakarta.validation.constraints.*;

public class CancionDTO {

	private Long id;
	
	@NotBlank(message="El titulo de la cancion no puede quedar vacio")
	private String titulo;
	
	@Min(value=1, message="La puntuacion no puede ser menor a 1")
	@Max(value=10, message="La puntuacion no puede ser mayor a 1")
	@NotNull
	private int puntuacion;
	
	
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
	public int getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	
}
```

* Decirle al controlador que verifique las validaciones con el @Valid

```java
package org.gobvasco.cursomsa.clasetres.jpademo.controllers;

import java.util.List;

import org.gobvasco.cursomsa.clasetres.jpademo.dto.CancionDTO;
import org.gobvasco.cursomsa.clasetres.jpademo.entities.*;
import org.gobvasco.cursomsa.clasetres.jpademo.repositories.CancionRepository;
import org.gobvasco.cursomsa.clasetres.jpademo.services.CancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;


@RestController
public class CancionController {

	//OJO: Mando un repository de una pero esta mal, deberia pasar por el service
	@Autowired
	private CancionService service;
	
	@PostMapping("/api/v1/canciones")
	public CancionDTO crearOk(@Valid @RequestBody CancionDTO cancion) {
		//El servicio haria validaciones por codigo, logica de negocios, etc...
		return this.service.crearSinError(cancion);
	}
	
	@PostMapping("/api/v1/canciones-sin-transaccion")
	public CancionDTO crearSinTransaccion(@Valid @RequestBody CancionDTO cancion) {
		//El servicio haria validaciones por codigo, logica de negocios, etc...
		return this.service.crearConError(cancion);
	}
	
	@PostMapping("/api/v1/canciones-con-transaccion")
	public CancionDTO crearConTransaccion(@Valid @RequestBody CancionDTO cancion) {
		//El servicio haria validaciones por codigo, logica de negocios, etc...
		return this.service.crearConErrorYTransaccion(cancion);
	}
	
	//Mejor api/cancion en singular se usa mucho
	@GetMapping("/api/v1/canciones")
	public List<CancionDTO> listar(){
		return this.service.listar();
	}
	
	//En la practica me gustraria tener un solo encpoint /canciones pero bueno...
	//Habria que agregarle el metodo al servicio, pero por lo pronto no me interesa
	/*@GetMapping("/api/v1/cancionestitulo")
	public List<Cancion> listarPorTitulo(@RequestParam String titulo){
		
		//return this.repo.findByTituloContaining(titulo);
		//O bien la otra opcion....
		//return this.repo.buscarPorTituloConQuery(titulo);		
	}*/
}

```

* Probarlo con algunos casos

```
curl -X POST http://localhost:8080/api/v1/canciones-con-transaccion -H "Content-Type: application/json" -d "{\"titulo\": \"Eye of the Tiger\", \"artista\":\"Survivor\", \"puntuacion\":-1}"
```

```
curl -X POST http://localhost:8080/api/v1/canciones-con-transaccion -H "Content-Type: application/json" -d "{\"titulo\": \"Eye of the Tiger\", \"artista\":\"Survivor\", \"puntuacion\":1000}"
```

```
curl -X POST http://localhost:8080/api/v1/canciones-con-transaccion -H "Content-Type: application/json" -d "{\"titulo\": \"\", \"artista\":\"Survivor\", \"puntuacion\":6}"
```

```
curl -X POST http://localhost:8080/api/v1/canciones-con-transaccion -H "Content-Type: application/json" -d "{\"titulo\": \"\", \"artista\":\"Survivor\", \"puntuacion\":11}"
```

## Manejo de errores personalizados

* Nos metemos en el mundo de Aspect Oriented Programming

* Anotaciones que vamos a utilizar
	* @RestControllerAdvice
	 * @ExceptionHandler(

* El mensaje de erro en las validaciones no es amigable para el usuario final y ademas es una vulnerabilidad de seguridad

* Poner un try catch para personalizar el mensaje en cada metodo de nuestros controladores termina siendo muy repetitivo

* Agrego una clase para el manejo global de GlobalExcetionHandler en el paquete Exceptions
```java
package org.gobvasco.cursomsa.clasetres.jpademo.exceptions;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExcetionHandler {
   
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> manejarErrores(MethodArgumentNotValidException ex){
		Map<String, String> errores = new HashMap<>();
		
		errores.put("error", "Se ha producido un error de Validacion");
		
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errores.put(error.getField(), error.getDefaultMessage());
		}
		return ResponseEntity.badRequest().body(errores);		
				
	}
}
```

---

# Arquitectura de Microservicios (MSA)

* Buenas Practicas
  * Algunos proyectos solo tienen paquete/capa de dto
  * Recomendacion tener dos paquetes
    * entidades : mas de negocio, mas similar al la estructura de la base de datos
    * dto : mas para comunicacion entre capas (controladores-servicios), mas pensando en lo que devuelven los controllers (controllers)
    * Para mapear entre dto y entidades algunos utilizan librerias como MapStruct
        * https://mapstruct.org/
* Seguridad: No dejar los mensajes de error que vienen por defecto en produccion ya que dan informacion sobre el codigo subyacente



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

# Para la proxima

* Documentacion de APIS con Swagger  << Va obligado
* Custom Validators  				 << Lo podemos agregar a este proyecto, igual lo pueden ver usteds ya tienen el concepto
* Migraciones de Bases de Datos
* Ademar..
    * Seguridad <<<< Tema ppal clase viene
    * Eureka
	* Monitoring
	* Api Gateway

