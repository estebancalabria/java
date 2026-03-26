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
  * OAuth2
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

##

##

---
# Spring Boot

## Documentacion con Swagger


## Clases de Conguracion

* @Configure
* Configuramos inyeccion de dependencias por codigo



