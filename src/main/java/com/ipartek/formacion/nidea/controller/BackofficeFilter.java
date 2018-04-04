package com.ipartek.formacion.nidea.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class BackofficeFilter
 */
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE,
		DispatcherType.ERROR }, description = "Dejar pasar al backoffice a los usuarios logeados", urlPatterns = {
				"/backoffice/*" })
public class BackofficeFilter implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println("BackofficeFilter destroy");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession();
		if (null != session.getAttribute("usuario")) {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		} else {
			informacionPeticion(req);
			res.sendRedirect(req.getContextPath() + "/login");
		}

	}

	/**
	 * mostramos por pantalla toda la informacion del usuario ==request
	 */
	private void informacionPeticion(HttpServletRequest req) {
		System.out.println("******* Acceso no permitido");
		req.getProtocol();
		System.out.println("IP: " + req.getLocalAddr());
		System.out.println("URL: " + req.getRequestURL());
		System.out.println("URI: " + req.getRequestURI());
		System.out.println("PORT: " + req.getRemotePort());

		System.out.println("****Encabezados************");
		System.out.println("Navegador: " + req.getHeader("user-agent"));
		Enumeration<String> headerValues = req.getHeaderNames();
		String headerName = "";
		while (headerValues.hasMoreElements()) {
			headerName = headerValues.nextElement();
			System.out.println(headerName + " = " + req.getHeader(headerName));
		}
		System.out.println("****atributos************");
		Enumeration<String> attributeValues = req.getAttributeNames();
		String attributeName = "";
		while (attributeValues.hasMoreElements()) {
			headerName = attributeValues.nextElement();
			System.out.println(attributeName + " = " + req.getHeader(attributeName));
		}

		System.out.println("****************");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("BackofficeFilter init");
	}

}
