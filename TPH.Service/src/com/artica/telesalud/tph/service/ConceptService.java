package com.artica.telesalud.tph.service;

import java.util.HashMap;
import java.util.List;

import com.artica.telesalud.common.service.DAOFactoryInstantiator;
import com.artica.telesalud.tph.concepts.ConceptsCode;
import com.artica.telesalud.tph.dao.ConceptDAO;
import com.artica.telesalud.tph.dao.ConceptSetDAO;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
import com.artica.telesalud.tph.model.concept.Concept;
import com.artica.telesalud.tph.model.concept.ConceptSet;

public class ConceptService {
	
	ConceptDAO conceptDAO;
	ConceptSetDAO conceptSetDAO;
	
	public ConceptService(String daoClassName, HashMap<String, String> params){
			
			conceptDAO = DAOFactoryInstantiator.instantiateDaoFactory(HibernateTPHDAOFactory.class, 
					daoClassName, params).getConceptDAO();
			conceptSetDAO = DAOFactoryInstantiator.instantiateDaoFactory(HibernateTPHDAOFactory.class, 
					daoClassName, params).getConceptSetDAO();
	}
	
	public Concept obtenerConcepto(Integer concepto){
		return conceptDAO.getConcept(concepto);
	}
	
	public List<Concept> getSetConcepts(Integer parentConcept){
		return conceptDAO.getSet(parentConcept);
	}
	public List<Concept> getTiposCierre(){
		ConceptsCode codes = new ConceptsCode();
		
		return conceptDAO.getSet(codes.cListaTiposCierre());
	}
	public List<Concept> getInsumos(){
		ConceptsCode codes = new ConceptsCode();
		
		return conceptDAO.getSet(codes.cListaInsumos());
	}
	public List<Concept> getViasAdministracion(){
		ConceptsCode codes = new ConceptsCode();
		
		return conceptDAO.getSet(codes.cListaViasAdministracion());
	}
	public List<Concept> getUnidadesVolumen(){
		ConceptsCode codes = new ConceptsCode();
		
		return conceptDAO.getSet(codes.cListaUnidadesVolumen());
	}
	public List<Concept> obtenerCausasAtencion(){
		ConceptsCode codes = new ConceptsCode();
		
		return conceptDAO.getSet(codes.cCausasAtencion());
	}
	
	public List<Concept> obtenerCausasAtencionActive(){
		ConceptsCode codes = new ConceptsCode();
		
		return conceptDAO.getSetActive(codes.cCausasAtencion());
	}
	
	public List<Concept> obtenerZonas(){
		ConceptsCode codes = new ConceptsCode();
		
		return conceptDAO.getSet(codes.cZonas());
	}
	
	public List<Concept> obtenerEstadosCiviles(){
		ConceptsCode codes = new ConceptsCode();
		
		return conceptDAO.getSet(codes.cEstadosCiviles());
	}
	
	public List<Concept> obtenerAseguradoras(){
		ConceptsCode codes = new ConceptsCode();
		
		return conceptDAO.getSet(codes.cAseguradoras());
	}

	public List<Concept> obtenerAseguradorasActive(){
		ConceptsCode codes = new ConceptsCode();
		
		return conceptDAO.getSetActive(codes.cAseguradoras());
	}
	public List<Concept> getProcedimientosSoporteVitalBasico(){
		ConceptsCode codes = new ConceptsCode();
		return conceptDAO.getSetActive(codes.cListaProcedimientosSoporteVitalBasico());
	}
	public List<Concept> getProcedimientosSoporteVitalAvanzado(){
		ConceptsCode codes = new ConceptsCode();
		return conceptDAO.getSetActive(codes.cListaProcedimientosSoporteVitalAvanzado());
	}
	public List<Concept> getProcedimientosOtros(){
		ConceptsCode codes = new ConceptsCode();
		return conceptDAO.getSetActive(codes.cListaProcedimientosOtros());
	}
	public List<Concept> getExposiciones(){
		ConceptsCode codes = new ConceptsCode();
		return conceptDAO.getSetActive(codes.cListaExposiciones());
	}
	public List<Concept> getRespuestasOculares(){
		ConceptsCode codes = new ConceptsCode();
		return conceptDAO.getSetActive(codes.cListaRespuestasOculares());
	}
	public List<Concept> obtenerEpss(){
		ConceptsCode codes = new ConceptsCode();
		
		return conceptDAO.getSet(codes.cEpss());
	}

