package org.indra.view;

import java.util.List;

import org.indra.dto.RegistroUsuarioDTO;
import org.indra.model.Usuario;
import org.indra.persistence.*;
import org.indra.services.*;

public class Progama {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Bienvenidos a Indragram.");
		
		IUsuarioRepositorio repo = new UsuarioRepositorioDb();
		Usuario nuevo = new Usuario("Pedro");
		repo.add(nuevo);
		//List<Usuario> todos = repo.bucarTodos();
		//todos.forEach( usr -> System.out.println(usr.getNombre()) );
		
		/*UsuarioRepositorioMock repo = new UsuarioRepositorioMock();
		UsuarioService usuarioService = new UsuarioService(repo);
		System.out.println("Voy a probar registrar un usuario nuevo");
		
		RegistroUsuarioDTO registro = new RegistroUsuarioDTO();
		registro.setNombreDeseado("juan");
		
		try {
			usuarioService.registrarUsuarioNuevo(registro);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("Esto no deberia pasar");
		}
		
		System.out.println("Usuario Registrado Satisfactoriamente");
		
		//------------------------------------------------
		System.out.println("\nVoy a probar registrar un usuario duplicado");
		RegistroUsuarioDTO duplicado = new RegistroUsuarioDTO();
		duplicado.setNombreDeseado("juan");
		try {
			usuarioService.registrarUsuarioNuevo(duplicado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Deberia dar un error de usuario duplicado");
			System.out.println(e.getMessage());
		}
		
		//-------------------------------------------------
		System.out.println("\nVoy a probar registrar un usuario vacio");
		RegistroUsuarioDTO vacio = new RegistroUsuarioDTO();
		vacio.setNombreDeseado("");
		try {
			usuarioService.registrarUsuarioNuevo(vacio);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Deberia dar un error de usuario vacio o muy corto");
			System.out.println(e.getMessage());
		}*/
		
	}

}
