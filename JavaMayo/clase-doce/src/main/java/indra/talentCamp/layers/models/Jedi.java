package indra.talentCamp.layers.models;

import java.awt.Color;

public class Jedi extends BuisinessObject {

	private String nombre;
	private int nivelDeFuerza;
	private Color colorLightsaber;

	public Jedi(String nombre, int nivelDeFuerza, Color colorLightsaber) {
		super();
		this.nombre = nombre;
		this.nivelDeFuerza = nivelDeFuerza;
		this.colorLightsaber = colorLightsaber;
	}
	
	public Jedi(String nombre, Color colorLightsaber) {
		super();
		this.nombre = nombre;
		this.nivelDeFuerza = 0;
		this.colorLightsaber = colorLightsaber;
	}

	public void setNivelDeFuerza(int nivelDeFuerza) {
		this.nivelDeFuerza = nivelDeFuerza;
	}

	public String getNombre() {
		return nombre;
	}

	public int getNivelDeFuerza() {
		return nivelDeFuerza;
	}

	public Color getColorLightsaber() {
		return colorLightsaber;
	}
	
	//Pasenme por chat un toString del Jedi que muestre todos los atributos
    @Override
    public String toString() {
        return "Jedi [getId()=" + getId() + ", nombre=" + nombre + ", nivelDeFuerza=" + nivelDeFuerza + ", colorLightSaber=" + colorLightsaber+"]";
    }
}
