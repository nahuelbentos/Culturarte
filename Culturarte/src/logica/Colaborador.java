package logica;

import java.util.GregorianCalendar;

import datatype.DtColaborador;
import datatype.DtUsuario;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("C")
public class Colaborador extends Usuario {

	public Colaborador() {
		super();
	}

	public Colaborador(String nickname, String nombre, GregorianCalendar fechaNacimiento, String correoElectronico,
			char[] password, String apellido, byte[] imagen) {
		super(nickname, nombre, fechaNacimiento, correoElectronico, password, apellido, imagen);
	}
	public DtColaborador getDtColaborador() {
		DtColaborador dtc = new DtColaborador(getNickname(), getNombre(), getApellido(),
				getCorreoElectronico(), getPassword(),getFechaNacimiento(), getImagen());
		return dtc;

	}
	
	@Override
	public DtUsuario getDtUsuario() {
		return new DtColaborador(super.getNickname(), super.getNombre(), super.getApellido(), super.getCorreoElectronico(),
				super.getPassword(), super.getFechaNacimiento(), super.getImagen());
	}

}