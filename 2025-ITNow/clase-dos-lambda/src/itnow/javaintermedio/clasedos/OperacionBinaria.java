package itnow.javaintermedio.clasedos;

@FunctionalInterface
public interface OperacionBinaria<R, T> {
	R operar(T a, T b);
}
