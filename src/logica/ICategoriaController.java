package logica;

import datatype.DtCategoria;

public interface ICategoriaController {

	public abstract DtCategoria[] listarCategorias();
	
	public abstract boolean agregarCategoria(DtCategoria dtCategoria);
}
