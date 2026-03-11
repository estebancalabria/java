package org.indra.patterns.templateMethod.sample;

public class ArrozConPollo extends RecetaDeCocina{

	@Override
	protected void listarIngredientes() {
        System.out.println("Pollo");
        System.out.println("Arroz");
        System.out.println("Verduras");		
	}

	@Override
	protected void prepararReceta() {
        System.out.println("Cortar verduras");
        System.out.println("Cortar pollo");		
	}

	@Override
	protected void cocinarPreparacion() {
        System.out.println("Sofreir verduras");
        System.out.println("Añadir pollo");
        System.out.println("Cocer arroz");
        System.out.println("Juntar todo");		
	}

	@Override
	protected void servirPreparacion() {
        System.out.println("Emplatar inmediatamente");		
	}

}
