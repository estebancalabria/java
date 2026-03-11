package indra.talentCamp.dependencyInjection.field;

public class FieldInjectionDemo {
	
	public static void aTodoGas() {
	    //Tiempo de Configuracion
		PartyService service = new PartyService();
		service.setRepository( new DatabaseRepository());
		
		//Tiempo de Ejecucion
		service.haveFun();
	}
}
