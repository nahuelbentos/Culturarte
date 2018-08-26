	package logica;


import excepciones.UsuarioYaExisteElUsuarioException;
import excepciones.UsuarioYaSigueAlUsuarioException;
import excepciones.UsuarioNoExisteElUsuarioException;
import datatype.*;
import logica.exceptions.ColaboradorNoExisteException;
import logica.handler.ColaboradorHandler;
import logica.handler.ProponenteHandler;
import logica.handler.UsuarioHandler;

public class UsuarioController implements IUsuarioController {

	@Override
	public void agregarUsuario(DtUsuario dtUsuario) throws UsuarioYaExisteElUsuarioException {
		UsuarioHandler usuarioHandler = UsuarioHandler.getInstance();
		Usuario usuario = usuarioHandler.obtenerUsuario(dtUsuario.getNickname());
		if (usuario != null) {
			throw new UsuarioYaExisteElUsuarioException("El usuario " + dtUsuario.getNickname() + " ya esta registrado");
		} else {
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
		}
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
	public void seguirUsuario(String nicknameUno, String nicknameDos) throws UsuarioYaSigueAlUsuarioException {
		UsuarioHandler usuarioHandler = UsuarioHandler.getInstance();
        Usuario usuarioUno = usuarioHandler.obtenerUsuario(nicknameUno);
        Usuario usuarioDos = usuarioHandler.obtenerUsuario(nicknameDos);
		if (usuarioUno.getUsuariosQueSigue().containsKey(nicknameDos)) {
        	throw new UsuarioYaSigueAlUsuarioException("El usuario " + nicknameUno
        			+ " ya sigue al usuario " + nicknameDos);
		} else {
	        usuarioUno.getUsuariosQueSigue().put(nicknameDos, usuarioDos);
		}
	}

	@Override
	public void dejarDeSeguirUsuario(String nicknameUno, String nicknameDos) {
		UsuarioHandler usuarioHandler = UsuarioHandler.getInstance();
        Usuario usuarioUno = usuarioHandler.obtenerUsuario(nicknameUno);
        Usuario usuarioDos = usuarioHandler.obtenerUsuario(nicknameDos);
        usuarioUno.getUsuariosQueSigue().remove(nicknameDos, usuarioDos);
	}

	@Override
	public DtUsuario[] listarUsuarios() {
		UsuarioHandler usuarioHandler = UsuarioHandler.getInstance();
        Usuario[] usrs = usuarioHandler.getUsuarios();
        if (usrs != null) {
            DtUsuario[] dtUsuario = new DtUsuario[usrs.length];
            Usuario usuario;
            for (int i = 0; i < usrs.length; i++) {
                usuario = usrs[i];
                dtUsuario[i] = new DtUsuario(usuario.getNickname(), usuario.getNombre(),
                		usuario.getApellido(), usuario.getCorreoElectronico(), usuario.getFechaNacimiento(), usuario.getImagen());
            }
            return dtUsuario;
        }
        return null;
	}

	@Override
	public DtUsuario verPerfilUsuario(String nickname) throws UsuarioNoExisteElUsuarioException {
		UsuarioHandler usuarioHandler = UsuarioHandler.getInstance();
        Usuario usuario = usuarioHandler.obtenerUsuario(nickname);
        DtUsuario dtUsuario = null;
        if (usuario != null) {
        	if (usuario instanceof Proponente) {
				Proponente proponente = (Proponente) usuario;
				dtUsuario = new DtProponente(proponente.getNickname(), proponente.getNombre(), proponente.getApellido(),
						proponente.getCorreoElectronico(), proponente.getFechaNacimiento(), proponente.getImagen(),
						proponente.getDireccion(), proponente.getBiografia(), proponente.getLinkWeb());
			} else if (usuario instanceof Colaborador) {
				Colaborador colaborador = (Colaborador) usuario;
				dtUsuario = new DtColaborador(colaborador.getNickname(), colaborador.getNombre(), colaborador.getApellido(),
						colaborador.getCorreoElectronico(), colaborador.getFechaNacimiento(), colaborador.getImagen());
			}
        	return dtUsuario;
        } else {
            throw new UsuarioNoExisteElUsuarioException("El usuario " + nickname + " no existe");
        }
	}

	@Override
	public DtUsuario[] listarUsuariosQueSigue(String nickname) {
		UsuarioHandler usuarioHandler = UsuarioHandler.getInstance();
		Usuario usuarioUno = usuarioHandler.obtenerUsuario(nickname);
		Usuario[] usuarios = usuarioUno.getListaUsuariosQueSigue();
		if (usuarios != null) {
	        DtUsuario[] listaDeUsuarios = new DtUsuario[usuarios.length];
	        Usuario usuarioDos;
	        for (int i = 0; i < usuarios.length; i++) {
	        	usuarioDos = usuarios[i];
	        	listaDeUsuarios[i] = new DtUsuario(usuarioDos.getNickname(), usuarioDos.getNombre(),
	        			usuarioDos.getApellido(), usuarioDos.getCorreoElectronico(), usuarioDos.getFechaNacimiento(), usuarioDos.getImagen());
	        }
	        return listaDeUsuarios;
		}
		return null;
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
