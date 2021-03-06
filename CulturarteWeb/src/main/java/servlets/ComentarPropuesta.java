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

import publicadores.DtPerfilUsuario;
import publicadores.DtPropuesta;
import publicadores.DtUsuario;
import publicadores.URISyntaxException;
import publicadores.UsuarioSinLoguearseException;
import publicadores.ControladorUsuarioPublish;
import publicadores.ControladorUsuarioPublishService;
import publicadores.ControladorUsuarioPublishServiceLocator;
import datatypeJee.DtPropuestaWeb;
//import logica.Factory;
//import logica.IUsuarioController;

@WebServlet("/ComentarPropuesta")
public class ComentarPropuesta extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ComentarPropuesta() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		ControladorUsuarioPublishService cupsl = new ControladorUsuarioPublishServiceLocator();
		ControladorUsuarioPublish cup;
		try {
			cup = cupsl.getControladorUsuarioPublishPort();
			
	    	HttpSession session = request.getSession();
			DtUsuario usuario = (DtUsuario)session.getAttribute("usuarioLogueado");
			DtPerfilUsuario perfilCompleto = cup.obtenerPerfilUsuario(usuario.getNickname(), usuario);
	    	
	    	DtPropuesta[] pAux;
	    	
	    	if (request.getParameter("titulo") == null) {	    	
				try {
					pAux = cup.listarPropuestasColaborador(usuario);
			    	DtPropuestaWeb[] props = new DtPropuestaWeb[pAux.length];
			    	
			    	for (int i = 0; i < pAux.length; i++) {
						props[i] = new DtPropuestaWeb(pAux[i].getTitulo(), pAux[i].getProponenteACargo().getNickname(), pAux[i].getImagen(), null, null, null, pAux[i].getEstadoActual());		
					}
			    	request.setAttribute("listaPropuestas", props);
			    	request.setAttribute("perfilCompleto", perfilCompleto);
					RequestDispatcher rd;
					rd = request.getRequestDispatcher("/Propuesta/navegarPropuestas.jsp");
					rd.forward(request, response);
				} catch (UsuarioSinLoguearseException e) {
					e.printStackTrace();
				}
	    	} else {
	    		String titulo = request.getParameter("titulo");
	    		request.getSession().setAttribute("titulo", titulo);	    		
	    		RequestDispatcher rd;
	    		rd = request.getRequestDispatcher("/Propuesta/comentarPropuesta.jsp");
				rd.forward(request, response);
	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		
		String boton = request.getParameter("submit");
		if (boton.equals("confirmar")) {
			DtUsuario usuario = (DtUsuario)request.getSession().getAttribute("usuarioLogueado");
			String titulo = (String) request.getSession().getAttribute("propuestaAComentar");
			String comentario = request.getParameter("comentario");
    		try {
    			this.agregarComentario(comentario, titulo, usuario);
				request.getSession().setAttribute("mensaje", "Se registro con exito el comentario");
			} catch (UsuarioSinLoguearseException e) {
				request.getSession().setAttribute("mensaje", "Ocurrio un error");
			} catch (Exception e) {
				request.getSession().setAttribute("mensaje", "Ocurrio un error");
				e.printStackTrace();
			}
	    	response.sendRedirect("/CulturarteWeb/ExplorarPropuestas");
		}
	}
	
	private void agregarComentario(String comentario, String propuesta, DtUsuario usuarioLogueado) throws Exception, UsuarioSinLoguearseException{
		ControladorUsuarioPublishService cups = new ControladorUsuarioPublishServiceLocator();
		ControladorUsuarioPublish port = cups.getControladorUsuarioPublishPort();
		port.agregarComentarioAPropuesta(comentario, propuesta, usuarioLogueado);
	}
	
	private void registrarAcceso(String ip, String url, String userAgent) throws ServiceException, publicadores.IOException, URISyntaxException, RemoteException {
		ControladorUsuarioPublishService cups = new ControladorUsuarioPublishServiceLocator();
		ControladorUsuarioPublish port = cups.getControladorUsuarioPublishPort();
		port.registrarAccesoAlSitio(ip, url, userAgent);
	}
}
