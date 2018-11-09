package logica;

import java.text.ParseException;
import java.util.ArrayList;

import datatype.DtColaboracion;
import datatype.DtDatosPropuesta;
import datatype.DtInfoPago;
import datatype.DtPropuesta;
import datatype.DtPropuestaMinificado;
import datatype.DtUsuario;
import datatype.EstadoPropuesta;
import excepciones.CategoriaNoExisteException;
import excepciones.ColaboracionExistenteException;
import excepciones.ColaboracionNoExisteException;
import excepciones.ColaboradorNoExisteException;
import excepciones.ProponenteNoExisteException;
import excepciones.PropuestaNoExisteException;
import excepciones.PropuestaRepetidaException;
import excepciones.TipoPagoInexistenteExpection;
import excepciones.UsuarioSinLoguearseException;

public interface IPropuestaController {

	public abstract void altaPropuesta(DtPropuesta dtPropuesta)  throws PropuestaRepetidaException, ProponenteNoExisteException, CategoriaNoExisteException;
	
	/**
	 * Se listan las propuestas del sistema en un array con el titulo de la propuesta y el proponente a cargo. Se lanza
	 * PropuestaNoExisteException en caso de no existir propuestas en el sistema. 
	 * @return DtPropuestaMinificado[]
	 * @throws PropuestaNoExisteException
	 */
	public abstract DtPropuestaMinificado[] listarPropuestas() throws PropuestaNoExisteException;
	
	public abstract DtPropuesta seleccionarPropuesta(String titulo);
	
	public abstract boolean modificarPropuesta(DtPropuesta dtPropuesta);
	
	public abstract DtColaboracion[] listarColaboraciones(String titulo);
	
	public abstract DtPropuesta[] listarPropuestasExistentes();
	
	public abstract DtDatosPropuesta consultarPropuesta(String titulo);
	
	public abstract DtPropuestaMinificado[] listarPropuestasPorEstado(EstadoPropuesta estadoPropuesta) throws PropuestaNoExisteException;
	
	public abstract void generarColaboracion(DtColaboracion colaboracion) throws ColaboradorNoExisteException, PropuestaNoExisteException, ColaboracionExistenteException;
	
	public abstract DtPropuestaMinificado[] listadoPropuestasIngresadas() throws PropuestaNoExisteException;;

	public abstract void evaluarPropuesta(String titulo, EstadoPropuesta estado) throws PropuestaNoExisteException;
	
	public abstract DtPropuestaMinificado[] listarPropuestasProponentePorEstado(String nicknameProponente, EstadoPropuesta estado) throws PropuestaNoExisteException;

	public abstract DtPropuestaMinificado[] listarPropuestasProponente(String nicknameProponente) throws PropuestaNoExisteException;
	
	public abstract DtPropuestaMinificado[] listarPropuestasActivas();
	
	public abstract void agregarFavorita(String titulo, DtUsuario usuarioLogueado) throws UsuarioSinLoguearseException;

	public abstract void extenderFinanciacion(String tituloPropuesta) throws PropuestaNoExisteException;

	public abstract void cancelarPropuesta(String tituloPropuesta) throws PropuestaNoExisteException;
	
	public abstract void borrarPropuestas(); // Para la carga de datos.

	public abstract void setearEstadosPropuests(String estado, String propuesta, String fechaCambio) throws ParseException; // Para la carga de datos.
	
	public abstract void borrarEstadosPropuestas(); // Para la carga de datos.
	
	public abstract ArrayList<DtPropuesta> listarPropuestasPorCategoria(String nombreCat);
	
	//funciones para el inicio de la aplicacion web
	public abstract DtPropuesta[] getPropuestasPopulares();

	public abstract DtPropuestaMinificado[] propuestasDesdeBuscador(String buscar);
	
	public abstract void pagarColaboracion(DtInfoPago infoPago) throws TipoPagoInexistenteExpection;
	
	public abstract DtColaboracion[] listarColaboracionesAPagar(String nickColaborador) throws ColaboracionNoExisteException;
	
}

