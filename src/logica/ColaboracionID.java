package logica;

import java.io.Serializable;

public class ColaboracionID implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int colaborador;
	private int propuestaColaborada;
	
	public ColaboracionID() {
		super();
	}

	public int getIdColaborador() {
		return colaborador;
	}

	public void setIdColaborador(int idColaborador) {
		this.colaborador = idColaborador;
	}

	public int getIdPropuesta() {
		return propuestaColaborada;
	}

	public void setIdPropuesta(int idPropuesta) {
		this.propuestaColaborada = idPropuesta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + colaborador;
		result = prime * result + propuestaColaborada;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ColaboracionID other = (ColaboracionID) obj;
		if (colaborador != other.colaborador)
			return false;
		if (propuestaColaborada != other.propuestaColaborada)
			return false;
		return true;
	}
	
	
	
	
}
