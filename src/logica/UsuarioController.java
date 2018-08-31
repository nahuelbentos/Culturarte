package logica;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import datatype.DtColaboracion;
import datatype.DtColaborador;
import datatype.DtPerfilColaborador;
import datatype.DtPerfilProponente;
import datatype.DtProponente;
import datatype.DtPropuesta;
import datatype.DtPropuestaColaborada;
import datatype.DtUsuario;
import datatype.EstadoPropuesta;
import datatype.TipoRetorno;
import excepciones.UsuarioNoExisteElUsuarioException;
import excepciones.UsuarioYaExisteElUsuarioException;
import excepciones.UsuarioYaSigueAlUsuarioException;
import logica.exceptions.ColaboradorNoExisteException;
import logica.handler.ColaboracionHandler;
import logica.handler.ColaboradorHandler;
import logica.handler.ProponenteHandler;
import logica.handler.PropuestaHandler;

public class UsuarioController implements IUsuarioController {

	private static EntityManager em;
	private static EntityManagerFactory emf;

	public UsuarioController() {
		super();
	}

	@Override
	public void agregarUsuario(DtUsuario dtUsuario) throws UsuarioYaExisteElUsuarioException {
		emf = Persistence.createEntityManagerFactory("Conexion");
		em = emf.createEntityManager();
		em.getTransaction().begin();

		Usuario usuario = em.find(Usuario.class, dtUsuario.getNickname());
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
				//proponenteHandler.agregarProponente(usuario);

			} else if (dtUsuario instanceof DtColaborador) {
				DtColaborador dtColaborador = (DtColaborador) dtUsuario;
				usuario = new Colaborador(dtColaborador.getNickname(), dtColaborador.getNombre(),
						dtColaborador.getFechaNacimiento(), dtColaborador.getEmail(), dtColaborador.getApellido(), dtColaborador.getImagen());

				colaboradorHandler.addColaborador(usuario);
			}
			
