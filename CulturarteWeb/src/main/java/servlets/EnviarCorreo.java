package servlets;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import datatypeJee.msjUI.DtMensajeUI;
import datatypeJee.msjUI.TipoMensaje;
import logica.EmailCliente;
import publicadores.ControladorPropuestaPublish;
import publicadores.ControladorPropuestaPublishService;
import publicadores.ControladorPropuestaPublishServiceLocator;
import publicadores.ControladorUsuarioPublish;
import publicadores.ControladorUsuarioPublishService;
import publicadores.ControladorUsuarioPublishServiceLocator;
import publicadores.DtColaboracion;
import publicadores.DtColaborador;
import publicadores.DtPropuesta;
import publicadores.DtUsuario;
import publicadores.UsuarioNoExisteElUsuarioException;

/**
 * Servlet implementation class EnviarCorreo
 */
@WebServlet("/EnviarCorreo")
public class EnviarCorreo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnviarCorreo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Testeo del envio de correo");
		
		try {
		
			String colaborador = (String)request.getAttribute("nickname");
			String propuesta = (String)request.getAttribute("propuesta");
			DtPropuesta dtp = this.getPropuesta(propuesta);
			DtColaborador dtc = this.getColaborador(colaborador);
			DtColaboracion dtcp = this.getColaboracion(propuesta, colaborador);
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
			String ahora = sdf.format(GregorianCalendar.getInstance().getTime());
			String asunto = "[Culturarte] ["+ahora+"] Pago de colaboracion registrado";
			
			String urlComprobante = "http://"+request.getLocalAddr()+":"+request.getLocalPort()+"/CulturarteWeb/EmitirConstanciaPagoColaboracion?propuesta="+propuesta;
			
			String htmlColaboracion = "<b>Propuesta:</b> "+ dtp.getTitulo() + "<br>"
									+ "<b>Proponente:</b> "+dtp.getProponenteACargo().getNickname() + "<br>"
									+ "<b>Colaborador:</b> "+dtc.getNombre() + "<br>"
									+ "<b>Monto:</b> "+dtcp.getMonto() + "<br>"
									+ "<b>Fecha de pago:</b> "+sdf.format(dtcp.getFechaAporte().getTime()) +"<br>";
			
			String htmlProponente = "Estimado " + dtp.getProponenteACargo().getNombre() + " " + dtp.getProponenteACargo().getApellido() + " <br>"
							+ " El pago correspondiente a la propuesta "+ dtp.getTitulo() + " por " + colaborador + " se realizo correctamente. <br><br>"
							+ " <h4>Datos de la propuesta: </h4>"
							+ htmlColaboracion;
			String htmlColaborador = "Estimado "+dtc.getNombre()+" "+dtc.getApellido() + "<br>"
							+ " El pago correspondiente a la propuesta " + dtp.getTitulo() + " se realizo correctamente. <br><br>"
							+ " <h4>Datos de la propuesta:</h4>"
							+ htmlColaboracion
							+ "<a href='"+urlComprobante+"'>Ver el comprobante de pago</a>";
			
			EmailCliente.sendAsHtml(dtp.getProponenteACargo().getEmail(), asunto, htmlProponente);
			EmailCliente.sendAsHtml(dtc.getEmail(), asunto, htmlColaborador);
			
			
			request.getRequestDispatcher("VerPerfil").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("mensaje", new DtMensajeUI("Verificar si fue efectuado el pago. <br>Para poder probar los correos en ambientes de testeo es necesario tener levantado el jar DevNullSmtp<br> ubicado en las librerias del directorio virtual.", TipoMensaje.error));
			request.getRequestDispatcher("/").forward(request, response);
			System.out.println("hubo un error en el envio!"+e.getMessage());
		}
		
	}	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private DtPropuesta getPropuesta(String titulo) throws ServiceException, RemoteException {
		ControladorPropuestaPublishService cppsl = new ControladorPropuestaPublishServiceLocator();
		ControladorPropuestaPublish cpp = cppsl.getControladorPropuestaPublishPort();
		return cpp.seleccionarPropuesta(titulo);
	}
	
	private DtColaboracion getColaboracion(String titulo, String nickname) throws ServiceException, RemoteException {
		ControladorPropuestaPublishService cppsl = new ControladorPropuestaPublishServiceLocator();
		ControladorPropuestaPublish cpp = cppsl.getControladorPropuestaPublishPort();
		return cpp.obtenerColaboracion(nickname, titulo);
	}
	
	
	private DtColaborador getColaborador(String nickname) throws ServiceException, UsuarioNoExisteElUsuarioException, RemoteException {
		ControladorUsuarioPublishService cups = new ControladorUsuarioPublishServiceLocator();
		ControladorUsuarioPublish cup = cups.getControladorUsuarioPublishPort();
		DtUsuario usu = cup.verPerfilUsuario(nickname);
		return (DtColaborador)usu;
	}
	
	

}
