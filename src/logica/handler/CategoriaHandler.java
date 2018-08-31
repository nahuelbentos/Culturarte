package logica.handler;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import logica.Categoria;

public class CategoriaHandler {
	private Map<String, Categoria> categorias;
	private static CategoriaHandler instancia = null;

	private CategoriaHandler() {
		categorias = new HashMap<String, Categoria>();
	}

	public static CategoriaHandler getInstancia() {
		if (instancia == null)
			instancia = new CategoriaHandler();
		return instancia;
	}

	public Categoria[] getCategorias() {
		if (categorias==null)
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
		if (!(categorias==null))
			return categorias.containsKey(nombre);
		else
			return false;
	}

	public Categoria getCategoria(String nombre) {
		return ((Categoria) categorias.get(nombre));
	}

	public void addCategoria(Categoria cat) {
//		if (!(cat==null)) {
			String nombre = cat.getNombre();
			categorias.put(nombre, cat);
//		}
	}
}
