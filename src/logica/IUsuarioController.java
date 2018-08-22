package logica;

import datatype.DtColaboracion;
import datatype.DtUsuario;
import datatype.DtProponente;

public interface IUsuarioController {

	public abstract boolean agregarUsuario(DtUsuario dtNuevoUsuario);
	
	// Crear el DtColaborador
	//public abstract DtColaborador[] listarColaboradores();
	
	// Revisar si están bien los parámetros que recibe
	public abstract DtColaboracion listarColaboracion(String titulo, String nickname);
	
	// Revisar si es necesario el DtPerfilColaborador
	//public abstract DtPerfilColaborador verPerfilColaborador(String nickname);
	
	public abstract DtProponente[] listarProponentes();
	
	// Revisar si es necesario el DtPerfilProponente
	//public abstract DtPerfilProponente verPerfilProponente(String nickname);
	
	// Revisar si están bien los parámetros que recibe
	public abstract boolean seguirUsuario(String nicknameUno, String nicknameDos);
	
	// Revisar si están bien los parámetros que recibe
	public abstract boolean dejarDeSeguirUsuario(String nicknameUno, String nicknameDos);
	
}
