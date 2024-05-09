package org.talentCamp.claseUnoSpringData.services;

import java.util.*;

//Layer supertype de servicios
public interface CrudService<T, ID> {
    List<T> recuperarTodos();
    T recuperarPorId(ID id);
    T registrarNuevo(T nuevo);
    T actualizar(T existente);
    void borrar(ID id);
}
