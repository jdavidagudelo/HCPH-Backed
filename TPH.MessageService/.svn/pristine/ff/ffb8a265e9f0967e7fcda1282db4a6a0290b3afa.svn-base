/**
 * QueryPatientAMPMessageServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.artica.telesalud.mirth.connect.webservice;

public class QueryPatientAMPMessageServiceLocator extends org.apache.axis.client.Service implements com.artica.telesalud.mirth.connect.webservice.QueryPatientAMPMessageService {

    public QueryPatientAMPMessageServiceLocator() {
    }


    public QueryPatientAMPMessageServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public QueryPatientAMPMessageServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for QueryPatientAMPMessagePort
    private java.lang.String QueryPatientAMPMessagePort_address = "http://172.21.0.174:8082/services/queryPatientAMP?wsdl";

    public java.lang.String getQueryPatientAMPMessagePortAddress() {
        return QueryPatientAMPMessagePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String QueryPatientAMPMessagePortWSDDServiceName = "QueryPatientAMPMessagePort";

    public java.lang.String getQueryPatientAMPMessagePortWSDDServiceName() {
        return QueryPatientAMPMessagePortWSDDServiceName;
    }

    public void setQueryPatientAMPMessagePortWSDDServiceName(java.lang.String name) {
        QueryPatientAMPMessagePortWSDDServiceName = name;
    }

    public com.artica.telesalud.mirth.connect.webservice.QueryPatientAMPMessage getQueryPatientAMPMessagePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(QueryPatientAMPMessagePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getQueryPatientAMPMessagePort(endpoint);
    }

    public com.artica.telesalud.mirth.connect.webservice.QueryPatientAMPMessage getQueryPatientAMPMessagePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.artica.telesalud.mirth.connect.webservice.QueryPatientAMPMessagePortBindingStub _stub = new com.artica.telesalud.mirth.connect.webservice.QueryPatientAMPMessagePortBindingStub(portAddress, this);
            _stub.setPortName(getQueryPatientAMPMessagePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setQueryPatientAMPMessagePortEndpointAddress(java.lang.String address) {
        QueryPatientAMPMessagePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.artica.telesalud.mirth.connect.webservice.QueryPatientAMPMessage.class.isAssignableFrom(serviceEndpointInterface)) {
                com.artica.telesalud.mirth.connect.webservice.QueryPatientAMPMessagePortBindingStub _stub = new com.artica.telesalud.mirth.connect.webservice.QueryPatientAMPMessagePortBindingStub(new java.net.URL(QueryPatientAMPMessagePort_address), this);
                _stub.setPortName(getQueryPatientAMPMessagePortWSDDServiceName());
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
        if ("QueryPatientAMPMessagePort".equals(inputPortName)) {
            return getQueryPatientAMPMessagePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webservice.connect.mirth.telesalud.artica.com/", "QueryPatientAMPMessageService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webservice.connect.mirth.telesalud.artica.com/", "QueryPatientAMPMessagePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("QueryPatientAMPMessagePort".equals(portName)) {
            setQueryPatientAMPMessagePortEndpointAddress(address);
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
