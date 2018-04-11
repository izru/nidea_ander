package com.ipartek.formacion.nidea.ejemplos;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ipartek.formacion.nidea.util.Utilidades;

public class UtilidadesTest {

	@Test
	public void test() {

		assertEquals("hola que hace", Utilidades.limpiarEspacios(" hola que        hace   "));
	}

}
