package org.allianz.clasecincospringboot.services;

import java.util.List;

import org.allianz.clasecincospringboot.models.Cliente;

public interface ClienteService {
	public void registrarNuevoCliente(Cliente cliente) throws ServiceException;
	public Cliente obtenerClientePorDocumento(int documento) throws ServiceException;
	public boolean existeCliente(int documento)  throws ServiceException;
	public List<Cliente> obtenerClientesPorApellido(String string);
	public List<Cliente> obtenerTodos() throws ServiceException;
	
}
