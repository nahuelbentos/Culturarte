package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import datatype.DtCategoria;
import datatype.DtColaboracion;
import datatype.DtColaborador;
import datatype.DtProponente;
import datatype.DtPropuesta;
import datatype.DtUsuario;
import datatype.TipoRetorno;
import excepciones.CategoriaNoExisteException;
import excepciones.CategoriaYaExisteException;
import excepciones.ColaboracionExistenteException;
import excepciones.ColaboradorNoExisteException;
import excepciones.ProponenteNoExisteException;
import excepciones.PropuestaNoExisteException;
import excepciones.PropuestaRepetidaException;
import excepciones.UsuarioNoExisteElUsuarioException;
import excepciones.UsuarioSinLoguearseException;
import excepciones.UsuarioYaExisteElEmailException;
import excepciones.UsuarioYaExisteElUsuarioException;
import excepciones.UsuarioYaSigueAlUsuarioException;
import logica.Factory;
import logica.ICategoriaController;
import logica.IPropuestaController;
import logica.IUsuarioController;
import logica.UsuarioController;

public class UsuarioControllerTest {

	private static Factory factory;
	private static IUsuarioController IUC;
	private static IPropuestaController IPC;
	private static ICategoriaController ICC;
	
	// Metodo que se ejecuta antes de todos los before
	@BeforeClass
	public static void antesDeTodo() throws UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException {
		factory = Factory.getInstance();
		IUC = factory.getIUsuarioController();
		IPC = factory.getIPropuestaController();
		ICC = factory.getICategoriaController();	
	}
	
	@Test(expected = Test.None.class)
	public void agregarUsuarioProponenteCorrectamente() throws UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException {
		DtUsuario dtNuevoUsuario = new DtProponente("testNickname", "testNombre", "testApellido", 
				"testCorreo", null, new GregorianCalendar(), null, "testDireccion", "TestBiografia", 
				"testSitioWeb");
        IUC.agregarUsuario(dtNuevoUsuario);
	}
	
	@Test(expected = Test.None.class)
	public void agregarUsuarioColaboradorCorrectamente() throws UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException {
		DtUsuario dtNuevoUsuario = new DtColaborador("testNickname", "testNombre", "testApellido", 
				"testCorreo", null, new GregorianCalendar(), null);
        IUC.agregarUsuario(dtNuevoUsuario);
	}
	
	@Test(expected = UsuarioYaExisteElUsuarioException.class)
	public void yaExisteUsuarioConEseNickname() throws UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException {
		DtUsuario dtNuevoUsuario = new DtProponente("testNickname", "testNombre", "testApellido", 
				"testCorreo", null, new GregorianCalendar(), null, "testDireccion", "TestBiografia", 
				"testSitioWeb");
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
        IUC.agregarUsuario(dtNuevoUsuarioUno);
        IUC.agregarUsuario(dtNuevoUsuarioDos);
        IUC.seguirUsuario(dtNuevoUsuarioUno.getNickname(), dtNuevoUsuarioDos.getNickname());
        IUC.obtenerPerfilUsuario(dtNuevoUsuarioUno.getNickname(), dtNuevoUsuarioUno);
	}
	
	@Test(expected = Test.None.class)
	public void obtenerPerfilDeUsuarioProponenteConPropuestas() throws UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException, UsuarioYaSigueAlUsuarioException, CategoriaYaExisteException, CategoriaNoExisteException, PropuestaRepetidaException, ProponenteNoExisteException {
		DtUsuario dtNuevoUsuarioUno = new DtProponente("testNicknameUno", "testNombre", "testApellido", 
				"testCorreoUno", null, new GregorianCalendar(), null, "testDireccion", "TestBiografia", 
				"testSitioWeb");
        IUC.agregarUsuario(dtNuevoUsuarioUno);
	
		String nombreCategoria = "testCategoria";
		ArrayList<DtCategoria> padres = new ArrayList<>();
		DtCategoria cat = new DtCategoria(nombreCategoria, padres, null);
        
		DtPropuesta p = new DtPropuesta("tituloPropuestaTest","dscPropuestaTest",null,40000,new GregorianCalendar(),
				new GregorianCalendar(),"lugarPropuestaTest",100,TipoRetorno.EntradasYPorcentaje,0,(DtProponente)dtNuevoUsuarioUno,null,
				null,cat,null);
		
		IPC.altaPropuesta(p);
		
		IUC.obtenerPerfilUsuario(dtNuevoUsuarioUno.getNickname(), dtNuevoUsuarioUno);
	}
	
