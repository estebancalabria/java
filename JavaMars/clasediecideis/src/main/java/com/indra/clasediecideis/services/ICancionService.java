package com.indra.clasediecideis.services;

import java.util.*;
import com.indra.clasediecideis.models.*;

public interface ICancionService {
	public List<Cancion> getAll();
	public Cancion getById(int id) throws Exception;
	public Cancion add(Cancion cancion) throws Exception;
}
