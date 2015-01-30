package com.artica.telesalud.tph.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.artica.telesalud.common.exception.TelesaludException;
import com.artica.telesalud.common.service.DAOFactoryInstantiator;
import com.artica.telesalud.tph.concepts.ConceptsCode;
import com.artica.telesalud.tph.dao.ObsDAO;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
import com.artica.telesalud.tph.model.concept.Concept;
import com.artica.telesalud.tph.model.encounter.Encounter;
import com.artica.telesalud.tph.model.observation.Obs;
import com.artica.telesalud.tph.model.observation.ObservationData;
import com.artica.telesalud.tph.model.observation.SimpleObs;
import com.artica.telesalud.tph.model.patient.Patient;

public class ObsService {

	/**
	 * @return the obsDAO
	 */
	public ObsDAO getObsDAO() {
		return obsDAO;
	}

	/**
	 * @param obsDAO
	 *            the obsDAO to set
	 */
	public void setObsDAO(ObsDAO obsDAO) {
		this.obsDAO = obsDAO;
	}

	private ObsDAO obsDAO;

	public ObsService(String daoClassName, HashMap<String, String> params) {

		obsDAO = DAOFactoryInstantiator.instantiateDaoFactory(
				HibernateTPHDAOFactory.class, daoClassName, params).getObsDAO();

		DAOFactoryInstantiator.instantiateDaoFactory(
				HibernateTPHDAOFactory.class, daoClassName, params)
				.getConceptDAO();

		DAOFactoryInstantiator.instantiateDaoFactory(
				HibernateTPHDAOFactory.class, daoClassName, params)
				.getPatientDAO();
	}

	/**
	 * Metodo que permite obtener el registro de los antecedentes de un paciente
	 * en la historia clinica con informacion AMP especificada como argumento
	 * para el paciente,
	 * 
	 * @param patient
	 * @param conceptIdHistoriaAMP
	 * @return
	 */
	public Obs getAntecedenteAMP(Patient patient, Integer conceptIdHistoriaAMP) {
		return obsDAO.getObs(patient, conceptIdHistoriaAMP);
	}

	/**
	 * Metodo utilizado para obtener todos los antecedentes patologicos de un
	 * paciente almacenados en la base de datos.
	 * 
	 * @param patient
	 * @param classIdAntecedenteAMP
	 * @return
	 */
	public List<Obs> getAntecedentesAMP(Patient patient, Integer conceptIdXMLAMP) {
		List<Obs> obsList = obsDAO.getObsPatient(patient, conceptIdXMLAMP);
		return obsList;
	}

	public Obs getObsClinicHistory(Patient patient, String ehrUuid,
			Integer historyAMPConceptId) {
		return obsDAO
				.getObsClinicHistory(patient, ehrUuid, historyAMPConceptId);
	}

	/**
	 * Este método permite almacenar los antecedentes obtenidos a partir de la
	 * historia clínica de teleasistencia como un documento XML que contiene un
	 * CDA codificado en base 64. En caso de que ya exista un registro asociado
	 * al paciente que contenga el registro de los antecedentes de
	 * teleasistencia este es actualizado con la informacion suministrada por
	 * metodo.
	 * 
	 * @param documentoXML
	 *            documento XML con los antecedentes del paciente obtenidos a
	 *            partir de la historia clinica de teleasistencia.
	 * @param patient
	 *            el paciente para el cual se alamcena la información de la
	 *            historia clinica de teleasistencia.
	 * @param creator
	 *            usuario que crea la historia
	 * @param conceptIdAntecedenteAMP
	 *            Id del concepto asociado a la historia clínica en la base de
	 *            datos TPH.
	 */
	public void saveAntecedenteAmp(String documentoXML, Patient patient,
			Integer creator, String ehrUuid, Integer xmlConceptId,
			Integer historyAMPConceptId) {

		Date date = new Date();
		Obs clinicHistory = getObsClinicHistory(patient, ehrUuid,
				historyAMPConceptId);
		Obs updatedClinicHistory = null;
		if (clinicHistory == null) {
			updatedClinicHistory = obsDAO.newObs();
			updatedClinicHistory.setDateCreated(date);
			updatedClinicHistory.setCreator(creator);
			updatedClinicHistory.setPerson(patient.getPerson());
			updatedClinicHistory.setConcept(historyAMPConceptId);
		} else {
			updatedClinicHistory = clinicHistory;
		}
		updatedClinicHistory.setValueText(ehrUuid);
		updatedClinicHistory.setObsDatetime(date);
		if (clinicHistory == null) {
			obsDAO.insert(updatedClinicHistory);
		} else {
			obsDAO.update(updatedClinicHistory);
		}

		List<Obs> currentList = obsDAO.getListObs(patient,
				updatedClinicHistory, xmlConceptId);

		Obs current = null;
		if (currentList.size() > 0) {
			current = currentList.get(0);
		}

		Obs obs = null;
		if (current == null) {
			obs = obsDAO.newObs();
			obs.setDateCreated(date);
			obs.setCreator(creator);
			obs.setPerson(patient.getPerson());
			obs.setConcept(xmlConceptId);
		} else {
			obs = current;
		}
		obs.setObsGroup(updatedClinicHistory);
		obs.setValueText(documentoXML);
		obs.setObsDatetime(date);
		if (current == null) {
			obsDAO.insert(obs);
		} else {
			obsDAO.update(obs);
		}
	}

	public Obs newBlankObs() {
		return obsDAO.newObs();
	}

	public Obs save(Obs obs) {
		return obsDAO.insert(obs);
	}

	public Obs guardar(Encounter encuentro, Integer concepto, Object valor,
			Integer creator) {

		Obs obs = obsDAO.newObs();
		obs.setEncounter(encuentro);
		obs.setPerson(encuentro.getPatient().getPerson());
		obs.setConcept(concepto);
		obs.setObsDatetime(new Date());
		obs.setCreator(creator);

		if (valor instanceof Date)
			obs.setValueDatetime((Date) valor);
		else if (valor instanceof String)
			obs.setValueText((String) valor);
		if (valor instanceof Concept)
			obs.setValueCoded(((Concept) valor).getConceptId());
		if (valor instanceof Double)
			obs.setValueNumeric((Double) valor);

		return obsDAO.insert(obs);

	}

	public Obs update(Obs obs) {
		return obsDAO.update(obs);
	}

