package datatype;

import java.util.GregorianCalendar;

public class DtProponente extends DtUsuario {
	
	private String direccion;
	private String biografia;
	private String sitioWeb;
	
	public DtProponente(String nickname, String nombre, String apellido, String email, char[] password,
			GregorianCalendar fechaNacimiento, byte[] imagen, String direccion, String biografia, String sitioWeb) {
		super(nickname, nombre, apellido, email, password, fechaNacimiento, imagen);
		this.direccion = direccion;
		this.biografia = biografia;
		this.sitioWeb = sitioWeb;
	}
	
	public DtProponente(String nickname, String nombre, String apellido, String email, char[] password,
			GregorianCalendar fechaNacimiento, GregorianCalendar fechaDeEliminacion, byte[] imagen, String direccion, String biografia, String sitioWeb) {
		super(nickname, nombre, apellido, email, password, fechaNacimiento, fechaDeEliminacion, imagen);
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
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
	public void setSitioWeb(String sitioWeb) {
		this.sitioWeb = sitioWeb;
	}
	public DtProponente() {
		super();
	}
	
}
