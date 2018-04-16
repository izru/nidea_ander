package com.ipartek.formacion.nidea.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import com.ipartek.formacion.nidea.pojo.Usuario;

public class UsuarioDAO implements Persistible<Usuario> {
	private static UsuarioDAO INSTANCE = null;

	// Private constructor para que no se pueda hacer new y crear N
	// instancias(objetos)
	private UsuarioDAO() {
	}

	// creador synchronized para protegerse de posibles problemas multi-hilo
	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new UsuarioDAO();
		}
	}

	public static UsuarioDAO getInstance() {
		if (INSTANCE == null) {
			createInstance();
		}
		return INSTANCE;
	}

	@Override
	public ArrayList<Usuario> getAll() {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		String sql = "select `usuario.idusuario`, `usuario.nombre`, `usuario.pass`, `usuario.id_rol`, `rol.nombre` from "
				+ "`usuario` inner join `rol` on `usuario.id_rol` = `rol.idrol` LIMIT 500;";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();) {

			Usuario u = null;
			while (rs.next()) {
				u = mapper(rs);
				lista.add(u);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public Usuario getById(int id) {
		Usuario usuario = null;
		String sql = "select `usuario.idusuario`, `usuario.nombre`, `usuario.pass`,`usuario.id_rol`, `rol.nombre` from "
				+ "`usuario` inner join `rol` on `usuario.id_rol` = `rol.idrol` WHERE `usuario.idusuario`= ?;";

		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery()) {
				usuario = mapper(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

	@Override
	public boolean save(Usuario pojo) throws SQLIntegrityConstraintViolationException, Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Usuario mapper(ResultSet rs) throws SQLException {
		Usuario usuario = new Usuario();
		usuario.setId(rs.getInt("id"));
		usuario.setNombre(rs.getString("nombre"));
		usuario.setPass(rs.getString("nombre"));
		usuario.setIdRol(rs.getInt("idRol"));
		usuario.setNombreRol(rs.getString("nombreRol"));

		return usuario;
	}

	public Usuario existeUsuario(String nombre, String password) {
		Usuario usuario = null;
		String sql = "select u.idusuario, u.nombre, u.pass, u.id_rol, r.nombre from "
				+ "usuario as u inner join rol as r on u.id_rol = r.idrol " + "WHERE u.nombre= ? AND u.pass= ?;";

		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, nombre);
			pst.setString(2, password);
			try (ResultSet rs = pst.executeQuery()) {
				usuario = mapper(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;

	}

}
