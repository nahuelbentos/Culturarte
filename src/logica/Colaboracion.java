package logica;

import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import datatype.DtColaboracion;
import datatype.DtPropuesta;
import datatype.DtPropuestaColaborada;
import datatype.TipoRetorno;

@Entity
@IdClass(ColaboracionID.class)
@Table(name="COLABORACIONES")
public class Colaboracion {
	
	@Id
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="COLABORADOR")
	private Colaborador colaborador;

	@Id
	@ManyToOne()
	@JoinColumn(name="PROPUESTA")
	private Propuesta propuestaColaborada;
	
	@Column(name="MONTO_APORTADO")
	private double monto;
	@Column(name="FECHA_APORTADO")
	private GregorianCalendar fechaAporte;
	@Column(name="TIPO_RETORNO")
	private TipoRetorno tipo;
	
	public Colaboracion() {
		super();
	}
	
	public Colaboracion(double d, GregorianCalendar fechaAporte, TipoRetorno tipo) {
		super();
		this.monto = d;
		this.fechaAporte = fechaAporte;
		this.tipo = tipo;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
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
	
	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public Propuesta getPropuestaColaborada() {
		return propuestaColaborada;
	}

	public void setPropuestaColaborada(Propuesta propuestaColaborada) {
		this.propuestaColaborada = propuestaColaborada;
	}
	
	public DtColaboracion getDataColaboracion() {
		return new DtColaboracion(propuestaColaborada.getTitulo(), colaborador.getNickname(),
				monto, fechaAporte, tipo);
		
//		return null;
	}
	
		
	public DtPropuestaColaborada getPropuestaFromColaboracion() {
		return propuestaColaborada.getInfoPropuestaColaborada();
	}
	
	public DtPropuesta obtPropuesta() {
		return new DtPropuesta(propuestaColaborada.getTitulo());
	}
}
