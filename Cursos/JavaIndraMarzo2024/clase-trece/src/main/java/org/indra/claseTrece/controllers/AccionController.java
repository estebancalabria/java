package org.indra.claseTrece.controllers;

import java.util.*;

import org.indra.claseTrece.models.Accion;
import org.indra.claseTrece.services.CrudService;
import org.indra.claseTrece.services.ServiceError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccionController {
	
	//Pasamos al Service
	//List<Accion> acciones = new ArrayList<>();
	
	//Inyeccion de dependencias
	//Yo no instancio esta propiedad
	@Autowired //Esta propiedad es como modificar el archivo beans.xmll
	private CrudService<String, Accion> service;
	
	public AccionController() {
		//Pasamos al service
		/*acciones.add(new Accion() {{
		  setSimbolo("INDRA");
		  setNombre("Indra");
		  setUltimaCotizacion(100.0);
		}});
		
		acciones.add(new Accion() {{
			  setSimbolo("NVDA");
			  setNombre("Nvidia Corporation");
			  setUltimaCotizacion(200.0);
			}});
		
		acciones.add(new Accion() {{
			  setSimbolo("MCD");
			  setNombre("MacDonalds");
			  setUltimaCotizacion(100.0);
			}});
		
		acciones.add(new Accion() {{
			  setSimbolo("MSFT");
			  setNombre("Microsoft");
			  setUltimaCotizacion(400.0);
			}});*/
	}
	
	
	@GetMapping("/api/accion")
	public List<Accion> getAll(){
		return this.service.readAll();
		
		//Pasamos al service
		//return acciones;
	}
	
	/*@GetMapping("/api/accion/{simbolo}")
	public Accion get(@PathVariable()String simbolo){
		Accion accion = this.acciones
				.stream().filter(a -> a.getSimbolo().equals(simbolo))
				.findFirst()
				.orElse( new Accion() {{
					setSimbolo("NOT-FOUND");
					setNombre("Accion Desconocida");
					setUltimaCotizacion(0);
				}});
		return accion;
	}*/
	
	//El ResponseEntity me permite manipular los status http
	@GetMapping("/api/accion/{simbolo}")
	public ResponseEntity<Accion> get(@PathVariable()String simbolo){
		try {
			return ResponseEntity.ok(this.service.readById(simbolo));
			
		}catch(ServiceError err) {
			return new ResponseEntity<Accion>(HttpStatus.NOT_FOUND);
		}
		
		//Pasa al servicio
		/*Accion accion = this.acciones
				.stream().filter(a -> a.getSimbolo().equals(simbolo))
				.findFirst()
				.orElse( null);
		if (accion != null) {
			return ResponseEntity.ok(accion);
		}
		return new ResponseEntity<Accion>(HttpStatus.NOT_FOUND);*/
	}
	
	@PostMapping("/api/accion") 
	public ResponseEntity<String> add(@RequestBody Accion accion){
		try {
			this.service.create(accion);
			return ResponseEntity.ok("Accion agregada satisfactoriamente");
		} catch (ServiceError err) {
			return ResponseEntity.badRequest().body(err.getMessage());
		}
		
		//Validar con un servicio etc etc....
		//-- Que tengan todos los campos
		/*if ((accion.getSimbolo()==null) || (accion.getSimbolo().trim().length()==0)) {
			return ResponseEntity.badRequest().body("Falta el simbolo");
		}
		
		if ((accion.getNombre()==null) || (accion.getNombre().trim().length()==0)) {
			return ResponseEntity.badRequest().body("Falta el nombre");
		}
		
		if (accion.getUltimaCotizacion()<=0) {
			return ResponseEntity.badRequest().body("Mal la ultima cotizacion");
		}
		
		//-- Que no este repetida la accion
		//Se animan uds?
		 if(this.acciones
	                .stream()
	                .filter(a -> a.getSimbolo().equals(accion.getSimbolo()))
	                .findFirst().isPresent()) {
	             
	                return ResponseEntity.badRequest().body("La accion ya existe");
	    }
		
		this.acciones.add(accion);
		return ResponseEntity.ok("Accion agregada satisfactoriamente");*/
	}
	
	@DeleteMapping("/api/accion/{simbolo}")
	public ResponseEntity<String> delete(@PathVariable() String simbolo){
		try {
			this.service.delete(simbolo);
			return ResponseEntity.ok("Accion eliminada satisfactoriamente");
		}catch (ServiceError err) {
			return new ResponseEntity<>(err.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		/*if (this.acciones.removeIf(n -> n.getSimbolo().equals(simbolo))) {
			 return ResponseEntity.ok("Accion eliminada satisfactoriamente");
		}
		return new ResponseEntity<>("Accion no encontrada", HttpStatus.NOT_FOUND);*/
	}
	
	@PutMapping("/api/accion/{simbolo}")
	public ResponseEntity<String> update(@PathVariable() String simbolo, @RequestBody Accion accion){
		try {
			this.service.update(simbolo, accion);
			return ResponseEntity.ok("Accion Modificada");
		}catch (ServiceError err) {
			return new ResponseEntity<>(err.getMessage(),HttpStatus.NOT_FOUND);
		}
		
		/*//recupero la accion con el sibolo
		//actualizo los datos con el otro parametro
		Accion accionActual = this.acciones
				.stream().filter(a -> a.getSimbolo().equals(simbolo))
				.findFirst()
				.orElse( null);
		if (accionActual != null) {
			//Validar que los datos nuevos esten bien
			accionActual.setSimbolo(accion.getSimbolo());
			accionActual.setNombre(accion.getNombre());
			accionActual.setUltimaCotizacion(accion.getUltimaCotizacion());
			
			return ResponseEntity.ok("Accion Modificada");
		}
		return new ResponseEntity<>("Accion No Existe",HttpStatus.NOT_FOUND);*/
	}
	
	/*@GetMapping("/api/accion/echo")
	public Accion echo(@RequestParam() Accion accion) {
		return accion;
	}*/
}
	
	
	


	

