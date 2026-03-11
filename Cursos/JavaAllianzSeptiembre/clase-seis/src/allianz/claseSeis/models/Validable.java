package allianz.claseSeis.models;

public interface Validable {
	void validate() throws ValidationException;
	boolean isValid();
}
