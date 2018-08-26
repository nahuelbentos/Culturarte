package logica.handler;

import java.util.ArrayList;

import logica.Proponente;
import logica.Usuario;

public class ProponenteHandler {

	private ArrayList<Proponente> proponentes;
	private static ProponenteHandler instancia = null;
	
	private ProponenteHandler(){
		proponentes = new ArrayList<Proponente>();
	}
    
	/**
     * Obtiene la instancia del manejador de proponentes de la aplicacion.
     */
	public static ProponenteHandler getInstance() {
		if (instancia == null)
			instancia = new ProponenteHandler();
		return instancia;
	}
	
	public void addProponente(Usuario p) {
		proponentes.add((Proponente)p);
	}
	
}
