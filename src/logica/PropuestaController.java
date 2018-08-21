package logica;

import datatype.DtColaboracion;
import datatype.DtPropuesta;
import datatype.EstadoPropuesta;

public class PropuestaController implements IPropuestaController {

	@Override
	public boolean altaPropuesta(DtPropuesta dtPropuesta) {
		// TODO Auto-generated method stub
		return false;
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
