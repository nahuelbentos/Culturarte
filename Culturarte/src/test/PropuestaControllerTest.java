package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import datatype.DtCategoria;
import datatype.DtColaboracion;
import datatype.DtColaborador;
import datatype.DtDatosPropuesta;
import datatype.DtEstado;
import datatype.DtProponente;
import datatype.DtPropuesta;
import datatype.DtPropuestaMinificado;
import datatype.DtUsuario;
import datatype.EstadoPropuesta;
import datatype.TipoRetorno;
import excepciones.CategoriaNoExisteException;
import excepciones.ColaboracionExistenteException;
import excepciones.ColaboradorNoExisteException;
import excepciones.ProponenteNoExisteException;
import excepciones.PropuestaNoExisteException;
import excepciones.PropuestaRepetidaException;
import excepciones.UsuarioSinLoguearseException;
import excepciones.UsuarioYaExisteElEmailException;
import excepciones.UsuarioYaExisteElUsuarioException;
import logica.Factory;
import logica.ICategoriaController;
import logica.IPropuestaController;
import logica.IUsuarioController;
import logica.Propuesta;
import persistencia.ConexionPostgresHibernate;

public class PropuestaControllerTest {
	
	private static ConexionPostgresHibernate cph;
	private static EntityManagerFactory emf;
	private static EntityManager em;
	
	private static Factory factory;
	private static IUsuarioController iUsuCont;
	private static IPropuestaController iPropCont;
	private static ICategoriaController iCatCont;
	
	private static DtCategoria cat;
	private static DtProponente propACargo;
	private static DtColaborador col;
	private static DtColaborador col2;
	private static DtPropuesta p;
	private static DtPropuesta cancelada;
	
	// Metodo que se ejecuta antes de todos los before
	@BeforeClass
	public static void antesDeTodo() throws UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException {
		factory = Factory.getInstance();
		iUsuCont = factory.getIUsuarioController();
		iPropCont = factory.getIPropuestaController();
		iCatCont = factory.getICategoriaController();
		
		iPropCont.borrarPropuestas();
		iUsuCont.borrarUsuarios();
		iCatCont.listarCategorias();
		
		cat = new DtCategoria("Categorias");
		propACargo = new DtProponente("testNicknameUno", "testNombre", "testApellido", 
				"testCorreoUno", null, new GregorianCalendar(), null, "testDireccion", "TestBiografia", 
				"testSitioWeb");
		col = new DtColaborador("testNicknameDos", "testNombre", "testApellido", 
				"testCorreoDos", null, new GregorianCalendar(), null);
		col2 = new DtColaborador("colaborador2", "testNombre", "testApellido", 
				"testCorreoDosColaborador", null, new GregorianCalendar(), null);
		iUsuCont.agregarUsuario(propACargo);
		iUsuCont.agregarUsuario(col);
		iUsuCont.agregarUsuario(col2);
		
	}
	
	@Before
	public void agregoPropuestaTest() throws PropuestaRepetidaException, ProponenteNoExisteException, CategoriaNoExisteException {
		p = new DtPropuesta("tituloPropuestaTest","dscPropuestaTest",null,40000,new GregorianCalendar(),
				new GregorianCalendar(),"lugarPropuestaTest",100,TipoRetorno.EntradasYPorcentaje,0,propACargo,null,
				null,cat,null);
		cancelada = new DtPropuesta("canceladaPropuestaTest","dscPropuestaTest",null,40000,new GregorianCalendar(),
				new GregorianCalendar(),"lugarPropuestaTest",100,TipoRetorno.EntradasYPorcentaje,0,propACargo,null,
				null,cat,null);
		iPropCont.altaPropuesta(p);
		iPropCont.altaPropuesta(cancelada);
	}
	
	@After
	public void borroPropuestas() {
		iPropCont.borrarPropuestas();
	}
	
	@Test(expected = ProponenteNoExisteException.class)
	public void agregoPropuestaSinProponenteTest() throws PropuestaRepetidaException, ProponenteNoExisteException, CategoriaNoExisteException {
		p = new DtPropuesta("tituloPropuestaTest2","dscPropuestaTest",null,40000,new GregorianCalendar(),
				new GregorianCalendar(),"lugarPropuestaTest",100,TipoRetorno.EntradasYPorcentaje,0, new DtProponente("noExiste", null, null, null, null, null, null, null, null, null),null,
				null,cat,null);
		iPropCont.altaPropuesta(p);
	}

