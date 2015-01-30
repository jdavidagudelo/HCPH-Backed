package com.artica.telesalud.tph.message.service.interactions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;

import javax.xml.rpc.ServiceException;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import com.artica.telesalud.common.data.HibernateSessionFactoryManager;
import com.artica.telesalud.mirth.connect.webservice.QueryPatientAMPMessageServiceLocator;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
import com.artica.telesalud.tph.message.service.exception.InvalidPatientException;
import com.artica.telesalud.tph.message.service.properties.HibernateProperties;
import com.artica.telesalud.tph.message.service.properties.XMLPathProperties;
import com.artica.telesalud.tph.message.service.util.HL7Formatter;
import com.artica.telesalud.tph.model.patient.Patient;
import com.artica.telesalud.tph.service.PatientService;
/**
 * Clase utilizada para generar y enviar al bus de servicios el mensaje asociado a la interaccion
 * con codigo RCMR_IN000031UV01. 
 * @author Juan David Agudelo Alvarez jdaaa2009@gmail.com
 *
 */
public class IN000031UV01MessageService {
	/**
	 * Atributo que contiene las URL de las plantillas XML.
	 */
	private static final XMLPathProperties XPATH_PROPERTIES = XMLPathProperties.getInstance();
	/**
	 * Atributo utilizado para dar formato HL7 v3 a la informacion de un paciente.
	 */
	private static final HL7Formatter HL7_FORMATTER = HL7Formatter.getInstance();
	/**
	 * Atributo utilizado para obtener los atributos y elementos del mensaje.
	 */
	private static final IN000031UV01XMLParser PARSER = IN000031UV01XMLParser.getInstance();
	/**
	 * Atributo utilizado para enviar el mensaje generado al bus de servicios.
	 */
	private static final QueryPatientAMPMessageServiceLocator SERVICE = new QueryPatientAMPMessageServiceLocator();
	/**
	 * Atributo utilizado para obtener las propiedades de Hibernate
	 */
	private static final HibernateProperties HIBERNATE_PROPERTIES = HibernateProperties
			.getInstance();

