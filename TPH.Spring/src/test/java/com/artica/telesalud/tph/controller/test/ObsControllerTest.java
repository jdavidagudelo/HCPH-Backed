package com.artica.telesalud.tph.controller.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.RestTemplate;

import com.artica.telesalud.common.data.HibernateSessionFactoryManager;
import com.artica.telesalud.persona.model.impl.UserDTO;
import com.artica.telesalud.persona.service.UserService;
import com.artica.telesalud.tph.concepts.ConceptsCode;
import com.artica.telesalud.tph.controller.model.concept.ConceptSpringDto;
import com.artica.telesalud.tph.controller.model.encounter.EncounterSpringDto;
import com.artica.telesalud.tph.controller.model.observation.AntecedenteSpringDto;
import com.artica.telesalud.tph.controller.model.observation.CierreAtencionSpringDto;
import com.artica.telesalud.tph.controller.model.observation.EvaluacionSpringDto;
import com.artica.telesalud.tph.controller.model.observation.HallazgoSpringDto;
import com.artica.telesalud.tph.controller.model.observation.EvaluacionCompletaSpringDto;
import com.artica.telesalud.tph.controller.model.observation.NotaAclaratoriaSpringDto;
import com.artica.telesalud.tph.controller.model.observation.NotaEvolucionSpringDto;
import com.artica.telesalud.tph.controller.model.observation.ProcedimientosSpringDto;
import com.artica.telesalud.tph.controller.model.observation.ResultadoSpringDto;
import com.artica.telesalud.tph.controller.model.observation.SignosVitalesSpringDto;
import com.artica.telesalud.tph.controller.model.observation.TeleasistenciaSpringDto;
import com.artica.telesalud.tph.controller.model.patient.PatientSpringDto;
import com.artica.telesalud.tph.controller.model.user.UserSpringDto;
import com.artica.telesalud.tph.controller.user.SessionUserApp;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
import com.artica.telesalud.tph.model.concept.Concept;
import com.artica.telesalud.tph.model.encounter.Encounter;
import com.artica.telesalud.tph.model.observation.Obs;
import com.artica.telesalud.tph.model.observation.ObservationData;
import com.artica.telesalud.tph.model.patient.Patient;
import com.artica.telesalud.tph.model.person.Person;
import com.artica.telesalud.tph.service.ConceptService;
import com.artica.telesalud.tph.service.EncounterService;
import com.artica.telesalud.tph.service.ObsService;
import com.artica.telesalud.tph.service.PatientService;
import com.artica.telesalud.tph.service.PersonaService;

class ListAntecedentes extends ArrayList<AntecedenteSpringDto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ListAntecedentes() {
		super();
	}

}
class ListTeleasistencias extends ArrayList<TeleasistenciaSpringDto>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ListTeleasistencias() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
class ListHallazgos extends ArrayList<HallazgoSpringDto> {
	private static final long serialVersionUID = 1L;

	public ListHallazgos() {
		super();
	}
}
class ListNotaAclaratoria extends ArrayList<NotaAclaratoriaSpringDto>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ListNotaAclaratoria() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
public class ObsControllerTest {
	static HttpHeaders getHeaders(String auth) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		byte[] encodedAuthorisation = Base64.encode(auth.getBytes());
		headers.add("Authorization", "Basic "
				+ new String(encodedAuthorisation));

