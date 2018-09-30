package datatype;

import java.util.GregorianCalendar;

public class DtUsuario {
	
	private String nickname;
	private String nombre;
	private String apellido;
	private String email;
	private char[] password;
	private GregorianCalendar fechaNacimiento;
	private byte[] imagen;
	
	public DtUsuario(String nickname, String nombre, String apellido, String email, char[] password, 
			GregorianCalendar fechaNacimiento, byte[] imagen) {
		super();
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		this.imagen = imagen;
	}
	public DtUsuario() {
		super();		
	}
	public String getNickname() {
		return nickname;
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public String getEmail() {
		return email;
	}
	public GregorianCalendar getFechaNacimiento() {
		return fechaNacimiento;
	}
	public byte[] getImagen() {
		return imagen;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}
	
}
