package itnow.javaintermedio.clasetres;

//Libreria clasica para manejar archivo
import java.io.*;
//libreia mas moderna para crear archivos
import java.nio.file.*;

public class Programa {

	public static void main(String[] args) {
		System.out.println("Ejemplo de Archivos");
		
		System.out.println("Creando archivo con Libreria Nio");
		Programa.crearArchivoConLibreriaNio();
		System.out.println("Archivo generado correctamente...");
		
		System.out.println();
		System.out.println("Ahora voy a leer el contenido del archivo con la libreria Nio");
		Programa.leerArchivoConLibreriaNio();
		System.out.println("Tadaaaaaaaaaaaaaaaa.......");
		
		
	}
	
	//Mas Facil...
	public static void crearArchivoConLibreriaNio() {
		Path ruta = Path.of("archivonio.txt");
		String contenido = "Este es el contenido del archivo generado con la libreria java.nio";
		
		//Siempre que utilizo archivos tengo que usar try..catch.. porque como decia TuSam... "Puede fallar"
		try {
			Files.writeString(ruta, contenido); //Crea o sobrescribe
		}catch (Exception ex) {
			System.out.println("Error al crear el archivo " + ex.getMessage());
		}
	}
	
	public static void leerArchivoConLibreriaNio() {
		Path ruta = Path.of("archivonio.txt");
		try {
			String contenido = Files.readString(ruta);
			System.out.println("El contendi del archivo es:");
			System.out.println(contenido);
		}catch (Exception ex) {
			System.out.println("Error al crear el archivo " + ex.getMessage());
		}
		
	}

}
