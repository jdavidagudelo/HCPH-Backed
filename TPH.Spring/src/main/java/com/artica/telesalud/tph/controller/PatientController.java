package com.artica.telesalud.tph.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.ServletContextAware;

import com.artica.telesalud.common.data.HibernateSessionFactoryManager;
import com.artica.telesalud.tph.controller.constants.HibernateProperties;
import com.artica.telesalud.tph.controller.constants.PatientRestURIConstants;
import com.artica.telesalud.tph.controller.exception.FieldErrorResource;
import com.artica.telesalud.tph.controller.exception.InternalErrorException;
import com.artica.telesalud.tph.controller.exception.InvalidRequestException;
import com.artica.telesalud.tph.controller.model.patient.PatientIdentifierTypeSpringDto;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
import com.artica.telesalud.tph.model.patient.PatientIdentifierType;
import com.artica.telesalud.tph.service.PatientService;
/**
 * 
 * @author Juan David Agudelo. jdaaa2009@gmail.com
 *
 */
@Controller
@RequestMapping(value = PatientRestURIConstants.PATIENT_SERVICE_URI)
public class PatientController extends AbstractController implements ServletContextAware {
	private PatientService patientService;
	private ServletContext context;
	/**
	 * Permite obtener la lista de los tipos de identificacion del sistema.
	 * @return lista de tipos de identificacion.
	 */
	@RequestMapping(value = PatientRestURIConstants.PATIENT_SERVICE_GET_PATIENT_IDENTIFIER_TYPES_URI, method = RequestMethod.GET)
	public @ResponseBody
	List<PatientIdentifierTypeSpringDto> getPatientIdentifierTypes() {
		
			super.refreshHibernateSession(context);
		List<PatientIdentifierTypeSpringDto> result = new ArrayList<PatientIdentifierTypeSpringDto>();
		List<PatientIdentifierType> patientIdentifierTypes = patientService
				.getPatientIdentifierTypes();
		for (PatientIdentifierType patientIdentifierType : patientIdentifierTypes) {
			PatientIdentifierTypeSpringDto current = new PatientIdentifierTypeSpringDto(
					patientIdentifierType);
			result.add(current);
		}
		return result;
		
		
	}
	
	@ExceptionHandler(InternalErrorException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody
	List<FieldErrorResource> handleException(InternalErrorException ex) {
		return ex.getErrors();
	}

	@ExceptionHandler(InvalidRequestException.class)
	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	public @ResponseBody
	List<FieldErrorResource> handleException(InvalidRequestException ex) {
		return ex.getErrors();
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		context = servletContext;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(
				HibernateTPHDAOFactory.TPH_HIBERNATE_CONFIG_FILE,
				servletContext
						.getInitParameter(HibernateProperties.HIBERNATE_CONFIGURATION_FILE_NAME));
		HibernateSessionFactoryManager manager = HibernateSessionFactoryManager
				.getInstance();
		manager.createFactory(servletContext
				.getInitParameter(HibernateProperties.HIBERNATE_CONFIGURATION_FILE_NAME));

		patientService = new PatientService(
				servletContext
						.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);
	}

}