	@Test(expected = Test.None.class)
	public void verPerfilProponenteTest() throws UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException, UsuarioYaSigueAlUsuarioException, CategoriaYaExisteException, CategoriaNoExisteException, PropuestaRepetidaException, ProponenteNoExisteException {
		DtUsuario dtNuevoUsuarioUno = new DtProponente("testNicknameUno", "testNombre", "testApellido", 
				"testCorreoUno", null, new GregorianCalendar(), null, "testDireccion", "TestBiografia", 
				"testSitioWeb");
        IUC.agregarUsuario(dtNuevoUsuarioUno);
	
		String nombreCategoria = "testCategoria";
		ArrayList<DtCategoria> padres = new ArrayList<>();
		DtCategoria cat = new DtCategoria(nombreCategoria, padres, null);
        
		DtPropuesta p = new DtPropuesta("tituloPropuestaTest","dscPropuestaTest",null,40000,new GregorianCalendar(),
				new GregorianCalendar(),"lugarPropuestaTest",100,TipoRetorno.EntradasYPorcentaje,0,(DtProponente)dtNuevoUsuarioUno,null,
				null,cat,null);
		
		IPC.altaPropuesta(p);
		
		IUC.verPerfilProponente(dtNuevoUsuarioUno.getNickname());
	}
	
	@Test(expected = Test.None.class)
	public void listarUsuariosDevuelveTodosLosUsuarios() throws UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException, UsuarioYaSigueAlUsuarioException {
		DtUsuario dtNuevoUsuarioUno = new DtColaborador("testNicknameUno", "testNombre", "testApellido", 
				"testCorreoUno", null, new GregorianCalendar(), null);
		DtUsuario dtNuevoUsuarioDos = new DtColaborador("testNicknameDos", "testNombre", "testApellido", 
				"testCorreoDos", null, new GregorianCalendar(), null);
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
        IUC.agregarUsuario(dtNuevoUsuarioUno);
        IUC.agregarUsuario(dtNuevoUsuarioDos);
        DtUsuario[] dtUsuarios = IUC.listarUsuariosQueSigue(dtNuevoUsuarioUno.getNickname());
        assertArrayEquals(dtUsuariosEsperados, dtUsuarios);
	}
	
	@Test(expected = ColaboradorNoExisteException.class)
	public void listarColaboradoresNoHayRegistrados() throws ColaboradorNoExisteException {
        DtUsuario[] dtUsuariosEsperados = IUC.listarColaboradores();
	}
	
	@Test(expected = Test.None.class)
	public void listarColaboradores() throws ColaboradorNoExisteException, UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException {
		DtUsuario dtNuevoUsuario = new DtColaborador("testNicknameUno", "testNombre", "testApellido", 
				"testCorreoUno", null, new GregorianCalendar(), null);
        IUC.agregarUsuario(dtNuevoUsuario);
        DtUsuario[] dtUsuariosEsperados = IUC.listarColaboradores();
	}
	
	@Test(expected = ProponenteNoExisteException.class)
	public void listarProponentesNoHayRegistrados() throws ProponenteNoExisteException {
        DtUsuario[] dtUsuariosEsperados = IUC.listarProponentes();
	}
	
	@Test(expected = Test.None.class)
	public void listarProponentes() throws ProponenteNoExisteException, UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException {
        DtUsuario dtNuevoUsuario = new DtProponente("testNicknameUno", "testNombre", "testApellido", 
				"testCorreoUno", null, new GregorianCalendar(), null, "testDireccion", "TestBiografia", 
				"testSitioWeb");
        IUC.agregarUsuario(dtNuevoUsuario);
        DtUsuario[] dtUsuariosEsperados = IUC.listarProponentes();
	}
	
	@Test(expected = UsuarioNoExisteElUsuarioException.class)
	public void iniciarSesionNoExisteElUsuario() throws UsuarioNoExisteElUsuarioException{
        DtUsuario dtUsuario = IUC.iniciarSesion("testUsuario", "testPassword".toCharArray());
	}
		
	@Test(expected = Test.None.class)
	public void iniciarSesionValidoConNickname() throws UsuarioNoExisteElUsuarioException, UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException{
        DtUsuario dtNuevoUsuario = new DtColaborador("testNickname", "testNombre", "testApellido", 
				"testCorreoUno", "testPassword".toCharArray(), new GregorianCalendar(), null);
        IUC.agregarUsuario(dtNuevoUsuario);
        DtUsuario dtUsuario = IUC.iniciarSesion("testNickname", "testPassword".toCharArray());
        assertEquals(dtNuevoUsuario.getNickname(), dtUsuario.getNickname());
	}
	
