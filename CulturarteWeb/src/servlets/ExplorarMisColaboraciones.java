package servlets;

import java.io.IOException;
import java.rmi.RemoteException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import datatypeJee.msjUI.DtMensajeUI;
import datatypeJee.msjUI.TipoMensaje;
import publicadores.ColaboracionNoExisteException;
import publicadores.ControladorPropuestaPublish;
import publicadores.ControladorPropuestaPublishService;
import publicadores.ControladorPropuestaPublishServiceLocator;
import publicadores.DtColaboracion;
import publicadores.DtColaborador;
import publicadores.DtUsuario;

/**
 * Servlet implementation class ExplorarColaboraciones
 */
@WebServlet("/ExplorarMisColaboraciones")
public class ExplorarMisColaboraciones extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExplorarMisColaboraciones() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DtUsuario usuLog = (DtUsuario)request.getSession().getAttribute("usuarioLogueado");
		
		if (usuLog instanceof DtColaborador) {
			try {
				DtColaboracion[] colhechas = this.misColaboraciones(usuLog.getNickname());
				request.setAttribute("colaboracionesHechas", colhechas);
				request.getRequestDispatcher("/Usuario/colaboracionesRealizadas.jsp").forward(request, response);;
			} catch (ServiceException | RemoteException e) {
				DtMensajeUI msg = new DtMensajeUI(e.getMessage(), TipoMensaje.error);
				request.setAttribute("mensaje", msg);
				request.setAttribute("nickname", usuLog.getNickname());
				request.getRequestDispatcher("VerPerfil").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("/").forward(request, response);;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private DtColaboracion[] misColaboraciones(String nickname) throws ServiceException, ColaboracionNoExisteException, RemoteException {
		ControladorPropuestaPublishService cps = new ControladorPropuestaPublishServiceLocator();
		ControladorPropuestaPublish cpp;
		cpp = cps.getControladorPropuestaPublishPort();
		return cpp.listarColaboracionAPagar(nickname);
	}

}
