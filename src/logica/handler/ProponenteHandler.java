package logica.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.Collection;
//import org.hibernate.mapping.Collection;

import logica.Proponente;

public class ProponenteHandler {

	private Map<String, Proponente> proponentes; 
	private static ProponenteHandler instancia = null;

	private ProponenteHandler(){
		proponentes = new HashMap<String, Proponente>();
	}
    
	/**
     * Obtiene la instancia del manejador de proponentes de la aplicacion.
     */
	public static ProponenteHandler getInstance() {
		if (instancia == null)
			instancia = new ProponenteHandler();
		return instancia;
	}
	
    public Map<String, Proponente> getProponentes() {
        if (proponentes.isEmpty())
            return null;
        else {                       
            return proponentes;
        }
    }
    

    public Proponente obtenerProponente(String nickname) {
        return ((Proponente) proponentes.get(nickname));
    }
    
    public void agregarProponente(Proponente prop) {
        String nickname = prop.getNickname();
        proponentes.put(nickname, prop);
    }
    
	
}
