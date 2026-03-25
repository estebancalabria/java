package org.gobvasco.cursomsa.clasedos.servicioB.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class ServiceBController {

		
	@GetMapping("/serviceb")
	public String heatbeat() {
		return "Service B is OK";
	}
	
	@GetMapping("/sync")
	public String syncCommunication() {
		String resultadoLlamada = WebClient.create("http://localhost:8081/")
			.get()
			.uri("servicea")
			.retrieve()
			.bodyToMono(String.class)
			.block();
		 
		return "La llamada anidada es " + resultadoLlamada; 
	}

}
