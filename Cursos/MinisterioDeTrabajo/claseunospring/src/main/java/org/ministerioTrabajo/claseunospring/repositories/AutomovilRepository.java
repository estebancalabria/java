package org.ministerioTrabajo.claseunospring.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.ministerioTrabajo.claseunospring.models.Automovil;
import org.ministerioTrabajo.claseunospring.services.NoEncontradoException;
import org.springframework.stereotype.Repository;

@Repository
public class AutomovilRepository implements GenericRepository<Automovil, Integer>{
	
    private List<Automovil> automoviles = new ArrayList<>(Arrays.asList(
            new Automovil(1,"ABC123", "Toyota Corolla"),
            new Automovil(2,"XYZ456", "Ford Fiesta"),
            new Automovil(3,"LMN789", "Chevrolet Spark")
    ));

	@Override
	public Automovil save(Automovil entity) {
		// TODO Auto-generated method stub
        if (entity.getId() == 0) {
            entity.setId(this.automoviles.stream().map(a -> a.getId()).max(Integer::compare).orElse(0) + 1);
            this.automoviles.add(entity);
        }
        
        return entity;
	}

	@Override
	public Optional<Automovil> findById(Integer id) {
	    return this.automoviles.stream().filter(a -> a.getId() == id).findFirst();
	}

	@Override
	public void deleteById(Integer id) {
        if (!this.automoviles.removeIf(a -> a.getId() == id)) {
            throw new NoEncontradoException("No se puede eliminar porque no existe automovil con id " + id);
        }
	}

	@Override
	public List<Automovil> findAll() {
		// TODO Auto-generated method stub
		  return this.automoviles;
	}

}
