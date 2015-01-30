package com.artica.telesalud.mirth.connect.webservice;

public class DocumentCancelNotificationMessageProxy implements com.artica.telesalud.mirth.connect.webservice.DocumentCancelNotificationMessage {
  private String _endpoint = null;
  private com.artica.telesalud.mirth.connect.webservice.DocumentCancelNotificationMessage documentCancelNotificationMessage = null;
  
  public DocumentCancelNotificationMessageProxy() {
    _initDocumentCancelNotificationMessageProxy();
  }
  
  public DocumentCancelNotificationMessageProxy(String endpoint) {
    _endpoint = endpoint;
    _initDocumentCancelNotificationMessageProxy();
  }
  
  private void _initDocumentCancelNotificationMessageProxy() {
    try {
      documentCancelNotificationMessage = (new com.artica.telesalud.mirth.connect.webservice.DocumentCancelNotificationMessageServiceLocator()).getDocumentCancelNotificationMessagePort();
      if (documentCancelNotificationMessage != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)documentCancelNotificationMessage)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)documentCancelNotificationMessage)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (documentCancelNotificationMessage != null)
      ((javax.xml.rpc.Stub)documentCancelNotificationMessage)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.artica.telesalud.mirth.connect.webservice.DocumentCancelNotificationMessage getDocumentCancelNotificationMessage() {
    if (documentCancelNotificationMessage == null)
      _initDocumentCancelNotificationMessageProxy();
    return documentCancelNotificationMessage;
  }
  
  public java.lang.String documentCancelNotification(java.lang.String xmlString) throws java.rmi.RemoteException{
    if (documentCancelNotificationMessage == null)
      _initDocumentCancelNotificationMessageProxy();
    return documentCancelNotificationMessage.documentCancelNotification(xmlString);
  }
  
  
}