package logica;

import datatype.*;
import logica.exceptions.ColaboradorNoExisteException;
import excepciones.UsuarioNoExisteElUsuarioException;
import excepciones.UsuarioYaExisteElUsuarioException;
import excepciones.UsuarioYaSigueAlUsuarioException;

public interface IUsuarioController {

	public abstract void agregarUsuario(DtUsuario dtNuevoUsuario) throws UsuarioYaExisteElUsuarioException;

	public abstract DtUsuario verPerfilUsuario(String nickname) throws UsuarioNoExisteElUsuarioException;

	// Revisar si est�n bien los par�metros que recibe
	public abstract DtColaboracion listarColaboracion(String titulo, String nickname);

	// Revisar si es necesario el DtPerfilColaborador
	//public abstract DtPerfilColaborador verPerfilColaborador(String nickname);

	public abstract DtUsuario[] listarUsuarios();

	public abstract DtUsuario[] listarUsuariosQueSigue(String nickname);

	public abstract DtProponente[] listarProponentes();

	// Revisar si es necesario el DtPerfilProponente
	//public abstract DtPerfilProponente verPerfilProponente(String nickname);

	public abstract void seguirUsuario(String nicknameUno, String nicknameDos) throws UsuarioYaSigueAlUsuarioException;

	public abstract void dejarDeSeguirUsuario(String nicknameUno, String nicknameDos);

	public abstract DtColaborador[] listarColaboradores() throws ColaboradorNoExisteException;

}
