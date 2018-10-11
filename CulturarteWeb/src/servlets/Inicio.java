package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datatype.DtColaborador;
import datatype.DtProponente;
import datatype.DtPropuesta;
import logica.Factory;
import logica.IPropuestaController;
import logica.IUsuarioController;

/**
 * Servlet implementation class Inicio
 */
@WebServlet("/Inicio")
public class Inicio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inicio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Factory factory = Factory.getInstance();
    	//IUsuarioController iUsuCont = factory.getIUsuarioController();
    	IPropuestaController iProCont = factory.getIPropuestaController();
    	
    	DtPropuesta[] populares					= iProCont.getPropuestasPopulares();
    	//DtProponente[] mayoresProponentes		= iUsuCont.getMasProponedores();
    	//DtColaborador[] mayoresColaboradores	= iUsuCont.getMasColaboradores();
    	
    	HttpSession session = request.getSession();
		//session.setAttribute("mayColaboradores", mayoresColaboradores);
		//session.setAttribute("mayProponentes", mayoresProponentes);
		session.setAttribute("masPopulares", populares);
		
    	request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
