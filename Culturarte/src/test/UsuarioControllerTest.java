package test;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import datatype.DtColaborador;
import datatype.DtProponente;
import datatype.DtUsuario;
import excepciones.UsuarioYaExisteElEmailException;
import excepciones.UsuarioYaExisteElUsuarioException;
import excepciones.UsuarioYaSigueAlUsuarioException;
import logica.Factory;
import logica.IUsuarioController;
import logica.UsuarioController;

public class UsuarioControllerTest {

	@Test(expected = Test.None.class)
	public void agregarUsuarioProponenteCorrectamente() throws UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException {
		DtUsuario dtNuevoUsuario = new DtProponente("testNickname", "testNombre", "testApellido", 
				"testCorreo", null, new GregorianCalendar(), null, "testDireccion", "TestBiografia", 
				"testSitioWeb");
        Factory factory = Factory.getInstance();
        IUsuarioController IUC = factory.getIUsuarioController();
        IUC.agregarUsuario(dtNuevoUsuario);
	}
	
	@Test(expected = Test.None.class)
	public void agregarUsuarioColaboradorCorrectamente() throws UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException {
		DtUsuario dtNuevoUsuario = new DtColaborador("testNickname", "testNombre", "testApellido", 
				"testCorreo", null, new GregorianCalendar(), null);
        Factory factory = Factory.getInstance();
        IUsuarioController IUC = factory.getIUsuarioController();
        IUC.agregarUsuario(dtNuevoUsuario);
	}
	
	@Test(expected = UsuarioYaExisteElUsuarioException.class)
	public void yaExisteUsuarioConEseNickname() throws UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException {
		DtUsuario dtNuevoUsuario = new DtProponente("testNickname", "testNombre", "testApellido", 
				"testCorreo", null, new GregorianCalendar(), null, "testDireccion", "TestBiografia", 
				"testSitioWeb");
        Factory factory = Factory.getInstance();
        IUsuarioController IUC = factory.getIUsuarioController();
        IUC.agregarUsuario(dtNuevoUsuario);
        IUC.agregarUsuario(dtNuevoUsuario);
	}
	
	@Test(expected = UsuarioYaExisteElEmailException.class)
	public void yaExisteUsuarioConEseEmail() throws UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException {
		DtUsuario dtNuevoUsuarioUno = new DtProponente("testNicknameUno", "testNombre", "testApellido", 
				"testCorreo", null, new GregorianCalendar(), null, "testDireccion", "TestBiografia", 
				"testSitioWeb");
		DtUsuario dtNuevoUsuarioDos = new DtProponente("testNicknameDos", "testNombre", "testApellido", 
				"testCorreo", null, new GregorianCalendar(), null, "testDireccion", "TestBiografia", 
				"testSitioWeb");
        Factory factory = Factory.getInstance();
        IUsuarioController IUC = factory.getIUsuarioController();
        IUC.agregarUsuario(dtNuevoUsuarioUno);
        IUC.agregarUsuario(dtNuevoUsuarioDos);
	}
	
	@Test(expected = Test.None.class)
	public void seguirUsuarioCorrectamente() throws UsuarioYaSigueAlUsuarioException, UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException {
		DtUsuario dtNuevoUsuarioUno = new DtProponente("testNicknameUno", "testNombre", "testApellido", 
				"testCorreoUno", null, new GregorianCalendar(), null, "testDireccion", "TestBiografia", 
				"testSitioWeb");
		DtUsuario dtNuevoUsuarioDos = new DtProponente("testNicknameDos", "testNombre", "testApellido", 
				"testCorreoDos", null, new GregorianCalendar(), null, "testDireccion", "TestBiografia", 
				"testSitioWeb");
        Factory factory = Factory.getInstance();
        IUsuarioController IUC = factory.getIUsuarioController();
        IUC.agregarUsuario(dtNuevoUsuarioUno);
        IUC.agregarUsuario(dtNuevoUsuarioDos);
        IUC.seguirUsuario(dtNuevoUsuarioUno.getNickname(), dtNuevoUsuarioDos.getNickname());
	}
	
