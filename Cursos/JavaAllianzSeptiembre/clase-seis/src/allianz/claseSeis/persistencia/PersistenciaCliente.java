package allianz.claseSeis.persistencia;

import allianz.claseSeis.models.Cliente;

//Opcion 1 : Clase Abstracta (Completamente abstracta)
/*public abstract class PersistenciaCliente {
	public abstract void guardar(Cliente cliente) throws Exception;
	public abstract Cliente leer(int documento) throws Exception;
}*/

//Opcion 2: Una interfaz
public interface PersistenciaCliente{
	void guardar(Cliente cliente) throws Exception;
	Cliente leer(int documento) throws Exception;
}
