package excepciones;

@SuppressWarnings("serial")
public class CategoriaYaExisteException extends Exception {

	public CategoriaYaExisteException(String message) {
		super(message);
	}
}
