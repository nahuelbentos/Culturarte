package logica;

import java.io.Serializable;

public class UsuarioSigueID implements Serializable {

	private static final long serialVersionUID = -6913036285254671806L;
	
	private String usuarioUno;
	private String usuarioDos;
	
	public UsuarioSigueID() {
		super();
	}
	
	public UsuarioSigueID(String usuarioUno, String usuarioDos) {
		super();
		this.usuarioUno = usuarioUno;
		this.usuarioDos = usuarioDos;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usuarioDos == null) ? 0 : usuarioDos.hashCode());
		result = prime * result + ((usuarioUno == null) ? 0 : usuarioUno.hashCode());
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
		UsuarioSigueID other = (UsuarioSigueID) obj;
		if (usuarioDos == null) {
			if (other.usuarioDos != null)
				return false;
		} else if (!usuarioDos.equals(other.usuarioDos))
			return false;
		if (usuarioUno == null) {
			if (other.usuarioUno != null)
				return false;
		} else if (!usuarioUno.equals(other.usuarioUno))
			return false;
		return true;
	}

	public String getUsuarioUno() {
		return usuarioUno;
	}
	public void setUsuarioUno(String usuarioUno) {
		this.usuarioUno = usuarioUno;
	}
	public String getUsuarioDos() {
		return usuarioDos;
	}
	public void setUsuarioDos(String usuarioDos) {
		this.usuarioDos = usuarioDos;
	}
	
}
