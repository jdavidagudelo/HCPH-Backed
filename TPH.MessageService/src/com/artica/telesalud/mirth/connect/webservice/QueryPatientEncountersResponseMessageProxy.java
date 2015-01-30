package com.artica.telesalud.mirth.connect.webservice;

public class QueryPatientEncountersResponseMessageProxy implements com.artica.telesalud.mirth.connect.webservice.QueryPatientEncountersResponseMessage {
  private String _endpoint = null;
  private com.artica.telesalud.mirth.connect.webservice.QueryPatientEncountersResponseMessage queryPatientEncountersResponseMessage = null;
  
  public QueryPatientEncountersResponseMessageProxy() {
    _initQueryPatientEncountersResponseMessageProxy();
  }
  
  public QueryPatientEncountersResponseMessageProxy(String endpoint) {
    _endpoint = endpoint;
    _initQueryPatientEncountersResponseMessageProxy();
  }
  
  private void _initQueryPatientEncountersResponseMessageProxy() {
    try {
      queryPatientEncountersResponseMessage = (new com.artica.telesalud.mirth.connect.webservice.QueryPatientEncountersResponseMessageServiceLocator()).getQueryPatientEncountersResponseMessagePort();
      if (queryPatientEncountersResponseMessage != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)queryPatientEncountersResponseMessage)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)queryPatientEncountersResponseMessage)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (queryPatientEncountersResponseMessage != null)
      ((javax.xml.rpc.Stub)queryPatientEncountersResponseMessage)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.artica.telesalud.mirth.connect.webservice.QueryPatientEncountersResponseMessage getQueryPatientEncountersResponseMessage() {
    if (queryPatientEncountersResponseMessage == null)
      _initQueryPatientEncountersResponseMessageProxy();
    return queryPatientEncountersResponseMessage;
  }
  
  public java.lang.String queryPatientEncountersResponseMessage(java.lang.String xmlString) throws java.rmi.RemoteException{
    if (queryPatientEncountersResponseMessage == null)
      _initQueryPatientEncountersResponseMessageProxy();
    return queryPatientEncountersResponseMessage.queryPatientEncountersResponseMessage(xmlString);
  }
  
  
}