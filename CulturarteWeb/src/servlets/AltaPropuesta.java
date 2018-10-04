package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import datatype.DtCategoria;
import datatype.DtProponente;
import datatype.DtPropuesta;
import datatype.DtUsuario;
import datatype.EstadoPropuesta;
import datatype.TipoRetorno;
import datatypeJee.msjUI.DtMensajeUI;
import datatypeJee.msjUI.TipoMensaje;
import excepciones.CategoriaNoExisteException;
import excepciones.ProponenteNoExisteException;
import excepciones.PropuestaRepetidaException;
import logica.Factory;
import logica.IPropuestaController;

@WebServlet("/AltaPropuesta")
@MultipartConfig
public class AltaPropuesta extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public AltaPropuesta() {
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
		DtUsuario user = (DtUsuario)request.getSession().getAttribute("usuarioLogueado");
		if (user instanceof DtProponente) {
			DtProponente dtProponente = (DtProponente) user;
			if (boton.equals("confirmar")) {
				String categoria = request.getParameter("categoria");
				String titulo = request.getParameter("titulo");
				String descripcion = request.getParameter("descripcion");
				String lugar = request.getParameter("lugar");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				GregorianCalendar fecha = new GregorianCalendar();
				try {
					fecha.setTime(sdf.parse(request.getParameter("fecha")));
				} catch (ParseException e1) {
					request.setAttribute("mensaje", "La fecha ingresada no es válida");
					request.getRequestDispatcher("/Propuesta/altaDePropuesta.jsp").forward(request, response);
				}
				Float precioEntrada = Float.valueOf(request.getParameter("precioEntrada"));
				Float montoNecesario = Float.valueOf(request.getParameter("montoNecesario"));
				TipoRetorno tipoRetorno = TipoRetorno.valueOf(request.getParameter("tipoRetorno"));
				
				/*
				 * Para obtener Parts en el servlet:
				 * - Se debe definir en el servlet la annotation @MultipartConfig (ver mas arriba)
				 * - Se debe definir en el jsp el enctype="multipart/form-data" (ver altaDePropuesta.jsp)
				 */
				Part file = request.getPart("imagenPropuestaArchivo");
				byte[] imagen = new byte[(int) file.getSize()];
		    	file.getInputStream().read(imagen);
		    	
				Factory factory = Factory.getInstance();
				IPropuestaController IPC = factory.getIPropuestaController();
				
				DtCategoria dtC = new DtCategoria(categoria);
				
				DtPropuesta dtP = new DtPropuesta(titulo, descripcion, imagen, montoNecesario, new GregorianCalendar(), fecha, lugar, precioEntrada, tipoRetorno, 0, dtProponente, EstadoPropuesta.ingresada, null, dtC, null);
				
				try {
					IPC.altaPropuesta(dtP);;
					HttpSession session = request.getSession();
					session.setAttribute("titulo", titulo);
					
					DtMensajeUI mensaje = new DtMensajeUI("Propuesta dada de alta correctamente <br> Debe ser evaluada por nuestros especialistas", TipoMensaje.aviso);
					request.setAttribute("mensaje", mensaje);
					request.getRequestDispatcher("/VerPropuesta").forward(request, response);
				}catch (PropuestaRepetidaException e) {
					request.setAttribute("mensaje", "Ya existe la propuesta");
					request.getRequestDispatcher("/Propuesta/altaDePropuesta.jsp").forward(request, response);
				}catch (ProponenteNoExisteException e) {
					request.setAttribute("mensaje", "No existe el proponente");
					request.getRequestDispatcher("/Propuesta/altaDePropuesta.jsp").forward(request, response);
				}catch (CategoriaNoExisteException e) {
					request.setAttribute("mensaje", "No existe la categoría");
					request.getRequestDispatcher("/Propuesta/altaDePropuesta.jsp").forward(request, response);
				}
			} else if (boton.equals("cancelar")) {
				RequestDispatcher rd;
				rd = request.getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
			}
		} else {
			request.setAttribute("mensaje", "Debe iniciar sesión como proponente para poder crear una nueva propuesta.");
			request.getRequestDispatcher("/Propuesta/altaDePropuesta.jsp").forward(request, response);
		}
	}

}