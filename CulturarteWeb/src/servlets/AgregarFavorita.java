package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.rpc.ServiceException;

import datatypeJee.DtUsuarioWeb;
import datatypeJee.msjUI.DtMensajeUI;
import datatypeJee.msjUI.TipoMensaje;
import publicadores.ControladorPropuestaPublish;
import publicadores.ControladorPropuestaPublishService;
import publicadores.ControladorPropuestaPublishServiceLocator;
import publicadores.DtUsuario;
import publicadores.UsuarioSinLoguearseException;

//import datatype.DtUsuario;
//import excepciones.UsuarioSinLoguearseException;
//import excepciones.UsuarioYaExisteFavoritaException;

//import logica.Factory;
//import logica.IPropuestaController;

/**
 * Servlet implementation class AgregarFavorita
 */
@WebServlet("/AgregarFavorita")
public class AgregarFavorita extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarFavorita() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String titulo = request.getParameter("propuesta");
		
		HttpSession session = request.getSession();
		DtUsuario usuarioLogueado = (DtUsuario)session.getAttribute("usuarioLogueado");
//		DtUsuarioWeb usuarioLogueado = (DtUsuarioWeb)session.getAttribute("usuarioLogueado");
		

		ControladorPropuestaPublishService cpp = new ControladorPropuestaPublishServiceLocator();
		ControladorPropuestaPublish port;
		try {
			port = cpp.getControladorPropuestaPublishPort();
			try {
				port.agregarFavorita(titulo, usuarioLogueado);
				//actualizo las favoritas del usuario logueado.
//				usuarioLogueado.addTituloFavoritas(titulo);
				session.setAttribute("usuarioLogueado", usuarioLogueado);
			} catch (UsuarioSinLoguearseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		DtMensajeUI mensaje = new DtMensajeUI("Ahora "+titulo+" es una de tus favoritas.", TipoMensaje.informacion);
		request.setAttribute("mensaje", mensaje);
		
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("VerPropuesta?titulo="+titulo);
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String titulo = request.getParameter("propuesta");
		
		HttpSession session = request.getSession();
		DtUsuario usuarioLogueado = (DtUsuario)session.getAttribute("usuarioLogueado");

		ControladorPropuestaPublishService cpp = new ControladorPropuestaPublishServiceLocator();
		ControladorPropuestaPublish port;
		try {
			port = cpp.getControladorPropuestaPublishPort();
			try {
				port.agregarFavorita(titulo, usuarioLogueado);
				// le agrego la propuesta a las propuestas favoritas del usuario 
				// en sesion para no leer de nuevo desde la base
//				usuarioLogueado.addTituloFavoritas(titulo);
				session.setAttribute("usuarioLogueado", usuarioLogueado);
			} catch (UsuarioSinLoguearseException e) {
				e.printStackTrace();
			}
		} catch (ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write("Agregada a favorita");
	}

}
