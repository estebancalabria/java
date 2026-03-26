# Microservicios - Gobierno Vasco

# Clase Cuatro - 26 de Marzo del 2026

# Repaso

* JPA
  * Interfaces de Repositorios
    * Convenciones de Nomre / Sufijos
  * Anotaciones
    * @Entity
    * Interfaces de JPA
    * Mapeos
       * @Column
       * @Id
       * @GeneratedValue
       * @Table
       * @OneToOne
       * @OneToMany << Tambien esta
       * @JoinColumn
     *  Manejo de Transacciones
       *  @Transactional
     *  Consultas Personalizadas
       *  @Query
* Validaciones de Beans
  * Validaciones Custom <<<< Vamos a ver si llegamos
  * Anotaciones
    * @NotNull
    * @NotBlank
    * @Min
    * @Max
    * @Valid
    * @Validate
      * Grupos de Validaciones
* Manejo de Excepciones
  * AOP
    * Anotaciones
      * @RestControllerAdvice
      * @ExceptionHandler
* Buenas practicas
  * MSA
    * Division de Entities y DTO
    * Versiona de Servicios
  * Persistencia
    

---
# Spring Security

* Formas de Autenticacion de Microservicios
  * Forma Personalizada
    * Con cookies
    * Headers con credenciales
    * QueryString con Sesion (Vulnerable a SessionHighjack)
  * MicroServicios con API Key
    * Forma mas utilizada para API Publicas
  * HttpBasic
  * JWT
	  * No es una forma de autenticacion en si sino una manera de transmitir informacion segura entre dos apps
	  * (Iformacion segura que no quiero que se altere) --> (La firmamos con un secreto/clave propio de la app) --> JWT
	  * https://www.jwt.io/
	  * Cualquiera lo puede desencriptar y ver
	  * Pero solo los que conocesn la clave lo pueden generar y saber si fue adulterad
      * No vamos a poner informacion sensible (solo el nombre de usuario y no la clave)
      * El JWT Tiene una fecha de expiracion, despues de ese tiempo hay crearlo de nuevo
  * OAuth2
	  * CLIENT_CREDENTIALS
			* ---> El servidor OAUTH directamnte devuelve un token si esta autenticado a partir de un   Clientid, ClientSecret
		    * ---> Se utiliza para autenticar un microservicio desde otro microservicio con usuario y clave fijos  (Clientid / ClientSecret)
      * AUTHORIZATION_CODE
            * ---> El usuario trata de acceder un recurso protegido
            * ---> El microservicio reconoce que el usuario no esta autemcado y manda al usuario al servidor de autenticacion (login de google)
            * ---> El usuario se atentica en la pagina de login del servidor de autenticacion con su usuario y clave
            * ---> El servidor de autenticacion redirije al usuario al recurso protegido con el token de acceso (JWT)
  * Certificados
* Autenticacion y Autorizacion
   * Autenticacion
      * Que un usuario verifique que es quien dice ser
         * Certificado
         * Usuario/Password
         * Api Key
      * Autorizacion
        * Que el usuario acceda a donde puede acceder
          * Por Usuario
          * Por Roles
          * Por Claims
             * Edad / Ubicacion / Dispositivo 
* Tipos de Autenticacion
   * Usuario ----> Microservicio
      * Cada Usuario tiene NombreUsuario / Password
   * Microservicio  ----> MicroServicio
      * Cada Microservicio tiene ClientID / ClientSecret
      * Cada Microservicio tiene un Certificado
   * Dispositivo ---> Microservicio
      * Cada Dispositivo tiene un Certificado

## Buenas Practicas

* Los password nunca se almacenan en texto
  * Directamente Springboot security no te deja
* Usar el criterio Least Priviledge Acces 
* Chequear vulnerabilidades comunes
  * CSRF (Cross Site Request Forgery) : https://es.wikipedia.org/wiki/Cross-site_request_forgery

## HttpBasic

*  Creamos el controlador con los endpoints

```python
@RestController
public class RoleController {
	
	@GetMapping("/user")
	public String userAccess() {
		return "Acceso permitodo para USER";
	}
	
	@GetMapping("/admin")
	public String adminAccess() {
		return "Acceso permitodo para ADMIN";
	}
	
	@GetMapping("/privado")
	public String privado() {
		return "Acceso privado para todos";
	}
	
	@GetMapping("/publico")
	public String publico() {
		return "Acceso publico para todos";
	}
}

```

* Si entro vemos un login,
  * Todos los endpoints protegidos,
  * En desarrollo tira una clave de prueba

* Cremos el fitro del security Config

```python
package org.gobvasco.cursomsa.basic_auth.controllers.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		//Aca Cada uno puede elegir su propio Encoder
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
		
		//UserDetailsManager es una interfaz de SpringSecurity 
		//UserDetailsManager podes implenetarlo y por debajo hace un httprequest que ya tene
		
		UserDetails user = User.builder()
				.username("user")
				.password(passwordEncoder.encode("1234"))
				.roles("USER")
				.build();
		
		UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder.encode("pass"))
				.roles("ADMIN")
				.build();

		UserDetails su = User.builder()
				.username("su")
				.password(passwordEncoder.encode("su"))
				.roles("USER", "ADMIN")
				.build();
		
	    return new InMemoryUserDetailsManager(user, admin, su);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(
				auth ->
					auth
						.requestMatchers("/user").hasRole("USER")
						.requestMatchers("/admin").hasRole("ADMIN")
						.requestMatchers("/privado").authenticated()
						.anyRequest().permitAll()					
				)
			  .httpBasic(Customizer.withDefaults())
			  //Si usas formularios, una interfaz web, lo habilitas
			  //Si el servicio se puede ejecutar de cualqueir lado lo deshabilitas
			  .csrf(csrf -> csrf.disable());
		
		return http.build();
	}
}

```

