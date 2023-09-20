package org.indra.model;

public class Archivo extends EntidadSistemaFicheros {
	
	private int tama�o;
	
	public Archivo(String nombre, int tama�o) {
		super(nombre);
		this.tama�o = tama�o;
		if (miradoPor!=null) miradoPor.notificar("Creando archivo "+nombre+" con "+tama�o+" bytes.");
	}
	
	public Archivo(String nombre) {
		super(nombre);
		this.tama�o = 0;
		if (miradoPor!=null) miradoPor.notificar("Creando archivo "+nombre+" vacio");
	}

	public int getTama�o() {
		return tama�o;
	}

	public void setTama�o(int tama�o) {
		this.tama�o = tama�o;
	}

	@Override
	public int calcularTama�o() {
		return this.tama�o;
	}	
}
