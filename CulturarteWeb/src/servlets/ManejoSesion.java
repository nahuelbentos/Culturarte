package servlets;

import java.io.IOException;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.rpc.ServiceException;

import org.apache.tomcat.util.codec.binary.Base64;

import publicadores.ControladorUsuarioPublish;
import publicadores.ControladorUsuarioPublishService;
import publicadores.ControladorUsuarioPublishServiceLocator;
import publicadores.DtProponente;
import publicadores.DtUsuario;
import publicadores.URISyntaxException;
import datatypeJee.TipoUsuario;
import publicadores.UsuarioNoExisteElUsuarioException;
//import logica.Factory;
//import logica.IUsuarioController;

/**
 * Servlet implementation class IniciarSesion
 */
@WebServlet("/ManejoSesion")
public class ManejoSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManejoSesion() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String manejo = request.getParameter("manejoSesion");
		if (manejo.equals("iniciar")) {
			String usuario = request.getParameter("usuario");
			String password = request.getParameter("password");
			
			ControladorUsuarioPublishService cppsl = new ControladorUsuarioPublishServiceLocator();
			ControladorUsuarioPublish ccp;
			try {
				ccp = cppsl.getControladorUsuarioPublishPort();
				try {
					DtUsuario usuarioLogueado = ccp.iniciarSesion(usuario, password);
					
					if (Arrays.equals(usuarioLogueado.getPasswordString().toCharArray(), password.toCharArray())) {
						HttpSession session = request.getSession();
						session.setAttribute("usuarioLogueado", usuarioLogueado);
						
						/* Para mostrar la imagen del usuario, se setea un atributo y desde jsp se levanta con
						 * 
						 * <img alt="img" src="data:image/jpeg;base64,${imagenPerfil}"/>
						 * 
						 */
						if (usuarioLogueado.getImagen() != null) {
				            byte[] encodeBase64 = Base64.encodeBase64(usuarioLogueado.getImagen());
				            String base64Encoded = new String(encodeBase64, "UTF-8");
				            request.setAttribute("imagenPerfil", base64Encoded);
						}
						
						if (usuarioLogueado instanceof DtProponente) {
							session.setAttribute("tipoUsuarioLogueado", TipoUsuario.proponente);
						}else {
							session.setAttribute("tipoUsuarioLogueado", TipoUsuario.colaborador);
						}
	
						request.getRequestDispatcher("/index.jsp").forward(request, response);;
					} else {
						request.setAttribute("mensaje", "Password incorrecta");
						request.getRequestDispatcher("/iniciarSesionForm.jsp").forward(request, response);
					}
					
				} catch (UsuarioNoExisteElUsuarioException u) {
					request.setAttribute("mensaje", "No existe el usuario");
					request.getRequestDispatcher("/iniciarSesionForm.jsp").forward(request, response);
				}
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("usuarioLogueado", null);
			
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}

	}
	
	private void registrarAcceso(String ip, String url, String userAgent) throws ServiceException, publicadores.IOException, URISyntaxException, RemoteException {
		ControladorUsuarioPublishService cups = new ControladorUsuarioPublishServiceLocator();
		ControladorUsuarioPublish port = cups.getControladorUsuarioPublishPort();
		port.registrarAccesoAlSitio(ip, url, userAgent);
	}
}
