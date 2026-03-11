package indra.talentCamp.dependencyInjection.constructor;

public class PartyService {
	
	private Repository repository;
	
	public PartyService(Repository repo) {
		this.repository = repo;
	}
	
	public void haveFun() {
		//...
		System.out.println("Everbody have fun tonight");
		System.out.println("Pero luego de divertirnos hay que guardar todo en el repo");
		this.repository.Save();
	}
}
