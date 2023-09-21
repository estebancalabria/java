//
// Hoy es el cumple del profe
//
package allianz.claseTres;

import allianz.claseTres.infraestructura.Console;
import allianz.claseTres.models.Persona;
import java.time.*;
import java.util.*;

public class Programa {

	public static void main(String[] args) {
		System.out.println("Bienvenidos a la clase tres");
		
		//
		//Fase 1: Aprendemos encapsulamiento
		//
		//Persona persona = new Persona(); //Cuando agreguemos el constructor esto no funciona
		////persona.nombre = "Juan";
		////persona.setNombre("Juan");
		////persona.setNombre("juan");  //Todo con minuscula
		//persona.setNombre("    juan      ");  //Todo con minuscula y tambien con espacios
		//persona.setDocumento(1234);
		//persona.setFechaDeNacimiento(LocalDate.of(1980, Month.SEPTEMBER , 20));
		
		//
		//Fase 2: Constructores
		//
		//Persona persona = new Persona(123, "    juan ", LocalDate.now() );
		
		//Hasta que no seteemos todos los valores tenemos un objeto inconsistente
		//Console.writeLine(String.format("Hola %s nacido el %s", persona.getNombre(), persona.getFechaDeNacimiento()));
		
		//
		//Fase 3 : Listas
		//
		List<Persona> personas = new ArrayList<Persona>(); 
		
		personas.add( new Persona(1, "Juan") );
		personas.add( new Persona(2, "Pedro") );
		personas.add( new Persona(3, "Alicia") );
		personas.add( new Persona(4, "Sonia") );
		personas.add( new Persona(5, "Sergi") );
		personas.add( new Persona(6, "Laura") );
		
		//Forma 1 de listar : for convencional
		Console.writeLine("Las personas de mi lista son (for convencional) :");
		for (int i=0; i<personas.size(); i++) {
			Console.writeLine(String.format("  La persona %d es %s", i+1, personas.get(i).getNombre()));
		}
		
		//Forma 2 de listar : foreach
		Console.writeLine("Las personas de mi lista son (for each) :");
		for (Persona persona : personas) {
			Console.writeLine(String.format("  La persona es %s (no tengo el indice)",persona.getNombre()));
		}
		
		//
		// Bonus track de expresiones lambda
		//
		personas.sort( (p1,p2) ->  p1.getNombre().compareTo(p2.getNombre()));
		//
		// o bien...
		//
		//personas.sort( (p1,p2) ->  { return p1.getNombre().compareTo(p2.getNombre()); });
		
		//Forma 3 de listar: con expresioones lamdda
		Console.writeLine("Las personas de mi lista son (con expresiones lambda) :");
		//personas.forEach( persona -> Console.writeLine("  " + persona.getNombre()));
		//
		//o bien con {} pero cuando es una sola linea no se suelen usar las llaves
		//
		personas.forEach( persona -> {Console.writeLine("  " + persona.getNombre()); } );		
	}

}