	@Test(expected = Test.None.class)
	public void iniciarSesionValidoConEmail() throws UsuarioNoExisteElUsuarioException, UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException{
        DtUsuario dtNuevoUsuario = new DtColaborador("testNickname", "testNombre", "testApellido", 
				"testCorreoUno", "testPassword".toCharArray(), new GregorianCalendar(), null);
        IUC.agregarUsuario(dtNuevoUsuario);
        DtUsuario dtUsuario = IUC.iniciarSesion("testCorreoUno", "testPassword".toCharArray());
        assertEquals(dtNuevoUsuario.getNickname(), dtUsuario.getNickname());
	}
	
	@Test(expected = Test.None.class)
	public void verPerfilUsuario() throws UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException, UsuarioNoExisteElUsuarioException {
		DtUsuario dtNuevoUsuario = new DtProponente("testNickname", "testNombre", "testApellido", 
				"testCorreo", null, new GregorianCalendar(), null, "testDireccion", "TestBiografia", 
				"testSitioWeb");
        IUC.agregarUsuario(dtNuevoUsuario);
        IUC.verPerfilUsuario(dtNuevoUsuario.getNickname());
	}
	
	@Test(expected = UsuarioNoExisteElUsuarioException.class)
	public void verPerfilUsuarioNoExisteElUsuario() throws UsuarioNoExisteElUsuarioException {
        IUC.verPerfilUsuario("testUsuarioNoExiste");
	}
	
	@Test(expected = UsuarioSinLoguearseException.class)
	public void listarPropuestasColaboradorSinLoguearse() throws UsuarioNoExisteElUsuarioException, UsuarioSinLoguearseException {
        IUC.listarPropuestasColaborador(null);
	}
	
	@Test(expected = UsuarioSinLoguearseException.class)
	public void listarPropuestasColaboradorPasandoleUnProponente() throws UsuarioNoExisteElUsuarioException, UsuarioSinLoguearseException, UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException {
		DtUsuario dtNuevoUsuario = new DtProponente("testNicknameUno", "testNombre", "testApellido", 
				"testCorreoUno", null, new GregorianCalendar(), null, "testDireccion", "TestBiografia", 
				"testSitioWeb");
		IUC.agregarUsuario(dtNuevoUsuario);
        IUC.listarPropuestasColaborador(dtNuevoUsuario);
	}
	
	@Test(expected = Test.None.class)
	public void listarPropuestasColaboradorCorrectamente() throws UsuarioNoExisteElUsuarioException, UsuarioSinLoguearseException, UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException {
        DtUsuario dtNuevoUsuario = new DtColaborador("testNickname", "testNombre", "testApellido", 
				"testCorreoUno", "testPassword".toCharArray(), new GregorianCalendar(), null);
        IUC.agregarUsuario(dtNuevoUsuario);
        IUC.listarPropuestasColaborador(dtNuevoUsuario);
	}
	
	@Test(expected = Test.None.class)
	public void agregarComentarioAPropuestaCorrectamente() throws UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException, PropuestaRepetidaException, ProponenteNoExisteException, CategoriaNoExisteException, ColaboradorNoExisteException, PropuestaNoExisteException, ColaboracionExistenteException, UsuarioSinLoguearseException{
		DtUsuario dtNuevoUsuarioUno = new DtProponente("testNicknameUno", "testNombre", "testApellido", 
				"testCorreoUno", null, new GregorianCalendar(), null, "testDireccion", "TestBiografia", 
				"testSitioWeb");

        IUC.agregarUsuario(dtNuevoUsuarioUno);
	
		String nombreCategoria = "testCategoria";
		ArrayList<DtCategoria> padres = new ArrayList<>();
		DtCategoria cat = new DtCategoria(nombreCategoria, padres, null);
        
		DtPropuesta p = new DtPropuesta("tituloPropuestaTest","dscPropuestaTest",null,40000,new GregorianCalendar(),
				new GregorianCalendar(),"lugarPropuestaTest",100,TipoRetorno.EntradasYPorcentaje,0,(DtProponente)dtNuevoUsuarioUno,null,
				null,cat,null);
		
		IPC.altaPropuesta(p);
		
		DtUsuario col = new DtColaborador("testNicknameDos", "testNombre", "testApellido", 
				"testCorreoDos", null, new GregorianCalendar(), null);
		DtColaboracion colaboracion = new DtColaboracion(p.getTitulo(),col.getNickname(),100,new GregorianCalendar(),TipoRetorno.EntradasGratis);
		IUC.agregarUsuario(col);
		IPC.generarColaboracion(colaboracion);
		
		IUC.agregarComentarioAPropuesta("agrego comentario", p.getTitulo(),col);
	}
	
