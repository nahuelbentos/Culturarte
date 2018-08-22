package datatype;

import java.awt.Image;
import java.util.Date;
import java.util.GregorianCalendar;

public class DtUsuario {
	
	private String nickname;
	private String nombre;
	private String apellido;
	private String email;
	private GregorianCalendar fechaNacimiento;
	private Image imagen;
	
	public DtUsuario(String nickname, String nombre, String apellido, String email, GregorianCalendar fechaNacimiento,
			Image imagen) {
		super();
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.imagen = imagen;
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
	public Image getImagen() {
		return imagen;
	}
	
}
