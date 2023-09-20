package org.indra.services;

import org.indra.dto.*;
import org.indra.model.*;
import org.indra.persistence.*;

public class UsuarioService {
	
	private IUsuarioRepositorio repo;
	
	public UsuarioService(IUsuarioRepositorio repo) {
		//this.repo = new UsuarioRepositorio();
		this.repo = repo;
	}
   
	public Usuario registrarUsuarioNuevo(RegistroUsuarioDTO registro) throws Exception {

		//-Validar que no haya otro igual
		if (this.repo.buscarPorNombre(registro.getNombreDeseado()) != null) {
			throw new Exception("Usuario ya existe");
		}		
		
		Usuario nuevo = new Usuario(registro.getNombreDeseado());
	    //-Validar que el nombre no este vacio y sea correcto
		nuevo.validar();
		
		//-Agregar el usuario en la base de datos
		this.repo.add(nuevo);
		return nuevo;
	}
}
