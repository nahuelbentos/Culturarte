package servlets;

import java.io.IOException;
import java.net.InetAddress;
import java.rmi.RemoteException;
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
import javax.xml.rpc.ServiceException;

import publicadores.DtCategoria;
import publicadores.DtProponente;
import publicadores.DtPropuesta;
import publicadores.DtUsuario;
import publicadores.EstadoPropuesta;
import publicadores.TipoRetorno;
import publicadores.URISyntaxException;
import datatypeJee.msjUI.DtMensajeUI;
import datatypeJee.msjUI.TipoMensaje;
import publicadores.ControladorPropuestaPublish;
import publicadores.ControladorPropuestaPublishService;
import publicadores.ControladorPropuestaPublishServiceLocator;
import publicadores.ControladorUsuarioPublish;
import publicadores.ControladorUsuarioPublishService;
import publicadores.ControladorUsuarioPublishServiceLocator;

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
		
		String ip = request.getRemoteAddr();
		if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
		    InetAddress inetAddress = InetAddress.getLocalHost();
		    String ipAddress = inetAddress.getHostAddress();
		    ip = ipAddress;
		}
		
		String url = request.getRequestURI();
		String userAgent = request.getHeader("User-Agent");
		
		try {
			registrarAcceso(ip, url, userAgent);
		} catch (ServiceException e1) {
			e1.printStackTrace();
		}
		
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
				TipoRetorno tipoRetorno = TipoRetorno.fromValue(request.getParameter("tipoRetorno"));
				
				/*
				 * Para obtener Parts en el servlet:
				 * - Se debe definir en el servlet la annotation @MultipartConfig (ver mas arriba)
				 * - Se debe definir en el jsp el enctype="multipart/form-data" (ver altaDePropuesta.jsp)
				 */
				Part file = request.getPart("imagenPropuestaArchivo");
				byte[] imagen = new byte[(int) file.getSize()];
		    	file.getInputStream().read(imagen);
		    	
				DtCategoria dtC = new DtCategoria();
				dtC.setNombre(categoria);
				
				DtPropuesta dtP = new DtPropuesta();
				dtP.setTitulo(titulo);
				dtP.setDescripcion(descripcion);
				dtP.setImagen(imagen);	
				dtP.setMontoNecesario(montoNecesario);
				dtP.setFechaPublicacion(new GregorianCalendar());
				dtP.setFechaEspecatulo(fecha);
				dtP.setLugar(lugar);
				dtP.setPrecioEntrada(precioEntrada);
				dtP.setTipo(tipoRetorno);
				dtP.setProponenteACargo(dtProponente);
				dtP.setEstadoActual(EstadoPropuesta.ingresada);
				dtP.setCategoria(dtC);
				
				ControladorPropuestaPublishService cppsl = new ControladorPropuestaPublishServiceLocator();
				try {
					ControladorPropuestaPublish cpp = cppsl.getControladorPropuestaPublishPort();
					
					try {
						cpp.altaPropuesta(dtP);
						
						HttpSession session = request.getSession();
						session.setAttribute("titulo", titulo);
						
						DtMensajeUI mensaje = new DtMensajeUI("Propuesta dada de alta correctamente <br> Debe ser evaluada por nuestros especialistas", TipoMensaje.aviso);
						request.setAttribute("mensaje", mensaje);
						request.getRequestDispatcher("/VerPropuesta").forward(request, response);
					} catch (RemoteException e) {
						e.printStackTrace();
						request.setAttribute("mensaje", "Ocurrió un error al intentar crear la propuesta");
						request.getRequestDispatcher("/Propuesta/altaDePropuesta.jsp").forward(request, response);
					}
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
	
	private void registrarAcceso(String ip, String url, String userAgent) throws ServiceException, publicadores.IOException, URISyntaxException, RemoteException {
		ControladorUsuarioPublishService cups = new ControladorUsuarioPublishServiceLocator();
		ControladorUsuarioPublish port = cups.getControladorUsuarioPublishPort();
		port.registrarAccesoAlSitio(ip, url, userAgent);
	}

}