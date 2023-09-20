package courses.java.talentCamp;

import java.util.ArrayList;
import java.util.List;

//import java.util.*;

public class Program {
	
	public static void cambiarValor(int n) {
		n = 20;
	}
	
	public static void cambiarRectangulo(Rectangulo rec) {
		rec.base = 30;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Bienvenidos a la Clase Dos");
		
		int number = 10;
		int b = 20;
		
		number = b;
		
		System.out.println(number);
		
		Rectangulo r = new Rectangulo();
		r.base = 10;
		r.altura = 20;
	    System.out.println(r.base);
	    
	    Rectangulo r2 = null;
	    //System.out.println(r2.altura); <<< NullPointerException
	    r2 = new Rectangulo();
	    r2.altura = 40;
	    r2.base = 100;
	    System.out.println(r2.altura);
	    
	    //Atenti!!!
	    r = r2;
	    r2.base = 444;
	    System.out.println(r.base); //<<< Que valor muestra por pantalla?
	    
	    int a = 555;
	    cambiarValor(a);
	    System.out.println(a);
	    
	    r.base = 888;
	    cambiarRectangulo(r);
	    System.out.println(r.base);
	    
	    //Ctrl+Shift+o sirve para autocompletar los import si la ide no lo sugiere
	    List<String> jedisDeJava = new ArrayList<String>();
	    jedisDeJava.add("Marcos");
	    jedisDeJava.add("Javier");
	    jedisDeJava.add("Borja");
	    jedisDeJava.add("Angello");
	    jedisDeJava.add("Jean");
	    
	    //Ojo, no es el que se usa
	    String jediActual = null; //El string es un tipo de dato especial, parece primitivo pero es de tipo de referencia
	                              //Aca vemos que apunta anull
	    System.out.println("Bienevenidos a la academia de Talent Camp queridos padawan:");
	    for (int i=0; i<jedisDeJava.size(); i++) {
	    	jediActual = jedisDeJava.get(i);
	    	System.out.println(jediActual);
	    }
	    
	    
	}

}
