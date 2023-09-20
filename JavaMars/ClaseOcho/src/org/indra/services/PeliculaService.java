package org.indra.services;

import java.util.List;

import org.indra.models.*;
import org.indra.repository.IPeliculaRepository;
import org.indra.repository.PeliculaRepositoryFactory;

public class PeliculaService {
	
	IPeliculaRepository repo;
	
	public PeliculaService() throws Exception{
		this.repo = PeliculaRepositoryFactory.getInstance().createRepository();
	}
	
	public void add(Pelicula pelicula) throws Exception {
		//Faltaria las reglas de negocio
		//Faltaria la validacion de la pelicula por ejemplo
		repo.add(pelicula); //Ejemplo de uso del repositorio
	}
	
    public Pelicula getById(int id) throws Exception {       
    	//podriamos poner por ejemplo un manejo de errores
        return this.repo.findById(id);       
    }
    
    public Pelicula getByName(String name) throws Exception {
        return this.repo.findByName(name);
    }
    
    public void update(Pelicula pelicula) throws Exception {
        // Faltaria las reglas de negocio
        // Faltaria la validacion de la pelicula por ejemplo
        repo.update(pelicula); // Ejemplo de uso del repositorio
    }
    
    public List<Pelicula> getAll() throws Exception {
        return repo.findAll();
    }
    
    public void delete(int id) throws Exception {
        this.repo.delete(id);
    }
    
    
}
