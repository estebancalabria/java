package org.indra.model;

public class ModerarMalasPalabras implements IEstrategiaModeracion {

	@Override
	public boolean esApropiado(String texto) {
		String[] malasPalabras = {"tonto","incapaz","feo","malo"};
		for (String palabra : malasPalabras) {
			if (texto.contains(palabra)) return false;
		}
		return true;
	}

}
