package indra.talentCamp.dependencyInjection.without;

public class PartyService {
	
	private Repository repository;
	
	public void haveFun() {
		//...
		System.out.println("Everbody have fun tonight");
		System.out.println("Pero luego de divertirnos hay que guardar todo en el repo");
		this.repository.Save();
	}
}
