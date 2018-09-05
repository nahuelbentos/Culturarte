package logica;

import datatype.*;
import excepciones.ColaboracionNoExisteException;
import excepciones.ColaboradorNoExisteException;
import excepciones.UsuarioNoExisteElUsuarioException;
import excepciones.UsuarioYaExisteElEmailException;
import excepciones.UsuarioYaExisteElUsuarioException;
import excepciones.UsuarioYaSigueAlUsuarioException;

public interface IUsuarioController {

	public abstract void agregarUsuario(DtUsuario dtNuevoUsuario) throws UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException;

	public abstract DtUsuario verPerfilUsuario(String nickname) throws UsuarioNoExisteElUsuarioException;

	public abstract DtColaboracion listarColaboracion(String titulo, String nickname) throws ColaboracionNoExisteException;

	// Revisar si es necesario el DtPerfilColaborador
	public abstract DtPerfilColaborador verPerfilColaborador(String nickname);

	public abstract DtUsuario[] listarUsuarios();

	public abstract DtUsuario[] listarUsuariosQueSigue(String nickname);

	public abstract DtUsuario[] listarProponentes();

	// Revisar si es necesario el DtPerfilProponente
	public abstract DtPerfilProponente verPerfilProponente(String nickname);

	public abstract void seguirUsuario(String nicknameUno, String nicknameDos) throws UsuarioYaSigueAlUsuarioException;

	public abstract void dejarDeSeguirUsuario(String nicknameUno, String nicknameDos);

	public abstract DtUsuario[] listarColaboradores() throws ColaboradorNoExisteException;
	
	public abstract DtPropuesta[] listarPropuestasDeUnColaborador(String nickname);

}
