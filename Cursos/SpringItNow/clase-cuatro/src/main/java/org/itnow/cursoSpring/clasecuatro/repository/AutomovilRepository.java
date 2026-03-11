package org.itnow.cursoSpring.clasecuatro.repository;

import org.itnow.cursoSpring.clasecuatro.models.*;
import java.util.*;

public interface AutomovilRepository {
	void insert(Automovil automovil);
	void update(Automovil automovil);
	void delete(int id);
	List<Automovil> findAll();
	Automovil findById(int id);
}
