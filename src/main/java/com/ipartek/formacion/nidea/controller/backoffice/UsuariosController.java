package com.ipartek.formacion.nidea.controller.backoffice;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UsuariosController
 */
@WebServlet("/usuarios_conectados")
public class UsuariosController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsuariosController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) {
		ServletContext context = request.getServletContext();

		// recogemos los usuarios del contexto
		HashMap<Integer, String> usuarios = (HashMap<Integer, String>) context.getAttribute("usuarios_conectados");
		if (usuarios == null) {
			usuarios = new HashMap<Integer, String>();
		}

		// creo de nuevo el contexto
		context.setAttribute("usuarios_conectados", usuarios);
		// dibujamos hashmap

	}

}
