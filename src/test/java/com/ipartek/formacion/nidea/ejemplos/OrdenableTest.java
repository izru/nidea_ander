package com.ipartek.formacion.nidea.ejemplos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ipartek.formacion.nidea.util.Utilidades;

public class OrdenableTest {

	static ArrayList<Ordenable> coleccion;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		coleccion = new ArrayList<Ordenable>();
	}

	@AfterClass
	public static void tearDownBeforeClass() throws Exception {
		coleccion = null;
	}

	@Before
	public void setUp() throws Exception {
		coleccion = new ArrayList<Ordenable>();

		Perro p1 = new Perro();
		p1.setNumVacunas(0);
		Perro p2 = new Perro();
		p2.setNumVacunas(1);
		Mesa m1 = new Mesa();
		m1.setNumPatas(3);
		Mesa m2 = new Mesa();
		m2.setNumPatas(9);

		coleccion.add(m2);
		coleccion.add(p1);
		coleccion.add(p2);
		coleccion.add(m1);

	}

	@After
	public void tearDown() throws Exception {
		coleccion = null;
	}

	@Test
	public void test() {

	}

	@Test
	public void testBubleSort() {

		Utilidades.bubleSort(coleccion);

		assertEquals(0, coleccion.get(0).getValor());
		assertEquals(1, coleccion.get(1).getValor());
		assertEquals(3, coleccion.get(2).getValor());
		assertEquals(9, coleccion.get(3).getValor());

		Ordenable elemento = coleccion.get(0);
		if (elemento instanceof Mesa) {
			Mesa m = (Mesa) elemento;
		} else if (elemento instanceof Perro) {
			Perro p = (Perro) elemento;
		} else {
			fail("No esperamos esta clase de objetos");
		}

	}

	@Test
	public void testCollectionSort() {

		Collections.sort(coleccion, new ComparatorOrdenables());

		assertEquals(0, coleccion.get(0).getValor());
		assertEquals(1, coleccion.get(1).getValor());
		assertEquals(3, coleccion.get(2).getValor());
		assertEquals(9, coleccion.get(3).getValor());

		Collections.reverse(coleccion);

		assertEquals(9, coleccion.get(0).getValor());
		assertEquals(3, coleccion.get(1).getValor());
		assertEquals(1, coleccion.get(2).getValor());
		assertEquals(0, coleccion.get(3).getValor());
	}

}
