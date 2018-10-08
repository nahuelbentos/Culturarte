package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datatypeJee.msjUI.DtMensajeUI;
import datatypeJee.msjUI.TipoMensaje;
import excepciones.PropuestaNoExisteException;
import logica.Factory;
import logica.IPropuestaController;

@WebServlet("/ExtenderFinanciacion")
public class ExtenderFinanciacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExtenderFinanciacion() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String titulo = request.getParameter("titulo");
		Factory fab = Factory.getInstance();
		IPropuestaController IPC = fab.getIPropuestaController();
		
		try {
			IPC.extenderFinanciacion(titulo);
			DtMensajeUI msg = new DtMensajeUI("Se extendió la financiacion por 30 días mas.", TipoMensaje.informacion);
			request.setAttribute("mensaje", msg);
		} catch (PropuestaNoExisteException e) {
			e.printStackTrace();
			DtMensajeUI msg = new DtMensajeUI(e.getMessage(), TipoMensaje.error);
			request.setAttribute("mensaje", msg);
		}
		request.getRequestDispatcher("VerPropuesta?titulo="+titulo).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
