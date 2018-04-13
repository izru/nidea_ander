package com.ipartek.formacion.nidea.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.nidea.pojo.Alert;
import com.ipartek.formacion.nidea.pojo.Usuario;

/**
 * Servlet implementation class LoginUserController
 */
@WebServlet("/loginUser")
public class LoginUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String view = "";
	private Alert alert = new Alert();
	private static final int SESSION_EXPIRATION = 60 * 1;// 1 min

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("loginUser.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// recoger parametros
			String usuario = request.getParameter("usuario");
			int id = Integer.parseInt(request.getParameter("id"));

			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(5);

			Usuario usuarioUser = new Usuario();
			usuarioUser.setNombre(usuario);
			usuarioUser.setId(id);

			session.setAttribute("uPublic", usuarioUser);

			// session.setAttribute("uPublic", new Object());

			/*
			 * esto si no usamos escuchadores // guardar en hashmap // recogemos los
			 * usuarios del servlet contexto ServletContext context =
			 * request.getServletContext();
			 * 
			 * HashMap<Integer, String> usuarios = (HashMap<Integer, String>)
			 * context.getAttribute("usuarios_conectados"); if (usuarios == null) { usuarios
			 * = new HashMap<Integer, String>(); }
			 * 
			 * // metemos al usuario en el hashmap usuarios.put(usuarios.size() + 1,
			 * usuario); // guardar hashmap en context servlet
			 * context.setAttribute("usuarios_conectados", usuarios);
			 */

			view = "frontoffice/index.jsp";
			alert = new Alert("Ongi Etorri", Alert.TIPO_PRIMARY);

		} catch (Exception e) {
			e.printStackTrace();
			view = "loginUser.jsp";
			alert = new Alert();

		} finally {
			request.setAttribute("alert", alert);
			request.getRequestDispatcher("frontoffice/index.jsp").forward(request, response);
		}

	}

}
