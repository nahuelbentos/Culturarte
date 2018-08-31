package logica;

import java.util.ArrayList;

import datatype.DtColaboracion;
import datatype.DtDatosPropuesta;
import datatype.DtPropuesta;
import datatype.DtPropuestaMinificado;
import datatype.EstadoPropuesta;
import logica.exceptions.ColaboradorNoExisteException;
import logica.exceptions.PropuestaNoExisteException;

public interface IPropuestaController {

	public abstract boolean altaPropuesta(DtPropuesta dtPropuesta);
	
	public abstract DtPropuestaMinificado[] listarPropuestas() throws PropuestaNoExisteException;
	
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

	public abstract void generarColaboracion(DtColaboracion colaboracion) throws ColaboradorNoExisteException, PropuestaNoExisteException;
	
	public abstract void nuevaColaboracionAuxiliarHarcode();

}
