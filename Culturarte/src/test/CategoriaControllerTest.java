package test;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import datatype.DtCategoria;
import excepciones.CategoriaNoExisteException;
import excepciones.CategoriaYaExisteException;
import logica.Factory;
import logica.ICategoriaController;
import logica.IPropuestaController;
import logica.IUsuarioController;

public class CategoriaControllerTest {

	
	@Test(expected = Test.None.class)
	public void agregarCategoriaSinPadreCorrectamente() throws CategoriaYaExisteException, CategoriaNoExisteException {
		listarCategorias();
		Factory factory = Factory.getInstance();
		ICategoriaController ICC = factory.getICategoriaController();		
		String nombreCategoria = "testCategoria";
		ArrayList<DtCategoria> padres = new ArrayList<>();
		DtCategoria dtNuevaCategoria = new DtCategoria(nombreCategoria, padres, null);
		ICC.agregarCategoria(dtNuevaCategoria);
	}
	
	@Test(expected = CategoriaYaExisteException.class)
	public void agregarCategoriaSinPadreYaExisteLaCategoria() throws CategoriaYaExisteException, CategoriaNoExisteException {
		listarCategorias();
		Factory factory = Factory.getInstance();
		ICategoriaController ICC = factory.getICategoriaController();		
		String nombreCategoria = "testCategoria";
		ArrayList<DtCategoria> padres = new ArrayList<>();
		DtCategoria dtNuevaCategoria = new DtCategoria(nombreCategoria, padres, null);
		ICC.agregarCategoria(dtNuevaCategoria);
		ICC.agregarCategoria(dtNuevaCategoria);
	}
	
	@Test(expected = CategoriaNoExisteException.class)
	public void agregarCategoriaSinPadreNoExisteCategoriaNodo() throws CategoriaYaExisteException, CategoriaNoExisteException {
		Factory factory = Factory.getInstance();
		ICategoriaController ICC = factory.getICategoriaController();		
		String nombreCategoria = "testCategoria";
		ArrayList<DtCategoria> padres = new ArrayList<>();
		DtCategoria dtNuevaCategoria = new DtCategoria(nombreCategoria, padres, null);
		ICC.agregarCategoria(dtNuevaCategoria);
	}
	
	@Test(expected = Test.None.class)
	public void agregarCategoriaConPadreCorrectamente() throws CategoriaYaExisteException, CategoriaNoExisteException {
		listarCategorias();
		Factory factory = Factory.getInstance();
		ICategoriaController ICC = factory.getICategoriaController();		
		String nombreCategoriaUno = "testCategoriaUno";
		ArrayList<DtCategoria> padres = new ArrayList<>();
		DtCategoria dtNuevaCategoriaUno = new DtCategoria(nombreCategoriaUno, padres, null);
		ICC.agregarCategoria(dtNuevaCategoriaUno);
		String nombreCategoriaDos = "testCategoriaDos";
		padres.add(new DtCategoria(nombreCategoriaUno));
		DtCategoria dtNuevaCategoriaDos = new DtCategoria(nombreCategoriaDos, padres, null);
		ICC.agregarCategoria(dtNuevaCategoriaDos);
	}
	
	@Test(expected = CategoriaNoExisteException.class)
	public void agregarCategoriaConPadreNoExisteElPadre() throws CategoriaYaExisteException, CategoriaNoExisteException {
		listarCategorias();
		Factory factory = Factory.getInstance();
		ICategoriaController ICC = factory.getICategoriaController();		
		String nombreCategoria = "testCategoriaUno";
		ArrayList<DtCategoria> padres = new ArrayList<>();
		padres.add(new DtCategoria("testCategoriaNoExiste"));
		DtCategoria dtNuevaCategoria = new DtCategoria(nombreCategoria, padres, null);
		ICC.agregarCategoria(dtNuevaCategoria);
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
	
	@Test(expected = Test.None.class)
	public void listarCategoriasTest() throws CategoriaYaExisteException, CategoriaNoExisteException {
		listarCategorias();
		Factory factory = Factory.getInstance();
		ICategoriaController ICC = factory.getICategoriaController();		
		String nombreCategoria = "testCategoria";
		ArrayList<DtCategoria> padres = new ArrayList<>();
		DtCategoria dtNuevaCategoria = new DtCategoria(nombreCategoria, padres, null);
		ICC.agregarCategoria(dtNuevaCategoria);
		listarCategorias();
	}
	
	@Test(expected = Test.None.class)
	public void listarCategoriasJTree() throws CategoriaYaExisteException, CategoriaNoExisteException {
		listarCategorias();
		Factory factory = Factory.getInstance();
		ICategoriaController ICC = factory.getICategoriaController();		
		String nombreCategoria = "testCategoria";
		ArrayList<DtCategoria> padres = new ArrayList<>();
		DtCategoria dtNuevaCategoria = new DtCategoria(nombreCategoria, padres, null);
		ICC.agregarCategoria(dtNuevaCategoria);
		ICC.listarCategoriasJTree();
	}
	
	public void listarCategorias() {
		Factory factory = Factory.getInstance();
        ICategoriaController ICC = factory.getICategoriaController();
		ICC.listarCategorias();
	}
	
}
