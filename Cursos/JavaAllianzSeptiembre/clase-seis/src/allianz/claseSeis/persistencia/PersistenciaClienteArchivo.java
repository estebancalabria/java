package allianz.claseSeis.persistencia;

import java.io.*;
import java.time.*;

import allianz.claseSeis.models.*;

public class PersistenciaClienteArchivo implements PersistenciaCliente {

	public void guardar(Cliente cliente) throws Exception {
		try {
			cliente.validate();

			try (FileWriter fileWriter = new FileWriter(
					"Cliente-" + Integer.toString(cliente.getDocumento()) + ".txt")) {
				fileWriter.write(Integer.toString(cliente.getDocumento()) + "\n");
				fileWriter.write(cliente.getNombre() + "\n");
				fileWriter.write(cliente.getApellido() + "\n");
				fileWriter.write(cliente.getFechaDeNacimiento().toString() + "\n");
			}

		// Opcion 1 : Propago la excepcion
		} catch (Exception ex) {
			throw ex;
		}
		
		// Opcion 2 : La convierto en un error para no tener que poner el throws
		//}catch(Exception ex) { 
		//    throw new Error(ex.getMessage()); 
		//} 
		
		// Opcion 3: Lanzo una excepcion especifica del paquete 
		//}catch(Exception ex) { 
		//    throw new PersistenceSaveError(ex.getMessage()); 
		//}
	}

	public Cliente leer(int documento) throws Exception {
		try {
			FileReader fileReader = new FileReader(String.format("Cliente-%d.txt", documento));

			try (BufferedReader reader = new BufferedReader(fileReader)) // Con el bufferedReader tengo el metodo
																			// readline que me permite leer lineas
																			// enteras
			{
				Cliente cliente = new Cliente();
				cliente.setDocumento(Integer.parseInt(reader.readLine()));
				cliente.setNombre(reader.readLine());
				cliente.setApellido(reader.readLine());
				cliente.setFechaDeNacimiento(LocalDate.parse(reader.readLine()));
				cliente.validate();

				return cliente;
			}
		} catch (Exception ex) {
			throw ex;
		}
	}
}
