package com.artica.telesalud.tph.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.xml.transform.TransformerException;

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
import com.artica.telesalud.message.service.dto.AntecedenteAmp;
import com.artica.telesalud.message.service.dto.AntecedentesAmp;
import com.artica.telesalud.persona.model.impl.UserDTO;
import com.artica.telesalud.persona.service.UserService;
import com.artica.telesalud.tph.concepts.ConceptsCode;
import com.artica.telesalud.tph.controller.constants.HibernateProperties;
import com.artica.telesalud.tph.controller.constants.LesionadoRestURIConstants;
import com.artica.telesalud.tph.controller.exception.FieldErrorResource;
import com.artica.telesalud.tph.controller.exception.InternalErrorException;
import com.artica.telesalud.tph.controller.exception.InvalidRequestException;
import com.artica.telesalud.tph.controller.model.amp.AntecedentesAmpSpringDto;
import com.artica.telesalud.tph.controller.model.concept.ConceptSpringDto;
import com.artica.telesalud.tph.controller.model.encounter.EncounterSpringDto;
import com.artica.telesalud.tph.controller.model.evento.EventAddressSpringDto;
import com.artica.telesalud.tph.controller.model.evento.LesionadoSpringDto;
import com.artica.telesalud.tph.controller.model.evento.PrimerRespondienteSpringDto;
import com.artica.telesalud.tph.controller.model.evento.ResponsableAtencionSpringDto;
import com.artica.telesalud.tph.controller.model.observation.AntecedenteSpringDto;
import com.artica.telesalud.tph.controller.model.observation.CierreAtencionSpringDto;
import com.artica.telesalud.tph.controller.model.observation.EvaluacionCompletaSpringDto;
import com.artica.telesalud.tph.controller.model.observation.EvaluacionSpringDto;
import com.artica.telesalud.tph.controller.model.observation.HallazgoSpringDto;
import com.artica.telesalud.tph.controller.model.observation.InsumoSpringDto;
import com.artica.telesalud.tph.controller.model.observation.NotaAclaratoriaSpringDto;
import com.artica.telesalud.tph.controller.model.observation.NotaEvolucionSpringDto;
import com.artica.telesalud.tph.controller.model.observation.ProcedimientosSpringDto;
import com.artica.telesalud.tph.controller.model.observation.SignosVitalesSpringDto;
import com.artica.telesalud.tph.controller.model.observation.TeleasistenciaSpringDto;
import com.artica.telesalud.tph.controller.model.patient.PatientSpringDto;
import com.artica.telesalud.tph.controller.model.user.UserSpringDto;
import com.artica.telesalud.tph.controller.user.SessionUserApp;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
import com.artica.telesalud.tph.message.service.interactions.IN000032UV01MessageService;
import com.artica.telesalud.tph.model.concept.Concept;
import com.artica.telesalud.tph.model.encounter.Encounter;
import com.artica.telesalud.tph.model.evento.EventAddress;
import com.artica.telesalud.tph.model.evento.Evento;
import com.artica.telesalud.tph.model.evento.Lesionado;
import com.artica.telesalud.tph.model.evento.PrimerRespondiente;
import com.artica.telesalud.tph.model.evento.ResponsableAtencion;
import com.artica.telesalud.tph.model.observation.Obs;
import com.artica.telesalud.tph.model.observation.ObservationData;
import com.artica.telesalud.tph.model.patient.Patient;
import com.artica.telesalud.tph.model.patient.PatientIdentifier;
import com.artica.telesalud.tph.model.patient.PersonComplete;
import com.artica.telesalud.tph.model.person.Person;
import com.artica.telesalud.tph.service.ConceptService;
import com.artica.telesalud.tph.service.EncounterService;
import com.artica.telesalud.tph.service.EventoService;
import com.artica.telesalud.tph.service.HospitalService;
import com.artica.telesalud.tph.service.LesionadoService;
import com.artica.telesalud.tph.service.ObsService;
import com.artica.telesalud.tph.service.PatientService;
import com.artica.telesalud.tph.service.PersonService;
import com.artica.telesalud.tph.service.PersonaService;
import com.artica.telesalud.tph.service.ResponsableAtencionService;
/**
 * Permite obtener y modificar la informacion de los pacientes lesionados en un evento de emergencias.
 * @author Juan David Agudelo. jdaaa2009@gmail.com
 *
 */
