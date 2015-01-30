package com.artica.telesalud.tph.webservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.rpc.ServiceException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.artica.telesalud.common.data.HibernateSessionFactoryManager;
import com.artica.telesalud.message.service.dto.AntecedenteAmp;
import com.artica.telesalud.message.service.dto.AntecedentesAmp;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
import com.artica.telesalud.tph.message.service.exception.InvalidPatientException;
import com.artica.telesalud.tph.message.service.interactions.ClinicalDocumentHCPHMessageService;
import com.artica.telesalud.tph.message.service.interactions.ClinicalDocumentHCPHXMLParser;
import com.artica.telesalud.tph.message.service.interactions.IN000001UV01MessageService;
import com.artica.telesalud.tph.message.service.interactions.IN000002UV01MessageService;
import com.artica.telesalud.tph.message.service.interactions.IN000019UV01MessageService;
import com.artica.telesalud.tph.message.service.interactions.IN000031UV01MessageService;
import com.artica.telesalud.tph.message.service.interactions.IN000032UV01MessageService;
import com.artica.telesalud.tph.message.service.interactions.IN000032UV01XMLParser;
import com.artica.telesalud.tph.message.service.interactions.IN900350UV02MessageService;
import com.artica.telesalud.tph.model.patient.Patient;
import com.artica.telesalud.tph.model.person.Person;
import com.artica.telesalud.tph.service.PatientService;
import com.artica.telesalud.tph.service.PersonaService;

public class MessageServiceTest {
	private PatientService patientService;
	private PersonaService personService;

	@Before
	public void init() {

		HashMap<String, String> params = new HashMap<String, String>();
		params.put(HibernateTPHDAOFactory.TPH_HIBERNATE_CONFIG_FILE,
				"hibernate-tph-test-service.cfg.xml");

		HibernateSessionFactoryManager manager = HibernateSessionFactoryManager
				.getInstance();
		manager.createFactory("hibernate-tph-test-service.cfg.xml");
		
		patientService = new PatientService(
				"com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory",
				params);
		personService = new PersonaService(
				"com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory",
				params);
	}
	//@Test
	public void testCDAHtml() throws JDOMException, IOException, TransformerException{
		
	}
	/**
	 * CDA para las interacciones 4 y 5
	 * 
	 * @throws InvalidPatientException
	 * @throws IOException
	 * @throws JDOMException
	 */
	// @Test
	public void testClinicalDocumentHCPHMessageService()
			throws InvalidPatientException, IOException, JDOMException {

		ClinicalDocumentHCPHMessageService ms = new ClinicalDocumentHCPHMessageService();
		File archive = new File(
				"/home/interoperabilidad/workspace/TPH.MessageService/xml.pdf");
		FileInputStream fis = null;
		byte[] bFile = new byte[(int) archive.length()];
		// convert file into array of bytes
		fis = new FileInputStream(archive);
		fis.read(bFile);
		fis.close();
		String s = ms.createMessage(100, bFile);
		try {
			// se almacena el archivo como si fuera un archivo de texto
			OutputStream os = new FileOutputStream("XML.xml");
			OutputStreamWriter bufferedWriter = new OutputStreamWriter(os,
					"UTF8");
			try {
				bufferedWriter.write(s);
			} catch (IOException ex) {
			}
			try {
				bufferedWriter.close();
			} catch (IOException ex) {
			}

			try {
				os.close();
			} catch (IOException ex) {
			}
		} catch (FileNotFoundException ex) {
		}
		System.out.println(s);
	}

	/**
	 * Prueba de la interaccion 2.
	 * 
	 * @throws FileNotFoundException
	 * @throws JDOMException
	 * @throws IOException
	 * @throws InvalidPatientException
	 * @throws ServiceException
	 */
	//@Test
	public void testIN000031UV01MessageService() throws FileNotFoundException,
			JDOMException, IOException, InvalidPatientException,
			ServiceException {
		IN000031UV01MessageService ms = new IN000031UV01MessageService();
		// String s = ms.createMessage(386);
		// System.out.println(s);
		String xml = ms.createMessage(394);
		System.out.println(xml);
		//ms.sendMessage(394);
	}

