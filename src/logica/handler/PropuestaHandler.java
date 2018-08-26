package logica.handler;

import java.util.HashMap;
import java.util.Map;

import logica.Propuesta;

public class PropuestaHandler {

	private Map<String, Propuesta> propuestas;
	private static PropuestaHandler instancia = null;
	
	private PropuestaHandler(){
		propuestas  = new HashMap<String, Propuesta>();
	}
    
	/**
     * Obtiene la instancia del manejador de proponentes de la aplicacion.
     */
	public static PropuestaHandler getInstance() {
		if (instancia == null)
			instancia = new PropuestaHandler();
		return instancia;
	}
	
    public Map<String, Propuesta> getPropuestas() {
        if (propuestas.isEmpty())
            return null;
        else {                       
            return propuestas;
        }
    }
    

    public Propuesta obtenerPropuesta(String titulo) {
        return ((Propuesta) propuestas.get(titulo));
    }
    
    public void agregarPropuesta(Propuesta prop) {
        String titulo = prop.getTitulo();
        propuestas.put(titulo, prop);
    }
	
}
