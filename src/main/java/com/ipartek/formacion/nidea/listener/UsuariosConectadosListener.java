package com.ipartek.formacion.nidea.listener;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.ipartek.formacion.nidea.pojo.Usuario;

/**
 * Application Lifecycle Listener implementation class
 * UsuariosConectadosListener
 *
 */
@WebListener
public class UsuariosConectadosListener implements HttpSessionListener, HttpSessionAttributeListener {

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent se) {
		// comprobar que sea atributo == uPublic
		// cojo el usuario del atributo upublic
		if (se.getSession().getAttribute("uPublic") != null) {
			Usuario u = (Usuario) se.getSession().getAttribute("uPublic");

			ServletContext context = se.getSession().getServletContext();

			if (context.getAttribute("usuarios_conectados") == null) {
				HashMap<Integer, Usuario> hmUsuarios = (HashMap<Integer, Usuario>) context
						.getAttribute("usuarios_conectados");

				hmUsuarios.remove(u.getId());
				context.setAttribute("usuarios_conectados", hmUsuarios);
			}

		}
	}

	/**
	 * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
	 */
	public void attributeAdded(HttpSessionBindingEvent event) {
		// comprobar que sea atributo == uPublic
		if (event.getName().equals("uPublic")) {
			// contexto de la aplicacion
			ServletContext context = event.getSession().getServletContext();

			HashMap<Integer, Usuario> hmUsuarios = null;

			if (context.getAttribute("usuarios_conectados") == null) {
				hmUsuarios = new HashMap<Integer, Usuario>();
			} else {
				hmUsuarios = (HashMap<Integer, Usuario>) context.getAttribute("usuarios_conectados");
			}

			// metemos al usuario en el hashmap
			Usuario u = (Usuario) event.getValue();
			hmUsuarios.put(u.getId(), u);
			// guardar hashmap en context servlet
			context.setAttribute("usuarios_conectados", hmUsuarios);

		}

	}

	/**
	 * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
	 */
	public void attributeRemoved(HttpSessionBindingEvent event) {

	}

	/**
	 * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
	 */
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
	}

}
