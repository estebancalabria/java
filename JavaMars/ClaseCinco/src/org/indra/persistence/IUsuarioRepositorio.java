package org.indra.persistence;

import java.util.List;
import org.indra.model.Usuario;

public interface IUsuarioRepositorio {

	public Usuario buscarPorNombre(String nombre);
	public List<Usuario> bucarTodos();
	public void add(Usuario usuario);
	//...
}
