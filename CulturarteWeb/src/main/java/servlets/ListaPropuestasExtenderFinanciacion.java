package servlets;

import java.io.IOException;
import java.net.InetAddress;
import java.rmi.RemoteException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import publicadores.DtUsuario;
import publicadores.URISyntaxException;
import publicadores.ControladorPropuestaPublish;
import publicadores.ControladorPropuestaPublishService;
import publicadores.ControladorPropuestaPublishServiceLocator;
import publicadores.ControladorUsuarioPublish;
import publicadores.ControladorUsuarioPublishService;
import publicadores.ControladorUsuarioPublishServiceLocator;

@WebServlet("/ListaPropuestasExtenderFinanciacion")
@MultipartConfig
public class ListaPropuestasExtenderFinanciacion extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	public ListaPropuestasExtenderFinanciacion() {
        super();
    }

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
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		ControladorPropuestaPublishService cps = new ControladorPropuestaPublishServiceLocator();
		ControladorPropuestaPublish cpp;
		try {
			cpp = cps.getControladorPropuestaPublishPort();
			
			DtUsuario user = (DtUsuario) request.getSession().getAttribute("usuarioLogueado");
			if (user instanceof publicadores.DtProponente) {
				try {
					publicadores.DtPropuestaMinificado[] publicadas = cpp.listarPropuestasProponentePorEstado(user.getNickname(), publicadores.EstadoPropuesta.publicada);
					request.setAttribute("propuestasPublicadas", publicadas);
				} catch (publicadores.PropuestaNoExisteException e) {
					request.setAttribute("mensaje", e);
				}
				
				try {
					publicadores.DtPropuestaMinificado[] enFinanciacion = cpp.listarPropuestasProponentePorEstado(user.getNickname(), publicadores.EstadoPropuesta.enFinanciacion);
					request.setAttribute("propuestasEnFinanciacion", enFinanciacion);
				} catch (publicadores.PropuestaNoExisteException e) {
					request.setAttribute("mensaje", e);
				}
			} else {
				request.setAttribute("mensaje", "Debe iniciar sesión como proponente para poder extender la financiación de una propuesta.");
			}
			request.getRequestDispatcher("/Propuesta/extenderFinanciacion.jsp").forward(request, response);
		
		} catch (ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void registrarAcceso(String ip, String url, String userAgent) throws ServiceException, publicadores.IOException, URISyntaxException, RemoteException {
		ControladorUsuarioPublishService cups = new ControladorUsuarioPublishServiceLocator();
		ControladorUsuarioPublish port = cups.getControladorUsuarioPublishPort();
		port.registrarAccesoAlSitio(ip, url, userAgent);
	}
}
