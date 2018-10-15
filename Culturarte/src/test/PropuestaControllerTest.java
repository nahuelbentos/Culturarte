package test;

import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import datatype.DtCategoria;
import datatype.DtProponente;
import datatype.DtPropuesta;
import datatype.DtPropuestaMinificado;
import datatype.EstadoPropuesta;
import datatype.TipoRetorno;
import excepciones.CategoriaNoExisteException;
import excepciones.CategoriaYaExisteException;
import excepciones.ProponenteNoExisteException;
import excepciones.PropuestaNoExisteException;
import excepciones.PropuestaRepetidaException;
import excepciones.UsuarioYaExisteElEmailException;
import excepciones.UsuarioYaExisteElUsuarioException;
import logica.Factory;
import logica.IPropuestaController;
import logica.IUsuarioController;

public class PropuestaControllerTest {
	
	private static Factory factory;
	private static IUsuarioController iUsuCont;
	private static IPropuestaController iPropCont;
	
	private static DtCategoria cat;
	private static DtProponente propACargo;
	private static DtPropuesta p;
	
	// Metodo que se ejecuta antes de todos los before
	@BeforeClass
	public static void antesDeTodo() throws UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException {
		factory = Factory.getInstance();
		iUsuCont = factory.getIUsuarioController();
		iPropCont = factory.getIPropuestaController();
		
		iPropCont.borrarPropuestas();
		iUsuCont.borrarUsuarios();
		
		cat = new DtCategoria("Categorias");
		propACargo = new DtProponente("testNicknameTres", "testNombre", "testApellido", 
				"testCorreoTres", null, new GregorianCalendar(), null, "testDireccion", "TestBiografia", 
				"testSitioWeb");
		iUsuCont.agregarUsuario(propACargo);
		
		p = new DtPropuesta("tituloPropuestaTest","dscPropuestaTest",null,40000,new GregorianCalendar(),
				new GregorianCalendar(),"lugarPropuestaTest",100,TipoRetorno.EntradasYPorcentaje,0,propACargo,null,
				null,cat,null);
	}
	
	@Before
	public void agregoPropuestaTest() throws PropuestaRepetidaException, ProponenteNoExisteException, CategoriaNoExisteException {
		iPropCont.altaPropuesta(p);
	}
	
	@After
	public void borroPropuestas() {
		iPropCont.borrarPropuestas();
	}
	
	@Test(expected = Test.None.class)
	public void aprobarPropuestaTest() throws PropuestaNoExisteException{
		iPropCont.evaluarPropuesta(p.getTitulo(),EstadoPropuesta.publicada);
	}
	
	@Test(expected = Test.None.class)
	public void noAprobadaPropuestaTest() throws PropuestaNoExisteException{
		iPropCont.evaluarPropuesta(p.getTitulo(),EstadoPropuesta.cancelada);
	}
	
	@Test(expected = Test.None.class)
	public void evaluarPropuestaInexistenteTest() throws PropuestaNoExisteException{
		iPropCont.evaluarPropuesta("no existe",EstadoPropuesta.cancelada);
	}
	

	@Test(expected = Test.None.class) 
	public void seleccionarPropuestaTest(){
		iPropCont.seleccionarPropuesta(p.getTitulo());
		} 
	

	@Test(expected = PropuestaNoExisteException.class)
	public void seleccionarPropuestaInexistenteTest() throws PropuestaNoExisteException{
		iPropCont.seleccionarPropuesta("NoExisteEstaPropuesta");
	} 
	
	public void listarPropuestasExistentesTest() throws PropuestaNoExisteException{
	
		DtPropuestaMinificado[] dtpm1 = iPropCont.listarPropuestas();
		DtPropuestaMinificado[] dtpm2 = iPropCont.listarPropuestasActivas();
		DtPropuesta[] dtp = iPropCont.listarPropuestasExistentes();
		
	}

	@Test(expected = PropuestaNoExisteException.class)
	public void listarPropuestasNoExistentesTest()throws PropuestaNoExisteException{
		borroPropuestas();
		DtPropuestaMinificado[] dtpm1 = iPropCont.listarPropuestas();
		DtPropuestaMinificado[] dtpm2 = iPropCont.listarPropuestasActivas();
		DtPropuesta[] dtp = iPropCont.listarPropuestasExistentes();
	}
	
	public void modificarPropuestaTest(){} 
	
	
	public void consultarPropuestaTest(){} 
	
	public void listarPropuestasPorEstadoTest(){} 
	
	public void generarColaboracionTest() {}
	
	 
	
	public void agregarFavoritaTest(){} 

	public void extenderFinanciacionTest(){} 

	public void cancelarPropuestaTest(){}
	
	public void getPropuestasPopularesTest(){} 
	
}
