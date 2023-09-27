package allianz.claseSeis.models;


//Como la poliza es abstracta no me obliga a implementar los metodos de la interfaz Validable
//Pero si me obliga a que los implementen las subclases
//   Opcion 1 - Delegar a las subclases que implementen los metodos de Validable
//   Opcion 2 - Implementarlos en la clase abstracta (aunque no es obligatorio hacerlo aqui)
public abstract class Poliza implements Validable {
	@Override
	public void validate() throws ValidationException {
		//Programar la logica del validate		
	}

	@Override
	public boolean isValid() {
		return true;
	}
}
