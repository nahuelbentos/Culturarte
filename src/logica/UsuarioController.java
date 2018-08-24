	package logica;

import datatype.DtColaboracion;
import datatype.DtColaborador;
import datatype.DtUsuario;
import datatype.DtProponente;
import logica.handler.UsuarioHandler;

public class UsuarioController implements IUsuarioController {

	@Override
	public boolean agregarUsuario(DtUsuario dtUsuario) {
		Usuario usuario = null;
		UsuarioHandler usuarioHandler = UsuarioHandler.getInstance();
		if (dtUsuario instanceof DtProponente) {
			DtProponente dtProponente = (DtProponente) dtUsuario;
			usuario = new Proponente(dtProponente.getDireccion(), dtProponente.getBiografia(), 
					dtProponente.getSitioWeb(), dtProponente.getNickname(), dtProponente.getNombre(), 
					dtProponente.getFechaNacimiento(), dtProponente.getEmail(), dtProponente.getApellido(), dtProponente.getImagen());
		} else if (dtUsuario instanceof DtColaborador) {
			DtColaborador dtColaborador = (DtColaborador) dtUsuario;
			usuario = new Colaborador(dtColaborador.getNickname(), dtColaborador.getNombre(), 
					dtColaborador.getFechaNacimiento(), dtColaborador.getEmail(), dtColaborador.getApellido(), dtColaborador.getImagen());
		}
		usuarioHandler.agregarUsuario(usuario);
		return true;
	}
	
	@Override
	public DtColaboracion listarColaboracion(String titulo, String nickname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DtProponente[] listarProponentes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean seguirUsuario(String nicknameUno, String nicknameDos) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean dejarDeSeguirUsuario(String nicknameUno, String nicknameDos) {
		// TODO Auto-generated method stub
		return false;
	}

}
