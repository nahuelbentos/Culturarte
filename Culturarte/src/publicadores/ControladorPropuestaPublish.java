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
import datatype.DtColaborador;
import datatype.DtDatosPropuesta;
import datatype.DtPerfilColaborador;
import datatype.DtPerfilProponente;
import datatype.DtPerfilUsuario;
import datatype.DtProponente;
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
import excepciones.UsuarioNoExisteElUsuarioException;
import excepciones.UsuarioSinLoguearseException;
import excepciones.UsuarioYaExisteElEmailException;
import excepciones.UsuarioYaExisteElUsuarioException;
import excepciones.UsuarioYaSigueAlUsuarioException;
import logica.Factory;
import logica.IPropuestaController;
import logica.IUsuarioController;


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
		String direccion = "http://127.0.0.1:1234/controladorPropuesta";
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
		return null;
		
	}
	
	@WebMethod
	public  boolean modificarPropuesta(DtPropuesta dtPropuesta) {
		return false;
		
	}
	
	@WebMethod
	public  DtColaboracion[] listarColaboraciones(String titulo) {
		return null;
		
	}
	
	// Falta crear el DtEstadoPropuesta
	//public  DtEstadoPropuesta[] listarEstadosDePropuestas();
	@WebMethod
	public  DtPropuesta[] listarPropuestasExistentes() {
		return null;
		
	}
	
	// Falta crear el DtDatosPropuesta
	@WebMethod
	public DtDatosPropuesta consultarPropuesta(String titulo){
		return null;
		
	}
	
	@WebMethod
	public  DtPropuestaMinificado[] listarPropuestasPorEstado(EstadoPropuesta estadoPropuesta) throws PropuestaNoExisteException{
		return null;
		
	}
	
	@WebMethod
	public  void generarColaboracion(DtColaboracion colaboracion) throws ColaboradorNoExisteException, PropuestaNoExisteException, ColaboracionExistenteException{
		
	}
	
	@WebMethod
	public  DtPropuestaMinificado[] listadoPropuestasIngresadas() throws PropuestaNoExisteException{
		return null;
		
	}

	@WebMethod
	public void evaluarPropuesta(String titulo, EstadoPropuesta estado) throws PropuestaNoExisteException{
		IPC.evaluarPropuesta(titulo, estado);
	}
	
	@WebMethod
	public  DtPropuestaMinificado[] listarPropuestasProponentePorEstado(String nicknameProponente, EstadoPropuesta estado) throws PropuestaNoExisteException{
		return IPC.listarPropuestasProponentePorEstado(nicknameProponente, estado);
	}

	@WebMethod
	public DtPropuestaMinificado[] listarPropuestasActivas(){
		return IPC.listarPropuestasActivas();
		
	}
	
	@WebMethod
	public void agregarFavorita(String titulo, DtUsuario usuarioLogueado) throws UsuarioSinLoguearseException{
		IPC.agregarFavorita(titulo, usuarioLogueado);
	}

	@WebMethod
	public void extenderFinanciacion(String tituloPropuesta) throws PropuestaNoExisteException{
		IPC.extenderFinanciacion(tituloPropuesta);
	}

	@WebMethod
	void cancelarPropuesta(String tituloPropuesta) throws PropuestaNoExisteException{
		IPC.cancelarPropuesta(tituloPropuesta);
	}
	
	@WebMethod(exclude = true)
	public void borrarPropuestas(){
		IPC.borrarPropuestas();
	} // Para la carga de datos.

	@WebMethod
	public void setearEstadosPropuests(String estado, String propuesta, String fechaCambio) throws ParseException{
		IPC.setearEstadosPropuests(estado, propuesta, fechaCambio);
	} // Para la carga de datos.
	
	@WebMethod
	public void borrarEstadosPropuestas(){
		IPC.borrarEstadosPropuestas();
	} // Para la carga de datos.
	
	@WebMethod
	public DtPropuesta[] listarPropuestasPorCategoria(String nombreCat){
		ArrayList<DtPropuesta> props = IPC.listarPropuestasPorCategoria(nombreCat);
		DtPropuesta[] dtProps = null;
		
		if (props.size() > 0) {
			dtProps = new DtPropuesta[props.size()];
			int i = 0;
			for(DtPropuesta dtP: props) {
				dtProps[i] = dtP;
				i++;
			}
		}
		
		return dtProps;
	}
	
	//funciones para el inicio de la aplicacion web
	@WebMethod
	public  DtPropuesta[] getPropuestasPopulares(){
		return null;
		
	}

	@WebMethod
	public  DtPropuestaMinificado[] propuestasDesdeBuscador(String buscar){
		return null;
		
	}
}
