package com.artica.telesalud.client.shared;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.store.ListStore;

public class Data extends BaseModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String 	VALUE_FIELD = "value",
								LABEL_FIELD = "label";
	
	public Data(){
		super();
	}
//	public Data(Concept concept){
//		super();
//		set(VALUE_FIELD, concept.getConceptId());
//		set(LABEL_FIELD, concept.getDescription());
//	}
	public Data(Integer value, String label){
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
	public Integer getValue(){
		return get(VALUE_FIELD);
	}
	public void setValue(Integer value){
		set(VALUE_FIELD, value);
	}
	@Override
	public String toString() {
		return get(LABEL_FIELD);
	}
	
	public static Data obtenerValor(ListStore<Data> datos, Integer valor){
		Data resultado = null;
		
		for(Data dato:datos.getModels()){
			if(dato.getValue().equals(valor))
				resultado = dato;
		}
		
		return resultado;
	}
	
	public static Data obtenerValor(ListStore<Data> datos, String valor){
		Data resultado = null;
		
		for(Data dato:datos.getModels()){
			if(dato.getLabel().equals(valor))
				resultado = dato;
		}
		
		return resultado;
	}
	
}
