package indra.talentCamp.dependencyInjection.field;

public class MockRepository implements Repository{

	public void Save() {
		System.out.println("MockRepository: Soy un entorno controlado para probar");
	}

}
