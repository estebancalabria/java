package allianz.claseCuatro;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import allianz.claseCuatro.models.*;
import allianz.claseCuatro.vista.Console;

public class Programa {

	public static void main(String[] args) {
		Console.writeLine("Bienvenidos a la clase cuatro");
		
		//Diferencias ArrayList vs LinkedList
		//   -ArrayList es bueno para acceder un elemento por su indice
		//   -LinkedList es buena para agregar elementos en el medio
		
		//Inicializacion Standard
		/*List<Formador> formadores = new LinkedList<Formador>();
		formadores.add(new Formador(...));
		formadores.add(new Formador(...));
		formadores.add(new Formador(...));*/
		
		//Inicializamos la lista con un Double-Brace Intialization
		/*List<Formador> formadores = new LinkedList<Formador>() {{
			add(new Formador("Esteban","Calabria", Sexo.MASCULINO, LocalDate.of(1980, Month.SEPTEMBER, 20), LocalDate.now().plusYears(-25)));
			add(new Formador("Ada","Lovelance", Sexo.FEMENINO, LocalDate.of(1815, Month.DECEMBER, 10)));
			add(new Formador("Chat","Gpt", Sexo.NO_DEFINIDO) );
		}};*/
		
		//En la version 9 de java (nosotros estamos con la 1.8) se recomienda List.of()
		List<Formador> formadores = Arrays.asList(
				new Formador("Esteban","Calabria", Sexo.MASCULINO, LocalDate.of(1980, Month.SEPTEMBER, 20), LocalDate.now().plusYears(-25)),
				new Formador("Ada","Lovelance", Sexo.FEMENINO, LocalDate.of(1815, Month.DECEMBER, 10)),
				new Formador("Chat","Gpt", Sexo.NO_DEFINIDO) 
		);
		
		List<Alumno> alumnos = Arrays.asList(
				new Alumno("Laura","Sánchez-Cuesta",Sexo.FEMENINO,LocalDate.of(1980, Month.DECEMBER, 27)),
				new Alumno("Sergi", "Puertes" , Sexo.MASCULINO, LocalDate.of(1979, Month.JUNE,07)),
				new Alumno("Sonia", "Pagespetit Montane" , Sexo.FEMENINO, LocalDate.of(1979, Month.JUNE,07)),
				new Alumno("Liliana","Mendoza", Sexo.FEMENINO, LocalDate.of(1900, Month.AUGUST, 28))
		);
		
		//Que genera, un linkedList? Un array List?
		//Console.writeLine(formadores.getClass().getName());
		
		Console.writeLine("Nuestros docentes son:");
		for (Formador formador : formadores) {
			/*Console.writeLine(String.format("    %s %s de %d años de experiencia", 
					formador.getNombre(), 
					formador.getApellido(), 
					(LocalDate.now().getYear()- formador.getFechaIncioActividad().getYear())) );*/
			
			//Refactoring 1 - Extraigo el metodo
			/*Console.writeLine(String.format("    %s %s de %d años de experiencia", 
					formador.getNombre(), 
					formador.getApellido(), 
					formador.getAñosDeExperiencia() ));*/
			
			//Refactoring 2 - Por detras de escena se llama en forma implicita al toString()
			Console.writeLine("    " + formador);
		}
		
		
		Console.writeLine("Nuestros docentes con expresiones lanbda son:");
		//Refactoring 2 - Ahora la expresion Lambda me quedo un one-liner - Por detras de escena se llama en forma implicita al toString()
		formadores.forEach( formador -> { Console.writeLine("    " + formador);} );

		/*Console.writeLine(String.format("    %s %s de %d años de experiencia", 
					formador.getNombre(), 
					formador.getApellido(), 
					(LocalDate.now().getYear()- formador.getFechaIncioActividad().getYear())) );*/
			
			//Refactoring 1 - Extraigo el metodo
			/*Console.writeLine(String.format("    %s %s de %d años de experiencia", 
					formador.getNombre(), 
					formador.getApellido(), 
					formador.getAñosDeExperiencia() ));*/
			
		//});
		
		Console.writeLine("Los eximios alumnos de esta institucion son");
		alumnos.forEach(alumno -> { Console.writeLine("  " + alumno); });

		//Trato de hacer new de una clase abstracta --> No se puede
		//Persona p = new Persona();
	
	}

}
