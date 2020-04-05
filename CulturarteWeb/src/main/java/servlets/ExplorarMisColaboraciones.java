package servlets;

import java.io.IOException;
import java.net.InetAddress;
import java.rmi.RemoteException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import datatypeJee.msjUI.DtMensajeUI;
import datatypeJee.msjUI.TipoMensaje;
import publicadores.ColaboracionNoExisteException;
import publicadores.ControladorPropuestaPublish;
import publicadores.ControladorPropuestaPublishService;
import publicadores.ControladorPropuestaPublishServiceLocator;
import publicadores.ControladorUsuarioPublish;
import publicadores.ControladorUsuarioPublishService;
import publicadores.ControladorUsuarioPublishServiceLocator;
import publicadores.DtColaboracion;
import publicadores.DtColaborador;
import publicadores.DtUsuario;
import publicadores.URISyntaxException;

/**
 * Servlet implementation class ExplorarColaboraciones
 */
@WebServlet("/ExplorarMisColaboraciones")
public class ExplorarMisColaboraciones extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExplorarMisColaboraciones() {
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
		
		DtUsuario usuLog = (DtUsuario)request.getSession().getAttribute("usuarioLogueado");
		
		if (usuLog instanceof DtColaborador) {
			try {
				DtColaboracion[] colhechas = this.misColaboraciones(usuLog.getNickname());
				request.setAttribute("colaboracionesHechas", colhechas);
				request.getRequestDispatcher("/Usuario/colaboracionesRealizadas.jsp").forward(request, response);;
			} catch (Exception e) {
				DtMensajeUI msg = new DtMensajeUI(e.getMessage(), TipoMensaje.error);
				request.setAttribute("mensaje", msg);
				request.setAttribute("nickname", usuLog.getNickname());
				request.getRequestDispatcher("VerPerfil").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("/").forward(request, response);;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private DtColaboracion[] misColaboraciones(String nickname) throws ServiceException, ColaboracionNoExisteException, RemoteException {
		ControladorPropuestaPublishService cps = new ControladorPropuestaPublishServiceLocator();
		ControladorPropuestaPublish cpp;
		cpp = cps.getControladorPropuestaPublishPort();
		return cpp.listarColaboracionAPagar(nickname);
	}
	
	private void registrarAcceso(String ip, String url, String userAgent) throws ServiceException, publicadores.IOException, URISyntaxException, RemoteException {
		ControladorUsuarioPublishService cups = new ControladorUsuarioPublishServiceLocator();
		ControladorUsuarioPublish port = cups.getControladorUsuarioPublishPort();
		port.registrarAccesoAlSitio(ip, url, userAgent);
	}

}
