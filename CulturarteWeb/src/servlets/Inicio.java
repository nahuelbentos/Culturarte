package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import publicadores.ControladorPropuestaPublish;
import publicadores.ControladorPropuestaPublishService;
import publicadores.ControladorPropuestaPublishServiceLocator;
import publicadores.ControladorUsuarioPublish;
import publicadores.ControladorUsuarioPublishService;
import publicadores.ControladorUsuarioPublishServiceLocator;
import publicadores.DtColaborador;
import publicadores.DtProponente;
import publicadores.DtPropuesta;

/**
 * Servlet implementation class Inicio
 */
@WebServlet("/Inicio")
public class Inicio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inicio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		DtPropuesta[] populares;
		try {
			populares = this.getPropuestasPopulares();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	DtProponente[] mayoresProponentes;
		try {
			mayoresProponentes = this.getMasProponedores();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	DtColaborador[] mayoresColaboradores;
		try {
			mayoresColaboradores = this.getMasColaboradores();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
		session.setAttribute("mayColaboradores", mayoresColaboradores);
		session.setAttribute("mayProponentes", mayoresProponentes);
		session.setAttribute("masPopulares", populares);
		
    	request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	//OPERACIÓN CONSUMIDA
	private DtPropuesta[] getPropuestasPopulares() throws Exception {
		ControladorPropuestaPublishService cpps = new ControladorPropuestaPublishServiceLocator();
		ControladorPropuestaPublish port = cpps.getControladorPropuestaPublishPort();
		return port.getPropuestasPopulares();
	}
	
	private DtProponente[] getMasProponedores() throws Exception {
		ControladorUsuarioPublishService cups = new ControladorUsuarioPublishServiceLocator();
		ControladorUsuarioPublish port = cups.getControladorUsuarioPublishPort();
		return port.getMasProponedores();
	}
	
	private DtColaborador[] getMasColaboradores() throws Exception {
		ControladorUsuarioPublishService cups = new ControladorUsuarioPublishServiceLocator();
		ControladorUsuarioPublish port = cups.getControladorUsuarioPublishPort();
		return port.getMasColaboradores();
	}

}
