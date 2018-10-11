package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import datatype.DtColaborador;
import datatype.DtProponente;
import datatype.DtUsuario;
import excepciones.ColaboradorNoExisteException;
import excepciones.ProponenteNoExisteException;
import excepciones.UsuarioNoExisteElUsuarioException;
import excepciones.UsuarioYaExisteElEmailException;
import excepciones.UsuarioYaExisteElUsuarioException;
import excepciones.UsuarioYaSigueAlUsuarioException;
import logica.Factory;
import logica.ICategoriaController;
import logica.IPropuestaController;
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
	
	@Test(expected = Test.None.class)
	public void listarUsuariosDevuelveTodosLosUsuarios() throws UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException, UsuarioYaSigueAlUsuarioException {
		DtUsuario dtNuevoUsuarioUno = new DtColaborador("testNicknameUno", "testNombre", "testApellido", 
				"testCorreoUno", null, new GregorianCalendar(), null);
		DtUsuario dtNuevoUsuarioDos = new DtColaborador("testNicknameDos", "testNombre", "testApellido", 
				"testCorreoDos", null, new GregorianCalendar(), null);
        Factory factory = Factory.getInstance();
        IUsuarioController IUC = factory.getIUsuarioController();
        IUC.agregarUsuario(dtNuevoUsuarioUno);
        IUC.agregarUsuario(dtNuevoUsuarioDos);
        DtUsuario[] dtUsuarios = IUC.listarUsuarios();
        assertEquals(dtNuevoUsuarioUno.getNickname(), dtUsuarios[0].getNickname());
	}
	
	@Test(expected = Test.None.class)
	public void listarUsuariosQueSigue() throws UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException, UsuarioYaSigueAlUsuarioException {
		DtUsuario dtNuevoUsuarioUno = new DtColaborador("testNicknameUno", "testNombre", "testApellido", 
				"testCorreoUno", null, new GregorianCalendar(), null);
		DtUsuario dtNuevoUsuarioDos = new DtColaborador("testNicknameDos", "testNombre", "testApellido", 
				"testCorreoDos", null, new GregorianCalendar(), null);
		DtUsuario dtNuevoUsuarioTres = new DtProponente("testNicknameTres", "testNombre", "testApellido", 
				"testCorreoTres", null, new GregorianCalendar(), null, "testDireccion", "TestBiografia", 
				"testSitioWeb");
		DtUsuario[] dtUsuariosEsperados = new DtUsuario[1];
		dtUsuariosEsperados[0] = dtNuevoUsuarioDos;
        Factory factory = Factory.getInstance();
        IUsuarioController IUC = factory.getIUsuarioController();
        IUC.agregarUsuario(dtNuevoUsuarioUno);
        IUC.agregarUsuario(dtNuevoUsuarioDos);
        IUC.agregarUsuario(dtNuevoUsuarioTres);
        IUC.seguirUsuario(dtNuevoUsuarioUno.getNickname(), dtNuevoUsuarioDos.getNickname());
        IUC.seguirUsuario(dtNuevoUsuarioUno.getNickname(), dtNuevoUsuarioTres.getNickname());
        DtUsuario[] dtUsuarios = IUC.listarUsuariosQueSigue(dtNuevoUsuarioUno.getNickname());
        assertEquals(dtUsuariosEsperados[0].getNickname(), dtUsuarios[0].getNickname());
	}
	
	@Test(expected = Test.None.class)
	public void listarUsuariosQueSigueNoSigueANinguno() throws UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException, UsuarioYaSigueAlUsuarioException {
		DtUsuario dtNuevoUsuarioUno = new DtColaborador("testNicknameUno", "testNombre", "testApellido", 
				"testCorreoUno", null, new GregorianCalendar(), null);
		DtUsuario dtNuevoUsuarioDos = new DtColaborador("testNicknameDos", "testNombre", "testApellido", 
				"testCorreoDos", null, new GregorianCalendar(), null);
		DtUsuario[] dtUsuariosEsperados = new DtUsuario[0];
        Factory factory = Factory.getInstance();
        IUsuarioController IUC = factory.getIUsuarioController();
        IUC.agregarUsuario(dtNuevoUsuarioUno);
        IUC.agregarUsuario(dtNuevoUsuarioDos);
        DtUsuario[] dtUsuarios = IUC.listarUsuariosQueSigue(dtNuevoUsuarioUno.getNickname());
        assertArrayEquals(dtUsuariosEsperados, dtUsuarios);
	}
	
	@Test(expected = ColaboradorNoExisteException.class)
	public void listarColaboradoresNoHayRegistrados() throws ColaboradorNoExisteException {
        Factory factory = Factory.getInstance();
        IUsuarioController IUC = factory.getIUsuarioController();
        DtUsuario[] dtUsuariosEsperados = IUC.listarColaboradores();
	}
	
	@Test(expected = Test.None.class)
	public void listarColaboradores() throws ColaboradorNoExisteException, UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException {
        Factory factory = Factory.getInstance();
        IUsuarioController IUC = factory.getIUsuarioController();
		DtUsuario dtNuevoUsuario = new DtColaborador("testNicknameUno", "testNombre", "testApellido", 
				"testCorreoUno", null, new GregorianCalendar(), null);
        IUC.agregarUsuario(dtNuevoUsuario);
        DtUsuario[] dtUsuariosEsperados = IUC.listarColaboradores();
	}
	
	@Test(expected = ProponenteNoExisteException.class)
	public void listarProponentesNoHayRegistrados() throws ProponenteNoExisteException {
        Factory factory = Factory.getInstance();
        IUsuarioController IUC = factory.getIUsuarioController();
        DtUsuario[] dtUsuariosEsperados = IUC.listarProponentes();
	}
	
	@Test(expected = Test.None.class)
	public void listarProponentes() throws ProponenteNoExisteException, UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException {
        Factory factory = Factory.getInstance();
        IUsuarioController IUC = factory.getIUsuarioController();
        DtUsuario dtNuevoUsuario = new DtProponente("testNicknameUno", "testNombre", "testApellido", 
				"testCorreoUno", null, new GregorianCalendar(), null, "testDireccion", "TestBiografia", 
				"testSitioWeb");
        IUC.agregarUsuario(dtNuevoUsuario);
        DtUsuario[] dtUsuariosEsperados = IUC.listarProponentes();
	}
	
	@Test(expected = UsuarioNoExisteElUsuarioException.class)
	public void iniciarSesionNoExisteElUsuario() throws UsuarioNoExisteElUsuarioException{
        Factory factory = Factory.getInstance();
        IUsuarioController IUC = factory.getIUsuarioController();
        DtUsuario dtUsuario = IUC.iniciarSesion("testUsuario", "testPassword".toCharArray());
	}
	
	@Test(expected = Test.None.class)
	public void iniciarSesionValidoConNickname() throws UsuarioNoExisteElUsuarioException, UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException{
        Factory factory = Factory.getInstance();
        IUsuarioController IUC = factory.getIUsuarioController();
        DtUsuario dtNuevoUsuario = new DtColaborador("testNickname", "testNombre", "testApellido", 
				"testCorreoUno", "testPassword".toCharArray(), new GregorianCalendar(), null);
        IUC.agregarUsuario(dtNuevoUsuario);
        DtUsuario dtUsuario = IUC.iniciarSesion("testNickname", "testPassword".toCharArray());
        assertEquals(dtNuevoUsuario.getNickname(), dtUsuario.getNickname());
	}
	
	@Test(expected = Test.None.class)
	public void iniciarSesionValidoConEmail() throws UsuarioNoExisteElUsuarioException, UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException{
        Factory factory = Factory.getInstance();
        IUsuarioController IUC = factory.getIUsuarioController();
        DtUsuario dtNuevoUsuario = new DtColaborador("testNickname", "testNombre", "testApellido", 
				"testCorreoUno", "testPassword".toCharArray(), new GregorianCalendar(), null);
        IUC.agregarUsuario(dtNuevoUsuario);
        DtUsuario dtUsuario = IUC.iniciarSesion("testCorreoUno", "testPassword".toCharArray());
        assertEquals(dtNuevoUsuario.getNickname(), dtUsuario.getNickname());
	}
	
	@Before
	public void eliminarDatos() {
		Factory factory = Factory.getInstance();
        IPropuestaController IPC = factory.getIPropuestaController();
        IUsuarioController IUC = factory.getIUsuarioController();
        ICategoriaController ICC = factory.getICategoriaController();
		IPC.borrarPropuestas();
		IUC.borrarUsuarios();
		ICC.borrarCategorias();
	}
	
}
