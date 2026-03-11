package org.indra.claseTrece.services;


//
//Se animan a refactorizar el servicio uds?
//

import java.util.ArrayList;
import java.util.List;
import org.indra.claseTrece.models.*;
import org.indra.claseTrece.repositories.AccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccionService implements CrudService<String, Accion> {

	@Autowired
	private AccionRepository repository;
	
	//List<Accion> acciones = new ArrayList<>();

	public AccionService() {
		/*acciones.add(new Accion() {
			{
				setSimbolo("INDRA");
				setNombre("Indra");
				setUltimaCotizacion(100.0);
			}
		});

		acciones.add(new Accion() {
			{
				setSimbolo("NVDA");
				setNombre("Nvidia Corporation");
				setUltimaCotizacion(200.0);
			}
		});

		acciones.add(new Accion() {
			{
				setSimbolo("MCD");
				setNombre("MacDonalds");
				setUltimaCotizacion(100.0);
			}
		});

		acciones.add(new Accion() {
			{
				setSimbolo("MSFT");
				setNombre("Microsoft");
				setUltimaCotizacion(400.0);
			}
		});*/
	}

	@Override
	public void create(Accion model) {
		System.out.println("Create de Accion Service");
		Accion accion = model;
		try {
			accion.validar();

			/*
			if(this.acciones
		                .stream()
		                .filter(a -> a.getSimbolo().equals(accion.getSimbolo()))
		                .findFirst().isPresent()) {
		             
				 throw new ServiceError("La accion ya existe");
		    }*/
			
			if (this.repository.existsById(accion.getSimbolo())){
				throw new ServiceError("La accion ya existe");
			}
			
			//this.acciones.add(accion);
			
			//Para probar arranco con el save
			this.repository.save(accion);
		}catch(Error e) {
			throw new ServiceError(e.getMessage());
		}
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		System.out.println("Delete de accion service");
		try {
			if (!this.repository.existsById(id)) {
				throw new ServiceError("Accion no encontrada");
			}
			
			this.repository.deleteById(id);
			
		} catch (Exception ex) {
			throw new ServiceError(ex.getMessage());
		}
		
		/*if (!this.acciones.removeIf(n -> n.getSimbolo().equals(id))) {
			throw new ServiceError("Accion no encontrada");
		}*/
	}

	@Override
	public void update(String id, Accion model) {
		// TODO Auto-generated method stub
		try {	
			System.out.println("Update de accion service");
			
			model.validar();
			
			if (!this.repository.existsById(model.getSimbolo())) {
				throw new ServiceError("Accion no encontrada");
			}
			
			this.repository.save(model);
			/*Accion accion = model;
			Accion accionActual = this.acciones
					.stream().filter(a -> a.getSimbolo().equals(id))
					.findFirst()
					.orElse( null);
			
			if (accionActual != null) {
				//Validar que los datos nuevos esten bien
				accionActual.setSimbolo(accion.getSimbolo());
				accionActual.setNombre(accion.getNombre());
				accionActual.setUltimaCotizacion(accion.getUltimaCotizacion());
				accionActual.validar();
			}
			
			throw new ServiceError("Accion no encontrada");*/
		} catch(Error e) {
			throw new ServiceError(e.getMessage());
		}

	}

	@Override
	public Accion readById(String id) {
		// TODO Auto-generated method stub
		System.out.println("Read by ID de accion service");
		
		/*Accion accion = this.acciones
				.stream().filter(a -> a.getSimbolo().equals(id))
				.findFirst()
				.orElse( null);
		
		if (accion == null) {
			throw new ServiceError("No existe la accion de simbolo " + id);
		}
		
		*/
		
		Accion accion = this.repository
				.findById(id)
				.orElseThrow( () -> (new ServiceError("No existe la accion de simbolo " + id)));
		

		return accion;
	}

	@Override
	public List<Accion> readAll() {
		// TODO Auto-generated method stub
		//System.out.println("Read all de accion service");
		//return this.acciones;
		try {
			return this.repository.findAll();
		}catch(Exception e) {
			throw new ServiceError(e.getMessage());
		}
	}

}
