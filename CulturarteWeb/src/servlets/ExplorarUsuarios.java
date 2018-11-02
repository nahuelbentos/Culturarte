 package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import publicadores.DtColaborador;
import publicadores.DtProponente;
import publicadores.DtUsuario;
import datatypeJee.DtColaboradorWeb;
import datatypeJee.DtProponenteWeb;

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
    	String accion = request.getParameter("accion");
    	String msg = request.getParameter("msg");
		HttpSession session = request.getSession();
		DtUsuario user = (DtUsuario) session.getAttribute("usuarioLogueado");
		String nickname = user.getNickname();
    	
    	DtUsuario[] auxUsuarios = null;
		try {
			auxUsuarios = listarUsuarios();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	List<DtColaboradorWeb> listaColaboradores = new ArrayList<DtColaboradorWeb>();
    	List<DtProponenteWeb> listaProponentes = new ArrayList<DtProponenteWeb>();
    	if (auxUsuarios.length > 0) {
		
	    	for (DtUsuario dtUsuario : auxUsuarios) {
				if (dtUsuario instanceof DtColaborador) {
					listaColaboradores.add(new DtColaboradorWeb(dtUsuario.getNickname(), dtUsuario.getNombre(), 
							dtUsuario.getApellido(), dtUsuario.getEmail(), dtUsuario.getFechaNacimiento(), dtUsuario.getImagen()));
				}else if (dtUsuario instanceof DtProponente) {
					listaProponentes.add(new DtProponenteWeb(dtUsuario.getNickname(), dtUsuario.getNombre(), 
							dtUsuario.getApellido(), dtUsuario.getEmail(), dtUsuario.getFechaNacimiento(), dtUsuario.getImagen(),
							((DtProponente) dtUsuario).getBiografia(),((DtProponente) dtUsuario).getDireccion(),((DtProponente) dtUsuario).getSitioWeb()));
				}
			}
	    	
	    	request.setAttribute("listaColaboradores", listaColaboradores);
	    	request.setAttribute("listaProponentes", listaProponentes);
	    	request.setAttribute("msg", msg);
	    	request.setAttribute("accion", accion);
			
    	}else {
	    	request.setAttribute("listaColaboradores", listaColaboradores);
	    	request.setAttribute("listaProponentes", listaProponentes);
	    	System.out.println(listaColaboradores.size());
    		request.setAttribute("msg", "No hay usuarios registrados en la aplicación.");
    	}
		
		DtUsuario[] seguidos = null;
		try {
			seguidos = listarSeguidos(nickname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("seguidos", seguidos);
    	request.getRequestDispatcher("/Usuario/navegarUsuarios.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private DtUsuario[] listarUsuarios() throws ServiceException, Exception {
		ControladorUsuarioPublishService cupls = new ControladorUsuarioPublishServiceLocator();
		ControladorUsuarioPublish cup = cupls.getControladorUsuarioPublishPort();
		return cup.listarUsuarios();
	}
	
	private DtUsuario[] listarSeguidos(String nickname) throws ServiceException, Exception {
		ControladorUsuarioPublishService cupls = new ControladorUsuarioPublishServiceLocator();
		ControladorUsuarioPublish cup = cupls.getControladorUsuarioPublishPort();
		return cup.listarUsuariosQueSigue(nickname);
	}

}
