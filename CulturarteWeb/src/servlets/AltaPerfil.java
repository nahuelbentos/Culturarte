package servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import datatype.DtColaborador;
import datatype.DtProponente;
import datatype.DtUsuario;
import excepciones.UsuarioYaExisteElEmailException;
import excepciones.UsuarioYaExisteElUsuarioException;
import logica.Factory;
import logica.IUsuarioController;

@WebServlet("/AltaPerfil")
@MultipartConfig
public class AltaPerfil extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public AltaPerfil() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boton = request.getParameter("submit");
		if (boton.equals("registrarse")) {
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			String nickname = request.getParameter("nickanme");
			char[] password = request.getParameter("password").toCharArray();
			char[] confirmarPassword = request.getParameter("confirmarPassword").toCharArray();
			String email = request.getParameter("email");
			String direccion = request.getParameter("direccion");
			String biografia = request.getParameter("biografia");
			String sitioWeb = request.getParameter("sitioWeb");
			String tipoUsuario = request.getParameter("tipoUsuario");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
			GregorianCalendar fecha = new GregorianCalendar();
			try {
				fecha.setTime(sdf.parse(request.getParameter("fechaDeNacimiento")));
			} catch (ParseException e1) {
				request.setAttribute("mensaje", "La fecha ingresada no es válida");
				request.getRequestDispatcher("/registrarseForm.jsp").forward(request, response);
			}
			
			/*
			 * Para obtener Parts en el servlet:
			 * - Se debe definir en el servlet la annotation @MultipartConfig (ver mas arriba)
			 * - Se debe definir en el jsp el enctype="multipart/form-data" (ver registrarseForm.jsp)
			 */
			Part file = request.getPart("imagenUsuarioArchivo");
			byte[] imagen = new byte[(int) file.getSize()];
	    	file.getInputStream().read(imagen);
	    	
			Factory factory = Factory.getInstance();
			IUsuarioController IUC = factory.getIUsuarioController();

			DtUsuario dtUsuario = null;
			
			if ("proponente".equals(tipoUsuario) || "colaborador".equals(tipoUsuario)) {
				if ("proponente".equals(tipoUsuario)) {
					dtUsuario = new DtProponente(nickname, nombre, apellido, email, password, fecha, 
							imagen, direccion, biografia, sitioWeb);
				} else if ("colaborador".equals(tipoUsuario)) {
					dtUsuario = new DtColaborador(nickname, nombre, apellido, email, confirmarPassword, 
							fecha, imagen);
				}
				if (Arrays.equals(password, confirmarPassword)) {
					try {
						IUC.agregarUsuario(dtUsuario);
						HttpSession session = request.getSession();
						session.setAttribute("usuarioLogueado", dtUsuario);
						
						RequestDispatcher rd;
						rd = request.getRequestDispatcher("/estaLogueado.jsp");
						rd.forward(request, response);
					} catch (UsuarioYaExisteElUsuarioException | UsuarioYaExisteElEmailException e) {
						request.setAttribute("mensaje", "Ya existe el nickname o el email");
						request.getRequestDispatcher("/registrarseForm.jsp").forward(request, response);
					}
				} else {
					request.setAttribute("mensaje", "Las passwords no coinciden");
					request.getRequestDispatcher("/registrarseForm.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("mensaje", "Debe seleccionar Proponente o Colaborador");
				request.getRequestDispatcher("/registrarseForm.jsp").forward(request, response);
			}
		} else if (boton.equals("cancelar")) {
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}
	}
}
