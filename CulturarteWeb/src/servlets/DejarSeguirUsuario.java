package servlets;

import java.io.IOException;
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
import publicadores.DtUsuario;
import datatypeJee.msjUI.DtMensajeUI;
import datatypeJee.msjUI.TipoMensaje;
import logica.IUsuarioController;
import publicadores.UsuarioYaSigueAlUsuarioException;

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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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

}
