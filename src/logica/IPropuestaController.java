package logica;

import datatype.DtColaboracion;
import datatype.DtDatosPropuesta;
import datatype.DtPropuesta;
import datatype.DtPropuestaMinificado;
import datatype.EstadoPropuesta;

import logica.exceptions.ColaboradorNoExisteException;
import logica.exceptions.PropuestaNoExisteException;
import logica.exceptions.CategoriaNoExisteException;
import logica.exceptions.ProponenteNoExisteException;
import logica.exceptions.PropuestaRepetidaException;

public interface IPropuestaController {

	public abstract void altaPropuesta(DtPropuesta dtPropuesta)  throws PropuestaRepetidaException, ProponenteNoExisteException, CategoriaNoExisteException;
	
	public abstract DtPropuestaMinificado[] listarPropuestas() throws PropuestaNoExisteException;
	
	public abstract DtPropuesta seleccionarPropuesta(String titulo);
	
	public abstract boolean modificarPropuesta(DtPropuesta dtPropuesta);
	
	public abstract DtColaboracion[] listarColaboraciones();
	
	// Falta crear el DtEstadoPropuesta
	//public abstract DtEstadoPropuesta[] listarEstadosDePropuestas();
	public abstract DtPropuesta[] listarPropuestasExistentes();
	
	// Falta crear el DtDatosPropuesta
	public DtDatosPropuesta consultarPropuesta(String titulo);
	
	public abstract DtPropuesta[] listarPropuestasPorEstado(EstadoPropuesta estadoPropuesta);
	
	public abstract boolean altaColaboracion(DtColaboracion dtColaboracion);
	
	// Revisar si est�n bien los par�metros que recibe
	public abstract boolean eliminarColaboraciones(String titulo, String nickname);

	public abstract void generarColaboracion(DtColaboracion colaboracion) throws ColaboradorNoExisteException, PropuestaNoExisteException;
	
}