	/**
	 * Interaccion 4, respuesta
	 * 
	 * @throws InvalidPatientException
	 * @throws IOException
	 * @throws JDOMException
	 * @throws SAXException
	 */
	//@Test
	public void testIN000032UV01MessageService()
			throws InvalidPatientException, IOException, JDOMException,
			SAXException {

		try {
			IN000032UV01MessageService ms = new IN000032UV01MessageService();
			String s = ms.createMessage(38,
					"co.udea.telesalud.hcteleasis",
					"Historia clínica teleasistencia domiciliaria. Artica, Udea V1.0");
			 ms.sendMessage(38,
						"co.udea.telesalud.hcteleasis",
						"Historia clínica teleasistencia domiciliaria. Artica, Udea V1.0");
			File archive = new File(
					"/home/interoperabilidad/workspace/TPH.MessageService/xml.pdf");
			FileInputStream fis = null;
			byte[] bFile = new byte[(int) archive.length()];
			// convert file into array of bytes
			fis = new FileInputStream(archive);
			fis.read(bFile);
			fis.close();
			try {
				// se almacena el archivo como si fuera un archivo de texto
				OutputStream os = new FileOutputStream("XML.xml");
				OutputStreamWriter bufferedWriter = new OutputStreamWriter(os,
						"UTF8");
				try {
					bufferedWriter.write(s);
				} catch (IOException ex) {
				}
				try {
					bufferedWriter.close();
				} catch (IOException ex) {
				}

				try {
					os.close();
				} catch (IOException ex) {
				}
			} catch (FileNotFoundException ex) {
			}
			System.out.println(s);
			System.out.println("Success");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Prueba de la interaccion 5
	 * 
	 * @throws InvalidPatientException
	 * @throws IOException
	 * @throws JDOMException
	 * @throws SAXException
	 */
	//@Test
	public void testIN000002UV01MessageService()
			throws InvalidPatientException, IOException, JDOMException,
			SAXException {

			try {
				IN000002UV01MessageService ms = new IN000002UV01MessageService();
				// String s = ms.createMessage(i);
				String s = ms.createMessage(37,
						"co.udea.telesalud.hcteleasis",
						"Historia clínica teleasistencia domiciliaria. Artica, Udea V1.0");
				/*ms.sendMessage(i,
						"co.udea.telesalud.hcteleasis",
						"Historia clínica teleasistencia domiciliaria. Artica, Udea V1.0");*/
				ms.sendMessage(100,
						"co.udea.telesalud.hcteleasis",
						"Historia clínica teleasistencia domiciliaria. Artica, Udea V1.0");
				System.out.println(s);
				try {
					// se almacena el archivo como si fuera un archivo de texto
					OutputStream os = new FileOutputStream("XML.xml");
					OutputStreamWriter bufferedWriter = new OutputStreamWriter(os,
							"UTF8");
					try {
						bufferedWriter.write(s);
					} catch (IOException ex) {
					}
					try {
						bufferedWriter.close();
					} catch (IOException ex) {
					}

					try {
						os.close();
					} catch (IOException ex) {
					}
				} catch (FileNotFoundException ex) {
				}
				System.out.println("Success");
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				ex.printStackTrace();
			}
		

		// String xml = e.getText();
		// String decodedXml = new String(Base64.decodeBase64(xml.getBytes()));
		// System.out.println(decodedXml);
		// Element element = parserc.getClinicalDocumentComponentNonXMLBodyText(
		// document).get(0);
		// byte data[] = Base64.decodeBase64(element.getText().getBytes());

		// System.out.println(s);
		// System.out.println(validateXMLSchema("C:\\Users\\ARTICA-HL7\\Desktop\\intero\\processable\\multicacheschemas\\RCMR_IN000001UV01.xsd",
		// /*"C:\\Users\\ARTICA-HL7\\Desktop\\intero\\XML\\RCMR_IN000001UV01.xml"));//*/"C:\\Users\\ARTICA-HL7\\workspaceNuevo\\TPH.MessageService\\src\\com\\artica\\telesalud\\tph\\message\\service\\templates\\RCMR_IN000002UV01.xml"));

		// FileOutputStream fos = new FileOutputStream("pdf.pdf");
		// fos.write(data);
		// fos.close();
		/*
		 * try { OutputStream os = new FileOutputStream("decoded.xml");
		 * OutputStreamWriter bufferedWriter = new OutputStreamWriter(os,
		 * "UTF8"); try { bufferedWriter.write(decodedXml); } catch (IOException
		 * ex) { } try { bufferedWriter.close(); } catch (IOException ex) { }
		 * 
		 * try { os.close(); } catch (IOException ex) { } } catch
		 * (FileNotFoundException ex) { }
		 */
	}

	/**
	 * Crear PDF interacciones 4 y 5
	 * 
	 * @throws IOException
	 * @throws JDOMException 
	 */
	 //@Test
	public void testPrintHCPH() throws IOException, JDOMException {
		//PrintHCPH p = new PrintHCPH();
		//p.createPdfFile(96, "xml.pdf");
		 SAXBuilder builder = new SAXBuilder();
		 Document document = builder.build(new File("Test.xml"));
		 ClinicalDocumentHCPHXMLParser parser = ClinicalDocumentHCPHXMLParser.getInstance();
		 String pdf = parser.getClinicalDocumentComponentNonXMLBodyText(document).get(0).getText();
		 byte[] b = com.artica.telesalud.common.service.Base64.decode(pdf);
		 OutputStream os = new FileOutputStream("xml.pdf");
		 os.write(b);
		 os.close();
	}
	//@Test
public void testError() throws JDOMException, IOException{
	SAXBuilder builder = new SAXBuilder();
	Document document = builder.build(new File("Test.xml"));
	 IN000032UV01XMLParser parser = IN000032UV01XMLParser.getInstance();
	 byte[] cda = com.artica.telesalud.common.service.Base64.decode(parser.getControlActProcessText(document).get(0).getValue());
	 FileOutputStream fos = new FileOutputStream(new File("cda.xml"));
	 fos.write(cda);
	 fos.close();
	builder = new SAXBuilder();
	 document = builder.build(new File("cda.xml"));
	 ClinicalDocumentHCPHXMLParser parser1 = ClinicalDocumentHCPHXMLParser.getInstance();
	 String pdf = parser1.getClinicalDocumentComponentNonXMLBodyText(document).get(0).getText();
	 byte[] b = com.artica.telesalud.common.service.Base64.decode(pdf);
	 OutputStream os = new FileOutputStream("xml.pdf");
	 os.write(b);
	 os.close();
}
	/**
	 * Prueba de la interaccion 3
	 * 
	 * @throws ServiceException
	 * @throws InvalidPatientException
	 * @throws IOException 
	 * @throws JDOMException 
	 * @throws FileNotFoundException 
	 */
//	@Test
	public void testIN900350UV02MessageService() throws ServiceException, InvalidPatientException, FileNotFoundException, JDOMException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateInString = "20140522";
		String date2String = "20140522";
		Date date1 = null, date2 = null;
		try {
			date1 = sdf.parse(dateInString);
			date2 = sdf.parse(date2String);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IN900350UV02MessageService ms = new IN900350UV02MessageService();
		String xml = ms.createMessage("10000001","2.16.840.1.113883.19.57.1.1.1.1.1", date2, date1,
				"co.udea.telesalud.hcemr",
				"Historia clínica Prehospitalaria. Artica, Udea V1.0");
		System.out.println(xml);
		 ms.sendMessage("1037524435","2.16.840.1.113883.19.57.1.1.1.1.1", date2, date1,
		 "co.udea.telesalud.hcteleasis",
		"Historia clínica teleasistencia domiciliaria. Artica, Udea V1.0");
	}
	//@Test
	public void testResponseMessage() throws ServiceException, InvalidPatientException, JDOMException, IOException, ParseException{
		SAXBuilder builder = new SAXBuilder();
		Document document = builder.build(new File("Test.xml"));
		XMLOutputter out = new XMLOutputter();
		IN900350UV02MessageService ms = new IN900350UV02MessageService();
		
		ms.responseMessage(out.outputString(document));
	}
	/**
	 * Prueba de la interaccion 1
	 * 
	 * @throws Exception
	 */
	 @Test
	public void testIN000001UV01MessageService() throws Exception {

		try {
			IN000001UV01MessageService ms = new IN000001UV01MessageService();
			// obtener paciente de la base de datos
			Patient p = patientService.buscarPatient(386);
			// crear nuevo mensaje
			Person person = new Person();
			person = personService.getPersonById(261);
			String s = ms.createMessage(p, person);
			System.out.println(s);
			ms.sendMessage(s);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}

	}
	/**
	 * Prueba de la interaccion 1
	 * 
	 * @throws Exception
	 */
//	 @Test
	public void testIN000001UV01MessageServiceIdentifier() throws Exception {

		try {
			IN000001UV01MessageService ms = new IN000001UV01MessageService();
			// obtener paciente de la base de datos
			//Patient p = patientService.buscarPatient(386);
			// crear nuevo mensaje
			Person person = new Person();
			person = personService.getPersonById(261);
			String s = ms.createMessage(37, person);
			System.out.println(s);
			ms.sendMessage(s);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}

	}
	
//	@Test
	public void testIN000019UV01MessageService() throws Exception {

		try {
			IN000019UV01MessageService ms = new IN000019UV01MessageService();
			// obtener paciente de la base de datos
			Patient p = patientService.buscarPatient(386);
			// crear nuevo mensaje
			Person person = new Person();
			person = personService.getPersonById(261);
			String s = ms.createMessage(p, person);
			System.out.println(s);
			ms.sendMessage(s);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}

	}
	 //@Test
	public void testXsd() throws SAXException, IOException {
		System.out
				.println(validateXMLSchema(
						"/home/interoperabilidad/workspace/intero/XML/CDA/CDA.xsd",
						"/home/interoperabilidad/workspace/TPH.MessageService/XML.xml"));
	}
//@Test
	public void testCdaList() throws JDOMException, IOException, TransformerException
	{
		IN000032UV01MessageService ms = new IN000032UV01MessageService();
		List<AntecedentesAmp> antecedentes = ms.getListAntecedentesAMP(100);
		for(AntecedentesAmp antecedente : antecedentes){
			List<AntecedenteAmp> amp = antecedente.getAntecedentes();
			System.out.println(antecedente.getHistoriaClinicaNombre());
			System.out.println(antecedente.getHistoriaClinicaUuid());
			for(AntecedenteAmp a : amp)
			{
				System.out.println(a.getListaAntecedentes());
			}
		}
	}
	public static boolean validateXMLSchema(String xsdPath, String xmlPath)
			throws SAXException, IOException {

		SchemaFactory factory = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = factory.newSchema(new File(xsdPath));
		Validator validator = schema.newValidator();
		validator.validate(new StreamSource(new File(xmlPath)));

		return true;
	}

}
