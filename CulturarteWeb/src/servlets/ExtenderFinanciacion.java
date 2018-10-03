package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datatype.DtProponente;
import datatype.DtPropuestaMinificado;
import datatype.DtUsuario;
import datatype.EstadoPropuesta;
import excepciones.PropuestaNoExisteException;
import logica.Factory;
import logica.IPropuestaController;

@WebServlet("/ExtenderFinanciacion")
@MultipartConfig
public class ExtenderFinanciacion extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	public ExtenderFinanciacion() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		Factory fab = Factory.getInstance();
		IPropuestaController IPC = fab.getIPropuestaController();
		
		DtUsuario user = (DtUsuario) request.getSession().getAttribute("usuarioLogueado");
		if (user instanceof DtProponente) {
			try {
				DtPropuestaMinificado[] publicadas = IPC.listarPropuestasProponentePorEstado(user.getNickname(), EstadoPropuesta.publicada);
				request.setAttribute("propuestasPublicadas", publicadas);
			} catch (PropuestaNoExisteException e) {
				request.setAttribute("mensaje", e);
			}
			
			try {
				DtPropuestaMinificado[] enFinanciacion = IPC.listarPropuestasProponentePorEstado(user.getNickname(), EstadoPropuesta.enFinanciacion);
				request.setAttribute("propuestasEnFinanciacion", enFinanciacion);
			} catch (PropuestaNoExisteException e) {
				request.setAttribute("mensaje", e);
			}
		} else {
			request.setAttribute("mensaje", "Debe iniciar sesión como proponente para poder extender la financiación de una propuesta.");
		}
		
		request.getRequestDispatcher("/Propuesta/extenderFinanciacion.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
