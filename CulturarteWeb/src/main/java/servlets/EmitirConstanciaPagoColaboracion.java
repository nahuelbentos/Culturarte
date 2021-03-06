package servlets;

import java.io.IOException;
import java.net.InetAddress;
import java.rmi.RemoteException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import publicadores.ColaboracionNoExisteException;
import publicadores.ControladorPropuestaPublish;
import publicadores.ControladorPropuestaPublishService;
import publicadores.ControladorPropuestaPublishServiceLocator;
import publicadores.ControladorUsuarioPublish;
import publicadores.ControladorUsuarioPublishService;
import publicadores.ControladorUsuarioPublishServiceLocator;
import publicadores.DtColaborador;
import publicadores.DtInfoPago;
import publicadores.DtUsuario;
import publicadores.TipoPagoInexistenteExpection;
import publicadores.URISyntaxException;

@WebServlet("/EmitirConstanciaPagoColaboracion")
public class EmitirConstanciaPagoColaboracion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmitirConstanciaPagoColaboracion() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		
		DtUsuario usuLog = (DtUsuario)request.getSession().getAttribute("usuarioLogueado");
		String propuesta = request.getParameter("propuesta");
		
		if (usuLog instanceof DtColaborador) {
			try {
				marcarPagoComoEmitido(usuLog.getNickname(), propuesta);
				DtInfoPago infoPago = this.obtenerInfoPago(usuLog.getNickname(), propuesta);
				request.setAttribute("infoPago", infoPago);
				request.setAttribute("propuesta", propuesta);
				request.getRequestDispatcher("/Usuario/verConstanciaPagoColaboracion.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("mensaje", "Ocurrió un problema al intentar obtener el comprobante de pago de la colaboración: \n" + e.getMessage());
				request.getRequestDispatcher("/Usuario/colaboracionesRealizadas.jsp").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("/").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private DtInfoPago obtenerInfoPago(String nickname, String tituloP) throws ColaboracionNoExisteException, TipoPagoInexistenteExpection, RemoteException, ServiceException {
		ControladorPropuestaPublishService cppsl = new ControladorPropuestaPublishServiceLocator();
		ControladorPropuestaPublish cpp = cppsl.getControladorPropuestaPublishPort();
		return cpp.obtenerComprobanteDePagoDeColaboracion(nickname, tituloP);
	}
	
	private void marcarPagoComoEmitido(String nickname, String tituloP) throws ColaboracionNoExisteException, RemoteException, ServiceException {
		ControladorPropuestaPublishService cppsl = new ControladorPropuestaPublishServiceLocator();
		ControladorPropuestaPublish cpp = cppsl.getControladorPropuestaPublishPort();
		cpp.marcarPagoComoEmitido(nickname, tituloP);
	}

	private void registrarAcceso(String ip, String url, String userAgent) throws ServiceException, publicadores.IOException, URISyntaxException, RemoteException {
		ControladorUsuarioPublishService cups = new ControladorUsuarioPublishServiceLocator();
		ControladorUsuarioPublish port = cups.getControladorUsuarioPublishPort();
		port.registrarAccesoAlSitio(ip, url, userAgent);
	}
}
