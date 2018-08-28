package logica;

import java.util.Collection;
import java.util.Map;
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
import logica.handler.PropuestaHandler;
import logica.handler.UsuarioHandler;

public class UsuarioController implements IUsuarioController {

	private static EntityManager em;
	private static EntityManagerFactory emf;

	public UsuarioController() {
		super();
	}

	@Override
	public void agregarUsuario(DtUsuario dtUsuario) throws UsuarioYaExisteElUsuarioException {
		//Configuramos el EMF a trav�s de la unidad de persistencia
		emf = Persistence.createEntityManagerFactory("Conexion");
		//Generamos un EntityManager
		em = emf.createEntityManager();
		//Iniciamos una transacci�n
		em.getTransaction().begin();

		UsuarioHandler usuarioHandler = UsuarioHandler.getInstance();
		Usuario usuario = usuarioHandler.obtenerUsuario(dtUsuario.getNickname());
		if (usuario != null) {
			throw new UsuarioYaExisteElUsuarioException("El usuario " + dtUsuario.getNickname() + " ya esta registrado");
		} else {
			ColaboradorHandler colaboradorHandler = ColaboradorHandler.getInstance();
			ProponenteHandler proponenteHandler = ProponenteHandler.getInstance();
			if (dtUsuario instanceof DtProponente) {
				DtProponente dtProponente = (DtProponente) dtUsuario;
				usuario = new Proponente(0, dtProponente.getDireccion(), dtProponente.getBiografia(),
						dtProponente.getSitioWeb(), dtProponente.getNickname(), dtProponente.getNombre(),
						dtProponente.getFechaNacimiento(), dtProponente.getEmail(), dtProponente.getApellido(), dtProponente.getImagen());
				proponenteHandler.agregarProponente(usuario);

			} else if (dtUsuario instanceof DtColaborador) {
				DtColaborador dtColaborador = (DtColaborador) dtUsuario;
				usuario = new Colaborador(0, dtColaborador.getNickname(), dtColaborador.getNombre(),
						dtColaborador.getFechaNacimiento(), dtColaborador.getEmail(), dtColaborador.getApellido(), dtColaborador.getImagen());

				colaboradorHandler.addColaborador(usuario);
			}
			usuarioHandler.agregarUsuario(usuario);

			//Persistimos el objeto
			em.persist(usuario);
			//Commmiteamos la transacci�n
			em.getTransaction().commit();
			//Cerramos el EntityManager
			em.close();
		}
	}

