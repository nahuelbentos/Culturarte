	package logica;

import datatype.*;
import logica.exceptions.ColaboradorNoExisteException;
import logica.handler.ColaboradorHandler;
import logica.handler.ProponenteHandler;
import logica.handler.UsuarioHandler;

public class UsuarioController implements IUsuarioController {

	@Override
	public boolean agregarUsuario(DtUsuario dtUsuario) {
		Usuario usuario = null;
		UsuarioHandler usuarioHandler = UsuarioHandler.getInstance();
		ColaboradorHandler colaboradorHandler = ColaboradorHandler.getInstance();
		ProponenteHandler proponenteHandler = ProponenteHandler.getInstance();
		if (dtUsuario instanceof DtProponente) {
			DtProponente dtProponente = (DtProponente) dtUsuario;
			usuario = new Proponente(dtProponente.getDireccion(), dtProponente.getBiografia(), 
					dtProponente.getSitioWeb(), dtProponente.getNickname(), dtProponente.getNombre(), 
					dtProponente.getFechaNacimiento(), dtProponente.getEmail(), dtProponente.getApellido(), dtProponente.getImagen());
			
			proponenteHandler.addProponente(usuario);
			
		} else if (dtUsuario instanceof DtColaborador) {
			DtColaborador dtColaborador = (DtColaborador) dtUsuario;
			usuario = new Colaborador(dtColaborador.getNickname(), dtColaborador.getNombre(), 
					dtColaborador.getFechaNacimiento(), dtColaborador.getEmail(), dtColaborador.getApellido(), dtColaborador.getImagen());
			colaboradorHandler.addColaborador(usuario);
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

	@Override
	public DtColaborador[] listarColaboradores() throws ColaboradorNoExisteException {

		ColaboradorHandler mcol = ColaboradorHandler.getInstance();
		Colaborador[] cols = mcol.getColaboradores();
		
		if (cols != null) {
			DtColaborador[] dtcols = new DtColaborador[cols.length];
			Colaborador cola;
            
			for (int i = 0; i < cols.length; i++) {
				cola = cols[i];
				dtcols[i] = new DtColaborador(cola.getNickname(), cola.getNombre(), cola.getApellido(),
						cola.getCorreoElectronico(), cola.getFechaNacimiento(), cola.getImagen());
            }
			
			return dtcols;
		}else {
			throw new ColaboradorNoExisteException("No existen colaboradores registrados");
		}
	}

}
