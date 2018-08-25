package logica;

import datatype.DtColaboracion;
import datatype.DtColaborador;
import datatype.DtProponente;
import datatype.DtUsuario;
import excepciones.UsuarioNoExisteElUsuarioException;
import excepciones.UsuarioYaExisteElUsuarioException;
import excepciones.UsuarioYaSigueAlUsuarioException;

public interface IUsuarioController {

	public abstract void agregarUsuario(DtUsuario dtNuevoUsuario) throws UsuarioYaExisteElUsuarioException;
	
	public abstract DtUsuario verPerfilUsuario(String nickname) throws UsuarioNoExisteElUsuarioException;
	
	public abstract DtColaborador[] listarColaboradores();
	
	// Revisar si están bien los parámetros que recibe
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
	
}
