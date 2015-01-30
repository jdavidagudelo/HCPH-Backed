package com.artica.telesalud.tph.message.service.interactions;

import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.filter.Filters;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

import com.artica.telesalud.tph.message.service.properties.IN000019UV01XPathProperties;

/**
 * Esta clase permite obtener los elementos XML a partir de una plantilla
 * predefinida. Utiliza rutas Xpath definidas en un archivo de propiedades para
 * obtener los elementos del documento XML que deben ser modificados para crear
 * un mensaje utilizado para la interaccion matricular documento de paciente.
 * 
 * @author Juan David Agudelo Alvarez jdaaa2009@gmail.com
 * 
 */
public class IN000019UV01XMLParser extends XMLParser{
	private IN000019UV01XMLParser() {
	}

	private static final IN000019UV01XPathProperties XPATH_PROPERTIES = IN000019UV01XPathProperties
			.getInstance();
	private static final IN000019UV01XMLParser INSTANCE = new IN000019UV01XMLParser();

	public static IN000019UV01XMLParser getInstance() {
		return INSTANCE;
	}

	/**
	 * Atributo que contiene el identificador universal del documento XML.
	 * @return
	 */
	public Attribute getIdExtension(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathIdExtension());
	}
	/**
	 * 
	 * @param document
	 * @return
	 */
	public Attribute getCreationTimeValue(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathCreationtimeValue());
	}
	/**
	 * 
	 * @param document
	 * @return
	 */
	public Attribute getInteractionIdValue(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathInteractionIdExtension());
	}
	/**
	 * 
	 * @param document
	 * @return
	 */
	public Attribute getProccessingCodeCode(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathProccessingCodeCode());
	}
	/**
	 * 
	 * @param document
	 * @return
	 */
	public Attribute getProccessingModeCodeCode(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathProccessingModeCodeCode());
	}

	public List<Element> getControlActProcessSubjectTargetRecordTargetPatientPatientPersonNameGiven(
			Document document) {
		return getElements(document, XPATH_PROPERTIES
								.getXpathControlActProcessSubjectTargetRecordTargetPatientPatientPersonNameGiven());
	}
	
	public Attribute getControlActProcessAuthorOrPerformerIdExtension(Document document)
	{
		XPathFactory xFactory = XPathFactory.instance();
		XPathExpression<Attribute> expr = xFactory.compile(XPATH_PROPERTIES.getXpathControlActProcessAuthorOrPerformerIdExtension(), Filters.attribute());
		return expr.evaluateFirst(document);
	}
	public Attribute getControlActProcessAuthorOrPerformerTelecomValue(Document document)
	{
		XPathFactory xFactory = XPathFactory.instance();
		XPathExpression<Attribute> expr = xFactory.compile(XPATH_PROPERTIES.getXpathControlActProcessAuthorOrPerformerTelecomValue(), Filters.attribute());
		return expr.evaluateFirst(document);
	}
	public Element getControlActProcessAuthorOrPerformerAssignedPersonNameGiven(Document document)
	{
		XPathFactory xFactory = XPathFactory.instance();
		XPathExpression<Element> expr = xFactory.compile(XPATH_PROPERTIES.getXpathControlActProcessAuthorOrPerformerAssignedPersonNameGiven(), Filters.element());
		return expr.evaluateFirst(document);
	}
	public Element getControlActProcessAuthorOrPerformerAssignedPersonNameFamily(Document document)
	{
		XPathFactory xFactory = XPathFactory.instance();
		XPathExpression<Element> expr = xFactory.compile(XPATH_PROPERTIES.getXpathControlActProcessAuthorOrPerformerAssignedPersonNameFamily(), Filters.element());
		return expr.evaluateFirst(document);
	}
	public Attribute getControlActProcessAuthorOrPerformerRepresentedOrganizationIdExtension(Document document)
	{
		XPathFactory xFactory = XPathFactory.instance();
		XPathExpression<Attribute> expr = xFactory.compile(XPATH_PROPERTIES.getXpathControlActProcessAuthorOrPerformerRepresentedOrganizationIdExtension(), Filters.attribute());
		return expr.evaluateFirst(document);
	}
	/**
	 * 
	 * @param document
	 * @return
	 */
	public List<Element> getControlActProcessSubjectTargetRecordTargetPatientPatientPersonNameFamily(
			Document document) {
		return getElements(document, XPATH_PROPERTIES
								.getXpathControlActProcessSubjectTargetRecordTargetPatientPatientPersonNameFamily());
	}
	/**
	 * 
	 * @param document
	 * @return
	 */
	public Attribute getReceiverDeviceIdExtension(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathReceiverDeviceIdExtension());
	}
	/**
	 * 
	 * @param document
	 * @return
	 */
	public Attribute getReceiverDeviceSoftwareNameDisplayName(
			Document document) {
		return getAttribute(document, XPATH_PROPERTIES
				.getXpathReceiverDeviceSoftwareNameDisplayName());
	}
	/**
	 * 
	 * @param document
	 * @return
	 */
	public Attribute getSenderDeviceIdExtension(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathSenderDeviceIdExtension());
	}
	/**
	 * 
	 * @param document
	 * @return
	 */
	public Attribute getSenderDeviceSoftwareNameDisplayName(
			Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathSenderDeviceSoftwareNameDisplayName());
	}
	/**
	 * 
	 * @param document
	 * @return
	 */
	public Attribute getControlActProcessSubjectTargetIdExtension(
			Document document) {
		return getAttribute(document, XPATH_PROPERTIES
				.getXpathControlActProcessSubjectTargetIdExtension());
	}
	/**
	 * 
	 * @param document
	 * @return
	 */
	public Attribute getControlActProcessSubjectTargetCodeCode(
			Document document) {
		return getAttribute(document, XPATH_PROPERTIES
				.getXpathControlActProcessSubjectTargetCodeCode());
	}
	/**
	 * 
	 * @param document
	 * @return
	 */
	public Attribute getControlActProcessSubjectTargetCodeDisplayName(
			Document document) {
		return getAttribute(document, XPATH_PROPERTIES
				.getXpathControlActProcessSubjectTargetCodeDisplayName());
	}
	/**
	 * 
	 * @param document
	 * @return
	 */
	public Attribute getControlActProcessSubjectTargetRecordTargetPatientPatientPersonAdministrativeGenderCodeCode(
			Document document) {
		return getAttribute(document, XPATH_PROPERTIES
								.getXpathControlActProcessSubjectTargetRecordTargetPatientPatientPersonAdministrativeGenderCodeCode());
	}
	/**
	 * 
	 * @param document
	 * @return
	 */
	public Attribute getControlActProcessSubjectTargetRecordTargetPatientPatientPersonAdministrativeGenderCodeCodeSystem(
			Document document) {
		return getAttribute(document, XPATH_PROPERTIES
								.getXpathControlActProcessSubjectTargetRecordTargetPatientPatientPersonAdministrativeGenderCodeCodeSystem());
	}
	/**
	 * 
	 * @param document
	 * @return
	 */
	public Attribute getControlActProcessSubjectTargetRecordTargetPatientPatientPersonAdministrativeGenderCodeDisplayName(
			Document document) {
		return getAttribute(document, XPATH_PROPERTIES
								.getXpathControlActProcessSubjectTargetRecordTargetPatientPatientPersonAdministrativeGenderCodeDisplayName());
	}
	/**
	 * 
	 * @param document
	 * @return
	 */
	public Attribute getControlActProcessSubjectTargetRecordTargetPatientPatientPersonBirthTimeValue(
			Document document) {
		return getAttribute(document, XPATH_PROPERTIES
								.getXpathControlActProcessSubjectTargetRecordTargetPatientPatientPersonBirthTimeValue());
	}
	/**
	 * 
	 * @param document
	 * @return
	 */
	public Attribute getControlActProcessSubjectTargetRecordTargetPatientIdExtension(
			Document document) {
		return getAttribute(document, XPATH_PROPERTIES
								.getXpathControlActProcessSubjectTargetRecordTargetPatientIdExtension());
	}
	/**
	 * 
	 * @param document
	 * @return
	 */
	public Attribute getControlActProcessSubjectTargetRecordTargetPatientPatientPersonIdExtension(
			Document document) {
		return getAttribute(document, XPATH_PROPERTIES
								.getXpathControlActProcessSubjectTargetRecordTargetPatientPatientPersonIdExtension());
	}
	/**
	 * 
	 * @param document
	 * @return
	 */
	public Attribute getControlActProcessSubjectTargetEffectiveTimeValue(
			Document document) {
		return getAttribute(document, XPATH_PROPERTIES
				.getXpathControlActProcessSubjectTargetEffectiveTimeValue());
	}
	/**
	 * 
	 * @param document
	 * @return
	 */
	public Attribute getControlActProcessSubjectTargetAuthorTimeValue(
			Document document) {
		return getAttribute(document, XPATH_PROPERTIES
				.getXpathControlActProcessSubjectTargetAuthorTimeValue());
	}
	/**
	 * 
	 * @param document
	 * @return
	 */
	public Attribute getControlActProcessSubjectTargetRecordTargetPatientPatientPersonIdRoot(Document document)
	{
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessSubjectTargetRecordTargetPatientPatientPersonIdRoot());
	}
}
