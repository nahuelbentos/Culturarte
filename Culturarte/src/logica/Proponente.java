package logica;

import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import datatype.DtPerfilProponente;
import datatype.DtProponente;
import datatype.DtUsuario;

@Entity
@DiscriminatorValue("P")
public class Proponente extends Usuario{
	
	@Column(name="DIRECCION")
    private String direccion;
	@Column(name="BIOGRAFIA", length=900)
    private String biografia;
	@Column(name="LINK_WEB")
    private String linkWeb;
    
    public Proponente() {
    	super();
    }
    
    public Proponente(String direccion, String biografia, String linkWeb, 
    		String nickname, String nombre, GregorianCalendar fechaNacimiento, 
    		String correoElectronico, String password, String apellido, byte[] imagen) {
        super(nickname, nombre, fechaNacimiento, correoElectronico, password, apellido, imagen);
        this.direccion = direccion;
        this.biografia = biografia;
        this.linkWeb = linkWeb;
    }

    public String getBiografia() {
        return biografia;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getLinkWeb() {
        return linkWeb;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setLinkWeb(String linkWeb) {
        this.linkWeb = linkWeb;
    }
    
    public DtPerfilProponente getDatosBasicos() {
    	
    	DtPerfilProponente dtp = new DtPerfilProponente(this.getNickname(), this.getNombre(), this.getApellido(), this.getCorreoElectronico(), 
    													this.getPassword(), this.getFechaNacimiento(), this.getImagen(), this.getDireccion(), this.getBiografia(),
    													this.getLinkWeb(),null,null, null, null, null, null); //null=Son las colecciones.    	
		return dtp;
    	
    }
    public DtProponente getDtProponente() {
    	return new DtProponente(super.getNickname(), super.getNombre(), super.getApellido(),
    			super.getCorreoElectronico(), super.getPassword(), super.getFechaNacimiento(), 
    			super.getImagen(), this.direccion, this.biografia, this.linkWeb);
    	
    }
    
    @Override
	public DtUsuario getDtUsuario() {
		return new DtProponente(super.getNickname(), super.getNombre(), super.getApellido(), super.getCorreoElectronico()
				, super.getPassword(), super.getFechaNacimiento(), super.getImagen(), this.direccion, this.biografia, this.linkWeb);
	}
}
