package org.talentCamp.claseUnoSpringData.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.talentCamp.claseUnoSpringData.models.Cancion;
import org.talentCamp.claseUnoSpringData.repositories.CancionRepository;

import java.util.List;

@Service
public class CancionService implements CrudService<Cancion, Integer>{

    @Autowired
    private CancionRepository repo;

    @Override
    public List<Cancion> recuperarTodos() {
        return this.repo.findAll();
    }

    @Override
    public Cancion recuperarPorId(Integer id) {
        return this.repo.findById(id).orElseThrow();
    }

    @Override
    public Cancion registrarNuevo(Cancion nuevo) {
        this.repo.save(nuevo);
        return  nuevo;
    }

    @Override
    public Cancion actualizar(Cancion existente) {
        this.repo.save(existente);
        return existente;
    }

    @Override
    public void borrar(Integer id) {
        this.repo.deleteById(id);
    }
}
