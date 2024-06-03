package org.talentCampo.ultimaClase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterHttpRequests(HttpSecurity http) throws Exception{
        return http.authorizeHttpRequests( (requests)->
                requests
                        .requestMatchers("/").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin( (form) -> form.loginPage("/login").permitAll())
                .build();
    }

    @Bean
    public UserDetailsService getUserDetailsService(){
        UserDetails user = User
                .withDefaultPasswordEncoder()
                .username("esteban")
                .password("esteban")
                .roles("USER").build();

        return new InMemoryUserDetailsManager(user);
    }
}
