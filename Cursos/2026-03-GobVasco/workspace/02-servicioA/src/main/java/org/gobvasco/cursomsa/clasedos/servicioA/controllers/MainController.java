package org.gobvasco.cursomsa.clasedos.servicioA.controllers;

import org.gobvasco.cursomsa.clasedos.servicioA.external.ServiceBInvocation;
import org.gobvasco.cursomsa.clasedos.servicioA.services.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MainController {
	
	private final RestTemplate http;
	
	@Value("${app.mensaje}")
	private String mensaje;
	
	@Autowired
	private AsyncService service;
	
	private ServiceBInvocation externalService;
		
	public MainController(RestTemplate http, ServiceBInvocation external) {
		this.http = http;
		this.externalService = external;
	}
	
	@GetMapping("/servicea")
	public String heatbeat() {
		//return "Service A is OK";
		return this.mensaje;
	}
	
	@GetMapping("/sync")
	public String syncComminication() {
		String resultadoAnidado = this.http.getForObject(
				"http://localhost:8080/serviceb", 
				String.class);
				
		
		return "La llamada al servicio anidado es " + resultadoAnidado;
		
	}
	
	@GetMapping("/sync-feign")
	public String syncComminicationFerign() {
		/*String resultadoAnidado = this.http.getForObject(
				"http://localhost:8080/serviceb", 
				String.class);*/
				
		
		return "La llamada al servicio anidado es " + this.externalService.getMensaje();
		
	}
	
	@GetMapping("/start-async")
	public String startAsyncProcess() {
		this.service.iniciarProcesoLargo();
		return "Proceso Largo Inciado";
	}
	
	@GetMapping("/async-status")
	public String estadoProceso() {
		return this.service.getEstadoProceso();		
	}

}
