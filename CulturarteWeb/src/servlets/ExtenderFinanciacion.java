package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String titulo = request.getParameter("titulo");
		Factory fab = Factory.getInstance();
		IPropuestaController IPC = fab.getIPropuestaController();
		
		try {
			IPC.extenderFinanciacion(titulo);
			request.setAttribute("mensaje", "Se extendió la financiacion por 30 días mas.");
		} catch (PropuestaNoExisteException e) {
			e.printStackTrace();
			request.setAttribute("mensaje", e);
		}
		request.getRequestDispatcher("VerPropuesta?titulo="+titulo).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
