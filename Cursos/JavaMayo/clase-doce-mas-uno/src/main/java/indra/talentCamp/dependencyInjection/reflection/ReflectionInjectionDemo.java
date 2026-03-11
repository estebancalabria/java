package indra.talentCamp.dependencyInjection.reflection;

public class ReflectionInjectionDemo {
	public static void aTodoGas() {
		
		//Configurar
		PartyService.registerRepositoryClass(DatabaseRepository.class);
		PartyServiceVersion2.registerRepositoryClass("indra.talentCamp.dependencyInjection.reflection.DatabaseRepository");
		
	    //Ejecucion
		PartyService service = new PartyService();
		service.haveFun();	
		
		System.out.println();
		System.out.println("Otro ejemplo");
		System.out.println();
		PartyServiceVersion2 serviceV2 = new PartyServiceVersion2();
		serviceV2.haveFun();
	}
}
