package com.ipartek.formacion.nidea.ejemplos;

public class EjercicioVehiculos {

	public static void main(String[] args) {
		/*
		 * no se puede crear objetos de una clase abstracta Vehiculo vehiculo = new
		 * Vehiculo(); System.out.println(vehiculo.toString());
		 */
		System.out.println("---");

		VehiculoElectrico tesla = new VehiculoElectrico();
		System.out.println(tesla.toString());

		System.out.println("TESLA");
		System.out.println("Color: " + tesla.getColor());

	}

}
