package org.allianz.hellospringboot.controllers;

import org.allianz.hellospringboot.dto.*;
import org.allianz.hellospringboot.models.Cliente;
import org.allianz.hellospringboot.services.IClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class ClienteController {
	
	IClienteService service;
	Logger log = LoggerFactory.getLogger(ClienteController.class);
	
	public ClienteController(@Autowired IClienteService service) {
		this.service = service;
	}

	@GetMapping("/api/cliente")
	public List<Cliente> listarTodos(){
		return this.service.getAll();
	}
	
	@GetMapping("/api/cliente/{id}")
	public ResponseEntity<Cliente> obtenerPorId(@PathVariable int id){
		try {
			Cliente c = this.service.getById(id);
			return ResponseEntity.ok(c);
		} catch (Exception ex) {
			return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/api/cliente/{id}")
	public ResponseEntity<DeleteResult> eliminar(@PathVariable int id){
		this.service.delete(id);
		return ResponseEntity.ok(new DeleteResult(true, "Cliente eliminado satisfactoriamente"));
	}
	
	@PostMapping("/api/cliente")
	public ResponseEntity<AddResult> agregar(@RequestBody Cliente cliente){
		try {
			this.service.add(cliente);
			return ResponseEntity.ok(new AddResult(true, "Cliente agregado satisfactoriamente", cliente));
		} catch (Exception ex) {
			return new ResponseEntity<AddResult>(new AddResult(false, ex.getMessage(), cliente), 
					HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
