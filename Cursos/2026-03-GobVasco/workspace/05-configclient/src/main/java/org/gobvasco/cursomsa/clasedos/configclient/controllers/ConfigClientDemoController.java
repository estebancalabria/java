package org.gobvasco.cursomsa.clasedos.configclient.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientDemoController {
	
	@Value("${app.mensaje}")
	private String mensaje;

	@GetMapping("/demo")
	public String demo() {
		return this.mensaje;
	}
}
