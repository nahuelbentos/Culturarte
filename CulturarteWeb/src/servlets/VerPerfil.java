package servlets;

import java.io.IOException;

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
		String nickname = request.getParameter("nickname");
		
		Factory factory = Factory.getInstance();
		IUsuarioController iUsuCont = factory.getIUsuarioController();
		try {
			DtUsuario perfilAux = iUsuCont.verPerfilUsuario(nickname);
			DtProponenteWeb perfilProp;
			DtColaboradorWeb perfilCol;
			if (perfilAux instanceof DtProponente) {
				perfilProp = new DtProponenteWeb(perfilAux.getNickname(), perfilAux.getNombre(), perfilAux.getApellido(),
						perfilAux.getEmail(), perfilAux.getPassword(), perfilAux.getFechaNacimiento(), perfilAux.getImagen(), 
						((DtProponente) perfilAux).getDireccion(), ((DtProponente) perfilAux).getBiografia(), ((DtProponente) perfilAux).getSitioWeb());
				
				request.setAttribute("tipoPerfil", TipoUsuario.proponente);
				request.setAttribute("perfil", perfilProp);
			}else if (perfilAux instanceof DtColaborador) {
				perfilCol = new DtColaboradorWeb(perfilAux.getNickname(), perfilAux.getNombre(), perfilAux.getApellido(),
						perfilAux.getEmail(), perfilAux.getPassword(), perfilAux.getFechaNacimiento(), perfilAux.getImagen());
				
				request.setAttribute("tipoPerfil", TipoUsuario.colaborador);
				request.setAttribute("perfil", perfilCol);
			}
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
