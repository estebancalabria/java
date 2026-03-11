package org.indra.claseSeis;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.indra.claseSeis.models.*;
import org.indra.claseSeis.persistence.ClienteFileRepository;

public class Program {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		//new Cliente().setNombre("Dsds"); No puedo porque el nivel de visibilidad es publico para el package solamente
		
		//Cliente c = new ClienteBuilder()
		/*Cliente c = Cliente.builder()
				.withDni(12)
				.withNombre("Esteban")
				.withApellido("Calabria")
				.withFechaNacimiento(LocalDate.now())
				.build();*/
		
		/*ClienteRepository repository = new ClienteRepository();
		repository.save(c);
		/*
		Cliente c2 = repository.findById(1);
		System.out.println(c2);*/
		
		ClienteFileRepository repository = new ClienteFileRepository();
		List<Cliente> clientes = repository.findAll();
		clientes.forEach(System.out::println);
	}

}
