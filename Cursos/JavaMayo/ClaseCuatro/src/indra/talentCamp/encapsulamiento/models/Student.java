package indra.talentCamp.encapsulamiento.models;

public class Student extends Person {

   //private String name;
   //private int document;
   //private String address;
   private boolean prefersFrontEnd;
   
   public Student(String name, int document, String address) {
	   //this.name = name;
	   //this.document = document;
	   //this.address = address;
	   super(name, document, address);
	   this.prefersFrontEnd = false; //Inicializando un atributo con un valor por defecto
   }
   
   public Student(String name, int document, String address, boolean prefersFrontEnd) {
	   super(name, document, address);
	   //super(); //Despues lo explicamos
		//this.name = name;
		//this.document = document;
		//this.address = address;
		this.prefersFrontEnd = prefersFrontEnd;
	}

   /*public String getName() { //readonly
	   return this.name;
   }
   
   //Commento el setter para hacer el nombre readonly
   //public void setName(String name) {
   //	   this.name = name;
   //}

	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}*/
	
	public boolean getPrefersFrontEnd() {
		return prefersFrontEnd;
	}

	public void setPrefersFrontEnd(boolean prefersFrontEnd) {
		this.prefersFrontEnd = prefersFrontEnd;
	}

	/*public int getDocument() {
		return document;
	}*/
	
	//El documento tambien es readonly
	//public void setDocument(int document) {
	//	this.document = document;
	//}
	
	@Override
	public String toString() {
		//return this.getName() + " (Documento: " + this.getDocument() +")" + "\n" +
		//		"["+ this.getAddress() + "]" + "\n" +
		//		(this.prefersFrontEnd ? "Entusiasta de FontEnd" : "Fantatico backend");
		return String.format("%s (Documento %d) \n[%s] \n%s", 
				this.getName(),
				this.getDocument(),
				this.getAddress(),
				(this.prefersFrontEnd ? "Entusiasta de FontEnd" : "Fantatico backend"));
	}
	
}
