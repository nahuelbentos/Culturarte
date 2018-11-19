package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import publicadores.ControladorUsuarioPublish;
import publicadores.ControladorUsuarioPublishService;
import publicadores.ControladorUsuarioPublishServiceLocator;

/**
 * Servlet implementation class ValidarNicknameEmail
 */
@WebServlet("/ValidarNicknameEmail")
public class ValidarNicknameEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidarNicknameEmail() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String datoSesion = request.getParameter("datoSesion");
		String esNickname = request.getParameter("esNickname");
		boolean esValido = false;
		
		ControladorUsuarioPublishService cup = new ControladorUsuarioPublishServiceLocator();
		ControladorUsuarioPublish port;
		
		try {
			port = cup.getControladorUsuarioPublishPort();
			if (esNickname.equals("false")) {
				esValido = port.verificarNicknameEmail(datoSesion, false);
			}else {
				esValido = port.verificarNicknameEmail(datoSesion, true);
			}
							
		} catch (ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
		
	    if(esNickname.equals("false")) {
		    if(esValido)
		    	response.getWriter().write("El email está disponible");
		    else
		    	response.getWriter().write("El email está en uso");
	    }else {
	    	if(esValido)
		    	response.getWriter().write("El nickname está disponible");
		    else
		    	response.getWriter().write("El nickname está en uso");
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
