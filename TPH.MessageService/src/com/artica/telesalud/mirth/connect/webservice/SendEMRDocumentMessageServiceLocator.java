/**
 * SendEMRDocumentMessageServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.artica.telesalud.mirth.connect.webservice;

@SuppressWarnings("serial")
public class SendEMRDocumentMessageServiceLocator extends org.apache.axis.client.Service implements com.artica.telesalud.mirth.connect.webservice.SendEMRDocumentMessageService {

    public SendEMRDocumentMessageServiceLocator() {
    }


    public SendEMRDocumentMessageServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SendEMRDocumentMessageServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SendEMRDocumentMessagePort
    private java.lang.String SendEMRDocumentMessagePort_address = "http://172.21.0.174:8088/services/sendEMRDocument?wsdl";

    public java.lang.String getSendEMRDocumentMessagePortAddress() {
        return SendEMRDocumentMessagePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SendEMRDocumentMessagePortWSDDServiceName = "SendEMRDocumentMessagePort";

    public java.lang.String getSendEMRDocumentMessagePortWSDDServiceName() {
        return SendEMRDocumentMessagePortWSDDServiceName;
    }

    public void setSendEMRDocumentMessagePortWSDDServiceName(java.lang.String name) {
        SendEMRDocumentMessagePortWSDDServiceName = name;
    }

    public com.artica.telesalud.mirth.connect.webservice.SendEMRDocumentMessage getSendEMRDocumentMessagePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SendEMRDocumentMessagePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSendEMRDocumentMessagePort(endpoint);
    }

    public com.artica.telesalud.mirth.connect.webservice.SendEMRDocumentMessage getSendEMRDocumentMessagePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.artica.telesalud.mirth.connect.webservice.SendEMRDocumentMessagePortBindingStub _stub = new com.artica.telesalud.mirth.connect.webservice.SendEMRDocumentMessagePortBindingStub(portAddress, this);
            _stub.setPortName(getSendEMRDocumentMessagePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSendEMRDocumentMessagePortEndpointAddress(java.lang.String address) {
        SendEMRDocumentMessagePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(@SuppressWarnings("rawtypes") Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.artica.telesalud.mirth.connect.webservice.SendEMRDocumentMessage.class.isAssignableFrom(serviceEndpointInterface)) {
                com.artica.telesalud.mirth.connect.webservice.SendEMRDocumentMessagePortBindingStub _stub = new com.artica.telesalud.mirth.connect.webservice.SendEMRDocumentMessagePortBindingStub(new java.net.URL(SendEMRDocumentMessagePort_address), this);
                _stub.setPortName(getSendEMRDocumentMessagePortWSDDServiceName());
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
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, @SuppressWarnings("rawtypes") Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("SendEMRDocumentMessagePort".equals(inputPortName)) {
            return getSendEMRDocumentMessagePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webservice.connect.mirth.telesalud.artica.com/", "SendEMRDocumentMessageService");
    }

    @SuppressWarnings("rawtypes")
	private java.util.HashSet ports = null;

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webservice.connect.mirth.telesalud.artica.com/", "SendEMRDocumentMessagePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SendEMRDocumentMessagePort".equals(portName)) {
            setSendEMRDocumentMessagePortEndpointAddress(address);
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
