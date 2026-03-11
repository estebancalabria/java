package indra.talentCamp.dependencyInjection.ioc;

public class BeanFactoryV1 {
	
	Repository repository;
	
	public void registerRepository(Repository repo) {
		  this.repository = repo;
	}

	public PartyService createPartyService() {
		return new PartyService(this.repository);
	}
}
