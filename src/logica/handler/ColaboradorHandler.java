package logica.handler;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import logica.Colaborador;
import logica.Usuario;

public class ColaboradorHandler {

	private Map<String, Colaborador> colaboradores;
	private static ColaboradorHandler instancia = null;
	
	private ColaboradorHandler(){
		colaboradores = new HashMap<String, Colaborador>();
	}
    
	/**
     * Obtiene la instancia del manejador de proponentes de la aplicacion.
     */
	public static ColaboradorHandler getInstance() {
		if (instancia == null)
			instancia = new ColaboradorHandler();
		return instancia;
	}
	
	public void addColaborador(Usuario col) {
		colaboradores.put(col.getNickname(), (Colaborador)col);
	}
	
	public Colaborador[] getColaboradores() {
        if (colaboradores.isEmpty())
            return null;
        else {
            Collection<Colaborador> cols = colaboradores.values();
            Object[] o = cols.toArray();
            Colaborador[] colTotal = new Colaborador[o.length];
            for (int i = 0; i < o.length; i++) {
                colTotal[i] = (Colaborador) o[i];
            }
            
            return colTotal;
        }
	}
}
