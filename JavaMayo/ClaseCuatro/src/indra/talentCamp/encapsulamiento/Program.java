package indra.talentCamp.encapsulamiento;

import indra.talentCamp.encapsulamiento.models.*;
import indra.talentCamp.utils.Console;

public class Program {

	public static void main(String[] args) {
		//Scanner in = new Scanner(System.in);
		//try {
			//Student padawan = new Student();
	        System.out.println("Bienvenidos al Talent Camp de Java");
        
	        //System.out.println("Como es su nombre?");
	        //String nombre = in.nextLine();
	        //padawan.name = in.nextLine();
	        //padawan.setName(in.nextLine());
	        //padawan.setName( Console.readString("Como es su nombre?"));
	        
	        //System.out.println("Cual es su documento?");
	        //int documento = Integer.parseInt(in.nextLine());
	        //padawan.setDocument(Integer.parseInt(in.nextLine()));
	        //padawan.setDocument( Console.readInt("Cual es su documento?"));
	        
	        //System.out.println("Cual es su direccion?");
	        //String direccion = in.nextLine();
	        //padawan.address  = in.nextLine();
	        //padawan.setAddress(in.nextLine());
	        //padawan.setAddress( Console.readString("Cual es su direccion?"));
	        
	        //System.out.println("Te gusta el front? (S/N)");
	        // boolean prefiereFront = (respuesta.equalsIgnoreCase("S"));
	        //padawan.prefersFrontEnd = (respuesta.equalsIgnoreCase("S"));
	        
	        //Ahora estoy obligado a usar mi nuevo constructor
	        System.out.println("Quien va a dictar el curso?");
	        Teacher jedi = new Teacher(
	        		Console.readString("Como es su nombre?"),
	        		Console.readInt("Cual es su documento?"),
	        		Console.readString("Cual es su direccion?"));

	        
	        System.out.println("Quien va a asistir al curso?");
	        Student padawan = new Student(
	        		Console.readString("Como es su nombre?"),
	        		Console.readInt("Cual es su documento?"),
	        		Console.readString("Cual es su direccion?"));
	        
	        String respuesta = Console.readString("Te gusta el front? (S/N)");
	        padawan.setPrefersFrontEnd(respuesta.equalsIgnoreCase("S"));
	        
	        //System.out.println("Bienvenido " + padawan.getName() + " al Talent Camp de Java");
	        //System.out.println("Su documento es " + padawan.getDocument() + " y vive en " + padawan.getAddress());
	        // if (padawan.getPrefersFrontEnd()) {
	        //	System.out.println("Esperemos que luego se le asigne un proyecto de front");
	        //}else {
	       // 	System.out.println("Esperemos que luego se le asigne un proyecto de backend");
	       // }	   
	        System.out.println("-------------------------------------------");
	        System.out.println("El Profesor es:");
	        System.out.println(jedi);
	        System.out.println("Asisten al curso:");
	        System.out.println(padawan);
		//}finally {
		//	in.close();
		//}
	}

}
