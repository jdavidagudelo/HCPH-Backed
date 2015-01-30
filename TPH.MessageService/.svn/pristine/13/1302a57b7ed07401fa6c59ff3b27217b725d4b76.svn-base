/**
 * SavePatientIndexMessagePortBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.artica.telesalud.mirth.connect.webservice;

public class SavePatientIndexMessagePortBindingStub extends
		org.apache.axis.client.Stub implements
		com.artica.telesalud.mirth.connect.webservice.SavePatientIndexMessage {
	/**
	 * Nombre de usuario requerido por el servidor que responde las solicitudes realizadas por
	 * los clientes de este servicio web.
	 */
	private String username;
	/**
	 * Clave asociado con el nombre de usuario requerido por el servidor que responde las solicitudes 
	 * de los clientes de este servicio web.
	 */
	private String password;
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/***
	 * private java.util.Vector cachedSerClasses = new java.util.Vector();
	 * private java.util.Vector cachedSerQNames = new java.util.Vector();
	 * private java.util.Vector cachedSerFactories = new java.util.Vector();
	 * private java.util.Vector cachedDeserFactories = new java.util.Vector();
	 */
	static org.apache.axis.description.OperationDesc[] _operations;

	static {
		_operations = new org.apache.axis.description.OperationDesc[1];
		_initOperationDesc1();
	}

	private static void _initOperationDesc1() {
		org.apache.axis.description.OperationDesc oper;
		org.apache.axis.description.ParameterDesc param;
		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("savePatientIndex");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("", "xmlString"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName(
						"http://www.w3.org/2001/XMLSchema", "string"),
				java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName(
				"http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(java.lang.String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[0] = oper;

	}

	public SavePatientIndexMessagePortBindingStub()
			throws org.apache.axis.AxisFault {
		this(null);
	}

	public SavePatientIndexMessagePortBindingStub(java.net.URL endpointURL,
			javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
		this(service);
		super.cachedEndpoint = endpointURL;
	}

	public SavePatientIndexMessagePortBindingStub(javax.xml.rpc.Service service)
			throws org.apache.axis.AxisFault {
		if (service == null) {
			super.service = new org.apache.axis.client.Service();
		} else {
			super.service = service;
		}
		((org.apache.axis.client.Service) super.service)
				.setTypeMappingVersion("1.2");
	}

	/**
	 * Metodo utilizado para especificar los parametros incluidos dentro del encabezado del request
	 * SOAP que se desea realizar.
	 * @return call que contiene la informacion que se incluira dentro del mensaje SOAP.
	 * @throws java.rmi.RemoteException
	 */
	protected org.apache.axis.client.Call createCall()
			throws java.rmi.RemoteException {
		try {
			org.apache.axis.client.Call call = super._createCall();
			call.setPassword(password);
			call.setUsername(username);
			if (super.maintainSessionSet) {
				call.setMaintainSession(super.maintainSession);
			}
			if (super.cachedUsername != null) {
				call.setUsername(super.cachedUsername);
			}
			if (super.cachedPassword != null) {
				call.setPassword(super.cachedPassword);
			}
			if (super.cachedEndpoint != null) {
				call.setTargetEndpointAddress(super.cachedEndpoint);
			}
			if (super.cachedTimeout != null) {
				call.setTimeout(super.cachedTimeout);
			}
			if (super.cachedPortName != null) {
				call.setPortName(super.cachedPortName);
			}
			java.util.Enumeration<?> keys = super.cachedProperties.keys();
			while (keys.hasMoreElements()) {
				java.lang.String key = (java.lang.String) keys.nextElement();
				call.setProperty(key, super.cachedProperties.get(key));
			}
			return call;
		} catch (java.lang.Throwable _t) {
			throw new org.apache.axis.AxisFault(
					"Failure trying to get the Call object", _t);
		}
	}

	public java.lang.String savePatientIndex(java.lang.String xmlString)
			throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call call = createCall();
		call.setOperation(_operations[0]);
		call.setUseSOAPAction(true);
		call.setSOAPActionURI("savePatientIndex");
		call.setEncodingStyle(null);
		call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR,
				Boolean.FALSE);
		call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS,
				Boolean.FALSE);
		call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		call.setOperationName(new javax.xml.namespace.QName(
				"http://webservice.connect.mirth.telesalud.artica.com/",
				"savePatientIndex"));

		setRequestHeaders(call);
		setAttachments(call);
		try {
			java.lang.Object _resp = call
					.invoke(new java.lang.Object[] { xmlString });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(call);
				try {
					return (java.lang.String) _resp;
				} catch (java.lang.Exception _exception) {
					return (java.lang.String) org.apache.axis.utils.JavaUtils
							.convert(_resp, java.lang.String.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

}
