package excepciones;

@SuppressWarnings("serial")
public class UsuarioYaLogueado extends Exception {
	
	public UsuarioYaLogueado(String message) {
		super(message);
	}
	
}
