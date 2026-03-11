package org.indra.patterns.templateMethod.sample;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RecetaDeCocina receta = new Pizza();
		receta.ejecutar();
		
		RecetaDeCocina otraRecetea = new ArrozConPollo();
		otraRecetea.ejecutar();
	}

}
