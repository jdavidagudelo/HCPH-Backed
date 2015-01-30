package com.artica.telesalud.tph.message.service.interactions;

import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.filter.Filters;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
/**
 * Clase utilizada como base para otras clases encargadas de obtener
 * los elementos y atributos a partir de un documento XML. 
 *	@author Juan David Agudelo Alvarez jdaaa2009@gmail.com
 *
 */
public abstract class XMLParser {
	
	private static final XPathFactory XPATH_FACTORY = XPathFactory.instance();

	/**
	 * Metodo utilizado para obtener un attributo del documento XML especificado que puede ser hallado utilizando la
	 * consulta Xpath ingresada como argumento.
	 * @param document documento XML que se desea procesar.
	 * @param xpath consulta escrita utilizando el estandar XPATH.
	 * @return el primer atributo que cumple con la consulta especificada como argumento.
	 */
	public Attribute getAttribute(Document document, String xpath) {
		XPathExpression<Attribute> expr = XPATH_FACTORY.compile(xpath,
				Filters.attribute());
		return expr.evaluateFirst(document);
	}
	/**
	 * Metodo utilizado para obtener los elementos del documento XML especificado que pueden ser hallados utilizando la
	 * consulta Xpath ingresada como argumento.
	 * @param document documento XML que se desea procesar.
	 * @param xpath consulta escrita utilizando el estandar XPATH.
	 * @return lista de elemento que cumplen con la consulta especificada como argumento.
	 */
	public List<Element> getElements(Document document, String xpath)
	{
		
		XPathExpression<Element> expr = XPATH_FACTORY
				.compile(
						xpath,
						Filters.element());
		return expr.evaluate(document);
	}
}
