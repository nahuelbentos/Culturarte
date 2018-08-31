package logica.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

import logica.Colaboracion;

public class ColaboracionHandler {
	
	private Map<Long,Colaboracion> colaboraciones;
	private static ColaboracionHandler instancia = null;
	
	private ColaboracionHandler(){
		colaboraciones = new HashMap<Long, Colaboracion>();
	}
    
	/**
     * Obtiene la instancia del manejador de proponentes de la aplicacion.
     */
	public static ColaboracionHandler getInstance() {
		if (instancia == null)
			instancia = new ColaboracionHandler();
		return instancia;
	}
	/*
	public void addColaboracion(Colaboracion c) {
		colaboraciones.put(c., c);
	}
	*/
	public Map<Long,Colaboracion> getMapColaboraciones() {
        if (colaboraciones.isEmpty())
            return null;
        else {
            return colaboraciones;
        }
	}
	
	public Colaboracion[] getColaboraciones() {
        if (colaboraciones.isEmpty())
            return null;
        else {
            Collection<Colaboracion> cols = colaboraciones.values();
            Object[] o = cols.toArray();
            Colaboracion[] colTotal = new Colaboracion[o.length];
            for (int i = 0; i < o.length; i++) {
                colTotal[i] = (Colaboracion) o[i];
            }
            
            return colTotal;
        }
	}
	
}