	@Test(expected = CategoriaNoExisteException.class)
	public void agregoPropuestaSinCategoriaTest() throws PropuestaRepetidaException, ProponenteNoExisteException, CategoriaNoExisteException {
		p = new DtPropuesta("tituloPropuestaTest3","dscPropuestaTest",null,40000,new GregorianCalendar(),
				new GregorianCalendar(),"lugarPropuestaTest",100,TipoRetorno.EntradasYPorcentaje,0,propACargo,null,
				null, new DtCategoria("CatNE"),null);
		iPropCont.altaPropuesta(p);
	}
	
	@Test(expected = PropuestaRepetidaException.class)
	public void agregoPropuestaRepetidaTest() throws PropuestaRepetidaException, ProponenteNoExisteException, CategoriaNoExisteException {
		p = new DtPropuesta("tituloPropuestaTest","dscPropuestaTest",null,40000,new GregorianCalendar(),
				new GregorianCalendar(),"lugarPropuestaTest",100,TipoRetorno.EntradasYPorcentaje,0,propACargo,null,
				null, cat,null);
		iPropCont.altaPropuesta(p);
	}
	
	@Test(expected = Test.None.class)
	public void aprobarPropuestaTest() throws PropuestaNoExisteException{
		iPropCont.evaluarPropuesta(p.getTitulo(),EstadoPropuesta.publicada);
	}
	
	@Test(expected = Test.None.class)
	public void noAprobadaPropuestaTest() throws PropuestaNoExisteException{
		iPropCont.evaluarPropuesta(cancelada.getTitulo(),EstadoPropuesta.cancelada);
	}
	
	@Test(expected = PropuestaNoExisteException.class)
	public void evaluarPropuestaInexistenteTest() throws PropuestaNoExisteException{
		iPropCont.evaluarPropuesta("no existe",EstadoPropuesta.cancelada);
	}
	
	@Test(expected = Test.None.class)
	public void primerColaboracionAPropuestaTest() throws ColaboradorNoExisteException, PropuestaNoExisteException, ColaboracionExistenteException {
		DtColaboracion colaboracion = new DtColaboracion(p.getTitulo(),col.getNickname(),100,new GregorianCalendar(),TipoRetorno.EntradasGratis);
		iPropCont.generarColaboracion(colaboracion);
		
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		Propuesta paux = em.find(Propuesta.class, colaboracion.getTituloPropuesta());
		assertEquals(EstadoPropuesta.enFinanciacion, paux.getEstadoActual());
		em.close();
	}
	
	@Test(expected = PropuestaNoExisteException.class)
	public void colaboracionNoExisteElColaborador() throws ColaboradorNoExisteException, PropuestaNoExisteException, ColaboracionExistenteException {
		DtColaboracion colaboracion = new DtColaboracion("noExisteLaPropuesta",col.getNickname(),100,new GregorianCalendar(),TipoRetorno.EntradasGratis);
		iPropCont.generarColaboracion(colaboracion);
	}
	
	@Test(expected = ColaboradorNoExisteException.class)
	public void colaboracionNoExisteLaPropuesta() throws ColaboradorNoExisteException, PropuestaNoExisteException, ColaboracionExistenteException {
		DtColaboracion colaboracion = new DtColaboracion(p.getTitulo(), "colaboradorNoExiste",100,new GregorianCalendar(),TipoRetorno.EntradasGratis);
		iPropCont.generarColaboracion(colaboracion);
	}
	
	@Test(expected = ColaboracionExistenteException.class)
	public void colaboracionExisteTest() throws ColaboradorNoExisteException, PropuestaNoExisteException, ColaboracionExistenteException {
		DtColaboracion colaboracion = new DtColaboracion(p.getTitulo(),col.getNickname(),100,new GregorianCalendar(),TipoRetorno.EntradasGratis);
		iPropCont.generarColaboracion(colaboracion);
		
		colaboracion = new DtColaboracion(p.getTitulo(),col.getNickname(),p.getMontoNecesario(),new GregorianCalendar(),TipoRetorno.EntradasGratis);
		iPropCont.generarColaboracion(colaboracion);
		
	}
	
