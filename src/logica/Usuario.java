package logica;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import datatype.DtPerfilProponente;
import datatype.DtProponente;

public abstract class Usuario {
    private String nickname;
    private String nombre;
    private GregorianCalendar fechaNacimiento;
    private String correoElectronico;
    private String apellido;
    
    // PseudoAtributos
    private ArrayList<Propuesta> proFavoritas;
    private ArrayList<Usuario> seguidores;
    private ArrayList<Usuario> seguidos;

    public Usuario(String nickname, String nombre, GregorianCalendar fechaNacimiento, String correoElectronico, String apellido) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.correoElectronico = correoElectronico;
        this.apellido = apellido;
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
}
