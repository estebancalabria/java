//
// Hoy es el cumple del profe
//
package allianz.claseTres;

import allianz.claseTres.infraestructura.Console;
import allianz.claseTres.models.Persona;
import java.time.*;

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
		Persona persona = new Persona(123, "    juan ", LocalDate.now() );
		
		//Hasta que no seteemos todos los valores tenemos un objeto inconsistente
		Console.writeLine(String.format("Hola %s nacido el %s", persona.getNombre(), persona.getFechaDeNacimiento()));
	}

}
