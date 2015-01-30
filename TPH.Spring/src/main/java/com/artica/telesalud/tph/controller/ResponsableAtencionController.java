package com.artica.telesalud.tph.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

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
import com.artica.telesalud.persona.model.impl.UserDTO;
import com.artica.telesalud.persona.service.UserService;
import com.artica.telesalud.tph.controller.constants.HibernateProperties;
import com.artica.telesalud.tph.controller.constants.ResponsableAtencionRestURIConstants;
import com.artica.telesalud.tph.controller.exception.FieldErrorResource;
import com.artica.telesalud.tph.controller.exception.InternalErrorException;
import com.artica.telesalud.tph.controller.exception.InvalidRequestException;
import com.artica.telesalud.tph.controller.model.evento.ResponsableAtencionSpringDto;
import com.artica.telesalud.tph.controller.model.patient.PersonSpringDto;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
import com.artica.telesalud.tph.model.encounter.Encounter;
import com.artica.telesalud.tph.model.evento.Lesionado;
import com.artica.telesalud.tph.model.evento.ResponsableAtencion;
import com.artica.telesalud.tph.model.patient.Patient;
import com.artica.telesalud.tph.model.person.Person;
import com.artica.telesalud.tph.service.LesionadoService;
import com.artica.telesalud.tph.service.PatientService;
import com.artica.telesalud.tph.service.PersonaService;
import com.artica.telesalud.tph.service.ResponsableAtencionService;
/**
 * Permite almacenar los responsables de la atencion de un paciente en la base de datos.
 * @author Juan David Agudelo Alvarez. jdaaa2009@gmail.com
 *
 */
