package org.indra.claseDiez;

public class MockRepository implements Repository{

	@Override
	public void save() {
		System.out.println("Se guarda en memoria");
	}

}
