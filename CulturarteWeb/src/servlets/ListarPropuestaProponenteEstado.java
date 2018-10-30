package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.rpc.ServiceException;

import publicadores.ControladorPropuestaPublish;
import publicadores.ControladorPropuestaPublishService;
import publicadores.ControladorPropuestaPublishServiceLocator;
import publicadores.DtPropuestaMinificado;
import publicadores.DtUsuario;
import publicadores.EstadoPropuesta;
import datatypeJee.DtPropuestaWeb;
import datatypeJee.msjUI.DtMensajeUI;
import publicadores.PropuestaNoExisteException;
//import logica.Factory;
//import logica.IPropuestaController;

/**
 * Servlet implementation class ListarPropuestaProponenteEstado
 */
@WebServlet("/ListarPropuestaProponenteEstado")
public class ListarPropuestaProponenteEstado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarPropuestaProponenteEstado() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		EstadoPropuesta estado = EstadoPropuesta.fromString(request.getParameter("estado"));
		DtMensajeUI mensaje = (DtMensajeUI)request.getAttribute("mensaje");

    	request.setAttribute("mensaje", mensaje);

    	ControladorPropuestaPublishService cppsl = new ControladorPropuestaPublishServiceLocator();
		ControladorPropuestaPublish cpp;
		try {
			cpp = cppsl.getControladorPropuestaPublishPort();
			
			//esto luego vemos como llamarlo desde algun ajax para no consumir tanto recurso, de momento llamo a todo junto.
			HttpSession session = request.getSession();
			DtUsuario user = (DtUsuario)session.getAttribute("usuarioLogueado");
			try {

		    	
		    	DtPropuestaMinificado[] pAux = cpp.listarPropuestasProponentePorEstado(user.getNickname(),estado);
		    	DtPropuestaWeb[] props = new DtPropuestaWeb[pAux.length];
				
		    	for (int i = 0; i < pAux.length; i++) {
					props[i] = new DtPropuestaWeb(pAux[i].getTitulo(), pAux[i].getProponente(), pAux[i].getImagen(), null, null, null, estado);		
				}
		    	
		    	request.setAttribute("propuestas", props);
				RequestDispatcher rd;
				//rd = request.getRequestDispatcher("/Propuesta/navegarPropuestas.jsp");
				
				rd = request.getRequestDispatcher("/Propuesta/navegarPropuestasACancelar.jsp");
				rd.forward(request, response);
				
			} catch (PropuestaNoExisteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ServiceException e1) {
			e1.printStackTrace();
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
