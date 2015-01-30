/**
 * QueryPatientEncountersResponseMessageServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.artica.telesalud.mirth.connect.webservice;

public class QueryPatientEncountersResponseMessageServiceLocator extends org.apache.axis.client.Service implements com.artica.telesalud.mirth.connect.webservice.QueryPatientEncountersResponseMessageService {

    public QueryPatientEncountersResponseMessageServiceLocator() {
    }


    public QueryPatientEncountersResponseMessageServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public QueryPatientEncountersResponseMessageServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for QueryPatientEncountersResponseMessagePort
    private java.lang.String QueryPatientEncountersResponseMessagePort_address = "http://172.21.0.174:8085/services/queryPatientEncountersResponse";

    public java.lang.String getQueryPatientEncountersResponseMessagePortAddress() {
        return QueryPatientEncountersResponseMessagePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String QueryPatientEncountersResponseMessagePortWSDDServiceName = "QueryPatientEncountersResponseMessagePort";

    public java.lang.String getQueryPatientEncountersResponseMessagePortWSDDServiceName() {
        return QueryPatientEncountersResponseMessagePortWSDDServiceName;
    }

    public void setQueryPatientEncountersResponseMessagePortWSDDServiceName(java.lang.String name) {
        QueryPatientEncountersResponseMessagePortWSDDServiceName = name;
    }

    public com.artica.telesalud.mirth.connect.webservice.QueryPatientEncountersResponseMessage getQueryPatientEncountersResponseMessagePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(QueryPatientEncountersResponseMessagePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getQueryPatientEncountersResponseMessagePort(endpoint);
    }

    public com.artica.telesalud.mirth.connect.webservice.QueryPatientEncountersResponseMessage getQueryPatientEncountersResponseMessagePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.artica.telesalud.mirth.connect.webservice.QueryPatientEncountersResponseMessagePortBindingStub _stub = new com.artica.telesalud.mirth.connect.webservice.QueryPatientEncountersResponseMessagePortBindingStub(portAddress, this);
            _stub.setPortName(getQueryPatientEncountersResponseMessagePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setQueryPatientEncountersResponseMessagePortEndpointAddress(java.lang.String address) {
        QueryPatientEncountersResponseMessagePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.artica.telesalud.mirth.connect.webservice.QueryPatientEncountersResponseMessage.class.isAssignableFrom(serviceEndpointInterface)) {
                com.artica.telesalud.mirth.connect.webservice.QueryPatientEncountersResponseMessagePortBindingStub _stub = new com.artica.telesalud.mirth.connect.webservice.QueryPatientEncountersResponseMessagePortBindingStub(new java.net.URL(QueryPatientEncountersResponseMessagePort_address), this);
                _stub.setPortName(getQueryPatientEncountersResponseMessagePortWSDDServiceName());
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
        if ("QueryPatientEncountersResponseMessagePort".equals(inputPortName)) {
            return getQueryPatientEncountersResponseMessagePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webservice.connect.mirth.telesalud.artica.com/", "QueryPatientEncountersResponseMessageService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webservice.connect.mirth.telesalud.artica.com/", "QueryPatientEncountersResponseMessagePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("QueryPatientEncountersResponseMessagePort".equals(portName)) {
            setQueryPatientEncountersResponseMessagePortEndpointAddress(address);
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
