package com.ipartek.formacion.nidea.util;

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

}
