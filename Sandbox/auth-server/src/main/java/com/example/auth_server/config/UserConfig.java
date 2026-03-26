package com.example.auth_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserConfig {

    @Bean
    public UserDetailsService users() {
        UserDetails user = User.withUsername("juan")
                               .password("{noop}1234")
                               .roles("USER")
                               .build();
        return new InMemoryUserDetailsManager(user);
    }
}