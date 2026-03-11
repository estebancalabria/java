package org.talentCamp.claseDos.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.talentCamp.claseDos.models.Saludo;

@RestController
public class HelloController {

    @RequestMapping(value = "/api/holaMundo", method = RequestMethod.GET)
    public String index(){
        return "Hola Mundo";
    }

    @GetMapping("/api/holaJson")
    public Saludo json(){
        return new Saludo("Hola mundo json", 1, "Esteban Calabria");
    }
}
