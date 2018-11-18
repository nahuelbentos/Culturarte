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
import javax.servlet.http.HttpSession;
import javax.xml.rpc.ServiceException;

import publicadores.ControladorPropuestaPublish;
import publicadores.ControladorPropuestaPublishService;
import publicadores.ControladorPropuestaPublishServiceLocator;
import publicadores.ControladorUsuarioPublish;
import publicadores.ControladorUsuarioPublishService;
import publicadores.ControladorUsuarioPublishServiceLocator;
import publicadores.DtPropuestaMinificado;
import publicadores.DtUsuario;
import publicadores.EstadoPropuesta;
import datatypeJee.DtPropuestaWeb;
import datatypeJee.msjUI.DtMensajeUI;
import datatypeJee.msjUI.TipoMensaje;
import publicadores.PropuestaNoExisteException;
//import logica.Factory;
//import logica.IPropuestaController;
import publicadores.URISyntaxException;

/**
 * Servlet implementation class ListarPropuestaProponenteEstado
 */
@WebServlet("/ListarPropuestaProponenteEstado")
public class ListarPropuestaProponenteEstado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarPropuestaProponenteEstado() {
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
		
		EstadoPropuesta estado = EstadoPropuesta.fromString(request.getParameter("estado"));
		
		ControladorPropuestaPublishService cppsl = new ControladorPropuestaPublishServiceLocator();
		ControladorPropuestaPublish cpp;
		RequestDispatcher rd;
		DtMensajeUI mensaje;
		try {
			cpp = cppsl.getControladorPropuestaPublishPort();
			
			//esto luego vemos como llamarlo desde algun ajax para no consumir tanto recurso, de momento llamo a todo junto.
			HttpSession session = request.getSession();
			DtUsuario user = (DtUsuario)session.getAttribute("usuarioLogueado");
			
			try {
				DtPropuestaMinificado[] pAux = cpp.listarPropuestasProponentePorEstado(user.getNickname(),estado);
		    	DtPropuestaWeb[] props = new DtPropuestaWeb[pAux.length];
		    	mensaje = (DtMensajeUI)request.getAttribute("mensaje");
		    	
		    	for (int i = 0; i < pAux.length; i++) {
					props[i] = new DtPropuestaWeb(pAux[i].getTitulo(), pAux[i].getProponente(), pAux[i].getImagen(), null, null, null, estado);		
				}
		    	
		    	request.setAttribute("propuestas", props);
				
				//rd = request.getRequestDispatcher("/Propuesta/navegarPropuestas.jsp");
		    	request.setAttribute("mensaje", mensaje);
				rd = request.getRequestDispatcher("/Propuesta/navegarPropuestasACancelar.jsp");
				rd.forward(request, response);
				
			} catch (PropuestaNoExisteException e) {
				e.printStackTrace();
				mensaje = new DtMensajeUI(e.getMessage1(), TipoMensaje.error);
				request.setAttribute("mensaje", mensaje);
				rd = request.getRequestDispatcher("VerPerfil?nickname="+user.getNickname());
				rd.forward(request, response);
			}
		} catch (ServiceException e1) {
			e1.printStackTrace();
			mensaje = new DtMensajeUI(e1.getMessage(), TipoMensaje.error);
			request.setAttribute("mensaje", mensaje);
			rd = request.getRequestDispatcher("Inicio");
			rd.forward(request, response);
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
