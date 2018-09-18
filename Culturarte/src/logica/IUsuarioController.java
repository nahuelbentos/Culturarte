package logica;

import datatype.*;
import excepciones.ColaboracionNoExisteException;
import excepciones.ColaboradorNoExisteException;
import excepciones.UsuarioNoExisteElUsuarioException;
import excepciones.UsuarioSinLoguearseException;
import excepciones.UsuarioYaExisteElEmailException;
import excepciones.UsuarioYaExisteElUsuarioException;
import excepciones.UsuarioYaLogueado;
import excepciones.UsuarioYaSigueAlUsuarioException;

public interface IUsuarioController {

	public abstract void iniciarSesion(String nickname, String password) throws UsuarioNoExisteElUsuarioException, UsuarioYaLogueado;
	
	public abstract void cerrarSesion() throws UsuarioSinLoguearseException;
	
	public abstract void agregarUsuario(DtUsuario dtNuevoUsuario) throws UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException;

	public abstract DtUsuario verPerfilUsuario(String nickname) throws UsuarioNoExisteElUsuarioException;

	public abstract DtColaboracion listarColaboracion(String titulo, String nickname) throws ColaboracionNoExisteException;

	public abstract DtPerfilColaborador verPerfilColaborador(String nickname);

	public abstract DtUsuario[] listarUsuarios();

	public abstract DtUsuario[] listarUsuariosQueSigue(String nickname);

	public abstract DtUsuario[] listarProponentes();

	public abstract DtPerfilProponente verPerfilProponente(String nickname);

	public abstract void seguirUsuario(String nicknameUno, String nicknameDos) throws UsuarioYaSigueAlUsuarioException;

	public abstract void dejarDeSeguirUsuario(String nicknameUno, String nicknameDos);

	public abstract DtUsuario[] listarColaboradores() throws ColaboradorNoExisteException;
	
	public abstract DtPropuesta[] listarPropuestasDeUnColaborador(String nickname);
	
	public abstract DtPropuesta[] listarPropuestasColaborador() throws UsuarioSinLoguearseException;
	
	public abstract void agregarComentarioAPropuesta(String comentario, String titulo) throws UsuarioSinLoguearseException;

	public abstract void borrarUsuarios();
}