	public List<Concept> obtenerEpssActive(){
		ConceptsCode codes = new ConceptsCode();
		
		return conceptDAO.getSetActive(codes.cEpss());
	}
	
	public List<Concept> obtenerPlanesBeneficios(){
		ConceptsCode codes = new ConceptsCode();
		
		return conceptDAO.getSet(codes.cPlanesBeneficios());
	}

	public List<Concept> obtenerPlanesBeneficiosActive(){
		ConceptsCode codes = new ConceptsCode();
		
		return conceptDAO.getSetActive(codes.cPlanesBeneficios());
	}
	
	public List<Concept> obtenerTiposAntecedentes(){
		ConceptsCode codes = new ConceptsCode();
		
		return conceptDAO.getSet(codes.cTiposAntecedentes());
	}
	public List<Concept> getRespondientes(){
		ConceptsCode codes = new ConceptsCode();
		
		return conceptDAO.getSet(codes.cListaRespondientes());
	}
	public List<Concept> getTipoEmergencias(){
		ConceptsCode codes = new ConceptsCode();
		return conceptDAO.getSet(codes.cTipoEmergencias());
	}
	public List<Concept> getCausasObstruccionViaAerea(){
		ConceptsCode codes = new ConceptsCode();
		return conceptDAO.getSet(codes.cCausasObstruccion());
	}
	public List<Concept> getResultadosAtencion(){
		ConceptsCode codes = new ConceptsCode();
		return conceptDAO.getSet(codes.cListaResultadosAtencion());
	}
	public List<Concept> getNegacionesPaciente(){
		ConceptsCode codes = new ConceptsCode();
		return conceptDAO.getSet(codes.cListaNegacionesPaciente());
	}
	public List<Concept> obtenerEntidadesRecepcion(){
		ConceptsCode codes = new ConceptsCode();
		
		return conceptDAO.getSet(codes.cEntidadesRecepcion());
	}
	
	public List<Concept> obtenerMediosSolicitudTeleasistencia(){
		ConceptsCode codes = new ConceptsCode();
		
		return conceptDAO.getSet(codes.cMediosSolicitudTeleasistencia());
	}	
	public List<Concept> getPermeabilidadesViaAerea(){
		ConceptsCode codes = new ConceptsCode();
		
		return conceptDAO.getSet(codes.cPermeabilidadesViaAerea());
	}
	public List<Concept> getPresenciasRespiracion(){
		ConceptsCode codes = new ConceptsCode();
		return conceptDAO.getSet(codes.cListaPresenciaRespiracion());
	}
	public List<Concept> getEstadosRespiracion(){
		ConceptsCode codes = new ConceptsCode();
		return conceptDAO.getSet(codes.cListaEstadosRespiracion());
	}
	public List<Concept> getCianosisRespiracion(){
		ConceptsCode codes = new ConceptsCode();
		return conceptDAO.getSet(codes.cListaCianosis());
	}
	public List<Concept> obtenerControlesAPH(){
		ConceptsCode codes = new ConceptsCode();
		return conceptDAO.getSet(codes.cControlesAPH());
	}
	
	public List<Concept> obtenerDiagnosticos(String query){
		return conceptDAO.getFindings(query, 0, -1);
	}
	public List<Concept> getLesiones()
	{
		ConceptsCode codes = new ConceptsCode();
		return conceptDAO.getSetActive(codes.cLesiones());
	}
	public List<Concept> obtenerProcedimientos(String query){
		return conceptDAO.getProcedures(query, 0, -1);
	}
	
	public List<Concept> obtenerProcedimientosHallazgos(String query){
		ConceptsCode codes = new ConceptsCode();
		return conceptDAO.getConceptsBySet(query, 0, -1, codes.cProcedimientosHallazgo());
	}

	public List<Concept> obtenerProcedimientosHallazgosActive(String query){
		ConceptsCode codes = new ConceptsCode();
		return conceptDAO.getConceptsBySetActive(query, 0, -1, codes.cProcedimientosHallazgo());
	}
	
	public Concept update(Concept concept) {
		return conceptDAO.update(concept);
	}

	public Concept save(Concept concept) {
		return conceptDAO.insert(concept);
	}

	public ConceptSet saveConceptSet(ConceptSet concept) {
		return conceptSetDAO.insert(concept);
	}
}
