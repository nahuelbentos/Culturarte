package datatypeJee;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;

import publicadores.UsuarioNoExisteElUsuarioException;

public class DtUsuarioWeb {
	
	private String nickname;
	private String nombre;
	private String apellido;
	private String email;
	private String password;
	private GregorianCalendar fechaNacimiento;
	private byte[] imagen;
	private TipoUsuario tipoUsuario;
	
	private String direccion;
	private String biografia;
	private String sitioWeb;

	//pseudoatributos del usuario logueado
	private List<String> tituloFavoritas;
	private List<String> usuarioSeguidos;
	
	public DtUsuarioWeb(String nickname, String nombre, String apellido, String email, String password, 
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
	
	public DtUsuarioWeb(String nickname, String nombre, String apellido, String email, String password,
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
	
	public DtUsuarioWeb(String nickname, String nombre, String apellido, String email, String password,
			Calendar fechaNacimiento, byte[] imagen, TipoUsuario tipoUsuario, String direccion,
			String biografia, String sitioWeb) {
		super();
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
		this.fechaNacimiento = (GregorianCalendar) fechaNacimiento;
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
	public String getPassword() {
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

	public void setTituloFavoritas(List<String> tituloFavoritas) {
		this.tituloFavoritas = tituloFavoritas;
	}
	
	public List<String> getTituloFavoritas() {
		return tituloFavoritas;
	}

	public List<String> getUsuarioSeguidos() {
		return usuarioSeguidos;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFechaNacimiento(GregorianCalendar fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
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

	public void addTituloFavoritas(String tituloFavorita) {
		this.tituloFavoritas.add(tituloFavorita);
	}
	
	public boolean isMemberTituloFavorita(String tituloFavorita) {
		return this.tituloFavoritas.contains(tituloFavorita);
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
