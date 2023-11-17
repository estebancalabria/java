package org.allianz.clasecincospringboot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/")
	public String getIndex() {
		return "<h1>Bienvenidos a SpringBoot</h1>";
	}

}
