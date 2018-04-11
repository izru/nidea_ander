package com.ipartek.formacion.nidea.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public interface Persistible<P> {
	/**
	 * Lista de una tabla de la base de datos ordenados por id descendente y
	 * limitado a 500
	 * 
	 * @return Coleccion
	 */
	public ArrayList<P> getAll();

	/**
	 * Obtenemos el detalle de un registro
	 * 
	 * @param id
	 *            identificador
	 * @return Registro si existe, null en caso contrario
	 */
	public P getById(int id);

	/**
	 * Guardamos un registro en la BBDD Si el id del Pojo ==-1 Creamos Si el id del
	 * Pojo >1 Modificamos
	 * 
	 * @param pojo
	 * @return
	 * @throws Exception
	 */
	public boolean save(P pojo) throws SQLIntegrityConstraintViolationException, Exception;

	/**
	 * Eliminamos un registro por su identificador
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(int id);

	/**
	 * Nos mapea un Resultado de la BBDD a un pojo
	 * 
	 * @param rs
	 *            ResultSet 1 registro de la consulta
	 * @return Pojo con los valores del ResulSet o un null si no hay valores
	 */
	public P mapper(ResultSet rs) throws SQLException;
}
