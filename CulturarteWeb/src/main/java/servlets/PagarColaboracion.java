package servlets;

import java.io.IOException;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import datatypeJee.msjUI.DtMensajeUI;
import datatypeJee.msjUI.TipoMensaje;
import publicadores.ControladorPropuestaPublish;
import publicadores.ControladorPropuestaPublishService;
import publicadores.ControladorPropuestaPublishServiceLocator;
import publicadores.ControladorUsuarioPublish;
import publicadores.ControladorUsuarioPublishService;
import publicadores.ControladorUsuarioPublishServiceLocator;
import publicadores.DtInfoPago;
import publicadores.DtPagoPayPal;
import publicadores.DtPagoTarjeta;
import publicadores.DtPagoTrfBancaria;
import publicadores.DtUsuario;
import publicadores.TipoPagoInexistenteExpection;
import publicadores.TipoTarjeta;
import publicadores.URISyntaxException;

/**
 * Servlet implementation class PagarColaboracion
 */
@WebServlet("/PagarColaboracion")
public class PagarColaboracion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PagarColaboracion() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String p = request.getParameter("p");
		request.setAttribute("propuesta", p);
		
		request.getRequestDispatcher("/Usuario/formPagoColaboracion.jsp").forward(request, response);
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
		
		String tipoPago = request.getParameter("tipoPago");
		String propuesta = request.getParameter("propuesta");
		
		DtUsuario colLog = (DtUsuario)request.getSession().getAttribute("usuarioLogueado");
		String colaborador = colLog.getNickname();
		
		DtInfoPago infoPago = new DtInfoPago();
		
		infoPago.setNickColaborador(colaborador);
		infoPago.setTitPropuesta(propuesta);

		if ("Tarjeta".equals(tipoPago)) {
			DtPagoTarjeta pagoTar = new DtPagoTarjeta();

			TipoTarjeta tipoTarjeta = TipoTarjeta.fromValue(request.getParameter("tipoTarjeta"));
			pagoTar.setTipoTarjeta(tipoTarjeta);

			String titular = request.getParameter("titularTarjeta");
			pagoTar.setNombreTitular(titular);

			String nroTarjetaStr = request.getParameter("nroTarjeta");
			double nroTarjeta = Double.valueOf(nroTarjetaStr);

			pagoTar.setNroTarjeta(nroTarjeta);

			String fechaStr = request.getParameter("fchVencTarjeta");
			int anio = Integer.parseInt(fechaStr.substring(3, fechaStr.length()));
			int mes = Integer.parseInt(fechaStr.substring(0, 2));
			Calendar fechaVenc = new GregorianCalendar(anio, mes, 1);
			pagoTar.setFechaVenc(fechaVenc);

			int cvc = Integer.valueOf(request.getParameter("CVCTarjeta"));
			pagoTar.setCvc(cvc);

			infoPago.setPago(pagoTar);
		} else if ("TrfBria".equals(tipoPago)) {
			DtPagoTrfBancaria pagoTrf = new DtPagoTrfBancaria();

			String nombreBanco = request.getParameter("bancoTrfBria");
			pagoTrf.setNombreBanco(nombreBanco);

			String numCuenta = request.getParameter("nroCuentaTrfBria");
			pagoTrf.setNumCuenta(numCuenta);

			String nombreTitular = request.getParameter("titularTrfBria");
			pagoTrf.setNombreTitular(nombreTitular);

			infoPago.setPago(pagoTrf);
		} else if ("PayPal".equals(tipoPago)) {
			DtPagoPayPal pagoPP = new DtPagoPayPal();

			String titularPP = request.getParameter("titularPaypal");
			pagoPP.setNombreTitular(titularPP);

			String nroCtaPP = request.getParameter("cuentaPaypal");
			pagoPP.setNumeroCuenta(nroCtaPP);
			infoPago.setPago(pagoPP);
		}
		
		try {
			this.pagarColaboracion(infoPago);
			DtMensajeUI msg = new DtMensajeUI("Pago realizado correctamente", TipoMensaje.informacion);
			request.setAttribute("mensaje", msg);
			request.setAttribute("nickname", colaborador);
			request.setAttribute("propuesta", propuesta);
			request.getRequestDispatcher("EnviarCorreo").forward(request,response);
			
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void pagarColaboracion(DtInfoPago infoPago) throws ServiceException, TipoPagoInexistenteExpection, RemoteException {
		ControladorPropuestaPublishService cpps = new ControladorPropuestaPublishServiceLocator();
		ControladorPropuestaPublish cpp = cpps.getControladorPropuestaPublishPort();
		cpp.pagarColaboracion(infoPago);
	}
	
	private void registrarAcceso(String ip, String url, String userAgent) throws ServiceException, publicadores.IOException, URISyntaxException, RemoteException {
		ControladorUsuarioPublishService cups = new ControladorUsuarioPublishServiceLocator();
		ControladorUsuarioPublish port = cups.getControladorUsuarioPublishPort();
		port.registrarAccesoAlSitio(ip, url, userAgent);
	}

}
