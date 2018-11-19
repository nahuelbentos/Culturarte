package logica;

import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import datatype.DtColaboracion;
import datatype.DtInfoPago;
import datatype.DtPago;
import datatype.DtPagoPayPal;
import datatype.DtPagoTarjeta;
import datatype.DtPagoTrfBancaria;
import datatype.DtPropuesta;
import datatype.DtPropuestaColaborada;
import datatype.TipoRetorno;
import excepciones.TipoPagoInexistenteExpection;
import persistencia.ConexionPostgresHibernate;

@Entity
@IdClass(ColaboracionID.class)
@Table(name="COLABORACIONES")
public class Colaboracion {
	
	@Id
	@ManyToOne
	@JoinColumn(name="COLABORADOR")
	private Colaborador colaborador;

	@Id
	@ManyToOne
	@JoinColumn(name="PROPUESTA")
	private Propuesta propuestaColaborada;
	
	@Column(name="MONTO_APORTADO")
	private double monto;
	@Column(name="FECHA_APORTADO")
	private GregorianCalendar fechaAporte;
	@Column(name="TIPO_RETORNO")
	@Enumerated(EnumType.STRING)
	private TipoRetorno tipo;	
	@Column(name="COMENTARIO")
	private String comentario;
	
	@Column(nullable=false, name="ESTAELIMINADA",columnDefinition = "boolean default false")
	private boolean flagElm;
	
	@OneToOne
	@JoinColumn(name="PAGO_ID",nullable = true)
	private Pago pago;
	
	public Colaboracion() {
		super();
	}
	
	public Colaboracion(double d, GregorianCalendar fechaAporte, TipoRetorno tipo) {
		super();
		this.monto = d;
		this.fechaAporte = fechaAporte;
		this.tipo = tipo;
	}

	public boolean isFlagElm() {
		return flagElm;
	}

	public void setFlagElm(boolean flagElm) {
		this.flagElm = flagElm;
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
	
	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public DtColaboracion getDataColaboracion() {
		return new DtColaboracion(propuestaColaborada.getTitulo(), colaborador.getNickname(),
				monto, fechaAporte, tipo, comentario);
	}
	
		
	public DtPropuestaColaborada getPropuestaFromColaboracion() {
		return propuestaColaborada.getInfoPropuestaColaborada();
	}
	
	public DtPropuesta obtPropuesta() {
		return new DtPropuesta(propuestaColaborada.getTitulo());
	}
	
	public DtPropuesta obtenerDtPropuesta() {
		return propuestaColaborada.getDtPropuesta();
	}
	
	public Pago getPago() {
		return pago;
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}

	public void crearPago(DtPago pago) throws TipoPagoInexistenteExpection {
		ConexionPostgresHibernate cph = ConexionPostgresHibernate.getInstancia();
		EntityManagerFactory emf = cph.getEntityManager();
		EntityManager em = emf.createEntityManager();
		
		Pago p;
		if (pago instanceof DtPagoTarjeta) {
			p = new PagoTarjeta((DtPagoTarjeta)pago);
		} else if (pago instanceof DtPagoTrfBancaria) {
			p = new PagoTrfBancaria((DtPagoTrfBancaria)pago);
		} else if (pago instanceof DtPagoPayPal) {
			p = new PagoPayPal((DtPagoPayPal)pago);
		} else {
			throw new TipoPagoInexistenteExpection("No existe la forma de pago.");
		}
		em.getTransaction().begin();
		em.persist(p);
		
		this.setPago(p);
		em.merge(this);
		em.getTransaction().commit();
		em.close();
	}
	
	public DtInfoPago getDtInfoPago() {
		DtPago dtP = pago.getDtPago();
		dtP.setMontoAPagar(monto);
		return new DtInfoPago(this.propuestaColaborada.getTitulo(), this.colaborador.getNickname(), dtP);
	}
	
	public void marcarPagoComoEmitido() {
		pago.setCompEmitido(true);
		pago.setFechaEmitido(GregorianCalendar.getInstance());
	}
}
