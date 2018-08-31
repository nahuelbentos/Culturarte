package logica;
import java.util.ArrayList;

import datatype.DtCategoria;
import logica.exceptions.ExcepcionCategoriaNoExiste;
import logica.handler.CategoriaHandler;

public class CategoriaController implements ICategoriaController {

	@Override
	public DtCategoria[] listarCategorias() throws ExcepcionCategoriaNoExiste{
	    CategoriaHandler catHan = CategoriaHandler.getInstancia();
        Categoria[] cats = catHan.getCategorias();

        if (cats != null) {
            DtCategoria[] dtC = new DtCategoria[cats.length];
            Categoria categ;

            for (int i = 0; i < cats.length; i++) {
                categ = cats[i];
                dtC[i] = new DtCategoria(categ.getNombre(), categ.getDtSuperCategorias());
            }

            return dtC;
        } else
            throw new ExcepcionCategoriaNoExiste("No existen categorías registradas");
	}

	@Override
	public void agregarCategoria(DtCategoria dtC) {
		CategoriaHandler catHan = CategoriaHandler.getInstancia();
		if (!catHan.isMember(dtC.getNombre())) {
			// create categoria
			Categoria cat = new Categoria(dtC.getNombre());
			
			// leo la lista de padres recibida
			ArrayList<String> padres = dtC.getPadres();
			
			// busco los objetos relativos a los padres para agregarlos
			if (!(padres==null)) {
				for (int i = 0; i < padres.size(); i++) {
					Categoria padre = catHan.getCategoria(padres.get(i));
					if (!(padre==null))
						cat.addPadre(padre);
				}
			}
			catHan.addCategoria(cat);
		}
	}

}
