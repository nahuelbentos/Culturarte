package logica;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TIPOUSUARIO")
@Table(name="USUARIO")
public abstract class Usuario {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	@Column(name="NICKNAME")
    private String nickname;
	@Column(name="NOMBRE")
    private String nombre;
	@Column(name="FECHA_DE_NACIMIENTO")
    private GregorianCalendar fechaNacimiento;
	@Column(name="EMAIL")
    private String correoElectronico;
	@Column(name="APELLIDO")
    private String apellido;
	@Column(name="IMAGEN")
    private String imagen;

//    private ArrayList<Propuesta> proFavoritas;

	//@OneToMany(mappedBy="usuario",cascade=CascadeType.DETACH,orphanRemoval=true)
	
	@OneToMany(mappedBy="usuarioDos")
	private List<UsuarioSigue> usuariosQueSigue = new ArrayList<>();
	
//    private Map<String, Usuario> usuariosQueSigue = new HashMap<String, Usuario>();

    public Usuario() {
    	super();
    }
    
    public Usuario(int id, String nickname, String nombre, GregorianCalendar fechaNacimiento,
    		String correoElectronico, String apellido, String imagen) {
    	super();
    	this.id = id;
        this.nickname = nickname;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.correoElectronico = correoElectronico;
        this.apellido = apellido;
        this.imagen = imagen;
    }

	// SETTERS
	public void setId(int id) {
		this.id = id;
	}
    
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
	public int getId() {
		return id;
	}

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

//    public Map<String, Usuario> getUsuariosQueSigue() {
//    	return usuariosQueSigue;
//    }
//
//    public Usuario[] getListaUsuariosQueSigue() {
//        if (usuariosQueSigue.isEmpty())
//            return null;
//        else {
//            Collection<Usuario> usrs = usuariosQueSigue.values();
//            Object[] o = usrs.toArray();
//            Usuario[] usuarios = new Usuario[o.length];
//            for (int i = 0; i < o.length; i++) {
//                usuarios[i] = (Usuario) o[i];
//            }
//            return usuarios;
//        }
//    }
    
	public void seguirUsuario(Usuario usuarioASeguir) {
		UsuarioSigue u = new UsuarioSigue(this, usuarioASeguir);
		usuariosQueSigue.add(u);
//		socio.getInscripciones().add(i);
	}
}
