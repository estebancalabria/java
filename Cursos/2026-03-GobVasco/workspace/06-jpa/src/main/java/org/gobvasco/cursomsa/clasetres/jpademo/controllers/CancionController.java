package org.gobvasco.cursomsa.clasetres.jpademo.controllers;

import java.util.List;

import org.gobvasco.cursomsa.clasetres.jpademo.dto.CancionDTO;
import org.gobvasco.cursomsa.clasetres.jpademo.entities.*;
import org.gobvasco.cursomsa.clasetres.jpademo.repositories.CancionRepository;
import org.gobvasco.cursomsa.clasetres.jpademo.services.CancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;


@RestController
public class CancionController {

	//OJO: Mando un repository de una pero esta mal, deberia pasar por el service
	@Autowired
	private CancionService service;
	
	@PostMapping("/api/v1/canciones")
	public CancionDTO crearOk(@Valid @RequestBody CancionDTO cancion) {
		//El servicio haria validaciones por codigo, logica de negocios, etc...
		return this.service.crearSinError(cancion);
	}
	
	@PostMapping("/api/v1/canciones-sin-transaccion")
	public CancionDTO crearSinTransaccion(@Valid @RequestBody CancionDTO cancion) {
		//El servicio haria validaciones por codigo, logica de negocios, etc...
		return this.service.crearConError(cancion);
	}
	
	@PostMapping("/api/v1/canciones-con-transaccion")
	public CancionDTO crearConTransaccion(@Valid @RequestBody CancionDTO cancion) {
		//El servicio haria validaciones por codigo, logica de negocios, etc...
		return this.service.crearConErrorYTransaccion(cancion);
	}
	
	//Mejor api/cancion en singular se usa mucho
	@GetMapping("/api/v1/canciones")
	public List<CancionDTO> listar(){
		return this.service.listar();
	}
	
	//En la practica me gustraria tener un solo encpoint /canciones pero bueno...
	//Habria que agregarle el metodo al servicio, pero por lo pronto no me interesa
	/*@GetMapping("/api/v1/cancionestitulo")
	public List<Cancion> listarPorTitulo(@RequestParam String titulo){
		
		//return this.repo.findByTituloContaining(titulo);
		//O bien la otra opcion....
		//return this.repo.buscarPorTituloConQuery(titulo);		
	}*/
}
