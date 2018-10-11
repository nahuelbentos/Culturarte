package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datatype.DtColaborador;
import datatype.DtPerfilUsuario;
import datatype.DtProponente;
import datatype.DtUsuario;
import datatypeJee.DtUsuarioWeb;
import datatypeJee.TipoUsuario;
import excepciones.UsuarioNoExisteElUsuarioException;
import logica.Factory;
import logica.IUsuarioController;

/**
 * Servlet implementation class VerPerfil
 */
@WebServlet("/VerPerfil")
public class VerPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerPerfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * Lo comento para obtenerlo de la sesion y poder mostrar el perfil despues de loguearse
		 * String nickname = request.getParameter("nickname");
		 */
		
		HttpSession session = request.getSession();
		
		String nickname = request.getParameter("nickname");
		if (nickname == null) {
			DtUsuario user = (DtUsuario) session.getAttribute("usuarioLogueado");
			nickname = user.getNickname();
		}
			
		Factory factory = Factory.getInstance();
		IUsuarioController iUsuCont = factory.getIUsuarioController();
		try {
			//esto luego vemos como llamarlo desde algun ajax para no consumir tanto recurso, de momento llamo a todo junto.
			//HttpSession session = request.getSession();
			DtPerfilUsuario  perfilCompleto = iUsuCont.obtenerPerfilUsuario(nickname, (DtUsuario)session.getAttribute("usuarioLogueado"));
			
			DtUsuario perfilAux = iUsuCont.verPerfilUsuario(nickname);
			DtUsuarioWeb perfil;
			
			if (perfilAux instanceof DtProponente) {
				perfil = new DtUsuarioWeb(perfilAux.getNickname(), perfilAux.getNombre(), perfilAux.getApellido(),
						perfilAux.getEmail(), perfilAux.getPassword(), perfilAux.getFechaNacimiento(), perfilAux.getImagen(), TipoUsuario.proponente,
						((DtProponente) perfilAux).getDireccion(), ((DtProponente) perfilAux).getBiografia(), ((DtProponente) perfilAux).getSitioWeb());
				
			}else if (perfilAux instanceof DtColaborador) {
				perfil = new DtUsuarioWeb(perfilAux.getNickname(), perfilAux.getNombre(), perfilAux.getApellido(),
						perfilAux.getEmail(), perfilAux.getPassword(), perfilAux.getFechaNacimiento(), perfilAux.getImagen(), TipoUsuario.colaborador,
						null, null, null); // como es colaborador inicializo la direccion, la biografia y el sitio web en null.
			
			}else {
				perfil = null;
			}
			
			request.setAttribute("perfil", perfil);
			request.setAttribute("perfilCompleto", perfilCompleto);
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/Usuario/perfil.jsp");
			rd.forward(request, response);
		} catch (UsuarioNoExisteElUsuarioException e) {
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