	/**
	 * Atributo utilizado para obtener la informacion de un encuentro de emergencias.
	 */
	private PatientService patientService;
	/**
	 * Url utilizada para cargar las plantillas XML utilizadas por esta clase.
	 * Este atributo debe ser especificado a partir del ambiente en el que se ejecute este servicio.
	 * Para el caso de esta aplicacion es configurado a partir de parametros almacenados en el archivo 
	 * web.xml de la aplicacion web asociada a esta historia clinica.
	 * El valor por defecto corresponde al especificado en el archivo de propiedades.
	 */
	private String urlBaseTemplates = XPATH_PROPERTIES
			.getXmlTemplateRootFilePath();
	/**
	 * Constructor que permite inicializar los servicios requeridos por esta clase a partir de los
	 * parametros de configuracion especificados como argumento.
	 * @param daoClassName nombre calificado de la clase que sirve con Fabrica para los objetos Dao 
	 * especificados en la capa de persistencia de la aplicacion.
	 * @param params corresponde a una lista de pares clave y valor que contiene los parametros de configuracion
	 * de Hibernate.
	 */
	public IN000031UV01MessageService(String daoClassName, HashMap<String, String> params)
	{
		patientService = new PatientService(
				daoClassName, params);
	}
	/**
	 * Constructor por defecto. Este constructor inicializa los servicios de la capa
	 * de logica del negocio con la informacion alamacenda en el archivo de 
	 * propiedades. No deberia ser usado en modo de produccion.
	 */
	public IN000031UV01MessageService(){

		HashMap<String, String> params = new HashMap<String, String>();
		params.put(HibernateTPHDAOFactory.TPH_HIBERNATE_CONFIG_FILE,
				HIBERNATE_PROPERTIES.getHibernateTphCfgXml());
		HibernateSessionFactoryManager manager = HibernateSessionFactoryManager
				.getInstance();
		manager.createFactory(HIBERNATE_PROPERTIES.getHibernateTphCfgXml());
		patientService = new PatientService(
				HIBERNATE_PROPERTIES.getHibernateFactoryClass(), params);
	}
	/**
	 * Metodo utilizado para crear y enviar un mensaje asociado al paciente ingresado como argumento al bus de servicios.
	 * @param patientId identificador del paciente.
	 * @return Mensaje que indica el exito en el envio del emnjsae
	 * @throws RemoteException
	 * @throws FileNotFoundException
	 * @throws ServiceException
	 * @throws JDOMException
	 * @throws IOException
	 * @throws InvalidPatientException
	 */
	public String sendMessage(Integer patientId) throws RemoteException, FileNotFoundException, ServiceException, JDOMException, IOException, InvalidPatientException{
		return SERVICE.getQueryPatientAMPMessagePort().queryPatientAMP(createMessage(patientId));
	}
	/**
	 * Metodo utilizado para crear y enviar un mensaje al bus de servicios
	 * asociado a un paciente con el numero de identificacion, tipo de identificacion y
	 * genero especificados como argumento.
	 * @param patientIdentifier numero de identificacion del paciente.
	 * @param identifierType id del tipo de identificacion del paciente.
	 * @param gender genero del paciente.
	 * @return mensaje de exito en el envio del mensaje.
	 * @throws RemoteException
	 * @throws FileNotFoundException
	 * @throws ServiceException
	 * @throws JDOMException
	 * @throws IOException
	 * @throws InvalidPatientException
	 */
	public String sendMessage(String patientIdentifier, String identifierType, String gender) throws RemoteException, FileNotFoundException, ServiceException, JDOMException, IOException, InvalidPatientException{
		return SERVICE.getQueryPatientAMPMessagePort().queryPatientAMP(createMessage(patientIdentifier, identifierType, gender));
	}
	/**
	 * Se crea un mensaje asociado a la interaccion RCMR_IN000031UV01 con la informacion 
	 * del paciente con el id ingresado como argumento.
	 * @param id identificador del paciente.
	 * @return mensaje XML asociado a  la interaccion RCMR_IN000031UV01 con la informacion 
	 * del paciente con el id ingresado como argumento.
	 * @throws FileNotFoundException
	 * @throws JDOMException
	 * @throws IOException
	 * @throws InvalidPatientException
	 */
	public String createMessage(Integer id) throws FileNotFoundException, JDOMException, IOException, InvalidPatientException
	{
		//paciente obtenido de la base de datos.
		Patient patient = patientService.buscarPatient(id);
		SAXBuilder builder = new SAXBuilder(); 
	    //plantilla XML obtenida a partir de la URL especificada   
		Document document = builder.build(new FileInputStream(urlBaseTemplates+XPATH_PROPERTIES.getXmlTemplateRCMR_IN000031UV01FilePath()));
        //variable utilizada para generar el mensaje como un String
		XMLOutputter xml = new XMLOutputter();
		//identificador unico del mensaje
        Attribute idMensaje = PARSER.getIdExtension(document);
        idMensaje.setValue(HL7_FORMATTER.getUuidMensaje());
        //tipo de documento del paciente
        Attribute documentType = PARSER.getControlActProcessQueryByParameterPatientIdValueRoot(document);
        documentType.setValue(HL7_FORMATTER.getPatientIdentifierType(patient));
        //identificador del contenido del mensaje
        Attribute idContenidoMensaje = PARSER.getControlActProcessQueryByParameterQueryIdExtension(document);
        idContenidoMensaje.setValue(HL7_FORMATTER.getUuidQueryMensaje());
        //fecha inicial de la consulta
        Attribute attributeFechaInicial = PARSER.getControlActProcessQueryByParameterEncompassingEncounterEffectiveTimeValueLowValue(document);
        attributeFechaInicial.setValue(HL7_FORMATTER.getHL7BirthTime(new Date()));
        //fecha final de la consulta
        Attribute attributeFechaFinal = PARSER.getControlActProcessQueryByParameterEncompassingEncounterEffectiveTimeValueHighValue(document);
        attributeFechaFinal.setValue(HL7_FORMATTER.getHL7BirthTime(new Date()));
        //identificacion del paciente
        Attribute patientId = PARSER.getControlActProcessQueryByParameterPatientIdValueExtension(document);
        patientId.setValue(HL7_FORMATTER.getHistoryCode(patient));
        //genero del paciente
        Attribute patientGender = PARSER.getControlActProcessQueryByParameterPatientLivingSubjectAdministrativeGenderCodeRealmCodeCode(document);
        patientGender.setValue(HL7_FORMATTER.getGenderCode(patient));
        //Identificacion del paciente
        Attribute personId = PARSER.getControlActProcessQueryByParameterPatientLivingSubjectIdValueExtension(document);
        personId.setValue(HL7_FORMATTER.getIdentifier(patient));
        //fecha de creacion del mensaje
        Attribute creationTime = PARSER.getCreationTimeValue(document);
        creationTime.setValue(HL7_FORMATTER.getHL7Date(new Date()));
        //retorna el mensaje como un String
        String outputString = xml.outputString(document);
        return outputString;
	}
	/**
	 * Se crea un mensaje asociado a la interaccion RCMR_IN000031UV01 a partir de la informacion del tipo de documento, numero de documento y
	 * genero especificados como argumento.
	 * @param patientIdentifier numero de identificacion del paciente.
	 * @param identifierType tipo de identificacion del paciente.
	 * @param gender genero del paciente.
	 * @return mensaje asociado a la interaccion RCMR_IN000031UV01 con la informacion del paciente ingresado como argumento.
	 * @throws FileNotFoundException
	 * @throws JDOMException
	 * @throws IOException
	 * @throws InvalidPatientException
	 */
	public String createMessage(String patientIdentifier, String identifierType, String gender) throws FileNotFoundException, JDOMException, IOException, InvalidPatientException{
		//documento obtenido a partir de una platilla XML
		SAXBuilder builder = new SAXBuilder(); 
		Document document = builder.build(new FileInputStream(urlBaseTemplates+XPATH_PROPERTIES.getXmlTemplateRCMR_IN000031UV01FilePath()));
        XMLOutputter xml = new XMLOutputter();
        //identificador unico del mensaje
        Attribute idMensaje = PARSER.getIdExtension(document);
        idMensaje.setValue(HL7_FORMATTER.getUuidMensaje());
        //tipo de documento del paciente
        Attribute documentType = PARSER.getControlActProcessQueryByParameterPatientIdValueRoot(document);
        documentType.setValue(HL7_FORMATTER.getPatientIdentifierType(identifierType));
        //identificador unico del contenido del mensaje
        Attribute idContenidoMensaje = PARSER.getControlActProcessQueryByParameterQueryIdExtension(document);
        idContenidoMensaje.setValue(HL7_FORMATTER.getUuidQueryMensaje());
        //fecha inicial por defecto
        Attribute attributeFechaInicial = PARSER.getControlActProcessQueryByParameterEncompassingEncounterEffectiveTimeValueLowValue(document);
        attributeFechaInicial.setValue(HL7_FORMATTER.getHL7BirthTime(new Date()));
        //fecha final por defecto
        Attribute attributeFechaFinal = PARSER.getControlActProcessQueryByParameterEncompassingEncounterEffectiveTimeValueHighValue(document);
        attributeFechaFinal.setValue(HL7_FORMATTER.getHL7BirthTime(new Date()));
        //identificacion del paciente
        Attribute patientId = PARSER.getControlActProcessQueryByParameterPatientIdValueExtension(document);
        patientId.setValue(patientIdentifier);
        //genero del paciente
        Attribute patientGender = PARSER.getControlActProcessQueryByParameterPatientLivingSubjectAdministrativeGenderCodeRealmCodeCode(document);
        patientGender.setValue(HL7_FORMATTER.getGenderCode(gender));
        //identificacion del paciente
        Attribute personId = PARSER.getControlActProcessQueryByParameterPatientLivingSubjectIdValueExtension(document);
        personId.setValue(patientIdentifier);
        //fecha de creacion del documento
        Attribute creationTime = PARSER.getCreationTimeValue(document);
        creationTime.setValue(HL7_FORMATTER.getHL7Date(new Date()));
        //se retorna el String
        String outputString = xml.outputString(document);
        return outputString;
	}
	

	public String getUrlBaseTemplates() {
		return urlBaseTemplates;
	}
	public void setUrlBaseTemplates(String urlBaseTemplates) {
		this.urlBaseTemplates = urlBaseTemplates;
	}
	
}
