package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datatype.DtUsuario;
import datatypeJee.msjUI.DtMensajeUI;
import datatypeJee.msjUI.TipoMensaje;
import excepciones.UsuarioSinLoguearseException;
import logica.Factory;
import logica.IPropuestaController;

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
		
		Factory factory = Factory.getInstance();
		IPropuestaController iProCont = factory.getIPropuestaController();
		
		try {
			iProCont.agregarFavorita(titulo, usuarioLogueado);
		} catch (UsuarioSinLoguearseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		Factory factory = Factory.getInstance();
		IPropuestaController iProCont = factory.getIPropuestaController();
		
		try {
			iProCont.agregarFavorita(titulo, usuarioLogueado);
		} catch (UsuarioSinLoguearseException e) {
			e.printStackTrace();
		}
		
		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write("Agregada a favorita");
	}

}
