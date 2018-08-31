package logica.handler;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import logica.Propuesta;
import logica.Usuario;

public class PropuestaHandler {

	private Map<String, Propuesta> propuestas;
	private static PropuestaHandler instancia = null;

	private static EntityManager em;
	private static EntityManagerFactory emf;
	
	private PropuestaHandler(){
		
		emf = Persistence.createEntityManagerFactory("Conexion");
		em = emf.createEntityManager();
		List<Propuesta> propAux = em.createQuery("SELECT p FROM Propuesta p", Propuesta.class).getResultList(); 
		
		for (Propuesta propuesta : propAux) {
			System.out.println(propuesta.getTitulo());	
		}
//		propuestas  = new HashMap<String, Propuesta>();
//		for (Propuesta propuesta : propAux) {
//			propuestas.put(propuesta.getTitulo(), propuesta);
//		}
		
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
    
    public Propuesta[] getPropuestasAsArray() {
        if (propuestas.isEmpty())
            return null;
        else {
            Collection<Propuesta> props = propuestas.values();
            Object[] o = props.toArray();
            Propuesta[] propuestasArray = new Propuesta[o.length];
            for (int i = 0; i < o.length; i++) {
                propuestasArray[i] = (Propuesta) o[i];
            }
            return propuestasArray;
        }
    }


    public Propuesta obtenerPropuesta(String titulo) {
        return ((Propuesta) propuestas.get(titulo));
    }

    public void agregarPropuesta(Propuesta prop) {
        String titulo = prop.getTitulo();
        propuestas.put(titulo, prop);
    }
    
    public boolean esMiembroPropuesta(String titulo) {
    	Propuesta prop = propuestas.get(titulo);
    	if (prop != null) {
			return true;
		}else {
			return false;
		}
    }

}
