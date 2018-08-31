package logica;

import java.util.GregorianCalendar;

import datatype.DtColaborador;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("C")
public class Colaborador extends Usuario {

	public Colaborador() {
		super();
	}

	public Colaborador(String nickname, String nombre, GregorianCalendar fechaNacimiento, String correoElectronico,
			String apellido, String imagen) {
		super(nickname, nombre, fechaNacimiento, correoElectronico, apellido, imagen);
	}
	public DtColaborador getDtColaborador() {
		DtColaborador dtc = new DtColaborador(getNickname(), getNombre(), getApellido(),
				getCorreoElectronico(),getFechaNacimiento(), getImagen());
		return dtc;

	}

}