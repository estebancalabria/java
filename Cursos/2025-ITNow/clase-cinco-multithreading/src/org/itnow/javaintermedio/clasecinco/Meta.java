package org.itnow.javaintermedio.clasecinco;

public class Meta {
	private boolean _hayGanador = false;
	
	//Los metodos sybchronized se pueden llamar desde varios threads distintos
	//El Sistema en operativo toma los recaudos necesarios para que no haya bloqueos entre threads
	public synchronized boolean hayGanador() {
		return this._hayGanador;
	}
	
	public synchronized void cruzar(String nombre) {
		if (!this._hayGanador) {
			this._hayGanador = true;
			System.out.println(nombre + " ha ganado la carrera");
		}
	}
}
