package publicadores;

public class ControladorCategoriaPublishProxy implements publicadores.ControladorCategoriaPublish {
  private String _endpoint = null;
  private publicadores.ControladorCategoriaPublish controladorCategoriaPublish = null;
  
  public ControladorCategoriaPublishProxy() {
    _initControladorCategoriaPublishProxy();
  }
  
  public ControladorCategoriaPublishProxy(String endpoint) {
    _endpoint = endpoint;
    _initControladorCategoriaPublishProxy();
  }
  
  private void _initControladorCategoriaPublishProxy() {
    try {
      controladorCategoriaPublish = (new publicadores.ControladorCategoriaPublishServiceLocator()).getControladorCategoriaPublishPort();
      if (controladorCategoriaPublish != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)controladorCategoriaPublish)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)controladorCategoriaPublish)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (controladorCategoriaPublish != null)
      ((javax.xml.rpc.Stub)controladorCategoriaPublish)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public publicadores.ControladorCategoriaPublish getControladorCategoriaPublish() {
    if (controladorCategoriaPublish == null)
      _initControladorCategoriaPublishProxy();
    return controladorCategoriaPublish;
  }
  
  public void borrarCategorias() throws java.rmi.RemoteException{
    if (controladorCategoriaPublish == null)
      _initControladorCategoriaPublishProxy();
    controladorCategoriaPublish.borrarCategorias();
  }
  
  public void agregarCategoria(publicadores.DtCategoria arg0) throws java.rmi.RemoteException, publicadores.CategoriaYaExisteException, publicadores.CategoriaNoExisteException{
    if (controladorCategoriaPublish == null)
      _initControladorCategoriaPublishProxy();
    controladorCategoriaPublish.agregarCategoria(arg0);
  }
  
  public publicadores.DtCategoria[] listarCategoriasJTree() throws java.rmi.RemoteException{
    if (controladorCategoriaPublish == null)
      _initControladorCategoriaPublishProxy();
    return controladorCategoriaPublish.listarCategoriasJTree();
  }
  
  public publicadores.DtCategoria[] listarCategorias() throws java.rmi.RemoteException{
    if (controladorCategoriaPublish == null)
      _initControladorCategoriaPublishProxy();
    return controladorCategoriaPublish.listarCategorias();
  }
  
  
}