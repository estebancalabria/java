package indra.talentCamp.dependencyInjection.ioc;

public class PartyService {
	
	private Repository repository;
	
	//Este es package-protectes osea que desde fuera de este paquete
	//no se puede crear un partyService desde otro paquete que no sea este
	PartyService(Repository repository) {
		this.repository = repository;
	}
	
	public PartyService(){
		super();
	}
	
	public void haveFun() {
		//...
		System.out.println("Everbody have fun tonight");
		System.out.println("Pero luego de divertirnos hay que guardar todo en el repo");
		this.repository.Save();
	}
}
