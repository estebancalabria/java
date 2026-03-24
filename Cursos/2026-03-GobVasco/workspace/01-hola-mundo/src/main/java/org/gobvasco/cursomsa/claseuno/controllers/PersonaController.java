package org.gobvasco.cursomsa.claseuno.controllers;

import java.util.List;

import org.gobvasco.cursomsa.claseuno.dto.Persona;
import org.gobvasco.cursomsa.claseuno.services.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonaController {
	
	@Autowired
	private IPersonaService personaService;
	
	@Value("${app.mensaje.holamundo}")
	private String holaMundoMessage;
	
	@GetMapping("/holamundo")
	public String holaMundo() {
		//return "Hola Mundo";
		return this.holaMundoMessage;
	}
	
	@GetMapping("/saludar")
	//public String saludar(@RequestParam String nombre) { 
	/*public String saludar(@RequestParam(required=false) String nombre) {
		return (nombre==null) ? "Hola desconocido" : "Hola "+nombre;
	}*/
	public String saludar(@RequestParam(defaultValue="desconocidooo") String nombre) {
		return "Hola "+ nombre;
	}
	
	@GetMapping("/saludo/{nombre}")
	public String saludo(@PathVariable String nombre) {
		return "Hola " + nombre;
	}
	
	
	@GetMapping("/persona")
	public List<Persona> getAll(){
		return this.personaService.getAll();
	}
	
	@GetMapping("/persona/{id}")
	public ResponseEntity<Persona> getById(@PathVariable int id){
		
		Persona persona = this.personaService.getById(id);
		

		if (persona==null) {
			return ResponseEntity.notFound().build(); 
		}
		
		return new ResponseEntity<Persona>(persona, HttpStatus.OK);
	}
	
	@PostMapping("/persona")
	public String add(@RequestBody Persona p) {
		this.personaService.add(p);
		return "OK";
	}
}

