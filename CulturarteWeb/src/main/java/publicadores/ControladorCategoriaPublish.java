/**
 * ControladorCategoriaPublish.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package publicadores;

public interface ControladorCategoriaPublish extends java.rmi.Remote {
    public void borrarCategorias() throws java.rmi.RemoteException;
    public void agregarCategoria(publicadores.DtCategoria arg0) throws java.rmi.RemoteException, publicadores.CategoriaYaExisteException, publicadores.CategoriaNoExisteException;
    public publicadores.DtCategoria[] listarCategoriasJTree() throws java.rmi.RemoteException;
    public publicadores.DtCategoria[] listarCategorias() throws java.rmi.RemoteException;
}
