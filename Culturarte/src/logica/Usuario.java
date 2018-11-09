package logica;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import datatype.DtUsuario;
import excepciones.UsuarioYaExisteFavoritaException;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TIPOUSUARIO")
@Table(name="USUARIO")
public abstract class Usuario {
	@Id
	@Column(name="NICKNAME")
    private String nickname;
	@Column(name="NOMBRE")
    private String nombre;
	@Column(name="FECHA_DE_NACIMIENTO")
    private GregorianCalendar fechaNacimiento;
	@Column(name="FECHA_DE_ELIMINACION")
    private GregorianCalendar fechaDeEliminacion;
	@Column(name="EMAIL")
    private String correoElectronico;
	@Column(name="PASSWORD")
    private char[] password;
	@Column(name="APELLIDO")
    private String apellido;
	@Column(nullable=false, name="ESTAELIMINADO",columnDefinition = "boolean default false")
	private boolean flagElm;
	@Lob
	@Column(name="IMAGEN")
    private byte[] imagen;
	
	@OneToMany(mappedBy="usuarioDos", 
			cascade = { 
					CascadeType.PERSIST, 
					CascadeType.MERGE
	    	}
			,orphanRemoval = true)
	private List<UsuarioSigue> usuariosQueSigue = new ArrayList<>();
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name="USU_PROPUESTASFAV",
				joinColumns=@JoinColumn(name="USU_ID"),
				inverseJoinColumns=@JoinColumn(name="PROP_ID"))
	private List<Propuesta> propuestasFavoritas = new ArrayList<>();

    public Usuario() {
    	super();
    }
    
    public Usuario(String nickname, String nombre, GregorianCalendar fechaNacimiento,
    		String correoElectronico, char[] password, String apellido, byte[] imagen) {
    	super();
        this.nickname = nickname;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.correoElectronico = correoElectronico;
        this.password = password;
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
    
    public void setPassword(char[] password) {
        this.password = password;
    }

    public void setFechaNacimiento(GregorianCalendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public void setFechaEliminacion(GregorianCalendar fechaDeEliminacion) {
        this.fechaDeEliminacion = fechaDeEliminacion;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    // GETTERS

    public String getApellido() {
        return apellido;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }
    
    public char[] getPassword() {
        return password;
    }

    public GregorianCalendar getFechaNacimiento() {
        return fechaNacimiento;
    }
    
    public GregorianCalendar getFechaDeEliminacion() {
        return fechaDeEliminacion;
    }

    public String getNickname() {
        return nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public byte[] getImagen() {
        return imagen;
    }
   
	public void seguirUsuario(Usuario usuarioASeguir) {
		UsuarioSigue u = new UsuarioSigue(this, usuarioASeguir);
		usuariosQueSigue.add(u);
	}
	
	public void dejarDeSeguirUsuario(Usuario usuarioADejarDeSeguir) {
		UsuarioSigue u = new UsuarioSigue(this, usuarioADejarDeSeguir);
		usuariosQueSigue.remove(u);
	}
	
	public void addFavorita(Propuesta p) throws UsuarioYaExisteFavoritaException{
		if (!this.propuestasFavoritas.contains(p))
			this.propuestasFavoritas.add(p);
		else
			throw new UsuarioYaExisteFavoritaException("Ya se encuentra "+p.getTitulo()+" como una propuesta favorita.");
	}
	
	public abstract DtUsuario getDtUsuario();
	
	public List<Propuesta> getPropuestasFavoritas() {
		return propuestasFavoritas;
	}

	public List<UsuarioSigue> getUsuariosQueSigue() {
		return usuariosQueSigue;
	}

	public boolean isFlagElm() {
		return flagElm;
	}

	public void setFlagElm(boolean flagElm) {
		this.flagElm = flagElm;
	}

}
