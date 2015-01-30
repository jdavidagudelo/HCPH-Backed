package com.artica.telesalud.tph.message.service.interactions;

import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;

import com.artica.telesalud.tph.message.service.properties.ClinicalDocumentHCPHProperties;
/**
 * Clase utilizada para obtener los atributos y elementos del documento XML asociado a los CDA utilizando
 * consultas XPATH.
 * Juan David Agudelo Alvarez jdaaa2009@gmail.com
 *
 */
public class ClinicalDocumentHCPHXMLParser extends XMLParser {
	
	private ClinicalDocumentHCPHXMLParser()
	{
		
	}
	/**
	 * Instancia de la clase, utilizada para utilizar el patron singleton.
	 */
	private static final ClinicalDocumentHCPHXMLParser INSTANCE = new ClinicalDocumentHCPHXMLParser();
	public static ClinicalDocumentHCPHXMLParser getInstance()
	{
		return INSTANCE;
	}
	/**
	 * Atributo que contiene las rutas XPATH de cada uno de los atributos y elementos del documento
	 * XML requeridos para generar el documento CDA.
	 */
	private static final ClinicalDocumentHCPHProperties XPATH_PROPERTIES = ClinicalDocumentHCPHProperties
			.getInstance();

	/**
	 * 
	 * @param document
	 * @return
	 */
	public Attribute getClinicalDocumentTypeIdRoot(Document document) {
		return getAttribute(document,
				XPATH_PROPERTIES.getXpathClinicalDocumentTypeIdRoot());
	}

	/**
	 * 
	 * @param document
	 * @return
	 */
	public Attribute getClinicalDocumentTypeIdExtension(Document document) {
		return getAttribute(document,
				XPATH_PROPERTIES.getXpathClinicalDocumentTypeIdExtension());
	}

	/**
	 * Atributo que contiene el identificador unico del documento.
	 * @param document documento que para el cual se desea obtener el atributo.
	 * @return Atributo que contiene el identificador unico del documento.
	 */
	public Attribute getClinicalDocumentIdExtension(Document document) {
		return getAttribute(document,
				XPATH_PROPERTIES.getXpathClinicalDocumentIdExtension());
	}
	
	public Attribute getClinicalDocumentCodeCode(Document document) {
		return getAttribute(document,
				XPATH_PROPERTIES.getXpathClinicalDocumentCodeCode());
	}

	public Attribute getClinicalDocumentCodeDisplayName(Document document) {
		return getAttribute(document,
				XPATH_PROPERTIES.getXpathClinicalDocumentCodeDisplayName());
	}

	public Attribute getClinicalDocumentCodeCodeSystemName(Document document) {
		return getAttribute(document,
				XPATH_PROPERTIES.getXpathClinicalDocumentCodeCodeSystemName());
	}

	public Attribute getClinicalDocumentCodeCodeSystem(Document document) {
		return getAttribute(document,
				XPATH_PROPERTIES.getXpathClinicalDocumentCodeCodeSystem());
	}

	public List<Element> getClinicalDocumentCodeOriginalText(Document document) {
		return getElements(document,
				XPATH_PROPERTIES.getXpathClinicalDocumentCodeOriginalText());
	}

	public Attribute getClinicalDocumentEffectiveTimeValue(Document document) {
		return getAttribute(document,
				XPATH_PROPERTIES.getXpathClinicalDocumentEffectiveTimeValue());
	}

	public Attribute getClinicalDocumentConfidentialityCodeCode(
			Document document) {
		return getAttribute(document,
				XPATH_PROPERTIES
						.getXpathClinicalDocumentConfidentialityCodeCode());
	}

	public Attribute getClinicalDocumentConfidentialityCodeCodeSystem(
			Document document) {
		return getAttribute(
				document,
				XPATH_PROPERTIES
						.getXpathClinicalDocumentConfidentialityCodeCodeSystem());
	}

	public Attribute getClinicalDocumentRecordTargetTypeCode(Document document) {
		return getAttribute(document,
				XPATH_PROPERTIES.getXpathClinicalDocumentRecordTargetTypeCode());
	}

	public Attribute getClinicalDocumentRecordTargetContextControlCode(
			Document document) {
		return getAttribute(
				document,
				XPATH_PROPERTIES
						.getXpathClinicalDocumentRecordTargetContextControlCode());
	}

	public Attribute getClinicalDocumentRecordTargetPatientRoleClassCode(
			Document document) {
		return getAttribute(
				document,
				XPATH_PROPERTIES
						.getXpathClinicalDocumentRecordTargetPatientRoleClassCode());
	}

	public Attribute getClinicalDocumentRecordTargetPatientRoleIdExtension(
			Document document) {
		return getAttribute(
				document,
				XPATH_PROPERTIES
						.getXpathClinicalDocumentRecordTargetPatientRoleIdExtension());
	}

	public Attribute getClinicalDocumentRecordTargetPatientRolePatientIdExtension(
			Document document) {
		return getAttribute(
				document,
				XPATH_PROPERTIES
						.getXpathClinicalDocumentRecordTargetPatientRolePatientIdExtension());
	}

	public List<Element> getClinicalDocumentRecordTargetPatientRolePatientNameGiven(
			Document document) {
		return getElements(
				document,
				XPATH_PROPERTIES
						.getXpathClinicalDocumentRecordTargetPatientRolePatientNameGiven());
	}

	public List<Element> getClinicalDocumentRecordTargetPatientRolePatientNameFamily(
			Document document) {
		return getElements(
				document,
				XPATH_PROPERTIES
						.getXpathClinicalDocumentRecordTargetPatientRolePatientNameFamily());
	}

