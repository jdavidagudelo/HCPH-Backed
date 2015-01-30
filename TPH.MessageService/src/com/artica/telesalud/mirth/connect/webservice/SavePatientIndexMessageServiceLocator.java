/**
 * SavePatientIndexMessageServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.artica.telesalud.mirth.connect.webservice;

public class SavePatientIndexMessageServiceLocator extends org.apache.axis.client.Service implements com.artica.telesalud.mirth.connect.webservice.SavePatientIndexMessageService {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SavePatientIndexMessageServiceLocator() {
    }


    public SavePatientIndexMessageServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SavePatientIndexMessageServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SavePatientIndexMessagePort
    private java.lang.String SavePatientIndexMessagePort_address = "http://172.21.0.174:8081/services/savePatientIndex?wsdl";

    public java.lang.String getSavePatientIndexMessagePortAddress() {
        return SavePatientIndexMessagePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SavePatientIndexMessagePortWSDDServiceName = "SavePatientIndexMessagePort";

    public java.lang.String getSavePatientIndexMessagePortWSDDServiceName() {
        return SavePatientIndexMessagePortWSDDServiceName;
    }

    public void setSavePatientIndexMessagePortWSDDServiceName(java.lang.String name) {
        SavePatientIndexMessagePortWSDDServiceName = name;
    }

    public com.artica.telesalud.mirth.connect.webservice.SavePatientIndexMessage getSavePatientIndexMessagePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SavePatientIndexMessagePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSavePatientIndexMessagePort(endpoint);
    }

    public com.artica.telesalud.mirth.connect.webservice.SavePatientIndexMessage getSavePatientIndexMessagePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.artica.telesalud.mirth.connect.webservice.SavePatientIndexMessagePortBindingStub _stub = new com.artica.telesalud.mirth.connect.webservice.SavePatientIndexMessagePortBindingStub(portAddress, this);
            _stub.setPortName(getSavePatientIndexMessagePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSavePatientIndexMessagePortEndpointAddress(java.lang.String address) {
        SavePatientIndexMessagePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    @SuppressWarnings("rawtypes")
	public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.artica.telesalud.mirth.connect.webservice.SavePatientIndexMessage.class.isAssignableFrom(serviceEndpointInterface)) {
                com.artica.telesalud.mirth.connect.webservice.SavePatientIndexMessagePortBindingStub _stub = new com.artica.telesalud.mirth.connect.webservice.SavePatientIndexMessagePortBindingStub(new java.net.URL(SavePatientIndexMessagePort_address), this);
                _stub.setPortName(getSavePatientIndexMessagePortWSDDServiceName());
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
    @SuppressWarnings("rawtypes")
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("SavePatientIndexMessagePort".equals(inputPortName)) {
            return getSavePatientIndexMessagePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webservice.connect.mirth.telesalud.artica.com/", "SavePatientIndexMessageService");
    }

    @SuppressWarnings("rawtypes")
	private java.util.HashSet ports = null;

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webservice.connect.mirth.telesalud.artica.com/", "SavePatientIndexMessagePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SavePatientIndexMessagePort".equals(portName)) {
            setSavePatientIndexMessagePortEndpointAddress(address);
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