	@Test(expected = Test.None.class)
	public void colaboracionSinCambiarEstadoPropTest() throws ColaboradorNoExisteException, PropuestaNoExisteException, ColaboracionExistenteException {
		DtColaboracion colaboracion = new DtColaboracion(p.getTitulo(),col2.getNickname(),100,new GregorianCalendar(),TipoRetorno.EntradasGratis);
		iPropCont.generarColaboracion(colaboracion);
		
		colaboracion = new DtColaboracion(p.getTitulo(),col.getNickname(),120,new GregorianCalendar(),TipoRetorno.EntradasGratis);
		iPropCont.generarColaboracion(colaboracion);
		
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		Propuesta paux = em.find(Propuesta.class, colaboracion.getTituloPropuesta());
		assertEquals(EstadoPropuesta.enFinanciacion, paux.getEstadoActual());
		em.close();
	}
	
	@Test(expected = Test.None.class)
	public void colaboracionAPropuestaPorFinanciarTest() throws ColaboradorNoExisteException, PropuestaNoExisteException, ColaboracionExistenteException {
		DtColaboracion colaboracion = new DtColaboracion(p.getTitulo(),col2.getNickname(),100,new GregorianCalendar(),TipoRetorno.EntradasGratis);
		iPropCont.generarColaboracion(colaboracion);
		
		colaboracion = new DtColaboracion(p.getTitulo(),col.getNickname(),p.getMontoNecesario(),new GregorianCalendar(),TipoRetorno.EntradasGratis);
		iPropCont.generarColaboracion(colaboracion);
		
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		Propuesta paux = em.find(Propuesta.class, colaboracion.getTituloPropuesta());
		assertEquals(EstadoPropuesta.financiada, paux.getEstadoActual());
		em.close();
	}

	@Test(expected = Test.None.class) 
	public void seleccionarPropuestaTest(){
		iPropCont.seleccionarPropuesta(p.getTitulo());
	}

	@Test(expected = Test.None.class)
	public void seleccionarPropuestaInexistenteTest() {
		assertEquals(null, iPropCont.seleccionarPropuesta("NoExisteEstaPropuesta"));
	} 
	
@Test(expected = Test.None.class)
	public void listarPropuestasExistentesTest() throws PropuestaNoExisteException, PropuestaRepetidaException, ProponenteNoExisteException, CategoriaNoExisteException{

		p = new DtPropuesta("tituloPropuestaTest","dscPropuestaTest",null,40000,new GregorianCalendar(),
				new GregorianCalendar(),"lugarPropuestaTest",100,TipoRetorno.EntradasYPorcentaje,0,propACargo,null,
				null, cat,null);
		iPropCont.altaPropuesta(p);

		DtPropuesta p2 = new DtPropuesta("tituloPropuestaTest2","dscPropuestaTest",null,40000,new GregorianCalendar(),
				new GregorianCalendar(),"lugarPropuestaTest",100,TipoRetorno.EntradasYPorcentaje,0,propACargo,null,
				null, cat,null);
		iPropCont.altaPropuesta(p2);
		iPropCont.listarPropuestas();
		iPropCont.listarPropuestasActivas();
		iPropCont.listarPropuestasExistentes();
	}

	@Test(expected = PropuestaNoExisteException.class)
	public void listarPropuestasNoExistentesTest()throws PropuestaNoExisteException {
		borroPropuestas();
		iPropCont.listarPropuestas();
		iPropCont.listarPropuestasActivas();
		iPropCont.listarPropuestasExistentes();
	}
	
	@Test(expected = Test.None.class) 
	public void modificarPropuestaExistenteTest(){
		byte[] image = null;
		char[] password = null;
		GregorianCalendar fechaPublicacion = new GregorianCalendar(), fechaEspecatulo= new GregorianCalendar(),fechaNacimiento = new GregorianCalendar();
		DtProponente proponenteACargo = new DtProponente("NuevoNickname", "NuevoNombre", "Nuevoapellido", "NuevoEmail", password, 
				fechaNacimiento, image, "NuevoDireccion", "NuevoBiografia", "NuevoSitioWeb");
		
		ArrayList<DtColaboracion> colaboraciones= new ArrayList<DtColaboracion>();
		DtCategoria categoria= new DtCategoria("Teatro");
		ArrayList<DtEstado> estadoHistorial= new ArrayList<DtEstado>();
		EstadoPropuesta estadoActual= EstadoPropuesta.financiada;
		DtPropuesta dtp = new DtPropuesta("tituloPropuestaTest", "nuevaDescripcion", image,100000 , fechaPublicacion, fechaEspecatulo,
				"nuevoLugar", 100, TipoRetorno.EntradasGratis, 0, proponenteACargo, estadoActual, estadoHistorial, categoria, colaboraciones);
		iPropCont.modificarPropuesta(dtp);
	}
	
