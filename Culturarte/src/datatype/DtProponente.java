package datatype;

import java.util.GregorianCalendar;

public class DtProponente extends DtUsuario {
	
	private String direccion;
	private String biografia;
	private String sitioWeb;
	
	public DtProponente(String nickname, String nombre, String apellido, String email, String password,
			GregorianCalendar fechaNacimiento, byte[] imagen, String direccion, String biografia, String sitioWeb) {
		super(nickname, nombre, apellido, email, password, fechaNacimiento, imagen);
		this.direccion = direccion;
		this.biografia = biografia;
		this.sitioWeb = sitioWeb;
	}
	public String getDireccion() {
		return direccion;
	}
	public String getBiografia() {
		return biografia;
	}
	public String getSitioWeb() {
		return sitioWeb;
	}
	
}
