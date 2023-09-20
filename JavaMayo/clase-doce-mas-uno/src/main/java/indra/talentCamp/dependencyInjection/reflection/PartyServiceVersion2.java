package indra.talentCamp.dependencyInjection.reflection;

public class PartyServiceVersion2 {
	
	static private String repositoryClass = null;
	
	public static void registerRepositoryClass(String repositoryClass) {
		PartyServiceVersion2.repositoryClass = repositoryClass;
	}
	
	private Repository repository;
	
	public PartyServiceVersion2() {
		if (repositoryClass==null) {
			throw new Error("Te falto configurar la clase PartyServiceVersion2");
		}
		
		try {
			Class<? extends Repository> clase = (Class<? extends Repository>)Class.forName(repositoryClass);
			this.repository = clase.getConstructor().newInstance();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Error("No se encuentra la clase" + this.repositoryClass);
		}
	}
	
	public void haveFun() {
		//...
		System.out.println("Everbody have fun tonight");
		System.out.println("Pero luego de divertirnos hay que guardar todo en el repo");
		this.repository.Save();
	}
	

}
