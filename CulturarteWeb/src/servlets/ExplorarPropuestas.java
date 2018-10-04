package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datatype.DtPropuestaMinificado;
import datatypeJee.DtPropuestaWeb;
import logica.Factory;
import logica.IPropuestaController;

/**
 * Servlet implementation class ExplorarPropuestas
 */
@WebServlet("/ExplorarPropuestas")
public class ExplorarPropuestas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExplorarPropuestas() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Factory factory = Factory.getInstance();
    	IPropuestaController iProCont = factory.getIPropuestaController();
    	
    	DtPropuestaMinificado[] pAux = iProCont.listarPropuestasActivas();
    	DtPropuestaWeb[] props = new DtPropuestaWeb[pAux.length];
    	
    	for (int i = 0; i < pAux.length; i++) {
			props[i] = new DtPropuestaWeb(pAux[i].getTitulo(), pAux[i].getProponente(), pAux[i].getImagen(), null, null, null, null);		
		}
    	
    	request.setAttribute("listaPropuestas", props);
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/Propuesta/navegarPropuestas.jsp");
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
