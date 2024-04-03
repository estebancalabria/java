package org.indra.claseTrece.services;

import java.util.List;
import org.indra.claseTrece.models.*;
import org.springframework.stereotype.Service;

@Service
public class AccionService implements CrudService<String, Accion> {

	@Override
	public void create(Accion model) {
		System.out.println("Create de Accion Service");
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		System.out.println("Delete de accion service");
		
	}

	@Override
	public void update(String id, Accion model) {
		// TODO Auto-generated method stub
		System.out.println("Update de accion service");
	}

	@Override
	public Accion readById(String id) {
		// TODO Auto-generated method stub
		System.out.println("Read by ID de accion service");
		return null;
	}

	@Override
	public List<Accion> readAll() {
		// TODO Auto-generated method stub
		System.out.println("Read all de accion service");
		return null;
	}

}