@Controller
@RequestMapping(value = ResponsableAtencionRestURIConstants.RESPONSABLE_ATENCION_SERVICE_URI)
public class ResponsableAtencionController extends AbstractController implements
		ServletContextAware {
	private LesionadoService lesionadoService;
	private ResponsableAtencionService responsableAtencionService;
	private PatientService patientService;
	private PersonaService personaService;
	private UserService userService;
	private ServletContext context;
	/**
	 * Permite completar la informacion del lesionado especificado como argumento.
	 * @param lesionado lesionado que se desea completar.
	 * @return lesionado completado.
	 */
	private Lesionado fillLesionado(Lesionado lesionado) {
		Encounter encounter = lesionado.getEncuentro();
		Patient patient = encounter.getPatient();
		patient = patientService.buscarPatient(encounter.getPatient()
				.getPatientId());
		encounter.setPatient(patient);
		lesionado.setEncuentro(encounter);

		return lesionado;
	}

	/**
	 * Permite obtener la lista de usuarios del sistema.
	 * @return lista de usuarios del sistema.
	 */
	@RequestMapping(value = ResponsableAtencionRestURIConstants.RESPONSABLE_ATENCION_SERVICE_GET_USERS_URI, method = RequestMethod.GET)
	public @ResponseBody
	List<PersonSpringDto> getUsers() {
		List<UserDTO> users = userService.readAll();
		List<PersonSpringDto> persons = new ArrayList<PersonSpringDto>();
		for (UserDTO user : users) {
			Person person = personaService.getPersonById(user.getPersonId());
			persons.add(new PersonSpringDto(person));
		}
		return persons;
	}

	/**
	 * Persona.
	 * @param personId
	 * @return
	 */
	private Person getPerson(Integer personId) {
		return personaService.getPersonById(personId);
	}
	/**
	 * Permite obtener la lista de los responsables de atencion del responsable especificado como argumento.
	 * @param lesionadoId id del lesionado para el que se desean obtener los responsables
	 * de atencion.
	 * @return lista de responsables de atencion del lesionado con el id especificado como argumento.
	 */
	@RequestMapping(value = ResponsableAtencionRestURIConstants.RESPONSABLE_ATENCION_SERVICE_GET_RESPONSABLES_ATENCION_URI, method = RequestMethod.GET)
	public @ResponseBody
	List<ResponsableAtencionSpringDto> getResponsablesAtencion(
			@PathVariable(value = ResponsableAtencionRestURIConstants.RESPONSABLE_ATENCION_SERVICE_GET_RESPONSABLES_ATENCION_LESIONADO_ID_PARAM_NAME) Integer lesionadoId) {
		try {
			super.refreshHibernateSession(context);
			Lesionado lesionado = lesionadoService
					.obtenerLesionado(lesionadoId);
			List<ResponsableAtencion> list = responsableAtencionService
					.obtenerResponsablesAtencion(lesionado);
			List<ResponsableAtencionSpringDto> result = new ArrayList<ResponsableAtencionSpringDto>();
			for (ResponsableAtencion responsable : list) {
				responsable.setLesionado(fillLesionado(responsable
						.getLesionado()));
				responsable.setPerson(getPerson(responsable.getPersona()));
				ResponsableAtencionSpringDto created = new ResponsableAtencionSpringDto(
						responsable);
				result.add(created);
			}
			return result;
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(
					ResponsableAtencionController.class.getCanonicalName(),
					String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex
							.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}

	/**
	 * Permite almacenar el responsable de atencion especificado como argumento en la base
	 * de datos.
	 * @param responsableAtencion informacion del responsable de la atencion.
	 * @param userId id del usuario que crea el responsable de la atencion.
	 * @return responsable de la atencion almacenado en la base de datos.
	 */
	@RequestMapping(value = ResponsableAtencionRestURIConstants.RESPONSABLE_ATENCION_SERVICE_SAVE_RESPONSABLE_ATENCION_URI)
	public @ResponseBody
	ResponsableAtencionSpringDto saveResponsableAtencion(
			@RequestBody() ResponsableAtencionSpringDto responsableAtencion,
			@PathVariable(value = ResponsableAtencionRestURIConstants.RESPONSABLE_ATENCION_SERVICE_SAVE_RESPONSABLE_ATENCION_USER_ID_PARAM_NAME) Integer userId) {
		super.refreshHibernateSession(context);
		if (responsableAtencion != null
				&& responsableAtencion.getLesionado() != null) {
			
			Integer lesionadoId = responsableAtencion.getLesionado()
					.getLesionadoId();
			Lesionado lesionado = lesionadoService
					.obtenerLesionado(lesionadoId);
			if (lesionado != null) {
				if (responsableAtencion.getPerson() != null) {
					PersonSpringDto person = responsableAtencion.getPerson();
					
					Integer personId = responsableAtencion.getPerson()
							.getPersonId();
					StringBuilder nombres = new StringBuilder();
					if (person.getPreferredName()
							.getGivenName() != null) {
						nombres.append(responsableAtencion.getPerson()
								.getPreferredName().getGivenName());
					}
					if (person.getPreferredName()
							.getMiddleName() != null) {
						nombres.append(responsableAtencion.getPerson()
								.getPreferredName().getMiddleName());
					}
					StringBuilder apellidos = new StringBuilder();
					if (person.getPreferredName()
							.getFamilyName() != null) {
						apellidos.append(responsableAtencion.getPerson()
								.getPreferredName().getFamilyName());
					}
					if (person.getPreferredName()
							.getFamilyName2() != null) {
						apellidos.append(responsableAtencion.getPerson()
								.getPreferredName().getFamilyName2());
					}
					String registro = responsableAtencion.getPerson()
							.getRegistro();
					Boolean isExternal = responsableAtencion.getExterno();

					Integer controlAph = null;
					if (person.getControlAph() != null) {
						controlAph = responsableAtencion.getPerson()
								.getControlAph().getConceptId();
					}
					return new ResponsableAtencionSpringDto(
							responsableAtencionService.guardar(lesionado,
									personId, nombres.toString(),
									apellidos.toString(), registro, isExternal,
									controlAph, userId, responsableAtencion.getResponsableAtencionId()));
				}
			}
		}
		return null;
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

		lesionadoService = new LesionadoService(
				servletContext
						.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);
		responsableAtencionService = new ResponsableAtencionService(
				servletContext
						.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);
		patientService = new PatientService(
				servletContext
						.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);
		personaService = new PersonaService(
				servletContext
						.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);
		userService = new UserService(
				servletContext
						.getInitParameter(HibernateProperties.PERSONA_DAO_CLASS_FACTORY_IMPL),
				params);
	}
}
