package logica.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import logica.Usuario;

public class UsuarioHandler {
	
	private Map<String, Usuario> usuarios;
	private static UsuarioHandler instancia = null;
	
	private UsuarioHandler(){
		usuarios = new HashMap<String, Usuario>();
	}
    
	/**
     * Obtiene la instancia del manejador de usuarios de la aplicacion.
     */
	public static UsuarioHandler getInstance() {
		if (instancia == null)
			instancia = new UsuarioHandler();
		return instancia;
	}

	public void agregarUsuario(Usuario usuario) {
        String nickname = usuario.getNickname();
        usuarios.put(nickname, usuario);
	}
	
    public Usuario obtenerUsuario(String nickname) {
        return ((Usuario) usuarios.get(nickname));
    }

    public Usuario[] getUsuarios() {
        if (usuarios.isEmpty())
            return null;
        else {
            Collection<Usuario> usrs = usuarios.values();
            Object[] o = usrs.toArray();
            Usuario[] usuarios = new Usuario[o.length];
            for (int i = 0; i < o.length; i++) {
                usuarios[i] = (Usuario) o[i];
            }
            return usuarios;
        }
    }
	
}
