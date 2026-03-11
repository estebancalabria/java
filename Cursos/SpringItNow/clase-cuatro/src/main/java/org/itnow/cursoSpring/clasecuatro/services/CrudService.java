package org.itnow.cursoSpring.clasecuatro.services;

import java.util.*;

import org.itnow.cursoSpring.clasecuatro.models.Automovil;

public interface CrudService<T> {
    public void registrarNuevo(T nuevo) throws CrudException;
    public void actualizarInformacion(T existente) throws CrudException;
    public void darDeBaja(int id) throws CrudException;
    public List<T> obtenerTodos() throws CrudException;
    public T obtenerPorId(int id) throws CrudException;
}
