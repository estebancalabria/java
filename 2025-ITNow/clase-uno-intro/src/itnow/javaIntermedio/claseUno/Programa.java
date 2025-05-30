package itnow.javaIntermedio.claseUno;

import java.util.ArrayList;
import java.util.List;

public class Programa {

	public static void main(String[] args) {
		try {
			
			//Genericos
			List<Persona> listaPersonas = new ArrayList<Persona>();
			
			
			Persona cristina = new Persona("Cristina", "Gonzalez");
			//cristina.setNombre("Cristina");
			//cristina.setApellido("Gonzalez");
			
			Persona juan = new Persona("Juan", "Mateo");
			//juan.setNombre("Juan");
			//juan.setApellido("Mateo");
			
			Persona guillerm = new Persona("Guillerm", "Arrufat");
			//guillerm.setNombre("Guillerm");
			//guillerm.setApellido("Arrufat");
	
			Persona laura = new Persona("Laura", "Miralles");
			//laura.setNombre("Laura");
			//laura.setApellido("Miralles");
			
			Persona xavier = new Persona("Xavier", "Launes");
			//xavier.setNombre("Xavier");
			//xavier.setApellido("Launes");
			
			
			listaPersonas.add(cristina);
			listaPersonas.add(juan);
			listaPersonas.add(guillerm);
			listaPersonas.add(laura);
			listaPersonas.add(xavier);
			
			System.out.println("Hola Todos. Biernevenidos al curso de Java Intermedio");
			
			//foreach
			/*for (Persona persona : listaPersonas) {
				System.out.println("Bienvenida/o al curso " 
						+ persona.getNombre() 
						+ " " 
						+ persona.getApellido());		
			}*/
			
			//for convencional
			/*for (int i=0; i<listaPersonas.size()-1; i++) {
				System.out.println("Bienvenida/o al curso " 
						+ listaPersonas.get(i).getNombre() 
						+ " " 
						+ listaPersonas.get(i).getApellido());		
				
			}*/
			
			//foreach
			/*listaPersonas.forEach(p -> {
				System.out.println("Bienvenida/o al curso " 
						+ p.getNombre() 
						+ " " 
						+ p.getApellido());				
			});*/
			
			//suma = (a,b) -> {return a+b})
			//int sunma(int a, int b) {return a+b }
			
			//Me voy a agregar a la lista
			Persona esteban = new Persona("Esteban", "Calabria");
			esteban.setNombre("");
			listaPersonas.add(esteban);
			
			for (Persona p : listaPersonas) {
				System.out.println("Hola " + p.getNombreCompleto());
			}
			
		} catch (Exception ex) {
			System.err.println("Se ha producido un error " + ex.getMessage());
		}
		
		//Me obliga en encerrar lo suguiente en un try catch proue setNombre lanza una excepcion tipada
		//Persona pepe = new Persona("Pepe", "Perez");
		//pepe.setNombre("Pepito");
		
		System.out.println("Fin del Programa");
		
	}///main
}//class
		

