package logica.handler;

import java.util.ArrayList;

import logica.Proponente;

public class ProponenteHandler {

	private ArrayList<Proponente> proponentes = new ArrayList<Proponente>();
	private static ProponenteHandler instancia = null;
	
	private ProponenteHandler(){}
    
	/**
     * Obtiene la instancia del manejador de proponentes de la aplicacion.
     */
	public static ProponenteHandler getInstance() {
		if (instancia == null)
			instancia = new ProponenteHandler();
		return instancia;
	}
	
}
