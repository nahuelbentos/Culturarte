package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import datatypeJee.DtPropuestaWeb;
import publicadores.ControladorPropuestaPublish;
import publicadores.ControladorPropuestaPublishService;
import publicadores.ControladorPropuestaPublishServiceLocator;

/**
 * Servlet implementation class Buscador
 */
@WebServlet("/Buscador")
public class Buscador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Buscador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorPropuestaPublishService cps = new ControladorPropuestaPublishServiceLocator();
		ControladorPropuestaPublish cpp;
		try {
			cpp = cps.getControladorPropuestaPublishPort();
	    	String cadena = request.getParameter("search");
	    	
	    	publicadores.DtPropuestaMinificado[] pAux = cpp.propuestasDesdeBuscador(cadena);
	    	DtPropuestaWeb[] props = new DtPropuestaWeb[pAux.length];
	    	for (int i = 0; i < pAux.length; i++) {
				props[i] = new DtPropuestaWeb(pAux[i].getTitulo(), pAux[i].getProponente(), pAux[i].getImagen(), null, null, null, null);		
			}
	    	request.setAttribute("listaPropuestas", props);
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/Propuesta/navegarPropuestas.jsp");
			rd.forward(request, response);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
