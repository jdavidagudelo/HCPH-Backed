package com.artica.telesalud.tph.message.service.properties;

import java.util.ResourceBundle;

public class DominioAMPProperties {
	private DominioAMPProperties() {
	}

	private static final DominioAMPProperties INSTANCE = new DominioAMPProperties();
	public static final ResourceBundle CONFIGURATION_RESOURCES = ResourceBundle
			.getBundle("com.artica.telesalud.tph.message.service.properties.DominioAMP");

	public static DominioAMPProperties getInstance() {
		return INSTANCE;
	}

	public Integer getTeleasistenciaCreatorDefaultId() {
		return Integer.parseInt(CONFIGURATION_RESOURCES
				.getString("teleasistencia.creator.default.id"));
	}

	public Integer getCoEduUdeaAmpHistoryId() {
		return Integer.parseInt(CONFIGURATION_RESOURCES
				.getString("co.edu.udea.amp.history.id"));
	}
	public Integer getConceptXmlAmpId()
	{
		return Integer.parseInt(CONFIGURATION_RESOURCES.getString("concept.xml.amp.id"));
	}
	public String getTeleasistenciaId() {
		return CONFIGURATION_RESOURCES.getString("teleasistencia.id");
	}
	public Integer getConceptClassAmpId(){
		return Integer.parseInt(CONFIGURATION_RESOURCES.getString("concept.class.amp.id"));
	}
	public String getTphGwtWarFolderPath(){
		return CONFIGURATION_RESOURCES.getString("tph.gwt.war.folder.path");
	}
	public String getTphGwtCdaFolderName(){
		return CONFIGURATION_RESOURCES.getString("tph.gwt.cda.folder.name");
	}
	public String getFileExtensionSeparator(){
		return CONFIGURATION_RESOURCES.getString("file.extension.separator");
	}
	public String getFileCdaExtension(){
		return CONFIGURATION_RESOURCES.getString("file.cda.extension");
	}
}
