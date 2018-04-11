package com.ipartek.formacion.nidea.ejemplos;

import java.util.ArrayList;

public class Pais {
	ArrayList<String> paises = new ArrayList<String>();

	int tamanio() {
		int tamanio = paises.size();
		return tamanio;
	}

	int Añadir(String pais) {
		int anterio = tamanio();
		int result = 0;

		if (paises.add(pais)) {
			result = tamanio();
		}
		return result;
	}

}
