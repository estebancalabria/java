package org.indra.lambda;

public class Automovil {
	private double velocidad;

	public Automovil(double velocidad) {
		super();
		this.velocidad = velocidad;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Soy un auto que viajo a "  + this.velocidad;
	}

	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}
}
