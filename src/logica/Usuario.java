package logica;

import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIO")
public abstract class Usuario {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

    private String nickname;
    private String nombre;
    private GregorianCalendar fechaNacimiento;
    private String correoElectronico;
    private String apellido;
    private String imagen;

//    private ArrayList<Propuesta> proFavoritas;
//    private ArrayList<Usuario> seguidores;

    private Map<String, Usuario> usuariosQueSigue = new HashMap<String, Usuario>();

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

    public Usuario(String nickname, String nombre, GregorianCalendar fechaNacimiento,
    		String correoElectronico, String apellido, String imagen) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.correoElectronico = correoElectronico;
        this.apellido = apellido;
        this.imagen = imagen;
    }

	// SETTERS
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setFechaNacimiento(GregorianCalendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    // GETTERS
    public String getApellido() {
        return apellido;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public GregorianCalendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getNickname() {
        return nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public Map<String, Usuario> getUsuariosQueSigue() {
    	return usuariosQueSigue;
    }

    public Usuario[] getListaUsuariosQueSigue() {
        if (usuariosQueSigue.isEmpty())
            return null;
        else {
            Collection<Usuario> usrs = usuariosQueSigue.values();
            Object[] o = usrs.toArray();
            Usuario[] usuarios = new Usuario[o.length];
            for (int i = 0; i < o.length; i++) {
                usuarios[i] = (Usuario) o[i];
            }
            return usuarios;
        }
    }
}
