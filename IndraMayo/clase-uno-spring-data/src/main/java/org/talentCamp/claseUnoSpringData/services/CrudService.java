package org.talentCamp.claseUnoSpringData.services;

import org.talentCamp.claseUnoSpringData.dto.Dto;

import java.util.*;

//Layer supertype de servicios
public interface CrudService<T extends Dto, ID> {
    List<T> recuperarTodos();
    T recuperarPorId(ID id);
    T registrarNuevo(T nuevo);
    T actualizar(T existente);
    void borrar(ID id);
}