* Probar los endpoints en el navegador y ver que pide clave

* Probar todas las alternativas por CURL

```
> curl http://localhost:8080/user
{"timestamp":"2026-03-26T10:43:24.838Z","status":401,"error":"Unauthorized","message":"Unauthorized","path":"/user"}
> curl http://localhost:8080/user
{"timestamp":"2026-03-26T10:43:28.693Z","status":401,"error":"Unauthorized","message":"Unauthorized","path":"/user"}
> curl -u user:1234 http://localhost:8080/user
Acceso permitodo para USER
> curl -u user:12343 http://localhost:8080/user
{"timestamp":"2026-03-26T10:44:03.533Z","status":401,"error":"Unauthorized","message":"Unauthorized","path":"/user"}
```


## Autenticacion con JWT

* Workflow
	* El usuario se autentica en /login
	* El sistema le devuelve un JWT con el nombre del usuario firmado con su clave privada(no guardamos el password)
	* El usuario accede a un recurso protegido y le manda el JWT que la aplicacion valida con su clave

* Incluir en el POM

````xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.11.5</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.11.5</version>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.11.5</version>
    <scope>runtime</scope>
</dependency>
```

* Para descriptar el token

```java
package org.gobvasco.cursomsa.jwt_auth_demo.util;

import java.util.Date;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	private final String SECRET_KEY = "claveeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee";
	
	public String generateToken(String username) {
		return 
				Jwts
					.builder()
					.setSubject(username)
					.setIssuedAt(new Date())
					.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) 
					.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
					.compact();
	}
	
	public String extractUserName(String token) {
		return Jwts
				.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(token)
				.getBody().getSubject();
	}
	
	public boolean vencido(String token) {
		return Jwts
				.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(token)
				.getBody()
				.getExpiration()
				.after(new Date());
	}
	
	
}
```

* Para validar el token lo hacemos directamente en un controlador

> Nota esto lo hariamos con un filter y un secutiry config pero lo hice mas rapido para mostrarlo asi vamos directo a Oauth

```java
package org.gobvasco.cursomsa.jwt_auth_demo.controllers;

import org.gobvasco.cursomsa.jwt_auth_demo.models.AuthRequest;
import org.gobvasco.cursomsa.jwt_auth_demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
	
	@Autowired
	JwtUtil jwtUtil;
		
	@GetMapping("/validar-token")
	public String validarToken(@RequestParam String token) {
		//ESTO LO HARIAMOS EN UN FILTER QUE VALIDE EL TOKEN EN EL HEADER PARA CADA REQUEST
		
		if (!this.jwtUtil.vencido(token)) {
			return "El token es invalido no tenes acceso";
		}
		return "Felicidades el token es valido usuario " + this.jwtUtil.extractUserName(token);
	}

	@GetMapping("/logear")
	public String login(@RequestParam String usuario, @RequestParam String password) {
		
		//Vamos a suponer que se logueando
		return jwtUtil.generateToken(usuario);
	}
}

```

* Podemos ver el contendio del token en https://www.jwt.io/

* Solo lo podemos verificar si tenemos la clave privada

## OAuth (Authorization server)

### OAuthServer

* Creamos un proyecto de Spring Initializr con Oauth2 Authorization Server
	* En la practica muchas veces no se instala el servidor de autorizacion sino se usa un proveedor conocido como google

* Este servidor lo vamos a ejecutar en el puerto 9000

```application.properties
spring.application.name=oauth-server
server.port=9000
```

* Si queremos autenticar otros usuarios con AUTHORIZATION_FLOW

```java
package org.gobvasco.cursomsa.oauth_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserConfig {

	
	//SI USARAMOS AUTHORIZATION_CODE (AUTHORIZATION_FLOW este es el usuario que usaria
	@Bean
	public UserDetailsService users() {
        UserDetails user = User.withUsername("juan")
                .password("{noop}1234")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
	}
}

```

* Si quiero atenticar otros microservicios con CLIENT_CREDENTIALS

```java
package org.gobvasco.cursomsa.oauth_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;


//Si usamos CLIENT_CREDENTIALS (es decir autenticamos otro microservicio) usamos esta configuracion

@Configuration
public class ClientConfig {
	
	@Bean
	public RegisteredClientRepository registeredClientRepository() {
		
		RegisteredClient clientA = RegisteredClient
				.withId("microservicio-cliente")
				.clientId("servicio-origen")
				.clientSecret("{noop}clavesecreta")
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
				.scope("read")
				.build();
		
		return new InMemoryRegisteredClientRepository(clientA);
	}

}

```

## OAuthResourceClient (usuario->microservicio)



---

## Clases de Conguracion

* @Configure
* Configuramos inyeccion de dependencias por codigo



