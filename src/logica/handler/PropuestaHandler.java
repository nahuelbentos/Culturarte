package logica.handler;

import java.util.ArrayList;

import logica.Propuesta;

public class PropuestaHandler {

	private ArrayList<Propuesta> colaboradores = new ArrayList<Propuesta>();
	private static PropuestaHandler instancia = null;
	
	private PropuestaHandler(){}
    
	/**
     * Obtiene la instancia del manejador de proponentes de la aplicacion.
     */
	public static PropuestaHandler getInstance() {
		if (instancia == null)
			instancia = new PropuestaHandler();
		return instancia;
	}
	
}
