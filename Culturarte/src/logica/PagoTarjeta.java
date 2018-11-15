package logica;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import datatype.DtPagoTarjeta;
import datatype.TipoTarjeta;

@Entity
@DiscriminatorValue("T")
public class PagoTarjeta extends Pago {
	@Column(name="PT_TIPOTARJETA")
	@Enumerated(EnumType.STRING)
	private TipoTarjeta tipoTarjeta;
	@Column(name="PT_NROTARJETA")
	private double nroTarjeta;
	@Column(name="PT_FECHAVENC")
	private Calendar fechaVenc;
	@Column(name="PT_CVC")
	private int cvc;
	@Column(name="PT_NOMBRETITULAR")
	private String nombreTitular;
	
	public PagoTarjeta() {
		super();
	}
	
	public PagoTarjeta(boolean compEmitido, Calendar fechaEmitido, double montoAPagar, TipoTarjeta tipoTarjeta, double nroTarjeta, Calendar fechaVenc, int cvc, String nombreTitular) {
		super(compEmitido, fechaEmitido, montoAPagar);
		this.tipoTarjeta = tipoTarjeta;
		this.nroTarjeta = nroTarjeta;
		this.fechaVenc = fechaVenc;
		this.cvc = cvc;
		this.nombreTitular = nombreTitular;
	}
	
	public PagoTarjeta(DtPagoTarjeta pt) {
		super(pt.isCompEmitido(), pt.getFechaEmitido(), pt.getMontoAPagar());
		this.tipoTarjeta = pt.getTipoTarjeta();
		this.nroTarjeta = pt.getNroTarjeta();
		this.fechaVenc = pt.getFechaVenc();
		this.cvc = pt.getCvc();
		this.nombreTitular = pt.getNombreTitular();
	}

	public TipoTarjeta getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(TipoTarjeta tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	public double getNroTarjeta() {
		return nroTarjeta;
	}

	public void setNroTarjeta(double nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

	public Calendar getFechaVenc() {
		return fechaVenc;
	}

	public void setFechaVenc(Calendar fechaVenc) {
		this.fechaVenc = fechaVenc;
	}

	public int getCvc() {
		return cvc;
	}

	public void setCvc(int cvc) {
		this.cvc = cvc;
	}

	public String getNombreTitular() {
		return nombreTitular;
	}

	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}
	
	@Override
	public DtPagoTarjeta getDtPago() {
		return new DtPagoTarjeta(this.getId(), 
								this.getMontoAPagar(), 
								this.getFechaEmitido(), 
								this.isCompEmitido(), 
								tipoTarjeta, 
								nroTarjeta, 
								fechaVenc,
								cvc,
								nombreTitular);
	}
}
