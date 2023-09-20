package indra.talentCamp.dependencyInjection.reflection;

public class MockRepository implements Repository{

	public void Save() {
		System.out.println("MockRepository: Soy un entorno controlado para probar");
	}

}
