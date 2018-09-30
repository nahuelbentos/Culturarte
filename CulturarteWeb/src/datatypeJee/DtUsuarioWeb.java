package datatypeJee;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.apache.tomcat.util.codec.binary.Base64;

import excepciones.UsuarioNoExisteElUsuarioException;

public class DtUsuarioWeb {
	
	private String nickname;
	private String nombre;
	private String apellido;
	private String email;
	private char[] password;
	private GregorianCalendar fechaNacimiento;
	private byte[] imagen;
	private TipoUsuario tipoUsuario;
	
	private String direccion;
	private String biografia;
	private String sitioWeb;
	
	public DtUsuarioWeb(String nickname, String nombre, String apellido, String email, char[] password,
			GregorianCalendar fechaNacimiento, byte[] imagen, TipoUsuario tipoUsuario, String direccion,
			String biografia, String sitioWeb) {
		super();
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		this.imagen = imagen;
		this.tipoUsuario = tipoUsuario;
		this.direccion = direccion;
		this.biografia = biografia;
		this.sitioWeb = sitioWeb;
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
	public char[] getPassword() {
		return password;
	}
	public GregorianCalendar getFechaNacimiento() {
		return fechaNacimiento;
	}
	public byte[] getImagen() {
		return imagen;
	}
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	
	public String getImagenAsBase64() throws UnsupportedEncodingException {
		byte[] encodeBase64 = Base64.encodeBase64(this.imagen);
        String base64Encoded = new String(encodeBase64, "UTF-8");
        return base64Encoded;
	}

	public String getDireccion() throws UsuarioNoExisteElUsuarioException {
		if (this.tipoUsuario == TipoUsuario.proponente) 
			return direccion;
		else
			throw new UsuarioNoExisteElUsuarioException("El usuario no es proponente");
		
	}

	public String getBiografia() throws UsuarioNoExisteElUsuarioException {
		if (this.tipoUsuario == TipoUsuario.proponente) 
			return biografia;
		else
			throw new UsuarioNoExisteElUsuarioException("El usuario no es proponente");
	}

	public String getSitioWeb() throws UsuarioNoExisteElUsuarioException {
		if (this.tipoUsuario == TipoUsuario.proponente) 
			return sitioWeb;
		else
			throw new UsuarioNoExisteElUsuarioException("El usuario no es proponente");
	}
	
	public String getFechaNacimientoAsString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
		
		return sdf.format(this.fechaNacimiento.getTime());
	}
}
