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

import publicadores.ControladorUsuarioPublish;
import publicadores.ControladorUsuarioPublishService;
import publicadores.ControladorUsuarioPublishServiceLocator;
import publicadores.DtUsuario;
import publicadores.URISyntaxException;

@WebServlet("/DejarSeguirUsuario")
public class DejarSeguirUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DejarSeguirUsuario() {
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
		
		HttpSession session = request.getSession();
		DtUsuario user = (DtUsuario) session.getAttribute("usuarioLogueado");
		
		String nickname = user.getNickname();
		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
		String nicknameSeguir = request.getParameter("nickname");
		
		try {
			dejarDeSeguir(nickname, nicknameSeguir);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		DtUsuario[] seguidos = null;
		try {
			seguidos = (DtUsuario[]) listarSeguidos(nickname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		session.setAttribute("seguidos", seguidos);
		session.setAttribute("usuarioLogueado", user);
		response.getWriter().write("Dejaste de seguir al usuario: " + nicknameSeguir);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void dejarDeSeguir(String nickname, String nicknameSeguir) throws ServiceException, Exception {
		ControladorUsuarioPublishService cupls = new ControladorUsuarioPublishServiceLocator();
		ControladorUsuarioPublish cup = cupls.getControladorUsuarioPublishPort();
		cup.dejarDeSeguirUsuario(nickname, nicknameSeguir);
	}
	
	private DtUsuario[] listarSeguidos(String nickname) throws ServiceException, Exception {
		ControladorUsuarioPublishService cupls = new ControladorUsuarioPublishServiceLocator();
		ControladorUsuarioPublish cup = cupls.getControladorUsuarioPublishPort();
		return cup.listarUsuariosQueSigue(nickname);
	}
	
	private void registrarAcceso(String ip, String url, String userAgent) throws ServiceException, publicadores.IOException, URISyntaxException, RemoteException {
		ControladorUsuarioPublishService cups = new ControladorUsuarioPublishServiceLocator();
		ControladorUsuarioPublish port = cups.getControladorUsuarioPublishPort();
		port.registrarAccesoAlSitio(ip, url, userAgent);
	}

}
