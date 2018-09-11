package excepciones;

@SuppressWarnings("serial")
public class UsuarioYaExisteElEmailException extends Exception {

    public UsuarioYaExisteElEmailException(String string) {
        super(string);
    }
	
}
