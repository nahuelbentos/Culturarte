/**
 * ControladorPropuestaPublish.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package publicadores;

public interface ControladorPropuestaPublish extends java.rmi.Remote {
    public void extenderFinanciacion(java.lang.String arg0) throws java.rmi.RemoteException, publicadores.PropuestaNoExisteException;
    public boolean modificarPropuesta(publicadores.DtPropuesta arg0) throws java.rmi.RemoteException;
    public void agregarFavorita(java.lang.String arg0, publicadores.DtUsuario arg1) throws java.rmi.RemoteException, publicadores.UsuarioSinLoguearseException;
    public void altaPropuesta(publicadores.DtPropuesta arg0) throws java.rmi.RemoteException, publicadores.PropuestaRepetidaException, publicadores.CategoriaNoExisteException, publicadores.ProponenteNoExisteException;
    public publicadores.DtPropuesta seleccionarPropuesta(java.lang.String arg0) throws java.rmi.RemoteException;
    public publicadores.DtPropuesta[] listarPropuestasExistentes() throws java.rmi.RemoteException;
    public void evaluarPropuesta(java.lang.String arg0, publicadores.EstadoPropuesta arg1) throws java.rmi.RemoteException, publicadores.PropuestaNoExisteException;
    public void setearEstadosPropuests(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException, publicadores.ParseException;
    public void borrarEstadosPropuestas() throws java.rmi.RemoteException;
    public void generarColaboracion(publicadores.DtColaboracion arg0) throws java.rmi.RemoteException, publicadores.ColaboradorNoExisteException, publicadores.PropuestaNoExisteException, publicadores.ColaboracionExistenteException;
    public publicadores.DtPropuestaMinificado[] listarPropuestas() throws java.rmi.RemoteException, publicadores.PropuestaNoExisteException;
    public publicadores.DtColaboracion[] listarColaboraciones(java.lang.String arg0) throws java.rmi.RemoteException;
    public publicadores.DtPropuestaMinificado[] listarPropuestasActivas() throws java.rmi.RemoteException;
    public publicadores.DtPropuestaMinificado[] listadoPropuestasIngresadas() throws java.rmi.RemoteException, publicadores.PropuestaNoExisteException;
    public publicadores.DtPropuestaMinificado[] listarPropuestasPorEstado(publicadores.EstadoPropuesta arg0) throws java.rmi.RemoteException, publicadores.PropuestaNoExisteException;
    public publicadores.DtDatosPropuesta consultarPropuesta(java.lang.String arg0) throws java.rmi.RemoteException, publicadores.ProponenteNoExisteException;
    public publicadores.DtPropuestaMinificado[] listarPropuestasProponentePorEstado(java.lang.String arg0, publicadores.EstadoPropuesta arg1) throws java.rmi.RemoteException, publicadores.PropuestaNoExisteException;
    public publicadores.DtInfoPago obtenerComprobanteDePagoDeColaboracion(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException, publicadores.ColaboracionNoExisteException, publicadores.TipoPagoInexistenteExpection;
    public void pagarColaboracion(publicadores.DtInfoPago arg0) throws java.rmi.RemoteException, publicadores.TipoPagoInexistenteExpection;
    public publicadores.DtColaboracion obtenerColaboracion(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException;
    public publicadores.DtPropuestaMinificado[] propuestasDesdeBuscador(java.lang.String arg0) throws java.rmi.RemoteException;
    public void marcarPagoComoEmitido(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException, publicadores.ColaboracionNoExisteException;
    public publicadores.DtPropuesta[] getPropuestasPopulares() throws java.rmi.RemoteException;
    public publicadores.DtPropuesta[] listarPropuestasPorCategoria(java.lang.String arg0) throws java.rmi.RemoteException;
    public void cancelarPropuesta(java.lang.String arg0) throws java.rmi.RemoteException, publicadores.PropuestaNoExisteException;
    public publicadores.DtColaboracion[] listarColaboracionAPagar(java.lang.String arg0) throws java.rmi.RemoteException, publicadores.ColaboracionNoExisteException;
    public publicadores.EstadoPropuesta[] obtenerEstadosPropuesta() throws java.rmi.RemoteException;
    public publicadores.TipoTarjeta[] obtenerTiposTarjeta() throws java.rmi.RemoteException;
    public publicadores.TipoRetorno[] obtenerTiposRetorno() throws java.rmi.RemoteException;
    public void losTiposExtendidos(publicadores.DtPagoTarjeta arg0, publicadores.DtPagoTrfBancaria arg1, publicadores.DtPagoPayPal arg2) throws java.rmi.RemoteException;
}