	public Attribute getClinicalDocumentRecordTargetPatientRolePatientAdministrativeGenderCodeCode(
			Document document) {
		return getAttribute(
				document,
				XPATH_PROPERTIES
						.getXpathClinicalDocumentRecordTargetPatientRolePatientAdministrativeGenderCodeCode());
	}

	public Attribute getClinicalDocumentRecordTargetPatientRolePatientAdministrativeGenderCodeCodeSystem(
			Document document) {
		return getAttribute(
				document,
				XPATH_PROPERTIES
						.getXpathClinicalDocumentRecordTargetPatientRolePatientAdministrativeGenderCodeCodeSystem());
	}

	public Attribute getClinicalDocumentRecordTargetPatientRolePatientAdministrativeGenderCodeCodeSystemName(
			Document document) {
		return getAttribute(
				document,
				XPATH_PROPERTIES
						.getXpathClinicalDocumentRecordTargetPatientRolePatientAdministrativeGenderCodeCodeSystemName());
	}

	public Attribute getClinicalDocumentRecordTargetPatientRolePatientAdministrativeGenderCodeDisplayName(
			Document document) {
		return getAttribute(
				document,
				XPATH_PROPERTIES
						.getXpathClinicalDocumentRecordTargetPatientRolePatientAdministrativeGenderCodeDisplayName());
	}

	public Attribute getClinicalDocumentRecordTargetPatientRolePatientBirthTimeValue(
			Document document) {
		return getAttribute(
				document,
				XPATH_PROPERTIES
						.getXpathClinicalDocumentRecordTargetPatientRolePatientBirthTimeValue());
	}

	public Attribute getClinicalDocumentAuthorTypeCode(Document document) {
		return getAttribute(document,
				XPATH_PROPERTIES.getXpathClinicalDocumentAuthorTypeCode());
	}

	public Attribute getClinicalDocumentAuthorContextControlCode(
			Document document) {
		return getAttribute(document,
				XPATH_PROPERTIES
						.getXpathClinicalDocumentAuthorContextControlCode());
	}

	public Attribute getClinicalDocumentAuthorTimeValue(Document document) {
		return getAttribute(document,
				XPATH_PROPERTIES.getXpathClinicalDocumentAuthorTimeValue());
	}

	public Attribute getClinicalDocumentAuthorAssignedAuthorClassCode(
			Document document) {
	        
		return getAttribute(
				document,
				XPATH_PROPERTIES
						.getXpathClinicalDocumentAuthorAssignedAuthorClassCode());
	}

	public Attribute getClinicalDocumentAuthorAssignedAuthorIdExtension(
			Document document) {
		return getAttribute(
				document,
				XPATH_PROPERTIES
						.getXpathClinicalDocumentAuthorAssignedAuthorIdExtension());
	}

	public Attribute getClinicalDocumentAuthorAssignedAuthorTelecomValue(
			Document document) {
		return getAttribute(
				document,
				XPATH_PROPERTIES
						.getXpathClinicalDocumentAuthorAssignedAuthorTelecomValue());
	}

	
	public List<Element> getClinicalDocumentAuthorAssignedAuthorAssignedPersonNameGiven(
			Document document) {
		return getElements(
				document,
				XPATH_PROPERTIES
						.getXpathClinicalDocumentAuthorAssignedAuthorAssignedPersonNameGiven());
	}

	public List<Element> getClinicalDocumentAuthorAssignedAuthorAssignedPersonNameFamily(
			Document document) {
		return getElements(
				document,
				XPATH_PROPERTIES
						.getXpathClinicalDocumentAuthorAssignedAuthorAssignedPersonNameFamily());
	}
	public Attribute getClinicalDocumentComponentOfEncompassingEncounterEffectiveTimeHighValue(Document document)
	{
		return getAttribute(
				document,
				XPATH_PROPERTIES
						.getXpathClinicalDocumentComponentOfEncompassingEncounterEffectiveTimeHighValue());
	}
	public Attribute getClinicalDocumentComponentOfEncompassingEncounterEffectiveTimeLowValue(Document document)
	{
		return getAttribute(
				document,
				XPATH_PROPERTIES
						.getXpathClinicalDocumentComponentOfEncompassingEncounterEffectiveTimeLowValue());
	}
	public List<Element> getClinicalDocumentComponentNonXMLBodyText(Document document)
	{
		return getElements(
				document,
				XPATH_PROPERTIES
						.getXpathClinicalDocumentComponentNonXMLBodyText());
	}

	public List<Element> getComponentStructureBodyComponent(Document document) {
		return getElements(document, XPATH_PROPERTIES.getXpathComponentStructureBodyComponent());
	}
	public Attribute getClinicalDocumentComponentOfEncompassingEncounterIdExtension(Document document){
		return getAttribute(document, XPATH_PROPERTIES.getXpathClinicalDocumentComponentOfEncompassingEncounterIdExtension());
	}
	public Attribute getClinicalDocumentAuthorAssignedAuthorIdRoot(Document document)
	{
		return getAttribute(document, XPATH_PROPERTIES.getXpathClinicalDocumentAuthorAssignedAuthorIdRoot());
	}
	public Attribute getClinicalDocumentRecordTargetPatientRolePatientIdRoot(Document document){
		return getAttribute(document, XPATH_PROPERTIES.getXpathClinicalDocumentRecordTargetPatientRolePatientIdRoot());
	}
}
