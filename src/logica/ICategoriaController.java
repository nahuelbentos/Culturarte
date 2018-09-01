package logica;

import datatype.DtCategoria;
import excepciones.CategoriaYaExisteException;
import excepciones.ExcepcionCategoriaNoExiste;

public interface ICategoriaController {

	public abstract DtCategoria[] listarCategorias() throws ExcepcionCategoriaNoExiste;
	
	public abstract void agregarCategoria(DtCategoria dtCategoria) throws CategoriaYaExisteException;
}
