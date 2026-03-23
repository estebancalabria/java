package com.example.security.demosecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
    @GetMapping("/public")
    public String publicEndpoint() {
        return "Acceso público";
    }

    @GetMapping("/private")
    public String privateEndpoint() {
        return "Acceso privado";
    }
}
