package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import datatypeJee.msjUI.DtMensajeUI;
import datatypeJee.msjUI.TipoMensaje;
import publicadores.PropuestaNoExisteException;
import publicadores.ControladorPropuestaPublish;
import publicadores.ControladorPropuestaPublishService;
import publicadores.ControladorPropuestaPublishServiceLocator;

/**
 * Servlet implementation class CancelarPropuesta
 */
@WebServlet("/CancelarPropuesta")
public class CancelarPropuesta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelarPropuesta() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String titulo = request.getParameter("titulo");
		String pantalla = request.getParameter("pantalla");
		
		ControladorPropuestaPublishService cppsl = new ControladorPropuestaPublishServiceLocator();
		ControladorPropuestaPublish cpp;
		
		
		try {
			cpp = cppsl.getControladorPropuestaPublishPort();
			cpp.cancelarPropuesta(titulo);
			
			System.out.println("pantalla: " + pantalla + "\n");
			DtMensajeUI msg = new DtMensajeUI("Se cancelo correctamente la propuesta: " + titulo, TipoMensaje.informacion);
			request.setAttribute("mensaje", msg);
			RequestDispatcher rd;
			if(pantalla.equals("propuesta"))
				rd = request.getRequestDispatcher("VerPropuesta?titulo="+titulo);
			else
				rd = request.getRequestDispatcher("ListarPropuestaProponenteEstado?estado=financiada");
			rd.forward(request, response);
		} catch (PropuestaNoExisteException | ServiceException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
