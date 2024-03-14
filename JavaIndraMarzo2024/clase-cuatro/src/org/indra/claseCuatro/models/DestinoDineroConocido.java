package org.indra.claseCuatro.models;

public class DestinoDineroConocido {
	
	private static Cajero _cajero = new Cajero();

	public static Cajero cajero(){
		return _cajero;
		
	}
	
	//Podria agrear otros destinos conocidos
	//Ej: Servicio, pago de un servicio
}
