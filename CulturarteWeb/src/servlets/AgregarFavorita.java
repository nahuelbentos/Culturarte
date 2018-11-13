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

import datatypeJee.msjUI.DtMensajeUI;
import datatypeJee.msjUI.TipoMensaje;
import publicadores.ControladorPropuestaPublish;
import publicadores.ControladorPropuestaPublishService;
import publicadores.ControladorPropuestaPublishServiceLocator;
import publicadores.ControladorUsuarioPublish;
import publicadores.ControladorUsuarioPublishService;
import publicadores.ControladorUsuarioPublishServiceLocator;
import publicadores.DtUsuario;
import publicadores.DtPropuesta;
import publicadores.UsuarioSinLoguearseException;

//import datatype.DtUsuario;
//import excepciones.UsuarioSinLoguearseException;
//import excepciones.UsuarioYaExisteFavoritaException;

//import logica.Factory;
//import logica.IPropuestaController;

/**
 * Servlet implementation class AgregarFavorita
 */
@WebServlet("/AgregarFavorita")
public class AgregarFavorita extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarFavorita() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String titulo = request.getParameter("propuesta");
		
		HttpSession session = request.getSession();
		DtUsuario usuarioLogueado = (DtUsuario)session.getAttribute("usuarioLogueado");
//		DtUsuarioWeb usuarioLogueado = (DtUsuarioWeb)session.getAttribute("usuarioLogueado");
		

		ControladorPropuestaPublishService cpp = new ControladorPropuestaPublishServiceLocator();
		ControladorPropuestaPublish port;
		System.out.println("doGet \n");
		System.out.println("1\n");
		ControladorUsuarioPublishService cup = new ControladorUsuarioPublishServiceLocator();
		System.out.println("2 \n");
		ControladorUsuarioPublish port_u;

		System.out.println("3\n");
		try {
			port = cpp.getControladorPropuestaPublishPort();
			System.out.println("4\n");
			port_u = cup.getControladorUsuarioPublishPort();
			System.out.println("5\n");
			try {
				port.agregarFavorita(titulo, usuarioLogueado);
				//actualizo las favoritas del usuario logueado.
				System.out.println("6\n");
				DtPropuesta[] favoritas = port_u.listarFavoritasUsuario(usuarioLogueado.getNickname());
				System.out.println("7\n");
				
				String[] titFav = new String[favoritas.length];
				System.out.println("8\n");
		        for (int i = 0; i < favoritas.length; i++) {
		        	System.out.println("9"+i+"\n");
		        	titFav[i] = favoritas[i].getTitulo();
				}
		        
				System.out.println("10\n");
				usuarioLogueado.setTituloFavoritas(titFav);
				System.out.println("11\n");
				session.setAttribute("usuarioLogueado", usuarioLogueado);
			} catch (UsuarioSinLoguearseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		DtMensajeUI mensaje = new DtMensajeUI("Ahora "+titulo+" es una de tus favoritas.", TipoMensaje.informacion);
		request.setAttribute("mensaje", mensaje);
		
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("VerPropuesta?titulo="+titulo);
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String titulo = request.getParameter("propuesta");

		System.out.println("doPost\n");
		HttpSession session = request.getSession();
		DtUsuario usuarioLogueado = (DtUsuario)session.getAttribute("usuarioLogueado");

		ControladorPropuestaPublishService cpp = new ControladorPropuestaPublishServiceLocator();
		ControladorPropuestaPublish port;
		ControladorUsuarioPublishService cup = new ControladorUsuarioPublishServiceLocator();
		ControladorUsuarioPublish port_u;
		System.out.println("1\n");
		
		System.out.println("2\n");
		try {
			port = cpp.getControladorPropuestaPublishPort();
			System.out.println("3\n");
			port_u = cup.getControladorUsuarioPublishPort();
			System.out.println("4\n");
			try {
				System.out.println("5\n");
				port.agregarFavorita(titulo, usuarioLogueado);
				System.out.println("6\n");
				DtPropuesta[] favoritas = port_u.listarFavoritasUsuario(usuarioLogueado.getNickname());
				System.out.println("7\n");
				
				String[] titFav = new String[favoritas.length];
				System.out.println("8\n");
				for (int i = 0; i < favoritas.length; i++) {
					System.out.println("9."+i+"\n");
					titFav[i] = favoritas[i].getTitulo();
				}

				System.out.println("10\n");
				usuarioLogueado.setTituloFavoritas(titFav);
				System.out.println("11\n");
				
				session.setAttribute("usuarioLogueado", usuarioLogueado);
			} catch (UsuarioSinLoguearseException e) {
				e.printStackTrace();
			}
		} catch (ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write("Agregada a favorita");
	}

}
