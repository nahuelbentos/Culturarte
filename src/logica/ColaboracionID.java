package logica;

import java.io.Serializable;

public class ColaboracionID implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String colaborador;
	private String propuestaColaborada;
	
	public ColaboracionID() {
		super();
	}

	public String getIdColaborador() {
		return colaborador;
	}

	public void setIdColaborador(String idColaborador) {
		this.colaborador = idColaborador;
	}

	public String getIdPropuesta() {
		return propuestaColaborada;
	}

	public void setIdPropuesta(String idPropuesta) {
		this.propuestaColaborada = idPropuesta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((colaborador == null) ? 0 : colaborador.hashCode());
		result = prime * result + ((propuestaColaborada == null) ? 0 : propuestaColaborada.hashCode());
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
		if (colaborador == null) {
			if (other.colaborador != null)
				return false;
		} else if (!colaborador.equals(other.colaborador))
			return false;
		if (propuestaColaborada == null) {
			if (other.propuestaColaborada != null)
				return false;
		} else if (!propuestaColaborada.equals(other.propuestaColaborada))
			return false;
		return true;
	}
	
	
}
