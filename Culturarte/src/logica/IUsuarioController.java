package logica;

import datatype.*;
import excepciones.ColaboracionNoExisteException;
import excepciones.ColaboradorNoExisteException;
import excepciones.UsuarioNoExisteElUsuarioException;
import excepciones.UsuarioSinLoguearseException;
import excepciones.UsuarioYaExisteElEmailException;
import excepciones.UsuarioYaExisteElUsuarioException;
import excepciones.UsuarioYaSigueAlUsuarioException;

public interface IUsuarioController {

	public abstract DtUsuario iniciarSesion(String nickname, String password) throws UsuarioNoExisteElUsuarioException;
	
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
	
	public abstract DtPropuesta[] listarPropuestasColaborador(DtUsuario usuarioLogueado) throws UsuarioSinLoguearseException;
	
	public abstract void agregarComentarioAPropuesta(String comentario, String titulo, DtUsuario usuarioLogueado) throws UsuarioSinLoguearseException;

	public abstract void borrarUsuarios();
}