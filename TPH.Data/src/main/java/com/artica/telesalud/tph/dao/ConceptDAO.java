package com.artica.telesalud.tph.dao;

import java.util.List;

import com.artica.telesalud.tph.model.concept.Concept;

public interface ConceptDAO {
	
	public List<Concept> getAll();
	public Concept insert(Concept concept);
	public Concept update(Concept concept);
	public Concept newConcept();
	public Concept delete(Concept concept);
	public List<Concept> getFindings(String query, int maxResult, int page);
	public Long getTotalFindings(String query);
	public List<Concept> getSet(Integer conceptSet);
	public List<Concept> getSetActive(Integer conceptSet);
	public List<Concept> getConceptsByClass(String query, int maxResult, int offset, Integer intClass);
	public Long getTotalConceptsByClass(String query, Integer intClass);
	public List<Concept> getProcedures(String query, int maxResult, int offset);
	public Long getTotalProcedures(String query);
	public Concept getConcept(Integer concept);
	public List<Concept> getDrugs(String query, int maxResult, int offset);
	public Long getTotalDrugs(String query);
	public List<Concept> getConceptsByClass(Integer intClass);
	public List<Concept> getConceptsBySet(String query, int maxResult, int offset, Integer intSet);
	public List<Concept> getConceptsBySetActive(String query, int maxResult, int offset, Integer intSet);
	public Long getTotalConceptsBySet(String query, Integer intSet);

}
