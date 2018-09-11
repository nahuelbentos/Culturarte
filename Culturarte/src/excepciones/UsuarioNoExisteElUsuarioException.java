package excepciones;

@SuppressWarnings("serial")
public class UsuarioNoExisteElUsuarioException extends Exception {

    public UsuarioNoExisteElUsuarioException(String string) {
        super(string);
    }
	
}
