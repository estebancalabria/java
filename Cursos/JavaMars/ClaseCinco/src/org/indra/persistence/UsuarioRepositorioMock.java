package org.indra.persistence;


import java.util.*;
import org.indra.model.Usuario;

public class UsuarioRepositorioMock implements IUsuarioRepositorio{
	
	List<Usuario> usuarios = new ArrayList<Usuario>(){{
		new Usuario("alice");
		new Usuario("bob");	
		new Usuario("charles");	
	}};
	
	public Usuario buscarPorNombre(String nombre) {
		//simulo que voy a la base de datos
		Usuario result = null;
		for (Usuario actual : this.usuarios) {
			if (actual.getNombre().equals(nombre)) {
				result = actual;
			}
		}
		return result;
	}
	
	public void add(Usuario usuario) {
		//Simulo que lo agrego en la base de datos
		this.usuarios.add(usuario);
	}

	@Override
	public List<Usuario> bucarTodos() {
		// TODO Auto-generated method stub
		return this.usuarios;
	}
}
