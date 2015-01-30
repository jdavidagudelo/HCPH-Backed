package com.artica.telesalud.tph.lightweightmodel;

import java.io.Serializable;

import com.artica.telesalud.client.shared.ValuedItem;
import com.artica.telesalud.tph.client.hallazgo.i18n.NombreLesiones;
import com.artica.telesalud.tph.lightweightmodel.HallazgoData.Lesiones;
import com.google.gwt.core.client.GWT;

@SuppressWarnings("serial")
public class LesionResumen implements Serializable{
	private int id;
	private String hallazgos, procedimientos;
	private HallazgoData hallazgo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHallazgos() {
		return hallazgos;
	}
	public void setHallazgos(String hallazgos) {
		this.hallazgos = hallazgos;
	}
	public String getProcedimientos() {
		return procedimientos;
	}
	public void setProcedimientos(String procedimientos) {
		this.procedimientos = procedimientos;
	}
	public HallazgoData getHallazgo(){
		return hallazgo;
	}
	
	public void adapt(HallazgoData hallazgoData) {
		this.hallazgo = hallazgoData;
		
		this.setId(hallazgoData.getOrden());
		
		NombreLesiones nombreLesiones = GWT.create(NombreLesiones.class);
		
		
		StringBuilder hallazgos = new StringBuilder();
		for(int i = 0; i < hallazgoData.getLesiones().size(); i++){
			Lesiones l = hallazgoData.getLesiones().get(i);
			hallazgos.append( nombreLesiones.getString(l.name()));
			
			if( i != hallazgoData.getLesiones().size() - 1)
				hallazgos.append( ", ");
		}
		
		this.setHallazgos(hallazgos.toString());
		
		StringBuilder procedimientos = new StringBuilder();
		
		for(int i = 0; i < hallazgoData.getProcedimientos().size(); i++){
			ValuedItem<Integer, String> procedimiento = hallazgoData.getProcedimientos().get(i);
			procedimientos.append(procedimiento.getLabel());
			
			if( i != hallazgoData.getProcedimientos().size() - 1)
				procedimientos.append( ", ");
		}
		
		this.setProcedimientos(procedimientos.toString());
	}
	
	
}
