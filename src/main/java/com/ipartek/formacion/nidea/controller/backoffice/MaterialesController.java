package com.ipartek.formacion.nidea.controller.backoffice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.nidea.model.MaterialDAO;
import com.ipartek.formacion.nidea.pojo.Alert;
import com.ipartek.formacion.nidea.pojo.Material;

/**
 * Servlet implementation class MaterialesController
 */
// @WebServlet(name = "MaterialesBackofficeController", urlPatterns = {
// "/materiales-backoffice" })
@WebServlet("/backoffice/materiales")
public class MaterialesController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// private static final String VIEW_INDEX = "backoffice/materiales/index.jsp";
	// private static final String VIEW_FORM = "/backoffice/materiales/detalle.jsp";

	private static final String VIEW_INDEX = "materiales/index.jsp";
	private static final String VIEW_FORM = "materiales/detalle.jsp";

	public static final int OP_NINGUNA = 0;
	public static final int OP_MOSTRAR_FORMULARIO = 1;
	public static final int OP_BUSQUEDA = 2;
	public static final int OP_ELIMINAR = 3;
	public static final int OP_GUARDAR = 4;

	private RequestDispatcher dispatcher;
	private Alert alert;

	private MaterialDAO dao;

	// PARAMETROS
	// parametros del material
	private int id;
	private String nombre;
	private float precio;

	// parametros comunes
	private String search;// para el buscador por nombre material
	private int op; // operacion a realizar

	/**
	 * se ejecuta solo 1º vez
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = MaterialDAO.getInstance();
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

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("antes de ejecutar doGET o doPost");
		super.service(request, response);
		System.out.println("despues de ejecutar doGET o doPost");
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

	/**
	 * Unimos las peticiones doGet y doPost, hacemos lo mismo en ambas
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
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

	private void listar(HttpServletRequest request) {
		ArrayList<Material> materiales = new ArrayList<Material>();
		materiales = dao.getAll();
		request.setAttribute("materiales", materiales);
		dispatcher = request.getRequestDispatcher(VIEW_INDEX);
	}

	private void guardar(HttpServletRequest request) {
		Material material = new Material();
		material.setId(id);
		if (nombre != null && nombre != "") {
			material.setNombre(nombre.trim().substring(0, 44));
			if (precio > 0) {
				material.setPrecio(precio);
				try {
					boolean salvar = dao.save(material);
					if (salvar) {
						alert = new Alert("Material guardado ", Alert.TIPO_PRIMARY);
					} else {
						alert = new Alert("Lo sentimos, no hemos podido guardar el material ", Alert.TIPO_DANGER);
					}
				} catch (Exception e) {

					alert = new Alert("El material esta duplicado " + e.getCause().toString(), Alert.TIPO_DANGER);
				}
			} else {
				alert = new Alert("El precio debe ser de valor positivo ", Alert.TIPO_DANGER);
			}
		} else {
			alert = new Alert("No ha definido el material ", Alert.TIPO_DANGER);
		}
		request.setAttribute("material", material);
		dispatcher = request.getRequestDispatcher(VIEW_FORM);
	}

	private void eliminar(HttpServletRequest request) {
		alert = new Alert("Material eliminado id " + id, Alert.TIPO_PRIMARY);
		listar(request);
	}

	private void buscar(HttpServletRequest request) {
		alert = new Alert("Busqueda para: " + search, Alert.TIPO_PRIMARY);
		ArrayList<Material> materiales = new ArrayList<Material>();
		materiales = dao.getPorNombre(search);
		request.setAttribute("materiales", materiales);
		dispatcher = request.getRequestDispatcher(VIEW_INDEX);

	}

	private void mostrarFormulario(HttpServletRequest request) {
		Material material = new Material();

		if (id > -1) {
			// TODO recuperar de la BBDD que es un material que existe
			alert = new Alert("Mostramos Detalle id:" + id, Alert.TIPO_WARNING);
			material = dao.getById(id);

		} else {
			alert = new Alert("Nuevo Producto", Alert.TIPO_WARNING);

		}
		request.setAttribute("material", material);
		dispatcher = request.getRequestDispatcher(VIEW_FORM);
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
		if (request.getParameter("precio") != null) {
			precio = Float.parseFloat(request.getParameter("precio"));
		} else {
			precio = 0;
		}

	}

}
