package logica.handler;

import java.util.Collection;
import java.util.Map;
import logica.Categoria;

public class CategoriaHandler {
	private Map<String, Categoria> categorias;
	private static CategoriaHandler instancia = null;

	private CategoriaHandler() {}

	public static CategoriaHandler getInstancia() {
		if (instancia == null)
			instancia = new CategoriaHandler();
		return instancia;
	}

	public Categoria[] getCategorias() {
		if (categorias.isEmpty())
            return null;
        else {
            Collection<Categoria> cats = categorias.values();
            Object[] o = cats.toArray();
            Categoria[] misCategorias = new Categoria[o.length];
            for (int i = 0; i < o.length; i++) {
                misCategorias[i] = (Categoria) o[i];
            }

            return misCategorias;
        }
	}

	public boolean isMember(String nombre) {
		return ((Categoria) categorias.get(nombre)) == null;
	}

	public Categoria getCategoria(String nombre) {
		return ((Categoria) categorias.get(nombre));
	}

	public void addCategoria(Categoria cat) {
		categorias.put(cat.getNombre(), cat);
	}
}
