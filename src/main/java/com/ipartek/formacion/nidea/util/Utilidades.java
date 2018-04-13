package com.ipartek.formacion.nidea.util;

import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.nidea.ejemplos.Ordenable;

public class Utilidades {
	/**
	 * metodo estatico para poder usarse desde la propia clase, sin tener que
	 * instanciar un objeto <br>
	 * 
	 * limpia los caracteres vacios "" de una cadena String <br>
	 * hacemos trim, ademas de sustitur todos los espacios en blanco por uno unico,
	 * ej: <br>
	 * " hola que hace " => "hola que hace"
	 * 
	 * @param cadena
	 * @return en caso de null retorna cadena vacia
	 */
	public static String limpiarEspacios(String cadena) {
		String resul = "";
		if (cadena != null) {
			// Reemplaza varios espacios en blanco por uno
			resul = cadena.replaceAll(" +", " ").trim();

		}

		return resul;
	}

	/**
	 * ordenrar uan coleccion con el algoritmo buble sort, ordena de menos a mayor
	 * basandose en el metodo basandose en el metodo getValor de la interfaz
	 * Ordenable
	 * 
	 * @see com.ipartek.formacion.nidea.ejemplos.Ordenable
	 * @param coleccion
	 *            List<Ordenable> coleccion con los elementos a ordenar
	 * @return en caso de null retornamos una lista vacia
	 */
	public static List<Ordenable> bubleSort(List<Ordenable> coleccion) {
		// List<Ordenable> resul = new List<Ordenable>();// como es una interfaz, no
		// puedo crear el objeto
		List<Ordenable> resul = new ArrayList<Ordenable>();
		if (coleccion != null) {
			coleccion = ordenar(coleccion);
			resul = coleccion;
		}

		return resul;
	}

	private static List<Ordenable> ordenar(List<Ordenable> aDesordenado) {
		ArrayList<Ordenable> arr = (ArrayList<Ordenable>) aDesordenado;

		int len = arr.size();
		Ordenable temp = null;

		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len - i - 1; j++) {
				if (arr.get(j).getValor() > arr.get(j + 1).getValor()) {
					// cambio
					temp = arr.get(j);
					arr.set(j, arr.get(j + 1));
					arr.set(j + 1, temp);
				}
			}
		}

		return arr;
	}

}