@Controller
@RequestMapping(value = LesionadoRestURIConstants.LESIONADO_SERVICE_URI)
public class LesionadoController extends AbstractController implements
		ServletContextAware {
	private LesionadoService lesionadoService;
	private EventoService eventoService;
	private UserService userService;
	private PatientService patientService;
	private ResponsableAtencionService responsableAtencionService;
	private ConceptService conceptService;
	private PersonaService personaService;
	private ObsService obsService;
	private EncounterService encounterService;
	private HospitalService hospitalService;
	private ServletContext context;
	private PersonService personService;
	private IN000032UV01MessageService in000032uv01MessageService;

	/**
	 * Completa la informacion del lesionado especificado como argumento.
	 * @param lesionado lesionado al que se desea completar la informacion.
	 * @return lesionado con la informacion completa.
	 */
	private Lesionado fillLesionado(Lesionado lesionado) {
		Encounter encounter = lesionado.getEncuentro();
		Patient patient = encounter.getPatient();
		patient = patientService.buscarPatient(encounter.getPatient()
				.getPatientId());
		PatientIdentifier identifier = patientService
				.getPatientIdentifier(patient);
		patient.setPreferredIdentifier(identifier);
		encounter.setPatient(patient);
		lesionado.setEncuentro(encounter);

		return lesionado;
	}
	/**
	 * Completa la informacion del encuentro especificado como argumento.
	 * @param encounter encuentro que se desea completar.
	 * @return encuentro completado.
	 */
	private Encounter fillEncounter(Encounter encounter) {

		Patient patient = encounter.getPatient();
		patient = patientService.buscarPatient(encounter.getPatient()
				.getPatientId());
		PatientIdentifier identifier = patientService
				.getPatientIdentifier(patient);
		patient.setPreferredIdentifier(identifier);
		encounter.setPatient(patient);
		return encounter;
	}

	/**
	 * Permite obtener la lista de los pacientes lesionados en un evento de emergencias.
	 * @param eventId id del evento.
	 * @param userId id del usuario que desea consultar los lesionados.
	 * @return lista de pacientes lesionados en un evento de emergencias.
	 * @throws TransformerException 
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	@RequestMapping(value = LesionadoRestURIConstants.LESIONADO_SERVICE_GET_LESIONADOS_EVENTO_URI, method = RequestMethod.GET)
	public @ResponseBody
	List<LesionadoSpringDto> getLesionadosEvento(
			@PathVariable(value = LesionadoRestURIConstants.LESIONADO_SERVICE_GET_LESIONADOS_EVENTO_EVENTO_ID_PARAM_NAME) Integer eventId,
			@PathVariable(value = LesionadoRestURIConstants.LESIONADO_SERVICE_GET_LESIONADOS_EVENTO_USER_ID_PARAM_NAME) Integer userId) throws JDOMException, IOException, TransformerException {
		/*try*/ {
			super.refreshHibernateSession(context);
			List<Lesionado> lesionados = new ArrayList<Lesionado>();
			if (eventId != null) {
				Evento event = eventoService.obtenerEvento(eventId);

				UserDTO user = userService.findUserById(userId);
				Integer role = user.getRoleId();
				if (SessionUserApp.isEquipoIntervencion(role)) {
					lesionados = lesionadoService.obtenerLesionados(event);
				} else if (SessionUserApp.isDigitador(role)
						|| SessionUserApp.isMedicoDigitador(role)
						|| SessionUserApp.isAdmin(role)) {
					lesionados = lesionadoService.obtenerLesionados(event);
				} else if (SessionUserApp.isMedicoTeleasistencia(role)) {
					lesionados = lesionadoService
							.obtenerLesionadosSolicitudTeleasistencia(event);
				}
			}
			List<LesionadoSpringDto> result = new ArrayList<LesionadoSpringDto>();
			for (Lesionado lesionado : lesionados) {
				result.add(completeLesionado(lesionado));
			}
			return result;
		} /*catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(LesionadoController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}*/
	}


	/**
	 * Permite obtener las listas asociadas a un lesionado en un evento de emergencias a partir de la base de datos.
	 * @param lesionado lesionado que se desea completar.
	 * @return lesionado que sera usado como retorno del servicio RESTFul.
	 * @throws TransformerException 
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	private LesionadoSpringDto completeLesionado(Lesionado lesionado) throws JDOMException, IOException, TransformerException
	{
		fillLesionado(lesionado);
		LesionadoSpringDto current = new LesionadoSpringDto(lesionado);
		current.setAntecedentesAmp(getAntecedentesAmp(lesionado));
		Encounter encounter = lesionado.getEncuentro();
		current.setSignosVitales(getSignosVitales(encounter));
		current.setTeleasistencias(getTeleasistencias(encounter));
		current.setResponsables(getResponsables(lesionado));
		current.setNotasAclaratorias(getNotasAclaratorias(lesionado));
		current.setRespondientes(getRespondientes(lesionado));
		current .setEvaluaciones(getEvaluaciones(current, encounter));
		current.setAntecedentes(getAntecedentes(current, lesionado));
		current.setCierreAtencion(getCierreAtencion( lesionado, encounter));
		ConceptsCode concepts = new ConceptsCode();
		Obs obsHC = obsService.obtenerObservacionPorConcepto(lesionado.getEncuentro(), concepts.cHallazgosClinicos());
		if(obsHC != null && obsHC.getValueText() != null){
			current.setHallazgosClinicos(obsHC.getValueText());
		}
		Obs obsID = obsService.obtenerObservacionPorConcepto(lesionado.getEncuentro(), concepts.cImpresionDiagnostica());
		if(obsID != null && obsID.getValueText() != null)
		{
			current.setImpresionDiagnostica(obsID.getValueText());
		}
		return current;
	}
	/**
	 * Permite obtener el lesionado con el id especificado como argumento.
	 * @param lesionadoId id del lesionado.
	 * @return lesionado con el id especificado como argumento.
	 */
	@RequestMapping(value = LesionadoRestURIConstants.LESIONADO_SERVICE_GET_LESIONADO_URI)
	public @ResponseBody
	LesionadoSpringDto getLesionado(
			@PathVariable(value = LesionadoRestURIConstants.LESIONADO_SERVICE_GET_LESIONADO_LESIONADO_ID_PARAM_NAME) Integer lesionadoId) {
		try {
			super.refreshHibernateSession(context);
			
			return completeLesionado(fillLesionado(lesionadoService
					.obtenerLesionado(lesionadoId)));
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(LesionadoController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}

	/**
	 * Permite obtener la lista de responsables de la atencion de un lesionado en un evento de emergencias.
	 * @param lesionado lesionado para el que se desean obtener los responsables de la atencion.
	 * @return lista de responsables de la atencion de un paciente.
	 */
	private List<ResponsableAtencionSpringDto> getResponsables(
			Lesionado lesionado) {
		/*try */{
			List<ResponsableAtencion> list = responsableAtencionService
					.obtenerResponsablesAtencion(lesionado);
			List<ResponsableAtencionSpringDto> result = new ArrayList<ResponsableAtencionSpringDto>();
			for (ResponsableAtencion responsable : list) {
				responsable.setLesionado(lesionado);
				responsable.setPerson(getPerson(responsable.getPersona()));
				ResponsableAtencionSpringDto created = new ResponsableAtencionSpringDto(
						responsable);
				result.add(created);
			}
			return result;
		} /*catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(
					ResponsableAtencionController.class.getCanonicalName(),
					String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex
							.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}*/
	}

	/**
	 * Permite obtener la persona con el id especificado como argumento.
	 * @param personId id de la persona.
	 * @return persona con el id especificado como argumento.
	 */
	private Person getPerson(Integer personId) {
		return personaService.getPersonById(personId);
	}

	/**
	 * Permite obtener la lista de las evaluaciones realizadas a un paciente lesionado en un evento de emergencias.
	 * @param lesionado lesionado para el que se desean obtener las evaluaciones.
	 * @param encounter encuentro para el que se desean obtener las evaluaciones.
	 * @return lista de evaluaciones realizadas al lesionado especificado como argumento.
	 */
	private List<EvaluacionSpringDto> getEvaluaciones(LesionadoSpringDto lesionado, Encounter encounter) {
		
		List<ObservationData> observations = obsService
				.getEvaluaciones(encounter);
		List<EvaluacionSpringDto> result = new ArrayList<EvaluacionSpringDto>();
		encounter = fillEncounter(encounter);
		for (ObservationData observationdata : observations) {
			EvaluacionSpringDto evaluacion = new EvaluacionSpringDto(
					observationdata.getSons(), conceptService);
			EvaluacionCompletaSpringDto e = new EvaluacionCompletaSpringDto(
					observationdata, encounter);
			evaluacion.setProcedimientos(getProcedimientosHistorico(
					encounter, e.getObsId()));
			evaluacion.setEvaluacion(e);
			evaluacion.setInsumos(getInsumos(encounter,
					e.getObsId()));
			evaluacion.setHallazgos(getHallazgos(encounter,
					e.getObsId()));
			
			result.add(evaluacion);
		}
		return result;
	}
	/**
	 * Lista de primeros respondientes del lesionado especificado como argumento.
	 * @param lesionado lesionado para el que se desean conocer los primeros respondientes.
	 * @return lista de primeros respondientes del lesionado especificado como argumento.
	 */
	private List<PrimerRespondienteSpringDto> getRespondientes(Lesionado lesionado)
	{
		List<PrimerRespondienteSpringDto> result = new ArrayList<PrimerRespondienteSpringDto>();
		for(PrimerRespondiente respondiente : lesionadoService.obtenerPrimerosRespondientes(lesionado))
		{
			result.add(new PrimerRespondienteSpringDto(respondiente));
		}
		return result;
	}
	/**
	 * Permite obtener los procedimientos realizados a la evaluacion especificada como argumento.
	 * @param encounter encuentro para el que se desean obtener los procedimientos.
	 * @param obsParentId id de la evaluacion para la que se desean obtener los procedimientos.
	 * @return procedimientos asociados a la evaluacion especificada como argumento.
	 */
	private ProcedimientosSpringDto getProcedimientosHistorico(
			Encounter encounter, Integer obsParentId) {
		Obs obsParent = obsService.getObs(obsParentId);
		List<ObservationData> procedimientos = obsService.getProcedimientos(
				encounter, obsParent);
		if (procedimientos != null && !procedimientos.isEmpty()) {
			ObservationData od = procedimientos.get(0);
			return new ProcedimientosSpringDto(od, od.getSons(), encounter,
					obsParent);
		}
		return null;
	}
	/**
	 * Lista de insumos asociados a la evaluacion especificada como argumento.
	 * @param encounter encuentro para el que se desean encontrar los insumos.
	 * @param obsParentId id de la evaluacion para la que se desean hallar los insumos.
	 * @return lista de insumos asociados a la evaluacion especificada como argumento.
	 */
	private List<InsumoSpringDto> getInsumos(Encounter encounter,
			Integer obsParentId) {
		Obs obsParent = obsService.getObs(obsParentId);
		List<ObservationData> hallazgos = obsService.getInsumos(encounter,
				obsParent);
		List<InsumoSpringDto> result = new ArrayList<InsumoSpringDto>();
		if (hallazgos != null && !hallazgos.isEmpty()) {
			for (ObservationData data : hallazgos) {
				result.add(new InsumoSpringDto(conceptService, data, encounter, obsParent));
			}
		}
		return result;
	}
	/**
	 * Permite obtener la lista de hallazgos de la evaluacion especificada como argumento.
	 * @param encounter encuentro para el que se desean hallar los hallazgos.
	 * @param obsParentId id de la evaluacion para la que se desean obtener los hallazgos.
	 * @return lista de hallazgos de la evaluacion especificada como argumento.
	 */
	private List<HallazgoSpringDto> getHallazgos(Encounter encounter,
			Integer obsParentId) {
		Obs obsParent = obsService.getObs(obsParentId);
		List<ObservationData> hallazgos = obsService.getHallazgos(encounter,
				obsParent);
		List<HallazgoSpringDto> result = new ArrayList<HallazgoSpringDto>();
		if (hallazgos != null && !hallazgos.isEmpty()) {
			for (ObservationData data : hallazgos) {
				result.add(new HallazgoSpringDto(data, encounter, obsParent));
			}
		}
		return result;
	}
/**
 * Permite crear un lesionado para el evento con el id especificado como argumento.
 * @param eventoId id del evento para el que se desea crear el lesionado.
 * @param creatorId id del usuario que crea el lesionado.
 * @return lesionado creado en la base de datos.
 */
	@RequestMapping(value = LesionadoRestURIConstants.LESIONADO_SERVICE_CREATE_LESIONADO_URI, method = RequestMethod.POST)
	public @ResponseBody
	LesionadoSpringDto createLesionado(
			@PathVariable(value = LesionadoRestURIConstants.LESIONADO_SERVICE_CREATE_LESIONADO_EVENTO_ID_PARAM_NAME) Integer eventoId,
			@PathVariable(value = LesionadoRestURIConstants.LESIONADO_SERVICE_CREATE_LESIONADO_USER_ID_PARAM_NAME) Integer creatorId) {
		try {
			super.refreshHibernateSession(context);
			Lesionado lesionado = fillLesionado(lesionadoService
					.crearNuevoLesionado(eventoId, creatorId));
			return new LesionadoSpringDto(lesionado);
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(LesionadoController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}

	/**
	 * Permite crear un lesionado para el evento especificado como argumento con el identificador dentro del
	 * evento especificado como argumento.
	 * @param eventoId id del evento para el que se desea crear un nuevo lesionado.
	 * @param creatorId id del usuario que desea crear el lesionado.
	 * @param eventLocalIdentifier id del lesionado dentro del evento. Este campo es usado para distinguir los
	 * lesionados sin requerir el numero de identificacion personal.
	 * @return lesionado creado en la base de datos.
	 */
	@RequestMapping(value = LesionadoRestURIConstants.LESIONADO_SERVICE_CREATE_LESIONADO_WITH_LOCAL_ID_URI, method = RequestMethod.POST)
	public @ResponseBody
	LesionadoSpringDto createLesionadoWithEventId(
			@PathVariable(value = LesionadoRestURIConstants.LESIONADO_SERVICE_CREATE_LESIONADO_WITH_LOCAL_ID_EVENTO_ID_PARAM_NAME) Integer eventoId,
			@PathVariable(value = LesionadoRestURIConstants.LESIONADO_SERVICE_CREATE_LESIONADO_WITH_LOCAL_ID_USER_ID_PARAM_NAME) Integer creatorId,
			@PathVariable(value = LesionadoRestURIConstants.LESIONADO_SERVICE_CREATE_LESIONADO_WITH_LOCAL_ID_EVENT_LOCAL_IDENTIFIER_PARAM_NAME) Integer eventLocalIdentifier) {
		try {
			super.refreshHibernateSession(context);
			Lesionado lesionado = fillLesionado(lesionadoService
					.crearNuevoLesionado(eventoId, creatorId));
			lesionado.setEventLocalIdentifier(eventLocalIdentifier);
			lesionado = fillLesionado(lesionadoService
					.updateLesionado(lesionado));
			return new LesionadoSpringDto(lesionado);
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(LesionadoController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}

	/**
	 * Permite inicializar la informacion del lesionado usado para generar el documento JSON a partir del
	 * lesionado obtenido a partir de la base de datos.
	 * @param lesionado lesionado usado para generar documento JSON.
	 * @param current lesionado almacenado en la base de datos.
	 * @return lesionado inicializado a partir del lesionado de la base de datos.
	 */
	private LesionadoSpringDto setupLesionado(LesionadoSpringDto lesionado,
			Lesionado current) {
		if (lesionado != null && current != null) {
			lesionado.setLesionadoId(current.getLesionadoId());
			if (lesionado.getEncuentro() != null
					&& current.getEncuentro() != null) {
				lesionado.getEncuentro().setEncounterId(
						current.getEncuentro().getEncounterId());
				if (lesionado.getEncuentro().getPatient() != null
						&& current.getEncuentro().getPatient() != null) {
					lesionado
							.getEncuentro()
							.getPatient()
							.setPatientId(
									current.getEncuentro().getPatient()
											.getPatientId());
					if (lesionado.getEncuentro().getPatient().getPerson() != null
							&& current.getEncuentro().getPatient().getPerson() != null) {
						lesionado
								.getEncuentro()
								.getPatient()
								.getPerson()
								.setPersonId(
										current.getEncuentro().getPatient()
												.getPerson().getPersonId());
						if (lesionado.getEncuentro().getPatient().getPerson()
								.getPreferredAddress() != null
								&& current.getEncuentro().getPatient()
										.getPerson().getPreferredAddress() != null) {
							lesionado
									.getEncuentro()
									.getPatient()
									.getPerson()
									.getPreferredAddress()
									.setPersonAddressId(
											current.getEncuentro().getPatient()
													.getPerson()
													.getPreferredAddress()
													.getPersonAddressId());
							if (lesionado.getEncuentro().getPatient()
									.getPerson().getPreferredAddress()
									.getCompleteAddress() != null
									&& current.getEncuentro().getPatient()
											.getPerson().getPreferredAddress()
											.getCompleteAddress() != null) {
								lesionado
										.getEncuentro()
										.getPatient()
										.getPerson()
										.getPreferredAddress()
										.getCompleteAddress()
										.setEventAddressId(
												current.getEncuentro()
														.getPatient()
														.getPerson()
														.getPreferredAddress()
														.getCompleteAddress()
														.getEventAddressId());
							}
						}
						if (lesionado.getEncuentro().getPatient().getPerson()
								.getPreferredName() != null
								&& current.getEncuentro().getPatient()
										.getPerson().getPreferredName() != null) {
							lesionado
									.getEncuentro()
									.getPatient()
									.getPerson()
									.getPreferredName()
									.setPersonNameId(
											current.getEncuentro().getPatient()
													.getPerson()
													.getPreferredName()
													.getPersonNameId());
						}
					}
					if (lesionado.getEncuentro().getPatient()
							.getPreferredIdentifier() != null
							&& current.getEncuentro().getPatient()
									.getPreferredIdentifier() != null) {
						lesionado
								.getEncuentro()
								.getPatient()
								.getPreferredIdentifier()
								.setPatientIdentifierId(
										current.getEncuentro().getPatient()
												.getPreferredIdentifier()
												.getPatientIdentifierId());
					}
				}
			}
		}
		return lesionado;
	}
	/**
	 * Permite almacenar el lesionado especificado como argumento en la base de datos. Si el lesionado 
	 * existe en la base de datos es actualizado.
	 * @param lesionado lesionado que se desea almacenar en la base de datos.
	 * @param userId id del usuario que desea almacenar el lesionado.
	 * @return lesionado almacenado en la base de datos.
	 */
	@RequestMapping(value = LesionadoRestURIConstants.LESIONADO_SERVICE_SAVE_LESIONADO_URI, method = RequestMethod.POST)
	public @ResponseBody
	LesionadoSpringDto saveLesionado(
			@RequestBody(required = true) LesionadoSpringDto lesionado,
			@PathVariable(value = LesionadoRestURIConstants.LESIONADO_SERVICE_SAVE_PATIENT_USER_ID_PARAM_NAME) Integer userId) {
		/* try{ */
		super.refreshHibernateSession(context);
		Integer usuario = userId;

		if (lesionado != null) {
			Lesionado current = null;

			Evento evento = eventoService.obtenerEvento(lesionado.getEvento()
					.getEventoId());
			current = lesionadoService.getLesionado(evento,
					lesionado.getEventLocalIdentifier());
			if (current == null && lesionado.getLesionadoId() != null) {
				current = lesionadoService.obtenerLesionado(lesionado
						.getLesionadoId());
			} else if (current == null && lesionado.getEvento() != null
					&& lesionado.getEvento().getEventoId() != null) {
				current = fillLesionado(lesionadoService.crearNuevoLesionado(
						lesionado.getEvento().getEventoId(), userId));
				current.setEventLocalIdentifier(lesionado
						.getEventLocalIdentifier());
				current = fillLesionado(lesionadoService
						.updateLesionado(current));

			}
			lesionado = setupLesionado(lesionado, current);
			if (current != null) {
				if (lesionado.getEncuentro() != null) {
					PatientSpringDto patient = lesionado.getEncuentro()
							.getPatient();
					if (patient != null) {
						PersonComplete personaCompleta = new PersonComplete();
						personaCompleta.setPatientId(patient.getPatientId());
						if (patient.getPerson() != null) {
							personaCompleta.setGender(patient.getPerson()
									.getGender());

							if (patient.getPerson().getPreferredAddress() != null) {
								personaCompleta.setAddress(patient.getPerson()
										.getPreferredAddress().getAddress());
							}
						}
						if (patient.getPreferredIdentifier() != null) {
							if (patient.getPreferredIdentifier()
									.getPatientIdentifierType() != null) {
								personaCompleta
										.setPatientIdentifierType(patient
												.getPreferredIdentifier()
												.getPatientIdentifierType()
												.getPatientIdentifierTypeId());
							}
						}
						if (patient.getPreferredIdentifier() != null) {

							personaCompleta.setIdentifier(patient
									.getPreferredIdentifier().getIdentifier());
						}
						if (patient.getPerson() != null) {
							if (patient.getPerson().getPreferredName() != null) {
								personaCompleta.setGivenName(patient
										.getPerson().getPreferredName()
										.getGivenName());
								personaCompleta.setMiddleName(patient
										.getPerson().getPreferredName()
										.getMiddleName());
								personaCompleta.setFamilyName(patient
										.getPerson().getPreferredName()
										.getFamilyName());
								personaCompleta.setFamilyName2(patient
										.getPerson().getPreferredName()
										.getFamilyName2());
							}
							personaCompleta.setBirthdate(patient.getPerson()
									.getBirthdate());

						}
						if (patient.getMaritalStatus() != null) {
							personaCompleta.setMaritalStatus(patient
									.getMaritalStatus().getConceptId());
						}
						personaCompleta.setOcupation(patient.getOcupation());
						if (patient.getPerson() != null) {
							if (patient.getPerson().getPreferredAddress() != null) {
								personaCompleta.setCity(patient.getPerson()
										.getPreferredAddress().getCity());
								personaCompleta.setZone(patient.getPerson()
										.getPreferredAddress().getRegion());
								personaCompleta.setPhone1(patient.getPerson()
										.getPreferredAddress().getPhone1());
								personaCompleta.setPhone2(patient.getPerson()
										.getPreferredAddress().getPhone2());
								if (patient.getPerson().getPreferredAddress()
										.getCompleteAddress() != null) {
									EventAddressSpringDto eventAddressSpringDto = patient
											.getPerson().getPreferredAddress()
											.getCompleteAddress();
									EventAddress eventAddress = new EventAddress();
									eventAddress
											.setBlockName(eventAddressSpringDto
													.getBlockName());
									eventAddress
											.setBlockNumber(eventAddressSpringDto
													.getBlockNumber());
									eventAddress
											.setEventAddressId(eventAddressSpringDto
													.getEventAddressId());
									eventAddress
											.setHomeName(eventAddressSpringDto
													.getHomeName());
									eventAddress
											.setHomeNumber(eventAddressSpringDto
													.getHomeNumber());
									eventAddress
											.setMainWay(eventAddressSpringDto
													.getMainWay());
									eventAddress
											.setMainWayDirection(eventAddressSpringDto
													.getMainWayDirection());
									eventAddress
											.setMainWayNumber(eventAddressSpringDto
													.getMainWayNumber());
									eventAddress
											.setMainWaySecondaryNumber(eventAddressSpringDto
													.getMainWaySecondaryNumber());
									eventAddress
											.setSecondaryWayDirection(eventAddressSpringDto
													.getSecondaryWayDirection());
									eventAddress
											.setSecondaryWayNumber(eventAddressSpringDto
													.getSecondaryWayNumber());
									eventAddress
											.setSecondaryWaySecondaryNumber(eventAddressSpringDto
													.getSecondaryWaySecondaryNumber());
									eventAddress
											.setWayNumberIdentifier(eventAddressSpringDto
													.getWayNumberIdentifier());
									personaCompleta
											.setEventAddress(eventAddress);
								}
							}
						}
						personaCompleta.setAttendatName(patient
								.getAttendatName());
						personaCompleta.setAttendatFamily(patient
								.getAttendatFamily());
						personaCompleta.setAttendatPhone(patient
								.getAttendatPhone());
						personaCompleta
								.setAttendatKin(patient.getAttendatKin());
						personaCompleta.setResponsableName(patient
								.getResponsableName());
						personaCompleta.setResponsableFamily(patient
								.getResponsableFamily());
						personaCompleta.setResponsablePhone(patient
								.getResponsablePhone());
						personaCompleta.setResponsablePhone1(patient
								.getResponsablePhone2());
						if (lesionado.getAseguradora() != null) {
							personaCompleta.setAseguradora(lesionado
									.getAseguradora().getConceptId());
						}
						if (lesionado.getEps() != null) {
							personaCompleta.setEps(lesionado.getEps()
									.getConceptId());
						}
						if (lesionado.getPlanBeneficios() != null) {
							personaCompleta.setPlanBeneficios(lesionado
									.getPlanBeneficios().getConceptId());
						}
						patientService.updatePatient(personaCompleta, usuario);
						
						if (personaCompleta.getPlanBeneficios() != null) {
							current.setPlanBeneficios(conceptService
									.obtenerConcepto(personaCompleta
											.getPlanBeneficios()));
						}
						if (personaCompleta.getEps() != null) {
							current.setEps(conceptService
									.obtenerConcepto(personaCompleta.getEps()));
						}

						if (personaCompleta.getAseguradora() != null) {
							current.setAseguradora(conceptService
									.obtenerConcepto(personaCompleta
											.getAseguradora()));
						}
					}
					if(lesionado.getResultado() != null)
					{
						current.setResultado(conceptService.obtenerConcepto(lesionado.getResultado().getConceptId()));
					}
					if(lesionado.getTipoNegacion() != null)
					{
						current.setTipoNegacion(conceptService.obtenerConcepto(lesionado.getTipoNegacion().getConceptId()));
					}
					if(lesionado.getEntidadRecepcion() != null)
					{
						current.setEntidadRecepcion(hospitalService.obtener(lesionado.getEntidadRecepcion().getHospitalId()));
					}
					if(lesionado.getRecibidoPor() != null)
					{
						current.setRecibidoPor(lesionado.getRecibidoPor());
					}
					if(lesionado.getRegistroRecibe() != null)
					{
						current.setRegistroRecibe(lesionado.getRegistroRecibe());
					}
					if(lesionado.getCargoEncargadoRecepcion() != null)
					{
						current.setCargoEncargadoRecepcion(lesionado.getCargoEncargadoRecepcion());
					}
					if(lesionado.getFechaUltimaMenstruacion() != null)
					{
						current.setFechaUltimaMenstruacion(lesionado.getFechaUltimaMenstruacion());
					}
					if(lesionado.getObservacionNegacion() != null)
					{
						current.setObservacionNegacion(lesionado.getObservacionNegacion());
					}
					ConceptsCode concepts = new ConceptsCode();
					Obs obsHC = obsService.obtenerObservacionPorConcepto(current.getEncuentro(), concepts.cHallazgosClinicos());
					if(obsHC == null){
						obsService.guardar(current.getEncuentro(), concepts.cHallazgosClinicos(), lesionado.getHallazgosClinicos(), usuario);
					}
					else
					{
						if(obsHC.getValueText()==null){
							obsHC.setValueText(lesionado.getHallazgosClinicos());
							obsService.update(obsHC);
						}
					}
					Obs obsID = obsService.obtenerObservacionPorConcepto(current.getEncuentro(), concepts.cImpresionDiagnostica());
					if(obsID == null)
					{
						obsService.guardar(current.getEncuentro(), concepts.cImpresionDiagnostica(), lesionado.getImpresionDiagnostica(), usuario);
					}
					else
					{
						if(obsID.getValueText()==null){
							obsID.setValueText(lesionado.getImpresionDiagnostica());
							obsService.update(obsID);
						}
					}
					
					current.setChangedBy(usuario);
					current.setDateChanged(new Date());
					lesionadoService.actualizarLesionado(current);
					LesionadoSpringDto result = new LesionadoSpringDto(
							fillLesionado(current));
					result.setCierreAtencion(getCierreAtencion( current, current.getEncuentro()));
					if(obsHC != null && obsHC.getValueText() != null){
						result.setHallazgosClinicos(obsHC.getValueText());
					}
					if(obsID != null && obsID.getValueText() != null)
					{
						result.setImpresionDiagnostica(obsID.getValueText());
					}
					/*
					result.setNotasAclaratorias(getNotasAclaratorias(current));
					result.setAntecedentesAmp(getAntecedentesAmp(current));
					Encounter encounter = fillEncounter(encounterService
							.getEncounterById(current.getEncuentro().getEncounterId()));
					result.setTeleasistencias(getTeleasistencias(encounter));
					result.setSignosVitales(getSignosVitales(encounter));
					result.setRespondientes(getRespondientes(current));
					result.setResponsables(getResponsables(current));
					result.setEvaluaciones(getEvaluaciones(result));
					result.setAntecedentes(getAntecedentes(result));*/
					return result;
				}
			}

		}
		return null;
		/*
		 * } catch(Exception ex) { List<FieldErrorResource> errors = new
		 * ArrayList<FieldErrorResource>(); errors.add(new
		 * FieldErrorResource(LesionadoController.class.getCanonicalName(),
		 * String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
		 * throw new InternalErrorException(ex.getMessage(), errors); }
		 */

	}
	/**
	 * Permite guardar el repondiente de un paciente lesionado.
	 * @param respondiente respondiente que se desea almacenar en la base de datos.
	 * @param userId id del usuario que desea crear el repondiente.
	 * @return respondiente creado en la base de datos.
	 */
	@RequestMapping(value=LesionadoRestURIConstants.LESIONADO_SERVICE_SAVE_PRIMER_RESPONDIENTE_URI, method = RequestMethod.POST)
	public @ResponseBody PrimerRespondienteSpringDto saveRespondiente(
			@RequestBody()PrimerRespondienteSpringDto respondiente, 
			@PathVariable(value=LesionadoRestURIConstants.LESIONADO_SERVICE_SAVE_PRIMER_RESPONDIENTE_USER_ID_PARAM_NAME)Integer userId)
	{
		Integer lesionadoId = respondiente.getLesionado().getLesionadoId();
		if(lesionadoId != null)
		{
			Lesionado lesionado = lesionadoService.obtenerLesionado(lesionadoId);
			if(lesionado != null && !lesionadoService.existePrimerRespondiente(lesionado, respondiente.getPrimerRespondiente().getConceptId())){
				Concept concept = conceptService.obtenerConcepto(respondiente.getPrimerRespondiente().getConceptId());
				respondiente = new PrimerRespondienteSpringDto(lesionadoService.guardarPrimerRespondiente(lesionado, concept, userId));
			}
		}
		return respondiente;
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

	/**
	 * Permite obtener la lista de teleasistencias realizadas a un lesionado en un evento de emergencias.
	 * @param encounter encuentro para el que se desean obtener las teleasistencias.
	 * @return lista de teleasistencias realizadas a un lesionado.
	 */
	private List<TeleasistenciaSpringDto> getTeleasistencias(Encounter encounter) {
		/*try */{
			List<TeleasistenciaSpringDto> lista = new ArrayList<TeleasistenciaSpringDto>();
			ConceptsCode concepts = new ConceptsCode();
			String idMedico = null;
			for (ObservationData od : obsService
					.obtenerSolicitudesTeleasistencia(encounter)) {
				TeleasistenciaSpringDto teleasistenciaData = new TeleasistenciaSpringDto();
				teleasistenciaData.setTeleasistenciaId(od.getObsId());
				teleasistenciaData.setMotivoSolicitud((String) ObservationData
						.obtenerValorConcepto(od.getSons(),
								concepts.cMotivoSolicitudTeleasistencia()));
				teleasistenciaData.setDetalles((String) ObservationData
						.obtenerValorConcepto(od.getSons(),
								concepts.cDetalleSolicitudTeleasistencia()));
				teleasistenciaData.setEncounter(new EncounterSpringDto(
						encounter));
				teleasistenciaData.setFecha((Date) ObservationData
						.obtenerValorConcepto(od.getSons(),
								concepts.cFechaSolicitudTeleasistencia()));
				ConceptSpringDto medio = new ConceptSpringDto(
						conceptService.obtenerConcepto((Integer) ObservationData
								.obtenerValorConcepto(od.getSons(), concepts
										.cMedioSolicitudTeleasistencia())));
				teleasistenciaData.setMedio(medio);

				teleasistenciaData
						.setNotasEvolucion(new ArrayList<NotaEvolucionSpringDto>());
				idMedico = (String) ObservationData
						.obtenerValorConcepto(od.getSons(),
								concepts.cUsuarioSolicitaTeleasistencia());

				if (idMedico != null) {
					Integer concept = Integer.parseInt(idMedico);
					UserDTO user = userService.findUserById(concept);
					Person person = personaService.getPersonById(user
							.getPersonId());
					String roleName = SessionUserApp.getRolName(user
							.getRoleId());
					UserSpringDto medicoSolicitante = new UserSpringDto(user,
							person, roleName);
					teleasistenciaData.setMedicoSolicitante(medicoSolicitante);
				}
				for (ObservationData odM : ObservationData.obtenerListaValores(
						od.getSons(), concepts.cNotaEvolucion())) {
					NotaEvolucionSpringDto ne = new NotaEvolucionSpringDto();
					ne.setObsId(odM.getObsId());
					Integer concept = (Integer) ObservationData
							.obtenerValorConcepto(odM.getSons(), concepts
									.cDiagnosticoPrincipalNotaEvolucion());
					if (concept != null) {
						Concept dxPrincipal = conceptService
								.obtenerConcepto(concept);
						ConceptSpringDto diagnosticoPrincipal = new ConceptSpringDto(
								dxPrincipal);
						ne.setDxPrincipal(diagnosticoPrincipal);
					}
					idMedico = (String) ObservationData.obtenerValorConcepto(
							odM.getSons(),
							concepts.cMedicoTratanteNotaEvolucion());
					if (idMedico != null) {
						concept = Integer.parseInt(idMedico);
						UserDTO user = userService.findUserById(concept);
						Person person = personaService.getPersonById(user
								.getPersonId());
						String roleName = SessionUserApp.getRolName(user
								.getRoleId());
						UserSpringDto medicoTratante = new UserSpringDto(user,
								person, roleName);
						ne.setMedicoTratante(medicoTratante);
					}
					String nombreMedico = (String) ObservationData.obtenerValorConcepto(
							odM.getSons(),
							concepts.cNombreMedicoTeleasistencia());
					if (nombreMedico != null) {
						ne.setMedicoTratanteNombre(nombreMedico);
					}
					ne.setFecha((Date) ObservationData.obtenerValorConcepto(
							odM.getSons(), concepts.cFechaNotaEvolucion()));
					ne.setRecomendaciones((String) ObservationData
							.obtenerValorConcepto(odM.getSons(),
									concepts.cRecomendacionesNotaEvolucion()));
					ne.setDxSecundarios(new ArrayList<ConceptSpringDto>());

					for (ObservationData odMD : ObservationData
							.obtenerListaValores(odM.getSons(), concepts
									.cDiagnosticoSecundarioNotaEvolucion())) {

						concept = odMD.getValueCoded();
						if (concept != null) {
							ConceptSpringDto diagnosticoSecundario = new ConceptSpringDto(
									conceptService.obtenerConcepto(concept));
							ne.getDxSecundarios().add(diagnosticoSecundario);
						}
					}
					teleasistenciaData.getNotasEvolucion().add(ne);
				}
				lista.add(teleasistenciaData);
			}

			return lista;
		} /*catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ObsController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}*/
	}

	/**
	 * Permite obtener la lista de signos vitales de un paciente lesionado en un evento de emergencias.
	 * @param encounter encuentro para el que se desea encontrar la lista de signos vitales.
	 * @return lista de signos vitales de un paciente.
	 */
	private List<SignosVitalesSpringDto> getSignosVitales(Encounter encounter) {
		/*try */{
			List<SignosVitalesSpringDto> result = new ArrayList<SignosVitalesSpringDto>();
			for (ObservationData od : obsService
					.obtenerSignosVitales(encounter)) {
				result.add(new SignosVitalesSpringDto(od, encounter));
			}
			return result;
		} /*catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ObsController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}*/
	}
	/**
	 * Permite obtener la lista de antecedentes de un paciente.
	 * @param lesionado lesionado para el que se desean obtener los antecedentes.
	 * @return lista de antecedentes del lesionado especificado como argumento.
	 * @throws TransformerException 
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	private List<AntecedentesAmpSpringDto> getAntecedentesAmp(Lesionado lesionado) throws JDOMException, IOException, TransformerException {
		/*try */{
			Integer lesionadoId = lesionado.getLesionadoId();
			List<AntecedentesAmpSpringDto> antecedentesAmp = new ArrayList<AntecedentesAmpSpringDto>();
			List<AntecedentesAmp> antecedentes = in000032uv01MessageService
					.getListAntecedentesAMP(lesionadoId, lesionado, lesionado.getEncuentro(), lesionado.getEncuentro().getPatient(), obsService);
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
				antecedentesAmp.add(new AntecedentesAmpSpringDto(antecedente,
						lesionado));
			}
			return antecedentesAmp;
		} /*catch (Exception ex) {

			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(MessageController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}*/

	}
	/**
	 * Lista de notas aclaratorias de un lesionado.
	 * @param lesionado lesionado para el que se desean obtener las notas aclaratorias.
	 * @return lista de notas aclaratorias del lesionado especificado como argumento.
	 */
	private List<NotaAclaratoriaSpringDto> getNotasAclaratorias(
			Lesionado lesionado) {
		
			super.refreshHibernateSession(context);
			List<NotaAclaratoriaSpringDto> lista = new ArrayList<NotaAclaratoriaSpringDto>();
			if (lesionado != null) {
				for (ObservationData sv : obsService
						.obtenerNotasAclaratorias(lesionado.getEncuentro())) {
					NotaAclaratoriaSpringDto notaAclaratoria = new NotaAclaratoriaSpringDto(sv, lesionado, personService);
					lista.add(notaAclaratoria);
				}
				return lista;
			}
			return null;
	}
	/**
	 * Lista de antecedentes personales del lesionado especificado como argumento.
	 * @param l lesionado usado para generar documento JSON.
	 * @param lesionado lesionado usado para almacenar en la base de datos.
	 * @return lista de antecedentes personales del lesionado especificado como argumento.
	 */
	private List<AntecedenteSpringDto> getAntecedentes(LesionadoSpringDto l, Lesionado lesionado)
	{
		ConceptsCode concepts = new ConceptsCode();
		List<AntecedenteSpringDto> antecedentes = new ArrayList<AntecedenteSpringDto>();
		for(ObservationData od : obsService.obtenerAntecedentes(lesionado.getEncuentro().getPatient())){
			AntecedenteSpringDto antecedente = new AntecedenteSpringDto();
			for(ObservationData odL : od.getSons()){
				if(odL.getConceptId().equals(concepts.cAnioAntecedente())){
					if(odL.getValueNumeric() != null)
					{
						antecedente.setYear(odL.getValueNumeric().intValue());
					}
				}
				if(odL.getConceptId().equals(concepts.cFechaAntecedente()))
				{
					antecedente.setDate(odL.getValueDatetime());
				}
				if(odL.getConceptId().equals(concepts.cTipoAntecedente())){
					antecedente.setConceptTipoAntecedente(new ConceptSpringDto(conceptService.obtenerConcepto(odL.getValueCoded())));
					Concept c = conceptService.obtenerConcepto(antecedente.getConceptTipoAntecedente().getConceptId());
					if(c!=null)
					{
						antecedente.setTipoAntecedente(c.getShortName());
					}
				}
				if(odL.getConceptId().equals(concepts.cObservacionAntecedente()))
				{
					antecedente.setObservacion(odL.getValueText());
				}
			}
			antecedente.setObsId(od.getObsId());
			antecedente.setPatient(new PatientSpringDto(lesionado.getEncuentro().getPatient())); 
			antecedentes.add(antecedente);
		}
		return antecedentes;
	}
	/**
	 * Permite cerrar la historia clinica de un paciente lesionado en un evento de emergencias.
	 * @param cierre contiene la informacion del cierre de la historia clinica del paciente.
	 * @param userId id del usuario que desea cerrar la historia clinica.
	 * @return lesionado con la historia clinica cerrada.
	 * @throws JDOMException
	 * @throws IOException
	 * @throws TransformerException
	 */
	@RequestMapping(value = LesionadoRestURIConstants.LESIONADO_SERVICE_CERRAR_HISTORIA_URI, method = RequestMethod.POST)
	public @ResponseBody LesionadoSpringDto cerrarHistoria(
			@RequestBody(required =true)CierreAtencionSpringDto cierre, 
			@PathVariable(LesionadoRestURIConstants.LESIONADO_SERVICE_CERRAR_HISTORIA_USER_ID_PARAM_NAME)Integer userId) throws JDOMException, IOException, TransformerException
	{
		Lesionado lesionado = lesionadoService.obtenerLesionado(cierre.getLesionadoId());
		Integer usuario = userId;
		Integer tipoCierre = cierre.getTipoCierre().getConceptId();
		String notaCierre = cierre.getNota();
		Obs obsParent = null;
		if(cierre.getObsId() != null)
		{
			obsParent = obsService.getObs(cierre.getObsId());
		}
		obsService.guardarDatosCierreAtencion(lesionado.getEncuentro(), tipoCierre, notaCierre, usuario, obsParent);
		encounterService.cerrarEncuentro(lesionado.getEncuentro(), usuario);
		lesionado = lesionadoService.obtenerLesionado(cierre.getLesionadoId());
		return completeLesionado(lesionado);
	}
	/**
	 * Permite obtener el cierre de la atencion de un paciente lesionado en un evento de emergencias.
	 * @param lesionado lesionado para el que se desea obtener el cierre de atencion.
	 * @param encounter encuentro para el que se desea obtener el cierre de atencion.
	 * @return cierre de atencion del lesionado especificado como argumento.
	 */
	private CierreAtencionSpringDto getCierreAtencion(Lesionado lesionado, Encounter encounter)
	{
		ConceptsCode concepts = new ConceptsCode();
		ObservationData od = obsService.obtenerDatosCierre(encounter);
		String idMedico = (String) ObservationData.obtenerValorConcepto(
				od.getSons(), concepts.cUsuarioCierraAtencion());

		if (idMedico != null) {
			Integer concept = Integer.parseInt(idMedico);
			UserDTO user = userService.findUserById(concept);
			Person person = personaService
					.getPersonById(user.getPersonId());
			String roleName = SessionUserApp.getRolName(user.getRoleId());
			return new CierreAtencionSpringDto(od, user, person, roleName, conceptService, lesionado.getLesionadoId());
		}
		return null;
	}
	@Override
	public void setServletContext(ServletContext servletContext) {
		context = servletContext;
		HashMap<String, String> params = new HashMap<String, String>();
		String baseUrlTemplates = servletContext
				.getInitParameter(HibernateProperties.URL_BASE_XML_TEMPLATES);
		String urlBodyImage = servletContext
				.getInitParameter(HibernateProperties.URL_BODY_IMAGE_FILE);
		String urlXsl = servletContext
				.getInitParameter(HibernateProperties.URL_BASE_XSL_TEMPLATES);
		params.put(
				HibernateTPHDAOFactory.TPH_HIBERNATE_CONFIG_FILE,
				servletContext
						.getInitParameter(HibernateProperties.HIBERNATE_CONFIGURATION_FILE_NAME));

		HibernateSessionFactoryManager manager = HibernateSessionFactoryManager
				.getInstance();
		manager.createFactory(servletContext
				.getInitParameter(HibernateProperties.HIBERNATE_CONFIGURATION_FILE_NAME));
		in000032uv01MessageService = new IN000032UV01MessageService(
				servletContext
						.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);
		eventoService = new EventoService(
				servletContext
						.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);

		encounterService = new EncounterService(
				servletContext
						.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);
		patientService = new PatientService(
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
		obsService = new ObsService(
				servletContext
						.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);
		personaService = new PersonaService(
				servletContext
						.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);
		userService = new com.artica.telesalud.persona.service.UserService(
				servletContext
						.getInitParameter(HibernateProperties.PERSONA_DAO_CLASS_FACTORY_IMPL),
				params);
		responsableAtencionService = new ResponsableAtencionService(
				servletContext
						.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);
		hospitalService = new HospitalService(
				servletContext
						.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);
		personService = new PersonService(
				servletContext
						.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);
		in000032uv01MessageService.setUrlBaseTemplates(baseUrlTemplates);
		in000032uv01MessageService.setUrlBodyImage(urlBodyImage);
		in000032uv01MessageService.setUrlXsl(urlXsl);

	}

}
