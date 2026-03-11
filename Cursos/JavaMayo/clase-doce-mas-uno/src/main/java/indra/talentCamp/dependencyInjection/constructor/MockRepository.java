package indra.talentCamp.dependencyInjection.constructor;

public class MockRepository implements Repository{

	public void Save() {
		System.out.println("MockRepository: Soy un entorno controlado para probar");
	}

}
