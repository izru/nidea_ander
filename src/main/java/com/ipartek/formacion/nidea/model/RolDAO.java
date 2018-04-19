package com.ipartek.formacion.nidea.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import com.ipartek.formacion.nidea.pojo.Rol;
import com.ipartek.formacion.nidea.util.Utilidades;

public class RolDAO implements Persistible<Rol> {

	private static RolDAO INSTANCE = null;

	// Private constructor para que no se pueda hacer new y crear N
	// instancias(objetos)
	private RolDAO() {
	}

	// creador synchronized para protegerse de posibles problemas multi-hilo
	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new RolDAO();
		}
	}

	public static RolDAO getInstance() {
		if (INSTANCE == null) {
			createInstance();
		}
		return INSTANCE;
	}

	@Override
	public ArrayList<Rol> getAll() {
		ArrayList<Rol> lista = new ArrayList<Rol>();
		String sql = "select `id`, `nombre`  from `rol` LIMIT 500;";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();) {

			Rol r = null;
			while (rs.next()) {
				r = mapper(rs);
				lista.add(r);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public Rol getById(int id) {
		Rol rol = null;
		String sql = "SELECT`id`, `nombre`  from `rol` WHERE `id`= ?;";

		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					rol = mapper(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rol;
	}

	@Override
	public boolean save(Rol pojo) throws SQLIntegrityConstraintViolationException, Exception {
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

	@Override
	public boolean delete(int id) {
		boolean resul = false;
		String sql = "DELETE FROM `rol` WHERE  `id`= ?;";

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
	public Rol mapper(ResultSet rs) throws SQLException {
		Rol rol = new Rol();
		rol.setId(rs.getInt("id"));
		rol.setNombre(rs.getString("nombre"));
		return rol;
	}

	public ArrayList<Rol> getPorNombre(String nombre) {
		ArrayList<Rol> lista = new ArrayList<Rol>();
		String sql = "select `id`, `nombre`  from `rol` \n" + "where nombre like ? order by id desc limit 500 ;";

		ResultSet rs = null;

		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setString(1, "%" + nombre + "%");
			rs = pst.executeQuery();

			Rol r = null;
			while (rs.next()) {
				r = mapper(rs);
				lista.add(r);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	private boolean modificar(Rol pojo) throws SQLIntegrityConstraintViolationException, Exception {
		boolean resul = false;
		String sql = "UPDATE `rol` SET `nombre`=? WHERE `id`=?;";

		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {

			pst.setString(1, pojo.getNombre());
			pst.setInt(2, pojo.getId());

			int affectedRows = pst.executeUpdate();
			if (affectedRows == 1) {
				resul = true;
			}
		}
		return resul;
	}

	private boolean crear(Rol pojo) throws SQLIntegrityConstraintViolationException, Exception {
		boolean resul = false;
		String sql = "INSERT INTO `rol` (`nombre`) VALUES(?);";
		try (Connection con = ConnectionManager.getConnection()) {
			PreparedStatement pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setString(1, pojo.getNombre());

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

}
