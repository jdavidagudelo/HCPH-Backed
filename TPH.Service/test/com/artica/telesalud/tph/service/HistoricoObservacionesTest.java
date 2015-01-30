package com.artica.telesalud.tph.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.artica.telesalud.common.data.HibernateSessionFactoryManager;
import com.artica.telesalud.tph.concepts.ConceptsCode;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
import com.artica.telesalud.tph.model.encounter.Encounter;
import com.artica.telesalud.tph.model.observation.Obs;
import com.artica.telesalud.tph.model.observation.ObservationData;
import com.artica.telesalud.tph.model.observation.SimpleObs;

public class HistoricoObservacionesTest {
	EncounterService encounterService;
	ObsService obsService;
	@Before
	public void init(){
		
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(HibernateTPHDAOFactory.TPH_HIBERNATE_CONFIG_FILE, "hibernate-tph-test-service.cfg.xml");
		
		HibernateSessionFactoryManager manager = HibernateSessionFactoryManager.getInstance();
		manager.createFactory("hibernate-tph-test-service.cfg.xml");
		
		encounterService = new EncounterService("com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory", params);
		obsService = new ObsService("com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory", params);
	}
	//@Test
	public void testCreateEvaluacion()
	{
		Encounter encuentro = encounterService.getEncounterById(37);
		obsService.getNewEvaluacionPaciente(encuentro, 35, new Date());
		
	}
	//@Test
	public void testCreateEvaluaciones()
	{
		ConceptsCode codes = new ConceptsCode();
		Encounter encuentro = encounterService.getEncounterById(37);
		Obs obs = obsService.getObs(29478);
		List<SimpleObs> list = new ArrayList<SimpleObs>();
		SimpleObs s = new SimpleObs(codes.cEvaluacionB(), "Este es el valor");
		list.add(s);
		obsService.guardarEvaluacionHistorico(encuentro, list, 35, new Date(), obs);
	}
	//@Test
	public void testCreateHallazgo()
	{
		ConceptsCode codes = new ConceptsCode();
		Encounter encuentro = encounterService.getEncounterById(37);
		Obs obs = obsService.getObs(29478);
		List<SimpleObs> list = new ArrayList<SimpleObs>();
		SimpleObs s = new SimpleObs(codes.cHallazgoPosicionX(), 11);
		list.add(s);
		s = new SimpleObs(codes.cHallazgoPosicionY(), 13);
		list.add(s);
		s = new SimpleObs(codes.cHallazgoOrden(), 1);
		list.add(s);
		obsService.saveHallazgoHistorico(encuentro, list, 35, obs, null);
		
	}
	//@Test
	public void testCreateProcedimiento()
	{
		ConceptsCode codes = new ConceptsCode();
		Encounter encuentro = encounterService.getEncounterById(37);
		Obs obs = obsService.getObs(29478);
		List<SimpleObs> list = new ArrayList<SimpleObs>();
		SimpleObs s = new SimpleObs(codes
				.cAccesoVenosoPeriferico(), 11);
		list.add(s);
		obsService.guardarProcedimientosHistorico(encuentro, list, 35, obs);
	}
	@Test
	public void testGetEvaluaciones()
	{
		Encounter encuentro = encounterService.getEncounterById(37);
		List<ObservationData> obs = obsService.getEvaluaciones(encuentro);
		System.out.println(obs.size());
		for(ObservationData o : obs)
		{
			List<ObservationData> list = o.getSons();
			if(o.getObsId() > 5000)
			{
			for(ObservationData x : list)
			{
				System.out.println(x.getObsId()+" :: "+x.getConceptId());
				for(ObservationData xx : x.getSons())
				{
					System.out.println(xx.getObsId()+" >> "+xx.getConceptId());
				}
			}
			System.out.println();
			}
		}
	}
}
