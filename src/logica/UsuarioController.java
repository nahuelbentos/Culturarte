package logica;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import excepciones.UsuarioYaExisteElUsuarioException;
import excepciones.UsuarioYaSigueAlUsuarioException;
import excepciones.UsuarioNoExisteElUsuarioException;
import datatype.*;
import logica.exceptions.ColaboradorNoExisteException;
import logica.handler.ColaboracionHandler;
import logica.handler.ColaboradorHandler;
import logica.handler.ProponenteHandler;
import logica.handler.UsuarioHandler;

public class UsuarioController implements IUsuarioController {

	//private EntityManagerFactory emf = Persistence.createEntityManagerFactory("conection");
	//private EntityManager em = emf.createEntityManager();
	
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
				proponenteHandler.agregarProponente(usuario);

			} else if (dtUsuario instanceof DtColaborador) {
				DtColaborador dtColaborador = (DtColaborador) dtUsuario;
				usuario = new Colaborador(dtColaborador.getNickname(), dtColaborador.getNombre(),
						dtColaborador.getFechaNacimiento(), dtColaborador.getEmail(), dtColaborador.getApellido(), dtColaborador.getImagen());

				colaboradorHandler.addColaborador(usuario);
			}
			usuarioHandler.agregarUsuario(usuario);
			// persisto en base...
			//em.persist(usuario);
		}
	}

	@Override
	public DtColaboracion listarColaboracion(String titulo, String nickname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public  DtProponente[] listarProponentes() {
		ProponenteHandler pro = ProponenteHandler.getInstance();
		DtProponente[] nicknames = null;

        Collection<Proponente> props = pro.getProponentes().values();
        Object[] o = props.toArray();
        Proponente[] proponentes = new Proponente[o.length];
        for (int i = 0; i < o.length; i++) {
        	proponentes[i] = (Proponente) o[i];
        }

		// TODO Auto-generated method stub
		return nicknames;
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


	@Override
	public DtPerfilProponente verPerfilProponente(String nickname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DtPropuesta[] listarPropuestasDeUnColaborador(String nickname) {
		// TODO Auto-generated method stub
		ColaboracionHandler c = ColaboracionHandler.getInstance();
		Colaboracion[] colaboraciones = c.getColaboraciones();
		
		return null;
	}

}
