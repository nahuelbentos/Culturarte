package publicadores;

public class ControladorUsuarioPublishProxy implements publicadores.ControladorUsuarioPublish {
  private String _endpoint = null;
  private publicadores.ControladorUsuarioPublish controladorUsuarioPublish = null;
  
  public ControladorUsuarioPublishProxy() {
    _initControladorUsuarioPublishProxy();
  }
  
  public ControladorUsuarioPublishProxy(String endpoint) {
    _endpoint = endpoint;
    _initControladorUsuarioPublishProxy();
  }
  
  private void _initControladorUsuarioPublishProxy() {
    try {
      controladorUsuarioPublish = (new publicadores.ControladorUsuarioPublishServiceLocator()).getControladorUsuarioPublishPort();
      if (controladorUsuarioPublish != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)controladorUsuarioPublish)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)controladorUsuarioPublish)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (controladorUsuarioPublish != null)
      ((javax.xml.rpc.Stub)controladorUsuarioPublish)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public publicadores.ControladorUsuarioPublish getControladorUsuarioPublish() {
    if (controladorUsuarioPublish == null)
      _initControladorUsuarioPublishProxy();
    return controladorUsuarioPublish;
  }
  
  public publicadores.DtUsuario iniciarSesion(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException, publicadores.UsuarioNoExisteElUsuarioException{
    if (controladorUsuarioPublish == null)
      _initControladorUsuarioPublishProxy();
    return controladorUsuarioPublish.iniciarSesion(arg0, arg1);
  }
  
  public publicadores.DtPropuesta[] listarPropuestasColaborador(publicadores.DtUsuario arg0) throws java.rmi.RemoteException, publicadores.UsuarioSinLoguearseException{
    if (controladorUsuarioPublish == null)
      _initControladorUsuarioPublishProxy();
    return controladorUsuarioPublish.listarPropuestasColaborador(arg0);
  }
  
  public publicadores.DtPerfilUsuario obtenerPerfilUsuario(java.lang.String arg0, publicadores.DtUsuario arg1) throws java.rmi.RemoteException{
    if (controladorUsuarioPublish == null)
      _initControladorUsuarioPublishProxy();
    return controladorUsuarioPublish.obtenerPerfilUsuario(arg0, arg1);
  }
  
  public publicadores.DtProponente[] getMasProponedores() throws java.rmi.RemoteException{
    if (controladorUsuarioPublish == null)
      _initControladorUsuarioPublishProxy();
    return controladorUsuarioPublish.getMasProponedores();
  }
  
  public void seguirUsuario(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException, publicadores.UsuarioYaSigueAlUsuarioException{
    if (controladorUsuarioPublish == null)
      _initControladorUsuarioPublishProxy();
    controladorUsuarioPublish.seguirUsuario(arg0, arg1);
  }
  
  public void dejarDeSeguirUsuario(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException{
    if (controladorUsuarioPublish == null)
      _initControladorUsuarioPublishProxy();
    controladorUsuarioPublish.dejarDeSeguirUsuario(arg0, arg1);
  }
  
  public publicadores.DtColaborador[] getMasColaboradores() throws java.rmi.RemoteException{
    if (controladorUsuarioPublish == null)
      _initControladorUsuarioPublishProxy();
    return controladorUsuarioPublish.getMasColaboradores();
  }
  
  public void agregarUsuario(publicadores.DtUsuario arg0) throws java.rmi.RemoteException, publicadores.UsuarioYaExisteElEmailException, publicadores.UsuarioYaExisteElUsuarioException{
    if (controladorUsuarioPublish == null)
      _initControladorUsuarioPublishProxy();
    controladorUsuarioPublish.agregarUsuario(arg0);
  }
  
  public void agregarComentarioAPropuesta(java.lang.String arg0, java.lang.String arg1, publicadores.DtUsuario arg2) throws java.rmi.RemoteException, publicadores.UsuarioSinLoguearseException{
    if (controladorUsuarioPublish == null)
      _initControladorUsuarioPublishProxy();
    controladorUsuarioPublish.agregarComentarioAPropuesta(arg0, arg1, arg2);
  }
  
  public publicadores.DtPerfilColaborador verPerfilColaborador(java.lang.String arg0) throws java.rmi.RemoteException{
    if (controladorUsuarioPublish == null)
      _initControladorUsuarioPublishProxy();
    return controladorUsuarioPublish.verPerfilColaborador(arg0);
  }
  
  public publicadores.DtUsuario[] listarColaboradores() throws java.rmi.RemoteException, publicadores.ColaboradorNoExisteException{
    if (controladorUsuarioPublish == null)
      _initControladorUsuarioPublishProxy();
    return controladorUsuarioPublish.listarColaboradores();
  }
  
  public publicadores.DtUsuario[] listarProponentes() throws java.rmi.RemoteException, publicadores.ProponenteNoExisteException{
    if (controladorUsuarioPublish == null)
      _initControladorUsuarioPublishProxy();
    return controladorUsuarioPublish.listarProponentes();
  }
  
  public publicadores.DtUsuario[] listarUsuarios() throws java.rmi.RemoteException{
    if (controladorUsuarioPublish == null)
      _initControladorUsuarioPublishProxy();
    return controladorUsuarioPublish.listarUsuarios();
  }
  
  public publicadores.DtUsuario verPerfilUsuario(java.lang.String arg0) throws java.rmi.RemoteException, publicadores.UsuarioNoExisteElUsuarioException{
    if (controladorUsuarioPublish == null)
      _initControladorUsuarioPublishProxy();
    return controladorUsuarioPublish.verPerfilUsuario(arg0);
  }
  
  public publicadores.DtUsuario[] listarUsuariosQueSigue(java.lang.String arg0) throws java.rmi.RemoteException{
    if (controladorUsuarioPublish == null)
      _initControladorUsuarioPublishProxy();
    return controladorUsuarioPublish.listarUsuariosQueSigue(arg0);
  }
  
  public publicadores.DtPropuesta[] listarPropuestasDeUnColaborador(java.lang.String arg0) throws java.rmi.RemoteException{
    if (controladorUsuarioPublish == null)
      _initControladorUsuarioPublishProxy();
    return controladorUsuarioPublish.listarPropuestasDeUnColaborador(arg0);
  }
  
  public publicadores.DtColaboracion listarColaboracion(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException, publicadores.ColaboracionNoExisteException{
    if (controladorUsuarioPublish == null)
      _initControladorUsuarioPublishProxy();
    return controladorUsuarioPublish.listarColaboracion(arg0, arg1);
  }
  
  public publicadores.DtPerfilProponente verPerfilProponente(java.lang.String arg0) throws java.rmi.RemoteException{
    if (controladorUsuarioPublish == null)
      _initControladorUsuarioPublishProxy();
    return controladorUsuarioPublish.verPerfilProponente(arg0);
  }
  
  
}