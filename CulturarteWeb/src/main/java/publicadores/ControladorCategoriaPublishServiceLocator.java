/**
 * ControladorCategoriaPublishServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package publicadores;

public class ControladorCategoriaPublishServiceLocator extends org.apache.axis.client.Service implements publicadores.ControladorCategoriaPublishService {

    public ControladorCategoriaPublishServiceLocator() {
    }


    public ControladorCategoriaPublishServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ControladorCategoriaPublishServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ControladorCategoriaPublishPort
    private java.lang.String ControladorCategoriaPublishPort_address = "http://127.0.0.1:1234/controladorCategoria";

    public java.lang.String getControladorCategoriaPublishPortAddress() {
        return ControladorCategoriaPublishPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ControladorCategoriaPublishPortWSDDServiceName = "ControladorCategoriaPublishPort";

    public java.lang.String getControladorCategoriaPublishPortWSDDServiceName() {
        return ControladorCategoriaPublishPortWSDDServiceName;
    }

    public void setControladorCategoriaPublishPortWSDDServiceName(java.lang.String name) {
        ControladorCategoriaPublishPortWSDDServiceName = name;
    }

    public publicadores.ControladorCategoriaPublish getControladorCategoriaPublishPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ControladorCategoriaPublishPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getControladorCategoriaPublishPort(endpoint);
    }

    public publicadores.ControladorCategoriaPublish getControladorCategoriaPublishPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            publicadores.ControladorCategoriaPublishPortBindingStub _stub = new publicadores.ControladorCategoriaPublishPortBindingStub(portAddress, this);
            _stub.setPortName(getControladorCategoriaPublishPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setControladorCategoriaPublishPortEndpointAddress(java.lang.String address) {
        ControladorCategoriaPublishPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (publicadores.ControladorCategoriaPublish.class.isAssignableFrom(serviceEndpointInterface)) {
                publicadores.ControladorCategoriaPublishPortBindingStub _stub = new publicadores.ControladorCategoriaPublishPortBindingStub(new java.net.URL(ControladorCategoriaPublishPort_address), this);
                _stub.setPortName(getControladorCategoriaPublishPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ControladorCategoriaPublishPort".equals(inputPortName)) {
            return getControladorCategoriaPublishPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://publicadores/", "ControladorCategoriaPublishService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://publicadores/", "ControladorCategoriaPublishPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ControladorCategoriaPublishPort".equals(portName)) {
            setControladorCategoriaPublishPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
