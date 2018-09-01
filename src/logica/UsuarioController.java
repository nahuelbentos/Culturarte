package logica;

import java.util.ArrayList;
import java.util.List;

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
import datatype.TipoRetorno;
import excepciones.ColaboradorNoExisteException;
import excepciones.UsuarioNoExisteElUsuarioException;
import excepciones.UsuarioYaExisteElUsuarioException;
import excepciones.UsuarioYaSigueAlUsuarioException;
import logica.handler.ColaboracionHandler;
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
		emf = Persistence.createEntityManagerFactory("Conexion");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		DtUsuario[] dtUsuario = null;
        List<Usuario> usuarios = em.createQuery("FROM Usuario WHERE TIPOUSUARIO = 'P'").getResultList();
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
		emf = Persistence.createEntityManagerFactory("Conexion");
		em = emf.createEntityManager();
		em.getTransaction().begin();

        Usuario usuarioUno = em.find(Usuario.class, nicknameUno);
        Usuario usuarioDos = em.find(Usuario.class, nicknameDos);
        usuarioUno.dejarDeSeguirUsuario(usuarioDos);
        
        UsuarioSigueID usuarioSigueId = new UsuarioSigueID();
        usuarioSigueId.setUsuarioUno(nicknameUno);
        usuarioSigueId.setUsuarioDos(nicknameDos);
        
		em.createQuery("delete from UsuarioSigue where id = :id").setParameter("id", usuarioSigueId).executeUpdate();
		em.getTransaction().commit();
		em.close();
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
	public DtUsuario[] listarColaboradores() throws ColaboradorNoExisteException {
		emf = Persistence.createEntityManagerFactory("Conexion");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		DtUsuario[] dtUsuario = null;
        List<Usuario> usuarios = em.createQuery("FROM Usuario WHERE TIPOUSUARIO = 'C'").getResultList();
        if (usuarios != null) {
            dtUsuario = new DtUsuario[usuarios.size()];
            Usuario usuario;
            for (int i = 0; i < usuarios.size(); i++) {
                usuario = usuarios.get(i);
                dtUsuario[i] = new DtUsuario(usuario.getNickname(), usuario.getNombre(),
                		usuario.getApellido(), usuario.getCorreoElectronico(), usuario.getFechaNacimiento(), usuario.getImagen());
            }
            em.close();
            return dtUsuario;
        }else {
        	em.close();
        	throw new ColaboradorNoExisteException("No hay colaboradores registrados");
        }
	}


	@Override
	public DtPerfilProponente verPerfilProponente(String nickname) {
		emf = Persistence.createEntityManagerFactory("Conexion");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
        List<Propuesta> propouestas = em.createQuery("FROM Propuesta").getResultList();

//		ColaboracionHandler mcol = ColaboracionHandler.getInstance();
//		Map<Long, Colaboracion> colabs = mcol.getMapColaboraciones();

        List<Colaboracion> colabs = em.createQuery("FROM Colaboracion").getResultList();
		
        Proponente p = em.find(Proponente.class, nickname); //1
		DtPerfilProponente auxUsuProponente = p.getDatosBasicos(); //2

		ArrayList<DtPropuesta> prPublicadas = new ArrayList<DtPropuesta>();
		ArrayList<DtPropuesta> prCanceladas = new ArrayList<DtPropuesta>();
		ArrayList<DtPropuesta> prEnFinanciacion = new ArrayList<DtPropuesta>();
		ArrayList<DtPropuesta> prFinanciadas = new ArrayList<DtPropuesta>();
		ArrayList<DtPropuesta> prNoFinanciadas = new ArrayList<DtPropuesta>();

		for(int i = 0; i < propouestas.size(); i++) { //3
			Propuesta prop = propouestas.get(i);
			if(prop.isProponenteACargo(nickname)) {

				ArrayList<DtColaboracion> colaboraciones = new ArrayList<DtColaboracion>();
				for(Colaboracion col : colabs) { //6
					if(col.tieneProp(prop.getTitulo())) {
						colaboraciones.add(col.getDataColaboracion());
					}
				}

				DtPropuesta dataPro = new DtPropuesta(prop.getTitulo(), prop.getDescripcion(), prop.getImagen(),prop.getMontoNecesario(),
				 prop.getFechaPublicacion(), prop.getFechaEspecatulo(), prop.getLugar(), prop.getPrecioEntrada(), TipoRetorno.EntradasGratis, 0,
				 prop.getProponenteACargo().getDtProponente(),/* prop.getEstadoActual().getDtEstado()*/null, prop.getDtEstadoHistorial(),
				 prop.getCategoria().getDtCategoria(), colaboraciones);
				
				switch (dataPro.getEstadoActual()){
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

		em.close();
		
		return new DtPerfilProponente(auxUsuProponente.getNickname(), auxUsuProponente.getNombre(),
				auxUsuProponente.getApellido(),auxUsuProponente.getEmail(), auxUsuProponente.getFechaNacimiento(), auxUsuProponente.getImagen(),
				auxUsuProponente.getDireccion(), auxUsuProponente.getBiografia(), auxUsuProponente.getSitioWeb(),
				prPublicadas, prCanceladas, prEnFinanciacion, prFinanciadas, prNoFinanciadas);
	}

	@Override
	public DtPerfilColaborador verPerfilColaborador(String nickname) {		
		emf = Persistence.createEntityManagerFactory("Conexion");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Usuario perfil = em.find(Usuario.class, nickname); //1y2

        List<Colaboracion> colabs = em.createQuery("FROM Colaboracion").getResultList();

		ArrayList<DtPropuestaColaborada> colaboracionesHechas = new ArrayList<DtPropuestaColaborada>();
		
		for(Colaboracion c : colabs) { //1*
			if(c.tieneColaborador(nickname)) { //2* y 2.1*
				 double montoAportado = c.getMonto(); //3*
				 DtPropuestaColaborada p = c.getPropuestaFromColaboracion(); //4* y 4.1*
				 DtPropuestaColaborada colaboracion = new DtPropuestaColaborada(p.getTitulo(), p.getDescripcion(), p.getImagen(), montoAportado,
						 p.getProponenteACargo(), p.getEstadoActual()); //3.2*
				 colaboracionesHechas.add(colaboracion);
			}
		}
		em.close();
		return new DtPerfilColaborador(perfil.getNickname(), perfil.getNombre(), perfil.getApellido(), perfil.getCorreoElectronico(),
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
