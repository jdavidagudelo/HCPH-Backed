package com.artica.telesalud.tph.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.artica.telesalud.tph.concepts.ConceptsCode;
import com.artica.telesalud.tph.controller.constants.HibernateProperties;
import com.artica.telesalud.tph.controller.constants.ObsRestURIConstants;
import com.artica.telesalud.tph.controller.exception.FieldErrorResource;
import com.artica.telesalud.tph.controller.exception.InternalErrorException;
import com.artica.telesalud.tph.controller.exception.InvalidRequestException;
import com.artica.telesalud.tph.controller.model.concept.ConceptSpringDto;
import com.artica.telesalud.tph.controller.model.observation.AntecedenteSpringDto;
import com.artica.telesalud.tph.controller.model.observation.EvaluacionCompletaSpringDto;
import com.artica.telesalud.tph.controller.model.observation.EvaluacionSpringDto;
import com.artica.telesalud.tph.controller.model.observation.HallazgoSpringDto;
import com.artica.telesalud.tph.controller.model.observation.InsumoSpringDto;
import com.artica.telesalud.tph.controller.model.observation.NotaAclaratoriaSpringDto;
import com.artica.telesalud.tph.controller.model.observation.NotaEvolucionSpringDto;
import com.artica.telesalud.tph.controller.model.observation.ProcedimientosSpringDto;
import com.artica.telesalud.tph.controller.model.observation.SignosVitalesSpringDto;
import com.artica.telesalud.tph.controller.model.observation.TeleasistenciaSpringDto;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
import com.artica.telesalud.tph.model.encounter.Encounter;
import com.artica.telesalud.tph.model.evento.Lesionado;
import com.artica.telesalud.tph.model.observation.Obs;
import com.artica.telesalud.tph.model.observation.SimpleObs;
import com.artica.telesalud.tph.model.person.Person;
import com.artica.telesalud.tph.service.EncounterService;
import com.artica.telesalud.tph.service.LesionadoService;
import com.artica.telesalud.tph.service.ObsService;
import com.artica.telesalud.tph.service.PatientService;
import com.artica.telesalud.tph.service.PersonService;
/**
 * Permite almacenar la informaciones de las observaciones realizadas a un paciente durante
 * la atencion prehospitalaria.
 * @author Juan David Agudelo. jdaaa2009@gmail.com
 *
 */
@Controller
@RequestMapping(value = ObsRestURIConstants.OBS_SERVICE_URI)
public class ObsController extends AbstractController implements ServletContextAware {
	private ObsService obsService;
	private PatientService patientService;
	private EncounterService encounterService;
	private LesionadoService lesionadoService;
	private PersonService personService;
	private ServletContext context;

	/**
	 * Permite completar la informacion del encuentro especificado como argumento.
	 * @param encounter encuentro que se desea completar.
	 * @return encuentro completado.
	 */
	private Encounter fillEncounter(Encounter encounter) {
		if(encounter != null && encounter.getPatient() != null)
		{
			encounter.setPatient(patientService.buscarPatient(encounter
					.getPatient().getPatientId()));
		}
		return encounter;
	}
	
	/**
	 * Permite almacenar en la base de datos los signos vitales especificados como argumento.
	 * @param signosVitales signos vitales que se desean almacenar en la base de datos.
	 * @param userId id del usuario que desea almacenar los signos vitales.
	 * @return signos vitales almacenados en la base de datos.
	 */
	@RequestMapping(value = ObsRestURIConstants.OBS_SERVICE_SAVE_SIGNOS_VITALES_URI, method = RequestMethod.POST)
	public @ResponseBody SignosVitalesSpringDto saveSignosVitales(
			@RequestBody SignosVitalesSpringDto signosVitales, 
			@PathVariable(value=ObsRestURIConstants.OBS_SERVICE_SAVE_SIGNOS_VITALES_USER_ID_PARAM_NAME)Integer userId)
	{
		/*try*/
		{
			super.refreshHibernateSession(context);
			Integer encounterId = signosVitales.getEncounter().getEncounterId();
			Obs obsParent = obsService.getObs(signosVitales.getObsId());
			Encounter encounter = fillEncounter(encounterService
					.getEncounterById(encounterId));
			Obs obs = obsService.guardarSignosVitales(encounter, signosVitales.getRespiracion(), signosVitales.getPaSistolica(), 
					signosVitales.getPaDiastolica(), signosVitales.getGlicemia(), 
					signosVitales.getPulso(), signosVitales.getTemperatura(), signosVitales.getSpo2(), 
					signosVitales.getFecha(), userId, obsParent);
			signosVitales.setObsId(obs.getObsId());
			return signosVitales;
		}
		/*catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ObsController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}*/
	}
	
	

	/**
	 * Permite almacenar los antecedentes especificados como argumento en la base de datos.
	 * @param antecedente antecedente que se desea almacenar.
	 * @param lesionadoId id del lesionado para el que se desea crear el antecedente.
	 * @param userId id del usuario que desea crear el antecedente.
	 * @return antecedente almacenado en la base de datos.
	 */
	@RequestMapping(value = ObsRestURIConstants.OBS_SERVICE_SAVE_ANTECEDENTE_URI, method = RequestMethod.POST)
	public @ResponseBody
	AntecedenteSpringDto saveAntecedente(
			@RequestBody(required = true) AntecedenteSpringDto antecedente,
			@PathVariable(value = ObsRestURIConstants.OBS_SERVICE_SAVE_ANTECEDENTE_LESIONADO_ID_PARAM_NAME) Integer lesionadoId,
			@PathVariable(value = ObsRestURIConstants.OBS_SERVICE_SAVE_ANTECEDENTE_USER_ID_PARAM_NAME) Integer userId) {
		/*try*/ {
			super.refreshHibernateSession(context);
			Lesionado lesionado = lesionadoService
					.obtenerLesionado(lesionadoId);
			Obs obsParent = obsService.getObs(antecedente.getObsId());
			if(antecedente.getConceptTipoAntecedente() != null){
			obsParent = obsService.guardarAntecedente(lesionado.getEncuentro(),
					antecedente.getYear(), antecedente.getConceptTipoAntecedente().getConceptId(),
					antecedente.getObservacion(), antecedente.getDate(), userId, obsParent);
			}
			if(obsParent != null)
			{
				antecedente.setObsId(obsParent.getObsId());
			}
			return antecedente;
		}/* catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ObsController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}*/
	}

	
	/**
	 * Permite crear una nueva evaluacion asociada al encuentro especificado como argumento.
	 * @param evaluacion evaluacion que contiene la fecha de creacion de la evaluacion.
	 * @param encounterId id del encuentro para el que se crea la nueva evaluacion.
	 * @param userId id del usuario que desea crear la evaluacion.
	 * @return evaluacion creada en la base de datos.
	 */
	@RequestMapping(value = ObsRestURIConstants.OBS_SERVICE_CREATE_EVALUACION_URI, method = RequestMethod.POST)
	public @ResponseBody EvaluacionCompletaSpringDto createEvaluacion(
			@RequestBody EvaluacionCompletaSpringDto evaluacion,
			@PathVariable(value = ObsRestURIConstants.OBS_SERVICE_CREATE_EVALUACION_ENCOUNTER_ID_PARAM_NAME)Integer encounterId, 
			@PathVariable(value = ObsRestURIConstants.OBS_SERVICE_CREATE_EVALUACION_USER_ID_PARAM_NAME)Integer userId)
	{
		super.refreshHibernateSession(context);
		Encounter encounter = fillEncounter(encounterService.getEncounterById(encounterId));
		Date dateCreated = new Date();
		if(evaluacion != null)
		{
			dateCreated = evaluacion.getDateCreated();
		}
		Obs obs = obsService.getNewEvaluacionPaciente(encounter, userId, dateCreated);
		return new EvaluacionCompletaSpringDto(obs, encounter);
	}


