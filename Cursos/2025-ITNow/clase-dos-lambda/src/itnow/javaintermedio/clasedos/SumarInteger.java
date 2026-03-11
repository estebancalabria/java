package itnow.javaintermedio.clasedos;

public class SumarInteger implements OperacionBinariaInteger{

	@Override
	public int operar(int a, int b) {
		if ((a<0) || (b<0)) {
			throw new IllegalArgumentException("Solo opera con numeros positivos");
		}
		
		return a+b;
	}

}
