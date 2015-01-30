package com.artica.telesalud.tph.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.ServletContextAware;

import com.artica.telesalud.common.data.HibernateSessionFactoryManager;
import com.artica.telesalud.tph.controller.constants.HibernateProperties;
import com.artica.telesalud.tph.controller.constants.HospitalRestURIConstants;
import com.artica.telesalud.tph.controller.exception.FieldErrorResource;
import com.artica.telesalud.tph.controller.exception.InternalErrorException;
import com.artica.telesalud.tph.controller.exception.InvalidRequestException;
import com.artica.telesalud.tph.controller.model.evento.HospitalSpringDto;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
import com.artica.telesalud.tph.model.evento.Hospital;
import com.artica.telesalud.tph.service.HospitalService;
@Controller
@RequestMapping(value=HospitalRestURIConstants.HOSPITAL_SERVICE_URI)
public class HospitalController extends AbstractController implements ServletContextAware{
	private HospitalService hospitalService;
	private ServletContext context;
	/**
	 * Lista de hospitales del sistema.
	 * @return lista de hospitales a los que puede ser remitido un paciente.
	 */
	@RequestMapping(value = HospitalRestURIConstants.HOSPITAL_SERVICE_GET_HOSPITALES_URI)
	public @ResponseBody List<HospitalSpringDto> getHospitales()
	{/*
		try{*/
			super.refreshHibernateSession(context);
		List<HospitalSpringDto> result = new ArrayList<HospitalSpringDto>();
		for(Hospital hospital : hospitalService.obtenerHospitales())
		{
			result.add(new HospitalSpringDto(hospital));
		}
		return result;
		/*}
		catch(Exception ex)
		{
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(HospitalController.class.getCanonicalName(), 
					String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}*/
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
		params.put(HibernateTPHDAOFactory.TPH_HIBERNATE_CONFIG_FILE,
				servletContext.getInitParameter(HibernateProperties.HIBERNATE_CONFIGURATION_FILE_NAME));
		HibernateSessionFactoryManager manager = HibernateSessionFactoryManager
				.getInstance();
		manager.createFactory(
				servletContext.getInitParameter(HibernateProperties.HIBERNATE_CONFIGURATION_FILE_NAME));

		hospitalService = new HospitalService(
				servletContext.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);
		}
}
