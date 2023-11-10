package org.itnow.cursoSpring.clasetres.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController {
	
	@GetMapping("/error")
	public String manejarError() {
		return "Bienvenidos a mi pagina de Error customizada";
	}

}
