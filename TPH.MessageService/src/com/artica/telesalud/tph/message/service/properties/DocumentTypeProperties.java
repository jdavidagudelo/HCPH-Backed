package com.artica.telesalud.tph.message.service.properties;

import java.util.ResourceBundle;

public class DocumentTypeProperties {
	private DocumentTypeProperties() {

	}

	private static final DocumentTypeProperties INSTANCE = new DocumentTypeProperties();

	public static DocumentTypeProperties getInstance() {
		return INSTANCE;
	}

	public static final ResourceBundle CONFIGURATION_RESOURCES = ResourceBundle
			.getBundle("com.artica.telesalud.tph.message.service.properties.DocumentType");

	public String getDocumentTypeCcCode() {
		return CONFIGURATION_RESOURCES.getString("document.type.cc.code");
	}

	public String getDocumentTypeCeCode() {
		return CONFIGURATION_RESOURCES.getString("document.type.ce.code");
	}

	public String getDocumentTypePaCode() {
		return CONFIGURATION_RESOURCES.getString("document.type.pa.code");
	}

	public String getDocumentTypeRcCode() {
		return CONFIGURATION_RESOURCES.getString("document.type.rc.code");
	}

	public String getDocumentTypeTiCode() {
		return CONFIGURATION_RESOURCES.getString("document.type.ti.code");
	}

	public String getDocumentTypeAsCode() {
		return CONFIGURATION_RESOURCES.getString("document.type.as.code");
	}

	public String getDocumentTypeMsCode() {
		return CONFIGURATION_RESOURCES.getString("document.type.ms.code");
	}

	public String getDocumentTypeNuCode() {
		return CONFIGURATION_RESOURCES.getString("document.type.nu.code");
	}

	public String getDocumentTypeCcId() {
		return CONFIGURATION_RESOURCES.getString("document.type.cc.id");
	}

	public String getDocumentTypeCeId() {
		return CONFIGURATION_RESOURCES.getString("document.type.ce.id");
	}

	public String getDocumentTypePaId() {
		return CONFIGURATION_RESOURCES.getString("document.type.pa.id");
	}

	public String getDocumentTypeRcId() {
		return CONFIGURATION_RESOURCES.getString("document.type.rc.id");
	}

	public String getDocumentTypeTiId() {
		return CONFIGURATION_RESOURCES.getString("document.type.ti.id");
	}

	public String getDocumentTypeAsId() {
		return CONFIGURATION_RESOURCES.getString("document.type.as.id");
	}

	public String getDocumentTypeMsId() {
		return CONFIGURATION_RESOURCES.getString("document.type.ms.id");
	}

	public String getDocumentTypeNuId() {
		return CONFIGURATION_RESOURCES.getString("document.type.nu.id");
	}
}
