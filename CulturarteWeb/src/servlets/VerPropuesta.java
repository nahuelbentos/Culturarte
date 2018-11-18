package servlets;

import java.io.IOException;
import java.net.InetAddress;
import java.rmi.RemoteException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import publicadores.ControladorPropuestaPublish;
import publicadores.ControladorPropuestaPublishService;
import publicadores.ControladorPropuestaPublishServiceLocator;
import publicadores.ControladorUsuarioPublish;
import publicadores.ControladorUsuarioPublishService;
import publicadores.ControladorUsuarioPublishServiceLocator;
import publicadores.DtDatosPropuesta;
import publicadores.URISyntaxException;
import datatypeJee.DtPropuestaWeb;
import datatypeJee.DtUsuarioWeb;
import datatypeJee.TipoUsuario;
import datatypeJee.msjUI.DtMensajeUI;
//import logica.Factory;
//import logica.IPropuestaController;

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
		
		String ip = request.getRemoteAddr();
		if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
		    InetAddress inetAddress = InetAddress.getLocalHost();
		    String ipAddress = inetAddress.getHostAddress();
		    ip = ipAddress;
		}
		
		String url = request.getRequestURI();
		String userAgent = request.getHeader("User-Agent");
		
		try {
			registrarAcceso(ip, url, userAgent);
		} catch (ServiceException e1) {
			e1.printStackTrace();
		}
		
		String titulo = request.getParameter("titulo");
		
		ControladorPropuestaPublishService cppsl = new ControladorPropuestaPublishServiceLocator();
		ControladorPropuestaPublish cpp;
		try {
			cpp = cppsl.getControladorPropuestaPublishPort();
			
			DtDatosPropuesta propuesta = cpp.consultarPropuesta(titulo);
			
			DtUsuarioWeb proponente = new DtUsuarioWeb(propuesta.getProponenteACargo().getNickname(), propuesta.getProponenteACargo().getNombre(), propuesta.getProponenteACargo().getApellido(),
					propuesta.getProponenteACargo().getEmail(), propuesta.getProponenteACargo().getPasswordString(), propuesta.getProponenteACargo().getFechaNacimiento(),
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
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void registrarAcceso(String ip, String url, String userAgent) throws ServiceException, publicadores.IOException, URISyntaxException, RemoteException {
		ControladorUsuarioPublishService cups = new ControladorUsuarioPublishServiceLocator();
		ControladorUsuarioPublish port = cups.getControladorUsuarioPublishPort();
		port.registrarAccesoAlSitio(ip, url, userAgent);
	}
}
