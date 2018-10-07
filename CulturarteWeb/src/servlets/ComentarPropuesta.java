package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datatype.DtPropuesta;
import datatype.DtUsuario;
import datatypeJee.DtPropuestaWeb;
import excepciones.UsuarioSinLoguearseException;
import logica.Factory;
import logica.IUsuarioController;

@WebServlet("/ComentarPropuesta")
public class ComentarPropuesta extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 public ComentarPropuesta() {
	        super();
	    }

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			DtUsuario usuario = (DtUsuario)request.getSession().getAttribute("usuarioLogueado");
			
			Factory factory = Factory.getInstance();
	    	IUsuarioController IUC = factory.getIUsuarioController();
	    	
	    	DtPropuesta[] pAux;
			try {
				pAux = IUC.listarPropuestasColaborador(usuario);
		    	DtPropuestaWeb[] props = new DtPropuestaWeb[pAux.length];
		    	
		    	for (int i = 0; i < pAux.length; i++) {
					props[i] = new DtPropuestaWeb(pAux[i].getTitulo(), pAux[i].getProponenteACargo().getNickname(), pAux[i].getImagen(), null, null, null, null);		
				}
		    	
		    	request.setAttribute("listaPropuestas", props);
				RequestDispatcher rd;
				rd = request.getRequestDispatcher("/Propuesta/navegarPropuestas.jsp");
				rd.forward(request, response);
			} catch (UsuarioSinLoguearseException e) {
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
