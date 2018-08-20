package logica;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Proponente extends Usuario{
    private String direccion;
    private String biografia;
    private String linkWeb;
    
    // PseudoAtributos
    private ArrayList<Propuesta> propuestas = new ArrayList<Propuesta>();;

    public Proponente(String direccion, String biografia, String linkWeb, 
    		String nickname, String nombre, GregorianCalendar fechaNacimiento, 
    		String correoElectronico, String apellido) {
        super(nickname, nombre, fechaNacimiento, correoElectronico, apellido);
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
    
    public void addPropuesta(Propuesta p) {
    	this.propuestas.add(p);
    }
    
}
