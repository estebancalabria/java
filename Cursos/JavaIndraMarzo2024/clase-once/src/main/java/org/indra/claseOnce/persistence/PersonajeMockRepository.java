package org.indra.claseOnce.persistence;

import java.util.HashMap;
import java.util.Map;

import org.indra.claseOnce.models.*;

public class PersonajeMockRepository implements Repository<Personaje> {
    private Map<Integer, Personaje> personajes;

    public PersonajeMockRepository() {
        personajes = new HashMap<>();
    }

    @Override
    public void save(Personaje personaje) {
    	if (personaje.getId()==0) {
    		int id = personajes.keySet().stream().mapToInt(p -> p).max().orElse(0) + 1;
    		personaje.setId(id);
    	}
        personajes.put(personaje.getId(), personaje);
        System.out.println("Se salva con el mock");
    }

    @Override
    public Personaje findById(int id) {
        return personajes.get(id);
    }
}