	@Test(expected = Test.None.class)
	public void modificarPropuestaNoExistenteTest(){
		byte[] image = null;
		char[] password = null;
		GregorianCalendar fechaPublicacion = new GregorianCalendar(), fechaEspecatulo= new GregorianCalendar(),fechaNacimiento = new GregorianCalendar();
		DtProponente proponenteACargo = new DtProponente("NuevoNickname", "NuevoNombre", "Nuevoapellido", "NuevoEmail", password, 
				fechaNacimiento, image, "NuevoDireccion", "NuevoBiografia", "NuevoSitioWeb");
		
		ArrayList<DtColaboracion> colaboraciones= new ArrayList<DtColaboracion>();
		DtCategoria categoria= new DtCategoria("Teatro");
		ArrayList<DtEstado> estadoHistorial= new ArrayList<DtEstado>();
		EstadoPropuesta estadoActual= EstadoPropuesta.financiada;
		DtPropuesta dtp = new DtPropuesta("nuevoTitulo", "nuevaDescripcion", image,100000 , fechaPublicacion, fechaEspecatulo,
				"nuevoLugar", 100, TipoRetorno.EntradasGratis, 0, proponenteACargo, estadoActual, estadoHistorial, categoria, colaboraciones);
		iPropCont.modificarPropuesta(dtp);
	}

	@Test(expected = Test.None.class)
	public void consultarPropuestaExistenteTest(){
		iPropCont.consultarPropuesta("tituloPropuestaTest");
	} 

	@Test(expected = Test.None.class)
	public void consultarPropuestaNoExistenteTest(){
		DtDatosPropuesta propuesta = iPropCont.consultarPropuesta("nuevoTitulo");
		assertEquals(null, propuesta.getTitulo());
	}
	
	@Test(expected = UsuarioSinLoguearseException.class)
	public void agregarPropuestaFavoritaSinLoguearse() throws UsuarioSinLoguearseException{
		iPropCont.agregarFavorita(p.getTitulo(), null);
	}
	
	@Test(expected = Test.None.class)
	public void agregarPropuestaFavorita() throws UsuarioSinLoguearseException, UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException{
		DtUsuario dtNuevoUsuario = new DtProponente("testNicknameTres", "testNombre", "testApellido", 
				"testCorreoTres", null, new GregorianCalendar(), null, "testDireccion", "TestBiografia", 
				"testSitioWeb");
		iUsuCont.agregarUsuario(dtNuevoUsuario);
		iPropCont.agregarFavorita(p.getTitulo(), dtNuevoUsuario);
	} 
	
	@Test(expected = Test.None.class)
	public void agregarPropuestaFavoritaUsuarioYaExisteFavorita() throws UsuarioSinLoguearseException, UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException{
		DtUsuario dtNuevoUsuario = new DtProponente("testNicknameCuatro", "testNombre", "testApellido", 
				"testCorreoCuatro", null, new GregorianCalendar(), null, "testDireccion", "TestBiografia", 
				"testSitioWeb");
		iUsuCont.agregarUsuario(dtNuevoUsuario);
		iPropCont.agregarFavorita(p.getTitulo(), dtNuevoUsuario);
		iPropCont.agregarFavorita(p.getTitulo(), dtNuevoUsuario);
	}
	
	@Test(expected = Test.None.class)
	public void agregarPropuestaFavoritaLaPropuestaEsNull() throws UsuarioSinLoguearseException, UsuarioYaExisteElUsuarioException, UsuarioYaExisteElEmailException{
		DtUsuario dtNuevoUsuario = new DtProponente("testNicknameCinco", "testNombre", "testApellido", 
				"testCorreoCinco", null, new GregorianCalendar(), null, "testDireccion", "TestBiografia", 
				"testSitioWeb");
		iUsuCont.agregarUsuario(dtNuevoUsuario);
		iPropCont.agregarFavorita("testPropuestaNoExiste", dtNuevoUsuario);
	} 

	@Test(expected = Test.None.class)
	public void extenderFinanciacionTest() throws PropuestaNoExisteException {
		iPropCont.extenderFinanciacion(p.getTitulo());
	}
	
