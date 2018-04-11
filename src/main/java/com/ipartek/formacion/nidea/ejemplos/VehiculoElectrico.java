package com.ipartek.formacion.nidea.ejemplos;

public class VehiculoElectrico extends Vehiculo {

	private float potencia;// kw

	public VehiculoElectrico() {
		super();// constructor de vehiculo
		this.potencia = 0;
		System.out.println("instanciado Vehiculo Electrico");
	}

	public VehiculoElectrico(float potencia) {
		this();
		this.potencia = potencia;
	}

	public float getPotencia() {
		return potencia;
	}

	public void setPotencia(float potencia) {
		this.potencia = potencia;
	}

	@Override
	public void arracar() {
		// super.arracar();
		System.out.println("pulsar boton encendido");
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + "VehiculoElectrico [potencia=" + potencia + "]";
	}
}
