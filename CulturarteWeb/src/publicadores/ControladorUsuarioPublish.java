/**
 * ControladorUsuarioPublish.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package publicadores;

public interface ControladorUsuarioPublish extends java.rmi.Remote {
    public void agregarUsuario(publicadores.DtUsuario arg0) throws java.rmi.RemoteException, publicadores.UsuarioYaExisteElEmailException, publicadores.UsuarioYaExisteElUsuarioException;
    public void dejarDeSeguirUsuario(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException;
    public publicadores.DtColaborador[] getMasColaboradores() throws java.rmi.RemoteException;
    public void seguirUsuario(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException, publicadores.UsuarioYaSigueAlUsuarioException;
    public publicadores.DtUsuario[] listarProponentes() throws java.rmi.RemoteException, publicadores.ProponenteNoExisteException;
    public publicadores.DtUsuario[] listarUsuarios() throws java.rmi.RemoteException;
    public publicadores.DtUsuario verPerfilUsuario(java.lang.String arg0) throws java.rmi.RemoteException, publicadores.UsuarioNoExisteElUsuarioException;
    public void agregarComentarioAPropuesta(java.lang.String arg0, java.lang.String arg1, publicadores.DtUsuario arg2) throws java.rmi.RemoteException, publicadores.UsuarioSinLoguearseException;
    public publicadores.DtUsuario[] listarColaboradores() throws java.rmi.RemoteException, publicadores.ColaboradorNoExisteException;
    public publicadores.DtPropuesta[] listarPropuestasDeUnColaborador(java.lang.String arg0) throws java.rmi.RemoteException;
    public publicadores.DtColaboracion listarColaboracion(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException, publicadores.ColaboracionNoExisteException;
    public publicadores.DtUsuario[] listarUsuariosQueSigue(java.lang.String arg0) throws java.rmi.RemoteException;
    public publicadores.DtPerfilProponente verPerfilProponente(java.lang.String arg0) throws java.rmi.RemoteException;
    public publicadores.DtPerfilColaborador verPerfilColaborador(java.lang.String arg0) throws java.rmi.RemoteException;
    public publicadores.DtPerfilUsuario obtenerPerfilUsuario(java.lang.String arg0, publicadores.DtUsuario arg1) throws java.rmi.RemoteException;
    public publicadores.DtPropuesta[] listarPropuestasColaborador(publicadores.DtUsuario arg0) throws java.rmi.RemoteException, publicadores.UsuarioSinLoguearseException;
    public publicadores.DtProponente[] getMasProponedores() throws java.rmi.RemoteException;
    public publicadores.DtUsuario iniciarSesion(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException, publicadores.UsuarioNoExisteElUsuarioException;
}
