package org.allianz.javaAvanzado.claseUno.services;

import java.util.List;

import org.allianz.javaAvanzado.claseUno.models.Cliente;

public interface ClienteService {
	public void registrarNuevoCliente(Cliente cliente) throws ServiceException;
	public Cliente obtenerClientePorDocumento(int documento) throws ServiceException;
	public boolean existeCliente(int documento)  throws ServiceException;
	public List<Cliente> obtenerClientesPorApellido(String string);
	
}
