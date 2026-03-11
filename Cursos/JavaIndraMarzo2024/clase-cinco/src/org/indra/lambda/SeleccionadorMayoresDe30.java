package org.indra.lambda;

public class SeleccionadorMayoresDe30 implements SeleccionadorPersona {

	@Override
	public boolean elegir(Persona p) {
		// TODO Auto-generated method stub
		return p.getEdad()>30;
	}

}
