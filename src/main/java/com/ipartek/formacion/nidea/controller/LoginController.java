package com.ipartek.formacion.nidea.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.nidea.model.MaterialDAO;
import com.ipartek.formacion.nidea.model.UsuarioDAO;
import com.ipartek.formacion.nidea.pojo.Alert;
import com.ipartek.formacion.nidea.pojo.Usuario;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private String view = "";
	private Alert alert = new Alert();

	private UsuarioDAO daoUsuario;

	private static final String USER = "admin";
	private static final String PASS = "admin";

	private static final String VIEW_LOGIN = "login.jsp";
	private static final String VIEW_BACKOFFICE = "backoffice/index.jsp";
	private static final String VIEW_FRONTOFFICE = "frontoffice/index.jsp";

	// private static final int SESSION_EXPIRATION = 60 * 1;// 1 min
	private static final int SESSION_EXPIRATION = -1; // No expira

	@Override
	public void init() throws ServletException {
		super.init();
		daoUsuario = UsuarioDAO.getInstance();
	}

	@Override
	public void destroy() {
		super.destroy();
		daoUsuario = null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(VIEW_LOGIN).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String usuario = request.getParameter("usuario");
			String password = request.getParameter("password");
			Usuario user = comprobarUsuarioConectado(usuario, password);

			if (user != null) {

				// if (USER.equalsIgnoreCase(usuario) && PASS.equals(password)) {

				// guardar usuario en sesion
				HttpSession session = request.getSession();
				session.setAttribute("user", user);

				/**
				 * tiempo de expiracion de sesion, tambien se puede configurar web.xml un valor
				 * negativo indica que nunca expira <session-config>
				 * <session-timeout>-1</session-timeout> </session-config>
				 */
				session.setMaxInactiveInterval(SESSION_EXPIRATION);

				// enviar como atributo la lista de materiales
				MaterialDAO dao = MaterialDAO.getInstance();
				request.setAttribute("materiales", dao.getAll());

				if (user.getRol().getId() == Usuario.USER_ROL_ADMINISTRADOR) {
					view = VIEW_BACKOFFICE;
				} else {
					view = VIEW_FRONTOFFICE;
				}

				alert = new Alert("Ongi Etorri", Alert.TIPO_PRIMARY);
			} else {

				view = VIEW_LOGIN;
				alert = new Alert("Credenciales incorrectas, prueba de nuevo");
			}

		} catch (Exception e) {
			e.printStackTrace();
			view = VIEW_LOGIN;
			alert = new Alert();

		} finally {
			request.setAttribute("alert", alert);
			request.getRequestDispatcher(view).forward(request, response);
		}

	}

	Usuario comprobarUsuarioConectado(String usuario, String password) {
		Usuario user = daoUsuario.existeUsuario(usuario, password);
		return user;
	}

}
