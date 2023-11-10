package allianz.claseSeis;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;

import allianz.claseSeis.models.*;
import allianz.claseSeis.persistencia.*;
import allianz.claseSeis.vista.Console;

public class Program {

	public static void main(String[] args) {
		Console.writeLine("Bienvenidos a la ultima clase del curso de Java");
		
		try {
			//PersistenciaClienteArchivo db = new PersistenciaClienteArchivo();
			PersistenciaCliente db = new PersistenciaClienteArchivo();
			
			Cliente cliente = new Cliente(2, "Mark", "Zuckemberg", LocalDate.of(1984, Month.MAY, 14));
			cliente.validate();
			
			db.guardar(cliente);
			
			Cliente otroCliente = db.leer(1);
			Console.writeLine(otroCliente.toString());
			
		} catch(Exception ex) {
			Console.writeLine("ERROR " + ex.getMessage());
		}
		
        //Generar un archivo
		/*try {
			Cliente cliente = new Cliente(1, "Elon", "Musk", LocalDate.of(1971, Month.JUNE, 28));
			cliente.validate();	
			
			FileWriter fileWriter = new FileWriter("Cliente-" + Integer.toString(cliente.getDocumento()) + ".txt");
			
			fileWriter.write(Integer.toString(cliente.getDocumento()) + "\n");
			fileWriter.write(cliente.getNombre() + "\n");
			fileWriter.write(cliente.getApellido() + "\n");
			fileWriter.write(cliente.getFechaDeNacimiento().toString() + "\n");
			
			fileWriter.close();
			
		}catch(Error | IOException ex) {
			Console.writeLine("ERROR : " + ex.getMessage());
		}*/
		
		//Leer el Archivo
		/*try {
			FileReader fileReader = new FileReader("Cliente-1.txt");
			
			//Opcion 1 : Try Finally
			/*BufferedReader reader = new BufferedReader(fileReader); //Con el bufferedReader tengo el metodo readline que me permite leer lineas enteras
			try {
				Cliente cliente = new Cliente();
				cliente.setDocumento(Integer.parseInt(reader.readLine()));
				cliente.setNombre(reader.readLine());
				cliente.setApellido(reader.readLine());
				cliente.setFechaDeNacimiento(LocalDate.parse(reader.readLine()));
				cliente.validate();
			
				Console.writeLine(cliente.toString());
			}finally {
				reader.close();
			}*/
			
			//Opcion 2 : Try-with-resouces : Hace el close automatico
			/*try (BufferedReader reader = new BufferedReader(fileReader)) //Con el bufferedReader tengo el metodo readline que me permite leer lineas enteras
			{
				Cliente cliente = new Cliente();
				cliente.setDocumento(Integer.parseInt(reader.readLine()));
				cliente.setNombre(reader.readLine());
				cliente.setApellido(reader.readLine());
				cliente.setFechaDeNacimiento(LocalDate.parse(reader.readLine()));
				cliente.validate();
				Console.writeLine(cliente.toString());
			}

		
		//Opcion 1 para hacer catch . Capturar con un |
		/*}catch(ValidationException | IOException ex) {
			Console.writeLine("ERROR : " + ex.getMessage());
		}*/
		
		//Opcion 2. Capturar una clase base
		/*}catch (Exception ex) {
			Console.writeLine("ERROR : " + ex.getMessage());
		}*/
			
		//Opcion 3: Distinto catch segun el tipo de excepcion
		/*}catch(ValidationException ex) {
			Console.writeLine("ERROR Validacion : " + ex.getMessage());
		}catch(IOException ex){
			Console.writeLine("ERROR ARCHIVO : " + ex.getMessage());
		}*/
		
		Console.writeLine("Fin del Programa");
	}

}
