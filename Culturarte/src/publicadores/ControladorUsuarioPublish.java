package publicadores;

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
	public DtUsuario iniciarSesion(String nickname, char[] password) throws UsuarioNoExisteElUsuarioException{
		DtUsuario dtU = null;
		try {
			dtU = IUC.iniciarSesion(nickname, password);
		} catch (UsuarioNoExisteElUsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtU;
	}
	
	@WebMethod
	public void agregarUsuario(DtUsuario dtNuevoUsuario) throws UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException{
		try {
			IUC.agregarUsuario(dtNuevoUsuario);
		} catch (UsuarioYaExisteElUsuarioException | UsuarioYaExisteElEmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@WebMethod
	public DtUsuario verPerfilUsuario(String nickname) throws UsuarioNoExisteElUsuarioException {
		DtUsuario dtU = null;
		try {
			dtU = IUC.verPerfilUsuario(nickname);
		} catch (UsuarioNoExisteElUsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtU;
	}

	@WebMethod
	public DtColaboracion listarColaboracion(String titulo, String nickname) throws ColaboracionNoExisteException {
		DtColaboracion dtC = null;
		try {
			dtC = IUC.listarColaboracion(titulo, nickname);
		} catch (ColaboracionNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtC;
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
		DtUsuario[] props = null;
		try {
			props = IUC.listarProponentes();
		} catch (ProponenteNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return props;
	}

	@WebMethod
	public DtPerfilProponente verPerfilProponente(String nickname) {
		return IUC.verPerfilProponente(nickname);
	}

	@WebMethod
	public void seguirUsuario(String nicknameUno, String nicknameDos) throws UsuarioYaSigueAlUsuarioException {
		try {
			IUC.seguirUsuario(nicknameUno, nicknameDos);
		} catch (UsuarioYaSigueAlUsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@WebMethod
	public void dejarDeSeguirUsuario(String nicknameUno, String nicknameDos) {
		IUC.dejarDeSeguirUsuario(nicknameUno, nicknameDos);
	}

	@WebMethod
	public DtUsuario[] listarColaboradores() throws ColaboradorNoExisteException {
		DtUsuario[] colabs = null;
		try {
			colabs = IUC.listarColaboradores();
		} catch (ColaboradorNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colabs;
	}
	
	@WebMethod
	public DtPropuesta[] listarPropuestasDeUnColaborador(String nickname) {
		return IUC.listarPropuestasDeUnColaborador(nickname);
	}
	
	@WebMethod
	public DtPropuesta[] listarPropuestasColaborador(DtUsuario usuarioLogueado) throws UsuarioSinLoguearseException {
		return listarPropuestasColaborador(usuarioLogueado);
	}

	@WebMethod
	public void agregarComentarioAPropuesta(String comentario, String titulo, DtUsuario usuarioLogueado) throws UsuarioSinLoguearseException {
		try {
			IUC.agregarComentarioAPropuesta(comentario, titulo, usuarioLogueado);
		} catch (UsuarioSinLoguearseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

}
