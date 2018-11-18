package publicadores;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

import datatype.DtColaboracion;
import datatype.DtColaborador;
import datatype.DtPerfilColaborador;
import datatype.DtPerfilProponente;
import datatype.DtPerfilUsuario;
import datatype.DtProponente;
import datatype.DtPropuesta;
import datatype.DtUsuario;
import excepciones.ColaboracionNoExisteException;
import excepciones.ColaboradorNoExisteException;
import excepciones.ProponenteNoExisteException;
import excepciones.UsuarioNoExisteElUsuarioException;
import excepciones.UsuarioSinLoguearseException;
import excepciones.UsuarioYaExisteElEmailException;
import excepciones.UsuarioYaExisteElUsuarioException;
import excepciones.UsuarioYaSigueAlUsuarioException;
import logica.Factory;
import logica.IUsuarioController;


@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ControladorUsuarioPublish {
	private Factory fabrica;
	private IUsuarioController IUC;
	private Endpoint endpoint;

	public ControladorUsuarioPublish() {
		fabrica = Factory.getInstance();
		IUC = fabrica.getIUsuarioController();
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
	public DtUsuario iniciarSesion(String nickname, String password) throws UsuarioNoExisteElUsuarioException{
		char[] aux = password.toCharArray();
		return IUC.iniciarSesion(nickname, aux);
	}
	
	@WebMethod
	public void agregarUsuario(DtUsuario dtNuevoUsuario) throws UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException{
		IUC.agregarUsuario(dtNuevoUsuario);
	}

	@WebMethod
	public DtUsuario verPerfilUsuario(String nickname) throws UsuarioNoExisteElUsuarioException {
		return IUC.verPerfilUsuario(nickname);
	}

	@WebMethod
	public DtColaboracion listarColaboracion(String titulo, String nickname) throws ColaboracionNoExisteException {
		return IUC.listarColaboracion(titulo, nickname);
	}

	@WebMethod
	public DtPerfilColaborador verPerfilColaborador(String nickname) {
		return IUC.verPerfilColaborador(nickname);
	}

	@WebMethod
	public DtUsuario[] listarUsuarios() {
		return IUC.listarUsuarios();
	}

	@WebMethod
	public DtUsuario[] listarUsuariosQueSigue(String nickname) {
		return IUC.listarUsuariosQueSigue(nickname);
	}

	@WebMethod
	public DtUsuario[] listarProponentes() throws ProponenteNoExisteException {
		return IUC.listarProponentes();
	}

	@WebMethod
	public DtPerfilProponente verPerfilProponente(String nickname) {
		return IUC.verPerfilProponente(nickname);
	}

	@WebMethod
	public void seguirUsuario(String nicknameUno, String nicknameDos) throws UsuarioYaSigueAlUsuarioException {
		IUC.seguirUsuario(nicknameUno, nicknameDos);
	}

	@WebMethod
	public void dejarDeSeguirUsuario(String nicknameUno, String nicknameDos) {
		IUC.dejarDeSeguirUsuario(nicknameUno, nicknameDos);
	}

	@WebMethod
	public DtUsuario[] listarColaboradores() throws ColaboradorNoExisteException {
		return IUC.listarColaboradores();
	}
	
	@WebMethod
	public DtPropuesta[] listarPropuestasDeUnColaborador(String nickname) {
		return IUC.listarPropuestasDeUnColaborador(nickname);
	}
	
	@WebMethod
	public DtPropuesta[] listarPropuestasColaborador(DtUsuario usuarioLogueado) throws UsuarioSinLoguearseException {
		return IUC.listarPropuestasColaborador(usuarioLogueado);
	}

	@WebMethod
	public void agregarComentarioAPropuesta(String comentario, String titulo, DtUsuario usuarioLogueado) throws UsuarioSinLoguearseException {
		IUC.agregarComentarioAPropuesta(comentario, titulo, usuarioLogueado);
	}
	
	@WebMethod
	public DtPerfilUsuario obtenerPerfilUsuario(String nickname, DtUsuario usuarioLogueado) {
		return IUC.obtenerPerfilUsuario(nickname, usuarioLogueado);
	}

	@WebMethod(exclude = true)
	public void borrarUsuarios() {
		IUC.borrarUsuarios();
	}
	
	@WebMethod
	public DtColaborador[] getMasColaboradores() {
		return IUC.getMasColaboradores();
	}
	
	@WebMethod
	public DtProponente[] getMasProponedores() {
		return IUC.getMasProponedores();
	}
	
	@WebMethod
	public DtUsuario[] verRankingUsuarios() {
		return IUC.verRankingUsuarios();
	}
	
	@WebMethod
	public DtPropuesta[] listarFavoritasUsuario(String nickname) {
		return IUC.listarFavoritasUsuario(nickname);
	}
	
	@WebMethod
	public void eliminarCuenta(String nickname) throws UsuarioNoExisteElUsuarioException{
		IUC.eliminarCuenta(nickname);
	}
	
	@WebMethod
	public void registrarAccesoAlSitio(String ip, String url, String userAgent) throws IOException, URISyntaxException {
		IUC.registrarAccesoAlSitio(ip, url, userAgent);
	}

}