	@Test(expected = PropuestaNoExisteException.class)
	public void extenderFinanciacionPropuestaInexistenteTest() throws PropuestaNoExisteException {
		iPropCont.extenderFinanciacion("testPropuestaNoExiste");
	}
	
	@Test(expected = Test.None.class)
	public void cancelarPropuestaExistenteTest() throws PropuestaNoExisteException{
		iPropCont.cancelarPropuesta(p.getTitulo());
		EstadoPropuesta estado = p.getEstadoActual();
		assertEquals(EstadoPropuesta.cancelada, estado);
	}

	@Test(expected = PropuestaNoExisteException.class)
	public void cancelarPropuestaNoExistenteTest() throws PropuestaNoExisteException{
		iPropCont.cancelarPropuesta("testPropuestaNoExiste");		
	}

	
	@Test(expected = Test.None.class)
	public void getPropuestasPopularesTest(){
		iPropCont.getPropuestasPopulares();
	} 
	
	@Test(expected = Test.None.class)
	public void borrarEstadosPropuestaTest(){
		iPropCont.borrarEstadosPropuestas();
	} 
	
	@Test(expected = Test.None.class)
	public void listarColaboracionesTestNull() {
		DtColaboracion[] dtC = iPropCont.listarColaboraciones("testPropuestaNoExiste");
		assertEquals(null, dtC);
	}
	
	@Test
	public void listarColaboracionesTestSuccess() {
		DtColaboracion[] dtC = iPropCont.listarColaboraciones(p.getTitulo());
	}
	
	@Test(expected = PropuestaNoExisteException.class)
	public void listarPropuestasPorEstadoTestFail() throws PropuestaNoExisteException {
		borroPropuestas();
		DtPropuestaMinificado[] dtPM = iPropCont.listarPropuestasPorEstado(EstadoPropuesta.ingresada);
	}
	
	@Test
	public void listarPropuestasPorEstadoTestSuccess() throws PropuestaNoExisteException {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		Propuesta paux = em.find(Propuesta.class, p.getTitulo());
		em.close();
		DtPropuestaMinificado[] dtPM = iPropCont.listarPropuestasPorEstado(paux.getEstadoActual());
	}
	
	@Test(expected = PropuestaNoExisteException.class)
	public void listadoPropuestasIngresadasTestFail() throws PropuestaNoExisteException {
		borroPropuestas();
		DtPropuestaMinificado[] dtPM = iPropCont.listadoPropuestasIngresadas();
	}
	
	@Test
	public void listadoPropuestasIngresadasTestSuccess() throws PropuestaNoExisteException {
		DtPropuestaMinificado[] dtPM = iPropCont.listadoPropuestasIngresadas();
	}
	
	@Test(expected = PropuestaNoExisteException.class)
	public void listarPropuestasProponentePorEstadoTestFail() throws PropuestaNoExisteException {
		DtPropuestaMinificado[] dtPM = iPropCont.listarPropuestasProponentePorEstado("", EstadoPropuesta.ingresada);
	}
	
	@Test
	public void listarPropuestasProponentePorEstadoTestSuccess() throws PropuestaNoExisteException {
		cph = ConexionPostgresHibernate.getInstancia();
		emf = cph.getEntityManager();
		em = emf.createEntityManager();
		Propuesta paux = em.find(Propuesta.class, p.getTitulo());
		em.close();
		
		DtPropuestaMinificado[] dtPM = iPropCont.listarPropuestasProponentePorEstado(paux.getProponenteACargo().getNickname(), paux.getEstadoActual());
	}

	@Test(expected = Test.None.class)
	public void listarPropuestaPorCategoriaTest() {
		ArrayList<DtPropuesta> propuestas = iPropCont.listarPropuestasPorCategoria("Teatro");
		assertTrue(propuestas.isEmpty());
	}
	
	@Test(expected = ParseException.class)
	public void setearEstadosPropuestaException() throws ParseException {
		iPropCont.setearEstadosPropuests("enFinanciacion", p.getTitulo(), new GregorianCalendar().toString());
	}
	
	@Test(expected = Test.None.class)
	public void setearEstadosPropuesta() throws ParseException {
		iPropCont.setearEstadosPropuests("enFinanciacion", p.getTitulo(), "2017-05-15 15:30:00.000");
	}
}
