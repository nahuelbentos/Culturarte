package publicadores;

public class ControladorPropuestaPublishProxy implements publicadores.ControladorPropuestaPublish {
  private String _endpoint = null;
  private publicadores.ControladorPropuestaPublish controladorPropuestaPublish = null;
  
  public ControladorPropuestaPublishProxy() {
    _initControladorPropuestaPublishProxy();
  }
  
  public ControladorPropuestaPublishProxy(String endpoint) {
    _endpoint = endpoint;
    _initControladorPropuestaPublishProxy();
  }
  
  private void _initControladorPropuestaPublishProxy() {
    try {
      controladorPropuestaPublish = (new publicadores.ControladorPropuestaPublishServiceLocator()).getControladorPropuestaPublishPort();
      if (controladorPropuestaPublish != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)controladorPropuestaPublish)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)controladorPropuestaPublish)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (controladorPropuestaPublish != null)
      ((javax.xml.rpc.Stub)controladorPropuestaPublish)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public publicadores.ControladorPropuestaPublish getControladorPropuestaPublish() {
    if (controladorPropuestaPublish == null)
      _initControladorPropuestaPublishProxy();
    return controladorPropuestaPublish;
  }
  
  public publicadores.DtDatosPropuesta consultarPropuesta(java.lang.String arg0) throws java.rmi.RemoteException, publicadores.ProponenteNoExisteException{
    if (controladorPropuestaPublish == null)
      _initControladorPropuestaPublishProxy();
    return controladorPropuestaPublish.consultarPropuesta(arg0);
  }
  
  public publicadores.DtPropuestaMinificado[] listarPropuestasPorEstado(publicadores.EstadoPropuesta arg0) throws java.rmi.RemoteException, publicadores.PropuestaNoExisteException{
    if (controladorPropuestaPublish == null)
      _initControladorPropuestaPublishProxy();
    return controladorPropuestaPublish.listarPropuestasPorEstado(arg0);
  }
  
  public publicadores.DtPropuestaMinificado[] listadoPropuestasIngresadas() throws java.rmi.RemoteException, publicadores.PropuestaNoExisteException{
    if (controladorPropuestaPublish == null)
      _initControladorPropuestaPublishProxy();
    return controladorPropuestaPublish.listadoPropuestasIngresadas();
  }
  
  public publicadores.DtPropuestaMinificado[] listarPropuestasProponentePorEstado(java.lang.String arg0, publicadores.EstadoPropuesta arg1) throws java.rmi.RemoteException, publicadores.PropuestaNoExisteException{
    if (controladorPropuestaPublish == null)
      _initControladorPropuestaPublishProxy();
    return controladorPropuestaPublish.listarPropuestasProponentePorEstado(arg0, arg1);
  }
  
  public publicadores.DtPropuestaMinificado[] listarPropuestasActivas() throws java.rmi.RemoteException{
    if (controladorPropuestaPublish == null)
      _initControladorPropuestaPublishProxy();
    return controladorPropuestaPublish.listarPropuestasActivas();
  }
  
  public void extenderFinanciacion(java.lang.String arg0) throws java.rmi.RemoteException, publicadores.PropuestaNoExisteException{
    if (controladorPropuestaPublish == null)
      _initControladorPropuestaPublishProxy();
    controladorPropuestaPublish.extenderFinanciacion(arg0);
  }
  
  public void cancelarPropuesta(java.lang.String arg0) throws java.rmi.RemoteException, publicadores.PropuestaNoExisteException{
    if (controladorPropuestaPublish == null)
      _initControladorPropuestaPublishProxy();
    controladorPropuestaPublish.cancelarPropuesta(arg0);
  }
  
  public publicadores.DtPropuesta[] listarPropuestasPorCategoria(java.lang.String arg0) throws java.rmi.RemoteException{
    if (controladorPropuestaPublish == null)
      _initControladorPropuestaPublishProxy();
    return controladorPropuestaPublish.listarPropuestasPorCategoria(arg0);
  }
  
  public publicadores.DtPropuesta[] getPropuestasPopulares() throws java.rmi.RemoteException{
    if (controladorPropuestaPublish == null)
      _initControladorPropuestaPublishProxy();
    return controladorPropuestaPublish.getPropuestasPopulares();
  }
  
  public publicadores.DtPropuestaMinificado[] propuestasDesdeBuscador(java.lang.String arg0) throws java.rmi.RemoteException{
    if (controladorPropuestaPublish == null)
      _initControladorPropuestaPublishProxy();
    return controladorPropuestaPublish.propuestasDesdeBuscador(arg0);
  }
  
  public void pagarColaboracion(publicadores.DtInfoPago arg0) throws java.rmi.RemoteException, publicadores.TipoPagoInexistenteExpection{
    if (controladorPropuestaPublish == null)
      _initControladorPropuestaPublishProxy();
    controladorPropuestaPublish.pagarColaboracion(arg0);
  }
  
  public publicadores.DtInfoPago obtenerComprobanteDePagoDeColaboracion(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException, publicadores.ColaboracionNoExisteException, publicadores.TipoPagoInexistenteExpection{
    if (controladorPropuestaPublish == null)
      _initControladorPropuestaPublishProxy();
    return controladorPropuestaPublish.obtenerComprobanteDePagoDeColaboracion(arg0, arg1);
  }
  
  public publicadores.DtColaboracion[] listarColaboracionAPagar(java.lang.String arg0) throws java.rmi.RemoteException, publicadores.ColaboracionNoExisteException{
    if (controladorPropuestaPublish == null)
      _initControladorPropuestaPublishProxy();
    return controladorPropuestaPublish.listarColaboracionAPagar(arg0);
  }
  
  public publicadores.TipoRetorno[] obtenerTiposRetorno() throws java.rmi.RemoteException{
    if (controladorPropuestaPublish == null)
      _initControladorPropuestaPublishProxy();
    return controladorPropuestaPublish.obtenerTiposRetorno();
  }
  
  public publicadores.EstadoPropuesta[] obtenerEstadosPropuesta() throws java.rmi.RemoteException{
    if (controladorPropuestaPublish == null)
      _initControladorPropuestaPublishProxy();
    return controladorPropuestaPublish.obtenerEstadosPropuesta();
  }
  
  public publicadores.TipoTarjeta[] obtenerTiposTarjeta() throws java.rmi.RemoteException{
    if (controladorPropuestaPublish == null)
      _initControladorPropuestaPublishProxy();
    return controladorPropuestaPublish.obtenerTiposTarjeta();
  }
  
  public void losTiposExtendidos(publicadores.DtPagoTarjeta arg0, publicadores.DtPagoTrfBancaria arg1, publicadores.DtPagoPayPal arg2) throws java.rmi.RemoteException{
    if (controladorPropuestaPublish == null)
      _initControladorPropuestaPublishProxy();
    controladorPropuestaPublish.losTiposExtendidos(arg0, arg1, arg2);
  }
  
  public void altaPropuesta(publicadores.DtPropuesta arg0) throws java.rmi.RemoteException, publicadores.PropuestaRepetidaException, publicadores.CategoriaNoExisteException, publicadores.ProponenteNoExisteException{
    if (controladorPropuestaPublish == null)
      _initControladorPropuestaPublishProxy();
    controladorPropuestaPublish.altaPropuesta(arg0);
  }
  
  public boolean modificarPropuesta(publicadores.DtPropuesta arg0) throws java.rmi.RemoteException{
    if (controladorPropuestaPublish == null)
      _initControladorPropuestaPublishProxy();
    return controladorPropuestaPublish.modificarPropuesta(arg0);
  }
  
  public void generarColaboracion(publicadores.DtColaboracion arg0) throws java.rmi.RemoteException, publicadores.ColaboradorNoExisteException, publicadores.PropuestaNoExisteException, publicadores.ColaboracionExistenteException{
    if (controladorPropuestaPublish == null)
      _initControladorPropuestaPublishProxy();
    controladorPropuestaPublish.generarColaboracion(arg0);
  }
  
  public void agregarFavorita(java.lang.String arg0, publicadores.DtUsuario arg1) throws java.rmi.RemoteException, publicadores.UsuarioSinLoguearseException{
    if (controladorPropuestaPublish == null)
      _initControladorPropuestaPublishProxy();
    controladorPropuestaPublish.agregarFavorita(arg0, arg1);
  }
  
  public void borrarEstadosPropuestas() throws java.rmi.RemoteException{
    if (controladorPropuestaPublish == null)
      _initControladorPropuestaPublishProxy();
    controladorPropuestaPublish.borrarEstadosPropuestas();
  }
  
  public void setearEstadosPropuests(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException, publicadores.ParseException{
    if (controladorPropuestaPublish == null)
      _initControladorPropuestaPublishProxy();
    controladorPropuestaPublish.setearEstadosPropuests(arg0, arg1, arg2);
  }
  
  public publicadores.DtPropuestaMinificado[] listarPropuestas() throws java.rmi.RemoteException, publicadores.PropuestaNoExisteException{
    if (controladorPropuestaPublish == null)
      _initControladorPropuestaPublishProxy();
    return controladorPropuestaPublish.listarPropuestas();
  }
  
  public publicadores.DtColaboracion[] listarColaboraciones(java.lang.String arg0) throws java.rmi.RemoteException{
    if (controladorPropuestaPublish == null)
      _initControladorPropuestaPublishProxy();
    return controladorPropuestaPublish.listarColaboraciones(arg0);
  }
  
  public publicadores.DtPropuesta[] listarPropuestasExistentes() throws java.rmi.RemoteException{
    if (controladorPropuestaPublish == null)
      _initControladorPropuestaPublishProxy();
    return controladorPropuestaPublish.listarPropuestasExistentes();
  }
  
  public publicadores.DtPropuesta seleccionarPropuesta(java.lang.String arg0) throws java.rmi.RemoteException{
    if (controladorPropuestaPublish == null)
      _initControladorPropuestaPublishProxy();
    return controladorPropuestaPublish.seleccionarPropuesta(arg0);
  }
  
  public void evaluarPropuesta(java.lang.String arg0, publicadores.EstadoPropuesta arg1) throws java.rmi.RemoteException, publicadores.PropuestaNoExisteException{
    if (controladorPropuestaPublish == null)
      _initControladorPropuestaPublishProxy();
    controladorPropuestaPublish.evaluarPropuesta(arg0, arg1);
  }
  
  
}