	@Override
	public DtColaboracion listarColaboracion(String titulo, String nickname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public  ArrayList<String> listarProponentes() {
		ProponenteHandler pro = ProponenteHandler.getInstance();
		ArrayList<String> nicknames = new ArrayList<String>();

        Collection<Proponente> props = pro.getProponentes().values();
        Object[] o = props.toArray();
        Proponente[] proponentes = new Proponente[o.length];
        for (int i = 0; i < o.length; i++) {
        	proponentes[i] = (Proponente) o[i];
					nicknames.add(proponentes[i].getNickname())
        }

		// TODO Auto-generated method stub
		return nicknames;
	}

	@Override
	public void seguirUsuario(String nicknameUno, String nicknameDos) throws UsuarioYaSigueAlUsuarioException {
//		UsuarioHandler usuarioHandler = UsuarioHandler.getInstance();
//        Usuario usuarioUno = usuarioHandler.obtenerUsuario(nicknameUno);
//        Usuario usuarioDos = usuarioHandler.obtenerUsuario(nicknameDos);
//		if (usuarioUno.getUsuariosQueSigue().containsKey(nicknameDos)) {
//        	throw new UsuarioYaSigueAlUsuarioException("El usuario " + nicknameUno
//        			+ " ya sigue al usuario " + nicknameDos);
//		} else {
//	        usuarioUno.getUsuariosQueSigue().put(nicknameDos, usuarioDos);
//		}
	}

	@Override
	public void dejarDeSeguirUsuario(String nicknameUno, String nicknameDos) {
//		UsuarioHandler usuarioHandler = UsuarioHandler.getInstance();
//        Usuario usuarioUno = usuarioHandler.obtenerUsuario(nicknameUno);
//        Usuario usuarioDos = usuarioHandler.obtenerUsuario(nicknameDos);
//        usuarioUno.getUsuariosQueSigue().remove(nicknameDos, usuarioDos);
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
//		UsuarioHandler usuarioHandler = UsuarioHandler.getInstance();
//		Usuario usuarioUno = usuarioHandler.obtenerUsuario(nickname);
//		Usuario[] usuarios = usuarioUno.getListaUsuariosQueSigue();
//		if (usuarios != null) {
//	        DtUsuario[] listaDeUsuarios = new DtUsuario[usuarios.length];
//	        Usuario usuarioDos;
//	        for (int i = 0; i < usuarios.length; i++) {
//	        	usuarioDos = usuarios[i];
//	        	listaDeUsuarios[i] = new DtUsuario(usuarioDos.getNickname(), usuarioDos.getNombre(),
//	        			usuarioDos.getApellido(), usuarioDos.getCorreoElectronico(), usuarioDos.getFechaNacimiento(), usuarioDos.getImagen());
//	        }
//	        return listaDeUsuarios;
//		}
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

		ProponenteHandler mpro = ProponenteHandler.getInstance();
		Map<String, Proponente> props = mpro.getProponentes();

		PropuestaHandler mpropue = PropuestaHandler.getInstance();
		Map<String, Propuesta> propues = mpropue.getPropuestas();

		ColaboracionHandler mcol = ColaboracionHandler.getInstance();
		Map<Long, Colaboracion> colabs = mcol.getMapColaboraciones();



		Proponente p = props.get(nickname); //1
		DtPerfilProponente auxUsuProponente = p.getDatosBasicos(); //2

		ArrayList<DtPropuesta> prPublicadas = new ArrayList<DtPropuesta>();
		ArrayList<DtPropuesta> prCanceladas = new ArrayList<DtPropuesta>();
		ArrayList<DtPropuesta> prEnFinanciacion = new ArrayList<DtPropuesta>();
		ArrayList<DtPropuesta> prFinanciadas = new ArrayList<DtPropuesta>();
		ArrayList<DtPropuesta> prNoFinanciadas = new ArrayList<DtPropuesta>();

		for(Propuesta prop : propues.values()) { //3
			if(prop.isProponenteACargo(nickname)) {

				ArrayList<DtColaboracion> colaboraciones = new ArrayList<DtColaboracion>();
				for(Colaboracion col : colabs.values()) { //6
					if(col.tieneProp(prop.getTitulo())) {
						colaboraciones.add(col.getDataColaboracion());
					}
				}

				DtPropuesta dataPro = new DtPropuesta(prop.getTitulo(), prop.getDescripcion(), prop.getImagen(),prop.getMontoNecesario(),
				 prop.getFechaPublicacion(), prop.getFechaEspecatulo(), prop.getLugar(), prop.getPrecioEntrada(), prop.getTipo(), 0,
				 prop.getProponenteACargo().getDtProponente(), prop.getEstadoActual().getDtEstado(), prop.getDtEstadoHistorial(),
				 prop.getCategoria().getDtCategoria(), colaboraciones);
//				dataPro=prop.getInfoPropuesta(); //4 y5

				switch (dataPro.getEstadoActual().getEstado()){
					case publicada:
						prPublicadas.add(dataPro);
						break;
					case cancelada:
						prCanceladas.add(dataPro);
						break;
					case enFinanciacion:
						prEnFinanciacion.add(dataPro);
						break;
					case financiada:
						prFinanciadas.add(dataPro);
						break;
					case noFinanciada:
						prNoFinanciadas.add(dataPro);
						break;
					default:
						break;
				}
			}
		}

		DtPerfilProponente usuProponente = new DtPerfilProponente(auxUsuProponente.getNickname(), auxUsuProponente.getNombre(),
				auxUsuProponente.getApellido(),auxUsuProponente.getEmail(), auxUsuProponente.getFechaNacimiento(), auxUsuProponente.getImagen(),
				auxUsuProponente.getDireccion(), auxUsuProponente.getBiografia(), auxUsuProponente.getSitioWeb(),
				prPublicadas, prCanceladas, prEnFinanciacion, prFinanciadas, prNoFinanciadas);

		return usuProponente;
	}

	@Override
	public DtPerfilColaborador verPerfilColaborador(String nickname) {
		// TODO Auto-generated method stub

		ColaboradorHandler mcol = ColaboradorHandler.getInstance();
		DtColaborador perfil = mcol.obtenerColaborador(nickname); //1y2


		ColaboracionHandler mcolab = ColaboracionHandler.getInstance();
		Map<Long, Colaboracion> colabs = mcolab.getMapColaboraciones();

		ArrayList<DtPropuestaColaborada> colaboracionesHechas = new ArrayList<>();
		for(Colaboracion c : colabs.values()) { //1*
			if(c.tieneColaborador(nickname)) { //2* y 2.1*
				 float montoAportado = c.getMonto(); //3*
				 DtPropuestaColaborada p = c.getPropuestaFromColaboracion(); //4* y 4.1*
				 DtPropuestaColaborada colaboracion = new DtPropuestaColaborada(p.getTitulo(), p.getDescripcion(), p.getImagen(), montoAportado,
						 p.getProponenteACargo(), p.getEstadoActual()); //3.2*
				 colaboracionesHechas.add(colaboracion);
			}
		}

		return new DtPerfilColaborador(perfil.getNickname(), perfil.getNombre(), perfil.getApellido(), perfil.getEmail(),
				perfil.getFechaNacimiento(), perfil.getImagen(), colaboracionesHechas);
	}

	@Override
	public DtPropuesta[] listarPropuestasDeUnColaborador(String nickname) {
//		// TODO Auto-generated method stub
//		ColaboracionHandler c = ColaboracionHandler.getInstance();
//		Colaboracion[] colaboraciones = c.getColaboraciones();
//
		return null;
	}

}
