package allianz.claseSeis.persistencia;

import allianz.claseSeis.models.Cliente;

public class PersistenciaClienteBaseDeDatos implements PersistenciaCliente {

	@Override
	public void guardar(Cliente cliente) throws Exception {
		//Dejamos para el "avanzado" programar con base de datos
		
	}

	@Override
	public Cliente leer(int documento) throws Exception {
		//Dejamos para el "avanzado" programar con base de datos
		return null;
	}

}
