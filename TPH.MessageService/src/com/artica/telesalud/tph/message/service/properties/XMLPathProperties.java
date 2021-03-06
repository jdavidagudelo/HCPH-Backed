package com.artica.telesalud.tph.message.service.properties;

import java.util.ResourceBundle;

public class XMLPathProperties {
	private XMLPathProperties()
	{	
	}
	private static final XMLPathProperties INSTANCE = new XMLPathProperties();
	public static final ResourceBundle CONFIGURATION_RESOURCES = ResourceBundle
			.getBundle("com.artica.telesalud.tph.message.service.properties.XMLPath");
	public static XMLPathProperties getInstance()
	{
		return INSTANCE;
	}

	public String getXmlHl7DateDefault()
	{
		return CONFIGURATION_RESOURCES.getString("xml.hl7.date.default");
	}
	public String getXmlPdfTempFilePath()
	{
		return CONFIGURATION_RESOURCES.getString("xml.pdf.temp.file.path");
	}
	public String getXmlRegistroPrefix()
	{
		return CONFIGURATION_RESOURCES.getString("xml.registro.prefix");
	}
	public String getXmlPhonePrefix()
	{
		return CONFIGURATION_RESOURCES.getString("xml.phone.prefix");
	}
	public String getXmlTemplateRCMR_IN000001UV01FilePath()
	{
		return CONFIGURATION_RESOURCES.getString("xml.template.RCMR_IN000001UV01.file.path");
	}
	public String getHibernateTphCfgXml()
	{
		return CONFIGURATION_RESOURCES.getString("hibernate.tph.cfg.xml");
	}
	public String getXmlTemplateRCMR_IN000002UV01FilePath()
	{
		return CONFIGURATION_RESOURCES.getString("xml.template.RCMR_IN000002UV01.file.path");
	}
	public String getXmlEncodeCharsetUTF8()
	{
		return CONFIGURATION_RESOURCES.getString("xml.encode.charset.UTF8");
	}
	
	public String getXmlTemplateDateFormat()
	{
		return CONFIGURATION_RESOURCES.getString("xml.template.date.format");
	}
	public String getXmlTemplateClinicalDocumentHCPHFilePath()
	{
		return CONFIGURATION_RESOURCES.getString("xml.template.Clinical_Document_HCPH.file.path");
	}
	public String getXmlTemplateBirthDateFormat()
	{
		return CONFIGURATION_RESOURCES.getString("xml.template.birth.date.format");
	}
	public String getXmlTemplateRCMR_IN000032UV01FilePath()
	{
		return CONFIGURATION_RESOURCES.getString("xml.template.RCMR_IN000032UV01.file.path");
	}
	public String getXmlTemplateRCMR_IN000031UV01FilePath()
	{
		return CONFIGURATION_RESOURCES.getString("xml.template.RCMR_IN000031UV01.file.path");
	}
	public String getXmlTemplateUnknownGenderValue()
	{
		return CONFIGURATION_RESOURCES.getString("xml.template.unknown.gender.value");
	}
	public String getXmlTemplateRootFilePath()
	{
		return CONFIGURATION_RESOURCES.getString("xml.template.root.file.path");
	}
	public String getXmlTemplatePRPA_IN900350UV02FilePath()
	{
		return CONFIGURATION_RESOURCES.getString("xml.template.PRPA_IN900350UV02.file.path");
	}
	public String getXmlEncounterStateEnded()
	{
		return CONFIGURATION_RESOURCES.getString("xml.encounter.state.ended");
	}
	public String getXmlEncouterDateEndNotPresent(){
		return CONFIGURATION_RESOURCES.getString("xml.encouter.date.end.NotPresent");
	}
	public String getXpathNameSpaceHl7Name() {
		return CONFIGURATION_RESOURCES.getString("xpath.name.space.hl7.name");
	}

	public String getXpathNameSpaceHl7Url() {
		return CONFIGURATION_RESOURCES.getString("xpath.name.space.hl7.url");
	}
	public String getXmlXslCDAFilePath(){
		return CONFIGURATION_RESOURCES.getString("xml.xsl.CDA.file.path");
	}
	public String getXmlXslRootFilePath(){
		return CONFIGURATION_RESOURCES.getString("xml.xsl.root.file.path");
	}

	public String getXmlTemplateRCMR_IN000019UV01FilePath() {
		return CONFIGURATION_RESOURCES.getString("xml.template.RCMR_IN000019UV01.file.path");
	}
}
