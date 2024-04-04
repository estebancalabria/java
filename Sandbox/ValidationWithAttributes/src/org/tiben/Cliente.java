package org.tiben;

public class Cliente extends ObjetoDeNegocio {
	@Requerido
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
