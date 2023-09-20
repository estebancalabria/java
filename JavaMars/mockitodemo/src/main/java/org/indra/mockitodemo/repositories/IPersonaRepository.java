package org.indra.mockitodemo.repositories;

import org.indra.mockitodemo.models.Persona;
import java.util.*;

public interface IPersonaRepository {
    public void add(Persona p);
    public void update(Persona p);
    public Persona get(int id);
    public List<Persona> getAll();
    public void delete(int id);
}
