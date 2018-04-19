package com.ipartek.formacion.nidea.controller.backoffice;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.nidea.model.RolDAO;
import com.ipartek.formacion.nidea.pojo.Alert;
import com.ipartek.formacion.nidea.pojo.Rol;

/**
 * Servlet implementation class RolesController
 */
@WebServlet("/backoffice/roles")
public class RolesController extends HttpServlet {

	private RequestDispatcher dispatcher;
	private Alert alert;

	private static final String VIEW_INDEX = "roles/index.jsp";
	private static final String VIEW_FORM = "roles/detalle.jsp";

	public static final int OP_NINGUNA = 0;
	public static final int OP_MOSTRAR_FORMULARIO = 1;
	public static final int OP_BUSQUEDA = 2;
	public static final int OP_ELIMINAR = 3;
	public static final int OP_GUARDAR = 4;

	private RolDAO dao;

	// PARAMETROS
	// parametros del rol
	private int id;
	private String nombre;

	// parametros comunes
	private String search;// para el buscador por nombre del rol
	private int op; // operacion a realizar

	private static final long serialVersionUID = 1L;

	/**
	 * se ejecuta solo 1º vez
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = RolDAO.getInstance();
	}

	/**
	 * se ejecuta cuando paramos el servidor de aplicaciones (en este caso es
	 * toncat)
	 */
	@Override
	public void destroy() {
		super.destroy();
		dao = null;
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

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		alert = null;

		try {
			// recoger los parametros
			recogerParametros(request);
			switch (op) {
			case OP_MOSTRAR_FORMULARIO:
				mostrarFormulario(request);
				break;
			case OP_BUSQUEDA:
				buscar(request);
				break;
			case OP_ELIMINAR:
				eliminar(request);
				break;
			case OP_GUARDAR:
				guardar(request);
				break;
			default:
				listar(request);
				break;
			}

		} catch (Exception e) {
			alert = new Alert();
			e.printStackTrace();
			dispatcher = request.getRequestDispatcher("VIEW_INDEX");
		} finally {
			request.setAttribute("alert", alert);
			// request interna a la jsp que queremos ir
			dispatcher.forward(request, response);
		}

	}

	private void recogerParametros(HttpServletRequest request) {
		if (request.getParameter("op") != null) {
			op = Integer.parseInt(request.getParameter("op"));
		} else {
			op = OP_NINGUNA;
		}
		search = (request.getParameter("search") != null) ? request.getParameter("search") : "";
		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
		} else {
			id = -1;
		}

		nombre = (request.getParameter("nombre") != null) ? request.getParameter("nombre") : "";

	}

	private void mostrarFormulario(HttpServletRequest request) {
		Rol rol = new Rol();

		if (id > -1) {
			// TODO recuperar de la BBDD que es un material que existe
			alert = new Alert("Mostramos Detalle id:" + id, Alert.TIPO_WARNING);
			rol = dao.getById(id);

		} else {
			alert = new Alert("Nuevo Rol", Alert.TIPO_WARNING);

		}
		request.setAttribute("rol", rol);
		dispatcher = request.getRequestDispatcher(VIEW_FORM);

	}

	private void buscar(HttpServletRequest request) {
		alert = new Alert("Busqueda para: " + search, Alert.TIPO_PRIMARY);
		ArrayList<Rol> roles = new ArrayList<Rol>();
		roles = dao.getPorNombre(search);
		request.setAttribute("roles", roles);
		dispatcher = request.getRequestDispatcher(VIEW_INDEX);

	}

	private void eliminar(HttpServletRequest request) {
		if (dao.delete(id)) {
			alert = new Alert("Rol eliminado id " + id, Alert.TIPO_PRIMARY);
			listar(request);
		} else {
			alert = new Alert("Lo sentimos, no hemos podido eliminar el rol id " + id, Alert.TIPO_DANGER);
		}

	}

	private void guardar(HttpServletRequest request) {
		Rol rol = new Rol();
		rol.setId(id);
		if (nombre != null && nombre != "") {

			nombre = nombre.trim();
			if (nombre.length() > 45) {
				nombre.substring(0, 44);
			}
			rol.setNombre(nombre);
			try {
				boolean salvar = dao.save(rol);
				if (salvar) {
					alert = new Alert("Rol guardado ", Alert.TIPO_PRIMARY);
				} else {
					alert = new Alert("Lo sentimos, no hemos podido guardar el rol ", Alert.TIPO_DANGER);
				}
			} catch (SQLIntegrityConstraintViolationException e) {
				alert = new Alert("El rol esta duplicado ", Alert.TIPO_DANGER);
			} catch (Exception e) {
				alert = new Alert("Ha habido un problema " + e.getCause().toString(), Alert.TIPO_DANGER);
			}
		} else {
			alert = new Alert("No ha definido el material ", Alert.TIPO_DANGER);
		}
		request.setAttribute("rol", rol);
		dispatcher = request.getRequestDispatcher(VIEW_FORM);

	}

	private void listar(HttpServletRequest request) {
		ArrayList<Rol> roles = new ArrayList<Rol>();
		roles = dao.getAll();
		request.setAttribute("roles", roles);
		dispatcher = request.getRequestDispatcher(VIEW_INDEX);

	}

}
