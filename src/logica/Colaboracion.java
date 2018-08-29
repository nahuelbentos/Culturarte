package logica;

import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import datatype.DtColaboracion;
import datatype.DtPropuestaColaborada;
import datatype.TipoRetorno;

@Entity
@IdClass(ColaboracionID.class)
@Table(name="COLABORACIONES")
public class Colaboracion {
	
	@Id
	@ManyToOne
	@JoinColumn(name="ID_COLABORADOR")
	private Colaborador colaborador;

	@Id
	@ManyToOne
	@JoinColumn(name="ID_PROPUESTA")
	private Propuesta propuestaColaborada;
	
	@Column(name="MONTO_APORTADO")
	private float monto;
	@Column(name="FECHA_APORTADO")
	private GregorianCalendar fechaAporte;
	@Column(name="TIPO_RETORNO")
	private TipoRetorno tipo;
	
	
	public Colaboracion(float monto, GregorianCalendar fechaAporte, TipoRetorno tipo) {
		super();
		this.monto = monto;
		this.fechaAporte = fechaAporte;
		this.tipo = tipo;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public GregorianCalendar getFechaAporte() {
		return fechaAporte;
	}

	public void setFechaAporte(GregorianCalendar fechaAporte) {
		this.fechaAporte = fechaAporte;
	}

	public TipoRetorno getTipo() {
		return tipo;
	}

	public void setTipo(TipoRetorno tipo) {
		this.tipo = tipo;
	}
	
	public boolean tieneProp(String titulo) {
		return propuestaColaborada.getTitulo().equals(titulo);
	}
	
	public boolean tieneColaborador(String nickname) {
		return colaborador.getNickname().equals(nickname);	
	}
	
	public DtColaboracion getDataColaboracion() {
		return new DtColaboracion(propuestaColaborada.getDtPropuesta(), monto,
				colaborador.getDtColaborador(), fechaAporte, tipo);
	}
	
	public DtPropuestaColaborada getPropuestaFromColaboracion() {
		return propuestaColaborada.getInfoPropuestaColaborada();
	}
	
}
