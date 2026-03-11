package org.itnow.javaintermedio.clasecinco;

public class AnimalThread extends Thread {

	private String nombre;
	private int tiempoEspera;
	private Meta meta;
	
	public AnimalThread(String nombre, int tiempoEspera, Meta meta) {
		super();
		this.nombre = nombre;
		this.tiempoEspera = tiempoEspera;
		this.meta = meta;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i=0; i<10; i++) {
			if (meta.hayGanador()) {
				System.out.println(nombre + " pierde la carrera");
				return;
			}
			
			System.out.println(this.nombre + " da un paso " + i);
			
			try {
				Thread.sleep(this.tiempoEspera);
			}catch (InterruptedException e) {
				System.out.println(nombre + "Se tropezo!");
			}
		}
		
		this.meta.cruzar(this.nombre);
		System.out.println(this.nombre + " llego a la meta");
	}

}
