package datatype;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class DtUsuario {
	
	private String nickname;
	private String nombre;
	private String apellido;
	private String email;
	private char[] password;
	private GregorianCalendar fechaNacimiento;
	private byte[] imagen;
	
	private List<String> tituloFavoritas;
	private List<String> usuarioSeguidos;
	
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
		this.tituloFavoritas = new ArrayList<String>();
		this.usuarioSeguidos = new ArrayList<String>();
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
	
	public List<String> getTituloFavoritas() {
		return tituloFavoritas;
	}
	
	public void setTituloFavoritas(List<String> tituloFavoritas) {
		this.tituloFavoritas = tituloFavoritas;
	}
	
	public void addTituloFavoritas(String tituloFavorita) {
		this.tituloFavoritas.add(tituloFavorita);
	}
	
	public boolean isMemberTituloFavorita(String tituloFavorita) {
		return this.tituloFavoritas.contains(tituloFavorita);
	}
	public List<String> getUsuarioSeguidos() {
		return usuarioSeguidos;
	}
	public void setUsuarioSeguidos(List<String> usuarioSeguidos) {
		this.usuarioSeguidos = usuarioSeguidos;
	}
	
	public void addUsuarioSeguido(String usuarioASeguir) {
		this.usuarioSeguidos.add(usuarioASeguir);
	}
	public void removeUsuarioSeguido(String usuarioASeguir) {
		this.usuarioSeguidos.remove(usuarioASeguir);
	}
	
	public boolean isMemberUsuarioSeguidos(String usuarioASeguir) {
		return this.usuarioSeguidos.contains(usuarioASeguir);
	}
}
