package org.indra.vista;

import java.lang.reflect.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

import javax.swing.*;
import org.indra.models.*;

public class Programa {

	public static void main(String[] args) throws Exception {
		
		//Ejemplo 1 : Utilizando el Consumer
		/*List<String> lista = Arrays.asList("Calculadora","Espacio","Nota","Garabato","Cielo");
		Consumer<String> metodoLambda = (n) -> { System.out.println("--" + n + "--");};
		lista.forEach( metodoLambda);*/
		
		//Ejemplo 2 : Interfaces funcionaes como remplazo del addEventListener
		/*JButton boton = new JButton("Haceme Click");
		boton.setBounds(1,1,300,50);
		boton.addActionListener( (e)-> { JOptionPane.showConfirmDialog(boton, "Hiciste Click a un lamda"); } );
		
		JFrame f = new JFrame();
		f.add(boton);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(null);
		f.setSize(640,480);
		f.setVisible(true);*/
		
		//Ejemplo 3 : Interfaz funcional propia
		/*Animal garfield = new Gato();
		garfield.mostar((a)->{ System.out.println("Soy un animal que hace " + a.getSonido());});*/
		
		/*IDibujador porCosola = (Animal a) -> {System.out.println("Soy un "+ a.getClass().getSimpleName() +" que hace " + a.getSonido());};
		Animal pluto = new Perro();
		Animal garfield = new Gato();
		pluto.mostar(porCosola);
		garfield.mostar(porCosola);*/
		
		/*IDibujador porConsola = (Animal a) -> {System.out.println("Soy un "+ a.getClass().getSimpleName() +" que hace " + a.getSonido());};
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Que animal queres crear");
			String animal = sc.nextLine();
			Class creador = Class.forName("org.indra.models." + animal);
			Animal a = (Animal)creador.newInstance();
			a.mostar(porConsola);
		}*/
		
		/*List<String> lista = Arrays.asList("a","b","c","d");
		lista.forEach(System.out::println);   //lista.forEach(a -> System.out.println(a));*/
		
		//Listar los metodos por reflection
		/*ArrayList<String> lista = new ArrayList<String>();
		Class claseArrayList = lista.getClass();
		Stream<Method> metodos = Arrays.stream(claseArrayList.getMethods());
		metodos.forEach( m-> System.out.println(m));*/

        /*String string = new String("Hola que tal");
        Class claseString = string.getClass();
        Stream<Method> metodosString = Arrays.stream(claseString.getMethods());
        Method metodoLength = metodosString.filter(m -> m.getName().equals("length")).findFirst().get();
        int size = (Integer)metodoLength.invoke(string);
        System.out.println(size);*/
		
		/*String str = new String("Hola que tal");
        Class claseString = str.getClass();
        Stream<Method> metodos = Arrays.stream(claseString.getMethods()); 
        Method methodContains = metodos.filter( m -> m.getName().equals("contains")).findFirst().get();
        boolean contiene = (Boolean) methodContains.invoke(str, "que");
        System.out.println(contiene?"Si lo contiene":"No lo contiene");*/
		
		
        
		
	}

}
