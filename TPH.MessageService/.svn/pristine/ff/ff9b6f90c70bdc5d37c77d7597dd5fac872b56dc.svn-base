package com.artica.telesalud.mirth.connect.webservice;

public class QueryEncounterResponseMessageProxy implements com.artica.telesalud.mirth.connect.webservice.QueryEncounterResponseMessage {
  private String _endpoint = null;
  private com.artica.telesalud.mirth.connect.webservice.QueryEncounterResponseMessage queryEncounterResponseMessage = null;
  
  public QueryEncounterResponseMessageProxy() {
    _initQueryEncounterResponseMessageProxy();
  }
  
  public QueryEncounterResponseMessageProxy(String endpoint) {
    _endpoint = endpoint;
    _initQueryEncounterResponseMessageProxy();
  }
  
  private void _initQueryEncounterResponseMessageProxy() {
    try {
      queryEncounterResponseMessage = (new com.artica.telesalud.mirth.connect.webservice.QueryEncounterResponseMessageServiceLocator()).getQueryEncounterResponseMessagePort();
      if (queryEncounterResponseMessage != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)queryEncounterResponseMessage)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)queryEncounterResponseMessage)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (queryEncounterResponseMessage != null)
      ((javax.xml.rpc.Stub)queryEncounterResponseMessage)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.artica.telesalud.mirth.connect.webservice.QueryEncounterResponseMessage getQueryEncounterResponseMessage() {
    if (queryEncounterResponseMessage == null)
      _initQueryEncounterResponseMessageProxy();
    return queryEncounterResponseMessage;
  }
  
  public java.lang.String queryEncounterResponseMessage(java.lang.String xmlString) throws java.rmi.RemoteException{
    if (queryEncounterResponseMessage == null)
      _initQueryEncounterResponseMessageProxy();
    return queryEncounterResponseMessage.queryEncounterResponseMessage(xmlString);
  }
  
  
}