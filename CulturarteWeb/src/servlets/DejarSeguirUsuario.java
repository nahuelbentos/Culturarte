package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datatype.DtUsuario;
import datatypeJee.msjUI.DtMensajeUI;
import datatypeJee.msjUI.TipoMensaje;
import excepciones.UsuarioYaSigueAlUsuarioException;
import logica.Factory;
import logica.IUsuarioController;

/**
 * Servlet implementation class DejarSeguirUsuario
 */
@WebServlet("/DejarSeguirUsuario")
public class DejarSeguirUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DejarSeguirUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		DtUsuario user = (DtUsuario) session.getAttribute("usuarioLogueado");
		
		String nickname = user.getNickname();
		Factory factory = Factory.getInstance();
		IUsuarioController iUsuCont = factory.getIUsuarioController();
		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
		String nicknameSeguir = request.getParameter("nickname");
		iUsuCont.dejarDeSeguirUsuario(nickname,nicknameSeguir);

		// le saco de los seguidos del usuario 
		// en sesion para no leer de nuevo desde la base
		user.removeUsuarioSeguido(nicknameSeguir);
		session.setAttribute("usuarioLogueado", user);
		response.getWriter().write("Dejaste de seguir al usuario: " + nicknameSeguir);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
