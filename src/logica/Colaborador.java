package logica;

import java.util.GregorianCalendar;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("C")
public class Colaborador extends Usuario {

	public Colaborador() {
		super();
	}
	
	public Colaborador(int id, String nickname, String nombre, GregorianCalendar fechaNacimiento, String correoElectronico,
			String apellido, String imagen) {
		super(id, nickname, nombre, fechaNacimiento, correoElectronico, apellido, imagen);
	}


}
