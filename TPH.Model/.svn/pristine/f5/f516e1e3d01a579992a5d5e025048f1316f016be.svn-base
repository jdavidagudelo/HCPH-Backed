package com.artica.telesalud.tph.model.observation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ObservationData implements Serializable{
	
	private static final long serialVersionUID = 1000975L;
	
	private Integer obsId;
	private Integer conceptId;
	private Integer typeId;
	private Integer valueBoolean;
	private Integer valueCoded;
	private Date valueDatetime;
	private Double valueNumeric;
	private String valueText;
	private Date dateCreated;
	private Integer creator;
	private List<ObservationData> sons = new ArrayList<ObservationData>();
	
	public void setObsId(Integer obsId) {
		this.obsId = obsId;
	}
	public Integer getObsId() {
		return obsId;
	}
	public void setConceptId(Integer conceptId) {
		this.conceptId = conceptId;
	}
	public Integer getConceptId() {
		return conceptId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setValueBoolean(Integer valueBoolean) {
		this.valueBoolean = valueBoolean;
	}
	public Integer getValueBoolean() {
		return valueBoolean;
	}
	public void setValueCoded(Integer valueCoded) {
		this.valueCoded = valueCoded;
	}
	public Integer getValueCoded() {
		return valueCoded;
	}
	public void setValueDatetime(Date valueDatetime) {
		this.valueDatetime = valueDatetime;
	}
	public Date getValueDatetime() {
		return valueDatetime;
	}
	public void setValueNumeric(Double valueNumeric) {
		this.valueNumeric = valueNumeric;
	}
	public Double getValueNumeric() {
		return valueNumeric;
	}
	public void setValueText(String valueText) {
		this.valueText = valueText;
	}
	public String getValueText() {
		return valueText;
	}
	public void setSons(List<ObservationData> sons) {
		this.sons = sons;
	}
	public List<ObservationData> getSons() {
		return sons;
	}
	
	public static Object obtenerValorConcepto(List<ObservationData> lista, Integer concepto){
		
		Object valor = null;
		
		for(ObservationData obs : lista){
			if(obs.getConceptId().equals(concepto)){
				
				if(obs.getValueText() != null)
					valor = obs.getValueText();
				else if(obs.getValueBoolean() != null)
					valor = (obs.getValueBoolean() == 1)? true:false;
				else if(obs.getValueNumeric() != null)
					valor = obs.getValueNumeric();
				else if(obs.getValueCoded() != null)
					valor = obs.getValueCoded();
				else if(obs.getValueDatetime() != null)
					valor = obs.getValueDatetime();
							

				break;
			}
		}
		
		return valor;
	}
	
	public static ObservationData obtenerValor(List<ObservationData> lista, Integer concepto){
		
		ObservationData valor = null;
		
		for(ObservationData obs : lista){
			if(obs.getConceptId().equals(concepto)){
				
				valor = obs; 
				
				break;
			}
		}
		
		return valor;
		
	}
	
	public static List<ObservationData> obtenerListaValores(List<ObservationData> lista, Integer concepto){
		
		List<ObservationData> observationsData = new ArrayList<ObservationData>();
		
		for(ObservationData obs : lista){
			if(obs.getConceptId().equals(concepto)){
				observationsData.add(obs);
			}
		}
		
		return observationsData;
		
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	
}
