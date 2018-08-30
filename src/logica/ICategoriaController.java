package logica;

import datatype.DtCategoria;
import logica.exceptions.ExcepcionCategoriaNoExiste;

public interface ICategoriaController {

	public abstract DtCategoria[] listarCategorias() throws ExcepcionCategoriaNoExiste;
	
	public abstract void agregarCategoria(DtCategoria dtCategoria);
}
