package org.indra.claseOcho;

import java.io.File;
import java.lang.reflect.*;

import org.indra.claseOcho.models.*;

public class Program {
	
	public static void main(String[] args) throws Exception {
			//Reflection
		// 1. Tener una referencia a una clase y crear objetos a partir de la misma
		// 2. Crear una clase a partir de un String
		// 3. Listar los atributos de una clase / objeto
		// 4. Listar los metodos de una clase
		// 5. Invocar un metodo a partir de un string
		// 6. Listar las clases que hay dentro de un paquete
	
		// 1. Tener una referencia a una clase y crear objetos a partir de la misma
		//Class clase = Car.class;
		//Class clase = Movil.class;
		Class<?> clase = Movil.class;
		//Class<?> clase2 = Movil.class;
		
		//Antes de Java 9
		//Object o = clase.newInstance();
		
		//En java 9
		Object o = clase.getDeclaredConstructors()[0].newInstance();
		
		System.out.println("Creando un objeto a partir de una variable de tipo Class");
		System.out.println(o);
		System.out.println();
		
		//2. Crear una clase a partir de un String
		System.out.println("Creando una clase a partir de un string");
		//Object o = Class.forName("Car").newInstance(); Pre java 9
		//Object o2 = Class.forName("Car").getDeclaredConstructors()[0].newInstance(); //java.lang.ClassNotFoundException:
		Object o2 = Class.forName("org.indra.claseOcho.models.Car").getDeclaredConstructors()[0].newInstance(); //java.lang.ClassNotFoundException:
		System.out.println(o2);
		System.out.println();
		
		//3. Listar los atributos de una clase
		System.out.println("Vamos a listar los campos(atributos) de la clase Car");
		for (Field campo : Car.class.getDeclaredFields() ) {
			System.out.println(campo.getName() + " : " + campo.getType().getName());
		}
		System.out.println();
		
		//4. Listar los metodos de una clase
		System.out.println("Vamos a listar los metodos de la clase File");
		for (Method metodo : File.class.getDeclaredMethods()) {
			System.out.println(metodo.getName());
		}
		System.out.println();
		
		//4.1. Listar los metodos del objeto c . (Se animan)
		Car c = new Car();
        System.out.println("Vamos a listar los metodos del objeto Car");
        for (Method metodo: c.getClass().getDeclaredMethods()) {
            System.out.println(metodo.getName());
        }
        System.out.println();
        
        //5. Invocar un metodo a partide un string
        String texto = (String)c.getClass().getDeclaredMethod("toString", null).invoke(c, (Object[])null);
        System.out.println("Invocamos el toString a partir de un string");
        System.out.println(texto);
        System.out.println();
		
						
	}


}
