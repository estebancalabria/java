package com.example.auth_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

@Configuration
public class ClientConfig {

    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        
    	//CLIENT_CREDENTIALS
    	/*RegisteredClient clientA = RegisteredClient.withId("microA-id")
            .clientId("microA")             // ✅ ClientID fijo
            .clientSecret("{noop}secretA")  // ✅ ClientSecret fijo
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS) // solo testing
            .scope("read")
            .build();

        return new InMemoryRegisteredClientRepository(clientA);*/
    	
    	//AutorizationCode
        RegisteredClient clientA = RegisteredClient.withId("cliente-microA")
                .clientId("microA")
                .clientSecret("{noop}secretA")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("http://localhost:8080/login/oauth2/code/microA-oidc")
                .scope("read")
                .build();
            return new InMemoryRegisteredClientRepository(clientA);
    	
    	
    }
}