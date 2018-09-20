package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datatype.DtProponente;
import datatype.DtUsuario;
import excepciones.UsuarioNoExisteElUsuarioException;
import logica.Factory;
import logica.IUsuarioController;

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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String manejo = request.getParameter("manejoSesion");
		if (manejo.equals("iniciar")) {
			System.out.println("Iniciar sesion");
			String usuario = request.getParameter("usuario");
			String password = request.getParameter("password");
			
			Factory factory = Factory.getInstance();
			IUsuarioController iUsuCon = factory.getIUsuarioController();
			try {
				DtUsuario usuarioLogueado = iUsuCon.iniciarSesion(usuario, password);

				HttpSession session = request.getSession();
				session.setAttribute("usuarioLogueado", usuarioLogueado);
				
				RequestDispatcher rd;
				rd = request.getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
				
			} catch (UsuarioNoExisteElUsuarioException u) {
				// TODO: handle exception
			}
		}else {
			System.out.println("Cerrar sesion.");
			HttpSession session = request.getSession();
			session.setAttribute("usuarioLogueado", null);
			
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}

	}

}
