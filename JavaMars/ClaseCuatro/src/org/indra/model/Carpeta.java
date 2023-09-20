package org.indra.model;

import java.util.*;

public class Carpeta extends EntidadSistemaFicheros {

	public Carpeta(String nombre) {
		super(nombre);
		if (miradoPor!=null) miradoPor.notificar("Creando carpeta "+nombre);
	}

	private List<EntidadSistemaFicheros> hijos = new ArrayList<EntidadSistemaFicheros>();
	
	public void Add(EntidadSistemaFicheros hijo) {
		if (!hijos.contains(hijo)) {
			if (miradoPor!=null) miradoPor.notificar("Agregando "+ hijo.getNombre() +" a la carpeta "+ this.getNombre());
			this.hijos.add(hijo);			
		}
	}

	@Override
	public int calcularTama�o() {
        int tama�o = 0;
        for (EntidadSistemaFicheros hijo: hijos) {
            tama�o += hijo.calcularTama�o();
        }
        return tama�o;
	}
}
