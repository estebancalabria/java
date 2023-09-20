package indra.talentCamp.generics;

//Incluyo la palabra "Generica" al final por un tema didactico pero no es recomendable
public abstract class FiguraGenerica<T extends Number> {
	public abstract T calcularArea();
	public abstract T calcularPerimetro();
}
