package excepciones;

@SuppressWarnings("serial")
public class UsuarioNoExisteException extends Exception {

	public UsuarioNoExisteException(String message) {
		super(message);
	}
	
}
