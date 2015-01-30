/**
 * QueryEncounterResponseMessageServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.artica.telesalud.mirth.connect.webservice;

public class QueryEncounterResponseMessageServiceLocator extends org.apache.axis.client.Service implements com.artica.telesalud.mirth.connect.webservice.QueryEncounterResponseMessageService {

    public QueryEncounterResponseMessageServiceLocator() {
    }


    public QueryEncounterResponseMessageServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public QueryEncounterResponseMessageServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for QueryEncounterResponseMessagePort
    private java.lang.String QueryEncounterResponseMessagePort_address = "http://172.21.0.174:8087/services/queryEncounterResponse?wsdl";

    public java.lang.String getQueryEncounterResponseMessagePortAddress() {
        return QueryEncounterResponseMessagePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String QueryEncounterResponseMessagePortWSDDServiceName = "QueryEncounterResponseMessagePort";

    public java.lang.String getQueryEncounterResponseMessagePortWSDDServiceName() {
        return QueryEncounterResponseMessagePortWSDDServiceName;
    }

    public void setQueryEncounterResponseMessagePortWSDDServiceName(java.lang.String name) {
        QueryEncounterResponseMessagePortWSDDServiceName = name;
    }

    public com.artica.telesalud.mirth.connect.webservice.QueryEncounterResponseMessage getQueryEncounterResponseMessagePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(QueryEncounterResponseMessagePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getQueryEncounterResponseMessagePort(endpoint);
    }

    public com.artica.telesalud.mirth.connect.webservice.QueryEncounterResponseMessage getQueryEncounterResponseMessagePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.artica.telesalud.mirth.connect.webservice.QueryEncounterResponseMessagePortBindingStub _stub = new com.artica.telesalud.mirth.connect.webservice.QueryEncounterResponseMessagePortBindingStub(portAddress, this);
            _stub.setPortName(getQueryEncounterResponseMessagePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setQueryEncounterResponseMessagePortEndpointAddress(java.lang.String address) {
        QueryEncounterResponseMessagePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.artica.telesalud.mirth.connect.webservice.QueryEncounterResponseMessage.class.isAssignableFrom(serviceEndpointInterface)) {
                com.artica.telesalud.mirth.connect.webservice.QueryEncounterResponseMessagePortBindingStub _stub = new com.artica.telesalud.mirth.connect.webservice.QueryEncounterResponseMessagePortBindingStub(new java.net.URL(QueryEncounterResponseMessagePort_address), this);
                _stub.setPortName(getQueryEncounterResponseMessagePortWSDDServiceName());
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
        if ("QueryEncounterResponseMessagePort".equals(inputPortName)) {
            return getQueryEncounterResponseMessagePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webservice.connect.mirth.telesalud.artica.com/", "QueryEncounterResponseMessageService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webservice.connect.mirth.telesalud.artica.com/", "QueryEncounterResponseMessagePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("QueryEncounterResponseMessagePort".equals(portName)) {
            setQueryEncounterResponseMessagePortEndpointAddress(address);
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
