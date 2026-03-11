package org.itnow.javaintermedio.proyectofinal.models;

public class ObjetoDeNegocio {
	//Representacion interna del objeto
	protected Integer id = null;    //De Donde Viene ahora este id?
	
	public Integer getId() {
		return id;
	}
	
	public void asignarIdDesdeBase(Integer idBase) {
		this.id = idBase;
	}
}
