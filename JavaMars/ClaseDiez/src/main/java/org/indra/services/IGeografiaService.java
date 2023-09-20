package org.indra.services;

import java.util.*;

public interface IGeografiaService {
	List<String> getComunidadesAutonomas();
	List<String> getProvincias(String comunidad);
}
