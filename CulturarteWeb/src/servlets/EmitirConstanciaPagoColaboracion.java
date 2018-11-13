package servlets;

import java.io.IOException;
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
import publicadores.DtColaborador;
import publicadores.DtInfoPago;
import publicadores.DtUsuario;
import publicadores.TipoPagoInexistenteExpection;

@WebServlet("/EmitirConstanciaPagoColaboracion")
public class EmitirConstanciaPagoColaboracion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmitirConstanciaPagoColaboracion() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		DtUsuario usuLog = (DtUsuario)request.getSession().getAttribute("usuarioLogueado");
		String propuesta = request.getParameter("propuesta");
		
		if (usuLog instanceof DtColaborador) {
			try {
				DtInfoPago infoPago = this.obtenerInfoPago(usuLog.getNickname(), propuesta);
				request.setAttribute("infoPago", infoPago);
				request.getRequestDispatcher("/Usuario/constanciaDePagoColaboracion.jsp").forward(request, response);
			} catch (RemoteException | ServiceException e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Ocurrió un problema al intentar pagar la colaboración: \n" + e.getMessage());
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

}
