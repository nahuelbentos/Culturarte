package publicadores;

import java.text.ParseException;
import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

import datatype.DtColaboracion;
import datatype.DtDatosPropuesta;
import datatype.DtPropuesta;
import datatype.DtPropuestaMinificado;
import datatype.DtUsuario;
import datatype.EstadoPropuesta;
import excepciones.CategoriaNoExisteException;
import excepciones.ColaboracionExistenteException;
import excepciones.ColaboradorNoExisteException;
import excepciones.ProponenteNoExisteException;
import excepciones.PropuestaNoExisteException;
import excepciones.PropuestaRepetidaException;
import excepciones.UsuarioSinLoguearseException;
import logica.Factory;
import logica.IPropuestaController;


@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ControladorPropuestaPublish {
	private Factory fabrica;
	private IPropuestaController IPC;
	private Endpoint endpoint;

	public ControladorPropuestaPublish() {
		fabrica = Factory.getInstance();
		IPC = fabrica.getIPropuestaController();
	}

	@WebMethod(exclude = true)
	public void publicar() {
		String direccion = "http://127.0.0.1:1234/controladorUsuario";
//		System.out.println(direccion);
		endpoint = Endpoint.publish(direccion, this);
	}
	
	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
        return endpoint;
	}
	
	//LOS MÉTODOS QUE VAMOS A PUBLICAR
	@WebMethod
	public  void altaPropuesta(DtPropuesta dtPropuesta)  throws PropuestaRepetidaException, ProponenteNoExisteException, CategoriaNoExisteException{
		IPC.altaPropuesta(dtPropuesta);		
	}
	
	/**
	 * Se listan las propuestas del sistema en un array con el titulo de la propuesta y el proponente a cargo. Se lanza
	 * PropuestaNoExisteException en caso de no existir propuestas en el sistema. 
	 * @return DtPropuestaMinificado[]
	 * @throws PropuestaNoExisteException
	 */
	@WebMethod
	public  DtPropuestaMinificado[] listarPropuestas() throws PropuestaNoExisteException{
		return IPC.listarPropuestas();
	}
	
	@WebMethod
	public  DtPropuesta seleccionarPropuesta(String titulo) {
		return IPC.seleccionarPropuesta(titulo);
		
	}
	
	@WebMethod
	public  boolean modificarPropuesta(DtPropuesta dtPropuesta) {
		return IPC.modificarPropuesta(dtPropuesta);
		
	}
	
	@WebMethod
	public  DtColaboracion[] listarColaboraciones(String titulo) {
		return IPC.listarColaboraciones(titulo);
		
	}
	
	// Falta crear el DtEstadoPropuesta
	//public  DtEstadoPropuesta[] listarEstadosDePropuestas();
	@WebMethod
	public  DtPropuesta[] listarPropuestasExistentes() {
		return IPC.listarPropuestasExistentes();
		
	}
	
	// Falta crear el DtDatosPropuesta
	@WebMethod
	public DtDatosPropuesta consultarPropuesta(String titulo){
		return IPC.consultarPropuesta(titulo);
		
	}
	
	@WebMethod
	public  DtPropuestaMinificado[] listarPropuestasPorEstado(EstadoPropuesta estadoPropuesta) throws PropuestaNoExisteException{
		return IPC.listarPropuestasPorEstado(estadoPropuesta);
		
	}
	
	@WebMethod
	public  void generarColaboracion(DtColaboracion colaboracion) throws ColaboradorNoExisteException, PropuestaNoExisteException, ColaboracionExistenteException{
		IPC.generarColaboracion(colaboracion);
	}
	
	@WebMethod
	public  DtPropuestaMinificado[] listadoPropuestasIngresadas() throws PropuestaNoExisteException{
		return IPC.listadoPropuestasIngresadas();
	}

	@WebMethod
	public  void evaluarPropuesta(String titulo, EstadoPropuesta estado) throws PropuestaNoExisteException{
	}
	
	@WebMethod
	public  DtPropuestaMinificado[] listarPropuestasProponentePorEstado(String nicknameProponente, EstadoPropuesta estado) throws PropuestaNoExisteException{
		return null;
		
	}

	@WebMethod
	public  DtPropuestaMinificado[] listarPropuestasActivas(){
		return null;
		
	}
	
	@WebMethod
	public  void agregarFavorita(String titulo, DtUsuario usuarioLogueado) throws UsuarioSinLoguearseException{
		
	}

	@WebMethod
	public  void extenderFinanciacion(String tituloPropuesta) throws PropuestaNoExisteException{
		
	}

	@WebMethod
	void cancelarPropuesta(String tituloPropuesta) throws PropuestaNoExisteException{
		
	}
	
	@WebMethod
	public  void borrarPropuestas(){
		
	} // Para la carga de datos.

	@WebMethod
	public  void setearEstadosPropuests(String estado, String propuesta, String fechaCambio) throws ParseException{
		
	} // Para la carga de datos.
	
	@WebMethod
	public  void borrarEstadosPropuestas(){
		
	} // Para la carga de datos.
	
	@WebMethod
	public  ArrayList<DtPropuesta> listarPropuestasPorCategoria(String nombreCat){
		return null;
		
	}
	
	//funciones para el inicio de la aplicacion web
	@WebMethod
	public  DtPropuesta[] getPropuestasPopulares(){
		return IPC.getPropuestasPopulares();		
	}

	@WebMethod
	public  DtPropuestaMinificado[] propuestasDesdeBuscador(String buscar){
		return IPC.propuestasDesdeBuscador(buscar);		
	}
}
