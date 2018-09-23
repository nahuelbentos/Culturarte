package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datatype.DtUsuario;
import excepciones.UsuarioNoExisteElUsuarioException;
import logica.Factory;
import logica.IUsuarioController;

@WebServlet("/AltaPerfil")
public class AltaPerfil extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public AltaPerfil() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boton = request.getParameter("submit");
		if (boton.equals("registrarse")) {
			System.out.println("Registrarse");
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			String nickanme = request.getParameter("nickanme");
			String password = request.getParameter("password");
			String confirmarPassword = request.getParameter("confirmarPassword");
			String email = request.getParameter("email");
			
			Factory factory = Factory.getInstance();
			IUsuarioController IUC = factory.getIUsuarioController();
			
//			DtUsuario dtUsuario
//			IUC.agregarUsuario(arg0);
//				DtUsuario usuarioLogueado = iUsuCon.iniciarSesion(usuario, password);
//
//				if (usuarioLogueado.getPassword().equals(password)) {
//					HttpSession session = request.getSession();
//					session.setAttribute("usuarioLogueado", usuarioLogueado);
//					
//					RequestDispatcher rd;
//					rd = request.getRequestDispatcher("/index.jsp");
//					rd.forward(request, response);
//				} else {
//					request.setAttribute("mensaje", "Password incorrecta");
//					request.getRequestDispatcher("/iniciarSesionForm.jsp").forward(request, response);
//				}

		} else if (boton.equals("cancelar")) {
			System.out.println("Cancelar");
//			HttpSession session = request.getSession();
//			session.setAttribute("usuarioLogueado", null);
//			
//			RequestDispatcher rd;
//			rd = request.getRequestDispatcher("/index.jsp");
//			rd.forward(request, response);
		}

	}
}
