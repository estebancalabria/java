package org.allianz.clasecincospringboot.services;

import java.util.*;
import java.util.stream.Collectors;

import org.allianz.clasecincospringboot.models.*;
import org.allianz.clasecincospringboot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImplementation implements ClienteService {

	@Autowired
	private ClienteMemoryRepository repository;

	/*public ClienteServiceImplementation(ClienteRepository repo) {
		this.repository = repo;
	}*/

	@Override
	public void registrarNuevoCliente(Cliente cliente) throws ServiceException {
		// TODO Auto-generated method stub
		if ((cliente.getNombre() == null) || (cliente.getNombre().trim().length() == 0)) {
			throw new ValidationException("Cliente: El nombre no puede quedar vacio");
		}

		if (cliente.getApellido() == null || cliente.getApellido().trim() == "") {
			throw new ValidationException("Cliente: El apellido no puede quedar vac�o");
		}

		if (cliente.getDocumento() <= 0) {
			throw new ValidationException("Cliente: El documento debe ser un numero positivo");
		}

		// Escribo de nuevo la expresion lambda
		if (this.existeCliente(cliente.getDocumento())) {
			throw new ServiceException(
					String.format("Ya existe el cliente con el documento %d", cliente.getDocumento()));
		}

		this.repository.insert(cliente);
	}

	@Override
	public Cliente obtenerClientePorDocumento(int documento) throws ServiceException {
		// supongo que no puedo modificar el repositorio
		List<Cliente> clientes = this.repository.findAll();
		return clientes.stream().filter(c -> c.getDocumento() == documento).findFirst()
				.orElseThrow(() -> new ServiceException(
						String.format("No existe cliente registrado con el documento %d", documento)));
		/*
		 * Cliente c = clientes .stream() .filter(c -> c.getDocumento()==documento)
		 * .findFirst(); if (c==null ) {new
		 * ServiceException("No existe cliente registrado con ese id")}
		 */
	}

	@Override
	public boolean existeCliente(int documento) throws ServiceException {
		return this.repository.findAll().stream().anyMatch(c -> c.getDocumento() == documento);
	}

	@Override
	public List<Cliente> obtenerClientesPorApellido(String apellido) {
		// TODO Auto-generated method stub
		// this.repository.findAll().stream().filter(null).toList();
		return this.repository.findAll().stream().filter(c -> c.getApellido().equals(apellido))
				.collect(Collectors.toList());

	}

	@Override
	public List<Cliente> obtenerTodos() {
		return this.repository.findAll();
	}

}
