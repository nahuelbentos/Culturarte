package logica;

import datatype.DtCategoria;
import excepciones.CategoriaNoExisteException;
import excepciones.CategoriaYaExisteException;

public interface ICategoriaController {

	public abstract DtCategoria[] listarCategorias();
	
	public abstract void agregarCategoria(DtCategoria dtCategoria) throws CategoriaYaExisteException, CategoriaNoExisteException;

	public abstract DtCategoria[] listarCategoriasJTree();
	
	public abstract void borrarCategorias();
}
