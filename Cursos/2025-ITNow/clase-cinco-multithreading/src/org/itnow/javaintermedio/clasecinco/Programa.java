package org.itnow.javaintermedio.clasecinco;

public class Programa {

	public static void main(String[] args) {
		System.out.println("Carrera de Animales!");
		
		Meta meta = new Meta();

		AnimalThread liebre = new AnimalThread("Liebre", 200, meta);
		AnimalThread tortuga = new AnimalThread("Tortuga", 500, meta);
		AnimalThread canguro = new AnimalThread("Canguro", 300, meta);
		
		liebre.start();
		tortuga.start();
		canguro.start();
	}

}
