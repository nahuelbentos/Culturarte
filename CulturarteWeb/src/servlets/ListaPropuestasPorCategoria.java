package servlets;

import java.io.IOException;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import publicadores.ControladorPropuestaPublish;
import publicadores.ControladorPropuestaPublishService;
import publicadores.ControladorPropuestaPublishServiceLocator;
import publicadores.ControladorUsuarioPublish;
import publicadores.ControladorUsuarioPublishService;
import publicadores.ControladorUsuarioPublishServiceLocator;
import publicadores.DtPropuesta;
import publicadores.DtPropuestaMinificado;
import publicadores.URISyntaxException;

@WebServlet("/ListaPropuestasPorCategoria")
public class ListaPropuestasPorCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListaPropuestasPorCategoria() {
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
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String categoria = request.getParameter("categoria");
		
		try {
			DtPropuesta[] propuestas = listarPropuestasPorCategoria(categoria);
			
			ArrayList<DtPropuestaMinificado> listaIngresada = new ArrayList<DtPropuestaMinificado>();
			ArrayList<DtPropuestaMinificado> listaPublicada = new ArrayList<DtPropuestaMinificado>();
			ArrayList<DtPropuestaMinificado> listaEnFinanciacion = new ArrayList<DtPropuestaMinificado>();
			ArrayList<DtPropuestaMinificado> listaFinanciada = new ArrayList<DtPropuestaMinificado>();
			ArrayList<DtPropuestaMinificado> listaNoFinanciada = new ArrayList<DtPropuestaMinificado>();
			ArrayList<DtPropuestaMinificado> listaCancelada = new ArrayList<DtPropuestaMinificado>();
			
			for (DtPropuesta prop : propuestas) {
				DtPropuestaMinificado dtPM = new DtPropuestaMinificado();
				dtPM.setTitulo(prop.getTitulo());
				dtPM.setProponente(prop.getProponenteACargo().getNickname());
				dtPM.setImagen(prop.getImagen());
				
				switch(prop.getEstadoActual().getValue()) {
				case "ingresada":
					listaIngresada.add(dtPM);
					break;
				case "publicada":
					listaPublicada.add(dtPM);
					break;
				case "enFinanciacion":
					listaEnFinanciacion.add(dtPM);
					break;
				case "financiada":
					listaFinanciada.add(dtPM);
					break;
				case "noFinanciada":
					listaNoFinanciada.add(dtPM);
					break;
				case "cancelada":
					listaCancelada.add(dtPM);
					break;
				}
			}
			
			request.setAttribute("listaIngresada", listaIngresada);
			request.setAttribute("listaPublicada", listaPublicada);
			request.setAttribute("listaEnFinanciacion", listaEnFinanciacion);
			request.setAttribute("listaFinanciada", listaFinanciada);
			request.setAttribute("listaNoFinanciada", listaNoFinanciada);
			request.setAttribute("listaCancelada", listaCancelada);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/Propuesta/propuestasPorEstado.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private DtPropuesta[] listarPropuestasPorCategoria(String categoria) throws ServiceException, Exception {
		ControladorPropuestaPublishService ccpls = new ControladorPropuestaPublishServiceLocator();
		ControladorPropuestaPublish ccp = ccpls.getControladorPropuestaPublishPort();
		return ccp.listarPropuestasPorCategoria(categoria);
	}

	private void registrarAcceso(String ip, String url, String userAgent) throws ServiceException, publicadores.IOException, URISyntaxException, RemoteException {
		ControladorUsuarioPublishService cups = new ControladorUsuarioPublishServiceLocator();
		ControladorUsuarioPublish port = cups.getControladorUsuarioPublishPort();
		port.registrarAccesoAlSitio(ip, url, userAgent);
	}
}