	/**
	 * Permite almacenar en la base de datos la informacion de la evaluacion especificada como argumento.
	 * @param evaluacion evaluacion que se desea almacenar en la base de datos.
	 * @param userId id del usuario que desea almacenar al evaluacion.
	 * @param obsParentId id de la evaluacion en la base de datos. Este id es obtenido una vez se llama
	 * el metodo createEvaluacion.
	 * @return evaluacion almacenada en la base de datos.
	 */
	@RequestMapping(value=ObsRestURIConstants.OBS_SERVICE_SAVE_EVALUACION_HISTORICO_URI, method=RequestMethod.POST)
	public @ResponseBody EvaluacionSpringDto 
	saveEvaluacionHistorico(
			@RequestBody EvaluacionSpringDto evaluacion, 
			@PathVariable(value = ObsRestURIConstants.OBS_SERVICE_SAVE_EVALUACION_HISTORICO_USER_ID_PARAM_NAME)Integer userId, 
			@PathVariable(value=ObsRestURIConstants.OBS_SERVICE_SAVE_EVALUACION_HISTORICO_OBS_PARENT_ID_PARAM_NAME)Integer obsParentId)
	{
		super.refreshHibernateSession(context);
		ConceptsCode concepts = new ConceptsCode();
		List<SimpleObs> datosEvaluacion = new ArrayList<SimpleObs>();
		if (evaluacion != null && obsParentId != null) {

			Obs obsParent = obsService.getObs(obsParentId);
			if(obsParent == null)
			{
				return null;
			}
			Encounter encounter = fillEncounter(encounterService
					.getEncounterById(evaluacion.getEvaluacion().getEncounter()
							.getEncounterId()));
			Integer concepto = null;
			if(evaluacion.getCausaObstruccionViaAerea() != null)
			{
				concepto = evaluacion.getCausaObstruccionViaAerea().getConceptId();
			}
			datosEvaluacion.add(new SimpleObs(concepts
					.cCausaObstruccionViaAereaCompleta(), concepto));
			
			concepto = null;
			if (evaluacion.getPermeavilidadViaAerea() != null) {
				concepto = evaluacion.getPermeavilidadViaAerea().getConceptId();
			}

			datosEvaluacion.add(new SimpleObs(concepts
					.cPermeabilidadViaArea(), concepto));
			datosEvaluacion.add(new SimpleObs(concepts
					.cCausaObstruccionViaAerea(), evaluacion
					.getCausaObstruccionVia()));

			if (evaluacion.getRespiracion() != null) {
				concepto = null;
				if (evaluacion.getRespiracion().equals(
						EvaluacionSpringDto.RESPIRACION_AUSENTE))
					concepto = concepts.cRespiracionAusente();
				else if (evaluacion.getRespiracion().equals(
						EvaluacionSpringDto.RESPIRACION_PRESENTE))
					concepto = concepts.cRespiracionPresente();
				datosEvaluacion.add(new SimpleObs(concepts
						.cRespiracion(), concepto));
			}

			concepto = null;
			if (evaluacion.getPulso() != null) {
				if (evaluacion.getPulso().equals(
						EvaluacionSpringDto.PULSO_AUSENTE))
					concepto = concepts.cPulsoAusente();
				datosEvaluacion.add(new SimpleObs(concepts
						.cValoracioPrimariaPulso(), concepto));
			}

		

			if (evaluacion.getPulso() != null) {
				concepto = null;
				if (evaluacion.getPulso().equals(
						EvaluacionSpringDto.PULSO_FUERTE))
					concepto = concepts.cPulsoFuerte();
				else if (evaluacion.getPulso().equals(
						EvaluacionSpringDto.PULSO_DEBIL))
					concepto = concepts.cPulsoDebil();
				datosEvaluacion.add(new SimpleObs(concepts.cFuerzaPulso(),
						concepto));
			}
			datosEvaluacion.add(new SimpleObs(concepts.cPielCaliente(),
					evaluacion.getPielCaliente()));
			datosEvaluacion.add(new SimpleObs(concepts.cPielCrianotica(),
					evaluacion.getPielCianotica()));
			datosEvaluacion.add(new SimpleObs(concepts.cPielHumeda(),
					evaluacion.getPielHumeda()));
			datosEvaluacion.add(new SimpleObs(concepts.cPielNormal(),
					evaluacion.getPielNormal()));
			datosEvaluacion.add(new SimpleObs(concepts.cPielPalidaFria(),
					evaluacion.getPielPalida()));
			datosEvaluacion.add(new SimpleObs(concepts.cPielSeca(),
					evaluacion.getPielSeca()));

			datosEvaluacion.add(new SimpleObs(concepts
					.cPupilasMidriaticas(), evaluacion
					.getPupilasMidriaticas()));
			datosEvaluacion.add(new SimpleObs(concepts.cPupilasMioticas(),
					evaluacion.getPupilasMioticas()));
			datosEvaluacion.add(new SimpleObs(concepts
					.cPupilasNoEvaluables(), evaluacion
					.getPupilasNoEvaluables()));
			datosEvaluacion.add(new SimpleObs(concepts
					.cPupilasNoReactivas(), evaluacion
					.getPupilasNoReactivas()));
			datosEvaluacion.add(new SimpleObs(concepts.cPupilasReactivas(),
					evaluacion.getPupilasReactivas()));
			datosEvaluacion.add(new SimpleObs(concepts
					.cPielPupilasIsocoricas(), evaluacion
					.getPupilasIsocoricas()));

			if (evaluacion.getGlasgowRM() != null)
				datosEvaluacion.add(new SimpleObs(concepts.cGlasgowRM(),
						Double.parseDouble(evaluacion.getGlasgowRM()
								.toString())));
			if (evaluacion.getGlasgowRO() != null)
				datosEvaluacion.add(new SimpleObs(concepts.cGlasgowRO(),
						Double.parseDouble(evaluacion.getGlasgowRO()
								.toString())));
			if (evaluacion.getGlasgowRV() != null)
				datosEvaluacion.add(new SimpleObs(concepts.cGlasgowRV(),
						Double.parseDouble(evaluacion.getGlasgowRV()
								.toString())));

			if (evaluacion.getTipoEmergencia() != null) {
				concepto = null;
				if (evaluacion.getTipoEmergencia().equals(
						EvaluacionSpringDto.EMERGENCIA_CARDIOVASCULAR))
					concepto = concepts.cCardiovascular();
				else if (evaluacion.getTipoEmergencia().equals(
						EvaluacionSpringDto.EMERGENCIA_ENFERMEDAD_COMUN))
					concepto = concepts.cEnfermedadComun();
				else if (evaluacion.getTipoEmergencia().equals(
						EvaluacionSpringDto.EMERGENCIA_GASTROINTESTINAL))
					concepto = concepts.cGastrointestinal();
				else if (evaluacion.getTipoEmergencia().equals(
						EvaluacionSpringDto.EMERGENCIA_GENITOURINARIO))
					concepto = concepts.cGenitourinario();
				else if (evaluacion.getTipoEmergencia().equals(
						EvaluacionSpringDto.EMERGENCIA_GINECOOBSTETRICO))
					concepto = concepts.cGnicoObstetrico();
				else if (evaluacion.getTipoEmergencia().equals(
						EvaluacionSpringDto.EMERGENCIA_INTOXICACION))
					concepto = concepts.cIntoxicacion();
				else if (evaluacion.getTipoEmergencia().equals(
						EvaluacionSpringDto.EMERGENCIA_NEUROLOGIA))
					concepto = concepts.cNeurologia();
				else if (evaluacion.getTipoEmergencia().equals(
						EvaluacionSpringDto.EMERGENCIA_ORGANOS_SENTIDOS))
					concepto = concepts.cOrganosSentidos();
				else if (evaluacion.getTipoEmergencia().equals(
						EvaluacionSpringDto.EMERGENCIA_OVACE))
					concepto = concepts.cOvace();
				else if (evaluacion.getTipoEmergencia().equals(
						EvaluacionSpringDto.EMERGENCIA_PARO_CARDIACO))
					concepto = concepts.cParoCardiaco();
				else if (evaluacion.getTipoEmergencia().equals(
						EvaluacionSpringDto.EMERGENCIA_SHOCK))
					concepto = concepts.cShock();
				else if (evaluacion.getTipoEmergencia().equals(
						EvaluacionSpringDto.EMERGENCIA_SIQUIATRICA))
					concepto = concepts.cPsiquiatrica();
				else if (evaluacion.getTipoEmergencia().equals(
						EvaluacionSpringDto.EMERGENCIA_TERMICA))
					concepto = concepts.cTermica();
				else if(evaluacion.getTipoEmergencia().equals(EvaluacionSpringDto.OTRA_EMERGENCIA))
					concepto = concepts.cOtraEmergenciaMedica();
				datosEvaluacion.add(new SimpleObs(concepts
						.cTipoEmergenciaMedica(), concepto));
				
			}

			datosEvaluacion.add(new SimpleObs(concepts.cTraumaPolitrauma(),
					evaluacion.getPolitrauma()));
			datosEvaluacion.add(new SimpleObs(concepts.cTraumaAbdomen(),
					evaluacion.getAbdomen()));
			datosEvaluacion.add(new SimpleObs(concepts
					.cTraumaExtremidadInferior(), evaluacion
					.getExtremidadInferior()));
			datosEvaluacion.add(new SimpleObs(concepts
					.cTraumaExtremidadSuperior(), evaluacion
					.getExtremidadSuperior()));
			datosEvaluacion.add(new SimpleObs(concepts
					.cTraumaMaxilofacial(), evaluacion.getMaxilofacial()));
			datosEvaluacion.add(new SimpleObs(concepts
					.cTraumaOrganosSentidos(), evaluacion
					.getOrganosSentidos()));
			datosEvaluacion
					.add(new SimpleObs(concepts.cTraumaOsteomuscular(),
							evaluacion.getOsteomuscular()));
			datosEvaluacion.add(new SimpleObs(concepts.cTraumaPelvico(),
					evaluacion.getPelvico()));
			datosEvaluacion.add(new SimpleObs(concepts
					.cTraumaRaquimedular(), evaluacion.getRaquimedular()));
			datosEvaluacion.add(new SimpleObs(concepts.cTraumaTEC(),
					evaluacion.getTec()));
			datosEvaluacion.add(new SimpleObs(concepts
					.cTraumaTejidosBlandos(), evaluacion
					.getTejidosBlandos()));
			datosEvaluacion.add(new SimpleObs(concepts.cTraumaTorax(),
					evaluacion.getTorax()));
			datosEvaluacion.add(new SimpleObs(concepts.cOtroTrauma(),
					evaluacion.getOtroTrauma()));
			datosEvaluacion.add(new SimpleObs(concepts.cCualOtroTrauma(),
					evaluacion.getCualOtroTrauma()));

			if (evaluacion.getPrioridadTriage() != null) {
				concepto = null;
				if (evaluacion.getPrioridadTriage().equals(
						EvaluacionSpringDto.PRIORIDAD_TRIAGE_AMARILLO))
					concepto = concepts.cPrioridadTriageAmarillo();
				else if (evaluacion.getPrioridadTriage().equals(
						EvaluacionSpringDto.PRIORIDAD_TRIAGE_BLANCO))
					concepto = concepts.cPrioridadTriageBlanco();
				else if (evaluacion.getPrioridadTriage().equals(
						EvaluacionSpringDto.PRIORIDAD_TRIAGE_NEGRO))
					concepto = concepts.cPrioridadTriageNegro();
				else if (evaluacion.getPrioridadTriage().equals(
						EvaluacionSpringDto.PRIORIDAD_TRIAGE_ROJO))
					concepto = concepts.cPrioridadTriageRojo();
				else if (evaluacion.getPrioridadTriage().equals(
						EvaluacionSpringDto.PRIORIDAD_TRIAGE_VERDE))
					concepto = concepts.cPrioridadTriageVerde();
				datosEvaluacion.add(new SimpleObs(concepts
						.cPrioridadTriage(), concepto));
			}
			datosEvaluacion.add(new SimpleObs(concepts.cViaAereaObstruidaTriage(), evaluacion.getTriageViaAreaObstruida()));
			datosEvaluacion.add(new SimpleObs(concepts.cInsuficienciaRespiratoriaTriage(), evaluacion.getTriageInsuficienciaRespiratoria()));
			datosEvaluacion.add(new SimpleObs(concepts.cPaMenor90Triage(), evaluacion.getTriagePa90()));
			datosEvaluacion.add(new SimpleObs(concepts.cMultiplesHeridasTriage(), evaluacion.getTriageMultiplesHeridas()));
			datosEvaluacion.add(new SimpleObs(concepts.cHemorragiasNoControladasTriage(), evaluacion.getTriageHemorragiasNoControladas()));
			datosEvaluacion.add(new SimpleObs(concepts.cLesionCervicalIncompletaTriage(), evaluacion.getTriageLesionCervicalIncompleta()));
			datosEvaluacion.add(new SimpleObs(concepts.cGlasgow48Triage(), evaluacion.getTriageGlasgow4a8()));
			datosEvaluacion.add(new SimpleObs(concepts.cExcitacionPsicomotoraTriage(), evaluacion.getTriageExcitacionPsicomotora()));
			datosEvaluacion.add(new SimpleObs(concepts.cAbdomenAgudoTriage(), evaluacion.getTriageAbdomenAgudo()));
			datosEvaluacion.add(new SimpleObs(concepts.cEvisceracionTriage(), evaluacion.getTriageEvisceracion()));
			datosEvaluacion.add(new SimpleObs(concepts.cTrabajoPartoSangradoTriage(), evaluacion.getTriageTrabajoPartoSangrado()));
			datosEvaluacion.add(new SimpleObs(concepts.cDolorToracicoTriage(), evaluacion.getTriageDolorToracico()));
			datosEvaluacion.add(new SimpleObs(concepts.cArritmiasTriage(), evaluacion.getTriageArritmias()));
			datosEvaluacion.add(new SimpleObs(concepts.cHemorragiasControladasTriage(), evaluacion.getTriageHemorragiasControladas()));
			datosEvaluacion.add(new SimpleObs(concepts.cLesionMedularDorsalTriage(), evaluacion.getTriageLesionMedularDorsal()));
			datosEvaluacion.add(new SimpleObs(concepts.cGlasgow913Triage(), evaluacion.getTriageGlasgow9a13()));
			datosEvaluacion.add(new SimpleObs(concepts.cAlteracionEstadoConcienciaTriage(), evaluacion.getTriageAlteracionEstadoConciencia()));
			datosEvaluacion.add(new SimpleObs(concepts.cFracturasMayoresTriage(), evaluacion.getTriageFracturasMayores()));
			datosEvaluacion.add(new SimpleObs(concepts.cQuemadurasModeradasTriage(), evaluacion.getTriageQuemadurasModeradas()));
			datosEvaluacion.add(new SimpleObs(concepts.cIntoxicacionTriage(), evaluacion.getTriageIntoxicacion()));
			datosEvaluacion.add(new SimpleObs(concepts.cLesionMedularLumbarTriage(), evaluacion.getTriageLesionMedularLumbar()));
			datosEvaluacion.add(new SimpleObs(concepts.cGlasgow1415Triage(), evaluacion.getTriageGlasgow14o15()));
			datosEvaluacion.add(new SimpleObs(concepts.cFracturasNoProximalesTriage(), evaluacion.getTriageFracturasNoProximales()));
			datosEvaluacion.add(new SimpleObs(concepts.cLesionesSuperficialesTriage(), evaluacion.getTriageLesionesSuperficiales()));
			datosEvaluacion.add(new SimpleObs(concepts.cQuemadurasGradoITriage(), evaluacion.getTriageQuemadurasPrimerGrado()));
			datosEvaluacion.add(new SimpleObs(concepts.cAfectadosTriage(), evaluacion.getTriageAfectados()));
			datosEvaluacion.add(new SimpleObs(concepts.cParoProlongadoTriage(), evaluacion.getTriageParoProlongado()));
			datosEvaluacion.add(new SimpleObs(concepts.cLesionCervicalCompletaTriage(), evaluacion.getTriageLesionCervicalCompleta()));
			datosEvaluacion.add(new SimpleObs(concepts.cGlasgow3Triage(), evaluacion.getTriageGlasgow3()));
			datosEvaluacion.add(new SimpleObs(concepts.cExposicionMasaEncefalicaTriage(), evaluacion.getTriageExposicionMasaEncefalica()));
			datosEvaluacion.add(new SimpleObs(concepts.cLesionesImpidenRCPTriage(), evaluacion.getTriageLesionesImpidenRcp()));
			datosEvaluacion.add(new SimpleObs(concepts.cQuemadurasGravesTriage(), evaluacion.getTriageQuemadurasGraves()));
			datosEvaluacion.add(new SimpleObs(concepts.cOtroTriage(), evaluacion.getTriageOtro()));
			datosEvaluacion.add(new SimpleObs(concepts.cPupilasNormales(), evaluacion.getPupilasNormales()));
			datosEvaluacion.add(new SimpleObs(concepts.cPupilasAnisocoricas(), evaluacion.getPupilasAnisocoricas()));
			datosEvaluacion.add(new SimpleObs(concepts.cEmergenciaDolorGinecoObstetrico(), evaluacion.getEmergenciaDolor()));
			datosEvaluacion.add(new SimpleObs(concepts.cEmergenciaEdema(), evaluacion.getEmergenciaEdema()));
			datosEvaluacion.add(new SimpleObs(concepts.cEmergenciaSangrado(), evaluacion.getEmergenciaSangrado()));
			if(evaluacion.getLlenadoCapilar() != null)
			{
				concepto = null;
				if(evaluacion.getLlenadoCapilar().equals(EvaluacionSpringDto.LLENADO_CAPILAR_MAYOR))
				{
					concepto = concepts.cLlenadoCapilarMayor3();
				}
				if(evaluacion.getLlenadoCapilar().equals(EvaluacionSpringDto.LLENADO_CAPILAR_MENOR))
				{
					concepto = concepts.cLlenadoCapilarMenor3();
				}
				datosEvaluacion.add(new SimpleObs(concepts.cLlenadoCapilar(), concepto));
			}
			if(evaluacion.getUbicacionPulso() != null)
			{
				concepto = null;
				if(evaluacion.getUbicacionPulso().equals(EvaluacionSpringDto.UBICACION_PULSO_CAROTIDEA))
				{
					concepto = concepts.cUbicacionPulsoCarotideo();
				}
				if(evaluacion.getUbicacionPulso().equals(EvaluacionSpringDto.UBICACION_PULSO_RADIAL))
				{
					concepto = concepts.cUbicacionPulsoRadial();
				}

				datosEvaluacion.add(new SimpleObs(concepts.cUbicacionPulso(), concepto));
			}
			if(evaluacion.getSangrado() != null)
			{
				concepto = null;
				if(evaluacion.getSangrado().equals(EvaluacionSpringDto.SANGRADO_AUSENTE))
				{
					concepto = concepts.cSangradoExistente();
				}
				if(evaluacion.getSangrado().equals(EvaluacionSpringDto.SANGRADO_EXISTENTE))
				{
					concepto = concepts.cSangradoInexistente();
				}
				datosEvaluacion.add(new SimpleObs(concepts.cSangrado(), concepto));
			}
			if(evaluacion.getOtraEmergenciaMedica() != null)
			{
				datosEvaluacion.add(new SimpleObs(concepts.cOtraEmergenciaMedicaText(), evaluacion.getOtraEmergenciaMedica()));
			}
		    if(evaluacion.getEstadoRespiracion() != null)
		    {
		    	concepto = null;
		    	if(evaluacion.getEstadoRespiracion().equals(EvaluacionSpringDto.ESTADO_RESPIRACION_ASIMETRICA))
		    	{
		    		concepto = concepts.cEstadoRespiracionAsimetrica();
		    	}
		    	if(evaluacion.getEstadoRespiracion().equals(EvaluacionSpringDto.ESTADO_RESPIRACION_SIMETRICA))
		    	{
		    		concepto = concepts.cEstadoRespiracionSimetrica();
		    	}
		    	if(evaluacion.getEstadoRespiracion().equals(EvaluacionSpringDto.ESTADO_RESPIRACION_DIFICULTOSA))
		    	{
		    		concepto = concepts.cEstadoRespiracionDificultosa();
		    	}
		    	if(evaluacion.getEstadoRespiracion().equals(EvaluacionSpringDto.ESTADO_RESPIRACION_NORMAL))
		    	{
		    		concepto = concepts.cEstadoRespiracionNormal();
		    	}

				datosEvaluacion.add(new SimpleObs(concepts.cEstadoRespiracion(), concepto));
		    }
		    if(evaluacion.getCianosis() != null)
		    {
		    	concepto = null;
		    	if(evaluacion.getCianosis().equals(EvaluacionSpringDto.CIANOSIS_AUSENTE))
		    	{
		    		concepto = concepts.cCianosisInexistente();
		    	}
		    	if(evaluacion.getCianosis().equals(EvaluacionSpringDto.CIANOSIS_EXISTENTE))
		    	{
		    		concepto = concepts.cCianosisExistente();
		    	}
				datosEvaluacion.add(new SimpleObs(concepts.cCianosis(), concepto));
		    }
		    if(evaluacion.getPielIcterica() != null)
		    {
		    	datosEvaluacion.add(new SimpleObs(concepts.cPielIcterica(), evaluacion.getPielIcterica()));
		    }
		    if(evaluacion.getPielFria() != null)
		    {
		    	datosEvaluacion.add(new SimpleObs(concepts.cPielFria(), evaluacion.getPielFria()));
		    }
		    if(evaluacion.getTriageCadenaCustodia() != null)
		    {
		    	datosEvaluacion.add(new SimpleObs(concepts.cTriageCadenaCustodia(), evaluacion.getTriageCadenaCustodia()));
		    }
		    if(evaluacion.getTriageEntregadoEntidad() != null)
		    {
		    	datosEvaluacion.add(new SimpleObs(concepts.cTriageEntregadoEntidad(), evaluacion.getTriageEntregadoEntidad()));
		    }
		    if(evaluacion.getTriageEntidadEntregaPacienteDifunto() != null)
		    {
		    	datosEvaluacion.add(new SimpleObs(concepts.cTriageEntidadEntregaPacienteDifunto(), evaluacion.getTriageEntidadEntregaPacienteDifunto()));
		    }
		    if(evaluacion.getTriageNombrePersonaRecibeDifunto() != null)
		    {
		    	datosEvaluacion.add(new SimpleObs(concepts.cTriageNombrePersonaRecibeDifunto(), evaluacion.getTriageNombrePersonaRecibeDifunto()));
		    }
		    if(evaluacion.getTriageRegistroPersonaRecibeDifunto() != null)
		    {
		    	datosEvaluacion.add(new SimpleObs(concepts.cTriageRegistroPersonaRecibeDifunto(),evaluacion.getTriageRegistroPersonaRecibeDifunto()));
		    }
		    Date dateCreated = evaluacion.getEvaluacion().getDateCreated();
			evaluacion.setEvaluacion(new EvaluacionCompletaSpringDto(
					obsService.guardarEvaluacionHistorico(encounter, datosEvaluacion, userId, dateCreated, obsParent), encounter));
		}
		return evaluacion;
	}
	/**
	 * Permite almacenar los procedimientos especificados como argumento en la base de datos.
	 * @param procedimientos procedimientos que se desean almacenar en la base de datos.
	 * @param userId id del usuario que desea crear los procedimientos.
	 * @param obsParentId id de la evaluacion asociada a los procedimientos en la base de datos.
	 * @return procedimientos almacenados en la base de datos.
	 */
	@RequestMapping(value = ObsRestURIConstants.OBS_SERVICE_SAVE_PROCEDIMIENTOS_URI, method = RequestMethod.POST)
	public @ResponseBody  ProcedimientosSpringDto saveProcedimientos(
			@RequestBody (required = true) ProcedimientosSpringDto procedimientos, 
			@PathVariable(value=ObsRestURIConstants.OBS_SERVICE_SAVE_PROCEDIMIENTOS_USER_ID_PARAM_NAME)Integer userId, 
			@PathVariable(value = ObsRestURIConstants.OBS_SERVICE_SAVE_PROCEDIMIENTOS_OBS_PARENT_ID_PARAM_NAME)Integer obsParentId)
	{
		super.refreshHibernateSession(context);
		ConceptsCode concepts = new ConceptsCode();
		List<SimpleObs> listaProcedimientos = new ArrayList<SimpleObs>();
		if (procedimientos.getEvaluacion().getEncounter() != null) {
			Encounter encounter = fillEncounter(encounterService
					.getEncounterById(procedimientos.getEvaluacion().getEncounter()
							.getEncounterId()));

			listaProcedimientos.add(new SimpleObs(concepts
					.cAspiracionSecreciones(), procedimientos
					.getAspiracionSecreciones()));
			listaProcedimientos.add(new SimpleObs(concepts
					.cCanulaOrofaringea(), procedimientos
					.getCanulaOrofaringea()));
			listaProcedimientos.add(new SimpleObs(
					concepts.cDespejeManual(), procedimientos
							.getDespejeManual()));
			listaProcedimientos
					.add(new SimpleObs(concepts.cCollarCervical(),
							procedimientos.getCollarCervical()));
			listaProcedimientos.add(new SimpleObs(concepts
					.cCanulaNasofaringea(), procedimientos
					.getCanulaNasofaringea()));
			listaProcedimientos.add(new SimpleObs(concepts
					.cReanimacionRespiratoria(), procedimientos
					.getReanimacionRespiratoria()));
			listaProcedimientos.add(new SimpleObs(concepts.cCanulaNasal(),
					procedimientos.getCanulaNasal()));
			listaProcedimientos.add(new SimpleObs(concepts
					.cMascaraNoReinhalacion(), procedimientos
					.getMascaraNoReinhalacion()));
			listaProcedimientos.add(new SimpleObs(
					concepts.cMascaraSimple(), procedimientos
							.getMascaraSimple()));
			listaProcedimientos.add(new SimpleObs(concepts.cBvm(),
					procedimientos.getBvm()));
			listaProcedimientos.add(new SimpleObs(concepts.cRccp(),
					procedimientos.getRccp()));
			listaProcedimientos.add(new SimpleObs(concepts.cHemostasia(),
					procedimientos.getHemostasia()));
			listaProcedimientos.add(new SimpleObs(concepts.cDea(),
					procedimientos.getDea()));
			listaProcedimientos.add(new SimpleObs(concepts
					.cMonitoreoSignosVitales(), procedimientos
					.getMonitoreoSignosVitales()));
			listaProcedimientos.add(new SimpleObs(concepts
					.cDispositivoSupragliotico(), procedimientos
					.getDispositivoSupragliotico()));
			listaProcedimientos.add(new SimpleObs(concepts
					.cCricotiroidotomia(), procedimientos
					.getCricotiroidotomia()));
			listaProcedimientos.add(new SimpleObs(concepts
					.cIntubacionOrotraqueal(), procedimientos
					.getIntubacionOrotraqueal()));
			listaProcedimientos.add(new SimpleObs(concepts
					.cDescompresionTorax(), procedimientos
					.getDescompresionTorax()));
			listaProcedimientos.add(new SimpleObs(concepts
					.cVentilacionMecanica(), procedimientos
					.getVentilacionMecanica()));
			listaProcedimientos.add(new SimpleObs(concepts
					.cRehidratacionOral(), procedimientos
					.getRehidratacionOral()));
			listaProcedimientos.add(new SimpleObs(concepts.cSsn09(),
					procedimientos.getSsn09()));
			listaProcedimientos.add(new SimpleObs(concepts
					.cAccesoVenosoPeriferico(), procedimientos
					.getAccesoVenosoPeriferico()));
			listaProcedimientos.add(new SimpleObs(concepts.cDestrosa(),
					procedimientos.getDestrosa()));
			listaProcedimientos.add(new SimpleObs(concepts.cPunsionOsea(),
					procedimientos.getPunsionOsea()));
			listaProcedimientos.add(new SimpleObs(concepts.cColoides(),
					procedimientos.getColoides()));
			listaProcedimientos.add(new SimpleObs(concepts.cHartman(),
					procedimientos.getHartman()));
			listaProcedimientos.add(new SimpleObs(concepts.cExposicion(),
					procedimientos.getExposicion()));
			listaProcedimientos.add(new SimpleObs(concepts
					.cMovimientoBloque(), procedimientos
					.getMovimientoBloque()));
			listaProcedimientos
					.add(new SimpleObs(concepts.cLavadoCuracion(),
							procedimientos.getLavadoCuracion()));
			listaProcedimientos.add(new SimpleObs(concepts
					.cChalecoExtracionVehicular(), procedimientos
					.getChalecoExtracionVehicular()));
			listaProcedimientos.add(new SimpleObs(concepts.cMantaTermica(),
					procedimientos.getMantaTermica()));
			listaProcedimientos.add(new SimpleObs(concepts
					.cTablaEspinalLarga(), procedimientos
					.getTablaEspinalLarga()));
			listaProcedimientos.add(new SimpleObs(concepts
					.cInmovilizacionFerulas(), procedimientos
					.getInmovilizacionFerulas()));
			listaProcedimientos.add(new SimpleObs(concepts
					.cTablaEspinalCorta(), procedimientos
					.getTablaEspinalCorta()));
			listaProcedimientos.add(new SimpleObs(concepts
					.cOtrosProcedimientos(), procedimientos
					.getOtrosProcedimientos()));
			
			listaProcedimientos.add(new SimpleObs(concepts.cProcedimientoMasajeCardiaco(), procedimientos.getMasajeCardiaco()));
			listaProcedimientos.add(new SimpleObs(concepts.cProcedimientoAccesoVenosoCentral(), procedimientos.getAccesoVenosoCentral()));
			listaProcedimientos.add(new SimpleObs(concepts.cProcedimientoPuncionOsea(), procedimientos.getPuncionOsea()));
			listaProcedimientos.add(new SimpleObs(concepts.cProcedimientoAtencionParto(), procedimientos.getAtencionParto()));
			listaProcedimientos.add(new SimpleObs(concepts.cProcedimientoGlucometria(), procedimientos.getGlucometria()));
			listaProcedimientos.add(new SimpleObs(concepts.cProcedimientoTorniqueteControlado(), procedimientos.getTorniqueteControlado()));			
			listaProcedimientos.add(new SimpleObs(concepts.cProcedimientoDesfibrilacion(), procedimientos.getDesfibrilacion()));			
			listaProcedimientos.add(new SimpleObs(concepts.cJoulesDesfibrilacion(), procedimientos.getJoulesDesfibrilacion()));			
			Obs obsParent = obsService.getObs(obsParentId);
			procedimientos.setEvaluacion(new EvaluacionCompletaSpringDto(obsService.saveProcedimientoHistorico(encounter, listaProcedimientos, userId, obsParent), encounter));
		}
		return procedimientos;
	}

