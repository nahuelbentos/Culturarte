package datatype;

import java.awt.Image;
import java.util.GregorianCalendar;

public class DtProponente extends DtUsuario {
	
	private String direccion;
	private String biografia;
	private String sitioWeb;
	
	public DtProponente(String nickname, String nombre, String apellido, String email, GregorianCalendar fechaNacimiento,
			Image imagen, String direccion, String biografia, String sitioWeb) {
		super(nickname, nombre, apellido, email, fechaNacimiento, imagen);
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
