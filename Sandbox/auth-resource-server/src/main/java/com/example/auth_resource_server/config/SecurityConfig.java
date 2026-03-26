package com.example.auth_resource_server.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	
	/*@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/recurso").authenticated() // proteger solo /recurso
	            .anyRequest().permitAll()
	        )
	        .oauth2ResourceServer(oauth2 -> oauth2
	            .jwt(jwt -> {}) // pasamos un Customizer vacío
	        );

	    return http.build();
	}*/
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/recurso").authenticated()
	            .anyRequest().permitAll()
	        )
	        .oauth2Login(oauth2 -> {}); // habilita login con Authorization Code

	    return http.build();
	
	}
}