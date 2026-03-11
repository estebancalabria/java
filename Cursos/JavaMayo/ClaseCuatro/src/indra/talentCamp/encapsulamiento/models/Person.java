package indra.talentCamp.encapsulamiento.models;

public class Person {

	private String name;
	private int document;
	private String address;
	
	public Person(String name, int document, String address) {
		super();
		this.name = name;
		this.document = document;
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public int getDocument() {
		return document;
	}
}
