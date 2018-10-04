package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excepciones.PropuestaNoExisteException;
import logica.Factory;
import logica.IPropuestaController;

/**
 * Servlet implementation class CancelarPropuesta
 */
@WebServlet("/CancelarPropuesta")
public class CancelarPropuesta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelarPropuesta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String titulo = request.getParameter("titulo");
		
		Factory factory = Factory.getInstance();
		IPropuestaController iPropCont = factory.getIPropuestaController();
		
		
		try {
			//esto luego vemos como llamarlo desde algun ajax para no consumir tanto recurso, de momento llamo a todo junto.
//			HttpSession session = request.getSession();
			iPropCont.cancelarPropuesta(titulo);
			
			
			request.setAttribute("mensaje", "Se cancelo correctamente la propuesta: " + titulo);
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("ListarPropuestaProponenteEstado?estado=financiada");
			rd.forward(request, response);
		} catch (PropuestaNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
