 package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datatype.DtColaborador;
import datatype.DtProponente;
import datatype.DtUsuario;
import datatypeJee.DtColaboradorWeb;
import datatypeJee.DtProponenteWeb;
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
    	Factory factory = Factory.getInstance();
    	IUsuarioController iUsuCont = factory.getIUsuarioController();
    	
    	DtUsuario[] auxUsuarios = iUsuCont.listarUsuarios();
    	
    	List<DtColaboradorWeb> listaColaboradores = new ArrayList<DtColaboradorWeb>();
    	List<DtProponenteWeb> listaProponentes = new ArrayList<DtProponenteWeb>();
    	
    	for (DtUsuario dtUsuario : auxUsuarios) {
			if (dtUsuario instanceof DtColaborador) {
				listaColaboradores.add(new DtColaboradorWeb(dtUsuario.getNickname(), dtUsuario.getNombre(), 
						dtUsuario.getApellido(), dtUsuario.getEmail(), dtUsuario.getPassword(), dtUsuario.getFechaNacimiento(), dtUsuario.getImagen()));
			}else if (dtUsuario instanceof DtProponente) {
				listaProponentes.add(new DtProponenteWeb(dtUsuario.getNickname(), dtUsuario.getNombre(), 
						dtUsuario.getApellido(), dtUsuario.getEmail(), dtUsuario.getPassword(), dtUsuario.getFechaNacimiento(), dtUsuario.getImagen(),
						((DtProponente) dtUsuario).getBiografia(),((DtProponente) dtUsuario).getDireccion(),((DtProponente) dtUsuario).getSitioWeb()));
			}
		}
    	
    	request.setAttribute("listaColaboradores", listaColaboradores);
    	request.setAttribute("listaProponentes", listaProponentes);
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
