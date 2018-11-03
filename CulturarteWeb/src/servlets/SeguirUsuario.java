package servlets;

import java.io.IOException;

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
import publicadores.UsuarioYaSigueAlUsuarioException;

@WebServlet("/SeguirUsuario")
public class SeguirUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public SeguirUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		DtUsuario user = (DtUsuario) session.getAttribute("usuarioLogueado");
		
		String nickname = user.getNickname();
		
		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
		try {
			//esto luego vemos como llamarlo desde algun ajax para no consumir tanto recurso, de momento llamo a todo junto.
			//HttpSession session = request.getSession();
				
			String nicknameSeguir = request.getParameter("nickname");
			seguirUsuario(nickname,nicknameSeguir);
			DtUsuario[] seguidos = listarSeguidos(nickname);
			session.setAttribute("seguidos", seguidos);
		    response.getWriter().write("Empezaste a seguir al usuario: " + nicknameSeguir);
			
		} catch (UsuarioYaSigueAlUsuarioException e) {
		    response.getWriter().write(e.getMessage());
			
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void seguirUsuario(String nickname, String nicknameSeguir) throws ServiceException, Exception {
		ControladorUsuarioPublishService cupsl = new ControladorUsuarioPublishServiceLocator();
		ControladorUsuarioPublish cup = cupsl.getControladorUsuarioPublishPort();
		cup.seguirUsuario(nickname, nicknameSeguir);
	}
	
	private DtUsuario[] listarSeguidos(String nickname) throws ServiceException, Exception {
		ControladorUsuarioPublishService cupls = new ControladorUsuarioPublishServiceLocator();
		ControladorUsuarioPublish cup = cupls.getControladorUsuarioPublishPort();
		return cup.listarUsuariosQueSigue(nickname);
	}

}
