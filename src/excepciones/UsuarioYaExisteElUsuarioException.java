package excepciones;

@SuppressWarnings("serial")
public class UsuarioYaExisteElUsuarioException extends Exception {

    public UsuarioYaExisteElUsuarioException(String string) {
        super(string);
    }
	
}
