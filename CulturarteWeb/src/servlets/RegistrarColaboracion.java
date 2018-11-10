package servlets;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import publicadores.DtUsuario;
import datatypeJee.DtPropuestaWeb;
import publicadores.TipoRetorno;
import publicadores.ControladorPropuestaPublish;
import publicadores.ControladorPropuestaPublishService;
import publicadores.ControladorPropuestaPublishServiceLocator;
import publicadores.DtColaboracion;

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
			TipoRetorno tipoRetorno = TipoRetorno.fromString(request.getParameter("tipoRetorno"));
			
			ControladorPropuestaPublishService cps = new ControladorPropuestaPublishServiceLocator();
			ControladorPropuestaPublish cpp;
			try {
				cpp = cps.getControladorPropuestaPublishPort();
				
				DtColaboracion dtColaboracion = new DtColaboracion();
				dtColaboracion.setColaborador(user.getNickname());
				dtColaboracion.setFechaAporte(new GregorianCalendar());
				dtColaboracion.setTituloPropuesta(titulo);
				dtColaboracion.setMonto(Double.valueOf(montoColaboracion));
				dtColaboracion.setTipo(tipoRetorno);
				dtColaboracion.setPago(false);
				try {
					cpp.generarColaboracion(dtColaboracion);
					request.getSession().setAttribute("mensaje", "Se registro con exito la colaboracion");
				} catch (publicadores.ColaboradorNoExisteException | publicadores.PropuestaNoExisteException
						| publicadores.ColaboracionExistenteException e) {
					request.getSession().setAttribute("mensaje", "Ocurrio un error");
				}
				response.sendRedirect("/CulturarteWeb/ExplorarPropuestas");
			} catch (ServiceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (boton.equals("cancelar")) {
			response.sendRedirect("/CulturarteWeb/ExplorarPropuestas");
		}
	}
}
