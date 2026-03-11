package org.inda.main;

public class Programa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Automovil auto = new Automovil("Lamborghini", "Murcielago");
		auto.setId(1);
		//auto.setMarca();
		//auto.setModelo();
		auto.setKilometros(0);
		System.out.println(auto);
		
		Reloj reloj = Reloj.builder().marca("Casio").modelo("XRS100").build();
		System.out.println(reloj);
		reloj.salvar();
		
		Persona persona = new Persona("Juan","Perez");
		System.out.println(persona);
	}

}
