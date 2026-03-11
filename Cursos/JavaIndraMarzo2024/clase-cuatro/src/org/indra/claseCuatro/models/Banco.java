package org.indra.claseCuatro.models;

import java.util.*;

//Vamos a suponer que en nuestro sistema hay un solo banco
//Vamos a implementar el patron Singleton
//para asegurarme que exista una sola instancia del Banco
public class Banco extends ObjetoDeNegocio{
	private static Banco _instance = null;
    private List<Cliente> clientes;
    private List<Cuenta> cuentas;
    
    private Banco() {
    	this.clientes = new ArrayList<Cliente>();
    	this.cuentas = new ArrayList<Cuenta>();
    }
    
    public static Banco getInstance() {
    	if (_instance == null) {
    		_instance = new Banco();
    	}
    	
    	return _instance;
    }
    
}