	/**
	 * Permite almacenar la solicitud de teleasistencia especificada como argumento en la base de datos.
	 * @param lesionadoId id del lesionado para el que se desea realizar la solicitud de teleasistencia.
	 * @param userId id del usuario que desea crear la solicitud de teleasistencia.
	 * @param teleasistencia informacion de la solicitud de teleasistencia que se desea almacenar en la
	 * base de datos.
	 * @return solicitud de teleasistencia almacenada en la base de datos.
	 */
	@RequestMapping(value = ObsRestURIConstants.OBS_SERVICE_SAVE_SOLICITUD_TELLEASISTENCIA_URI, method = RequestMethod.POST)
	public @ResponseBody
	Integer saveSolicitudTeleasistencia(
			@PathVariable(value = ObsRestURIConstants.OBS_SERVICE_SAVE_SOLICITUD_TELEASISTENCIA_LESIONADO_ID_PARAM_NAME) Integer lesionadoId,
			@PathVariable(value = ObsRestURIConstants.OBS_SERVICE_SAVE_SOLICITUD_TELEASISTENCIA_USER_ID_PARAM_NAME) Integer userId,
			@RequestBody(required = true) TeleasistenciaSpringDto teleasistencia) {
		super.refreshHibernateSession(context);
		Integer usuario = userId;
		Lesionado lesionado = lesionadoService.obtenerLesionado(lesionadoId);
		Obs obsParent = obsService.getObs(teleasistencia.getTeleasistenciaId());
		Obs solicitudId = obsService.guardarSolicitudTeleasistencia(
				lesionado.getEncuentro(), teleasistencia.getFecha(),
				teleasistencia.getMotivoSolicitud(), teleasistencia
						.getDetalles(), teleasistencia.getMedio()
						.getConceptId(), usuario, obsParent);
		lesionado.setSolicitaTeleasistencia(1);
		lesionadoService.actualizarLesionado(lesionado);
		return solicitudId.getObsId();
	}

