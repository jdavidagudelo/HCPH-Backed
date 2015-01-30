package com.artica.telesalud.tph.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

import com.artica.telesalud.common.data.HibernateSessionFactoryManager;
import com.artica.telesalud.persona.model.impl.UserDTO;
import com.artica.telesalud.persona.service.UserService;
import com.artica.telesalud.tph.concepts.ConceptsCode;
import com.artica.telesalud.tph.controller.constants.EventoRestURIConstants;
import com.artica.telesalud.tph.controller.constants.HibernateProperties;
import com.artica.telesalud.tph.controller.constants.LocationRestURIConstants;
import com.artica.telesalud.tph.controller.exception.FieldErrorResource;
import com.artica.telesalud.tph.controller.exception.InvalidRequestException;
import com.artica.telesalud.tph.controller.model.evento.EventAddressSpringDto;
import com.artica.telesalud.tph.controller.model.evento.EventoSpringDto;
import com.artica.telesalud.tph.controller.user.SessionUserApp;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
import com.artica.telesalud.tph.model.concept.Concept;
import com.artica.telesalud.tph.model.encounter.Encounter;
import com.artica.telesalud.tph.model.evento.EventAddress;
import com.artica.telesalud.tph.model.evento.Evento;
import com.artica.telesalud.tph.model.evento.Lesionado;
import com.artica.telesalud.tph.model.location.City;
import com.artica.telesalud.tph.service.ConceptService;
import com.artica.telesalud.tph.service.EventoService;
import com.artica.telesalud.tph.service.LesionadoService;
import com.artica.telesalud.tph.service.LocationService;
/**
 * Clase utilizada para acceder a la informacion de eventos de emergencias.
 * @author Juan David Agudelo Alvarez. jdaaa2009@gmail.com
 */
