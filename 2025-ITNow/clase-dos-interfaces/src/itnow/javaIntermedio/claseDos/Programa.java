package itnow.javaIntermedio.claseDos;

import java.util.List;

public class Programa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Bienvenidos al mundo de las Interfaces!");
		
		//Ejemplo 1: Uso directo
	    //   Las variables estan en el stack y aputan al heap
		//   Cada vez que declaro una variable que no es de un tipo de dato primitivo es un puntero al heap
		System.out.println("Ejemplo de uso directo de clases concretas (sin interfaz)");
		System.out.println("---------------------------------------------------------");
		FichaDeLuz luzDirecta = new FichaDeLuz();
		luzDirecta.encender();
		luzDirecta.apagar();
		
		Radio radioDirecta = new Radio();
		radioDirecta.encender();
		radioDirecta.apagar();
		
		//Ejemplo 2 : Uso de Interfaces
		System.out.println("");
		System.out.println("Ejemplo de Interfaces");
		System.out.println("---------------------");
		Operable operable = new Radio();
		operable.encender();
		operable.apagar();
		
		Operable otroOperable = new FichaDeLuz();
		otroOperable.encender();
		otroOperable.apagar();
		
		
		//Ejemplo 3: Casteos
		System.out.println("");
		System.out.println("Cuando una variable de tipo interfaz apunta a una clase concreta...");
		System.out.println("-------------------------------------------------------------------");
		Operable casio = new Radio();
		//casio.cambiarEstacion();  //NO me lo autocompleta. El metodo no aparece. No compila
		
		if (casio instanceof Radio) {  //Operador instanceof para preguntar si una variable pertenece a una instanca
			//Lo tengo que castear
			((Radio) casio).cambiarEstacion();  //Castinc o casteo.
		}
		
		System.out.println("Una variable de tipo interfaz puede apuntar a cualquier clase que la implementa");
		casio = new FichaDeLuz();  //Que paso con radio anterior : tengo un objeto inaccesible.
		                           //Va a quedar en el heap hasta que el garbage Collecto la reclame y libere la memoria
		try {
			((Radio)casio).cambiarEstacion(); //Que pasa con esto?
		}catch(ClassCastException e) {
			System.out.println("Ups... me salio mal el casteo");
		}

		//Ejemplo 4: Algo de polimorfismo
		System.out.println("");
		System.out.println("Vamos a ver algo de polimorfismo ...");
		System.out.println("------------------------------------");
		
		ControRemotoUniversal control = new ControRemotoUniversal(radioDirecta);
		control.probarDispositivo();
		
		//Ejemplo 5: Uso de Listas
		System.out.println("");
		System.out.println("Ahora con listas, puedo crear una lista de operables ...");
		System.out.println("--------------------------------------------------------");
		//Vamos a apagar todo
		List<Operable> lista = List.of(radioDirecta, luzDirecta, casio, operable, otroOperable); //Creamos una lista inmutable
		for (Operable o : lista) {
			o.apagar();
		}
		try {
			lista.add(otroOperable);
		}catch (Exception e) {
			System.out.println("Ups... es una lista inmutable, no se le puede agregar nada");
		}
		
		//Ejemplo 6: Bonus track. Clases Anonimas
		System.out.println("");
		System.out.println("Clases Anonimas ...");
		System.out.println("-------------------");
		Operable anonimo = new Operable() {

			@Override
			public void encender() {
				System.out.println("Soy algo que se enciende");
			}

			@Override
			public void apagar() {
				System.out.println("Soy algo que se apaga");
			}
		};
		anonimo.encender();
		anonimo.apagar();
	}

}
