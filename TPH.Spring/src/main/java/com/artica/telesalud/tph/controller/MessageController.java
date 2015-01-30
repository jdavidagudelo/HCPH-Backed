package com.artica.telesalud.tph.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.xml.rpc.ServiceException;

import org.jdom2.JDOMException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.ServletContextAware;

import com.artica.telesalud.common.data.HibernateSessionFactoryManager;
import com.artica.telesalud.common.service.Base64;
import com.artica.telesalud.message.service.dto.AntecedenteAmp;
import com.artica.telesalud.message.service.dto.AntecedentesAmp;
import com.artica.telesalud.patientmasterindex.model.Ehr;
import com.artica.telesalud.patientmasterindex.service.EhrService;
import com.artica.telesalud.tph.controller.constants.HibernateProperties;
import com.artica.telesalud.tph.controller.constants.MessageRestURIConstants;
import com.artica.telesalud.tph.controller.exception.FieldErrorResource;
import com.artica.telesalud.tph.controller.exception.InternalErrorException;
import com.artica.telesalud.tph.controller.exception.InvalidRequestException;
import com.artica.telesalud.tph.controller.model.amp.AntecedentesAmpSpringDto;
import com.artica.telesalud.tph.controller.model.amp.EhrSpringDto;
import com.artica.telesalud.tph.controller.model.amp.SendDocumentInfo;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
import com.artica.telesalud.tph.message.service.exception.InvalidPatientException;
import com.artica.telesalud.tph.message.service.interactions.IN000001UV01MessageService;
import com.artica.telesalud.tph.message.service.interactions.IN000002UV01MessageService;
import com.artica.telesalud.tph.message.service.interactions.IN000031UV01MessageService;
import com.artica.telesalud.tph.message.service.interactions.IN000032UV01MessageService;
import com.artica.telesalud.tph.model.encounter.Encounter;
import com.artica.telesalud.tph.model.evento.Lesionado;
import com.artica.telesalud.tph.model.patient.Patient;
import com.artica.telesalud.tph.model.patient.PatientIdentifier;
import com.artica.telesalud.tph.model.person.Person;
import com.artica.telesalud.tph.server.util.PrintHCPH;
import com.artica.telesalud.tph.service.EncounterService;
import com.artica.telesalud.tph.service.LesionadoService;
import com.artica.telesalud.tph.service.PatientService;
import com.artica.telesalud.tph.service.PersonService;
@Controller
@RequestMapping(value=MessageRestURIConstants.MESSAGE_SERVICE_URI)
public class MessageController extends AbstractController  implements ServletContextAware{
	private IN000001UV01MessageService in000001uv01MessageService;
	private IN000002UV01MessageService in000002uv01MessageService;
	private ServletContext context;
	private IN000031UV01MessageService in000031uv01MessageService;
	private IN000032UV01MessageService in000032uv01MessageService;
	private EhrService ehrService;
	private PersonService personService;
	private LesionadoService lesionadoService;
	private PatientService patientService;
	private EncounterService encounterService;
	@RequestMapping(value=MessageRestURIConstants.MESSAGE_SERVICE_INTEROPERAR_PACIENTE_URI, method = RequestMethod.POST)
	public @ResponseBody String interoperarPaciente(
			@PathVariable(value=MessageRestURIConstants.MESSAGE_SERVICE_INTEROPERAR_PACIENTE_LESIONADO_ID_PARAM_NAME)Integer lesionadoId,
			Integer personId)
	{

		try {
			super.refreshHibernateSession(context);
			Person person = personService.obtenerPersona(personId);
			return in000001uv01MessageService.sendMessage(in000001uv01MessageService.createMessage(lesionadoId, person));
		} catch (Exception ex) {

			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(MessageController.class.getCanonicalName(), String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		} 
	}
	@RequestMapping(value = MessageRestURIConstants.MESSAGE_SERVICE_GET_EHRS_URI, method = RequestMethod.GET)
	 @ResponseBody public List<EhrSpringDto> getEhrs()
	{
		List<EhrSpringDto> result = new ArrayList<EhrSpringDto>();
		List<Ehr> ehrs = ehrService.getAll();
		for(Ehr ehr : ehrs)
		{
			result.add(new EhrSpringDto(ehr));
		}
		return result;
	}
	@RequestMapping(value=MessageRestURIConstants.MESSAGE_SERVICE_SEND_EMR_DOCUMENT_PDF_URI, method = RequestMethod.POST)
	public @ResponseBody String sendEMRDocumentPdf(
			@PathVariable(value=MessageRestURIConstants.MESSAGE_SERVICE_SEND_EMR_DOCUMENT_PDF_ENCOUNTER_ID_PARAM_NAME)
			Integer encounterId, 
			@RequestBody(required=true) SendDocumentInfo documentInfo) throws RemoteException, ServiceException, InvalidPatientException, IOException, JDOMException {
		super.refreshHibernateSession(context);
		String message = "";
		for(String uuid : documentInfo.getEhrs()){
			Ehr ehr = ehrService.get(uuid);
				
					message = in000002uv01MessageService.sendMessage(encounterId, ehr.getUuid(), ehr.getName(), documentInfo.getEncodedPdf());
			
		}
		return message;
	}
	@RequestMapping(value=MessageRestURIConstants.MESSAGE_SERVICE_SEND_EMR_DOCUMENT_URI, method = RequestMethod.POST)
	public @ResponseBody String sendEMRDocument(
			@PathVariable(value=MessageRestURIConstants.MESSAGE_SERVICE_SEND_EMR_DOCUMENT_ENCOUNTER_ID_PARAM_NAME)
			Integer encounterId, 
			@RequestBody(required=true) List<String> ehrs) {
		super.refreshHibernateSession(context);
		String message = "";
		for(String uuid : ehrs){
			Ehr ehr = ehrService.get(uuid);
				try {
					message = in000002uv01MessageService.sendMessage(encounterId, ehr.getUuid(), ehr.getName());
				} catch (Exception ex) {
					

					List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
					errors.add(new FieldErrorResource(MessageController.class.getCanonicalName(), String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
					throw new InternalErrorException(ex.getMessage(), errors);
				} 
		}
		return message;
	}
	@RequestMapping(value=MessageRestURIConstants.MESSAGE_SERVICE_REQUEST_ANTECEDENTES_PACIENTE_IDENTIFIER_URI, method = RequestMethod.POST)
	public @ResponseBody String requestAntecedentesPaciente(
			@PathVariable(value=MessageRestURIConstants.MESSAGE_SERVICE_REQUEST_ANTECEDENTES_IDENTIFIER_PARAM_NAME)String patientIdentifier, 
			@PathVariable(value=MessageRestURIConstants.MESSAGE_SERVICE_REQUEST_ANTECEDENTES_IDENTIFIER_TYPE_PARAM_NAME)String identifierType,
			@PathVariable(value=MessageRestURIConstants.MESSAGE_SERVICE_REQUEST_ANTECEDENTES_GENDER_PARAM_NAME)String gender) throws RemoteException, FileNotFoundException, ServiceException, JDOMException, IOException, InvalidPatientException {
	
			super.refreshHibernateSession(context);
			return in000031uv01MessageService.sendMessage( patientIdentifier, identifierType,
					 gender);
	
	}
	@RequestMapping(value=MessageRestURIConstants.MESSAGE_SERVICE_REQUEST_ANTECEDENTES_PACIENTE_PATIENT_ID_URI, method = RequestMethod.POST)
	public @ResponseBody String requestAntecedentesPaciente(
			@PathVariable(value=MessageRestURIConstants.MESSAGE_SERVICE_REQUEST_ANTECEDENTES_PATIENT_ID_PARAM_NAME)Integer pacienteId)
	{
		try {
			super.refreshHibernateSession(context);
			return in000031uv01MessageService.sendMessage(pacienteId);
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(MessageController.class.getCanonicalName(), String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}
	
	@RequestMapping(value=MessageRestURIConstants.MESSAGE_SERVICE_GET_ANTECEDENTES_AMP_IDENTIFIER_URI, method = RequestMethod.GET)
	public @ResponseBody List<AntecedentesAmpSpringDto> getAntecedentesAmp(
			@PathVariable(MessageRestURIConstants.MESSAGE_SERVICE_GET_ANTECEDENTES_AMP_IDENTIFIER_IDENTIFIER_PARAM_NAME)String identifier, 
			@PathVariable(MessageRestURIConstants.MESSAGE_SERVICE_GET_ANTECEDENTES_AMP_IDENTIFIER_IDENTIFIER_TYPE_ID_PARAM_NAME)String identifierTypeId)
	{
			super.refreshHibernateSession(context);
			for(PatientIdentifier identifierPatient : patientService.getPatients(identifier))
			{
				//se obtiene el paciente
				Patient patient = patientService.buscarPatient(identifierPatient.getPatient().getPatientId());
				if(identifierTypeId.equals(patient.getPreferredIdentifier().getPatientIdentifierType().getPatientIdentifierTypeId().toString()))
				{
					List<Encounter> encounters = encounterService.obtenerEncuentros(patient, 1, 0);
					if(!encounters.isEmpty())
					{
						Encounter encounter = encounters.get(0);
						List<Lesionado> lesionados =  lesionadoService.getLesionadosEncounter(encounter.getEncounterId());
						if(!lesionados.isEmpty())
						{
							Lesionado lesionado = lesionados.get(0);
							return getAntecedentesAmp(lesionado.getLesionadoId());
						}
						
					}
				}
			}
			return null;
		


	}
	
	@RequestMapping(value=MessageRestURIConstants.MESSAGE_SERVICE_GET_ANTECEDENTES_AMP_URI, method = RequestMethod.GET)
	public @ResponseBody List<AntecedentesAmpSpringDto> getAntecedentesAmp(
			@PathVariable(value=MessageRestURIConstants.MESSAGE_SERVICE_GET_ANTECEDENTES_AMP_LESIONADO_ID_PARAM_NAME)Integer lesionadoId)
	{
		try {
			super.refreshHibernateSession(context);
			Lesionado lesionado = lesionadoService.obtenerLesionado(lesionadoId);
			List<AntecedentesAmpSpringDto> antecedentesAmp = new ArrayList<AntecedentesAmpSpringDto>();
			List<AntecedentesAmp> antecedentes = in000032uv01MessageService
					.getListAntecedentesAMP(lesionadoId);
			for (AntecedentesAmp a : antecedentes) {
				AntecedentesAmp antecedente = new AntecedentesAmp();
				List<AntecedenteAmp> antecedenteAmp = new ArrayList<AntecedenteAmp>();
				for (com.artica.telesalud.message.service.dto.AntecedenteAmp an : a
						.getAntecedentes()) {
					AntecedenteAmp current = new AntecedenteAmp();
					current.setListaAntecedentes(an.getListaAntecedentes());
					current.setListaFechas(an.getListaFechas());
					current.setTipoAntecedente(an.getTipoAntecedente());
					antecedenteAmp.add(current);
				}
				antecedente.setAntecedentes(antecedenteAmp);
				antecedente.setHistoriaClinicaNombre(a
						.getHistoriaClinicaNombre());
				antecedente.setHistoriaClinicaUuid(a.getHistoriaClinicaUuid());
				antecedentesAmp.add(new AntecedentesAmpSpringDto(antecedente, lesionado));
			}
			return antecedentesAmp;
		}  catch (Exception ex) {

			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(MessageController.class.getCanonicalName(), String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}


	}
	
	@ExceptionHandler(InternalErrorException.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody List<FieldErrorResource> handleException(InternalErrorException ex)
	{
		return ex.getErrors();
	}
	@ExceptionHandler(InvalidRequestException.class)
	@ResponseStatus(value=HttpStatus.UNPROCESSABLE_ENTITY)
	public @ResponseBody List<FieldErrorResource> handleException(InvalidRequestException ex)
	{
		return ex.getErrors();
	}
	@RequestMapping(value=MessageRestURIConstants.MESSAGE_SERVICE_GET_PDF_DOCUMENT_URI)
	public @ResponseBody String getPdf( 
			@PathVariable(value=MessageRestURIConstants.MESSAGE_SERVICE_GET_PDF_DOCUMENT_LESIONADO_ID_PARAM_NAME)
	Integer lesionadoId) throws IOException
	{
		PrintHCPH print = new PrintHCPH();
		String filePath = "example.pdf";
		return Base64.encodeBytes(print.createPdfFile(lesionadoId, filePath));
	}
	@Override
	public void setServletContext(ServletContext servletContext) {
		context = servletContext;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(HibernateTPHDAOFactory.TPH_HIBERNATE_CONFIG_FILE,
				servletContext
						.getInitParameter(HibernateProperties.HIBERNATE_CONFIGURATION_FILE_NAME));
		HibernateSessionFactoryManager manager = HibernateSessionFactoryManager
				.getInstance();
		manager.createFactory(servletContext
				.getInitParameter(HibernateProperties.HIBERNATE_CONFIGURATION_FILE_NAME));
		String baseUrlTemplates = servletContext.getInitParameter(HibernateProperties.URL_BASE_XML_TEMPLATES);
		String urlBodyImage = servletContext.getInitParameter(HibernateProperties.URL_BODY_IMAGE_FILE);
		String urlXsl = servletContext.getInitParameter(HibernateProperties.URL_BASE_XSL_TEMPLATES);
		
		ehrService = new EhrService(
				servletContext.getInitParameter(HibernateProperties.PATIENT_MASTER_INDEX_DAO_CLASS_FACTORY_IMPL),
				params);

		in000001uv01MessageService = new IN000001UV01MessageService(
				servletContext
						.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);
		in000002uv01MessageService = new IN000002UV01MessageService(
				servletContext
				.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
		params);

		in000031uv01MessageService = new IN000031UV01MessageService(
				servletContext
				.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);
		in000032uv01MessageService = new IN000032UV01MessageService(
				servletContext
				.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);


		personService = new PersonService(
				servletContext
						.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);
		patientService = new PatientService(
				servletContext
						.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);
		lesionadoService = new LesionadoService(
				servletContext
						.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);
		encounterService = new EncounterService(
				servletContext
						.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);
		in000031uv01MessageService.setUrlBaseTemplates(baseUrlTemplates);
		in000032uv01MessageService.setUrlBaseTemplates(baseUrlTemplates);
		in000032uv01MessageService.setUrlBodyImage(urlBodyImage);
		in000032uv01MessageService.setUrlXsl(urlXsl);
		in000001uv01MessageService.setUrlBaseTemplates(baseUrlTemplates);
		in000002uv01MessageService.setUrlBaseTemplates(baseUrlTemplates);
		in000002uv01MessageService.setUrlBodyImage(urlBodyImage);
		in000002uv01MessageService.setUrlXsl(urlXsl);
		
	}
	
}
