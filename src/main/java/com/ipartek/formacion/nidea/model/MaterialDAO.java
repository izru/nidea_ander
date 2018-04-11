package com.ipartek.formacion.nidea.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import com.ipartek.formacion.nidea.pojo.Material;
import com.ipartek.formacion.nidea.util.Utilidades;

public class MaterialDAO implements Persistible<Material> {
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
		String sql = "SELECT id, nombre, precio FROM material LIMIT 500;";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();) {

			/*
			 * Class.forName("com.mysql.jdbc.Driver"); final String URL =
			 * "jdbc:mysql://192.168.0.42/spoty?user=alumno&password=alumno"; con =
			 * DriverManager.getConnection(URL);
			 */

			Material m = null;
			while (rs.next()) {
				m = mapper(rs);
				lista.add(m);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public ArrayList<Material> getPorNombre(String nombre) {

		ArrayList<Material> lista = new ArrayList<Material>();
		String sql = "select id, nombre, precio from material \n" + "where nombre like ? order by id desc limit 500 ;";

		ResultSet rs = null;

		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setString(1, "%" + nombre + "%");
			rs = pst.executeQuery();

			Material m = null;
			while (rs.next()) {
				m = mapper(rs);
				lista.add(m);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public Material getById(int id) {
		Material material = null;
		String sql = "SELECT `id`, `nombre`, `precio` FROM `material` WHERE `id`= ?;";

		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery()) {
				material = mapper(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return material;
	}

	@Override
	public boolean save(Material pojo) throws Exception, SQLIntegrityConstraintViolationException {
		boolean resul = false;

		// sanitizar el nombre
		pojo.setNombre(Utilidades.limpiarEspacios(pojo.getNombre()));

		if (pojo != null) {
			if (pojo.getId() == -1) {
				resul = crear(pojo);
			} else {
				resul = modificar(pojo);
			}
		}
		return resul;

	}

	private boolean modificar(Material pojo) throws SQLIntegrityConstraintViolationException, Exception {
		boolean resul = false;
		String sql = "UPDATE `material` SET `nombre`=?,`precio`=? WHERE `id`=?;";

		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {

			pst.setString(1, pojo.getNombre());
			pst.setFloat(2, pojo.getPrecio());
			pst.setInt(3, pojo.getId());

			int affectedRows = pst.executeUpdate();
			if (affectedRows == 1) {
				resul = true;
			}
		}
		return resul;
	}

	private boolean crear(Material pojo) throws SQLIntegrityConstraintViolationException, Exception {
		boolean resul = false;
		String sql = "INSERT INTO `material` (`nombre`,`precio`) VALUES(?,?);";
		try (Connection con = ConnectionManager.getConnection()) {
			PreparedStatement pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setString(1, pojo.getNombre());
			pst.setFloat(2, pojo.getPrecio());

			int affetedRows = pst.executeUpdate();

			if (affetedRows == 1) {
				resul = true;
				// recuperar ID generado de forma automatica
				try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
					while (generatedKeys.next()) {
						int id = generatedKeys.getInt(1);
						pojo.setId(id);// como es paso por referencia, se cambia automaticamente
					}
				}
			}
		}
		return resul;
	}

	@Override
	public boolean delete(int id) {
		boolean resul = false;
		String sql = "DELETE FROM `material` WHERE  `id`= ?;";

		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, id);

			int affetedRows = pst.executeUpdate();

			if (affetedRows == 1) {
				resul = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resul;
	}

	@Override
	public Material mapper(ResultSet rs) throws SQLException {
		Material material = new Material();
		material.setId(rs.getInt("id"));
		material.setNombre(rs.getString("nombre"));
		material.setPrecio(rs.getFloat("precio"));

		return material;
	}

}
