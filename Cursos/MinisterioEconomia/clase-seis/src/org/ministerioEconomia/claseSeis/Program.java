package org.ministerioEconomia.claseSeis;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;
import java.util.UUID;

import org.ministerioEconomia.models.*;

/*
 * class Program
 * Autor : Esteban Calabria
 * Fecha : 06-11-20204
 * Proposito : La Clase principal del Programa
 */
public class Program {

	public static void main(String[] args) {
		
		Scanner lector = new Scanner(System.in);
		try {
			
			//Teoria 1:
			//Metodos de instancia vs Metodos estaticos
			//Los metodo estaticos son aquellos que para invocarlos tengo que referirme a un tipo de datos
			//--Metodos Estaticos
			//           <Tipo_De_Dato>.metodoEstatico();
			//        Ej:
			//           Integer.parseInt
			//           MessageFormat
			//Los metodos de instancia refieren a una variable que cree previamente con un new (o con un metodo estatico)
			//--Metodos de Instancia
			//           <Variable>.metodoDeInstancia();
			//         Ej:
			//           lector.nextInt();
			
			
			
			// Ejercicio 1 : Introducimos los unique identifiers 
			//UUID identificadorUnico = UUID.randomUUID(); /*Utilizo el metodo statico ramdomUUID para crear un ID Nuevo */
			//System.out.println(identificadorUnico);
			
			//Ejercicio 2 : Creacion de Objetos y Sobrecarga de constructores
			Departamento aplicaciones = new Departamento("Aplicaciones");
			
			Usuario angel = new Usuario("Angel", aplicaciones, LocalDate.of(1994, Month.DECEMBER, 9));
			Usuario joseLuis = new Usuario("Jose Luis", aplicaciones, LocalDate.of(1994, Month.DECEMBER, 9));
			Usuario recienNacido = new Usuario("Pedro", new Departamento("Incubadora"));
			Usuario dios = Usuario.superUsuario(); //Creo un objeto pero con mi "factory method" (metodo estatico)
			
			System.out.println(angel);
			System.out.println(joseLuis);
			System.out.println(recienNacido);
			System.out.println(dios);
			
		}finally {
			lector.close();
		}/* try */
	} /*void main */
}/*class Program*/
