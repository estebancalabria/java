package indra.talentCamp.dependencyInjection.reflection;

public class PartyService {
	
	static private Class<? extends Repository> repositoryClass = null;
	
	public static void registerRepositoryClass(Class<? extends Repository> repositoryClass) {
		PartyService.repositoryClass = repositoryClass;
	}
	
	private Repository repository;
	
	public PartyService() {
		if (PartyService.repositoryClass==null) {
			throw new Error("Te olvidaste de configurar el PartyService");
		}
		
		try {
			//Esta forma sirve solamente para las clases que tienen constructores sin parametros
			//Parameterless contructor
			this.repository = PartyService.repositoryClass.getConstructor().newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Error("El constructor es privado");
		}
	}
	
	public void haveFun() {
		//...
		System.out.println("Everbody have fun tonight");
		System.out.println("Pero luego de divertirnos hay que guardar todo en el repo");
		this.repository.Save();
	}
}
