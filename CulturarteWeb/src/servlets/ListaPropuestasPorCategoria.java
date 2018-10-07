package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datatype.DtPropuesta;
import datatype.DtPropuestaMinificado;
import logica.Factory;
import logica.IPropuestaController;

@WebServlet("/ListaPropuestasPorCategoria")
public class ListaPropuestasPorCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListaPropuestasPorCategoria() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String categoria = request.getParameter("categoria");
		
		Factory fab = Factory.getInstance();
		IPropuestaController IPC = fab.getIPropuestaController();
		
		ArrayList<DtPropuesta> propuestas = IPC.listarPropuestasPorCategoria(categoria);
		
		ArrayList<DtPropuestaMinificado> listaIngresada = new ArrayList<DtPropuestaMinificado>();
		ArrayList<DtPropuestaMinificado> listaPublicada = new ArrayList<DtPropuestaMinificado>();
		ArrayList<DtPropuestaMinificado> listaEnFinanciacion = new ArrayList<DtPropuestaMinificado>();
		ArrayList<DtPropuestaMinificado> listaFinanciada = new ArrayList<DtPropuestaMinificado>();
		ArrayList<DtPropuestaMinificado> listaNoFinanciada = new ArrayList<DtPropuestaMinificado>();
		ArrayList<DtPropuestaMinificado> listaCancelada = new ArrayList<DtPropuestaMinificado>();
		
		for (DtPropuesta prop : propuestas) {
			switch(prop.getEstadoActual()) {
			case ingresada:
				listaIngresada.add(new DtPropuestaMinificado(prop.getTitulo(), prop.getProponenteACargo().getNickname(), prop.getImagen()));
				break;
			case publicada:
				listaPublicada.add(new DtPropuestaMinificado(prop.getTitulo(), prop.getProponenteACargo().getNickname(), prop.getImagen()));
				break;
			case enFinanciacion:
				listaEnFinanciacion.add(new DtPropuestaMinificado(prop.getTitulo(), prop.getProponenteACargo().getNickname(), prop.getImagen()));
				break;
			case financiada:
				listaFinanciada.add(new DtPropuestaMinificado(prop.getTitulo(), prop.getProponenteACargo().getNickname(), prop.getImagen()));
				break;
			case noFinanciada:
				listaNoFinanciada.add(new DtPropuestaMinificado(prop.getTitulo(), prop.getProponenteACargo().getNickname(), prop.getImagen()));
				break;
			case cancelada:
				listaCancelada.add(new DtPropuestaMinificado(prop.getTitulo(), prop.getProponenteACargo().getNickname(), prop.getImagen()));
				break;
			}
		}
		
		request.setAttribute("listaIngresada", listaIngresada);
		request.setAttribute("listaPublicada", listaPublicada);
		request.setAttribute("listaEnFinanciacion", listaEnFinanciacion);
		request.setAttribute("listaFinanciada", listaFinanciada);
		request.setAttribute("listaNoFinanciada", listaNoFinanciada);
		request.setAttribute("listaCancelada", listaCancelada);
		
		request.getRequestDispatcher("/Propuesta/propuestasPorEstado.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
