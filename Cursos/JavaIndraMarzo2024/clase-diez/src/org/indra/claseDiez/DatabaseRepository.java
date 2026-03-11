package org.indra.claseDiez;

public class DatabaseRepository implements Repository {

	@Override
	public void save() {
		System.out.println("Se salva en la base de datos");		
	}

}
