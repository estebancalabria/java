package com.indra.clasediecideis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import com.indra.clasediecideis.models.Cancion;
import com.indra.clasediecideis.repositories.CancionRepository;

@Service
public class CancionService implements ICancionService {

	@Autowired
	private CancionRepository repository;
	
	public List<Cancion> getAll() {
		// TODO Auto-generated method stub
		return this.repository.findAll();
	}

	@Override
	public Cancion getById(int id) throws Exception {
		return this.repository.findById(id).get();
	}

	@Override
	public Cancion add(Cancion cancion) throws Exception {
		// TODO Auto-generated method stub
		if ((cancion.getTitulo()==null) || cancion.getTitulo().length()==0) {
			throw new Exception("Falta el titulo de la cancion");
		}
		
		if ((cancion.getAutor()==null) || cancion.getAutor().length()==0) {
			throw new Exception("Falta el autor de la cancion");
		}
		
		if ((cancion.getDisco()==null) || cancion.getDisco().length()==0) {
			throw new Exception("Falta el disco de la cancion");
		}
		
		if (cancion.getSegundos() < 15) {
			throw new Exception("La cancion no puede ocupar menos de 15 segundos");			
		}
		
		this.repository.save(cancion);
		
		return cancion;
	}

}
