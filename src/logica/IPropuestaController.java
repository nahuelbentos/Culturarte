package logica;

import java.util.ArrayList;

import datatype.DtColaboracion;
import datatype.DtDatosPropuesta;
import datatype.DtPropuesta;
import datatype.EstadoPropuesta;

public interface IPropuestaController {

	public abstract boolean altaPropuesta(DtPropuesta dtPropuesta);
	
	public abstract DtPropuesta[] listarPropuestas();
	
	public abstract DtPropuesta seleccionarPropuesta(String titulo);
	
	public abstract boolean modificarPropuesta(DtPropuesta dtPropuesta);
	
	public abstract DtColaboracion[] listarColaboraciones();
	
	// Falta crear el DtEstadoPropuesta
	//public abstract DtEstadoPropuesta[] listarEstadosDePropuestas();
	public abstract ArrayList<DtPropuesta> listarPropuestasExistentes();
	
	// Falta crear el DtDatosPropuesta
	public DtDatosPropuesta consultarPropuesta(String titulo);
	
	public abstract DtPropuesta[] listarPropuestasPorEstado(EstadoPropuesta estadoPropuesta);
	
	public abstract boolean altaColaboracion(DtColaboracion dtColaboracion);
	
	// Revisar si est�n bien los par�metros que recibe
	public abstract boolean eliminarColaboraciones(String titulo, String nickname);

}
