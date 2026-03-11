package org.indra.patterns.templateMethod.sample;

public abstract class RecetaDeCocina {
	
	protected abstract void listarIngredientes();
	protected abstract void prepararReceta();
	protected abstract void cocinarPreparacion();
	protected abstract void servirPreparacion();
	
	public final void ejecutar() {
		System.out.println("Los Ingredientes son:");
		this.listarIngredientes();
		System.out.println();
		
		System.out.println("Para prearar hay que:");
		this.prepararReceta();
		System.out.println();
		
		System.out.println("Cocine la preparacion asi:");
		this.cocinarPreparacion();
		System.out.println();
		
		System.out.println("Finalmente sira asi:");
		this.servirPreparacion();
		System.out.println();
	}
}
