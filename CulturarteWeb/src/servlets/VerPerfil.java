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

import publicadores.ControladorUsuarioPublish;
import publicadores.ControladorUsuarioPublishService;
import publicadores.ControladorUsuarioPublishServiceLocator;
import publicadores.DtColaborador;
import publicadores.DtPerfilUsuario;
import publicadores.DtProponente;
import publicadores.DtUsuario;
import publicadores.URISyntaxException;
import datatypeJee.DtUsuarioWeb;
import datatypeJee.TipoUsuario;
import datatypeJee.msjUI.DtMensajeUI;
import publicadores.UsuarioNoExisteElUsuarioException;
//import logica.Factory;
//import logica.IUsuarioController;

/**
 * Servlet implementation class VerPerfil
 */
@WebServlet("/VerPerfil")
public class VerPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerPerfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * Lo comento para obtenerlo de la sesion y poder mostrar el perfil despues de loguearse
		 * String nickname = request.getParameter("nickname");
		 */
		
		HttpSession session = request.getSession();
		
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
		
		String nickname = request.getParameter("nickname");
		if (nickname == null) {
			DtUsuario user = (DtUsuario) session.getAttribute("usuarioLogueado");
			nickname = user.getNickname();
		}
		
		ControladorUsuarioPublishService cupsl = new ControladorUsuarioPublishServiceLocator();
		ControladorUsuarioPublish cup;
		try {
			cup = cupsl.getControladorUsuarioPublishPort();
			DtMensajeUI mensaje = (DtMensajeUI)request.getAttribute("mensaje");
			try {
				//esto luego vemos como llamarlo desde algun ajax para no consumir tanto recurso, de momento llamo a todo junto.
				//HttpSession session = request.getSession();
				DtPerfilUsuario  perfilCompleto = cup.obtenerPerfilUsuario(nickname, (DtUsuario)session.getAttribute("usuarioLogueado"));
				
				DtUsuario perfilAux = cup.verPerfilUsuario(nickname);
				DtUsuarioWeb perfil;
				
				if (perfilAux instanceof DtProponente) {
					perfil = new DtUsuarioWeb(perfilAux.getNickname(), perfilAux.getNombre(), perfilAux.getApellido(),
							perfilAux.getEmail(), perfilAux.getPasswordString(), perfilAux.getFechaNacimiento(), perfilAux.getImagen(), TipoUsuario.proponente,
							((DtProponente) perfilAux).getDireccion(), ((DtProponente) perfilAux).getBiografia(), ((DtProponente) perfilAux).getSitioWeb());
					
				}else if (perfilAux instanceof DtColaborador) {
					perfil = new DtUsuarioWeb(perfilAux.getNickname(), perfilAux.getNombre(), perfilAux.getApellido(),
							perfilAux.getEmail(), perfilAux.getPasswordString(), perfilAux.getFechaNacimiento(), perfilAux.getImagen(), TipoUsuario.colaborador,
							null, null, null); // como es colaborador inicializo la direccion, la biografia y el sitio web en null.
				
				}else {
					perfil = null;
				}
				
				request.setAttribute("perfil", perfil);
				request.setAttribute("perfilCompleto", perfilCompleto);
				request.setAttribute("mensaje", mensaje);
				RequestDispatcher rd;
				rd = request.getRequestDispatcher("/Usuario/perfil.jsp");
				rd.forward(request, response);
			} catch (UsuarioNoExisteElUsuarioException e) {
				e.printStackTrace();
			}
		} catch (ServiceException e1) {
			e1.printStackTrace();
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
