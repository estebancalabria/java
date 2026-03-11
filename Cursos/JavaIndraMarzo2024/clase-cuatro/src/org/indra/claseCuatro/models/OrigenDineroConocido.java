package org.indra.claseCuatro.models;

public class OrigenDineroConocido {
	
	private static Cajero _cajero = new Cajero();

	public static Cajero cajero(){
		return _cajero;
		
	}
}
