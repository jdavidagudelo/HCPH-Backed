package com.artica.telesalud.client.shared;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModel;

public class DataTextos extends BaseModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String 	VALUE_FIELD = "value",
								LABEL_FIELD = "label";
	
	public DataTextos(){
		super();
	}
//	public Data(Concept concept){
//		super();
//		set(VALUE_FIELD, concept.getConceptId());
//		set(LABEL_FIELD, concept.getDescription());
//	}
	public DataTextos(String value, String label){
		super();
		set(VALUE_FIELD, value);
		set(LABEL_FIELD, label);
	}
	public String getLabel(){
		return get(LABEL_FIELD);
	}
	public void setLabel(String label){
		set(LABEL_FIELD, label);
	}
	public String getValue(){
		return get(VALUE_FIELD);
	}
	public void setValue(String value){
		set(VALUE_FIELD, value);
	}
	@Override
	public String toString() {
		return get(LABEL_FIELD);
	}
	
}
