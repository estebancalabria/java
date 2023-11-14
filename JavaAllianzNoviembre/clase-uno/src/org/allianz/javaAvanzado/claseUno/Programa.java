package org.allianz.javaAvanzado.claseUno;

import java.util.*;

import org.allianz.javaAvanzado.claseUno.infraestructura.*;
import org.allianz.javaAvanzado.claseUno.models.Cliente;

public class Programa {

	public static void main(String[] args) {
        System.out.println("Hola Mundo");
        
        //Log log = new ConsoleLog();
        Log log = new FileLog();
        
        List<Cliente> clientes = new ArrayList<>();
        
        //Ejemplo 1 - Creo una clase del Modelo
        Cliente elon = new Cliente();
        elon.setDocumento(1);
        elon.setNombre("Elon");
        elon.setApellido("Musk");
        clientes.add(elon);
        
        Cliente mark = new Cliente(2, "Mark", "Zuckemberg");
        clientes.add(mark);
        
        //Ya no uso mas la consola, ahora voy a usar el log
        //clientes.forEach( c -> { System.out.println(c); });
        //clientes.forEach( System.out::println );
        clientes.forEach( c -> { log.info(c.toString()); });
        
	}

}

