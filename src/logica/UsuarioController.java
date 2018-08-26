	package logica;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import datatype.DtColaboracion;
import datatype.DtColaborador;
import datatype.DtPerfilProponente;
import datatype.DtUsuario;
import datatype.DtProponente;
import logica.handler.ProponenteHandler;
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
	public  ArrayList<String> listarProponentes() {
		ProponenteHandler pro = ProponenteHandler.getInstance();
		ArrayList<String> nicknames=null;

        Collection<Proponente> props = pro.getProponentes().values();
        Object[] o = props.toArray();
        Proponente[] proponentes = new Proponente[o.length];
        for (int i = 0; i < o.length; i++) {
        	proponentes[i] = (Proponente) o[i];
        	nicknames.add(proponentes[i].getNickname());
        }               
		
		// TODO Auto-generated method stub
		return nicknames;
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

	
	@Override
	public DtPerfilProponente verPerfilProponente(String nickname) {
		// TODO Auto-generated method stub
		return null;
	}

}
