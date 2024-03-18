package org.indra.claseSeis.persistence;

import java.util.*;
import org.indra.claseSeis.models.*;
import java.io.*;
import java.time.LocalDate;

public class ClienteFileRepository implements ClienteRepository {

	public List<Cliente> findAll() throws Exception{
		List<Cliente> result  = new ArrayList<>();
		File file = new File("clientes\\");
		for (File f : file.listFiles()) {
			Cliente c = this.findById(Integer.parseInt(f.getName()));
			result.add(c);
		};
		return result;
	}
	
	public Cliente findById(int id) throws Exception{
		File file = new File("clientes\\" + id);
		
		if (!file.exists()) {
			throw new Error("El cliente no existe");
		}
		
		
		FileReader reader = new FileReader(file);
		BufferedReader readerMejor = new BufferedReader(reader);
		
		
		Cliente c = Cliente
				.builder()
				.withId(Integer.parseInt(readerMejor.readLine()))
				.withDni(Integer.parseInt(readerMejor.readLine()))
                .withNombre(readerMejor.readLine())
                .withApellido(readerMejor.readLine())
                .withFechaNacimiento(LocalDate.parse(readerMejor.readLine()))
				.build();
		
		
		//readerMejor.readLine();
		
		return c;
	}
	
	public void save(Cliente c) throws Exception {
		if (c.getId()!=0) {
			throw new Error("El cliente ya tiene ID");
		}
		
		//Creamos el directorio clientes si no existe
		File directorio = new File("clientes");
		if (!directorio.exists()) {
			directorio.mkdir();
		}
		
		//contmos cuantos archivos hay en el directorio
		int cantidadArchivos = directorio.listFiles().length;
		int id = cantidadArchivos + 1;
		c.setId(id);
		
		
		//Este es para grabar caracter por caracter
		FileWriter writer = new FileWriter("clientes\\" + id);
		//BufferedWriter w = new BufferedWriter(writer)
		PrintWriter printWriter = new PrintWriter (writer);
		printWriter.println(c.getId());
		printWriter.println(c.getDni());
		printWriter.println(c.getNombre());
		printWriter.println(c.getApellido());
		printWriter.println(c.getFechaNacimiento().toString());
		
		writer.close();
		
	}
	
	public void update(Cliente c) throws Exception {
		if (c.getId()==0) {
			throw new Error("El cliente es nuevo");
		}
		
		this.delete(c.getId());
		this.save(c);
		
	}
	
	public void delete(int id) {
		File file = new File("clientes\\" + id);
		if (file.exists()) {
			file.delete();
		}
	}
}
