package com.ipartek.formacion.nidea.pojo;

public class Coche implements AutoCloseable {

	public Coche() {
		super();
		System.out.println("Creamos coche");
	}

	public void conducir() {
		System.out.println("estamos conduciendo");
	}

	@Override
	public void close() throws Exception {
		System.out.println("paramos coche");

	}

	public static void main(String[] args) {
		// si declaramos un objeto que implemente la interfaz autoclosable
		// dentro de los parentesis de try, cuando llega al finally se ejecuta de forma
		// automatica
		// su metodo close
		try (Coche c = new Coche()) {
			System.out.println("empezamos el programa");
			c.conducir();

		} catch (Exception e) {
			System.out.println("Tenemos una excepcion");
		} finally {
			System.out.println("finalizamos");

		}
	}

}
