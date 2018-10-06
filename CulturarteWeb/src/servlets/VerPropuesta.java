package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datatype.DtDatosPropuesta;
import datatypeJee.DtPropuestaWeb;
import datatypeJee.DtUsuarioWeb;
import datatypeJee.TipoUsuario;
import datatypeJee.msjUI.DtMensajeUI;
import logica.Factory;
import logica.IPropuestaController;

/**
 * Servlet implementation class VerPropuesta
 */
@WebServlet("/VerPropuesta")
public class VerPropuesta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerPropuesta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String titulo = request.getParameter("titulo");
		
		Factory factory = Factory.getInstance();
		IPropuestaController iProCont = factory.getIPropuestaController();
		
		
		DtDatosPropuesta propuesta = iProCont.consultarPropuesta(titulo);
		
		DtUsuarioWeb proponente = new DtUsuarioWeb(propuesta.getProponenteACargo().getNickname(), propuesta.getProponenteACargo().getNombre(), propuesta.getProponenteACargo().getApellido(),
				propuesta.getProponenteACargo().getEmail(), propuesta.getProponenteACargo().getPassword(), propuesta.getProponenteACargo().getFechaNacimiento(),
				propuesta.getProponenteACargo().getImagen(), TipoUsuario.proponente, propuesta.getProponenteACargo().getDireccion(), propuesta.getProponenteACargo().getBiografia(), propuesta.getProponenteACargo().getSitioWeb());
		
		DtPropuestaWeb proWeb = new DtPropuestaWeb(propuesta.getTitulo(), proponente.getNickname(), propuesta.getImagen(),
				propuesta.getFechaPublicacion(), propuesta.getFechaEspecatulo(), propuesta.getFechaFinalizacion(), propuesta.getEstadoActual());
		
		
		DtMensajeUI mensaje = (DtMensajeUI)request.getAttribute("mensaje");
		if(mensaje != null)
			request.setAttribute("mensaje", mensaje);
		
		request.setAttribute("propuesta", propuesta);
		request.setAttribute("proponenteACargo", proponente);
		request.setAttribute("propuestaWeb", proWeb);
		//request.setAttribute("mensaje", request.getAttribute("mensaje"));
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/Propuesta/propuesta.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
