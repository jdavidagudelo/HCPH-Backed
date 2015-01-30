package com.artica.telesalud.tph.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.ServletContextAware;

import com.artica.telesalud.common.data.HibernateSessionFactoryManager;
import com.artica.telesalud.tph.controller.constants.HibernateProperties;
import com.artica.telesalud.tph.controller.constants.TripulacionRestURIConstants;
import com.artica.telesalud.tph.controller.exception.FieldErrorResource;
import com.artica.telesalud.tph.controller.exception.InternalErrorException;
import com.artica.telesalud.tph.controller.exception.InvalidRequestException;
import com.artica.telesalud.tph.controller.model.evento.TripulacionSpringDto;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
import com.artica.telesalud.tph.model.evento.Evento;
import com.artica.telesalud.tph.model.evento.Tripulacion;
import com.artica.telesalud.tph.service.EventoService;
import com.artica.telesalud.tph.service.TripulacionService;

@Controller
@RequestMapping(value=TripulacionRestURIConstants.TRIPULACION_SERVICE_URI)
public class TripulacionController extends AbstractController implements ServletContextAware{
	private TripulacionService tripulacionService;
	private EventoService eventoService;
	private ServletContext context;
	/**
	 * Lista de las tripulaciones de un evento de emergencias.
	 * @param eventoId id del evento para el que se desean obtener las tripulaciones.
	 * @return lista de tripulaciones de un evento de emergencias.
	 */
	@RequestMapping(value=TripulacionRestURIConstants.TRIPULACION_SERVICE_GET_TRIPULACIONES_EVENTO_URI)
	public @ResponseBody List<TripulacionSpringDto> getTripulacionesEvento(
			@PathVariable(value=TripulacionRestURIConstants.TRIPULACION_SERVICE_GET_TRIPULACIONES_EVENTO_EVENTO_ID_PARAM_NAME)
			Integer eventoId)
	{
		try
		{
			super.refreshHibernateSession(context);
		List<TripulacionSpringDto> result = new ArrayList<TripulacionSpringDto>();
		Evento evento = eventoService.obtenerEvento(eventoId);
		for(Tripulacion tripulacion : tripulacionService.obtenerTripulaciones(evento))
		{
			result.add(new TripulacionSpringDto(tripulacion));
		}
		return result;
		}
		catch(Exception ex)
		{
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(TripulacionController.class.getCanonicalName(), 
					String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
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

		tripulacionService = new TripulacionService(
				servletContext
						.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);
		eventoService = new EventoService(
				servletContext
						.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);
	
	}
}
