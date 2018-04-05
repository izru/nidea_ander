package com.ipartek.formacion.nidea.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.formacion.nidea.pojo.Material;

public class MaterialDAO {
	private static MaterialDAO INSTANCE = null;

	// Private constructor para que no se pueda hacer new y crear N
	// instancias(objetos)
	private MaterialDAO() {
	}

	// creador synchronized para protegerse de posibles problemas multi-hilo
	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MaterialDAO();
		}
	}

	public static MaterialDAO getInstance() {
		if (INSTANCE == null) {
			createInstance();
		}
		return INSTANCE;
	}

	/**
	 * Recupera todos los materiales de la BBDD ordenados por id descendente
	 * 
	 * @return ArrayList<Material> si no existen registros new ArrayList<Material>()
	 */
	public ArrayList<Material> getAll() {

		ArrayList<Material> lista = new ArrayList<Material>();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			/*
			 * Class.forName("com.mysql.jdbc.Driver"); final String URL =
			 * "jdbc:mysql://192.168.0.42/spoty?user=alumno&password=alumno"; con =
			 * DriverManager.getConnection(URL);
			 */

			con = ConnectionManager.getConnection();
			String sql = "SELECT id, nombre, precio FROM material;";

			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			Material m = null;
			while (rs.next()) {
				m = new Material();
				m.setId(rs.getInt("id"));
				m.setNombre(rs.getString("nombre"));
				m.setPrecio(rs.getFloat("precio"));
				lista.add(m);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (pst != null) {
					pst.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return lista;
	}

	public ArrayList<Material> getPorNombre(String nombre) {

		ArrayList<Material> lista = new ArrayList<Material>();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			final String URL = "jdbc:mysql://192.168.0.42/spoty?user=alumno&password=alumno";
			con = DriverManager.getConnection(URL);
			String sql = "select id, nombre, precio from spoty.material \n" + "where nombre like '%" + nombre + "%'\n"
					+ "order by id desc limit 500 ;";

			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			Material m = null;
			while (rs.next()) {
				m = new Material();
				m.setId(rs.getInt("id"));
				m.setNombre(rs.getString("nombre"));
				m.setPrecio(rs.getFloat("precio"));
				lista.add(m);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (pst != null) {
					pst.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return lista;
	}

}
