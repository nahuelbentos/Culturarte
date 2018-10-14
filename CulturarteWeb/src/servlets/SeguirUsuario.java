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
 * Servlet implementation class SeguirUsuario
 */
@WebServlet("/SeguirUsuario")
public class SeguirUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeguirUsuario() {
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
		try {
			//esto luego vemos como llamarlo desde algun ajax para no consumir tanto recurso, de momento llamo a todo junto.
			//HttpSession session = request.getSession();
				
			String nicknameSeguir = request.getParameter("nickname");
			iUsuCont.seguirUsuario(nickname,nicknameSeguir);
			user.addUsuarioSeguido(nicknameSeguir);
			session.setAttribute("usuarioLogueado", user);
//			DtMensajeUI msg = new DtMensajeUI("Empezaste a seguir al usuario: " + nicknameSeguir, TipoMensaje.informacion);
//			request.setAttribute("mensaje", msg);

		    response.getWriter().write("Empezaste a seguir al usuario: " + nicknameSeguir);
			
		} catch (UsuarioYaSigueAlUsuarioException e) {
			// TODO Auto-generated catch block
//			DtMensajeUI msg = new DtMensajeUI(e.getMessage(), TipoMensaje.error);
//			request.setAttribute("mensaje", msg);
		    response.getWriter().write(e.getMessage());
			
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
