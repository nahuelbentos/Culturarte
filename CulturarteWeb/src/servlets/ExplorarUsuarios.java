 package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datatype.DtUsuario;
import logica.Factory;
import logica.IUsuarioController;

/**
 * Servlet implementation class ExplorarUsuarios
 */
@WebServlet("/ExplorarUsuarios")
public class ExplorarUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExplorarUsuarios() {
    	super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	Factory factory = Factory.getInstance();
    	IUsuarioController iUsuCont = factory.getIUsuarioController();
    	
    	DtUsuario[] listadoUsuarios = iUsuCont.listarUsuarios();
    	request.setAttribute("listaUsuarios", listadoUsuarios);
    	
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/Usuario/navegarUsuarios.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