	/**
	 * Permite especificar una nota de evolucion de una teleasistencia realizada a un paciente.
	 * @param lesionadoId id del lesionado para el que se desea realizar la nota de evolucion.
	 * @param idTeleasistencia id de la teleasistencia para la que se desea hacer la nota de evolucion.
	 * @param userId id del usuario que crea la nota de evolucion.
	 * @param notaEvolucion informacion de la nota de evolucion.
	 * @return nota de evolucion almacenada en la base de datos.
	 */
	@RequestMapping(value = ObsRestURIConstants.OBS_SERVICE_SAVE_NOTA_EVOLUCION_URI)
	public @ResponseBody
	Integer saveNotaEvolucion(
			@PathVariable(value = ObsRestURIConstants.OBS_SERVICE_SAVE_NOTA_EVOLUCION_LESIONADO_ID_PARAM_NAME) Integer lesionadoId,
			@PathVariable(value = ObsRestURIConstants.OBS_SERVICE_SAVE_NOTA_EVOLUCION_TELEASISTENCIA_ID_PARAM_NAME) Integer idTeleasistencia,
			@PathVariable(value = ObsRestURIConstants.OBS_SERVICE_SAVE_NOTA_EVOLUCION_USER_ID_PARAM_NAME) Integer userId,
			@RequestBody(required = true) NotaEvolucionSpringDto notaEvolucion) {
		super.refreshHibernateSession(context);
		Integer dxPpal = null;
		Integer medico = null;
		List<Integer> dxSecundarios = null;
		Date fechaActual = new Date();
		/*try {*/
			Integer usuario = userId;
			Lesionado lesionado = lesionadoService
					.obtenerLesionado(lesionadoId);
			if (notaEvolucion.getDxPrincipal() != null) {
				dxPpal = notaEvolucion.getDxPrincipal().getConceptId();
			}

			dxSecundarios = new ArrayList<Integer>();
			if(notaEvolucion.getDxSecundarios() != null)
			{
			for (ConceptSpringDto dx : notaEvolucion.getDxSecundarios()) {
				dxSecundarios.add(dx.getConceptId());
			}
			}
			String nombreMedico = notaEvolucion.getMedicoTratanteNombre();
			if(notaEvolucion.getMedicoTratante() != null)
			{
				medico = notaEvolucion.getMedicoTratante().getUserId();
			}
			Obs obsParent = obsService.getObs(notaEvolucion.getObsId());
			Obs obs = obsService.guardarNotaEvolucion(lesionado.getEncuentro(),
					idTeleasistencia, fechaActual, dxPpal, dxSecundarios,
					notaEvolucion.getRecomendaciones(), medico, usuario, nombreMedico, obsParent);
			return obs.getObsId();
			/*} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ObsController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}*/
	}
	/**
	 * Permite crear una nota aclaratoria.
	 * @param userId id del usuario que desea crear la nota aclaratoria.
	 * @param notaAclaratoria informacion de la nota aclaratoria que incluye el id del lesionado asociado a la
	 * nota aclaratoria.
	 * @return nota aclaratoria creada en la base de datos.
	 */
	@RequestMapping(value = ObsRestURIConstants.OBS_SERVICE_SAVE_NOTA_ACLARATORIA_URI, method = RequestMethod.POST)
	public @ResponseBody
	NotaAclaratoriaSpringDto saveNotaAclaratoria(
			@PathVariable(value = ObsRestURIConstants.OBS_SERVICE_SAVE_NOTA_ACLARATORIA_USER_ID_PARAM_NAME) Integer userId,
			 @RequestBody(required = true) NotaAclaratoriaSpringDto notaAclaratoria) {

		super.refreshHibernateSession(context);
		Integer usuario = userId;
		Integer idUsuario = null;
		if(notaAclaratoria != null)
		{
		Integer lesionadoId = notaAclaratoria.getLesionadoId();
		if(lesionadoId != null)
		{
		Lesionado lesionado = lesionadoService.obtenerLesionado(lesionadoId);
		if(lesionado != null)
		{
		String nota = notaAclaratoria.getNota();
		Integer obsId = notaAclaratoria.getObsId();
		Person person = personService.obtenerPorUsuario(usuario);
		if (person != null) {
			idUsuario = person.getPersonId();
		}
		Obs obsParent = obsService.getObs(obsId);
		notaAclaratoria.setObsId(obsService.guardarNotaAclaratoria(lesionado.getEncuentro(), idUsuario,
				nota, usuario, obsParent).getObsId());
		}
		}
		}
		return notaAclaratoria;
	}
	/**
	 * Permite almacenar el insumo especificado como argumento en la base de datos.
	 * @param insumo el insumo que se desea almacenar en la base de datos.
	 * @param userId id del usuario que desea crear el insumo.
	 * @param obsParentId id de la evaluacion a la cual pertenece el insumo especificado como argumento.
	 * @return insumo creado en la base de datos.
	 */
	@RequestMapping(value=ObsRestURIConstants.OBS_SERVICE_SAVE_INSUMO_URI, method = RequestMethod.POST)
	public @ResponseBody InsumoSpringDto saveInsumo(
			@RequestBody InsumoSpringDto insumo, 
			@PathVariable(value=ObsRestURIConstants.OBS_SERVICE_SAVE_INSUMO_USER_ID_PARAM_NAME)Integer userId, 
			@PathVariable(value=ObsRestURIConstants.OBS_SERVICE_SAVE_INSUMO_OBS_PARENT_ID_PARAM_NAME)Integer obsParentId)
	{
		if(insumo.getEvaluacion() != null && insumo.getEvaluacion().getEncounter() != null)
		{
			Integer obsId = insumo.getObsId();
			Encounter encuentro = fillEncounter(encounterService.getEncounterById(insumo.getEvaluacion().getEncounter().getEncounterId()));
			Obs obsEvaluacionParent = obsService.getObs(obsParentId);
			ConceptsCode concepts = new ConceptsCode();
			List<SimpleObs> datosInsumo = new ArrayList<SimpleObs>();
			datosInsumo.add(new SimpleObs(concepts.cInsumo(), insumo.getInsumo().getConceptId()));
			datosInsumo.add(new SimpleObs(concepts.cViaAdministracionInsumo(), insumo.getViaAdministracion().getConceptId()));
			datosInsumo.add(new SimpleObs(concepts.cCantidadInsumo(), insumo.getCantidad()));
			datosInsumo.add(new SimpleObs(concepts.cUnidadVolumenInsumo(), insumo.getUnidades().getConceptId()));
			datosInsumo.add(new SimpleObs(concepts.cSuministroInmediatoInsumo(), insumo.getInmediato()));
			datosInsumo.add(new SimpleObs(concepts.cHorasSuministroInsumo(), insumo.getHoras()));
			datosInsumo.add(new SimpleObs(concepts.cMinutosSuministroInsumo(), insumo.getMinutos()));
			datosInsumo.add(new SimpleObs(concepts.cSegundosSuministroInsumo(), insumo.getSegundos()));
			Obs obs = obsService.saveInsumo(encuentro, datosInsumo, userId, obsEvaluacionParent, obsId);
			insumo.setObsId(obs.getObsId());
		}
		return insumo;
	}
	/**
	 * Permite almacenar el hallazgo especificado como argumento en la base de datos.
	 * @param hallazgo hallazgo que se desea almacenar en la base de datos.
	 * @param userId id del usuario que desea crear el hallazgo.
	 * @param obsParentId id de la evaluacion asociada al hallazgo que se desea crear.
	 * @return hallazgo creado en la base de datos.
	 */
	@RequestMapping(value=ObsRestURIConstants.OBS_SERVICE_SAVE_HALLAZGO_HISTORICO_URI, method=RequestMethod.POST)
	public @ResponseBody HallazgoSpringDto saveHallazgoHistorico(
			@RequestBody HallazgoSpringDto hallazgo, 
			@PathVariable(value=ObsRestURIConstants.OBS_SERVICE_SAVE_HALLAZGO_HISTORICO_USER_ID_PARAM_NAME)Integer userId, 
			@PathVariable(value=ObsRestURIConstants.OBS_SERVICE_SAVE_HALLAZGO_HISTORICO_OBS_PARENT_ID_PARAM_NAME)Integer obsParentId)
	{
		super.refreshHibernateSession(context);
		if (hallazgo.getEvaluacion() != null && hallazgo.getEvaluacion().getEncounter() != null) {
			Integer hallazgoId = hallazgo.getHallazgoId();
			Encounter encuentro = fillEncounter(encounterService.getEncounterById(hallazgo.getEvaluacion().getEncounter().getEncounterId()));
			Obs obsEvaluacionParent = obsService.getObs(obsParentId);
			ConceptsCode concepts = new ConceptsCode();
			List<SimpleObs> datosHallazgo = new ArrayList<SimpleObs>();
			datosHallazgo.add(new SimpleObs(concepts.cHallazgoOrden(),
					Double.parseDouble(hallazgo.getOrden().toString())));
			datosHallazgo.add(new SimpleObs(concepts.cHallazgoPosicionX(),
					Double.parseDouble(hallazgo.getX().toString())));
			datosHallazgo.add(new SimpleObs(concepts.cHallazgoPosicionY(),
					Double.parseDouble(hallazgo.getY().toString())));
			datosHallazgo.add(new SimpleObs(concepts.cLesionAbrasion(), hallazgo.getAbrasion()));
			datosHallazgo.add(new SimpleObs(concepts.cLesionAmputacion(), hallazgo.getAmputacion()));
			datosHallazgo.add(new SimpleObs(concepts.cLesionAplastamiento(), hallazgo.getAplastamiento()));
			datosHallazgo.add(new SimpleObs(concepts.cLesionAvulsion(), hallazgo.getAvulsion()));
			datosHallazgo.add(new SimpleObs(concepts.cLesionContusion(), hallazgo.getContusion()));
			datosHallazgo.add(new SimpleObs(concepts.cLesionDolor(), hallazgo.getDolor()));
			datosHallazgo.add(new SimpleObs(concepts.cLesionEsguince(), hallazgo.getEsguince()));
			datosHallazgo.add(new SimpleObs(concepts.cLesionFracturaAbierta(), hallazgo.getFracturaAbierta()));
			datosHallazgo.add(new SimpleObs(concepts.cLesionFracturaCerrada(), hallazgo.getFracturaCerrada()));
			datosHallazgo.add(new SimpleObs(concepts.cLesionHematoma(), hallazgo.getHematoma()));
			datosHallazgo.add(new SimpleObs(concepts.cLesionHemorragia(), hallazgo.getHemorragia()));
			datosHallazgo.add(new SimpleObs(concepts.cLesionHerida(), hallazgo.getHerida()));
			datosHallazgo.add(new SimpleObs(concepts.cLesionHeridaArmaBlanca(), hallazgo.getHeridaArmaBlanca()));
			datosHallazgo.add(new SimpleObs(concepts.cLesionHeridaArmaFuego(), hallazgo.getHeridaArmaDeFuego()));
			datosHallazgo.add(new SimpleObs(concepts.cLesionLaceracion(), hallazgo.getLaceracion()));
			datosHallazgo.add(new SimpleObs(concepts.cLesionMordida(), hallazgo.getMordida()));
			datosHallazgo.add(new SimpleObs(concepts.cLesionPicadura(), hallazgo.getPicadura()));
			datosHallazgo.add(new SimpleObs(concepts.cLesionPuncion(), hallazgo.getPuncion()));
			datosHallazgo.add(new SimpleObs(concepts.cLesionQuemadura(), hallazgo.getQuemadura()));
			datosHallazgo.add(new SimpleObs(concepts.cLesionTraumaCerrado(), hallazgo.getTraumaCerrado()));
			datosHallazgo.add(new SimpleObs(concepts.cLesionTraumaPenetrante(), hallazgo.getTraumaPenetrante()));
			datosHallazgo.add(new SimpleObs(concepts.cLesionCuerpoExtranio(), hallazgo.getCuerpoExtranio()));
			datosHallazgo.add(new SimpleObs(concepts.cLesionCrepitacion(), hallazgo.getCrepitacion()));
			Obs obs = obsService.saveHallazgoHistorico(encuentro, datosHallazgo, userId, obsEvaluacionParent, hallazgoId);
			hallazgo.setHallazgoId(obs.getObsId());
		}
		return hallazgo;
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
		encounterService = new EncounterService(
				servletContext
						.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);
		obsService = new ObsService(
				servletContext
						.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);
		patientService = new PatientService(
				servletContext
						.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);
	
		personService = new PersonService(
				servletContext
						.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);
		lesionadoService = new LesionadoService(
				servletContext
						.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);

	}

}
