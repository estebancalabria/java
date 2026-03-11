package itnow.javaintermedio.clasedos;

//Una interfaz funcional es una interfaz que tiene un solo metodo
//Para hacerlo mas explicito lo puedo anotar con la anotacion @FunctionalInterface 
@FunctionalInterface
public interface OperacionBinariaInteger {
	int operar(int a, int b);
	//int operar2(int a, int b);  //Me tira error si agrego otro metodo porque esta anotada con @FuntionalInterface
}
