/**
 * ControladorUsuarioPublish.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package publicadores;

public interface ControladorUsuarioPublish extends java.rmi.Remote {
    public void dejarDeSeguirUsuario(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException;
    public void seguirUsuario(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException, publicadores.UsuarioYaSigueAlUsuarioException;
    public void agregarComentarioAPropuesta(java.lang.String arg0, java.lang.String arg1, publicadores.DtUsuario arg2) throws java.rmi.RemoteException, publicadores.UsuarioSinLoguearseException;
    public void agregarUsuario(publicadores.DtUsuario arg0) throws java.rmi.RemoteException, publicadores.UsuarioYaExisteElEmailException, publicadores.UsuarioYaExisteElUsuarioException;
    public publicadores.DtUsuario[] listarProponentes() throws java.rmi.RemoteException, publicadores.ProponenteNoExisteException;
    public publicadores.DtUsuario[] listarUsuariosQueSigue(java.lang.String arg0) throws java.rmi.RemoteException;
    public publicadores.DtUsuario[] listarUsuarios() throws java.rmi.RemoteException;
    public publicadores.DtUsuario verPerfilUsuario(java.lang.String arg0) throws java.rmi.RemoteException, publicadores.UsuarioNoExisteElUsuarioException;
    public publicadores.DtPerfilProponente verPerfilProponente(java.lang.String arg0) throws java.rmi.RemoteException;
    public publicadores.DtUsuario[] listarColaboradores() throws java.rmi.RemoteException, publicadores.ColaboradorNoExisteException;
    public publicadores.DtColaboracion listarColaboracion(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException, publicadores.ColaboracionNoExisteException;
    public publicadores.DtPropuesta[] listarPropuestasDeUnColaborador(java.lang.String arg0) throws java.rmi.RemoteException;
    public publicadores.DtPerfilColaborador verPerfilColaborador(java.lang.String arg0) throws java.rmi.RemoteException;
    public publicadores.DtPropuesta[] listarFavoritasUsuario(java.lang.String arg0) throws java.rmi.RemoteException;
    public void registrarAccesoAlSitio(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException, publicadores.IOException, publicadores.URISyntaxException;
    public publicadores.DtUsuario[] verRankingUsuarios() throws java.rmi.RemoteException;
    public publicadores.DtProponente[] getMasProponedores() throws java.rmi.RemoteException;
    public publicadores.DtColaborador[] getMasColaboradores() throws java.rmi.RemoteException;
    public publicadores.DtPropuesta[] listarPropuestasColaborador(publicadores.DtUsuario arg0) throws java.rmi.RemoteException, publicadores.UsuarioSinLoguearseException;
    public publicadores.DtPerfilUsuario obtenerPerfilUsuario(java.lang.String arg0, publicadores.DtUsuario arg1) throws java.rmi.RemoteException;
    public boolean verificarNicknameEmail(java.lang.String arg0, boolean arg1) throws java.rmi.RemoteException;
    public void eliminarCuenta(java.lang.String arg0) throws java.rmi.RemoteException, publicadores.UsuarioNoExisteElUsuarioException;
    public publicadores.DtUsuario iniciarSesion(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException, publicadores.UsuarioNoExisteElUsuarioException;
}
