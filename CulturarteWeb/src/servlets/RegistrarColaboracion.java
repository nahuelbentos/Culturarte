package servlets;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datatype.DtColaboracion;
import datatype.DtUsuario;
import datatype.TipoRetorno;
import datatypeJee.DtPropuestaWeb;
import excepciones.ColaboracionExistenteException;
import excepciones.ColaboradorNoExisteException;
import excepciones.PropuestaNoExisteException;
import logica.Factory;
import logica.IPropuestaController;

@WebServlet("/RegistrarColaboracion")
public class RegistrarColaboracion extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
    public RegistrarColaboracion() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boton = request.getParameter("boton");
		if (boton.equals("confirmar")) {
			DtUsuario user = (DtUsuario)request.getSession().getAttribute("usuarioLogueado");
			DtPropuestaWeb propWeb = (DtPropuestaWeb)request.getSession().getAttribute("propuestaAColaborar");
			// ya lo obtuve, lo dejo nulo.
			request.getSession().setAttribute("propuestaAColaborar", null);
			
			String titulo = propWeb.getTitulo();
			String  montoColaboracion = request.getParameter("montoColaboracion");
			TipoRetorno tipoRetorno = TipoRetorno.valueOf(request.getParameter("tipoRetorno"));
			
			Factory factory = Factory.getInstance();
			IPropuestaController IPC = factory.getIPropuestaController();
			
			String colaborador = user.getNickname();
			DtColaboracion dtColaboracion = new DtColaboracion(titulo, colaborador, 
					Double.parseDouble(montoColaboracion), new GregorianCalendar(), 
					tipoRetorno);
			
			try {
				IPC.generarColaboracion(dtColaboracion );
				request.getSession().setAttribute("mensaje", "Se registro con exito la colaboracion");
			} catch (ColaboradorNoExisteException | PropuestaNoExisteException
					| ColaboracionExistenteException e) {
				request.getSession().setAttribute("mensaje", "Ocurrio un error");
			}
			response.sendRedirect("/CulturarteWeb/ExplorarPropuestas");
				
		} else if (boton.equals("cancelar")) {
			response.sendRedirect("/CulturarteWeb/ExplorarPropuestas");
		}
	}
}
