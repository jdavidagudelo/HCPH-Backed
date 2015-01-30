package com.artica.telesalud.mirth.connect.webservice;

public class QueryPatientAMPMessageProxy implements com.artica.telesalud.mirth.connect.webservice.QueryPatientAMPMessage {
  private String _endpoint = null;
  private com.artica.telesalud.mirth.connect.webservice.QueryPatientAMPMessage queryPatientAMPMessage = null;
  
  public QueryPatientAMPMessageProxy() {
    _initQueryPatientAMPMessageProxy();
  }
  
  public QueryPatientAMPMessageProxy(String endpoint) {
    _endpoint = endpoint;
    _initQueryPatientAMPMessageProxy();
  }
  
  private void _initQueryPatientAMPMessageProxy() {
    try {
      queryPatientAMPMessage = (new com.artica.telesalud.mirth.connect.webservice.QueryPatientAMPMessageServiceLocator()).getQueryPatientAMPMessagePort();
      if (queryPatientAMPMessage != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)queryPatientAMPMessage)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)queryPatientAMPMessage)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (queryPatientAMPMessage != null)
      ((javax.xml.rpc.Stub)queryPatientAMPMessage)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.artica.telesalud.mirth.connect.webservice.QueryPatientAMPMessage getQueryPatientAMPMessage() {
    if (queryPatientAMPMessage == null)
      _initQueryPatientAMPMessageProxy();
    return queryPatientAMPMessage;
  }
  
  public java.lang.String queryPatientAMP(java.lang.String xmlString) throws java.rmi.RemoteException{
    if (queryPatientAMPMessage == null)
      _initQueryPatientAMPMessageProxy();
    return queryPatientAMPMessage.queryPatientAMP(xmlString);
  }
  
  
}