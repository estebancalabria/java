package org.allianz.hellospringboot.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@SuppressWarnings("deprecation")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("root").password("{noop}root").roles("USUARIO");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("Configurando Permisos Acceso de la application");
		/*http
			.httpBasic()
			.and()
			.authorizeHttpRequests()
			.antMatchers("/api/cliente/*", "/api/cliente")
			.hasRole("USUARIO")*/
		http
		.httpBasic()
		.and()
		.authorizeHttpRequests()
		.antMatchers("/api/login")
		.hasRole("USUARIO");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		//Los siguientes se validan con el token
		web.ignoring().antMatchers("/api/cliente","api/cliente/*");
	}
}