@Controller
@RequestMapping(value = EventoRestURIConstants.EVENTO_SERVICE_URI)
public class EventoController extends AbstractController implements
		ServletContextAware {
	private EventoService eventoService;
	private ConceptService conceptService;
	private LocationService locationService;
	private UserService userService;
	private ServletContext context;
	private LesionadoService lesionadoService;

	public EventoController() {
	}

	/**
	 * Permite obtener el evento con el id especificado como argumento.
	 * @param eventoId id de un evento en la base de datos.
	 * @return evento con el id especificado como argumento.
	 */
	@RequestMapping(value = EventoRestURIConstants.EVENTO_SERVICE_GET_EVENTO_URI, method = RequestMethod.GET)
	public @ResponseBody
	EventoSpringDto getEvento(
			@PathVariable(value = EventoRestURIConstants.EVENTO_SERVICE_GET_EVENTO_EVENTO_ID_PARAM_NAME) Integer eventoId) {
		super.refreshHibernateSession(context);
		Evento evento = eventoService.obtenerEvento(eventoId);
		List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();

		if (evento == null) {
			errors.add(new FieldErrorResource(EventoController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.UNPROCESSABLE_ENTITY),
					"El evento no existe en la base de datos"));
			throw new InvalidRequestException(
					"Error al procesar el nuevo evento", errors);
		}
		return new EventoSpringDto(evento);
	}
	/**
	 * Permite finalizar el evento con el id especificado como argumento.
	 * Una vez el evento esta finalizado no puede ser editado nuevamente.
	 * @param eventId id del evento que se desea finalizar.
	 * @return evento finalizado.
	 */
	@RequestMapping(value = EventoRestURIConstants.EVENTO_SERVICE_FINALIZAR_EVENTO_URI, method = RequestMethod.POST)
	public @ResponseBody EventoSpringDto finalizarEvento(
			@PathVariable(value=EventoRestURIConstants.EVENTO_SERVICE_FINALIZAR_EVENTO_EVENT_ID_PARAM_NAME)Integer eventId) {

		Evento event = eventoService.obtenerEvento(eventId);

		Boolean puedeCerrar = true;
		/*
		 * for(Tripulacion tripulacion :
		 * tripulacionService.obtenerTripulaciones(evento)){
		 * if(tripulacion.getHoraRegreso()==null){ puedeCerrar = false; break; }
		 * }
		 */

		for (Lesionado lesionado : lesionadoService.obtenerLesionados(event)) {
			if (lesionado.getEncuentro().getState()
					.equals(Encounter.STATE_ACTIVE)) {
				puedeCerrar = false;
				break;
			}
		}

		if (puedeCerrar) {

			event.setEstado(Evento.ESTADO_FINALIZADO);
			eventoService.actualizarEvento(event);
		}

		return new EventoSpringDto(event);

	}

	/**
	 * Permite actualizar un evento de emergencias.
	 * @param event contiene la nueva informacion del evento de emergencias. Este campo debe
	 * tener obligatoriamente el numero de caso del evento, la causa de atencion del evento, la ciudad
	 * y la zona.
	 * @param userId id del usuario que actualiza el evento.
	 * @return evento actualizado.
	 */
	public EventoSpringDto updateEvento(EventoSpringDto event, Integer userId) {
		super.refreshHibernateSession(context);
		List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
		Evento evento = null;
		if (event.getNumeroCaso() != null
				&& eventoService.existeNumeroCaso(event.getNumeroCaso().trim())) {
			evento = eventoService.getEventoByNumeroCaso(event.getNumeroCaso());
		} else {
			errors.add(new FieldErrorResource(EventoController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.UNPROCESSABLE_ENTITY),
					"El evento no existe en el sistema"));
			throw new InvalidRequestException(
					"Error al procesar el nuevo evento", errors);
		}

		ConceptsCode conceptos = new ConceptsCode();
		if (event.getCausaAtencion() != null) {
			Concept causaAtencion = conceptService.obtenerConcepto(event
					.getCausaAtencion().getConceptId());

			evento.setCausaAtencion(causaAtencion);
		} else {
			errors.add(new FieldErrorResource(EventoController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.UNPROCESSABLE_ENTITY),
					"La causa de atención es obligatoria"));
			throw new InvalidRequestException(
					"Error al procesar el nuevo evento", errors);
		}
		City city = null;
		if (event.getCiudad() != null) {
			city = locationService.obtenerCity(event.getCiudad().getCityId());

		} else {
			errors.add(new FieldErrorResource(EventoController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.UNPROCESSABLE_ENTITY),
					"La ciudad del evento es obligatoria"));
			throw new InvalidRequestException(
					"Error al procesar el nuevo evento", errors);
		}
		evento.setCiudad(city);
		Integer conceptoZona = null;
		if (event.getZona() != null) {
			if (event.getZona().getShortName()
					.equalsIgnoreCase(LocationRestURIConstants.LOCATION_ZONA_RURAL)) {
				conceptoZona = conceptos.cZonaRural();
			} else if (event.getZona().getShortName()
					.equalsIgnoreCase(LocationRestURIConstants.LOCATION_ZONA_URBANA)) {
				conceptoZona = conceptos.cZonaUrbana();
			}
			if (conceptoZona != null) {
				Concept zonaConcept = conceptService
						.obtenerConcepto(conceptoZona);
				evento.setZona(zonaConcept);
			}
		} else {
			errors.add(new FieldErrorResource(EventoController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.UNPROCESSABLE_ENTITY),
					"La zona de ocurrencia es obligatoria"));
			throw new InvalidRequestException(
					"Error al procesar el nuevo evento", errors);
		}
		evento.setDireccion(event.getDireccion());
		evento.setEstado(event.getEstado());
		evento.setFechaLlamada(event.getFechaLlamada());
		evento.setNumeroCaso(event.getNumeroCaso());
		evento.setCreator(userId);
		if (event.getNumeroCaso() != null
				&& eventoService.existeNumeroCaso(event.getNumeroCaso().trim())) {
			evento = eventoService.actualizarEvento(evento);
		} else {
			errors.add(new FieldErrorResource(EventoController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.UNPROCESSABLE_ENTITY),
					"El número de caso es obligatorio"));
			throw new InvalidRequestException(
					"Error al procesar el nuevo evento", errors);
		}
		return new EventoSpringDto(evento);
	}

	/**
	 * Permirte almacenar el evento especificado como argumento en la base de datos.
	 * @param event evento que se desea insertar. Solo el numero de caso es un campo obligatorio.
	 * Si existe un evento con el numero de caso del evento especificado como argumento, el registro
	 * es actualizado.
	 * @param userId id del usuario que crea el evento.
	 * @return
	 */
	@RequestMapping(value = EventoRestURIConstants.EVENTO_SERVICE_SAVE_EVENTO_URI, method = RequestMethod.POST)
	public @ResponseBody
	EventoSpringDto saveEvento(
			@RequestBody EventoSpringDto event,
			@PathVariable(value = EventoRestURIConstants.EVENTO_SERVICE_SAVE_EVENTO_USER_ID_PARAM_NAME) Integer userId) {
		super.refreshHibernateSession(context);
		List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
		Evento evento = null;
		if (event.getNumeroCaso() != null && event.getNumeroCaso().matches("[0-9]+")
				&& eventoService.existeNumeroCaso(event.getNumeroCaso().trim())) {
			evento = eventoService.getEventoByNumeroCaso(event.getNumeroCaso());
		} 
		else if(event.getNumeroCaso() != null && event.getNumeroCaso().matches("[0-9]+"))
		{
			evento = eventoService.crearNuevoEvento();
		}
		else
		{
			return null;
		}
		ConceptsCode conceptos = new ConceptsCode();
		if (event.getCausaAtencion() != null) {
			Concept causaAtencion = conceptService.obtenerConcepto(event
					.getCausaAtencion().getConceptId());
			evento.setCausaAtencion(causaAtencion);
		}
		City city = null;
		if (event.getCiudad() != null) {
			if (event.getCiudad().getCityId() != null) {
				city = locationService.obtenerCity(event.getCiudad()
						.getCityId());
			} else {
				if (event.getCiudad().getName() != null) {
					String cityName = event.getCiudad().getName();
					String stateName = event.getCiudad().getStateProvince()
							.getName();
					String countryName = event.getCiudad().getStateProvince()
							.getCountry().getName();
					city = locationService.getCityByName(cityName, stateName,
							countryName);
				}
			}
		}
		evento.setCiudad(city);
		Integer conceptoZona = null;
		if (event.getZona() != null) {
			if (event.getZona().getShortName() != null) {
				if (event.getZona().getShortName()
						.equalsIgnoreCase(LocationRestURIConstants.LOCATION_ZONA_RURAL)) {
					conceptoZona = conceptos.cZonaRural();
				} else if (event.getZona().getShortName()
						.equalsIgnoreCase(LocationRestURIConstants.LOCATION_ZONA_URBANA)) {
					conceptoZona = conceptos.cZonaUrbana();
				}
			}
			if (conceptoZona != null) {
				Concept zonaConcept = conceptService
						.obtenerConcepto(conceptoZona);
				evento.setZona(zonaConcept);
			}
		}
		if (event.getEventAddress() != null) {
			EventAddressSpringDto eventAddressSpringDto = event
					.getEventAddress();
			EventAddress eventAddress = new EventAddress();
			eventAddress.setBlockName(eventAddressSpringDto.getBlockName());
			eventAddress.setBlockNumber(eventAddressSpringDto.getBlockNumber());
			eventAddress.setEventAddressId(eventAddressSpringDto
					.getEventAddressId());
			eventAddress.setHomeName(eventAddressSpringDto.getHomeName());
			eventAddress.setHomeNumber(eventAddressSpringDto.getHomeNumber());
			eventAddress.setMainWay(eventAddressSpringDto.getMainWay());
			eventAddress.setMainWayDirection(eventAddressSpringDto
					.getMainWayDirection());
			eventAddress.setMainWayNumber(eventAddressSpringDto
					.getMainWayNumber());
			eventAddress.setMainWaySecondaryNumber(eventAddressSpringDto
					.getMainWaySecondaryNumber());
			eventAddress.setSecondaryWayDirection(eventAddressSpringDto
					.getSecondaryWayDirection());
			eventAddress.setSecondaryWayNumber(eventAddressSpringDto
					.getSecondaryWayNumber());
			eventAddress.setSecondaryWaySecondaryNumber(eventAddressSpringDto
					.getSecondaryWaySecondaryNumber());
			eventAddress.setWayNumberIdentifier(eventAddressSpringDto
					.getWayNumberIdentifier());
			evento.setEventAddress(eventAddress);
		}
		evento.setDireccion(event.getDireccion());
		evento.setOtherCause(event.getOtherCause());
		evento.setEstado(event.getEstado());
		evento.setFechaLlamada(event.getFechaLlamada());
		evento.setNumeroCaso(event.getNumeroCaso());
		evento.setCreator(userId);
		evento.setCoordinates(event.getCoordinates());
		if (event.getNumeroCaso() != null
				&& eventoService.existeNumeroCaso(event.getNumeroCaso().trim())) {
			evento = eventoService.actualizarEvento(evento);
		} else if (event.getNumeroCaso() != null) {
			evento = eventoService.guardarEvento(evento);
		} else {
			errors.add(new FieldErrorResource(EventoController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.UNPROCESSABLE_ENTITY),
					"El número de caso es obligatorio"));
			throw new InvalidRequestException(
					"Error al procesar el nuevo evento", errors);
		}
		return new EventoSpringDto(evento);
	}

	/**
	 * Lista de eventos de emergenciasa los que tiene acceso el usuario especificado como argumento.
	 * @param userId id del usuario que realiza la consulta.
	 * @param response 
	 * @return lista de eventos a los que tiene acceso el usuario especificado como argumento.
	 */
	@RequestMapping(value = EventoRestURIConstants.EVENTO_SERVICE_GET_EVENTOS_URI, method = RequestMethod.GET)
	public @ResponseBody
	List<EventoSpringDto> getEventos(
			@PathVariable(value = EventoRestURIConstants.EVENTO_SERVICE_GET_EVENTOS_USER_ID_PARAM_NAME) Integer userId,
			final HttpServletResponse response) {

		super.refreshHibernateSession(context);
		UserDTO user = userService.findUserById(userId);
		if (user != null) {
			Integer userRoleId = user.getRoleId();
			List<Evento> eventos = null;
			if (SessionUserApp.isEquipoIntervencion(userRoleId)) {

				eventos = eventoService.obtenerEventosActivos(userId);

			} else if (SessionUserApp.isDigitador(userRoleId)
					|| SessionUserApp.isMedicoDigitador(userRoleId)) {
				eventos = eventoService.obtenerEventosActivos();
			} else if (SessionUserApp.isMedicoTeleasistencia(userRoleId)) {
				eventos = eventoService.obtenerEventosSolicitudTeleasistencia();
			}
			if (eventos != null) {
				List<EventoSpringDto> result = new ArrayList<EventoSpringDto>();
				for (Evento evento : eventos) {
					EventoSpringDto event = new EventoSpringDto(evento);
					event.setPatientsCount(lesionadoService.obtenerLesionados(evento).size()); 
					result.add(event);
				}
				return result;
			}
		}
		return new ArrayList<EventoSpringDto>();
	}

	@ExceptionHandler(InvalidRequestException.class)
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

		eventoService = new EventoService(
				servletContext
						.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);
		locationService = new LocationService(
				servletContext
						.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);
		conceptService = new ConceptService(
				servletContext
						.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);
		lesionadoService = new LesionadoService(
				servletContext
						.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);

		userService = new com.artica.telesalud.persona.service.UserService(
				servletContext
						.getInitParameter(HibernateProperties.PERSONA_DAO_CLASS_FACTORY_IMPL),
				params);
	}

}
