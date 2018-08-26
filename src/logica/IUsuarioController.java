package logica;

import datatype.*;
import logica.exceptions.ColaboradorNoExisteException;

public interface IUsuarioController {

	public abstract boolean agregarUsuario(DtUsuario dtNuevoUsuario);
	
	// Crear el DtColaborador
	//public abstract DtColaborador[] listarColaboradores();
	
	// Revisar si est�n bien los par�metros que recibe
	public abstract DtColaboracion listarColaboracion(String titulo, String nickname);
	
	// Revisar si es necesario el DtPerfilColaborador
	//public abstract DtPerfilColaborador verPerfilColaborador(String nickname);
	
	public abstract DtProponente[] listarProponentes();
	
	// Revisar si es necesario el DtPerfilProponente
	//public abstract DtPerfilProponente verPerfilProponente(String nickname);
	
	// Revisar si est�n bien los par�metros que recibe
	public abstract boolean seguirUsuario(String nicknameUno, String nicknameDos);
	
	// Revisar si est�n bien los par�metros que recibe
	public abstract boolean dejarDeSeguirUsuario(String nicknameUno, String nicknameDos);
	
	public abstract DtColaborador[] listarColaboradores() throws ColaboradorNoExisteException;
	
}
