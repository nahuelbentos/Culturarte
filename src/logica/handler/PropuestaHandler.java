package logica.handler;

import java.util.ArrayList;

import logica.Propuesta;

public class PropuestaHandler {

	private ArrayList<Propuesta> propuestas;
	private static PropuestaHandler instancia = null;
	
	private PropuestaHandler(){
		propuestas = new ArrayList<Propuesta>();
	}
    
	/**
     * Obtiene la instancia del manejador de proponentes de la aplicacion.
     */
	public static PropuestaHandler getInstance() {
		if (instancia == null)
			instancia = new PropuestaHandler();
		return instancia;
	}
	
	public void addPropuesta(Propuesta prop) {
		propuestas.add(prop);
	}
}
