package org.tiben.springweboauthdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
public class WebConfigure {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests( auth -> {
        	auth.requestMatchers("/", "/hello", "/user", "/error", "/webjars/**").permitAll();
        	auth.anyRequest().authenticated();
        })
        .oauth2Login(withDefaults())
        .formLogin(withDefaults());

        return http.build();
    }

}