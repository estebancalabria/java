package org.indra.model;

public class Usuario extends ObjetoDeNegocio {

	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public Usuario(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	@Override
	public void validar() throws Exception {
		// TODO Auto-generated method stub
		super.validar();
		
		if ((nombre==null) || (nombre.length()<3)) {
			throw new Exception("El nombre esta vacio o es demasiado corto");
		}
	}
}
