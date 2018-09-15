package logica;

public class Factory {

    private static Factory instancia = null;

    private Factory() {};

    public static Factory getInstance() {
        if (instancia == null) {
            instancia = new Factory();
        }
        return instancia;
    }

    public ICategoriaController getICategoriaController() {
        return new CategoriaController();
    }
    
    public IPropuestaController getIPropuestaController() {
        return new PropuestaController();
    }
    
    public IUsuarioController getIUsuarioController() {
        return new UsuarioController();
    }
    
    
	/**
	 * Metodo consumir desde los servlets, datoSesion puede ser el nickname o el correo del proponente.
	 *  
	 * @param datoSesion
	 * @return PropuestaController
	 */
    public IPropuestaController getIPropuestaControllerFromSession(String datoSesion) {
        return new PropuestaController(datoSesion);
    }
	
}
