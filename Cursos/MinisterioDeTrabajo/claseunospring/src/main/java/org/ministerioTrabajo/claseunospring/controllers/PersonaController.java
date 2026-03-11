package org.ministerioTrabajo.claseunospring.controllers;

import java.util.List;

import org.ministerioTrabajo.claseunospring.dto.PersonaDto;
import org.ministerioTrabajo.claseunospring.services.ElementoDuplicadoException;
import org.ministerioTrabajo.claseunospring.services.NoEncontradoException;
import org.ministerioTrabajo.claseunospring.services.PersonaService;
import org.ministerioTrabajo.claseunospring.services.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonaController {
	
	@Autowired //Spring va a elegir la implementacion de la interfaz para poner aqui...
	public PersonaService service;

	//SE VA A BORRAR, LO MANEJA EL SERVICIO
	/*private List<Persona> personas = new ArrayList<>(Arrays.asList(new Persona(1, "Esteban", 43), new Persona(2, "Diego", 32),
			new Persona(3, "Javier", 36)));*/

	@GetMapping("/personas")
	//public List<Persona> getAll() {
	//public ResponseEntity<List<Persona>> getAll() {
	public ResponseEntity<List<PersonaDto>> getAll() {
		// Como se crea un array de numeros en java
		// int[] numeros = { 1,2,3,4 };
		//return this.personas;
		
		//return ResponseEntity.ok(this.personas);
		
		//Una vez que agrego el servicio...
		return ResponseEntity.ok(this.service.findAll());
	}

	@GetMapping("/personas/{id}")
	//public ResponseEntity<Persona> getById(@PathVariable int id) {
	public ResponseEntity<PersonaDto> getById(@PathVariable int id) {
		//Persona result = null;
		
		//Forma 1 : Clasica
		/*for (Persona p : this.personas) {
			if (p.getId() == id) {
				result = p;
				break; //Se puede mejorar con el break
			}
		}*/
		
		//Forma 2 : NO ANDA : Introduccion a expresiones lambda
		/*this.personas.forEach(p -> { 
				if (p.getId() == id) {
					result = p;
				}
		});*/
		
		//Forma 3 : La de los pro
		//result = this.personas.stream().filter(p -> p.getId() == id).findFirst().get();
		//Si lo queremos igual a la forma uno que no lance una excepcion si no existe
		//result = this.personas.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
		
		//Forma 4 : Con el response Emptity
		//Optional<Persona> buscado = this.personas.stream().filter(p -> p.getId() == id).findFirst();
		
		//if (buscado.isEmpty()) {
			//devolvemos un 404
		//	return ResponseEntity.notFound().build();
			//return new ResponseEntity<>("No se encuentra la persona con ese id", HttpStatus.NOT_FOUND);
		//}
		
		//Este...
		//return ResponseEntity.ok(buscado.get());
		//O este...
		//return new ResponseEntity<>(buscado.get(), HttpStatus.OK);
		//o bien...
		//return new ResponseEntity<Persona>(buscado.get(), HttpStatus.OK);
		
		//return new ResponseEntity<Persona>(this.service.findById(id), HttpStatus.OK);		
		return new ResponseEntity<>(this.service.findById(id), HttpStatus.OK);
	}
	
	@PostMapping("/personas")
	//public ResponseEntity<String> agregar(@RequestBody Persona persona) {
	public ResponseEntity<String> agregar(@RequestBody PersonaDto persona) {
		//200 - Ok-  Si pudo agregar bien
		//400 - Bad Request - Si hay un error de validacion
		//409 - Conflict - Si ya otra persona con el mismo nombre
		
		//Opcion 1: Solucion NAIVE para la parte del ID
		//persona.setId( this.personas.size() + 1);  //Vemos que falla si elimino uno
		
		//Opcion 2: Tradicional
		/*int maxId = 0;
		for (Persona p : this.personas) {
			if (p.getId()>maxId) {
				maxId = p.getId();
			}
		}*/
		
		//Validar
		//if ( (persona.getNombre() == null) ||  persona.getNombre().trim().length() == 0 ) {
		/*if ( (persona.getNombre() == null) ||  persona.getNombre().isBlank() ) {
			return ResponseEntity.badRequest().body("El nombre de la persona no puede quedar vacio");
		}
		
		//Valido que la edad de la persona sea mayor a 0
		if(persona.getEdad() <= 0) {
			return ResponseEntity.badRequest().body("Edad de la persona incorrecta");
		}		
		
		//Vamos a suponer que no permito dos personas con el mismo nombre
		Optional<Persona> existente = this.personas.stream().filter( p -> p.getNombre().equals(persona.getNombre()))
				.findFirst();
		if (existente.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe otra persona con el mismo nombre");
		}
		
		
		//Opcion 3 : El problema si la lista esta vacia	
		int maxId = 0;
		if (!this.personas.isEmpty()) {
		    maxId = this.personas.stream().max( (p1,p2)-> p1.getId() - p2.getId() ).get().getId();
		}
		

		//Opcion 4 :
		//Por lo general no se ponen las llaves en la funcion anonima y el return es implicito
		//int maxId = this.personas.stream().map( p -> p.getId() ).max( (n1,n2) -> n1-n2 ).orElse(0);
		
		//Si pongo las llaves en la funcion anonima hay que escribir el return
		//int maxId = this.personas.stream().map( p -> p.getId() ).max( (n1,n2) -> { return n1-n2; } ).orElse(0);
		
		//En vez de pasarle una funcion anonima le paso un metodo que ya existe
		//int maxId = this.personas.stream().map( p -> p.getId() ).max(Integer::compare).orElse(0);
		
		persona.setId(maxId+1);
		
		this.personas.add(persona);
		return ResponseEntity.ok("Persona Agregada Satisfactoriamente");*/
		
		this.service.agregar(persona);
		return new ResponseEntity<>(HttpStatus.CREATED);

		//Tambien es posible...
		//return ResponseEntity.created(URI.create("/persona/"+persona.getId())).build();
	}
	
	@DeleteMapping("/personas/{id}")
	public ResponseEntity<String> eliminar(@PathVariable int id) {
		
		/*if (this.personas.removeIf(p -> p.getId() == id)) {
			return "Pesona Eliminada Satisfactoriamente";	
		}else {
			return "Pesona No existe";
		}*/
		
		
		/*return this.personas.removeIf(p -> p.getId() == id) 				
				? ResponseEntity.ok("Pesona Eliminada Satisfactoriamente") 
				: new ResponseEntity<>("Pesona No existe", HttpStatus.NOT_FOUND);*/
		
		//Ahora con el servicio
		this.service.deleteById(id);
		return ResponseEntity.ok("Pesona Eliminada Satisfactoriamente");
		
	}
	
	/*@ExceptionHandler(NoEncontradoException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handleNoEncontradoException(NoEncontradoException exception){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
	}
	
	@ExceptionHandler(ElementoDuplicadoException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public ResponseEntity<String> handleElementoDuplicadoException(ElementoDuplicadoException exception) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
	}	
	
	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleElementoDuplicadoException(ValidationException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}*/
	
}
