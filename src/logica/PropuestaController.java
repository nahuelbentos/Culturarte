package logica;

import java.util.ArrayList;
import java.util.Map;

import datatype.DtColaboracion;
import datatype.DtColaborador;
import datatype.DtDatosPropuesta;
import datatype.DtPropuesta;
import datatype.EstadoPropuesta;
import logica.handler.ColaboracionHandler;
import logica.handler.PropuestaHandler;

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
	public ArrayList<DtPropuesta> listarPropuestasExistentes() {
		PropuestaHandler mpropue = PropuestaHandler.getInstance();
		Map<String, Propuesta> props = mpropue.getPropuestas();
		
		ArrayList<DtPropuesta> DtPropuestas = new ArrayList<DtPropuesta>(); 
		for(Propuesta p : props.values()) {
			DtPropuestas.add(p.getDtPropuesta());
		}

		return DtPropuestas;
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

	@Override
	public DtDatosPropuesta consultarPropuesta(String titulo) {
		
		PropuestaHandler mpropue = PropuestaHandler.getInstance();
		Propuesta p = mpropue.obtenerPropuesta(titulo); //1
		
		DtDatosPropuesta datapro = p.getDtDatosPropuesta(); //2
		
		ColaboracionHandler mcolab = ColaboracionHandler.getInstance();
		Colaboracion[] colColab = mcolab.getColaboraciones();
		ArrayList<DtColaborador> colaboradores = new ArrayList<DtColaborador>();
		float montoTotal=0;
		for (Colaboracion col : colColab) { //3
			if(col.tieneProp(titulo)) { //4 
				montoTotal += col.getMonto(); //5.1 
				colaboradores.add(col.getDataColaboracion().getColaborador()); //5.2				
			}
		}
		
		DtDatosPropuesta dtp = new DtDatosPropuesta(datapro.getTitulo(), datapro.getDescripcion(), datapro.getImagen(),
				datapro.getMontoNecesario(), datapro.getFechaPublicacion(), datapro.getFechaEspecatulo(), datapro.getLugar(),
				datapro.getPrecioEntrada(), datapro.getTipo(), montoTotal, colaboradores);
		return dtp;
	}

}
