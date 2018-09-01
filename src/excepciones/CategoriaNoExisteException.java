package excepciones;

@SuppressWarnings("serial")
public class CategoriaNoExisteException extends Exception {

	public CategoriaNoExisteException(String message) {
		super(message);
	}

}
