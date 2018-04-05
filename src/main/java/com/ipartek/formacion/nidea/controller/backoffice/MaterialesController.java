package com.ipartek.formacion.nidea.controller.backoffice;

import java.io.IOException;
import java.util.ArrayList;

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
@WebServlet(name = "MaterialesBackofficeController", urlPatterns = { "/materiales-backoffice" })
public class MaterialesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Material> materiales = new ArrayList<Material>();
		Alert alert = null;
		try {

			String search = request.getParameter("search");
			System.out.println("Filtro busqueda =" + search);

			// enviar como atributo la lista de materiales
			MaterialDAO dao = MaterialDAO.getInstance();
			if (search != null) {
				materiales = dao.getPorNombre(search);
			} else {
				materiales = dao.getAll();
			}

			// request interna a la jsp que queremos ir

		} catch (Exception e) {
			alert = new Alert();
			e.printStackTrace();
		} finally {
			request.setAttribute("alert", alert);
			request.setAttribute("materiales", materiales);
			request.getRequestDispatcher("backoffice/materiales/index.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
