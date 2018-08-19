package logica.handler;

import java.util.ArrayList;

import logica.Colaborador;

public class ColaboradorHandler {

	private ArrayList<Colaborador> colaboradores = new ArrayList<Colaborador>();
	private static ColaboradorHandler instancia = null;
	
	private ColaboradorHandler(){}
    
	/**
     * Obtiene la instancia del manejador de proponentes de la aplicacion.
     */
	public static ColaboradorHandler getInstance() {
		if (instancia == null)
			instancia = new ColaboradorHandler();
		return instancia;
	}
	
}