	@Test(expected = Test.None.class)
	public void verPerfilColaboradorTest() throws UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException, PropuestaRepetidaException, ProponenteNoExisteException, CategoriaNoExisteException, ColaboradorNoExisteException, PropuestaNoExisteException, ColaboracionExistenteException, UsuarioSinLoguearseException{
		DtUsuario dtNuevoUsuarioUno = new DtProponente("testNicknameUno", "testNombre", "testApellido", 
				"testCorreoUno", null, new GregorianCalendar(), null, "testDireccion", "TestBiografia", 
				"testSitioWeb");

        IUC.agregarUsuario(dtNuevoUsuarioUno);
	
		String nombreCategoria = "testCategoria";
		ArrayList<DtCategoria> padres = new ArrayList<>();
		DtCategoria cat = new DtCategoria(nombreCategoria, padres, null);
		DtPropuesta p = new DtPropuesta("tituloPropuestaTest","dscPropuestaTest",null,40000,new GregorianCalendar(),
				new GregorianCalendar(),"lugarPropuestaTest",100,TipoRetorno.EntradasYPorcentaje,0,(DtProponente)dtNuevoUsuarioUno,null,
				null,cat,null);
		
		IPC.altaPropuesta(p);
		
		DtUsuario col = new DtColaborador("testNicknameDos", "testNombre", "testApellido", 
				"testCorreoDos", null, new GregorianCalendar(), null);
		DtColaboracion colaboracion = new DtColaboracion(p.getTitulo(),col.getNickname(),100,new GregorianCalendar(),TipoRetorno.EntradasGratis);
		IUC.agregarUsuario(col);
		IPC.generarColaboracion(colaboracion);
		
		IUC.verPerfilColaborador(col.getNickname());
	}
	
	@Test(expected = Test.None.class)
	public void listarPropuestasColaboradorTest() throws UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException, PropuestaRepetidaException, ProponenteNoExisteException, CategoriaNoExisteException, ColaboradorNoExisteException, PropuestaNoExisteException, ColaboracionExistenteException, UsuarioSinLoguearseException{
		DtUsuario dtNuevoUsuarioUno = new DtProponente("testNicknameUno", "testNombre", "testApellido", 
				"testCorreoUno", null, new GregorianCalendar(), null, "testDireccion", "TestBiografia", 
				"testSitioWeb");

        IUC.agregarUsuario(dtNuevoUsuarioUno);
	
		String nombreCategoria = "testCategoria";
		ArrayList<DtCategoria> padres = new ArrayList<>();
		DtCategoria cat = new DtCategoria(nombreCategoria, padres, null);
        
		DtPropuesta p = new DtPropuesta("tituloPropuestaTest","dscPropuestaTest",null,40000,new GregorianCalendar(),
				new GregorianCalendar(),"lugarPropuestaTest",100,TipoRetorno.EntradasYPorcentaje,0,(DtProponente)dtNuevoUsuarioUno,null,
				null,cat,null);
		
		IPC.altaPropuesta(p);
		
		DtUsuario col = new DtColaborador("testNicknameDos", "testNombre", "testApellido", 
				"testCorreoDos", null, new GregorianCalendar(), null);
		DtColaboracion colaboracion = new DtColaboracion(p.getTitulo(),col.getNickname(),100,new GregorianCalendar(),TipoRetorno.EntradasGratis);
		IUC.agregarUsuario(col);
		IPC.generarColaboracion(colaboracion);
		
		IUC.listarPropuestasDeUnColaborador(col.getNickname());
	}
	
	@Test(expected = Test.None.class)
	public void masColaboradoresTest() {       
        IUC.getMasColaboradores();
	}
	
	@Test(expected = Test.None.class)
	public void masProponedoresTest() {       
        IUC.getMasProponedores();
	}
	
	@Before
	public void eliminarDatos() {
		IPC.borrarPropuestas();
		IUC.borrarUsuarios();
		//ICC.borrarCategorias();
	}
	
}
