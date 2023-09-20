package org.allianz.hellospringboot.controllers;

import org.allianz.hellospringboot.configurations.YamlConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	private YamlConfigProperties config;

	@GetMapping("/api/hello")
	public String hello(@RequestParam(defaultValue = "Mundo") String nombre) {
		return "Hola " + nombre + " en " + config.getIdioma() + " powered by " + config.getAutor();
	}
}
