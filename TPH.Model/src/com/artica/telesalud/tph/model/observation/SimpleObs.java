package com.artica.telesalud.tph.model.observation;

public class SimpleObs {

	private Integer concept;
	private Object value;
	
	
	public SimpleObs(Integer concept, Object value) {
		super();
		this.concept = concept;
		this.value = value;
	}
	public Integer getConcept() {
		return concept;
	}
	public void setConcept(Integer concept) {
		this.concept = concept;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
}
