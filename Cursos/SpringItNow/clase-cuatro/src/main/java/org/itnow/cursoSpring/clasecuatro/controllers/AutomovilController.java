package org.itnow.cursoSpring.clasecuatro.controllers;

import java.util.*;
import org.itnow.cursoSpring.clasecuatro.models.*;
import org.itnow.cursoSpring.clasecuatro.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
//@BaseUrl("/api/automovil") << Hay algo asi
public class AutomovilController {

	@Autowired
	AutomovilService service;

	@GetMapping("/automovil")
	public ResponseEntity<List<Automovil>> index() {
		/*
		 * List<Automovil> misAutosFavoritos = new ArrayList<>();
		 * misAutosFavoritos.add(new Automovil("Porsche","911",1960));
		 * misAutosFavoritos.add(new Automovil("Bugatti","Veyron",2015));
		 * misAutosFavoritos.add(new Automovil("Ford","Mustang",1967));
		 */

		// Incorporo el servicio
		List<Automovil> misAutosFavoritos = service.obtenerTodos();
		return ResponseEntity.ok(misAutosFavoritos);
	}

	@RequestMapping(value = "/automovil/{id}", method = RequestMethod.GET)
	public ResponseEntity<Automovil> index(@PathVariable() int id) {
		// return ResponseEntity.ok(new Automovil("Porsche","911",1960));

		// Incorporo al service
		Automovil existente = service.obtenerPorId(id);
		return ResponseEntity.ok(existente);

	}

	@PostMapping("/automovil")
	public ResponseEntity<ControllerResponse> post(@RequestBody() Automovil nuevo) {
		// Incorporo al service
		try {
			service.registrarNuevo(nuevo);

			return ResponseEntity.ok(
					ControllerResponse
						.builder()
						.operacion("ADD")
						.mensaje("Automovil registrado satisfactoriamente")
						.success(true)
						.entidadAfectada(nuevo)
						.build());			
		}catch(Exception ex) {
			return ResponseEntity.internalServerError()
						.body(ControllerResponse.builder()
							.operacion("ADD")
							.mensaje(ex.getMessage())
							.success(false)
							.build());	
			
		}
	}

	@PutMapping("/automovil")
	public ResponseEntity<ControllerResponse> put(@RequestBody() Automovil existente) {
		// Incorporo al service
		try {
		  service.actualizarInformacion(existente);
		  return ResponseEntity.ok(ControllerResponse.builder()
				     .success(true)
				     .operacion("UPDATE")
				     .entidadAfectada(existente)
				     .mensaje("Actualizado Automovil existente")
				     .build());
		}catch(Exception ex){
			  return ResponseEntity
					  .internalServerError()
					  .body(ControllerResponse
					     .builder()
					     .success(false)
					     .operacion("UPDATE")
					     .mensaje(ex.getMessage())
					     .build());			
		}
	}

	@RequestMapping(value = "/automovil/{id}", method = RequestMethod.DELETE)
	ResponseEntity<ControllerResponse> delete(@PathVariable() int id) {
		// Incorporo al service
		try {
			Automovil eliminado = service.obtenerPorId(id);
			service.darDeBaja(id);
			return ResponseEntity.ok(ControllerResponse
				     .builder()
				     .success(true)
				     .operacion("DELETE")
				     .entidadAfectada(eliminado)
				     .mensaje("Eliminado Automovil existente")
				     .build());
			
		} catch (Exception ex) {
			return ResponseEntity
					.internalServerError()
					.body(ControllerResponse
						     .builder()
						     .success(false)
						     .operacion("DELETE")
						     .mensaje(ex.getMessage())
						     .build());
		}
	}

}
