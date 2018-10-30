package servlets;

import java.io.IOException;

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
import publicadores.DtPerfilUsuario;
import publicadores.DtPropuesta;
import publicadores.DtUsuario;
import datatypeJee.DtPropuestaWeb;
import publicadores.UsuarioSinLoguearseException;
//import logica.Factory;
//import logica.IUsuarioController;

@WebServlet("/ComentarPropuesta")
public class ComentarPropuesta extends HttpServlet {

	/**
	 * 
	 */
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
				DtUsuario usuario = (DtUsuario)request.getSession().getAttribute("usuarioLogueado");
				DtPerfilUsuario perfilCompleto = cup.obtenerPerfilUsuario(usuario.getNickname(), (DtUsuario)session.getAttribute("usuarioLogueado"));
		    	
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
			} catch (ServiceException e1) {
				e1.printStackTrace();
			}
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String boton = request.getParameter("submit");
			if (boton.equals("confirmar")) {
				DtUsuario usuario = (DtUsuario)request.getSession().getAttribute("usuarioLogueado");
				String titulo = (String) request.getSession().getAttribute("propuestaAComentar");
				String comentario = request.getParameter("comentario");
				
				ControladorUsuarioPublishService cupsl = new ControladorUsuarioPublishServiceLocator();
				ControladorUsuarioPublish cup;
				try {
					cup = cupsl.getControladorUsuarioPublishPort();
					
			    	try {
						cup.agregarComentarioAPropuesta(comentario, titulo, usuario);
						request.getSession().setAttribute("mensaje", "Se registro con exito el comentario");
					} catch (UsuarioSinLoguearseException e) {
						request.getSession().setAttribute("mensaje", "Ocurrio un error");
					}
			    	response.sendRedirect("/CulturarteWeb/ExplorarPropuestas");
				} catch (ServiceException e1) {
					e1.printStackTrace();
				}
			}
		}
}
