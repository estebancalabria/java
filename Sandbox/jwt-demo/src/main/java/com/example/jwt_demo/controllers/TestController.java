package com.example.jwt_demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/private")
    public String privateEndpoint() {
        return "Acceso a endpoint protegido por JWT";
    }
}