	@Test(expected = UsuarioYaSigueAlUsuarioException.class)
	public void usuarioYaSigueAlUsuario() throws UsuarioYaSigueAlUsuarioException, UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException {
		DtUsuario dtNuevoUsuarioUno = new DtProponente("testNicknameUno", "testNombre", "testApellido", 
				"testCorreoUno", null, new GregorianCalendar(), null, "testDireccion", "TestBiografia", 
				"testSitioWeb");
		DtUsuario dtNuevoUsuarioDos = new DtProponente("testNicknameDos", "testNombre", "testApellido", 
				"testCorreoDos", null, new GregorianCalendar(), null, "testDireccion", "TestBiografia", 
				"testSitioWeb");
        Factory factory = Factory.getInstance();
        IUsuarioController IUC = factory.getIUsuarioController();
        IUC.agregarUsuario(dtNuevoUsuarioUno);
        IUC.agregarUsuario(dtNuevoUsuarioDos);
        IUC.seguirUsuario(dtNuevoUsuarioUno.getNickname(), dtNuevoUsuarioDos.getNickname());
        IUC.seguirUsuario(dtNuevoUsuarioUno.getNickname(), dtNuevoUsuarioDos.getNickname());
	}
	
	@Test(expected = Test.None.class)
	public void dejarDeSeguirUsuarioCorrectamente() throws UsuarioYaSigueAlUsuarioException, UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException {
		DtUsuario dtNuevoUsuarioUno = new DtProponente("testNicknameUno", "testNombre", "testApellido", 
				"testCorreoUno", null, new GregorianCalendar(), null, "testDireccion", "TestBiografia", 
				"testSitioWeb");
		DtUsuario dtNuevoUsuarioDos = new DtProponente("testNicknameDos", "testNombre", "testApellido", 
				"testCorreoDos", null, new GregorianCalendar(), null, "testDireccion", "TestBiografia", 
				"testSitioWeb");
        Factory factory = Factory.getInstance();
        IUsuarioController IUC = factory.getIUsuarioController();
        IUC.agregarUsuario(dtNuevoUsuarioUno);
        IUC.agregarUsuario(dtNuevoUsuarioDos);
        IUC.seguirUsuario(dtNuevoUsuarioUno.getNickname(), dtNuevoUsuarioDos.getNickname());
        IUC.dejarDeSeguirUsuario(dtNuevoUsuarioUno.getNickname(), dtNuevoUsuarioDos.getNickname());
	}
	
	@Test(expected = Test.None.class)
	public void obtenerPerfilDeUsuarioProponenteCorrectamente() throws UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException, UsuarioYaSigueAlUsuarioException {
		DtUsuario dtNuevoUsuarioUno = new DtProponente("testNicknameUno", "testNombre", "testApellido", 
				"testCorreoUno", null, new GregorianCalendar(), null, "testDireccion", "TestBiografia", 
				"testSitioWeb");
		DtUsuario dtNuevoUsuarioDos = new DtProponente("testNicknameDos", "testNombre", "testApellido", 
				"testCorreoDos", null, new GregorianCalendar(), null, "testDireccion", "TestBiografia", 
				"testSitioWeb");
        Factory factory = Factory.getInstance();
        IUsuarioController IUC = factory.getIUsuarioController();
        IUC.agregarUsuario(dtNuevoUsuarioUno);
        IUC.agregarUsuario(dtNuevoUsuarioDos);
        IUC.seguirUsuario(dtNuevoUsuarioUno.getNickname(), dtNuevoUsuarioDos.getNickname());
        IUC.obtenerPerfilUsuario(dtNuevoUsuarioUno.getNickname(), dtNuevoUsuarioUno);
	}
	
	@Test(expected = Test.None.class)
	public void obtenerPerfilDeUsuarioColaboradorCorrectamente() throws UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException, UsuarioYaSigueAlUsuarioException {
		DtUsuario dtNuevoUsuarioUno = new DtColaborador("testNicknameUno", "testNombre", "testApellido", 
				"testCorreoUno", null, new GregorianCalendar(), null);
		DtUsuario dtNuevoUsuarioDos = new DtColaborador("testNicknameDos", "testNombre", "testApellido", 
				"testCorreoDos", null, new GregorianCalendar(), null);
        Factory factory = Factory.getInstance();
        IUsuarioController IUC = factory.getIUsuarioController();
        IUC.agregarUsuario(dtNuevoUsuarioUno);
        IUC.agregarUsuario(dtNuevoUsuarioDos);
        IUC.seguirUsuario(dtNuevoUsuarioUno.getNickname(), dtNuevoUsuarioDos.getNickname());
        IUC.obtenerPerfilUsuario(dtNuevoUsuarioUno.getNickname(), dtNuevoUsuarioUno);
	}
	
	@Before
	public void eliminarUsuarios() {
		Factory factory = Factory.getInstance();
        IUsuarioController IUC = factory.getIUsuarioController();
        IUC.borrarUsuariosTests();
	}
	
}