		return headers;
	}
	private String baseUrl = "http://localhost:8091/TPH.Spring/";
	private PatientService patientService;
	private EncounterService encounterService;
	private ObsService obsService;
	private ConceptService conceptService;
	private PersonaService personService;
	private UserService userService;
	@Before
	public void init() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(HibernateTPHDAOFactory.TPH_HIBERNATE_CONFIG_FILE,
				"hibernate-riesgo.cfg.xml");
		HibernateSessionFactoryManager manager = HibernateSessionFactoryManager
				.getInstance();
		manager.createFactory("hibernate-tph.cfg.xml");
		conceptService = new ConceptService(
				"com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory",
				params);
		obsService = new ObsService(
				"com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory",
				params);

		patientService = new PatientService(
				"com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory",
				params);

		personService = new PersonaService(
				"com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory",
				params);
		encounterService = new EncounterService(
				"com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory",
				params);
						userService = new com.artica.telesalud.persona.service.UserService("com.artica.telesalud.persona.dao.hibernate.HibernatePersonaDAOFactory", params);
	
	}
	public List<TeleasistenciaSpringDto> getTeleasistencias(Integer encounterId)
	{
		List<TeleasistenciaSpringDto> lista = new ArrayList<TeleasistenciaSpringDto>();
		ConceptsCode concepts = new ConceptsCode();
		Encounter encounter = encounterService.getEncounterById(encounterId);
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
			teleasistenciaData.setFecha((Date) ObservationData
					.obtenerValorConcepto(od.getSons(),
							concepts.cFechaSolicitudTeleasistencia()));
			ConceptSpringDto medio = new ConceptSpringDto(
					conceptService.obtenerConcepto((Integer) ObservationData
							.obtenerValorConcepto(od.getSons(),
									concepts.cMedioSolicitudTeleasistencia())));
			teleasistenciaData.setMedio(medio);

			teleasistenciaData
					.setNotasEvolucion(new ArrayList<NotaEvolucionSpringDto>());

			idMedico = (String) ObservationData.obtenerValorConcepto(
					od.getSons(), concepts.cUsuarioSolicitaTeleasistencia());

			if (idMedico != null) {

				Integer concept = Integer.parseInt(idMedico);
				UserDTO user = userService.findUserById(concept);
				Person person = personService.getPersonById(user.getPersonId());
				String roleName = SessionUserApp.getRolName(user.getRoleId());
				UserSpringDto medicoSolicitante = new UserSpringDto(user,
						person, roleName);
				teleasistenciaData.setMedicoSolicitante(medicoSolicitante);
			}

			for (ObservationData odM : ObservationData.obtenerListaValores(
					od.getSons(), concepts.cNotaEvolucion())) {

				NotaEvolucionSpringDto ne = new NotaEvolucionSpringDto();

				Integer concept = (Integer) ObservationData
						.obtenerValorConcepto(odM.getSons(),
								concepts.cDiagnosticoPrincipalNotaEvolucion());
				if (concept != null) {
					Concept dxPrincipal = conceptService
							.obtenerConcepto(concept);
					ConceptSpringDto diagnosticoPrincipal = new ConceptSpringDto(
							dxPrincipal);
					ne.setDxPrincipal(diagnosticoPrincipal);
				}
				idMedico = (String) ObservationData.obtenerValorConcepto(
						odM.getSons(), concepts.cMedicoTratanteNotaEvolucion());
				if (idMedico != null) {

					concept = Integer.parseInt(idMedico);
					UserDTO user = userService.findUserById(concept);
					Person person = personService.getPersonById(user
							.getPersonId());
					String roleName = SessionUserApp.getRolName(user.getRoleId());
					UserSpringDto medicoTratante = new UserSpringDto(user,
							person, roleName);
					ne.setMedicoTratante(medicoTratante);
				}
				ne.setFecha((Date) ObservationData.obtenerValorConcepto(
						odM.getSons(), concepts.cFechaNotaEvolucion()));
				ne.setRecomendaciones((String) ObservationData
						.obtenerValorConcepto(odM.getSons(),
								concepts.cRecomendacionesNotaEvolucion()));
				ne.setDxSecundarios(new ArrayList<ConceptSpringDto>());

				for (ObservationData odMD : ObservationData
						.obtenerListaValores(odM.getSons(),
								concepts.cDiagnosticoSecundarioNotaEvolucion())) {

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
	}
	private Encounter fillEncounter(Encounter encounter) {
		encounter.setPatient(patientService.buscarPatient(encounter
				.getPatient().getPatientId()));
		return encounter;
	}

	public ListAntecedentes getAntecedentes(Integer patientId) {
		ConceptsCode concepts = new ConceptsCode();
		Patient patient = patientService.buscarPatient(patientId);
		PatientSpringDto patientSpring = new PatientSpringDto(patient);
		ListAntecedentes antecedentes = new ListAntecedentes();
		for (ObservationData od : obsService.obtenerAntecedentes(patient)) {
			AntecedenteSpringDto antecedente = new AntecedenteSpringDto();
			for (ObservationData odL : od.getSons()) {
				if (odL.getConceptId().equals(concepts.cAnioAntecedente())) {
					antecedente.setYear(odL.getValueNumeric().intValue());
				}
				if (odL.getConceptId().equals(concepts.cTipoAntecedente())) {
					antecedente.setConceptTipoAntecedente(new ConceptSpringDto(conceptService.obtenerConcepto(odL.getValueCoded())));

					Concept c = conceptService.obtenerConcepto(antecedente.getConceptTipoAntecedente().getConceptId());
					if (c != null) {
						antecedente.setTipoAntecedente(c.getShortName());
					}
				}
				if (odL.getConceptId().equals(
						concepts.cObservacionAntecedente())) {
					antecedente.setObservacion(odL.getValueText());
				}
			}
			antecedente.setPatient(patientSpring);
			antecedentes.add(antecedente);

		}
		return antecedentes;
	}

	public ProcedimientosSpringDto getProcedimientos(Integer encounterId) {
		Encounter encounter = fillEncounter(encounterService
				.getEncounterById(encounterId));
		ObservationData od = obsService.obtenerProcedimientos(
				encounter);
		return new ProcedimientosSpringDto(od, od.getSons(), encounter, null);
	}

	public SignosVitalesSpringDto getSignosVitales(Integer encounterId) {
		Encounter encounter = fillEncounter(encounterService
				.getEncounterById(encounterId));
		return new SignosVitalesSpringDto(
				obsService.obtenerUltimosSignosVitales(encounter), encounter);
	}

	private EvaluacionSpringDto getEvaluacion(Integer encounterId) {
		Encounter encounter = fillEncounter(encounterService
				.getEncounterById(encounterId));
		return new EvaluacionSpringDto(obsService.obtenerEvaluacion(
				encounter).getSons(), conceptService);
	}

	//@Test
	public void testGetAntecedentes() {

		HttpEntity<String> requestEntity = new HttpEntity<String>(
				getHeaders("Digitador" + ":" + "Digitador"));
		for (Patient patient : patientService.getAll()) {
			Integer patientId = patient.getPatientId();
			String url = baseUrl+"ObsController/getAntecedentes/{patientId}";
			RestTemplate template = new RestTemplate();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("patientId", patientId);
			ResponseEntity<ListAntecedentes> entity = template.exchange(url,
					HttpMethod.GET, requestEntity, ListAntecedentes.class, map);
			ListAntecedentes list = entity.getBody();
			Assert.assertNotNull(template);
			Assert.assertNotNull(entity);
			for (AntecedenteSpringDto p : list) {
				Assert.assertNotNull(p);
			}
			Assert.assertTrue(list.equals(getAntecedentes(patientId)));
		}
	}

	//@Test
	public void testGetProcedimientos() {

		HttpEntity<String> requestEntity = new HttpEntity<String>(
				getHeaders("Digitador" + ":" + "Digitador"));

		for (Encounter encounter : encounterService.getAll()) {
			Integer encounterId = encounter.getEncounterId();
			String url = baseUrl+"ObsController/getProcedimientos/{encounterId}";
			RestTemplate template = new RestTemplate();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("encounterId", encounterId);
			ResponseEntity<ProcedimientosSpringDto> entity = template.exchange(
					url, HttpMethod.GET, requestEntity,
					ProcedimientosSpringDto.class, map);
			ProcedimientosSpringDto list = entity.getBody();
			Assert.assertNotNull(template);
			Assert.assertNotNull(entity);
			Assert.assertNotNull(list);
			Assert.assertTrue(list.equals(getProcedimientos(encounterId)));
		}
	}

	//@Test
	public void testGetSignosVitales() {

		HttpEntity<String> requestEntity = new HttpEntity<String>(
				getHeaders("Digitador" + ":" + "Digitador"));

		for (Encounter encounter : encounterService.getAll()) {
			Integer encounterId = encounter.getEncounterId();
			String url = baseUrl+"ObsController/getSignosVitales/{encounterId}";
			RestTemplate template = new RestTemplate();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("encounterId", encounterId);
			ResponseEntity<SignosVitalesSpringDto> entity = template.exchange(
					url, HttpMethod.GET, requestEntity,
					SignosVitalesSpringDto.class, map);
			SignosVitalesSpringDto list = entity.getBody();
			Assert.assertNotNull(template);
			Assert.assertNotNull(entity);
			Assert.assertNotNull(list);
			Assert.assertTrue(list.equals(getSignosVitales(encounterId)));
		}
	}

	//@Test
	public void testGetEvaluacion() {

		HttpEntity<String> requestEntity = new HttpEntity<String>(
				getHeaders("Digitador" + ":" + "Digitador"));

		for (Encounter encounter : encounterService.getAll()) {
			Integer encounterId = encounter.getEncounterId();
			String url = baseUrl+"ObsController/getEvaluacion/{encounterId}";
			RestTemplate template = new RestTemplate();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("encounterId", encounterId);
			ResponseEntity<EvaluacionSpringDto> entity = template.exchange(url,
					HttpMethod.GET, requestEntity, EvaluacionSpringDto.class,
					map);
			EvaluacionSpringDto list = entity.getBody();
			Assert.assertNotNull(template);
			Assert.assertNotNull(entity);
			Assert.assertNotNull(list);
			System.out.println(list);
			System.out.println(getEvaluacion(encounterId));
			System.out.println(encounterId+"=>"+list.equals(getEvaluacion(encounterId)));
			
			Assert.assertTrue(list.equals(getEvaluacion(encounterId)));
		}
	}
	//@Test
	public void testGetEvaluaciones() {

		HttpEntity<String> requestEntity = new HttpEntity<String>(
				getHeaders("Digitador" + ":" + "Digitador"));

		for (Encounter encounter : encounterService.getAll()) {
			Integer encounterId = 37;
			String url = baseUrl+"ObsController/getEvaluaciones/{encounterId}";
			RestTemplate template = new RestTemplate();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("encounterId", encounterId);
			ResponseEntity<EvaluacionSpringDto> entity = template.exchange(url,
					HttpMethod.GET, requestEntity, EvaluacionSpringDto.class,
					map);
			EvaluacionSpringDto list = entity.getBody();
			Assert.assertNotNull(template);
			Assert.assertNotNull(entity);
			Assert.assertNotNull(list);
			System.out.println(list);
		}
	}
	//@Test
	public void testGetHallazgos() {

		HttpEntity<String> requestEntity = new HttpEntity<String>(
				getHeaders("Digitador" + ":" + "Digitador"));

		for (Encounter encounter : encounterService.getAll()) {
			Integer encounterId = encounter.getEncounterId();
			String url = baseUrl+"ObsController/getHallazgos/{encounterId}";
			RestTemplate template = new RestTemplate();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("encounterId", encounterId);
			ResponseEntity<ListHallazgos> entity = template.exchange(url,
					HttpMethod.GET, requestEntity, ListHallazgos.class, map);
			ListHallazgos list = entity.getBody();
			Assert.assertNotNull(template);
			Assert.assertNotNull(entity);
			Assert.assertNotNull(list);
		}
	}
	//@Test
	public void testGetTeleasistencias() {

		HttpEntity<String> requestEntity = new HttpEntity<String>(
				getHeaders("Digitador" + ":" + "Digitador"));

		for (Encounter encounter : encounterService.getAll()) {
			Integer encounterId = encounter.getEncounterId();
			String url = baseUrl+"ObsController/getTeleasistencias/{encounterId}";
			RestTemplate template = new RestTemplate();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("encounterId", encounterId);
			ResponseEntity<ListTeleasistencias> entity = template.exchange(url,
					HttpMethod.GET, requestEntity, ListTeleasistencias.class, map);
			ListTeleasistencias list = entity.getBody();
			Assert.assertNotNull(template);
			Assert.assertNotNull(entity);
			System.out.println(encounter.getEncounterId()+"=>"+list);
			//Assert.assertNotNull(list);
		}
	}
	//@Test
	public void testGetResultado() {

		HttpEntity<String> requestEntity = new HttpEntity<String>(
				getHeaders("Digitador" + ":" + "Digitador"));

		for (Encounter encounter : encounterService.getAll()) {
			Integer encounterId = encounter.getEncounterId();
			String url = baseUrl+"ObsController/getResultado/{lesionadoId}";
			RestTemplate template = new RestTemplate();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("lesionadoId", encounterId);
			ResponseEntity<ResultadoSpringDto> entity = template.exchange(url,
					HttpMethod.GET, requestEntity, ResultadoSpringDto.class,
					map);
			ResultadoSpringDto list = entity.getBody();
			Assert.assertNotNull(template);
			Assert.assertNotNull(entity);
			Assert.assertNotNull(list);
			System.out.println(encounterId+"=>"+list);
		}
	}
	
	//@Test
	public void testGetCierreAtencion() {

		HttpEntity<String> requestEntity = new HttpEntity<String>(
				getHeaders("Digitador" + ":" + "Digitador"));

		for (Encounter encounter : encounterService.getAll()) {
			Integer encounterId = encounter.getEncounterId();
			String url = baseUrl+"ObsController/getCierreAtencion/{encounterId}";
			RestTemplate template = new RestTemplate();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("encounterId", encounterId);
			ResponseEntity<CierreAtencionSpringDto> entity = template.exchange(url,
					HttpMethod.GET, requestEntity, CierreAtencionSpringDto.class, map);
			CierreAtencionSpringDto list = entity.getBody();
			Assert.assertNotNull(template);
			Assert.assertNotNull(entity);
			
			//Assert.assertNotNull(list);
		}
	}
	//@Test
	public void testGetNotasAclaratorias() {

		HttpEntity<String> requestEntity = new HttpEntity<String>(
				getHeaders("Digitador" + ":" + "Digitador"));

		for (Encounter encounter : encounterService.getAll()) {
			Integer encounterId = encounter.getEncounterId();
			String url = baseUrl+"ObsController/getNotasAclaratorias/{lesionadoId}";
			RestTemplate template = new RestTemplate();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("lesionadoId", encounterId);
			ResponseEntity<ListNotaAclaratoria> entity = template.exchange(url,
					HttpMethod.GET, requestEntity, ListNotaAclaratoria.class, map);
			ListNotaAclaratoria list = entity.getBody();
			Assert.assertNotNull(template);
			Assert.assertNotNull(entity);
			System.out.println(encounterId+"=>"+list);
			//Assert.assertNotNull(list);
		}
	}
	//@Test
	public void testSaveAntecedente() {
		Patient patient = patientService.buscarPatient(265);
		Integer lesionadoId = 100;
		Integer idTipoAntecedente = 29;
		AntecedenteSpringDto antecedente = new AntecedenteSpringDto();
		String tipoAntecedente = "Alergico";
		antecedente.setObservacion("Alergia al mani");
		antecedente.setTipoAntecedente(tipoAntecedente);
		antecedente.setPatient(new PatientSpringDto(patient));
		antecedente.setYear(2014);

		HttpEntity<AntecedenteSpringDto> requestEntity = new HttpEntity<AntecedenteSpringDto>(
				antecedente, getHeaders("Digitador" + ":" + "Digitador"));

		String url = baseUrl+"ObsController/saveAntecedente/{lesionadoId}/{userId}";
		RestTemplate template = new RestTemplate();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("lesionadoId", lesionadoId);
		map.put("userId", 37);
		ResponseEntity<AntecedenteSpringDto> entity = template.exchange(url,
				HttpMethod.POST, requestEntity, AntecedenteSpringDto.class, map);
		AntecedenteSpringDto list = entity.getBody();
		Assert.assertNotNull(template);
		Assert.assertNotNull(entity);
		Assert.assertNotNull(list);

	}
	//@Test
	public void testSaveProcedimientos() {
		Integer encounterId = 100;
		ProcedimientosSpringDto procedimientos = getProcedimientos(encounterId);
		procedimientos.setCanulaNasofaringea(true);
		procedimientos.setMovimientoBloque(true);
		procedimientos.setOtrosProcedimientos("Destruccion del tejido muscular");
		HttpEntity<ProcedimientosSpringDto> requestEntity = new HttpEntity<ProcedimientosSpringDto>(
				procedimientos, getHeaders("Digitador" + ":" + "Digitador"));

		String url = baseUrl+"ObsController/saveProcedimientos/{userId}";
		RestTemplate template = new RestTemplate();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("userId", 37);
		ResponseEntity<ProcedimientosSpringDto> entity = template.exchange(url,
				HttpMethod.POST, requestEntity, ProcedimientosSpringDto.class, map);
		ProcedimientosSpringDto list = entity.getBody();
		Assert.assertNotNull(template);
		Assert.assertNotNull(entity);
		Assert.assertNotNull(list);

	}
    @Test
	public void testSaveEvaluaciones() {
		Integer encounterId = 1231;
		EvaluacionCompletaSpringDto evaluacionCompleta = new EvaluacionCompletaSpringDto();
		EvaluacionSpringDto evaluacion = getEvaluacion(encounterId);
		Encounter encounter = encounterService.getEncounterById(encounterId);
		evaluacionCompleta.setEncounter(new EncounterSpringDto(encounter));
		evaluacion.setEvaluacion(evaluacionCompleta);
		System.out.println(evaluacion.getPermeavilidadViaAerea());
		evaluacion.setTriageAbdomenAgudo(true);
		evaluacion.setTriageAfectados(true);
		evaluacion.setTriageArritmias(true);
		evaluacion.setTipoEmergencia(EvaluacionSpringDto.OTRA_EMERGENCIA);
		evaluacion.setOtraEmergenciaMedica("Esta es otra emergencia");
		ProcedimientosSpringDto procedimientos = new ProcedimientosSpringDto();
		procedimientos.setOtrosProcedimientos("Otros procedimientos");
		procedimientos.setEvaluacion(evaluacionCompleta);
		evaluacion.setProcedimientos(procedimientos);
		HallazgoSpringDto hallazgo = new HallazgoSpringDto();
		hallazgo.setEvaluacion(evaluacionCompleta);
		hallazgo.setAmputacion(true);
		hallazgo.setContusion(true);
		hallazgo.setDolor(true);
		hallazgo.setHematoma(true);
		hallazgo.setHallazgoId(63953);
		hallazgo.setOrden(1);
		hallazgo.setX(200.0);
		hallazgo.setY(200.19);
		evaluacion.getHallazgos().add(hallazgo);
		evaluacion.setTriageOtro("Otro triage");
		HttpEntity<EvaluacionSpringDto> requestEntity = new HttpEntity<EvaluacionSpringDto>(
				evaluacion, getHeaders("Digitador" + ":" + "Digitador"));

		String url = baseUrl+"ObsController/saveEvaluacionHistorico/{userId}/{obsParentId}";
		RestTemplate template = new RestTemplate();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("userId", 37);
		map.put("obsParentId", null);
		ResponseEntity<EvaluacionSpringDto> entity = template.exchange(url,
				HttpMethod.POST, requestEntity, EvaluacionSpringDto.class, map);
		EvaluacionSpringDto list = entity.getBody();
		Assert.assertNotNull(template);
		Assert.assertNotNull(entity);
		Assert.assertNotNull(list);
	}
	//@Test
	public void testSaveSignosVitales()
	{
		Integer encounterId = 778;
		Encounter encounter = encounterService.getEncounterById(encounterId);
		SignosVitalesSpringDto signosVitales = new SignosVitalesSpringDto();
		signosVitales.setEncounter(new EncounterSpringDto(encounter));
		signosVitales.setFecha(new Date());
		signosVitales.setGlicemia(12.0);
		signosVitales.setPaDiastolica(12.0);
		signosVitales.setPaDiastolica(12.0);
		signosVitales.setPaSistolica(12.0);
		signosVitales.setPulso(12.0);
		signosVitales.setRespiracion(12.0);
		signosVitales.setSpo2(12.0);
		signosVitales.setTemperatura(12.1);
		HttpEntity<SignosVitalesSpringDto> requestEntity = new HttpEntity<SignosVitalesSpringDto>(
				signosVitales, getHeaders("Digitador" + ":" + "Digitador"));
		String url = baseUrl+"ObsController/saveSignosVitales/{userId}";
		RestTemplate template = new RestTemplate();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("userId", 37);
		ResponseEntity<SignosVitalesSpringDto> entity = template.exchange(url,
				HttpMethod.POST, requestEntity, SignosVitalesSpringDto.class, map);
		System.out.println(entity.getBody());
	}
	//@Test
	public void testSaveEvaluacion() {
		Integer encounterId = 100;
		EvaluacionSpringDto evaluacion = getEvaluacion(encounterId);
		
		HttpEntity<EvaluacionSpringDto> requestEntity = new HttpEntity<EvaluacionSpringDto>(
				evaluacion, getHeaders("Digitador" + ":" + "Digitador"));

		String url = baseUrl+"ObsController/saveEvaluacion/{userId}";
		RestTemplate template = new RestTemplate();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("userId", 37);
		ResponseEntity<EvaluacionSpringDto> entity = template.exchange(url,
				HttpMethod.POST, requestEntity, EvaluacionSpringDto.class, map);
		EvaluacionSpringDto list = entity.getBody();
		Assert.assertNotNull(template);
		Assert.assertNotNull(entity);
		Assert.assertNotNull(list);
	}
	//@Test
	public void testSaveSolicitudTeleasistencia() {
		TeleasistenciaSpringDto tele = getTeleasistencias(37).get(0);
		tele.setDetalles("Requerimiento de atencion especializada");
		HttpEntity<TeleasistenciaSpringDto> requestEntity = new HttpEntity<TeleasistenciaSpringDto>(tele,
				 getHeaders("Digitador" + ":" + "Digitador"));
		for (Encounter encounter : encounterService.getAll()) {
			Integer encounterId = encounter.getEncounterId();
			String url =baseUrl+"ObsController/saveSolicitudTeleasistencia/{encounterId}/{userId}";
			RestTemplate template = new RestTemplate();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("encounterId", encounterId);map.put("userId", 37);
			ResponseEntity<Integer> entity = template.exchange(url,
					HttpMethod.POST, requestEntity, Integer.class,
					map);
			Integer list = entity.getBody();
			Assert.assertNotNull(template);
			Assert.assertNotNull(entity);
			Assert.assertNotNull(list);
			System.out.println(encounterId+"=>"+list);
		}
	}
	//@Test
	public void testSaveNotaEvolucion() {
		TeleasistenciaSpringDto tele = getTeleasistencias(37).get(0);
		NotaEvolucionSpringDto nota = tele.getNotasEvolucion().get(0);
		nota.setRecomendaciones("Se recomienda darle una muerte rapida al paciente para terminar su sufrimiento");
		HttpEntity<NotaEvolucionSpringDto> requestEntity = new HttpEntity<NotaEvolucionSpringDto>(nota,
				 getHeaders("Digitador" + ":" + "Digitador"));
		for (Encounter encounter : encounterService.getAll()) {
			Integer encounterId = encounter.getEncounterId();
			String url = baseUrl+"ObsController/saveNotaEvolucion/{lesionadoId}/{teleasistenciaId}/{userId}";
			RestTemplate template = new RestTemplate();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("lesionadoId", encounterId);
			map.put("teleasistenciaId", tele.getTeleasistenciaId());
			map.put("userId", 37);
			ResponseEntity<Date> entity = template.exchange(url,
					HttpMethod.POST, requestEntity, Date.class,
					map);
			Date list = entity.getBody();
			Assert.assertNotNull(template);
			Assert.assertNotNull(entity);
			Assert.assertNotNull(list);
			System.out.println(encounterId+"=>"+list);
		}
	}
	
	//@Test
	public void testCreateEvaluacion() {
		HttpEntity<String> requestEntity = new HttpEntity<String>(
				 getHeaders("Digitador" + ":" + "Digitador"));
		//for (Encounter encounter : encounterService.getAll()) {
			Integer encounterId = 37;//encounter.getEncounterId();
			String url = baseUrl+"ObsController/createEvaluacion/{encounterId}/{userId}";
			RestTemplate template = new RestTemplate();

			//MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("encounterId", encounterId);
			map.put("userId", 35);
			ResponseEntity<String> entity = template.exchange(url,
					HttpMethod.POST, requestEntity, String.class, map);
			String list = entity.getBody().toString();
			System.out.println(list);
		
	}
	//@Test
	public void testSaveNotaAclaratoria() {
		HttpEntity<String> requestEntity = new HttpEntity<String>(
				 getHeaders("Digitador" + ":" + "Digitador"));
		for (Encounter encounter : encounterService.getAll()) {
			Integer encounterId = encounter.getEncounterId();
			String url = baseUrl+"ObsController/saveNotaAclaratoria/{lesionadoId}/{nota}/{userId}";
			RestTemplate template = new RestTemplate();

			//MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("lesionadoId", encounterId);
			map.put("nota", "Esto es una nota.");
			map.put("userId", 37);
			ResponseEntity<String> entity = template.exchange(url,
					HttpMethod.POST, requestEntity, String.class, map);
			String list = entity.getBody();
			Assert.assertNotNull(template);
			Assert.assertNotNull(entity);
			Assert.assertNotNull(list);
			System.out.println(encounterId+"=>"+list);
		}
	}
	//@Test
	public void testSaveProcedimientosHistorico()
	{
		ProcedimientosSpringDto procedimientos = new ProcedimientosSpringDto();
		procedimientos.setAccesoVenosoPeriferico(true);
		procedimientos.setAspiracionSecreciones(true);
		procedimientos.setColoides(true);
		procedimientos.setOtrosProcedimientos("Cambio en el procedimiento comprobado");
		Encounter encounter = encounterService.getEncounterById(37);
		HttpEntity<ProcedimientosSpringDto> requestEntity = new HttpEntity<ProcedimientosSpringDto>(procedimientos,
				 getHeaders("Digitador" + ":" + "Digitador"));
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("userId", 35);
		map.put("obsParentId", 3339);
		String url = baseUrl+"ObsController/saveProcedimientoHistorico/{userId}/{obsParentId}";
		RestTemplate template = new RestTemplate();
		ResponseEntity<ProcedimientosSpringDto> entity = template.exchange(url,
				HttpMethod.POST, requestEntity, ProcedimientosSpringDto.class, map);
		System.out.println(entity.getBody().getOtrosProcedimientos());
		/*Encounter encounter = encounterService.getEncounterById(37);
		Obs obsParent = obsService.getObs(3339);
		List<ObservationData> procedimientos = obsService
				.getProcedimientos(encounter, obsParent);
		System.out.println(procedimientos.size());*/
	}
	//@Test
	public void testSaveHallazgo()
	{
		HallazgoSpringDto hallazgo = new HallazgoSpringDto();
		Encounter encounter = fillEncounter(encounterService.getEncounterById(37));
			hallazgo.setOrden(1);
		hallazgo.setX(100.14);
		hallazgo.setY(270.35);
		HttpEntity<HallazgoSpringDto> requestEntity = new HttpEntity<HallazgoSpringDto>(hallazgo,
				 getHeaders("Digitador" + ":" + "Digitador"));
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("userId", 35);
		map.put("obsParentId", 3339);
		String url = baseUrl+"ObsController/saveHallazgoHistorico/{userId}/{obsParentId}";
		RestTemplate template = new RestTemplate();
		ResponseEntity<HallazgoSpringDto> entity = template.exchange(url,
				HttpMethod.POST, requestEntity, HallazgoSpringDto.class, map);
		System.out.println(entity.getBody());
	}
	
}
