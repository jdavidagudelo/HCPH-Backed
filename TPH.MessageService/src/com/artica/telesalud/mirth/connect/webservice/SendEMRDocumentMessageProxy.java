package com.artica.telesalud.mirth.connect.webservice;

public class SendEMRDocumentMessageProxy implements com.artica.telesalud.mirth.connect.webservice.SendEMRDocumentMessage {
  private String _endpoint = null;
  private com.artica.telesalud.mirth.connect.webservice.SendEMRDocumentMessage sendEMRDocumentMessage = null;
  
  public SendEMRDocumentMessageProxy() {
    _initSendEMRDocumentMessageProxy();
  }
  
  public SendEMRDocumentMessageProxy(String endpoint) {
    _endpoint = endpoint;
    _initSendEMRDocumentMessageProxy();
  }
  
  private void _initSendEMRDocumentMessageProxy() {
    try {
      sendEMRDocumentMessage = (new com.artica.telesalud.mirth.connect.webservice.SendEMRDocumentMessageServiceLocator()).getSendEMRDocumentMessagePort();
      if (sendEMRDocumentMessage != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sendEMRDocumentMessage)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sendEMRDocumentMessage)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sendEMRDocumentMessage != null)
      ((javax.xml.rpc.Stub)sendEMRDocumentMessage)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.artica.telesalud.mirth.connect.webservice.SendEMRDocumentMessage getSendEMRDocumentMessage() {
    if (sendEMRDocumentMessage == null)
      _initSendEMRDocumentMessageProxy();
    return sendEMRDocumentMessage;
  }
  
  public java.lang.String sendEMRDocument(java.lang.String xmlString) throws java.rmi.RemoteException{
    if (sendEMRDocumentMessage == null)
      _initSendEMRDocumentMessageProxy();
    return sendEMRDocumentMessage.sendEMRDocument(xmlString);
  }
  
  
}