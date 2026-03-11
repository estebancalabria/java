package org.indra.patterns.templateMethod.sample;

public class Pizza extends RecetaDeCocina {

	@Override
	protected void listarIngredientes() {
		System.out.println("Harina");
		System.out.println("Sal");
		System.out.println("Agua");
		System.out.println("Queso");
		
	}

	@Override
	protected void prepararReceta() {
		// TODO Auto-generated method stub
		System.out.println("Amasar la harina con el agua y sal");
		System.out.println("Dejar Reposar");
	}

	@Override
	protected void cocinarPreparacion() {
		// TODO Auto-generated method stub
		System.out.println("Cocinar al horno 10 minutos con queso arriba");
		
	}

	@Override
	protected void servirPreparacion() {
		// TODO Auto-generated method stub
		System.out.println("Servir en un bandeja redonda inmediatamwnte");
		
	}

}