			em.persist(usuario);
			em.getTransaction().commit();
			em.close();
		}
	}

	@Override
	public DtColaboracion listarColaboracion(String titulo, String nickname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public  DtUsuario[] listarProponentes() {
		
		// Falta agregar la condicion en la query para que traiga solo los proponentes
		
		emf = Persistence.createEntityManagerFactory("Conexion");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		DtUsuario[] dtUsuario = null;
        List<Usuario> usuarios = em.createQuery("FROM Usuario").getResultList();
        if (usuarios != null) {
            dtUsuario = new DtUsuario[usuarios.size()];
            Usuario usuario;
            for (int i = 0; i < usuarios.size(); i++) {
                usuario = usuarios.get(i);
                dtUsuario[i] = new DtUsuario(usuario.getNickname(), usuario.getNombre(),
                		usuario.getApellido(), usuario.getCorreoElectronico(), usuario.getFechaNacimiento(), usuario.getImagen());
            }
        }
        
        em.close();
        
        return dtUsuario;
	}

	@Override
	public void seguirUsuario(String nicknameUno, String nicknameDos) throws UsuarioYaSigueAlUsuarioException {
		emf = Persistence.createEntityManagerFactory("Conexion");
		em = emf.createEntityManager();
		em.getTransaction().begin();

        Usuario usuarioUno = em.find(Usuario.class, nicknameUno);
        Usuario usuarioDos = em.find(Usuario.class, nicknameDos);
        
        UsuarioSigueID usuarioSIgueID = new UsuarioSigueID(nicknameUno,nicknameDos);
        UsuarioSigue usuarioSigue = em.find(UsuarioSigue.class, usuarioSIgueID);
        
		if (usuarioSigue != null) {
        	throw new UsuarioYaSigueAlUsuarioException("El usuario " + nicknameUno
        			+ " ya sigue al usuario " + nicknameDos);
		} else {
			usuarioUno.seguirUsuario(usuarioDos);
		}
        
		em.flush();
		em.getTransaction().commit();
		em.close();
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
		emf = Persistence.createEntityManagerFactory("Conexion");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		DtUsuario[] dtUsuario = null;
        List<Usuario> usuarios = em.createQuery("FROM Usuario").getResultList();
        if (usuarios != null) {
            dtUsuario = new DtUsuario[usuarios.size()];
            Usuario usuario;
            for (int i = 0; i < usuarios.size(); i++) {
                usuario = usuarios.get(i);
                dtUsuario[i] = new DtUsuario(usuario.getNickname(), usuario.getNombre(),
                		usuario.getApellido(), usuario.getCorreoElectronico(), usuario.getFechaNacimiento(), usuario.getImagen());
            }
        }
        
        em.close();
        
        return dtUsuario;
	}

	@Override
	public DtUsuario verPerfilUsuario(String nickname) throws UsuarioNoExisteElUsuarioException {
		emf = Persistence.createEntityManagerFactory("Conexion");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Usuario usuario = em.find(Usuario.class, nickname);
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
        } else {
            throw new UsuarioNoExisteElUsuarioException("El usuario " + nickname + " no existe");
        }
        
        em.close();
        
        return dtUsuario;
	}

	@Override
	public DtUsuario[] listarUsuariosQueSigue(String nickname) {
		emf = Persistence.createEntityManagerFactory("Conexion");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		DtUsuario[] dtUsuario = null;
        List<Usuario> usuarios = em.createQuery("SELECT usuarioDos FROM UsuarioSigue").getResultList();
		if (usuarios != null) {
	        DtUsuario[] listaDeUsuarios = new DtUsuario[usuarios.size()];
	        Usuario usuarioDos;
	        for (int i = 0; i < usuarios.size(); i++) {
	        	usuarioDos = usuarios.get(i);
	        	listaDeUsuarios[i] = new DtUsuario(usuarioDos.getNickname(), usuarioDos.getNombre(),
	        			usuarioDos.getApellido(), usuarioDos.getCorreoElectronico(), usuarioDos.getFechaNacimiento(), usuarioDos.getImagen());
	        }
	        return listaDeUsuarios;
		}
		return null;
	}

	@Override
	public DtColaborador[] listarColaboradores() throws ColaboradorNoExisteException {

/*

		emf = Persistence.createEntityManagerFactory("Conexion");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		DtUsuario[] dtUsuario = null;
        List<Usuario> usuarios = em.createQuery("FROM Usuario").getResultList();
        if (usuarios != null) {
            dtUsuario = new DtUsuario[usuarios.size()];
            Usuario usuario;
            for (int i = 0; i < usuarios.size(); i++) {
                usuario = usuarios.get(i);
                dtUsuario[i] = new DtUsuario(usuario.getNickname(), usuario.getNombre(),
                		usuario.getApellido(), usuario.getCorreoElectronico(), usuario.getFechaNacimiento(), usuario.getImagen());
            }
        }
        
        em.close();
        
        return dtUsuario;


 * */
		emf = Persistence.createEntityManagerFactory("Conexion");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		DtColaborador[] dtColaboradores = null;
		

		@SuppressWarnings("unchecked")
		List<Colaborador> colaboradores = em.createQuery("FROM Colaborador").getResultList();
		
		if (colaboradores != null) {
			dtColaboradores = new DtColaborador[colaboradores.size()];
			Colaborador c;

			for (int i = 0; i < colaboradores.size(); i++) {
				c = colaboradores.get(i);
				dtColaboradores[i] = new DtColaborador(c.getNickname(), c.getNombre(), c.getApellido(),
						c.getCorreoElectronico(), c.getFechaNacimiento(), c.getImagen());
            }

			return dtColaboradores;
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
				 prop.getFechaPublicacion(), prop.getFechaEspecatulo(), prop.getLugar(), prop.getPrecioEntrada(), TipoRetorno.entradasGratis, 0,
				 prop.getProponenteACargo().getDtProponente(),/* prop.getEstadoActual().getDtEstado()*/null, prop.getDtEstadoHistorial(),
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
				 double montoAportado = c.getMonto(); //3*
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

	@Override
	public void crearPropuestaAuxiliar() {
		// TODO Auto-generated method stub
		
	}

}
