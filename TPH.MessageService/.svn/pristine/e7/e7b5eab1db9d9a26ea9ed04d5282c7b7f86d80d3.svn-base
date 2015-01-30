package com.artica.telesalud.mirth.connect.webservice;

public class SavePatientIndexMessageProxy implements com.artica.telesalud.mirth.connect.webservice.SavePatientIndexMessage {
  private String _endpoint = null;
  private com.artica.telesalud.mirth.connect.webservice.SavePatientIndexMessage savePatientIndexMessage = null;
  
  public SavePatientIndexMessageProxy() {
    _initSavePatientIndexMessageProxy();
  }
  
  public SavePatientIndexMessageProxy(String endpoint) {
    _endpoint = endpoint;
    _initSavePatientIndexMessageProxy();
  }
  
  private void _initSavePatientIndexMessageProxy() {
    try {
      savePatientIndexMessage = (new com.artica.telesalud.mirth.connect.webservice.SavePatientIndexMessageServiceLocator()).getSavePatientIndexMessagePort();
      if (savePatientIndexMessage != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)savePatientIndexMessage)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)savePatientIndexMessage)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (savePatientIndexMessage != null)
      ((javax.xml.rpc.Stub)savePatientIndexMessage)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.artica.telesalud.mirth.connect.webservice.SavePatientIndexMessage getSavePatientIndexMessage() {
    if (savePatientIndexMessage == null)
      _initSavePatientIndexMessageProxy();
    return savePatientIndexMessage;
  }
  
  public java.lang.String savePatientIndex(java.lang.String xmlString) throws java.rmi.RemoteException{
    if (savePatientIndexMessage == null)
      _initSavePatientIndexMessageProxy();
    return savePatientIndexMessage.savePatientIndex(xmlString);
  }
  
  
}