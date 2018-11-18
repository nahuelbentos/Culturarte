package servlets;

import java.io.IOException;
import java.net.InetAddress;
import java.rmi.RemoteException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.rpc.ServiceException;

import datatypeJee.msjUI.DtMensajeUI;
import datatypeJee.msjUI.TipoMensaje;
import publicadores.ControladorUsuarioPublish;
import publicadores.ControladorUsuarioPublishService;
import publicadores.ControladorUsuarioPublishServiceLocator;
import publicadores.DtProponente;
import publicadores.DtUsuario;
import publicadores.URISyntaxException;

/**
 * Servlet implementation class EliminarCuenta
 */
@WebServlet("/EliminarCuenta")
public class EliminarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	// atributos
	private DtUsuario usuLog;
	private DtMensajeUI msg;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarCuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		usuLog = (DtUsuario) session.getAttribute("usuarioLogueado");
		if ((usuLog != null) && (usuLog instanceof DtProponente))
			request.getRequestDispatcher("/Usuario/eliminarCuenta.jsp").forward(request, response);
		else
			request.getRequestDispatcher("/").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		
		try {
			this.eliminarCuenta();
			
			msg = new DtMensajeUI("El usuario " + usuLog.getNickname() + " fue eliminado correctamente.", TipoMensaje.informacion);
			request.setAttribute("mensaje", msg);
			request.getSession().setAttribute("usuarioLogueado", null);
			
			request.getRequestDispatcher("Inicio").forward(request, response);
		} catch (ServiceException | RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void eliminarCuenta() throws ServiceException, RemoteException {
		ControladorUsuarioPublishService cups = new ControladorUsuarioPublishServiceLocator();
		ControladorUsuarioPublish port = cups.getControladorUsuarioPublishPort();
		port.eliminarCuenta(usuLog.getNickname());
	}
	
	private void registrarAcceso(String ip, String url, String userAgent) throws ServiceException, publicadores.IOException, URISyntaxException, RemoteException {
		ControladorUsuarioPublishService cups = new ControladorUsuarioPublishServiceLocator();
		ControladorUsuarioPublish port = cups.getControladorUsuarioPublishPort();
		port.registrarAccesoAlSitio(ip, url, userAgent);
	}
}
