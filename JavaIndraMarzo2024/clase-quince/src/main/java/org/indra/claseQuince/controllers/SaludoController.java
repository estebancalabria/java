package org.indra.claseQuince.controllers;

import org.indra.claseQuince.services.SaludoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaludoController {
	
	@Autowired
	private SaludoService service;

	@GetMapping("/")
	public String saludar() {
		return service.saludar();
	}
}
