package datatypeJee.msjUI;

public class DtMensajeUI {
	
	private String mensaje;
	private TipoMensaje tipo;
	
	public DtMensajeUI(String mensaje, TipoMensaje tipo) {
		super();
		this.mensaje = mensaje;
		this.tipo = tipo;
	}
	
	public String getMensaje() {
		return mensaje;
	}
	public TipoMensaje getTipo() {
		return tipo;
	}
	
	

}
