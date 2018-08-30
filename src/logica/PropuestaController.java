package logica;

import datatype.DtColaboracion;
import datatype.DtPropuesta;
import datatype.EstadoPropuesta;
import logica.exceptions.CategoriaNoExisteException;
import logica.exceptions.ProponenteNoExisteException;
import logica.exceptions.PropuestaNoExisteException;
import logica.exceptions.PropuestaRepetidaException;
import logica.handler.CategoriaHandler;
import logica.handler.ProponenteHandler;
import logica.handler.PropuestaHandler;

public class PropuestaController implements IPropuestaController {

	@Override
	public void altaPropuesta(DtPropuesta dtPropuesta) throws PropuestaRepetidaException, ProponenteNoExisteException, CategoriaNoExisteException{
		PropuestaHandler propHan = PropuestaHandler.getInstance();
		Propuesta prop = propHan.obtenerPropuesta(dtPropuesta.getTitulo());
		
		//------------------------- Seteo los PseudoAtributos -------------------------
		// Proponente a cargo
		ProponenteHandler proponenteH = ProponenteHandler.getInstance();
		String nicknameProponente = dtPropuesta.getProponenteACargo().getNickname();
		Proponente proponente = proponenteH.obtenerProponente(nicknameProponente);
		
		if (proponente == null)
			throw new ProponenteNoExisteException("No existe el proponente " + nicknameProponente);
		
		// Categoria
		CategoriaHandler catHan = CategoriaHandler.getInstancia();
		String nombreCategoria = dtPropuesta.getCategoria().getNombre();
		Categoria cat = catHan.getCategoria(nombreCategoria);
		
		if (cat == null)
			throw new CategoriaNoExisteException("No existe la categoría " + nombreCategoria);
		
		
		// Cargo la categoría
		if (prop != null)
			throw new PropuestaRepetidaException("Ya existe la propuesta" + dtPropuesta.getTitulo());
		
		prop = new Propuesta(dtPropuesta);
		prop.setProponenteACargo(proponente);
		prop.setCategoria(cat);
		propHan.agregarPropuesta(prop);
		
	}

	@Override
	public DtPropuesta[] listarPropuestas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DtPropuesta seleccionarPropuesta(String titulo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modificarPropuesta(DtPropuesta dtPropuesta) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DtColaboracion[] listarColaboraciones() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DtPropuesta[] listarPropuestasExistentes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DtPropuesta[] listarPropuestasPorEstado(EstadoPropuesta estadoPropuesta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean altaColaboracion(DtColaboracion dtColaboracion) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminarColaboraciones(String titulo, String nickname) {
		// TODO Auto-generated method stub
		return false;
	}

}
