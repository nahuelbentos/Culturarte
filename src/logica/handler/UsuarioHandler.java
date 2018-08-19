package logica.handler;

import java.util.ArrayList;

import logica.Usuario;

public class UsuarioHandler {
	
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private static UsuarioHandler instancia = null;
	
	private UsuarioHandler(){}
    
	/**
     * Obtiene la instancia del manejador de usuarios de la aplicacion.
     */
	public static UsuarioHandler getInstance() {
		if (instancia == null)
			instancia = new UsuarioHandler();
		return instancia;
	}

	
}
