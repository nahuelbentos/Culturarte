package logica;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(UsuarioSigueID.class)
public class UsuarioSigue {

	@Id 
	@ManyToOne
	@JoinColumn(
			name="USUARIO_UNO_ID",
			columnDefinition="integer"
	)
	private Usuario usuarioUno;
	
	@Id
	@ManyToOne
	@JoinColumn(
			name="USUARIO_DOS_ID",
			columnDefinition="integer"
	)
	private Usuario usuarioDos;
	
	public UsuarioSigue() {
		super();
	}
	
	public UsuarioSigue(Usuario usuarioUno, Usuario usuarioDos) {
		super();
		this.usuarioUno = usuarioUno;
		this.usuarioDos = usuarioDos;
	}

	public Usuario getUsuarioUno() {
		return usuarioUno;
	}

	public void setUsuarioUno(Usuario usuarioUno) {
		this.usuarioUno = usuarioUno;
	}

	public Usuario getUsuarioDos() {
		return usuarioDos;
	}

	public void setUsuarioDos(Usuario usuarioDos) {
		this.usuarioDos = usuarioDos;
	}
}
