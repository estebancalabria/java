package com.example.auth_resource_server.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecursoController {

    // Endpoint protegido
    @GetMapping("/recurso")
    public String recurso(@AuthenticationPrincipal Jwt jwt) {
        return "Hola " + jwt.getClaimAsString("user_name") + ", accediste al recurso protegido!";
    }

    // Endpoint público
    @GetMapping("/publico")
    public String publico() {
        return "Este endpoint es público y accesible sin token.";
    }
}