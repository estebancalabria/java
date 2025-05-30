package itnow.javaintermedio.clasecuatro.persistencia;

import java.sql.SQLException;
import java.util.List;

import itnow.javaintermedio.clasecuatro.models.ObjetoDeNegocio;
import itnow.javaintermedio.clasecuatro.models.Ticket;

//Lo vamos a convertir en un generico
/*public interface TicketRepository {
	List<Ticket> getAll() throws SQLException ;
	Ticket getById(int id) throws SQLException ;
	Ticket save(Ticket nuevo) throws SQLException ;
	Ticket update(Ticket existente) throws SQLException ;
	boolean delete(int id) throws SQLException ;
}*/


//Restriccion o constraint quiero que el tipo T sea solamente clases que hereden de ObjetoDeNegocio
//Respository<Ticket> ---- Si
//Respository<Usuario> ---- Si
//Respository<Integer> ---- No
//Respository<Program> ---- No
public interface Repository<T extends ObjetoDeNegocio> {
	List<T> getAll() throws SQLException ;
	T getById(int id) throws SQLException ;
	T save(T nuevo) throws SQLException ;
	T update(T existente) throws SQLException ;
	boolean delete(int id) throws SQLException ;
}