	public List<ObservationData> getObsByPatient(Patient patient,
			Integer concept) {

		List<ObservationData> obsData = new ArrayList<ObservationData>();

		try {
			List<Obs> observations = obsDAO
					.getObservations(patient, concept, 0);

			for (Obs obs : observations) {

				ObservationData oData = new ObservationData();
				oData.setConceptId(obs.getConcept());
				oData.setObsId(obs.getObsId());

				if (obs.getValueBoolean() != null)
					oData.setValueBoolean(obs.getValueBoolean());
				if (obs.getValueCoded() != null) {
					oData.setValueCoded(obs.getValueCoded());
				}
				if (obs.getValueDatetime() != null)
					oData.setValueDatetime(obs.getValueDatetime());
				if (obs.getValueNumeric() != null)
					oData.setValueNumeric(obs.getValueNumeric());
				if (obs.getValueText() != null)
					oData.setValueText(obs.getValueText());

				oData.setSons(getObsSun(obs));
				obsData.add(oData);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new TelesaludException("Exception!", e, ObsService.class);
		}
		return obsData;
	}

	public List<ObservationData> getObsByEncounter(Encounter encounter,
			Integer concept) {

		List<ObservationData> obsData = new ArrayList<ObservationData>();

		try {
			List<Obs> observations = obsDAO.getObservations(encounter, concept,
					0);

			for (Obs obs : observations) {

				ObservationData oData = new ObservationData();
				oData.setConceptId(obs.getConcept());
				oData.setObsId(obs.getObsId());

				if (obs.getValueBoolean() != null)
					oData.setValueBoolean(obs.getValueBoolean());
				if (obs.getValueCoded() != null) {
					oData.setValueCoded(obs.getValueCoded());
				}
				if (obs.getValueDatetime() != null)
					oData.setValueDatetime(obs.getValueDatetime());
				if (obs.getValueNumeric() != null)
					oData.setValueNumeric(obs.getValueNumeric());
				if (obs.getValueText() != null)
					oData.setValueText(obs.getValueText());
				oData.setDateCreated(obs.getDateCreated());
				oData.setCreator(obs.getCreator());
				oData.setSons(getObsSun(obs));
				obsData.add(oData);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new TelesaludException("Exception!", e, ObsService.class);
		}
		return obsData;
	}

	public List<ObservationData> getLastObsByEncounter(Encounter encounter,
			Integer concept) {

		List<ObservationData> obsData = new ArrayList<ObservationData>();

		try {
			List<Obs> observations = obsDAO.getObservations(encounter, concept,
					1);

			for (Obs obs : observations) {

				ObservationData oData = new ObservationData();
				oData.setConceptId(obs.getConcept());
				oData.setObsId(obs.getObsId());

				if (obs.getValueBoolean() != null)
					oData.setValueBoolean(obs.getValueBoolean());
				if (obs.getValueCoded() != null) {
					oData.setValueCoded(obs.getValueCoded());
				}
				if (obs.getValueDatetime() != null)
					oData.setValueDatetime(obs.getValueDatetime());
				if (obs.getValueNumeric() != null)
					oData.setValueNumeric(obs.getValueNumeric());
				if (obs.getValueText() != null)
					oData.setValueText(obs.getValueText());

				oData.setSons(getObsSun(obs));
				obsData.add(oData);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new TelesaludException("Exception!", e, ObsService.class);
		}
		return obsData;
	}

	public List<ObservationData> getObsByEncounter(Encounter encounter) {

		List<ObservationData> obsData = new ArrayList<ObservationData>();

		try {
			List<Obs> observations = obsDAO.getEncounterObservations(encounter);

			for (Obs obs : observations) {

				ObservationData oData = new ObservationData();
				oData.setConceptId(obs.getConcept());
				oData.setObsId(obs.getObsId());

				if (obs.getValueBoolean() != null)
					oData.setValueBoolean(obs.getValueBoolean());
				if (obs.getValueCoded() != null) {
					oData.setValueCoded(obs.getValueCoded());
				}
				if (obs.getValueDatetime() != null)
					oData.setValueDatetime(obs.getValueDatetime());
				if (obs.getValueNumeric() != null)
					oData.setValueNumeric(obs.getValueNumeric());
				if (obs.getValueText() != null)
					oData.setValueText(obs.getValueText());

				oData.setSons(getObsSun(obs));
				obsData.add(oData);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new TelesaludException("Exception!", e, ObsService.class);
		}
		return obsData;
	}

	public List<ObservationData> getObsSun(Obs obsPpal) {

		List<ObservationData> obsData = new ArrayList<ObservationData>();

		try {

			List<Obs> observations = obsDAO.getSonObservations(obsPpal);
			for (Obs obs : observations) {

				ObservationData oData = new ObservationData();
				oData.setConceptId(obs.getConcept());
				oData.setObsId(obs.getObsId());

				if (obs.getValueBoolean() != null)
					oData.setValueBoolean(obs.getValueBoolean());
				if (obs.getValueCoded() != null) {
					oData.setValueCoded(obs.getValueCoded());

					if (obs.getValueCoded() != null) {
					}
				}
				if (obs.getValueDatetime() != null)
					oData.setValueDatetime(obs.getValueDatetime());
				if (obs.getValueNumeric() != null)
					oData.setValueNumeric(obs.getValueNumeric());
				if (obs.getValueText() != null)
					oData.setValueText(obs.getValueText());

				oData.setSons(getObsSun(obs));
				obsData.add(oData);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new TelesaludException("Exception!", e, ObsService.class);
		}
		return obsData;
	}

	public List<ObservationData> obtenerAntecedentes(Patient patient) {
		ConceptsCode concepts = new ConceptsCode();

		return getObsByPatient(patient, concepts.cAntecedentes());
	}

	public Obs guardarAntecedente(Encounter encuentro, Integer anio,
			Integer tipoAntecedente, String observacion, Date date,
			Integer creator, Obs obsParent) {

		ConceptsCode concepts = new ConceptsCode();
		if (obsParent == null) {
			obsParent = obsDAO.newObs();
			obsParent.setEncounter(encuentro);
			obsParent.setPerson(encuentro.getPatient().getPerson());
			obsParent.setConcept(concepts.cAntecedentes());
			obsParent.setObsDatetime(new Date());
			obsParent.setCreator(creator);
			obsParent = obsDAO.insert(obsParent);
		}

		// Va a guardar el año del antecedente
		boolean newObs = false;
		Obs obsAnio = obsDAO.getObs(encuentro, obsParent,
				concepts.cAnioAntecedente());
		newObs = obsAnio == null;
		if (newObs) {
			obsAnio = obsDAO.newObs();
		}
		obsAnio.setEncounter(encuentro);
		obsAnio.setPerson(encuentro.getPatient().getPerson());
		obsAnio.setConcept(concepts.cAnioAntecedente());
		obsAnio.setObsDatetime(new Date());
		obsAnio.setCreator(creator);
		obsAnio.setObsGroup(obsParent);
		if (anio != null) {
			obsAnio.setValueNumeric((double) anio);
		}
		if (newObs) {
			obsDAO.insert(obsAnio);
		} else {
			obsDAO.update(obsAnio);
		}
		Obs obsDate = obsDAO.getObs(encuentro, obsParent,
				concepts.cFechaAntecedente());
		newObs = obsDate == null;
		if (newObs) {
			obsDate = obsDAO.newObs();
		}
		obsDate.setValueDatetime(date);
		obsDate.setEncounter(encuentro);
		obsDate.setPerson(encuentro.getPatient().getPerson());
		obsDate.setConcept(concepts.cFechaAntecedente());
		obsDate.setObsDatetime(new Date());
		obsDate.setCreator(creator);
		obsDate.setObsGroup(obsParent);
		if (newObs) {
			obsDAO.insert(obsDate);
		} else {
			obsDAO.update(obsDate);
		}
		Obs obsTipo = obsDAO.getObs(encuentro, obsParent,
				concepts.cTipoAntecedente());
		newObs = obsTipo == null;
		if (newObs) {
			obsTipo = obsDAO.newObs();
		}
		obsTipo.setEncounter(encuentro);
		obsTipo.setPerson(encuentro.getPatient().getPerson());
		obsTipo.setConcept(concepts.cTipoAntecedente());
		obsTipo.setObsDatetime(new Date());
		obsTipo.setCreator(creator);
		obsTipo.setObsGroup(obsParent);
		obsTipo.setValueCoded(tipoAntecedente);
		if (newObs) {
			obsDAO.insert(obsTipo);
		} else {
			obsDAO.update(obsTipo);
		}

		Obs obsObservacion = obsDAO.getObs(encuentro, obsParent,
				concepts.cObservacionAntecedente());
		newObs = obsObservacion == null;
		if (newObs) {
			obsObservacion = obsDAO.newObs();
		}
		obsObservacion.setEncounter(encuentro);
		obsObservacion.setPerson(encuentro.getPatient().getPerson());
		obsObservacion.setConcept(concepts.cObservacionAntecedente());
		obsObservacion.setObsDatetime(new Date());
		obsObservacion.setCreator(creator);
		obsObservacion.setObsGroup(obsParent);
		obsObservacion.setValueText(observacion);
		if (newObs) {
			obsDAO.insert(obsObservacion);
		} else {
			obsDAO.update(obsObservacion);
		}
		return obsParent;
	}

	public void guardarArchivo(Encounter encuentro, String originalName,
			String name, String contentType, Integer fileId, Integer creator) {

		Obs obsParent, obsResult;
		ConceptsCode concepts = new ConceptsCode();
		if (fileId == null) {

			obsResult = obsDAO.getObs(encuentro, concepts.cArchivosAdjuntos());
			if (obsResult == null) {

				obsParent = obsDAO.newObs();
				obsParent.setEncounter(encuentro);
				obsParent.setPerson(encuentro.getPatient().getPerson());
				obsParent.setConcept(concepts.cArchivosAdjuntos());
				obsParent.setObsDatetime(new Date());
				obsParent.setCreator(creator);

				obsParent = obsDAO.insert(obsParent);

				// Va a guardar el año del antecedente
				Obs obsTP = obsDAO.newObs();
				obsTP.setEncounter(encuentro);
				obsTP.setPerson(encuentro.getPatient().getPerson());
				obsTP.setConcept(concepts.cTipoContenidoArchivos());
				obsTP.setObsDatetime(new Date());
				obsTP.setCreator(creator);
				obsTP.setObsGroup(obsParent);
				obsTP.setValueText(contentType);
				obsDAO.insert(obsTP);

				Obs obsON = obsDAO.newObs();
				obsON.setEncounter(encuentro);
				obsON.setPerson(encuentro.getPatient().getPerson());
				obsON.setConcept(concepts.cNombreOriginalArchivo());
				obsON.setObsDatetime(new Date());
				obsON.setCreator(creator);
				obsON.setObsGroup(obsParent);
				obsON.setValueText(originalName);
				obsDAO.insert(obsON);

				Obs obsN = obsDAO.newObs();
				obsN.setEncounter(encuentro);
				obsN.setPerson(encuentro.getPatient().getPerson());
				obsN.setConcept(concepts.cNombreGuardadoArchivo());
				obsN.setObsDatetime(new Date());
				obsN.setCreator(creator);
				obsN.setObsGroup(obsParent);
				obsN.setValueText(name);
				obsDAO.insert(obsN);
			}
		}

	}

	public void guardarProcedimientos(Encounter encuentro,
			List<SimpleObs> procedimientos, Integer creator) {

		ConceptsCode concepts = new ConceptsCode();
		Obs obsParent = null, obsResult = null;

		obsResult = obsDAO.getObs(encuentro, concepts.cProcedimientos());
		if (obsResult != null)
			obsParent = obsResult;
		else {
			obsParent = obsDAO.newObs();
			obsParent.setEncounter(encuentro);
			obsParent.setPerson(encuentro.getPatient().getPerson());
			obsParent.setConcept(concepts.cProcedimientos());
			obsParent.setObsDatetime(new Date());
			obsParent.setCreator(creator);
			obsParent = obsDAO.insert(obsParent);
		}

		for (SimpleObs obs : procedimientos) {

			obsResult = obsDAO.getObs(encuentro, obsParent, obs.getConcept());

			if (obsResult == null) {
				Obs obsObservacion = obsDAO.newObs();
				obsObservacion.setEncounter(encuentro);
				obsObservacion.setPerson(encuentro.getPatient().getPerson());
				obsObservacion.setConcept(obs.getConcept());
				obsObservacion.setObsDatetime(new Date());
				obsObservacion.setCreator(creator);
				obsObservacion.setObsGroup(obsParent);

				if (obs.getValue() instanceof String)
					obsObservacion.setValueText((String) obs.getValue());
				else if (obs.getValue() instanceof Boolean)
					obsObservacion.setValueBoolean((((Boolean) obs.getValue() == true) ? 1 : 0));

				obsDAO.insert(obsObservacion);
			} else {
				if (obs.getValue() instanceof String) {

					if (obsResult.getValueText() == null || !obsResult.getValueText().equals(
							(String) obs.getValue())) {
						obsResult.setValueText((String) obs.getValue());
						obsDAO.update(obsResult);
					}

				} else if (obs.getValue() instanceof Boolean&&obsResult.getValueBoolean() != null) {

					if (obsResult.getValueBoolean() == null
							|| obsResult.getValueBoolean() != (((Boolean) obs.getValue() == true) ? 1 : 0)) {
						obsResult.setValueBoolean(((Boolean) obs.getValue() == true) ? 1 : 0);
						obsDAO.update(obsResult);
					}
				}
			}
		}
	}

	public Obs getNewEvaluacionPaciente(Encounter encuentro, Integer creator,
			Date dateCreated) {
		ConceptsCode concepts = new ConceptsCode();
		Obs obsParent = null;
		List<Obs> obs = obsDAO.getObsByDate(encuentro.getPatient(), concepts.cEvaluacionLesionado(), dateCreated);
		if(obs.isEmpty())
		{
		obsParent = obsDAO.newObs();
		obsParent.setEncounter(encuentro);
		obsParent.setPerson(encuentro.getPatient().getPerson());
		obsParent.setConcept(concepts.cEvaluacionLesionado());
		obsParent.setDateCreated(dateCreated);
		obsParent.setObsDatetime(dateCreated);
		obsParent.setCreator(creator);
		obsParent = obsDAO.insert(obsParent);
		}
		else
		{
			obsParent = obs.get(0);
		}
		return obsParent;
	}

	public Obs guardarProcedimientosHistorico(Encounter encuentro,
			List<SimpleObs> procedimientos, Integer creator,
			Obs obsEvaluacionParent) {

		ConceptsCode concepts = new ConceptsCode();
		Obs obsParent = null, obsResult = null;
		obsResult = obsDAO.getObs(encuentro, obsEvaluacionParent,
				concepts.cProcedimientos());
		if (obsResult != null)
			obsParent = obsResult;
		else {
			obsParent = obsDAO.newObs();
			obsParent.setEncounter(encuentro);
			obsParent.setPerson(encuentro.getPatient().getPerson());
			obsParent.setConcept(concepts.cProcedimientos());
			obsParent.setObsDatetime(new Date());
			obsParent.setCreator(creator);
			obsParent.setObsGroup(obsEvaluacionParent);
			obsParent = obsDAO.insert(obsParent);
		}

		for (SimpleObs obs : procedimientos) {

			obsResult = obsDAO.getObs(encuentro, obsParent, obs.getConcept());

			if (obsResult == null) {
				Obs obsObservacion = obsDAO.newObs();
				obsObservacion.setEncounter(encuentro);
				obsObservacion.setPerson(encuentro.getPatient().getPerson());
				obsObservacion.setConcept(obs.getConcept());
				obsObservacion.setObsDatetime(new Date());
				obsObservacion.setCreator(creator);
				obsObservacion.setObsGroup(obsParent);

				if (obs.getValue() instanceof String)
					obsObservacion.setValueText((String) obs.getValue());
				else if (obs.getValue() instanceof Boolean)
					obsObservacion.setValueBoolean((((Boolean) obs.getValue() == true) ? 1 : 0));

				obsDAO.insert(obsObservacion);
			} else {
				if (obs.getValue() instanceof String) {

					if (!obsResult.getValueText().equals(
							(String) obs.getValue())) {
						obsResult.setValueText((String) obs.getValue());
						obsDAO.update(obsResult);
					}

				} else if (obs.getValue() instanceof Boolean) {

					if (obsResult.getValueBoolean() == null
							|| !obsResult.getValueBoolean().equals(
									(Integer) obs.getValue())) {
						obsResult.setValueBoolean((((Boolean) obs.getValue() == true) ? 1 : 0));
						obsDAO.update(obsResult);
					}
				}
			}
		}
		return obsParent;
	}

	public Obs updateEvaluacionHistorico(Encounter encuentro,
			List<SimpleObs> datosEvaluacion, Integer creator, Date dateCreated,
			Obs obsParent) {
		return obsDAO.update(guardarEvaluacionHistorico(encuentro,
				datosEvaluacion, creator, dateCreated, obsParent));

	}

	public Obs guardarEvaluacionHistorico(Encounter encuentro,
			List<SimpleObs> datosEvaluacion, Integer creator, Date dateCreated,
			Obs obsParent) {
		if (obsParent == null) {/*
			obsParent = getNewEvaluacionPaciente(encuentro, creator,
					dateCreated);*/
		}
		for (SimpleObs obs : datosEvaluacion) {

			List<Obs> obsList = obsDAO.getListObs(encuentro, obsParent, obs.getConcept());
			Obs obsResult = null;
			if(!obsList.isEmpty())
			{
				obsResult = obsList.get(0);
			}

			if (obsResult == null) {

				Obs obsObservacion = obsDAO.newObs();
				obsObservacion.setEncounter(encuentro);
				obsObservacion.setPerson(encuentro.getPatient().getPerson());
				obsObservacion.setConcept(obs.getConcept());
				obsObservacion.setObsDatetime(dateCreated);
				obsObservacion.setCreator(creator);
				obsObservacion.setObsGroup(obsParent);

				if (obs.getValue() instanceof String)
					obsObservacion.setValueText((String) obs.getValue());
				else if (obs.getValue() instanceof Boolean)
					obsObservacion.setValueBoolean((((Boolean) obs.getValue() == true) ? 1 : 0));
				else if (obs.getValue() instanceof Integer)
					obsObservacion.setValueCoded((Integer) obs.getValue());
				else if (obs.getValue() instanceof Double)
					obsObservacion.setValueNumeric((Double) obs.getValue());

				obsDAO.insert(obsObservacion);
			} else {
				if (obs.getValue() instanceof String) {

					if (obsResult.getValueText() == null
							|| !obsResult.getValueText().equals(
									(String) obs.getValue())) {
						obsResult.setValueText((String) obs.getValue());
						obsDAO.update(obsResult);
					}

				} else if (obs.getValue() instanceof Integer) {

					if (obsResult.getValueCoded() == null
							|| !obsResult.getValueCoded().equals(
									(Integer) obs.getValue())) {
						obsResult.setValueCoded((Integer) obs.getValue());
						obsDAO.update(obsResult);
					}
				} else if (obs.getValue() instanceof Boolean) {

					if (obsResult.getValueCoded() == null
							|| !obsResult.getValueCoded()
									.equals(obs.getValue())) {
						obsResult.setValueBoolean((((Boolean) obs.getValue() == true) ? 1 : 0));
						obsDAO.update(obsResult);
					}
				} else if (obs.getValue() instanceof Double) {

					if (obsResult.getValueNumeric() == null
							|| !obsResult.getValueNumeric().equals(
									(Double) obs.getValue())) {
						obsResult.setValueNumeric((Double) obs.getValue());
						obsDAO.update(obsResult);
					}
				}
			}
		}
		return obsParent;
	}

	public Obs saveProcedimientoHistorico(Encounter encuentro,
			List<SimpleObs> datosHallazgo, Integer creator,
			Obs obsEvaluacionParent) {
		ConceptsCode concepts = new ConceptsCode();
		Obs obsParent = null;
		obsParent = obsDAO.getObs(encuentro, obsEvaluacionParent,
				concepts.cProcedimientos());
		if (obsParent == null) {
			obsParent = obsDAO.newObs();
			obsParent.setEncounter(encuentro);
			obsParent.setPerson(encuentro.getPatient().getPerson());
			obsParent.setConcept(concepts.cProcedimientos());
			obsParent.setObsDatetime(new Date());
			obsParent.setCreator(creator);
			obsParent.setObsGroup(obsEvaluacionParent);
			obsParent = obsDAO.insert(obsParent);
		}
		for (SimpleObs obs : datosHallazgo) {
			Obs obsResult = obsDAO.getObs(encuentro, obsParent,
					obs.getConcept());

			if (obsResult == null) {

				Obs obsObservacion = obsDAO.newObs();
				obsObservacion.setEncounter(encuentro);
				obsObservacion.setPerson(encuentro.getPatient().getPerson());
				obsObservacion.setConcept(obs.getConcept());
				obsObservacion.setObsDatetime(new Date());
				obsObservacion.setCreator(creator);
				obsObservacion.setObsGroup(obsParent);

				if (obs.getValue() instanceof String)
					obsObservacion.setValueText((String) obs.getValue());
				else if (obs.getValue() instanceof Boolean)
					obsObservacion.setValueBoolean((((Boolean) obs.getValue() == true) ? 1 : 0));
				else if (obs.getValue() instanceof Integer)
					obsObservacion.setValueCoded((Integer) obs.getValue());
				else if (obs.getValue() instanceof Double)
					obsObservacion.setValueNumeric((Double) obs.getValue());

				obsDAO.insert(obsObservacion);
			} else {
				if (obs.getValue() instanceof String) {

					if (obsResult.getValueText() == null
							|| !obsResult.getValueText().equals(
									(String) obs.getValue())) {
						obsResult.setValueText((String) obs.getValue());
						obsDAO.update(obsResult);
					}

				} else if (obs.getValue() instanceof Integer) {

					if (obsResult.getValueCoded() == null
							|| !obsResult.getValueCoded().equals(
									(Integer) obs.getValue())) {
						obsResult.setValueCoded((Integer) obs.getValue());
						obsDAO.update(obsResult);
					}
				} else if (obs.getValue() instanceof Boolean) {

					if (obsResult.getValueCoded() == null
							|| !obsResult.getValueCoded()
									.equals(obs.getValue())) {
						obsResult.setValueBoolean((((Boolean) obs.getValue() == true) ? 1 : 0));
						obsDAO.update(obsResult);
					}
				} else if (obs.getValue() instanceof Double) {

					if (obsResult.getValueNumeric() == null
							|| !obsResult.getValueNumeric().equals(
									(Double) obs.getValue())) {
						obsResult.setValueNumeric((Double) obs.getValue());
						obsDAO.update(obsResult);
					}
				}
			}
		}
		return obsEvaluacionParent;
	}

	public Obs getHallazgo(Double order, Encounter encuentro, Obs obsEvaluacionParent) {
		List<ObservationData> observations = getHallazgos(encuentro, obsEvaluacionParent);
		for (ObservationData obsParent : observations) {
			ConceptsCode concepts = new ConceptsCode();
			Obs obs = obsDAO.getObs(obsParent.getObsId());
			Obs current = obsDAO.getObs(encuentro, obs,
					concepts.cHallazgoOrden());
			if (current != null && current.getValueNumeric() != null
					&& current.getValueNumeric().equals(order)) {
				return obs;
			}
		}
		return null;
	}

	public Obs saveInsumo(Encounter encuentro, List<SimpleObs> datosInsumo,
			Integer creator, Obs obsEvaluacionParent, Integer obsId) {
		ConceptsCode concepts = new ConceptsCode();
		Obs obsParent = null;
		if (obsId == null) {
			obsParent = obsDAO.newObs();
			obsParent.setEncounter(encuentro);
			obsParent.setPerson(encuentro.getPatient().getPerson());
			obsParent.setConcept(concepts.cInformacionInsumoSuministrado());
			obsParent.setObsDatetime(new Date());
			obsParent.setCreator(creator);
			obsParent.setObsGroup(obsEvaluacionParent);
			obsParent = obsDAO.insert(obsParent);
		} else {
			obsParent = obsDAO.getObs(obsId);
		}
		for (SimpleObs obs : datosInsumo) {
			Obs obsResult = obsDAO.getObs(encuentro, obsParent,
					obs.getConcept());

			if (obsResult == null) {

				Obs obsObservacion = obsDAO.newObs();
				obsObservacion.setEncounter(encuentro);
				obsObservacion.setPerson(encuentro.getPatient().getPerson());
				obsObservacion.setConcept(obs.getConcept());
				obsObservacion.setObsDatetime(new Date());
				obsObservacion.setCreator(creator);
				obsObservacion.setObsGroup(obsParent);

				if (obs.getValue() instanceof String)
					obsObservacion.setValueText((String) obs.getValue());
				else if (obs.getValue() instanceof Boolean)
					obsObservacion.setValueBoolean((((Boolean) obs.getValue() == true) ? 1 : 0));
				else if (obs.getValue() instanceof Integer)
					obsObservacion.setValueCoded((Integer) obs.getValue());
				else if (obs.getValue() instanceof Double)
					obsObservacion.setValueNumeric((Double) obs.getValue());

				obsDAO.insert(obsObservacion);
			} else {
				if (obs.getValue() instanceof String) {

					if (obsResult.getValueText() == null
							|| !obsResult.getValueText().equals(
									(String) obs.getValue())) {
						obsResult.setValueText((String) obs.getValue());
						obsDAO.update(obsResult);
					}

				} else if (obs.getValue() instanceof Integer) {

					if (obsResult.getValueCoded() == null
							|| !obsResult.getValueCoded().equals(
									(Integer) obs.getValue())) {
						obsResult.setValueCoded((Integer) obs.getValue());
						obsDAO.update(obsResult);
					}
				} else if (obs.getValue() instanceof Boolean) {

					if (obsResult.getValueCoded() == null
							|| !obsResult.getValueCoded()
									.equals(obs.getValue())) {
						obsResult.setValueBoolean((((Boolean) obs.getValue() == true) ? 1 : 0));
						obsDAO.update(obsResult);
					}
				} else if (obs.getValue() instanceof Double) {

					if (obsResult.getValueNumeric() == null
							|| !obsResult.getValueNumeric().equals(
									(Double) obs.getValue())) {
						obsResult.setValueNumeric((Double) obs.getValue());
						obsDAO.update(obsResult);
					}
				}
			}
		}
		return obsParent;
	}

	public Obs saveHallazgoHistorico(Encounter encuentro,
			List<SimpleObs> datosHallazgo, Integer creator,
			Obs obsEvaluacionParent, Integer hallazgoId) {

		ConceptsCode concepts = new ConceptsCode();
		Double order = null;
		for (SimpleObs obs : datosHallazgo) {
			if (obs.getConcept().equals(concepts.cHallazgoOrden())) {
				order = (Double) obs.getValue();
			}
		}
		Obs obsParent = getHallazgo(order, encuentro, obsEvaluacionParent);
		if (hallazgoId == null && obsParent == null) {
			obsParent = obsDAO.newObs();
			obsParent.setEncounter(encuentro);
			obsParent.setPerson(encuentro.getPatient().getPerson());
			obsParent.setConcept(concepts.cHallazgo());
			obsParent.setObsDatetime(new Date());
			obsParent.setCreator(creator);
			obsParent.setObsGroup(obsEvaluacionParent);
			obsParent = obsDAO.insert(obsParent);
		} else if (hallazgoId != null) {
			obsParent = obsDAO.getObs(hallazgoId);
		}
		for (SimpleObs obs : datosHallazgo) {
			Obs obsResult = obsDAO.getObs(encuentro, obsParent,
					obs.getConcept());

			if (obsResult == null) {

				Obs obsObservacion = obsDAO.newObs();
				obsObservacion.setEncounter(encuentro);
				obsObservacion.setPerson(encuentro.getPatient().getPerson());
				obsObservacion.setConcept(obs.getConcept());
				obsObservacion.setObsDatetime(new Date());
				obsObservacion.setCreator(creator);
				obsObservacion.setObsGroup(obsParent);

				if (obs.getValue() instanceof String)
					obsObservacion.setValueText((String) obs.getValue());
				else if (obs.getValue() instanceof Boolean)
					obsObservacion.setValueBoolean((((Boolean) obs.getValue() == true) ? 1 : 0));
				else if (obs.getValue() instanceof Integer)
					obsObservacion.setValueCoded((Integer) obs.getValue());
				else if (obs.getValue() instanceof Double)
					obsObservacion.setValueNumeric((Double) obs.getValue());

				obsDAO.insert(obsObservacion);
			} else {
				if (obs.getValue() instanceof String) {

					if (obsResult.getValueText() == null
							|| !obsResult.getValueText().equals(
									(String) obs.getValue())) {
						obsResult.setValueText((String) obs.getValue());
						obsDAO.update(obsResult);
					}

				} else if (obs.getValue() instanceof Integer) {

					if (obsResult.getValueCoded() == null
							|| !obsResult.getValueCoded().equals(
									(Integer) obs.getValue())) {
						obsResult.setValueCoded((Integer) obs.getValue());
						obsDAO.update(obsResult);
					}
				} else if (obs.getValue() instanceof Boolean) {

					if (obsResult.getValueCoded() == null
							|| !obsResult.getValueCoded()
									.equals(obs.getValue())) {
						obsResult.setValueBoolean((((Boolean) obs.getValue() == true) ? 1 : 0));
						obsDAO.update(obsResult);
					}
				} else if (obs.getValue() instanceof Double) {

					if (obsResult.getValueNumeric() == null
							|| !obsResult.getValueNumeric().equals(
									(Double) obs.getValue())) {
						obsResult.setValueNumeric((Double) obs.getValue());
						obsDAO.update(obsResult);
					}
				}
			}
		}
		return obsParent;
	}

	public void guardarEvaluacion(Encounter encuentro,
			List<SimpleObs> datosEvaluacion, Integer creator) {

		ConceptsCode concepts = new ConceptsCode();
		Obs obsParent = null, obsResult = null;

		obsResult = obsDAO.getObs(encuentro, concepts.cEvaluacionLesionado());
		if (obsResult != null)
			obsParent = obsResult;
		else {
			obsParent = obsDAO.newObs();

			obsParent.setEncounter(encuentro);
			obsParent.setPerson(encuentro.getPatient().getPerson());
			obsParent.setConcept(concepts.cEvaluacionLesionado());
			obsParent.setObsDatetime(new Date());
			obsParent.setCreator(creator);

			obsParent = obsDAO.insert(obsParent);
		}

		for (SimpleObs obs : datosEvaluacion) {

			obsResult = obsDAO.getObs(encuentro, obsParent, obs.getConcept());

			if (obsResult == null) {

				Obs obsObservacion = obsDAO.newObs();
				obsObservacion.setEncounter(encuentro);
				obsObservacion.setPerson(encuentro.getPatient().getPerson());
				obsObservacion.setConcept(obs.getConcept());
				obsObservacion.setObsDatetime(new Date());
				obsObservacion.setCreator(creator);
				obsObservacion.setObsGroup(obsParent);

				if (obs.getValue() instanceof String)
					obsObservacion.setValueText((String) obs.getValue());
				else if (obs.getValue() instanceof Boolean)
					obsObservacion.setValueBoolean((((Boolean) obs.getValue() == true) ? 1 : 0));
				else if (obs.getValue() instanceof Integer)
					obsObservacion.setValueCoded((Integer) obs.getValue());

				obsDAO.insert(obsObservacion);
			} else {
				if (obs.getValue() instanceof String) {

					if (obsResult.getValueText() == null
							|| !obsResult.getValueText().equals(
									(String) obs.getValue())) {
						obsResult.setValueText((String) obs.getValue());
						obsDAO.update(obsResult);
					}

				} else if (obs.getValue() instanceof Integer) {

					if (obsResult.getValueCoded() == null
							|| !obsResult.getValueCoded().equals(
									(Integer) obs.getValue())) {
						obsResult.setValueCoded((Integer) obs.getValue());
						obsDAO.update(obsResult);
					}
				} else if (obs.getValue() instanceof Boolean) {

					if (obsResult.getValueCoded() == null
							|| !obsResult.getValueCoded()
									.equals(obs.getValue())) {
						obsResult.setValueBoolean((((Boolean) obs.getValue() == true) ? 1 : 0));
						obsDAO.update(obsResult);
					}
				} else if (obs.getValue() instanceof Double) {

					if (obsResult.getValueNumeric() == null
							|| !obsResult.getValueNumeric().equals(
									(Double) obs.getValue())) {
						obsResult.setValueNumeric((Double) obs.getValue());
						obsDAO.update(obsResult);
					}
				}
			}
		}
	}

	public void guardarHallazgo(Encounter encuentro,
			List<SimpleObs> datosEvaluacion, Integer creator, Integer hallazgoId) {

		ConceptsCode concepts = new ConceptsCode();
		Obs obsParent = null;
		List<Obs> listaObsResult = new ArrayList<Obs>();
		if (hallazgoId == null) {
			obsParent = obsDAO.newObs();

			obsParent.setEncounter(encuentro);
			obsParent.setPerson(encuentro.getPatient().getPerson());
			obsParent.setConcept(concepts.cHallazgo());
			obsParent.setObsDatetime(new Date());
			obsParent.setCreator(creator);

			obsParent = obsDAO.insert(obsParent);
		} else {
			obsParent = obsDAO.getObs(hallazgoId);
		}
		for (SimpleObs obs : datosEvaluacion) {

			listaObsResult = obsDAO.getListObs(encuentro, obsParent,
					obs.getConcept());

			if (listaObsResult.size() == 0) {
				Obs obsObservacion = obsDAO.newObs();
				obsObservacion.setEncounter(encuentro);
				obsObservacion.setPerson(encuentro.getPatient().getPerson());
				obsObservacion.setConcept(obs.getConcept());
				obsObservacion.setObsDatetime(new Date());
				obsObservacion.setCreator(creator);
				obsObservacion.setObsGroup(obsParent);

				if (obs.getValue() instanceof String)
					obsObservacion.setValueText((String) obs.getValue());
				else if (obs.getValue() instanceof Boolean)
					obsObservacion.setValueBoolean((((Boolean) obs.getValue() == true) ? 1 : 0));
				else if (obs.getValue() instanceof Double)
					obsObservacion.setValueNumeric(Double.valueOf(obs
							.getValue().toString()));
				else if (obs.getValue() instanceof Integer)
					obsObservacion.setValueCoded((Integer) obs.getValue());

				obsDAO.insert(obsObservacion);
			} else {
				boolean esNuevo = true;
				for (Obs observation : listaObsResult) {

					if (observation.getConcept().equals(
							concepts.cProcedimientoHallazgo())) {
						if (observation.getValueCoded() != null
								&& observation.getValueCoded().equals(
										obs.getValue())) {
							esNuevo = false;
						}
					} else
						esNuevo = false;
				}

				if (esNuevo) {
					Obs obsObservacion = obsDAO.newObs();
					obsObservacion.setEncounter(encuentro);
					obsObservacion
							.setPerson(encuentro.getPatient().getPerson());
					obsObservacion.setConcept(obs.getConcept());
					obsObservacion.setObsDatetime(new Date());
					obsObservacion.setCreator(creator);
					obsObservacion.setObsGroup(obsParent);

					obsObservacion.setValueCoded((Integer) obs.getValue());

					obsDAO.insert(obsObservacion);
				}
			}
		}
	}

	public Obs guardarSolicitudTeleasistencia(Encounter encuentro, Date fecha,
			String motivo, String detalle, Integer medio, Integer creator,
			Obs obsParent) {

		ConceptsCode concepts = new ConceptsCode();
		if (obsParent == null) {
			obsParent = obsDAO.newObs();
			obsParent.setEncounter(encuentro);
			obsParent.setPerson(encuentro.getPatient().getPerson());
			obsParent.setConcept(concepts.cSolicitudTeleasistencia());
			obsParent.setObsDatetime(new Date());
			obsParent.setCreator(creator);
			obsParent = obsDAO.insert(obsParent);
		}

		// Va a guardar el usuario que realiza la solicitud
		Obs obsUsuario = obsDAO.getObs(encuentro, obsParent,
				concepts.cUsuarioSolicitaTeleasistencia());
		boolean newObs = obsUsuario == null;
		if (newObs) {
			obsUsuario = obsDAO.newObs();
		}
		obsUsuario.setEncounter(encuentro);
		obsUsuario.setPerson(encuentro.getPatient().getPerson());
		obsUsuario.setConcept(concepts.cUsuarioSolicitaTeleasistencia());
		obsUsuario.setObsDatetime(new Date());
		obsUsuario.setCreator(creator);
		obsUsuario.setObsGroup(obsParent);
		obsUsuario.setValueText(creator.toString());
		if (newObs) {
			obsDAO.insert(obsUsuario);
		} else {
			obsDAO.update(obsUsuario);
		}

		// Va a guardar la fecha de la solicitud
		Obs obsFecha = obsDAO.getObs(encuentro, obsParent,
				concepts.cFechaSolicitudTeleasistencia());
		newObs = obsFecha == null;
		if (newObs) {
			obsFecha = obsDAO.newObs();
		}
		obsFecha.setEncounter(encuentro);
		obsFecha.setPerson(encuentro.getPatient().getPerson());
		obsFecha.setConcept(concepts.cFechaSolicitudTeleasistencia());
		obsFecha.setObsDatetime(new Date());
		obsFecha.setCreator(creator);
		obsFecha.setObsGroup(obsParent);
		obsFecha.setValueDatetime(fecha);
		if (newObs) {
			obsDAO.insert(obsFecha);
		} else {
			obsDAO.update(obsFecha);
		}

		// Va a guardar el motivo

		Obs obsMotivo = obsDAO.getObs(encuentro, obsParent,
				concepts.cMotivoSolicitudTeleasistencia());
		newObs = obsMotivo == null;
		if (newObs) {
			obsMotivo = obsDAO.newObs();
		}
		obsMotivo.setEncounter(encuentro);
		obsMotivo.setPerson(encuentro.getPatient().getPerson());
		obsMotivo.setConcept(concepts.cMotivoSolicitudTeleasistencia());
		obsMotivo.setObsDatetime(new Date());
		obsMotivo.setCreator(creator);
		obsMotivo.setObsGroup(obsParent);
		obsMotivo.setValueText(motivo);
		if (newObs) {
			obsDAO.insert(obsMotivo);
		} else {
			obsDAO.update(obsMotivo);
		}

		// Va a guardar el detalle
		Obs obsDetalle = obsDAO.getObs(encuentro, obsParent,
				concepts.cDetalleSolicitudTeleasistencia());
		newObs = obsDetalle == null;
		if (newObs) {
			obsDetalle = obsDAO.newObs();
		}
		obsDetalle.setEncounter(encuentro);
		obsDetalle.setPerson(encuentro.getPatient().getPerson());
		obsDetalle.setConcept(concepts.cDetalleSolicitudTeleasistencia());
		obsDetalle.setObsDatetime(new Date());
		obsDetalle.setCreator(creator);
		obsDetalle.setObsGroup(obsParent);
		obsDetalle.setValueText(detalle);
		if (newObs) {
			obsDAO.insert(obsDetalle);
		} else {
			obsDAO.update(obsDetalle);
		}
		// Va a guardar el medio
		Obs obsMedio = obsDAO.getObs(encuentro, obsParent,
				concepts.cMedioSolicitudTeleasistencia());
		newObs = obsMedio == null;
		if (newObs) {
			obsMedio = obsDAO.newObs();
		}
		obsMedio.setEncounter(encuentro);
		obsMedio.setPerson(encuentro.getPatient().getPerson());
		obsMedio.setConcept(concepts.cMedioSolicitudTeleasistencia());
		obsMedio.setObsDatetime(new Date());
		obsMedio.setCreator(creator);
		obsMedio.setObsGroup(obsParent);
		obsMedio.setValueCoded(medio);
		if (newObs) {
			obsDAO.insert(obsMedio);
		} else {
			obsDAO.update(obsMedio);
		}
		return obsParent;
	}

	public Obs guardarSignosVitales(Encounter encuentro, Double respiracion,
			Double paSistolica, Double paDiastolica, Double glicemia,
			Double pulso, Double temperatura, Double spo2, Date date,
			Integer creator, Obs obsParent) {

		ConceptsCode concepts = new ConceptsCode();
		if (obsParent == null) {
			obsParent = obsDAO.newObs();
			obsParent.setEncounter(encuentro);
			obsParent.setPerson(encuentro.getPatient().getPerson());
			obsParent.setConcept(concepts.CSignosVitales());
			obsParent.setObsDatetime(new Date());
			obsParent.setCreator(creator);
			obsParent = obsDAO.insert(obsParent);
		}

		// Va a guardar respiracion
		boolean newObs = false;
		if (respiracion != null) {
			Obs obsRespiracion = obsDAO.getObs(encuentro, obsParent,
					concepts.cRespiracion());
			newObs = obsRespiracion == null;
			if (obsRespiracion == null) {
				obsRespiracion = obsDAO.newObs();
			}
			obsRespiracion.setEncounter(encuentro);
			obsRespiracion.setPerson(encuentro.getPatient().getPerson());
			obsRespiracion.setConcept(concepts.cRespiracion());
			obsRespiracion.setObsDatetime(new Date());
			obsRespiracion.setCreator(creator);
			obsRespiracion.setObsGroup(obsParent);
			obsRespiracion.setValueNumeric(respiracion);
			if (newObs) {
				obsDAO.insert(obsRespiracion);
			} else {
				obsDAO.update(obsRespiracion);
			}
		}

		// Va a guardar paSistolica
		if (paSistolica != null) {
			Obs obsSistolica = obsDAO.getObs(encuentro, obsParent,
					concepts.cPresionArterialSistolica());
			newObs = obsSistolica == null;
			if (obsSistolica == null) {
				obsSistolica = obsDAO.newObs();
			}
			obsSistolica.setEncounter(encuentro);
			obsSistolica.setPerson(encuentro.getPatient().getPerson());
			obsSistolica.setConcept(concepts.cPresionArterialSistolica());
			obsSistolica.setObsDatetime(new Date());
			obsSistolica.setCreator(creator);
			obsSistolica.setObsGroup(obsParent);
			obsSistolica.setValueNumeric(paSistolica);
			if (newObs) {
				obsDAO.insert(obsSistolica);
			} else {
				obsDAO.update(obsSistolica);
			}
		}
		// Va a guardar paDiastolica
		if (paDiastolica != null) {
			Obs obsDiastolica = obsDAO.getObs(encuentro, obsParent,
					concepts.cPresionArterialDiastolica());
			newObs = obsDiastolica == null;
			if (obsDiastolica == null) {
				obsDiastolica = obsDAO.newObs();
			}
			obsDiastolica.setEncounter(encuentro);
			obsDiastolica.setPerson(encuentro.getPatient().getPerson());
			obsDiastolica.setConcept(concepts.cPresionArterialDiastolica());
			obsDiastolica.setObsDatetime(new Date());
			obsDiastolica.setCreator(creator);
			obsDiastolica.setObsGroup(obsParent);
			obsDiastolica.setValueNumeric(paDiastolica);
			if (newObs) {
				obsDAO.insert(obsDiastolica);
			} else {
				obsDAO.update(obsDiastolica);
			}
		}
		// Va a guardar glicemia
		if (glicemia != null) {
			Obs obsGlicemia = obsDAO.getObs(encuentro, obsParent,
					concepts.cGlicemia());
			newObs = obsGlicemia == null;
			if (obsGlicemia == null) {
				obsGlicemia = obsDAO.newObs();
				newObs = true;
			}
			obsGlicemia.setEncounter(encuentro);
			obsGlicemia.setPerson(encuentro.getPatient().getPerson());
			obsGlicemia.setConcept(concepts.cGlicemia());
			obsGlicemia.setObsDatetime(new Date());
			obsGlicemia.setCreator(creator);
			obsGlicemia.setObsGroup(obsParent);
			obsGlicemia.setValueNumeric(glicemia);
			if (newObs) {
				obsDAO.insert(obsGlicemia);
			} else {
				obsDAO.update(obsGlicemia);
			}
		}

		// Va a guardar pulso
		if (pulso != null) {
			Obs obsPulso = obsDAO.getObs(encuentro, obsParent,
					concepts.cFrecuenciaPulso());
			newObs = obsPulso == null;
			if (obsPulso == null) {
				obsPulso = obsDAO.newObs();
			}
			obsPulso.setEncounter(encuentro);
			obsPulso.setPerson(encuentro.getPatient().getPerson());
			obsPulso.setConcept(concepts.cFrecuenciaPulso());
			obsPulso.setObsDatetime(new Date());
			obsPulso.setCreator(creator);
			obsPulso.setObsGroup(obsParent);
			obsPulso.setValueNumeric(pulso);
			if (newObs) {
				obsDAO.insert(obsPulso);
			} else {
				obsDAO.update(obsPulso);
			}
		}
		// Va a guardar temp
		if (temperatura != null) {
			Obs obsTemp = obsDAO.getObs(encuentro, obsParent,
					concepts.cTemperatura());
			newObs = obsTemp == null;
			if (obsTemp == null) {
				obsTemp = obsDAO.newObs();
			}
			obsTemp.setEncounter(encuentro);
			obsTemp.setPerson(encuentro.getPatient().getPerson());
			obsTemp.setConcept(concepts.cTemperatura());
			obsTemp.setObsDatetime(new Date());
			obsTemp.setCreator(creator);
			obsTemp.setObsGroup(obsParent);
			obsTemp.setValueNumeric(temperatura);
			if (newObs) {
				obsDAO.insert(obsTemp);
			} else {
				obsDAO.update(obsTemp);
			}
		}
		// Va a guardar spo
		if (spo2 != null) {
			Obs obsSPO = obsDAO.getObs(encuentro, obsParent, concepts.cSPO2());
			newObs = obsSPO == null;
			if (obsSPO == null) {
				obsSPO = obsDAO.newObs();
			}
			obsSPO.setEncounter(encuentro);
			obsSPO.setPerson(encuentro.getPatient().getPerson());
			obsSPO.setConcept(concepts.cSPO2());
			obsSPO.setObsDatetime(new Date());
			obsSPO.setCreator(creator);
			obsSPO.setObsGroup(obsParent);
			obsSPO.setValueNumeric(spo2);
			if (newObs) {
				obsDAO.insert(obsSPO);
			} else {
				obsDAO.update(obsSPO);
			}
		}

		// Va a guardar la fecha
		if (date != null) {
			Obs obsFecha = obsDAO.getObs(encuentro, obsParent,
					concepts.cFechaSignoVital());
			newObs = obsFecha == null;
			if (obsFecha == null) {
				obsFecha = obsDAO.newObs();
			}
			obsFecha.setEncounter(encuentro);
			obsFecha.setPerson(encuentro.getPatient().getPerson());
			obsFecha.setConcept(concepts.cFechaSignoVital());
			obsFecha.setObsDatetime(new Date());
			obsFecha.setCreator(creator);
			obsFecha.setObsGroup(obsParent);
			obsFecha.setValueDatetime(date);
			if (newObs) {
				obsDAO.insert(obsFecha);
			} else {
				obsDAO.update(obsFecha);
			}
		}
		return obsParent;
	}

	public Obs guardarDatosCierreAtencion(Encounter encuentro,
			Integer tipoCierre, String nota, Integer creator, Obs obsParent) {

		ConceptsCode concepts = new ConceptsCode();
		if(obsParent == null)
		{
		obsParent = obsDAO.newObs();
		obsParent.setEncounter(encuentro);
		obsParent.setPerson(encuentro.getPatient().getPerson());
		obsParent.setConcept(concepts.cCierreAtencionLesionado());
		obsParent.setObsDatetime(new Date());
		obsParent.setCreator(creator);

		obsParent = obsDAO.insert(obsParent);
		}

		// Va a guardar tipo cierre
		Obs obsTipo = 
				obsDAO.getObs(encuentro, obsParent,
						concepts.cTipoCierreAtencion());
		if(obsTipo == null)
		{
		obsTipo =	obsDAO.newObs();
		obsTipo.setEncounter(encuentro);
		obsTipo.setPerson(encuentro.getPatient().getPerson());
		obsTipo.setConcept(concepts.cTipoCierreAtencion());
		obsTipo.setObsDatetime(new Date());
		obsTipo.setCreator(creator);
		obsTipo.setObsGroup(obsParent);
		obsTipo.setValueCoded(tipoCierre);
		obsDAO.insert(obsTipo);
		}
		// Va a guardar el usuario que cierra la atención
		Obs obsUsuario = 
				obsDAO.getObs(encuentro, obsParent,
						concepts.cUsuarioCierraAtencion());
		if(obsUsuario == null)
		{
		obsUsuario=		obsDAO.newObs();
		obsUsuario.setEncounter(encuentro);
		obsUsuario.setPerson(encuentro.getPatient().getPerson());
		obsUsuario.setConcept(concepts.cUsuarioCierraAtencion());
		obsUsuario.setObsDatetime(new Date());
		obsUsuario.setCreator(creator);
		obsUsuario.setObsGroup(obsParent);
		obsUsuario.setValueText(creator.toString());
		obsDAO.insert(obsUsuario);
		}

		// Va a guardar nota cierre
		Obs obsNota = 
				obsDAO.getObs(encuentro, obsParent,
						concepts.cNotaAclaratoriaCierre());
		if(obsNota == null)
		{
			obsNota =	obsDAO.newObs();
		obsNota.setEncounter(encuentro);
		obsNota.setPerson(encuentro.getPatient().getPerson());
		obsNota.setConcept(concepts.cNotaAclaratoriaCierre());
		obsNota.setObsDatetime(new Date());
		obsNota.setCreator(creator);
		obsNota.setObsGroup(obsParent);
		obsNota.setValueText(nota);
		obsDAO.insert(obsNota);
		}
		return obsParent;
	}

	public Obs guardarNotaAclaratoria(Encounter encuentro, Integer usuario,
			String texto, Integer creator, Obs obsParent) {

		ConceptsCode concepts = new ConceptsCode();
		if(obsParent == null)
		{
			obsParent = obsDAO.newObs();
			obsParent.setEncounter(encuentro);
			obsParent.setPerson(encuentro.getPatient().getPerson());
			obsParent.setConcept(concepts.cNotaAclaratoria());
			obsParent.setObsDatetime(new Date());
			obsParent.setCreator(creator);
			obsParent = obsDAO.insert(obsParent);
		}

		// Va a guardar usuario
		Obs obsUsuario = 
				obsDAO.getObs(encuentro, obsParent,
						concepts.cUsuarioNotaAclaratoria());
		if(obsUsuario == null)
		{
			obsUsuario = obsDAO.newObs();
			obsUsuario.setEncounter(encuentro);
			obsUsuario.setPerson(encuentro.getPatient().getPerson());
			obsUsuario.setConcept(concepts.cUsuarioNotaAclaratoria());
			obsUsuario.setObsDatetime(new Date());
			obsUsuario.setCreator(creator);
			obsUsuario.setObsGroup(obsParent);
			obsUsuario.setValueText(String.valueOf(usuario));
			obsDAO.insert(obsUsuario);
		}

		// Va a guardar texto nota
		Obs obsTexto = 
				obsDAO.getObs(encuentro, obsParent,
						concepts.cTextoNotaAclaratoria());
		if(obsTexto == null)
		{
		obsTexto = obsDAO.newObs();
		obsTexto.setEncounter(encuentro);
		obsTexto.setPerson(encuentro.getPatient().getPerson());
		obsTexto.setConcept(concepts.cTextoNotaAclaratoria());
		obsTexto.setObsDatetime(new Date());
		obsTexto.setCreator(creator);
		obsTexto.setObsGroup(obsParent);
		obsTexto.setValueText(texto);
		obsDAO.insert(obsTexto);
		}

		// Va a guardar texto nota
		Obs obsFecha =
				obsDAO.getObs(encuentro, obsParent,
						concepts.cFechaNotaAclaratoria());
		if(obsFecha == null)
		{
		obsFecha = obsDAO.newObs();
		obsFecha.setEncounter(encuentro);
		obsFecha.setPerson(encuentro.getPatient().getPerson());
		obsFecha.setConcept(concepts.cFechaNotaAclaratoria());
		obsFecha.setObsDatetime(new Date());
		obsFecha.setCreator(creator);
		obsFecha.setObsGroup(obsParent);
		obsFecha.setValueDatetime(new Date());
		obsDAO.insert(obsFecha);
		}
		return obsParent;
	}

	public Obs guardarNotaEvolucion(Encounter encuentro,
			Integer idTeleasistencia, Date fecha, Integer dxPrincipal,
			List<Integer> dxSecundarios, String recomendacion,
			Integer medicoTratante, Integer creator, String nombreMedico, Obs obsParent) {

		Obs obsPpal;
		ConceptsCode concepts = new ConceptsCode();
		obsPpal = obsDAO.getObs(idTeleasistencia);
		if (obsParent == null) {
			obsParent = obsDAO.newObs();
			obsParent.setEncounter(encuentro);
			obsParent.setPerson(encuentro.getPatient().getPerson());
			obsParent.setConcept(concepts.cNotaEvolucion());
			obsParent.setObsDatetime(new Date());
			obsParent.setObsGroup(obsPpal);
			obsParent.setCreator(creator);
			obsParent = obsDAO.insert(obsParent);
		}
		// Va a guardar la fecha

		Obs obsFecha = obsDAO.getObs(encuentro, obsParent,
				concepts.cFechaNotaEvolucion());
		boolean newObs = obsFecha == null;
		if (fecha != null) {
			if (newObs) {
				obsFecha = obsDAO.newObs();
			}
			obsFecha.setEncounter(encuentro);
			obsFecha.setPerson(encuentro.getPatient().getPerson());
			obsFecha.setConcept(concepts.cFechaNotaEvolucion());
			obsFecha.setObsDatetime(new Date());
			obsFecha.setCreator(creator);
			obsFecha.setObsGroup(obsParent);
			obsFecha.setValueDatetime(fecha);
			if (newObs) {
				obsDAO.insert(obsFecha);
			} else {
				obsDAO.update(obsFecha);
			}
		}
		// Va a guardar la recomendacion
		if (recomendacion != null) {
			Obs obsRecomendacion = obsDAO.getObs(encuentro, obsParent,
					concepts.cRecomendacionesNotaEvolucion());
			newObs = obsRecomendacion == null;
			if (newObs) {
				obsRecomendacion = obsDAO.newObs();
			}
			obsRecomendacion.setEncounter(encuentro);
			obsRecomendacion.setPerson(encuentro.getPatient().getPerson());
			obsRecomendacion.setConcept(concepts
					.cRecomendacionesNotaEvolucion());
			obsRecomendacion.setObsDatetime(new Date());
			obsRecomendacion.setCreator(creator);
			obsRecomendacion.setObsGroup(obsParent);
			obsRecomendacion.setValueText(recomendacion);
			if (newObs) {
				obsDAO.insert(obsRecomendacion);
			} else {
				obsDAO.update(obsRecomendacion);
			}
		}
		// Va a guardar el medico tratante
		if (medicoTratante != null) {
			Obs obsMedico = obsDAO.getObs(encuentro, obsParent,
					concepts.cMedicoTratanteNotaEvolucion());
			newObs = obsMedico == null;
			if (newObs) {
				obsMedico = obsDAO.newObs();
			}

			obsMedico.setEncounter(encuentro);
			obsMedico.setPerson(encuentro.getPatient().getPerson());
			obsMedico.setConcept(concepts.cMedicoTratanteNotaEvolucion());
			obsMedico.setObsDatetime(new Date());
			obsMedico.setCreator(creator);
			obsMedico.setObsGroup(obsParent);
			obsMedico.setValueText(String.valueOf(medicoTratante));
			if (newObs) {
				obsDAO.insert(obsMedico);
			} else {
				obsDAO.update(obsMedico);
			}
		}
		if (nombreMedico != null) {
			Obs obsNombreMedico = obsDAO.getObs(encuentro, obsParent,
					concepts.cNombreMedicoTeleasistencia());
			newObs = obsNombreMedico == null;
			if (newObs) {
				obsNombreMedico = obsDAO.newObs();
			}

			obsNombreMedico.setEncounter(encuentro);
			obsNombreMedico.setPerson(encuentro.getPatient().getPerson());
			obsNombreMedico.setConcept(concepts.cNombreMedicoTeleasistencia());
			obsNombreMedico.setObsDatetime(new Date());
			obsNombreMedico.setCreator(creator);
			obsNombreMedico.setObsGroup(obsParent);
			obsNombreMedico.setValueText(nombreMedico);
			if (newObs) {
				obsDAO.insert(obsNombreMedico);
			} else {
				obsDAO.update(obsNombreMedico);
			}
		}
		// Va a guardar el Dx principal
		if (dxPrincipal != null) {
			Obs obsMedio = obsDAO.getObs(encuentro, obsParent,
					concepts.cDiagnosticoPrincipalNotaEvolucion());
			newObs = obsMedio == null;
			if (newObs) {
				obsMedio = obsDAO.newObs();
			}
			obsMedio.setEncounter(encuentro);
			obsMedio.setPerson(encuentro.getPatient().getPerson());
			obsMedio.setConcept(concepts.cDiagnosticoPrincipalNotaEvolucion());
			obsMedio.setObsDatetime(new Date());
			obsMedio.setCreator(creator);
			obsMedio.setObsGroup(obsParent);
			obsMedio.setValueCoded(dxPrincipal);
			if (newObs) {
				obsDAO.insert(obsMedio);
			} else {
				obsDAO.update(obsMedio);
			}
		}
		List<Obs> diagnosticos = obsDAO.getObsList(encuentro, obsParent,
				concepts.cDiagnosticoSecundarioNotaEvolucion());
		if (diagnosticos == null || diagnosticos.isEmpty()) {
			// Va a guardar los Dx secundarios
			for (Integer dx : dxSecundarios) {
				Obs obsDx = obsDAO.newObs();
				obsDx.setEncounter(encuentro);
				obsDx.setPerson(encuentro.getPatient().getPerson());
				obsDx.setConcept(concepts.cDiagnosticoSecundarioNotaEvolucion());
				obsDx.setObsDatetime(new Date());
				obsDx.setCreator(creator);
				obsDx.setObsGroup(obsParent);
				obsDx.setValueCoded(dx);
				obsDAO.insert(obsDx);
			}
		}
		return obsParent;

	}

	public List<ObservationData> getProcedimientos(Encounter encounter,
			Obs obsParent) {
		ConceptsCode concepts = new ConceptsCode();
		List<ObservationData> obsData = new ArrayList<ObservationData>();
		List<Obs> list = obsDAO.getObsList(encounter, obsParent,
				concepts.cProcedimientos());
		for (Obs obs : list) {

			ObservationData oData = new ObservationData();
			oData.setConceptId(obs.getConcept());
			oData.setObsId(obs.getObsId());

			if (obs.getValueBoolean() != null)
				oData.setValueBoolean(obs.getValueBoolean());
			if (obs.getValueCoded() != null) {
				oData.setValueCoded(obs.getValueCoded());
			}
			if (obs.getValueDatetime() != null)
				oData.setValueDatetime(obs.getValueDatetime());
			if (obs.getValueNumeric() != null)
				oData.setValueNumeric(obs.getValueNumeric());
			if (obs.getValueText() != null)
				oData.setValueText(obs.getValueText());
			oData.setDateCreated(obs.getDateCreated());
			oData.setCreator(obs.getCreator());
			oData.setSons(getObsSun(obs));
			obsData.add(oData);
		}
		return obsData;
	}

	public ObservationData obtenerProcedimientos(Encounter encounter) {
		ConceptsCode concepts = new ConceptsCode();

		List<ObservationData> obss = getObsByEncounter(encounter,
				concepts.cProcedimientos());

		if (obss.size() > 0)
			return obss.get(0);

		ObservationData obsData = new ObservationData();
		obsData.setSons(new ArrayList<ObservationData>());

		return obsData;
	}

	public List<ObservationData> getEvaluaciones(Encounter encounter) {
		ConceptsCode concepts = new ConceptsCode();
		List<ObservationData> obss = getObsByEncounter(encounter,
				concepts.cEvaluacionLesionado());
		return obss;
	}

	public ObservationData obtenerEvaluacion(Encounter encounter) {
		ConceptsCode concepts = new ConceptsCode();

		List<ObservationData> obss = getObsByEncounter(encounter,
				concepts.cEvaluacionLesionado());

		if (obss.size() > 0)
			return obss.get(0);

		ObservationData obsData = new ObservationData();
		obsData.setSons(new ArrayList<ObservationData>());

		return obsData;
	}

	public List<ObservationData> getInsumos(Encounter encounter, Obs obsParent) {
		ConceptsCode concepts = new ConceptsCode();
		List<ObservationData> obsData = new ArrayList<ObservationData>();
		List<Obs> list = obsDAO.getObsList(encounter, obsParent,
				concepts.cInformacionInsumoSuministrado());
		for (Obs obs : list) {

			ObservationData oData = new ObservationData();
			oData.setConceptId(obs.getConcept());
			oData.setObsId(obs.getObsId());

			if (obs.getValueBoolean() != null)
				oData.setValueBoolean(obs.getValueBoolean());
			if (obs.getValueCoded() != null) {
				oData.setValueCoded(obs.getValueCoded());
			}
			if (obs.getValueDatetime() != null)
				oData.setValueDatetime(obs.getValueDatetime());
			if (obs.getValueNumeric() != null)
				oData.setValueNumeric(obs.getValueNumeric());
			if (obs.getValueText() != null)
				oData.setValueText(obs.getValueText());
			oData.setDateCreated(obs.getDateCreated());
			oData.setCreator(obs.getCreator());
			oData.setSons(getObsSun(obs));
			obsData.add(oData);
		}
		return obsData;
	}

	public List<ObservationData> getHallazgos(Encounter encounter, Obs obsParent) {
		ConceptsCode concepts = new ConceptsCode();
		List<ObservationData> obsData = new ArrayList<ObservationData>();
		List<Obs> list = obsDAO.getObsList(encounter, obsParent,
				concepts.cHallazgo());
		for (Obs obs : list) {

			ObservationData oData = new ObservationData();
			oData.setConceptId(obs.getConcept());
			oData.setObsId(obs.getObsId());

			if (obs.getValueBoolean() != null)
				oData.setValueBoolean(obs.getValueBoolean());
			if (obs.getValueCoded() != null) {
				oData.setValueCoded(obs.getValueCoded());
			}
			if (obs.getValueDatetime() != null)
				oData.setValueDatetime(obs.getValueDatetime());
			if (obs.getValueNumeric() != null)
				oData.setValueNumeric(obs.getValueNumeric());
			if (obs.getValueText() != null)
				oData.setValueText(obs.getValueText());
			oData.setDateCreated(obs.getDateCreated());
			oData.setCreator(obs.getCreator());
			oData.setSons(getObsSun(obs));
			obsData.add(oData);
		}
		return obsData;
	}

	public List<ObservationData> obtenerHallazgos(Encounter encounter) {
		ConceptsCode concepts = new ConceptsCode();

		List<ObservationData> obss = getObsByEncounter(encounter,
				concepts.cHallazgo());

		return obss;
	}

	public List<ObservationData> obtenerArchivos(Encounter encounter) {
		ConceptsCode concepts = new ConceptsCode();

		List<ObservationData> obss = getObsByEncounter(encounter,
				concepts.cArchivosAdjuntos());

		return obss;
	}

	public List<ObservationData> obtenerSolicitudesTeleasistencia(
			Encounter encounter) {
		ConceptsCode concepts = new ConceptsCode();

		List<ObservationData> obss = getObsByEncounter(encounter,
				concepts.cSolicitudTeleasistencia());

		return obss;
	}

	public List<ObservationData> obtenerSignosVitales(Encounter encounter) {
		ConceptsCode concepts = new ConceptsCode();

		List<ObservationData> obss = getObsByEncounter(encounter,
				concepts.CSignosVitales());

		return obss;
	}

	public List<ObservationData> obtenerNotasAclaratorias(Encounter encounter) {
		ConceptsCode concepts = new ConceptsCode();

		List<ObservationData> obss = getObsByEncounter(encounter,
				concepts.cNotaAclaratoria());

		return obss;
	}

	public ObservationData obtenerDatosCierre(Encounter encounter) {
		ConceptsCode concepts = new ConceptsCode();

		List<ObservationData> obss = getObsByEncounter(encounter,
				concepts.cCierreAtencionLesionado());

		if (obss.size() > 0)
			return obss.get(0);

		ObservationData obsData = new ObservationData();
		obsData.setSons(new ArrayList<ObservationData>());

		return obsData;
	}

	public ObservationData obtenerUltimosSignosVitales(Encounter encounter) {
		ConceptsCode concepts = new ConceptsCode();

		List<ObservationData> obss = getLastObsByEncounter(encounter,
				concepts.CSignosVitales());

		if (obss.size() > 0)
			return obss.get(0);

		ObservationData obsData = new ObservationData();
		obsData.setSons(new ArrayList<ObservationData>());

		return obsData;
	}

	public Obs getObs(Integer obsId) {
		return obsDAO.getObs(obsId);
	}

	public Obs obtenerObservacionPorConcepto(Encounter encounter,
			Integer conceptId) {

		return obsDAO.getObs(encounter, conceptId);
	}

}
