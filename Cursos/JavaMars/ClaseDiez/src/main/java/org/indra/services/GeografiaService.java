package org.indra.services;

import java.util.*;

public class GeografiaService implements IGeografiaService {

	private List<String> comunidadesAutonomas;
	private Map<String, String> provincias;
	
	public List<String> getComunidadesAutonomas() {
		// TODO Auto-generated method stub
		return this.comunidadesAutonomas;
	}
	
	public void setComunidadesAutonomas(List<String> comunidadesAutonomas) {
		this.comunidadesAutonomas = comunidadesAutonomas;
	}

	public Map<String, String> getProvincias() {
		return provincias;
	}

	public void setProvincias(Map<String, String> provincias) {
		this.provincias = provincias;
	}

	@Override
	public List<String> getProvincias(String comunidad) {
		// TODO Auto-generated method stub
		return Arrays.asList(this.provincias.get(comunidad).split(","));
	}

}
